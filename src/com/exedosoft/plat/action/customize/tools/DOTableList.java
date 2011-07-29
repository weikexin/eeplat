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

import java.util.Iterator;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
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

		DODataSource dss = DOGlobals.getInstance().getSessoinContext().getTenancyValues().getDataDDS();
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
			String[] tblTypes = new String[] { "TABLE", "VIEW" };

			String schema = null;
			if (dss.isOracle()) {
				schema = dss.getUserName().trim().toUpperCase();
			}

			DOService aService = DOService
					.getService("multi_tenancy_table_findtablesbytenancyid");
			List<String> authTableStrs = new ArrayList<String>();

			BOInstance biTenancy = null;
			if (DOGlobals.getInstance().getSessoinContext().getTenancyValues() != null) {
				biTenancy = (BOInstance) DOGlobals.getInstance()
						.getSessoinContext().getTenancyValues().getTenant();

				if (biTenancy != null && biTenancy.getValue("name") != null) {
					List<BOInstance> hisTables = aService
							.invokeSelect(biTenancy.getValue("name"));
					for (Iterator<BOInstance> it = hisTables.iterator(); it
							.hasNext();) {
						BOInstance bi = it.next();
						authTableStrs.add(bi.getValue("table_name"));
					}
				}
			}

			ResultSet rs = meta.getTables(null, schema, null, tblTypes);

			while (rs.next()) {
				String aTable = rs.getString("TABLE_NAME");

				String tableType = rs.getString("TABLE_TYPE");
				for (Iterator<String> it = authTableStrs.iterator(); it
						.hasNext();) {
					String hisTable = it.next();

					// ////////////增强更新功能
					// //////首先要跟现有的tableName比较

					if (this.judgeExists(hisTable)) {
						continue;
					}
					if (!"dtproperties".equals(aTable)) {
						if (biTenancy != null
								&& (biTenancy.getValue("name") + "_" + hisTable)
										.equalsIgnoreCase(aTable)) {

							BOInstance bi = new BOInstance();
							InputConfigCols icc = this
									.getCols(con, aTable, dss);
							if (!aTable.startsWith("bin$")) {
								bi.putValue(this.service.getBo().getKeyCol(),
										aTable);
								bi.putValue("tablename", hisTable);
								///多租户情况下，只能采用ID作为主键
								bi.putValue("keyCol", "id,id");
								bi.putValue("valueCol", icc.getValueCols());
								bi.putValue("tableType", tableType);
								if (biTenancy != null
										&& tableType.equalsIgnoreCase("view")) {
									bi.setUid("tenancy;" + hisTable);
								} else {
									bi.setUid(aTable);
								}
								list.add(bi);
							}
						}
					}
				}
			}
			// ///end get tables

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

		try {

			DatabaseMetaData meta = con.getMetaData();

			if (dss.isOracle()) {
				aTable = aTable.toUpperCase();
			}

			ResultSet rs = meta.getColumns(null, null, aTable, null);
			while (rs.next()) {

				if ((rs.getInt("DATA_TYPE") == Types.VARCHAR || rs
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
		InputConfigCols icc = new InputConfigCols(keyCols.toString(), valueCols
				.toString());
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
