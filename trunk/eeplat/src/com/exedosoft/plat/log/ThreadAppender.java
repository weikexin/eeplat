package com.exedosoft.plat.log;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.id.UUIDHex;

/**
 * The JDBCAppender provides for sending log events to a database.
 * 
 * <p>
 * <b><font color="#FF2222">WARNING: This version of JDBCAppender is very likely
 * to be completely replaced in the future. Moreoever, it does not log
 * exceptions</font></b>.
 * 
 * <p>
 * Each append call adds to an <code>ArrayList</code> buffer. When the buffer is
 * filled each log event is placed in a sql statement (configurable) and
 * executed.
 * 
 * <b>BufferSize</b>, <b>db URL</b>, <b>User</b>, & <b>Password</b> are
 * configurable options in the standard log4j ways.
 * 
 * <p>
 * The <code>setSql(String sql)</code> sets the SQL statement to be used for
 * logging -- this statement is sent to a <code>PatternLayout</code> (either
 * created automaticly by the appender or added by the user). Therefore by
 * default all the conversion patterns in <code>PatternLayout</code> can be used
 * inside of the statement. (see the test cases for examples)
 * 
 * <p>
 * Overriding the {@link #getLogStatement} method allows more explicit control
 * of the statement used for logging.
 * 
 * <p>
 * For use as a base class:
 * 
 * <ul>
 * 
 * <li>Override <code>getConnection()</code> to pass any connection you want.
 * Typically this is used to enable application wide connection pooling.
 * 
 * <li>Override <code>closeConnection(Connection con)</code> -- if you override
 * getConnection make sure to implement <code>closeConnection</code> to handle
 * the connection you generated. Typically this would return the connection to
 * the pool it came from.
 * 
 * <li>Override <code>getLogStatement(LoggingEvent event)</code> to produce
 * specialized or dynamic statements. The default uses the sql option value.
 * 
 * </ul>
 * 
 * @author Kevin Steppe (<A
 *         HREF="mailto:ksteppe@pacbell.net">ksteppe@pacbell.net</A>)
 */
