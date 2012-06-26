package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.gene.jquery.SqlCol;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * 
 * 做增加，不做修改和删除
 * 
 * 可以对增加做扫描
 * 
 * @author anolesoft
 * 
 */

public class DOTableList extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String excute() {

		DODataSource dss = null;

		if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {
			dss = DOGlobals.getInstance().getSessoinContext()
					.getTenancyValues().getDataDDS();
		} else {// 单租户情况

			DOBO bo = DOBO.getDOBOByName("do_datasource");
			dss = DODataSource.getDataSourceByL10n(bo.getCorrInstance()
					.getValue("l10n"));
		}

		// if(dss.getDriverClass().equals("org.hsqldb.jdbcDriver") &&
		// dss.getDriverUrl().indexOf("jdbc:hsqldb") == -1){
		// String path =
		// DODataSource.class.getResource("/globals.xml").getPath();
		// path = path.substring(0, path.toLowerCase().indexOf("classes"));
		// StringBuilder driverUrl =
		// new
		// StringBuilder("jdbc:hsqldb:file:").append(path).append("db/").append(dss.getDriverUrl());
		//
		// dss.setDriverUrl(driverUrl.toString());
		// }

		List list = new ArrayList();
		Connection con = null;
		try {
			con = dss.getConnection();
			DatabaseMetaData meta = con.getMetaData();
			String[] tblTypes = new String[] { "TABLE" };

			String schema = null;
			if (dss.isOracle()) {
				schema = dss.getUserName().trim().toUpperCase();
			}
			ResultSet rs = meta.getTables(null, schema, null, tblTypes);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				// ////////////增强更新功能
				// //////首先要跟现有的tableName比较

				// ////////////////

				if (this.judgeExists(tableName)) {
					continue;
				}
				if (!"dtproperties".equals(tableName)) {
					BOInstance bi = new BOInstance();
					InputConfigCols icc = this.getCols(con, tableName, dss);
					String aTable = rs.getString("TABLE_NAME").toLowerCase();
					if (!aTable.startsWith("bin$")) {
						bi.putValue(this.service.getBo().getKeyCol(), aTable);
						bi.putValue("tablename", aTable);

						if ("false".equals(DOGlobals.getValue("model.uuid"))) {
							bi.putValue("keyCol", icc.getValueCols());
						} else {
							bi.putValue("keyCol", icc.getKeyCols());
						}
						bi.putValue("valueCol", icc.getValueCols());
						bi.setUid(aTable);
						list.add(bi);
					}
				}
			}
		} catch (SQLException ex) {
			this.setEchoValue(ex.getMessage());
			return NO_FORWARD;

		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				this.setEchoValue(ex1.getMessage());
			}

		}
		// System.out.println("Tablelist:::" + list);
		this.setInstances(list);

		// TODO Auto-generated method stub
		return DEFAULT_FORWARD;
	}

	public boolean judgeExists(String aTable) {

		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();

			String judgeSql = "select * from do_bo where lower(name) = lower(?) ";

			PreparedStatement pstmt = con.prepareStatement(judgeSql);
			pstmt.setString(1, aTable);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return false;
	}

	public InputConfigCols getCols(Connection con, String aTable,
			DODataSource dss) {

		StringBuffer keyCols = new StringBuffer();
		StringBuffer valueCols = new StringBuffer();
		String schema = null;
		try {

			DatabaseMetaData meta = con.getMetaData();

			if (dss.isOracle()) {
				aTable = aTable.toUpperCase();
				schema = dss.getUserName().trim().toUpperCase();
			}

			ResultSet rs = meta.getColumns(null, schema, aTable, null);
			while (rs.next()) {
				
				if("false".equals(DOGlobals.getValue("gene_key_uuid"))){
					keyCols.append(rs.getString("COLUMN_NAME")).append(",")
					.append(rs.getString("COLUMN_NAME")).append(";");
					
				}else	if ((rs.getInt("DATA_TYPE") == Types.VARCHAR || rs
						.getInt("DATA_TYPE") == Types.CHAR)
						&& rs.getInt("COLUMN_SIZE") >= 32) {
					keyCols.append(rs.getString("COLUMN_NAME")).append(",")
							.append(rs.getString("COLUMN_NAME")).append(";");
				}
				valueCols.append(rs.getString("COLUMN_NAME")).append(",")
						.append(rs.getString("COLUMN_NAME")).append(";");

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		InputConfigCols icc = new InputConfigCols(keyCols.toString(),
				valueCols.toString());
		return icc;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String path = DODataSource.class.getResource("/globals.xml").getPath();
		path = path.substring(0, path.toLowerCase().indexOf("classes"));
		StringBuilder driverUrl = new StringBuilder("jdbc:hsqldb:file:")
				.append(path).append("db/").append("test/test");

		System.out.println("DriverUrl:::" + driverUrl);

	}

}

class InputConfigCols {

	private String keyCols;

	private String valueCols;

	InputConfigCols(String keyCols, String valueCols) {

		this.keyCols = keyCols;
		this.valueCols = valueCols;
	}

	public String getKeyCols() {
		return keyCols;
	}

	public void setKeyCols(String keyCols) {
		this.keyCols = keyCols;
	}

	public String getValueCols() {
		return valueCols;
	}

	public void setValueCols(String valueCols) {
		this.valueCols = valueCols;
	}

}
