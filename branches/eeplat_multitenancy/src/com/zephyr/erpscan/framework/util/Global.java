/**
 * 
 */
package com.zephyr.erpscan.framework.util;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.db.DBPool;
import com.zephyr.db.DBPoolConfigure;
import com.zephyr.erpscan.framework.exception.PropertyNotFoundException;
import com.zephyr.erpscan.object.User;

/**
 * @author t
 *
 */
public class Global {
	
	private static Logger log = Logger.getLogger(Global.class);
	private static String url = "jdbc:oracle:thin:@195.0.2.10:1529:CRP2";
	private static String user = "apps";
	private static String password = "apps";
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	
	/* use db connection pool
	static{
		// 装载全局配置信息
	    try {
	        Properties props = new Properties(System.getProperties());
	        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("zephyr.properties"));
	        String tmp = props.getProperty("db.url");
	        if (tmp != null && tmp.length()>0) {
	           url  = tmp;
	        }

	        tmp = props.getProperty("db.user");
	        if (tmp != null && tmp.length()>0) {
	           user = tmp;
	        }
	        
	        tmp = props.getProperty("db.passwd");
	        if (tmp != null && tmp.length()>0) {
	           passwd = tmp;
	        }
	        
	        tmp = props.getProperty("db.driver");
	        if (tmp != null && tmp.length()>0) {
	           driver  = tmp;
	        }

	    } catch (IOException ex) {
	      log.fatal("ERP Scan Configuration problem:", ex);
	      throw new RuntimeException(
	          "ERP Scan Configuration problem: " + ex.getMessage(), ex);
	    }
	}
	*/
	
	// use connection driver
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Global() {
		super();
	}
	
