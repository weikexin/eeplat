package com.exedosoft.plat.action.zidingyi.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.exedosoft.plat.bo.DODataSource;


public class MySqlOperation {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			DODataSource dss = DODataSource.getDataSourceByL10n("民生金融租赁_档案管理");
			conn = dss.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static ResultSet archItemBySql(Connection conn, String sql)
			throws SQLException {
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static void main(String[] args) {

	}

}
