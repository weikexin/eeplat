/**
 * 
 */
package com.zephyr.erpscan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.erpscan.framework.exception.DatastoreException;
import com.zephyr.erpscan.framework.util.Global;
import com.zephyr.erpscan.object.User;
import com.zephyr.erpscan.struts.manageUser.AddUserForm;

/**
 * @author t
 *
 */
public class UserManageServiceImpl extends ErpScanServiceImpl {
	
	private Logger log = Logger.getLogger(UserManageServiceImpl.class);

	/**
	 * 
	 */
	public UserManageServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Vector getUserByClkno(String clkno) throws DatastoreException{
		String where = " where clkno='"+clkno+"'";
		
		return this.getUser(where);
	}
	
	public Vector getUserByDepno(String depno) throws DatastoreException{
		
		String where = " where depno='"+depno+"'";
		
		return this.getUser(where);
	}
	
	public Vector getAllUser() throws DatastoreException{
		
		return this.getUser(""); 
	}
	
	
	/**
	 * 
	 * @param where
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getUser(String where) throws DatastoreException{
		Vector results = new Vector();
		
		String sql = "SELECT c.clkno,c.pswd, c.name, c.depno, c.pswdflag, c.role_name, c.state , c.mgr_depno FROM clerks c " 
				+ where +" order by clkno";
		log.info("get user sql: "+sql);
		
		Connection dbcon = null;
		Statement stm = null;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()){
				User user = new User();
				
				user.setClkno(rs.getString("clkno").trim());
				user.setPswd(rs.getString("pswd").trim());
				user.setDepno(rs.getString("depno").trim());
				user.setPswdflag(rs.getInt("pswdflag"));
				user.setName(rs.getString("name").trim());
				user.setRole_name(rs.getString("role_name").trim());
				user.setState(rs.getInt("state"));
				
				if(rs.getString("mgr_depno")==null){
					user.setMgr_depno("");
				}else{
					user.setMgr_depno(rs.getString("mgr_depno").trim());
				}
				
				results.add(user);
			}
			
		}
		catch (SQLException ex) {

			throw DatastoreException.datastoreError(ex);
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		
		return results;
	}

	/**
	 * 
	 * @param clkno
	 * @return
	 * @throws DatastoreException
	 */
	public boolean deleteUser(String clkno) throws DatastoreException {
		
		// TODO Auto-generated method stub
		
		
		String sql = "delete from clerks where clkno='"+clkno+"'";
		
		Connection dbcon = null;
		Statement stm = null;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			stm.executeUpdate(sql);
		}
		catch(SQLException e){
			
			throw DatastoreException.datastoreError(e);
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		return true;
	}
	
	
	public boolean addUser(AddUserForm form) throws DatastoreException{
		System.out.print("manager depno is  "+form.getMgr_depno());
		
		String sql = "insert into clerks values(" 
				+ "'" + form.getClkno() +"', "
				+ "'" + form.getPassword() +"', "
				+ "0, "
				+ "'" + form.getName() +"', "
				+ "0, "
				+ "'" + form.getDepno() +"', "
				+ "'" + form.getRole_name() +"', "
				+ "null"+", "
				+"'"+form.getMgr_depno()+"'"
				+ ")";
		
		log.info("add new user sql: "+sql);
		
		Connection dbcon = null;
		Statement stm = null;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			stm.executeUpdate(sql);
		}
		catch(SQLException e){
			
			throw DatastoreException.datastoreError(e);
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		return true;

	}
	
	
	public boolean updateUser(AddUserForm form) throws DatastoreException{
		System.out.print("manager depno is  "+form.getMgr_depno());
		
		String sql = "update clerks set name=" 
				+ "'" + form.getName() +"', "
				+ "depno="
				+ "'" + form.getDepno() +"', "
				+ "role_name="
				+ "'" + form.getRole_name() +"', "
				+ "mgr_depno="
				+"'"+form.getMgr_depno()+"'"
				+ " where clkno="
				+ "'" + form.getClkno() +"' ";
		
		log.info("add new user sql: "+sql);
		
		Connection dbcon = null;
		Statement stm = null;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			stm.executeUpdate(sql);
		}
		catch(SQLException e){
			
			throw DatastoreException.datastoreError(e);
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		return true;

	}

	public boolean modifyPswd(String clkno, String oldPswd, String newPswd) throws DatastoreException {
		// TODO Auto-generated method stub
		String sql = "update clerks set pswd='" + newPswd +"'" +
				" where pswd='" + oldPswd +"' and clkno='" + clkno +"'";
		
		log.info("modify password sql: "+sql);
		
		Connection dbcon = null;
		Statement stm = null;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			int ok = stm.executeUpdate(sql);
		}
		catch(SQLException e){
			
			throw DatastoreException.datastoreError(e);
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		
		return true;
	}

	public boolean renewPSWD(String clkno) {
		
		String newPswd = "";
		try {
			newPswd = Global.getERPScanProperties().getProperty("renew.user.password");
		} catch (Exception e1) {
			
			e1.printStackTrace();
			
			return false;
		}
		
		String sql = "update clerks set pswd='" + newPswd 
					+ "' where clkno='" + clkno + "'";

		log.info("Administrator renew "+clkno+" password sql: " + sql);

		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();

			int ok = stm.executeUpdate(sql);
		} catch (SQLException e) {

			//throw DatastoreException.datastoreError(e);
			
			return false;
		} finally {
			Global.releaseCon(stm, dbcon);
		}

		return true;
		
	}

}
