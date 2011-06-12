package com.exedosoft.plat.login.zidingyi.excel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.login.zidingyi.SalaryMessage;

public class MySqlOperation {
//	private static String driverName = "com.mysql.jdbc.Driver";
//	private static String url = "jdbc:mysql://192.168.0.3:3306/zfbx_system?useUnicode=true&characterEncoding=utf-8";
//	private static String user = "eunit";
//	private static String password = "eunit123";

	public static Connection getConnection() {
		Connection conn = null;
		try {
//			Class.forName(driverName);
//			conn = DriverManager.getConnection(url, user, password);
			 DODataSource dss = DODataSource.getDataSourceByL10n("报销数据库I");
			 conn = dss.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// ============================gz_salarymessage============================
	// ============================gz_salarymessage============================
	public static void insert(Connection conn, SalaryMessage sm, long objuid)
			throws SQLException {
		// 5+4+3+4=16个参数
		String sql = "insert into gz_salarymessage"
				+ "(objuid,month,name,basesalary,buckshee,rentdeduct," + // 6
				"leavededuct,factsalary,payyanglaoinsure,payshiyeinsure," + // 4
				"payyilaioinsure,payshebaofee,payhousingsurplus," + // 3
				"taxbefore,taxget,taxlv,taxrm,tax,taxafter,remark) " + // 7
				"values(?,?,?,?,?,?, ?,?,?,?, ?,?,?, ?,?,?,? ,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		String name = sm.getName();
		if (name != null)
			name = name.trim();
		ps.setString(1, HexToStr(objuid).trim());
		ps.setDate(2, sm.getMonth());
		ps.setString(3, name);
		ps.setDouble(4, sm.getBasesalary());
		ps.setDouble(5, sm.getBuckshee());
		ps.setDouble(6, sm.getRentdeduct());
		ps.setDouble(7, sm.getLeavededuct());
		ps.setDouble(8, sm.getFactsalary());
		ps.setDouble(9, sm.getPayyanglaoinsure());
		ps.setDouble(10, sm.getPayshiyeinsure());
		ps.setDouble(11, sm.getPayyilaioinsure());
		ps.setDouble(12, sm.getPayshebaofee());
		ps.setDouble(13, sm.getPayhousingsurplus());
		ps.setDouble(14, sm.getTaxbefore());
		ps.setDouble(15, sm.getTaxget());
		ps.setString(16, sm.getTaxlv());
		ps.setDouble(17, sm.getTaxrm());
		ps.setDouble(18, sm.getTax());
		ps.setDouble(19, sm.getTaxafter());
		ps.setString(20, sm.getRemark());
		ps.executeUpdate();
	}

	public static void update(Connection conn, SalaryMessage sm, String objuid)
			throws SQLException {
		// 5+4+3+4=16个参数
		String sql = "update gz_salarymessage set "
				+ "month=?,name=?,basesalary=?,buckshee=?,rentdeduct=?,"
				+ // 5
				"leavededuct=?,factsalary=?,payyanglaoinsure=?,payshiyeinsure=?,"
				+ // 4
				"payyilaioinsure=?,payshebaofee=?,payhousingsurplus=?,"
				+ // 3
				"taxbefore=?,taxget=?,taxlv=?,taxrm=?,tax=?,taxafter=?,remark=? "
				+ // 7
				"where objuid=?";
		PreparedStatement ps = conn.prepareStatement(sql);

		String name = sm.getName();
		if (name != null)
			name = name.trim();
		ps.setDate(1, sm.getMonth());
		ps.setString(2, name);
		ps.setDouble(3, sm.getBasesalary());
		ps.setDouble(4, sm.getBuckshee());
		ps.setDouble(5, sm.getRentdeduct());
		ps.setDouble(6, sm.getLeavededuct());
		ps.setDouble(7, sm.getFactsalary());
		ps.setDouble(8, sm.getPayyanglaoinsure());
		ps.setDouble(9, sm.getPayshiyeinsure());
		ps.setDouble(10, sm.getPayyilaioinsure());
		ps.setDouble(11, sm.getPayshebaofee());
		ps.setDouble(12, sm.getPayhousingsurplus());
		ps.setDouble(13, sm.getTaxbefore());
		ps.setDouble(14, sm.getTaxget());
		ps.setString(15, sm.getTaxlv());
		ps.setDouble(16, sm.getTaxrm());
		ps.setDouble(17, sm.getTax());
		ps.setDouble(18, sm.getTaxafter());
		ps.setString(19, sm.getRemark());
		ps.setString(20, objuid);
		ps.executeUpdate();
	}

	public static ResultSet SMfindByName(Connection conn, String name)
			throws SQLException {
		System.out.println("like========================" + name
				+ "==============================like");
		String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' order by month";
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static ResultSet SMfindByNameAndDate(Connection conn, String name,
			Date date) throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sd = format.format(date);
		String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = YEAR('" + sd
				+ "') and MONTH(month) = MONTH('" + sd + "')";
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static ResultSet SMfindBySQL(Connection conn, String sql)
			throws SQLException {
		System.out.println("sql========================\n" + sql
				+ "\n==============================sql");
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static ResultSet SMfindByDate(Connection conn, Date date)
			throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sd = format.format(date);
		String sql = "select * from gz_salarymessage where YEAR(month) = YEAR('"
				+ sd + "') and MONTH(month) = MONTH('" + sd + "')";
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static void SMDeleteByNameAndDate(Connection conn, String name,
			Date date) throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sd = format.format(date);
		String sql = "delete from gz_salarymessage where name='" + name
				+ "' and YEAR(month) = YEAR('" + sd
				+ "') and MONTH(month) = MONTH('" + sd + "')";
		Statement stm = null;
		stm = conn.createStatement();
		stm.executeUpdate(sql);
	}

	public static String SMfindNameByObjuid(Connection conn, String objuid)
			throws SQLException {
		String name = null;
		String sql = "select name from gz_salarymessage where objuid='"
				+ objuid + "'";
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		while (rs.next()) {
			String st = rs.getString("name");
			name = st;
		}
		return name;
	}

	// ============================gz_sendemail============================
	// ============================gz_sendemail============================
	public static String findTonameByName(Connection conn, String name)
			throws SQLException {
		String toname = null;
		String sql = "select toname from gz_sendemail where name='" + name
				+ "'";
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		while (rs.next()) {
			String st = rs.getString("toname");
			toname = st;
		}
		return toname;
	}

	public static String findEmailByName(Connection conn, String name)
			throws SQLException {
		String email = null;
		String sql = "select emailself from gz_sendemail where name='" + name
				+ "'";
		Statement stm = null;
		stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		while (rs.next()) {
			String st = rs.getString("emailself");
			email = st;
		}
		return email;
	}

	// ============================报销单相关词典表============================
	// ============================报销单相关词典表============================
	public static ResultSet BXusefee(Connection conn, String baoxiaouid)
			throws SQLException {
		String sql = "select * from cw_bxusefeedetail where baoxiaouid='"
				+ baoxiaouid + "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static ResultSet cityBasic(Connection conn, String citycode)
			throws SQLException {
		String sql = "select * from zd_cityfixstand where citycode='"
				+ citycode + "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static ResultSet BXfixfee(Connection conn, String baoxiaouid)
			throws SQLException {
		String sql = "select * from cw_bxfixfeedetail where baoxiaouid='"
				+ baoxiaouid + "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static ResultSet itemType(Connection conn, String itemcode)
			throws SQLException {
		String sql = "select * from zd_itemtype where itemcode='" + itemcode
				+ "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static ResultSet baoxiaoms(Connection conn, String baoxiaouid)
			throws SQLException {
		String sql = "select * from cw_baoxiaoinfor where baoxiaouid='"
				+ baoxiaouid + "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		return rs;
	}

	public static String getProject(Connection conn, String projectuid)
			throws SQLException {
		String sql = "select projectname from pm_projectinfor  where projectuid ='"
				+ projectuid + "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		String name = null;
		while (rs.next()) {
			name = rs.getString("projectname");
		}
		return name;
	}

	public static String baoxiaostate(Connection conn, Integer baoxiaostate)
			throws SQLException {
		String sql = "select baoxiaostatename from zd_baoxiaostate  where baoxiaostate ='"
				+ baoxiaostate + "'";
		Statement stm = conn.createStatement();
		stm.execute(sql);
		ResultSet rs = stm.getResultSet();
		String name = null;
		while (rs.next()) {
			name = rs.getString("baoxiaostatename");
		}
		return name;
	}

	// ++++++++++++++++++++++++++++需用到的函数++++++++++++++++++++++++++++
	// ++++++++++++++++++++++++++++需用到的函数++++++++++++++++++++++++++++
	// 主键设定， 十六进制
	private static String HexToStr(long i) {
		String s = "0123456789abcdef";
		i = i + 16 * 3732000000000L;
		StringBuffer sb = new StringBuffer();
		for (long j = 0; i >= 16; j++) {
			long a = i % 16;
			i /= 16;
			sb.append(s.charAt((int) a));
		}
		sb.append(s.charAt((int) i));
		String objuid = sb.reverse().toString();
		StringBuffer temp = new StringBuffer();
		int len = objuid.length();
		for (int k = 0; k < 32 - len; k++) {
			Random r = new Random();
			int n = r.nextInt(16);
			temp.append(s.charAt(n));
		}
		objuid = objuid + temp.toString();
		return objuid;
	}

	public static void main(String[] args) {
		Connection conn = MySqlOperation.getConnection();
		// Date date = Date.valueOf("2009-01-28");
		// try {
		// ResultSet rs = MySqlOperation.SMfindByNameAndDate(conn, "宋和平	"
		// .trim(), date);
		// while (rs.next()) {
		// System.out.println("delete");
		// MySqlOperation.SMDeleteByNameAndDate(conn, " 宋和平".trim(), date);
		// }
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
	}

}
