package com.exedosoft.plat.util.sequence;

import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;

import java.util.Hashtable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import com.exedosoft.plat.util.id.UUIDHex;

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
public class DOMAXIDTrash {

	private static DOMAXIDTrash builder;

	private static Object lockObj = new Object();// lock object

	private static Hashtable casheKey = new Hashtable();// cashe

	private static Hashtable casheOverFlowId = new Hashtable();

	private int overFlowId = 0; // when cashe's id more than this id ,have to

	// retrieve from db

	private int poolSize = 10;// getId numbers without accessing datebase

	private int maxSequence = 1;

	private DOMAXIDTrash() {
	}

	/**
	 * Gets only one TableIdBuidler object
	 * 
	 * @return
	 */
	public static DOMAXIDTrash getInstance() {

		if (builder == null) {
			synchronized (lockObj) {
				if (builder == null) {
					builder = new DOMAXIDTrash();
				}
			}
		}
		return builder;
	}

	// //////////codeitemid 探矿权 4028803613728794011372a5f4670005
	// //////////codeitemid 采矿权 2028803613728794011372a5f4670005

	public void trashID(String codeItemID, String aDeptCode, int aTrashID) {

		int aYear = Calendar.getInstance().get(Calendar.YEAR);

		StringBuffer insertSql = new StringBuffer(
				"insert into DO_Code_Sequence_Trash values(")
				.append("?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement stmt = null;
		DOBO bo = DOBO.getDOBOByName("DO_CODE_MAXSEQUENCE");
		DODataSource dss =  bo.getDataBase();

		try {

			con = dss.getContextConnection();

			// //////////////insert
			Date curDate = new Date(System.currentTimeMillis());
			stmt = con.prepareStatement(insertSql.toString());
			stmt.setString(1, UUIDHex.getInstance().generate());
			stmt.setString(2, codeItemID);
			stmt.setString(3, aDeptCode);
			stmt.setInt(4, aYear);
			stmt.setInt(5, aTrashID);
			stmt.setDate(6, curDate);
			stmt.execute();

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();

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

	/**
	 * 
	 * Gets the number value of max of objid +1
	 */
	public int getTrashID(String codeItemID, String aDeptCode) {

		int aYear = Calendar.getInstance().get(Calendar.YEAR);

		// propertyValue:::::::::::deptCode

		codeItemID = codeItemID.toUpperCase();
		String selectSql = "SELECT TRASH_CODE FROM DO_Code_Sequence_Trash WHERE CODE_ITEMUID=? and DEPT_CODE=? and YEAR_SEQ=? ";

		StringBuffer deleteSql = new StringBuffer(
				"delete from DO_Code_Sequence_Trash ")
				.append("where CODE_ITEMUID=? and DEPT_CODE=? and YEAR_SEQ=? ");

		Connection con = null;
		PreparedStatement stmt = null;
		int retId = -1;// //////////返回的值
		DOBO bo = DOBO.getDOBOByName("DO_CODE_MAXSEQUENCE");
		DODataSource dss =  bo.getDataBase();
		try {

			con = dss.getContextConnection();
			// query
			stmt = con.prepareStatement(selectSql);
			stmt.setString(1, codeItemID);
			stmt.setString(2, aDeptCode);
			stmt.setInt(3, aYear);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				retId = rs.getInt("TRASH_CODE");
				// update
				stmt = con.prepareStatement(deleteSql.toString());
				stmt.setString(1, codeItemID);
				stmt.setString(2, aDeptCode);
				stmt.setInt(3, aYear);
				stmt.execute();
			}

			return retId;

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
			return 0;
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

}