public class ThreadAppender extends org.apache.log4j.AppenderSkeleton implements
		org.apache.log4j.Appender {

	/**
	 * URL of the DB for default connection handling
	 */
	protected String databaseURL = "jdbc:odbc:myDB";

	/**
	 * User to connect as for default connection handling
	 */
	protected String databaseUser = "me";

	/**
	 * User to use for default connection handling
	 */
	protected String databasePassword = "mypassword";

	/**
	 * Connection used by default. The connection is opened the first time it is
	 * needed and then held open until the appender is closed (usually at
	 * garbage collection). This behavior is best modified by creating a
	 * sub-class and overriding the <code>getConnection</code> and
	 * <code>closeConnection</code> methods.
	 */
	protected Connection connection = null;

	/**
	 * Stores the string given to the pattern layout for conversion into a SQL
	 * statement, eg: insert into LogTable (Thread, Class, Message) values
	 * ("%t", "%c", "%m").
	 * 
	 * Be careful of quotes in your messages!
	 * 
	 * Also see PatternLayout.
	 */
	protected String sqlStatement = "";

	/**
	 * size of LoggingEvent buffer before writting to the database. Default is
	 * 100.
	 */
	protected int bufferSize = 100;

	private boolean locationInfo = false;

	private static  boolean IS_LOG_EXITS = false;
	


	private static final ThreadLocal<List> currentThread = new ThreadLocal<List>();

	public ThreadAppender() {
		super();
		if("true".equals(DOGlobals.getValue("log.db"))){
			IS_LOG_EXITS = true;
		}
		System.out.println("IS_LOG_EXITS::::::::::::" + IS_LOG_EXITS);

	}

	/**
	 * Gets whether the location of the logging request call should be captured.
	 * 
	 * @since 1.2.16
	 * @return the current value of the <b>LocationInfo</b> option.
	 */
	public boolean getLocationInfo() {
		return locationInfo;
	}

	/**
	 * The <b>LocationInfo</b> option takes a boolean value. By default, it is
	 * set to false which means there will be no effort to extract the location
	 * information related to the event. As a result, the event that will be
	 * ultimately logged will likely to contain the wrong location information
	 * (if present in the log format).
	 * <p/>
	 * <p/>
	 * Location information extraction is comparatively very slow and should be
	 * avoided unless performance is not a concern.
	 * </p>
	 * 
	 * @since 1.2.16
	 * @param flag
	 *            true if location information should be extracted.
	 */
	public void setLocationInfo(final boolean flag) {
		locationInfo = flag;
	}

	/**
	 * Adds the event to the buffer. When full the buffer is flushed.
	 */
	public void append(LoggingEvent event) {

		if (!IS_LOG_EXITS) {
			return;
		}

		event.getNDC();
		event.getThreadName();
		// Get a copy of this thread's MDC.
		event.getMDCCopy();
		if (locationInfo) {
			event.getLocationInformation();
		}
		event.getRenderedMessage();
		event.getThrowableStrRep();

		List threadLogs = currentThread.get();
		if (threadLogs == null) {
			threadLogs = new ArrayList();
		}
		currentThread.set(threadLogs);
		// /查询日志的面板
		if ("Pml the servletPath:PM_do_log_dev_Result.pml".equals(event
				.getMessage())) {
			threadLogs.add(0, "SearchLogResult");
		}
		if (threadLogs.size() > 0
				&& threadLogs.get(0).equals("SearchLogResult")) {
			if ("The thread is end!".equals(event.getMessage())) {
				threadLogs.clear();
			}
		} else {
			if ("The thread is end!".equals(event.getMessage())
					|| threadLogs.size() > this.bufferSize) {
				flushBuffer(event);
			} else {
				threadLogs.add(event);
			}
		}
	}

	/**
	 * By default getLogStatement sends the event to the required Layout object.
	 * The layout will format the given pattern into a workable SQL string.
	 * 
	 * Overriding this provides direct access to the LoggingEvent when
	 * constructing the logging statement.
	 * 
	 */
	protected String getLogStatement(LoggingEvent event) {
		return getLayout().format(event);
	}

	/**
	 * 
	 * Override this to provide an alertnate method of getting connections (such
	 * as caching). One method to fix this is to open connections at the start
	 * of flushBuffer() and close them at the end. I use a connection pool
	 * outside of JDBCAppender which is accessed in an override of this method.
	 * */
	protected void execute(String sql) throws SQLException {

		Connection con = null;
		Statement stmt = null;

		try {
			con = getConnection();

			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			closeConnection(con);
		}

		// System.out.println("Execute: " + sql);
	}

	/**
	 * Override this to return the connection to a pool, or to clean up the
	 * resource.
	 * 
	 * The default behavior holds a single connection open until the appender is
	 * closed (typically when garbage collected).
	 */
	protected void closeConnection(Connection con) {
	}

	/**
	 * Override this to link with your connection pooling system.
	 * 
	 * By default this creates a single connection which is held open until the
	 * object is garbage collected.
	 */
	protected Connection getConnection() throws SQLException {
		if (!DriverManager.getDrivers().hasMoreElements())
			setDriver("sun.jdbc.odbc.JdbcOdbcDriver");

		if (connection == null) {
			connection = DriverManager.getConnection(databaseURL, databaseUser,
					databasePassword);
		}

		return connection;
	}

	/**
	 * Closes the appender, flushing the buffer first then closing the default
	 * connection if it is open.
	 */
	public void close() {

		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			errorHandler.error("Error closing connection", e,
					ErrorCode.GENERIC_FAILURE);
		}
		this.closed = true;
	}

