package com.exedosoft.plat.login.zidingyi.excel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.exedosoft.plat.bo.DODataSource;

public class MySqlOperationII {
//	private static String driverName = "com.mysql.jdbc.Driver";
//	private static String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8";
//	private static String user = "root";
//	private static String password = "";

	public static Connection getConnection() {
		Connection conn = null;
		try {
//			Class.forName(driverName);
//			conn = DriverManager.getConnection(url, user, password);
			DODataSource dss = DODataSource.getDataSourceByL10n("紫枫报销数据库I");
			 conn = dss.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}

	// ============================do_org_depte============================
	// ============================do_org_dept============================
	public static String getDeptByUser(Connection conn, String user_uid)
			throws SQLException {
		String sql = "select d.name from do_org_dept as d inner join "
				+ "do_org_user_link as l on d.objuid = l.dept_uid where l.user_uid ='"
				+ user_uid + "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		String dept = null;
		while (rs.next()) {
			dept = rs.getString("name");
		}
		return dept;
	}

	public static String getDeptByUid(Connection conn, String objuid)
			throws SQLException {
		String sql = "select name from do_org_dept where objuid ='" + objuid
				+ "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		String dept = null;
		while (rs.next()) {
			dept = rs.getString("name");
		}
		return dept;
	}

	// ============================do_org_user_link============================
	// ============================do_org_user_link============================
	public static void insertDeptLink(Connection conn, String objuid,
			String deptuid, String useruid, String userName)
			throws SQLException {
		String sql = "insert into do_org_user_link"
				+ "(objuid, dept_uid, user_uid, user_cn) " + // 4
				"values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		if (objuid != null)
			objuid = objuid.trim();
		if (deptuid != null)
			deptuid = deptuid.trim();
		if (useruid != null)
			useruid = useruid.trim();
		if (userName != null)
			userName = userName.trim();
		ps.setString(1, objuid);
		ps.setString(2, deptuid);
		ps.setString(3, useruid);
		ps.setString(4, userName);
		ps.executeUpdate();
	}

	public static ResultSet getUsersByDept(Connection conn, String dept)
			throws SQLException {
		String sql = "select * from do_org_user_link where dept_uid ='" + dept
				+ "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();

		return rs;
	}

	public static String getUserCNByUserUid(Connection conn, String useruid)
			throws SQLException {
		String user_cn = null;
		String sql = "select * from do_org_user_link where user_uid ='" + useruid
				+ "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		while(rs.next()) {
			user_cn = rs.getString("user_cn");
			if(user_cn != null)
				break;
		}
		return user_cn;
	}

	public static void main(String[] args) {
		Connection conn = MySqlOperationII.getConnection();
		// try {
		// MySqlOperation.findByName(conn, "徐景辉");
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}
