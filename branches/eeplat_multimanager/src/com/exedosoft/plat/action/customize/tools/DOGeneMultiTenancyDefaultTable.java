package com.exedosoft.plat.action.customize.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.ExcuteSqlFile;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.util.id.UUIDHex;

public class DOGeneMultiTenancyDefaultTable extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 558266835833057020L;

	private static Log log = LogFactory
			.getLog(DOGeneMultiTenancyDefaultTable.class);

	@Override
	public String excute() throws ExedoException {

		DOBO aBO = DOBO.getDOBOByName("multi_tenancy");
		BOInstance curTenant = aBO.getCorrInstance();

		if (aBO == null || curTenant == null) {
			this.setEchoValue("没有定义租户！");
			return this.NO_FORWARD;
		}

		if (curTenant.getValue("multi_datasource_uid") != null
				&& curTenant.getValue("model_datasource_uid") != null) {
			this.setEchoValue("该租户已经创建!");
			return this.NO_FORWARD;
		}

		// //租户预分配一个业务数据源，这个业务数据源后续要参与数据源的调度
		// / 这个数据源有可能不是最终的数据源
		// / 后面的代码有一个set multi_datasource_uid
		String dsHostUid = "basehost_1";
		if (curTenant.getValue("datasource_host_uid") != null) {
			dsHostUid = curTenant.getValue("datasource_host_uid");
		}

		DOService findDSHost = DOService
				.getService("multi_datasource_host_browse");
		BOInstance biHost = findDSHost.getInstance(dsHostUid);

		if (biHost == null) {
			this.setEchoValue("数据库主机没有分配!");
			return this.NO_FORWARD;
		}
		DODataSource dss = this.getDataSource(biHost);

		String pwd = UUIDHex.getInstance().generate();

		curTenant = this.dealMultiDSS(curTenant, biHost, pwd);
		if (curTenant == null
				|| curTenant.getValue("multi_datasource_uid") == null
				|| curTenant.getValue("model_datasource_uid") == null) {
			this.setEchoValue("定义过程出错!");
			return this.NO_FORWARD;
		}

		// /创建数据库
		boolean bInit = createDataBase(curTenant, dss, pwd);
		if (!bInit) {
			this.setEchoValue("创建租户数据库出错！");
			return this.NO_FORWARD;
		}

		// /初始化租户配置库
		initModelDatabase(curTenant);

		// /初始化租户业务库
		initBusiDatabase(curTenant);


		this.setEchoValue("初始化成功！");
		return DEFAULT_FORWARD;

	}

	private boolean createDataBase(BOInstance curTenant, DODataSource dss,
			String pwd) {
		Connection con = dss.getConnection();

		try {

			con.setAutoCommit(false);

			if (curTenant != null) {
				String tenantName = curTenant.getValue("name").toLowerCase();
				// ///创建库
				String createDataDB = "CREATE DATABASE IF NOT EXISTS "
						+ tenantName
						+ "_data default charset utf8 COLLATE utf8_general_ci";
				String createModelDB = "CREATE DATABASE IF NOT EXISTS "
						+ tenantName
						+ "_model default charset utf8 COLLATE utf8_general_ci";

				Statement stmt = con.createStatement();
				// ///创建库
				stmt.execute(createModelDB);
				stmt.execute(createDataDB);

				con.commit();
				createUserPwd(pwd, tenantName, stmt,"'%'");
				createUserPwd(pwd, tenantName, stmt,"localhost");
				
				con.commit();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				this.setEchoValue(ex1.getMessage());
				return false;
			}
		}

		return true;
	}

	private void createUserPwd(String pwd, String tenantName, Statement stmt,String location)
			throws SQLException {
		// //创建mysql 用户、密码.
		String createUser = new StringBuffer("create user ")
				.append(tenantName).append("@").append(location).append(" IDENTIFIED BY '")
				.append(pwd).append("'").toString();

		////模型库只有管理员可见
		
		String grantData = new StringBuffer("GRANT ALL ON ")
				.append(tenantName).append("_data.*  to ")
				.append(tenantName).append("@").append(location).append("").toString();

//		String grantModel = new StringBuffer("GRANT ALL ON ")
//				.append(tenantName).append("_model.*  to ")
//				.append(tenantName).append("@").append(location).append("").toString();

		stmt.execute(createUser);
		stmt.execute(grantData);
//		stmt.execute(grantModel);
	}

	private void initBusiDatabase(BOInstance curTenant) {
		// //DataSource
		String multi_datasource_uid = curTenant
				.getValue("multi_datasource_uid");

		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf"));

		// ///////////////业务库
		DOService findDataSource = DOService
				.getService("multi_datasource_browse");
		BOInstance aBIDataDS = findDataSource.getInstance(multi_datasource_uid);

		DODataSource dssBusi = this.getDataSource(aBIDataDS);
		if (dssBusi != null) {
			String baseFile = prefix + "/exedo/initsql/mysql.sql";

			System.out.println("DssBusi:::::::::" + dssBusi);

			excuteSQL(dssBusi, baseFile);

			String defaultDataSql = prefix + "/exedo/initsql/default_data.sql";
			excuteSQL(dssBusi, defaultDataSql);
		}

	}

	private void initModelDatabase(BOInstance curTenant) {
		// //DataSource
		String model_datasource_uid = curTenant
				.getValue("model_datasource_uid");

		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf"));

		// ///////////////业务库
		DOService findDataSource = DOService
				.getService("multi_datasource_browse");
		// /////////////////配置库
		BOInstance aBIModelDS = findDataSource
				.getInstance(model_datasource_uid);
		if (aBIModelDS != null) {
			DODataSource dssModel = this.getDataSource(aBIModelDS);

			System.out.println("dssModel:::::::::" + dssModel);

			String fileName = prefix + "/exedo/initsql/default_model.sql";
			excuteSQL(dssModel, fileName);
		}
	}

	private void excuteSQL(DODataSource dss, String fileName) {

		log.info("The DataSource:::::::::::" + dss);
		Connection con = dss.getConnection();
		try {
			con.setAutoCommit(false);

			// List<String> sqls = readSqlFile(fileName);
			// Statement stmt = con.createStatement();
			// int i = 0;
			// for (String sql : sqls) {
			// i++;
			//
			// if (sql != null && !sql.trim().equals("")) {
			// System.out.println("正在执行SQL语句:::" + sql);
			// stmt.addBatch(sql);
			// }
			//
			// if (i > 100) {// / 100 个sql语句为一个batch
			// stmt.executeBatch();
			// stmt.clearBatch();
			// i = 0;
			// }
			// }
			// if (i != 0) {
			// stmt.executeBatch();
			// }

			List<String> sqls = this.readSqlFile(fileName);
			Statement pstmt = con.createStatement();
			for (String sql : sqls) {
				if (sql != null && !sql.trim().equals("")) {
					System.out.println("正在执行SQL语句:::" + sql);
					pstmt.execute(sql);
				}
			}
			con.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 从文件读放内容到按分号放到sqlFileList
	public static List<String> readSqlFile(String fileName) {

		List<String> sqlList = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName), "utf-8"));

			StringBuffer aSql = new StringBuffer();

			while (true) {
				String aLine = in.readLine();
				if (aLine == null) {
					break;
				}
				if (aLine.startsWith("/*")) {
					continue;
				}
				aSql.append(aLine);
				if (aLine.trim().endsWith(";")) {
					sqlList.add(aSql.toString());
					aSql.setLength(0);
				}
			}
			in.close();

		} catch (IOException e) {
			e.getStackTrace();
		}

		return sqlList;
	}

	private BOInstance dealMultiDSS(BOInstance curTenant, BOInstance biHost,
			String pwd) {

		if (curTenant == null) {
			return curTenant;
		}

		String url = biHost.getValue("driver_url");
		String tenantName = curTenant.getValue("name").toLowerCase();
		// ////////去掉objuid
		biHost.getMap().remove("objuid");
		String driverUrl = url.replace("information_schema", tenantName
				+ "_model");
		biHost.putValue("driver_url", driverUrl);
		biHost.putValue("l10n", tenantName + "_modeldata");

		DOService insertDSS = DOService.getService("multi_datasource_insert");
		Transaction t = insertDSS.currentTransaction();



		t.begin();
		try {
			// //模型数据
			BOInstance modelDS = insertDSS.invokeUpdate(biHost);

			// //应用数据
			driverUrl = url
					.replace("information_schema", tenantName + "_data");
			biHost.putValue("driver_url", driverUrl);
			biHost.putValue("username", tenantName);
			biHost.putValue("password", pwd);
			biHost.putValue("l10n", tenantName + "_busidata");

			BOInstance dataDS = insertDSS.invokeUpdate(biHost);

			curTenant.putValue("model_datasource_uid", modelDS.getUid());
			curTenant.putValue("multi_datasource_uid", dataDS.getUid());

			DOService updateTenant = DOService
					.getService("multi_tenancy_update");
			curTenant = updateTenant.invokeUpdate(curTenant);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t.rollback();
			return null;
		} finally {
			t.end();
		}
		return curTenant;
	}

	private DODataSource getDataSource(BOInstance biDataSource) {

		System.out.println("biDataSource:::::::" + biDataSource);
		DODataSource dss = new DODataSource();
		// /uid
		
		System.out.println("DODataSource.objuid:::::" + biDataSource.getUid());
		dss.setObjUid(biDataSource.getUid());
		dss.setDialect(biDataSource.getValue("dialect").trim());
		dss.setDriverClass(biDataSource.getValue("driver_class").trim());
		dss.setDriverUrl(biDataSource.getValue("driver_url").trim());
		dss.setUserName(biDataSource.getValue("username").trim());
		if (biDataSource.getValue("password") != null) {
			dss.setPassword(biDataSource.getValue("password").trim());
		}
		return dss;

	}

	public static void main(String[] args) {

		// DIALECT_ORACLE=oracle
		// DIALECT_SQLSERVER=sqlserver
		// DIALECT_DB2=db2
		// DIALECT_MYSQL=mysql
		// DIALECT_HSQLDB=hqldb
		// DIALECT_DEFAULT=default
		// DIALECT_FROM_CONFIG=fromconfig
		// dialect=mysql
		// jndiName=null
		// l10n=null
		// driverClass=com.mysql.jdbc.Driver
		// driverUrl=jdbc:mysql://127.0.0.1:3306/mypaas_data?useUnicode=true&characterEncoding=utf-8
		// userName=mypaas
		// poolType=null
		// poolsize=null
		// otherparas=null
		// password=4028803b3690fea5013690fed5c60001
		// isInit=null

		DOGeneMultiTenancyDefaultTable dt = new DOGeneMultiTenancyDefaultTable();

		// DOService findDataSource = DOService
		// .getService("multi_datasource_browse");
		// BOInstance aBIDataDS = findDataSource
		// .getInstance("4028803b369045ed013690460f390002");
		//
		//
		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf"));
		//
		// DODataSource dssBusi = dt.getDataSource(aBIDataDS);

		DODataSource dss = new DODataSource();
		dss.setDialect("mysql");
		dss.setDriverClass("com.mysql.jdbc.Driver");
		dss.setDriverUrl("jdbc:mysql://127.0.0.1:3306/mypaas_model?useUnicode=true&characterEncoding=utf-8");
		dss.setUserName("mypaas");
		dss.setPassword("4028803b3692966001369298def30001");

		if (dss != null) {
			String baseFile = prefix + "/exedo/initsql/default_model.sql";
			dt.excuteSQL(dss, baseFile);

			// dt.readSqlFile(fileName) ;
		}
	}
}
