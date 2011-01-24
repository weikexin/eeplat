package com.exedosoft.plat.util.sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

/**
 * <p>Title: </p> <p>Description: </p> <p>Copyright: Copyright (c) 2003</p> <p>Company: </p>
 * @author  not attributable
 * @version  1.0
 */
public class UserDefineIDSequence implements SequenceBuilder {

	private static UserDefineIDSequence builder;
	private static Object lockObj = new Object();// lock object

	private UserDefineIDSequence() {
	}

	/**
	 * Gets only one TableIdBuidler object
	 * 
	 * @return
	 */
	public static UserDefineIDSequence getInstance() {

		if (builder == null) {
			synchronized (lockObj) {
				if (builder == null) {
					builder = new UserDefineIDSequence();
				}
			}
		}
		return builder;
	}

	/**
	 * 
	 * Gets the number value of max of objid +1
	 */
	private int getIDFromDb(String codeItemID) {

		// StringBuffer buffer = new StringBuffer();
		codeItemID = codeItemID.toUpperCase();
		String sql = "SELECT max_sequence FROM DO_Code_MaxSequence WHERE propertyvalue is null and upper(code_ItemUid)=?";
		StringBuffer sqlUpdate = new StringBuffer(
				"update DO_Code_MaxSequence SET max_sequence=max_sequence+1")
				.append(
						"  WHERE propertyvalue is null  and upper(code_ItemUid)=?");
		return getIDHelper(codeItemID, sql, sqlUpdate);
	}
	
	/**
	 * 
	 * Gets the number value of max of objid +1
	 */
	private int getIDFromDb4Bu(String codeItemID) {

		// StringBuffer buffer = new StringBuffer();
		codeItemID = codeItemID.toUpperCase();
		String sql = "SELECT max_sequence FROM DO_Code_MaxSequence WHERE propertyvalue = '100000' and upper(code_ItemUid)=?";
		StringBuffer sqlUpdate = new StringBuffer(
				"update DO_Code_MaxSequence SET max_sequence=max_sequence+1")
				.append(
						"  WHERE propertyvalue = '100000'  and upper(code_ItemUid)=?");
		return getIDHelper(codeItemID, sql, sqlUpdate);
	}

	private int getIDHelper(String codeItemID, String sql, StringBuffer sqlUpdate) {
		Connection con = null;
		PreparedStatement stmt = null;
		DOBO bo = DOBO.getDOBOByName("DO_CODE_MAXSEQUENCE");
		DODataSource dss =  bo.getDataBase();
		try {
			// query


			con = dss.getContextConnection();

			stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int retId = 1;

			stmt.setString(1, codeItemID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				retId = rs.getInt("max_sequence") + 1;
			}
			// stmt.close();
			//
			// //update
			stmt = con.prepareStatement(sqlUpdate.toString());
			stmt.setString(1, codeItemID);
			stmt.execute();
			return retId;

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
			return -1;

		} finally {// Close Connection
			try {
				if (stmt != null) {
					stmt.close();
				}
				dss.ifCloseConnection(con);
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
	}




	public synchronized Long getLong(String codeItemID, String aDept) {
		if("100000".equalsIgnoreCase(aDept) || "010000".equalsIgnoreCase(aDept)
				){
			int  iBUValue = getIDFromDb4Bu(codeItemID);
			if(iBUValue <= 500){
				return new Long(iBUValue);
			}
		}
		return new Long(getIDFromDb(codeItemID));
	}


	public synchronized String getString(String codeItemID, String aDept) {
		
		
		
		
		/**
		 * 探矿权号码回收
		 */
		DOService aService = DOService.getService("reuse_feihao_tankuangquan");
		List  list = aService.invokeSelect();
		if(list!=null && list.size()>0){
			BOInstance aInstance = (BOInstance)list.get(0);
			String licenceid = aInstance.getValue("withdraw_reason");
			if(licenceid!=null && licenceid.length()==18){
				return licenceid.substring(12, 18);
				
			}
		}
		
		
		if("100000".equalsIgnoreCase(aDept) ||  "010000".equalsIgnoreCase(aDept)){
			int  iBUValue = getIDFromDb4Bu(codeItemID);
			if(iBUValue <= 500){
				return String.valueOf(iBUValue);
			}
		}
		return String.valueOf(getIDFromDb(codeItemID));
	}



	public static void main(String[] args) {



	}

}
