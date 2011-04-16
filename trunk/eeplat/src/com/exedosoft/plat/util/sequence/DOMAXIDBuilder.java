package com.exedosoft.plat.util.sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
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
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class DOMAXIDBuilder implements SequenceBuilder {

	private static DOMAXIDBuilder builder;

	private static Object lockObj = new Object();// lock object

	private int maxSequence = 0;

	private DOMAXIDBuilder() {
	}

	/**
	 * Gets only one TableIdBuidler object
	 * 
	 * @return
	 */
	public static DOMAXIDBuilder getInstance() {

		if (builder == null) {
			synchronized (lockObj) {
				if (builder == null) {
					builder = new DOMAXIDBuilder();
				}
			}
		}
		return builder;
	}

	/**
	 * 
	 * DOMAMIDBuidler.getID
	 * 
	 * 
	 * insert into tbstudent(objuid,name,lsh)
	 * 
	 * Gets the number value of max of objid +1
	 */
	private int getIDFromDb(String codeItemID) {

		// StringBuffer buffer = new StringBuffer();
		codeItemID = codeItemID.toUpperCase();
		String sql = "SELECT max_sequence FROM DO_Code_MaxSequence WHERE upper(code_ItemUid)=?";

		StringBuffer insertSql = new StringBuffer(
				"insert into DO_Code_MaxSequence(OBJUID,SEQUENCE_NAME,CODE_ITEMUID,PROPERTYUID,PROPERTYVALUE,YEARSEQ,MAX_SEQUENCE) values(")
				.append("?,?,?,null,?,?,?)");

		StringBuffer sqlUpdate = new StringBuffer(
				"update DO_Code_MaxSequence SET max_sequence=max_sequence+1")
				.append(" WHERE upper(code_ItemUid)=?");
		Connection con = null;
		PreparedStatement stmt = null;
		DOBO bo = DOBO.getDOBOByName("do_authorization");
		DODataSource dss = bo.getDataBase();

		try {
			// query
			con = dss.getContextConnection();
			stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int retId = 1;

			stmt.setString(1, codeItemID);
			ResultSet rs = stmt.executeQuery();

			System.out.println("The SQL" + sql);

			if (rs.next()) {
				retId = rs.getInt("max_sequence") + 1;
				// update
				// //update
				stmt = con.prepareStatement(sqlUpdate.toString());
				stmt.setString(1, codeItemID);
				stmt.execute();
			} else {
				// //////////////insert
				stmt = con.prepareStatement(insertSql.toString());
				stmt.setString(1, UUIDHex.getInstance().generate());
				stmt.setString(2, null);
				stmt.setString(3, codeItemID);
				stmt.setString(4, null);
				stmt.setInt(5, 0);
				stmt.setLong(6, retId);
				stmt.execute();
			}
			// stmt.close();
			//

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

	/**
	 * Gets table key by a table name
	 * 
	 * @return key in cashe
	 */
	public synchronized int getID(String codeItemID) {

		maxSequence = this.getIDFromDb(codeItemID);
		return maxSequence;
	}

	public synchronized Long getLong(String codeItemID, String aDept) {
		return new Long(getID(codeItemID));
	}

	public synchronized Integer getInteger(String codeItemID) {
		return new Integer(getID(codeItemID));
	}

	public synchronized String getString(String codeItemID, String aDept) {
		return String.valueOf(getID(codeItemID));
	}

	public synchronized String getString(String codeItemID, String aYear,
			String aDept) {
		return String.valueOf(getID(codeItemID));
	}

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			int aID = DOMAXIDBuilder.getInstance().getID(
					"2028803613728794011372a5f4670005");

			System.out.println(aID);
		}

	}

}
