/**
 * 
 */
package com.zephyr.erpscan;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.erpscan.framework.exception.*;
import com.zephyr.erpscan.framework.util.Global;
import com.zephyr.erpscan.object.Role;
import com.zephyr.erpscan.object.User;
/**
 * @author t
 *
 */
public class ErpScanServiceImpl implements IErpScanService {
	private static Logger log = Logger.getLogger(ErpScanServiceImpl.class);
	
	ServletContext servletContext = null;
	private int curRow;
	private int totleCount;
	
	/**
	 * 
	 */
	public ErpScanServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ErpScanServiceImpl(int curRow){
		super();
		
		this.setCurRow(curRow);
	}
	
	
	/**
	 * auth user by clkno & passwd
	 */
	public User authenticate(String clkno, String password) throws
    	InvalidLoginException,DatastoreException {
		
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();
			
			
			String sql = "SELECT c.name, c.depno, c.pswdflag, c.role_name, c.state, " +
					"r.priv_name, r.range, r.rangexp " +
					"FROM clerks c, roles r " +
					"WHERE c.role_name=r.role_name and clkno='" +
					clkno+"' and pswd='" +password+"' order by priv_name";

			log.debug("auth: "+sql);
			
			//PreparedStatement pstm = dbcon.prepareStatement(sql);

			//pstm.setString(1, clkno);
			//pstm.setString(2, password);

			//java.sql.ResultSet rs = pstm.executeQuery();
			
			stm = dbcon.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sql);
			

			User user = new User();
			Vector<Role> roles = new Vector<Role>();
			
			while (rs.next()) {
				
				user.setClkno(clkno.trim());
				user.setDepno(rs.getString("depno").trim());
				user.setName(rs.getString("name").trim());
				user.setPswd(password.trim());
				user.setPswdflag(rs.getInt("pswdflag"));
				user.setRole_name(rs.getString("role_name").trim());
				user.setState(rs.getInt("state"));
				
				Role role = new Role();
				
				role.setPriv_name(rs.getString("priv_name").trim());
				role.setRange(rs.getInt("range"));
				role.setRangexp(rs.getString("rangexp"));
				
				log.info("clerk "+clkno+" has priv: "+role.getPriv_name());
				
				roles.add(role);
			}

			
			if(user.getName()==null){
			// 抛出登录无效异常
			// rs is empty
				log.debug("User login fail");
				
				throw new InvalidLoginException();

			}
			
			user.setRoles(roles);

			rs.close();
			// 返回客户对象
			return user;

		} catch (SQLException ex) {
			ex.printStackTrace();

			// 抛出数据存取异常
			// throw DatastoreException.datastoreError(ex);
			throw new DatastoreException(ex);
			
		} catch(NullPointerException ne){
			throw new DatastoreException(ne);
		}
		finally {
			Global.releaseCon(stm, dbcon);
		}
	}
	
	
	/**
	 * 
	 * @param clkno
	 */
	public void logout(String clkno){
		// do logout thing
		// now do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zephyr.erpscan.IErpScanService#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext ctx) {

		this.servletContext = ctx;
	}

	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanService#getServletContext()
	 */
	public ServletContext getServletContext() {

		return servletContext;
	}

	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanService#destory()
	 */
	public void destory() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanService#getRowCount()
	 */
	public int getCurRow() {

		return curRow;
	}

	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanService#setRowCount(int)
	 */
	public void setCurRow(int curRow) {
		
		this.curRow = curRow;
	}
	
	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanService#getTotleCount()
	 */
	public int getTotleCount() {

		return totleCount;
	}

	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanService#setTotleCount(int)
	 */
	public void setTotleCount(int totleCount) {
		
		this.totleCount = totleCount;
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		ErpScanServiceFactory f = new ErpScanServiceFactory();
		
		IErpScanService s = f.createService();
	}


}