	// Initialize global variables
	public static Properties getERPScanProperties() throws Exception {
		try {
			Properties erpScanProps = new Properties(System.getProperties());
			erpScanProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("zephyr.properties"));
			return erpScanProps;
		} catch (IOException ex) {
			throw new Exception(ex);
		}
	}
	
	
	public static String getPicPathPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("upload.file.store.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	
	/**
	 * 从配置文件中得到“发票单据”存储子目录
	 * @return
	 * @throws PropertyNotFoundException
	 */
	public static String getInvoicePrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("invoice.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	/**
	 * 
	  * Method Name:getInvoiceAdvancePrefix
	  * Note：应付发票子类：预付款
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Jan 12, 2010, 5:33:02 PM
	  * @Author:liBing
	 */
	public static String getInvoiceAdvancePrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("invoice.Advance.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	/**
	 * 
	  * Method Name:getInvoiceCancelPrefix
	  * Note：应付发票子类：核销预付款
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Jan 12, 2010, 5:33:22 PM
	  * @Author:liBing
	 */
	public static String getInvoiceCancelPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("invoice.Cancel.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	/**
	 * 
	  * Method Name:getInvoiceNaturalPrefix
	  * Note：应付发票子类：正常付款
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Jan 12, 2010, 5:33:46 PM
	  * @Author:liBing
	 */
	public static String getInvoiceNaturalPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("invoice.Natural.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	
	/**
	 * 从配置文件中得到“报销单据”存储子目录
	 * @return
	 * @throws PropertyNotFoundException
	 */
	public static String getExpensePrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("expense.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	
	/**
	 * 从配置文件中得到“资产单据”存储子目录
	 * @return
	 * @throws PropertyNotFoundException
	 */
	public static String getAssetPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("asset.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	
	/**
	 * 从配置文件中得到“总帐单据”存储子目录
	 * @return
	 * @throws PropertyNotFoundException
	 */
	public static String getLedgerPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("ledger.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	/**
	 * 
	  * Method Name:getContractPrefix
	  * Note：从配置文件中得到“合同单据”存储子目录
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Nov 9, 2009, 3:38:52 PM
	  * @Author:liBing
	 */
	public static String getContractPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contract.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	/**
	 * 
	  * Method Name:getContractFzPrefix
	  * Note：合同房租子项
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Dec 22, 2009, 4:36:38 PM
	  * @Author:liBing
	 */
	public static String getContractFzPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contractFZ.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	/**
	 * 
	  * Method Name:getContractZxPrefix
	  * Note：合同装修
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Dec 22, 2009, 4:38:26 PM
	  * @Author:liBing
	 */
	public static String getContractZxPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contractZX.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	/**
	 * 
	  * Method Name:getContractGxPrefix
	  * Note：合同广告宣传子项
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Dec 22, 2009, 4:39:27 PM
	  * @Author:liBing
	 */
	public static String getContractGxPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contractGX.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	/**
	 * 
	  * Method Name:getContractZxPrefix
	  * Note：合同采购子项
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Dec 22, 2009, 4:40:14 PM
	  * @Author:liBing
	 */
	public static String getContractCgPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contractCG.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	/**
	 * 
	  * Method Name:getContractKcPrefix
	  * Note：合同科技采购
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Dec 22, 2009, 4:41:15 PM
	  * @Author:liBing
	 */
	public static String getContractKcPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contractKC.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	 /**
	  * 
	   * Method Name:getContractZxPrefix
	   * Note：合同服务子项
	   * @return
	   * @throws PropertyNotFoundException
	   * Date：Dec 22, 2009, 4:42:12 PM
	   * @Author:liBing
	  */
	public static String getContractFwPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contractFW.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	/**
	 * 
	  * Method Name:getContractQtPrefix
	  * Note：合同其它子项
	  * @return
	  * @throws PropertyNotFoundException
	  * Date：Dec 22, 2009, 4:43:00 PM
	  * @Author:liBing
	 */
	public static String getContractQtPrefix() throws PropertyNotFoundException{
		String path = null;
		try{
			path = getERPScanProperties().getProperty("contractQT.code.prefix");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return path;
	}
	
	
	/**
	 * 从配置文件中得到ICE服务运行IP地址
	 * @return
	 * @throws PropertyNotFoundException
	 */
	public static String getIP() throws PropertyNotFoundException{
		String ip = null;
		try{
			ip = getERPScanProperties().getProperty("ice.server.ip");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return ip;
	}
	
	
	/**
	 * 从配置文件中得到ICE服务运行端口
	 * @return
	 * @throws PropertyNotFoundException
	 */
	public static String getPort() throws PropertyNotFoundException{
		String port = null;
		try{
			port = getERPScanProperties().getProperty("ice.server.port");
		}
		catch(Exception e){
			throw new PropertyNotFoundException(e);
		}
		return port;
	}
	
	
	/**
	 * 得到数据库连接，从连接池中
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		//conn = OraclePoolConnection.getdbpool().getConn();
		//DBConnection dbcon = new DBConnection();
		//dbcon.attach(conn); 
		
		//DBConnection dbcon = null;
		//dbcon = DBPool.getI18nDBConnection(DBPoolConfigure.getPropertyHelper().getString("alias"));
/*
		try {
			con = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("create db connection problem : "
					+ e.getMessage(), e);
		}
		conn = DBPool.getI18nConnection(DBPoolConfigure.getPropertyHelper().getString("alias1"));
		if(conn==null)DBPool.getI18nConnection(DBPoolConfigure.getPropertyHelper().getString("alias2"));
		if(conn == null) throw new SQLException("connection is null");
		*/
		 Connection conn = null;
	        conn = DBPool.getI18nConnection(DBPoolConfigure.getPropertyHelper().getString("alias1"));
	        if(conn == null)
	            DBPool.getI18nConnection(DBPoolConfigure.getPropertyHelper().getString("alias2"));
	        if(conn == null)
	            throw new SQLException("connection is null");
	        else
	            return conn;
	}
	
	/**
	 * 得到数据库连接 (connection)
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnections() throws SQLException {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("create db connection problem : "
					+ e.getMessage(), e);
		}
		
		return con;
	}
	
	
	/**
	 * 释放数据库连接
	 * @param con
	 */
	public static void releaseCon(Statement stm, Connection con) {
		log.debug("release db connection");
		try {
			if(stm != null)
				stm.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// do not care
		}/**/
	}
	

	/**
	 * 得到给定格式的时间串
	 * @param format
	 * @return
	 */
	public static String getNowTimeStr(String format){
		String result = "";
		Locale l = new Locale("zh", "CN");
		
		Date today;
	    SimpleDateFormat formatter = new SimpleDateFormat(format, l);
	    
	    today = new Date();
	    
	    result = formatter.format(today);

		
		return result;
	}
	
	public static String getUserInfo(User user){
		StringBuffer info = new StringBuffer();
		
		info.append("id:"+user.getClkno());
		info.append(" name:"+user.getName());
		info.append(" dep:"+user.getDepno());
		info.append(" role:"+user.getRole_name());
		
		return info.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			/*String tmp = Global.getERPScanProperties().getProperty("upload.file.store.prefix");
			log.debug("result from properties: "+tmp);
			
			String str = Global.getNowTimeStr("yyyyMMddhhmm");
			log.debug("time: "+str);*/
			Connection conn = null;
	        conn = DBPool.getI18nConnection(DBPoolConfigure.getPropertyHelper().getString("alias1"));
	        if(conn == null)
	            DBPool.getI18nConnection(DBPoolConfigure.getPropertyHelper().getString("alias2"));
	        if(conn == null)
	            throw new SQLException("connection is null");
	        Statement s =  conn.createStatement();
		     ResultSet r = s.executeQuery("select 1 from imageinfo");
		     while(r.next()) {
		      System.out.println(r.getString(1));
		     }
		     r.close();
		     s.close();
	        conn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 加解密图片
	 * @param data
	 * @return
	 */
	public static byte[] code(byte[] data) {
		// TODO Auto-generated method stub
		if (isCodeSwitch()) {

			byte[] tmp = new byte[data.length];
			

			for (int i = 0; i < data.length; i++) {
				tmp[i] = data[data.length-i-1];
			}

			return tmp;
		}

		return data;
	}
	
	
	/**
	 * not use
	 * @param data
	 * @return
	 */
	public static byte[] codePic(byte[] data) {
		// TODO Auto-generated method stub
		if (isCodeSwitch()) {

			byte[] tmp = new byte[data.length];
			int point = getCodePicPoint();

			for (int i = 0; i < point; i++) {
				tmp[i + data.length - point] = data[i];
			}

			for (int i = point; i < data.length; i++) {
				tmp[i - point] = data[i];
			}

			return tmp;
		}

		return data;
	}

	
	/**
	 * not use
	 * @param data
	 * @return
	 */
	public static byte[] decodePic(byte[] data) {
		// TODO Auto-generated method stub
		if (isCodeSwitch()) {
			byte[] tmp = new byte[data.length];

			int point = getCodePicPoint();

			for (int i = 0; i < point; i++) {
				tmp[i] = data[i + data.length - point];
			}

			for (int i = point; i < data.length; i++) {
				tmp[i] = data[i - point];
			}

			return tmp;
		}

		return data;
	}
	
	
	/** no use
	 * @return
	 */
	private static int getCodePicPoint() {
		int point = 10;
		
		try {
			String s = getERPScanProperties().getProperty("code.pic.move", "10");
			point = Integer.parseInt(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return point;
	}
	
	
	/**
	 * 是否执行图片加密
	 * @return
	 */
	private static boolean isCodeSwitch() {
		try {
			String s = getERPScanProperties().getProperty("code.pic.switch", "false");
			
			if(s.equalsIgnoreCase("true")){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {

			//e.printStackTrace();
			return false;
		}
	}

	public static int getPagination() {
		StringBuffer page = new StringBuffer();
		int i = 10;
		
		try {
			String s = getERPScanProperties().getProperty("perpage.number", "20");
			
			i = Integer.parseInt(s);
			
		} catch (Exception e) {
			return 20;
		}
		
		/*if(totle <= i) {}*/
			
		return i;
	}

}
