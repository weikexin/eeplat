/**
 * 
 */
package com.zephyr.erpscan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.erpscan.framework.util.Global;
import com.zephyr.erpscan.object.HtmlSelectOption;
import com.zephyr.erpscan.object.User;

/**
 * @author t
 *
 */
public class PageInfoServiceImpl extends ErpScanServiceImpl {
	
	private Logger log = Logger.getLogger(PageInfoServiceImpl.class);

	/**
	 * 
	 */
	public PageInfoServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public List getCreditList(){
		ArrayList<HtmlSelectOption> result = new ArrayList<HtmlSelectOption>();

		String sql = "select depno, depname from depart where depno like '66%' order by depno";
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();

			stm = dbcon.createStatement();

			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()){
				HtmlSelectOption hso = new HtmlSelectOption();
				hso.setId(rs.getString("depno").trim());
				hso.setDisplayName(
						rs.getString("depno")
						+"-"
						+rs.getString("depname")
						);
				
				result.add(hso);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}

		return result;
	}
	
	public List getNotCreditList(){
		ArrayList<HtmlSelectOption> result = new ArrayList<HtmlSelectOption>();

		String sql = "select depno, depname from depart where depno not like '66%' order by depno";
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();

			stm = dbcon.createStatement();

			ResultSet rs = stm.executeQuery(sql);
			
			
			while(rs.next()){
				HtmlSelectOption hso = new HtmlSelectOption();
				hso.setId(rs.getString("depno").trim());
				hso.setDisplayName(
						rs.getString("depno")
						+"-"
						+rs.getString("depname")
						);
				
				result.add(hso);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}

		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public List getDepnoList(){
		ArrayList<HtmlSelectOption> result = new ArrayList<HtmlSelectOption>();

		String sql = "select depno, depname from depart order by depno";
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();

			stm = dbcon.createStatement();

			ResultSet rs = stm.executeQuery(sql);
			
		//	result.add(new HtmlSelectOption("all", "   "));
		
			while(rs.next()){
				HtmlSelectOption hso = new HtmlSelectOption();
				hso.setId(rs.getString("depno").trim());
				hso.setDisplayName(
						rs.getString("depno")
						+"-"
						+rs.getString("depname")
						);
				
				result.add(hso);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}

		return result;
	}
	
	
	public List getMgr_depnoList(String clkno){
		ArrayList<HtmlSelectOption> result = new ArrayList<HtmlSelectOption>();

		String sql = "select mgr_depno from clerks where clkno='"+clkno+"'";
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();

			stm = dbcon.createStatement();

			ResultSet rs = stm.executeQuery(sql);
			
			String mgr_depno = null;
			
			if(rs.next()){
				mgr_depno = rs.getString(1);			
			}
			if(mgr_depno != null){
				
				String []mgr_depnos = mgr_depno.split("@");
				
				StringBuffer sql1 = null;
				
				sql1 = new StringBuffer();
				
				if(mgr_depnos.length>0){
					sql1.append("select depno , depname from depart ");
					
					int count = mgr_depno.indexOf("all@");
					System.out.println(" count is  "+count);
					
					if(count != -1){
						result.add(new HtmlSelectOption("all", "   "));
						sql1.append(" where 1=1");
						sql1.append(" order by depno");
					}else {
						sql1.append(" where depno='");
						sql1.append(mgr_depnos[0]);
						sql1.append("'");
						if(mgr_depnos.length > 1){	
							for(int i = 1 ; i < mgr_depnos.length; i ++){
								sql1.append("  or depno='");
								sql1.append(mgr_depnos[i]);
								sql1.append("'");
						}
						sql1.append(" order by depno");
					}
					
				}
			}
				System.out.println("sql1  is "+sql1);
				ResultSet rs1 = stm.executeQuery(sql1.toString());
				
				while(rs1.next()){
					HtmlSelectOption hso = new HtmlSelectOption();
					hso.setId(rs1.getString("depno").trim());
					hso.setDisplayName(
							rs1.getString("depno")
							+"-"
							+rs1.getString("depname")
							);
					
					result.add(hso);
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}

		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public List getRoleNameList(){
		ArrayList<HtmlSelectOption> result = new ArrayList<HtmlSelectOption>();
		

		String sql = "select distinct role_name from roles ";
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();

			stm = dbcon.createStatement();

			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()){
				HtmlSelectOption hso = new HtmlSelectOption();
				hso.setId(rs.getString("role_name").trim());
				hso.setDisplayName(rs.getString("role_name"));
				
				result.add(hso);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List getStatusList() {
		ArrayList<HtmlSelectOption> result = new ArrayList<HtmlSelectOption>();
		
		
		String sql = "SELECT VL.LOOKUP_CODE, VL.DESCRIPTION " +
					"FROM apps.FND_LOOKUP_VALUES VL " +
					"WHERE VL.LOOKUP_TYPE = 'EXPENSE REPORT STATUS' and VL.LANGUAGE='ZHS'";
		
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();

			stm = dbcon.createStatement();

			ResultSet rs = stm.executeQuery(sql);
			
			result.add(new HtmlSelectOption("all", ""));
			
			while(rs.next()){
				HtmlSelectOption hso = new HtmlSelectOption();
				hso.setId(rs.getString("LOOKUP_CODE").trim());
				hso.setDisplayName(rs.getString("LOOKUP_CODE")
						+ "-"
						+ rs.getString("DESCRIPTION"));
				
				result.add(hso);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		
		return result;
	}
}