//	private static boolean checkExistDevLog() {
//
//		DODataSource dss = null;
//		Connection con = null;
//		try {
//			DOBO boLog = DOBO.getDOBOByName("do_log_dev");
//			if (boLog == null) {
//				return false;
//			}
//
//			if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {
//				if(DOGlobals.getInstance().getSessoinContext()
//						.getTenancyValues()==null){
//					return false;
//				}
//
//				dss = DOGlobals.getInstance().getSessoinContext()
//						.getTenancyValues().getDataDDS();
//			} else {
//
//				DOBO bo = DOBO.getDOBOByName("do_datasource");
//				if (bo == null) {
//					return false;
//				}
//				dss = bo.getDataBase();
//			}
//
//			con = dss.getConnection();
//
//			String sql = "select * from do_log_dev";
//
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			pstmt.close();
//		} catch (Exception e) {
//			return false;
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//		} finally {
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					// e.printStackTrace();
//					return false;
//				}
//			}
//		}
//
//		return true;
//
//	}

	/**
	 * loops through the buffer of LoggingEvents, gets a sql string from
	 * getLogStatement() and sends it to execute(). Errors are sent to the
	 * errorHandler.
	 * 
	 * If a statement fails the LoggingEvent stays in the buffer!
	 */
	public void flushBuffer(LoggingEvent event) {

		List threadLogs = currentThread.get();

		StringBuffer sb = new StringBuffer()
				.append("<table class='tablesorter' border='0' cellpadding='1' cellspacing='1' >");
		for (Iterator i = threadLogs.iterator(); i.hasNext();) {
			LoggingEvent logEvent = (LoggingEvent) i.next();
			try {
				// logEvent.getMessage()
				// logEvent.
				sb.append(this.format(logEvent));
				// String sql = getLogStatement(logEvent);
				// execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append("</table>");

		String escapedThread = Transform.escapeTags(event.getThreadName());
		String userName = "";
		String ip = "";
		if (DOGlobals.getInstance().getSessoinContext() != null) {
			ip = DOGlobals.getInstance().getSessoinContext().getIp();
			BOInstance user = DOGlobals.getInstance().getSessoinContext()
					.getUser();
			if (user != null) {
				userName = user.getName();
			}
		}

		DODataSource dss = null;
		if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {

			if (DOGlobals.getInstance().getSessoinContext().getTenancyValues() != null) {
				dss = DOGlobals.getInstance().getSessoinContext()
						.getTenancyValues().getDataDDS();
			}else{
				return;
			}
		} else {

			DOBO bo = DOBO.getDOBOByName("do_datasource");
			dss = bo.getDataBase();
		}

		Connection con = dss.getConnection();

		if (con != null) {
			String sql = "insert into do_log_dev(id,threadstr,starttime,username,ip,msgstr) values(?,?,?,?,?,?)";
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, UUIDHex.getInstance().generate());
				pstmt.setString(2, escapedThread);
				pstmt.setTimestamp(3, new java.sql.Timestamp(event.timeStamp));
				pstmt.setString(4, userName);
				pstmt.setString(5, ip);
				pstmt.setString(6, sb.toString());
				pstmt.execute();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		sb.setLength(0);
		threadLogs.clear();
	}

	public String format(LoggingEvent event) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);

		buffer.append("<td title=\"Level\">");
		if (event.getLevel().equals(Level.DEBUG)) {
			buffer.append("<font color=\"#339933\">");
			buffer.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			buffer.append("</font>");
		} else if (event.getLevel().isGreaterOrEqual(Level.WARN)) {
			buffer.append("<font color=\"#993300\"><strong>");
			buffer.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			buffer.append("</strong></font>");
		} else {
			buffer.append(Transform.escapeTags(String.valueOf(event.getLevel())));
		}
		buffer.append("</td>" + Layout.LINE_SEP);

		String escapedLogger = Transform.escapeTags(event.getLoggerName());
		escapedLogger = escapedLogger
				.substring(escapedLogger.lastIndexOf('.') + 1);
		buffer.append("<td title=\"" + escapedLogger + " category\">");
		buffer.append(escapedLogger);
		buffer.append("</td>" + Layout.LINE_SEP);

		if (this.locationInfo) {
			LocationInfo locInfo = event.getLocationInformation();
			buffer.append("<td>");
			buffer.append(Transform.escapeTags(locInfo.getFileName()));
			buffer.append(':');
			buffer.append(locInfo.getLineNumber());
			buffer.append("</td>" + Layout.LINE_SEP);
		}
		buffer.append("<td title=\"Message\">");
		buffer.append(Transform.escapeTags(event.getRenderedMessage()));
		buffer.append("</td>" + Layout.LINE_SEP);
		buffer.append("</tr>");
		return buffer.toString();
	}

	/** closes the appender before disposal */
	public void finalize() {
		close();
	}

	/**
	 * JDBCAppender requires a layout.
	 * */
	public boolean requiresLayout() {
		return true;
	}

	/**
	   *
	   */
	public void setSql(String s) {
		sqlStatement = s;
		if (getLayout() == null) {
			this.setLayout(new PatternLayout(s));
		} else {
			((PatternLayout) getLayout()).setConversionPattern(s);
		}
	}

	/**
	 * Returns pre-formated statement eg: insert into LogTable (msg) values
	 * ("%m")
	 */
	public String getSql() {
		return sqlStatement;
	}

	public void setUser(String user) {
		databaseUser = user;
	}

	public void setURL(String url) {
		databaseURL = url;
	}

	public void setPassword(String password) {
		databasePassword = password;
	}

	public void setBufferSize(int newBufferSize) {
		bufferSize = newBufferSize;
	}

	public String getUser() {
		return databaseUser;
	}

	public String getURL() {
		return databaseURL;
	}

	public String getPassword() {
		return databasePassword;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	/**
	 * Ensures that the given driver class has been loaded for sql connection
	 * creation.
	 */
	public void setDriver(String driverClass) {
		try {
			Class.forName(driverClass);
		} catch (Exception e) {
			errorHandler.error("Failed to load driver", e,
					ErrorCode.GENERIC_FAILURE);
		}
	}

	public static void main(String[] args) {

//		String aClass = "com.exedosoft.SearchImp";
//		System.out.println(aClass.substring(aClass.lastIndexOf('.') + 1));
		CacheFactory.getCacheData().fromSerialObject();
		DOBO  logDev = DOBO.getDOBOByName("do_log_dev");
		System.out.println("logDev::" + logDev.getDataBase());

	}
}
