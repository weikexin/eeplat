package com.zephyr.erpscan;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.db.DBPool;
import com.zephyr.db.DBPoolConfigure;
import com.zephyr.erpscan.framework.exception.DatastoreException;
import com.zephyr.erpscan.framework.util.Global;
import com.zephyr.erpscan.object.PicObject;


/**
 * @author kay
 *
 */
public class ErpScanProcImpl extends ErpScanServiceImpl{
	private Logger log = Logger.getLogger(ErpScanProcImpl.class);

	
	/**
	 * 
	 */
	public ErpScanProcImpl(){
		super();
	}
	

	//get the update records
	public ArrayList<PicObject> callProc(String strid,String operator,int num){
		CallableStatement cs = null;
		Connection con = null;
		String ids[] = null;
		String sid = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		Statement stm = null;
		ArrayList<PicObject> al = null;
		PicObject pic = null;
		
		System.out.println("string id is "+strid);
		try {
			con = Global.getConnections();

			String plsStmt = "begin relay.relaystatus(:1, :2, :3, :4, :5, :6, :7);end;";
			cs = con.prepareCall(plsStmt);
			
			cs.registerOutParameter(1,oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.setString(3, strid);
			cs.setString(4, operator);
			cs.setInt(5,num);
			cs.registerOutParameter(6,oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(7,oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			
			sid = cs.getString(1);
			int flag = cs.getInt(2);
			String error = cs.getString(6);
			String info = cs.getString(7);
			
			System.out.println("sid  is  "+sid);
			System.out.println("flag  is  "+flag);
			System.out.println("oracle make error  is  "+error);
			System.out.println("info  is  "+info);
			
			ids=sid.split("@");

			sql = new StringBuffer();
			if(flag>0){
				sql.append("select serialno, scandate, clerk, depno, status, form_number from xxxcmbc_erpscan.imageinfo ");
				sql.append(" where id=");
				sql.append(Integer.parseInt(ids[0]));
				if(flag>1){
					for(int i = 1;i < flag; i ++){
						sql.append("  or id=");
						sql.append(Integer.parseInt(ids[i]));
					}
				}
				sql.append(" order by serialno");
			}
			
			System.out.println("sql is "+sql.toString());
			stm = con.createStatement();
			rs = stm.executeQuery(sql.toString());
			al = new ArrayList<PicObject>();
			
			while(rs.next()){
				pic = new PicObject();
				pic.setScanDate(rs.getString("scandate"));
				pic.setClerk(rs.getString("clerk"));
				pic.setSerialno(rs.getString("serialno"));
				pic.setDepno((rs.getString("depno")));
				pic.setStatus((rs.getInt("status")));
				pic.setForm_number((rs.getString("form_number")));
				
				al.add(pic);
			}
			
		}catch (Exception e) {
			System.out.println("exception is "+e);
		}finally{
			Global.releaseCon(cs, con);
		}
		return al;
	}
	
	
	public static void main (String arg[]){
		ArrayList al = new ErpScanProcImpl().callProc("167671&", "1", 1);
		
		System.out.print("al is "+al);
	}
	
}
