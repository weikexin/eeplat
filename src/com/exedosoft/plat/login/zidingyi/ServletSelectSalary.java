package com.exedosoft.plat.login.zidingyi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;

public class ServletSelectSalary extends HttpServlet {

	/**
	 * @param args
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		
		String objuid = req.getParameter("uid");
		if(objuid == null || objuid.trim().length() <= 0) {
			objuid = (String)req.getSession().getAttribute("uid");
		}else {
			req.getSession().setAttribute("uid", objuid);
		}

		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String salaryL = req.getParameter("lower");
		String salaryH = req.getParameter("high");
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(salaryL);
		System.out.println(salaryH);

		if(year != null && year.trim().length() <= 0)
			year = null;
		if (month != null && month.trim().length() <= 0)
			month = null;

		Double sL = null;
		Double sH = null;

		if (salaryL != null && salaryL.matches("^\\d+|\\d*\\.\\d+$"))
			sL = Double.parseDouble(salaryL);
		else
			sL = null;
		if (salaryH != null && salaryH.matches("^\\d+|\\d*\\.\\d+$"))
			sH = Double.parseDouble(salaryH);
		else
			sH = null;

		Connection conn = MySqlOperation.getConnection();
		ResultSet set = null;
		String name = null;
		try {
			name = MySqlOperation.SMfindNameByObjuid(conn, objuid);
			if(name != null)
				name = name.trim();
			if (year == null && month == null && sL == null && sH == null)//0000
				set = MySqlOperation.SMfindByName(conn, name);
			else if (year == null && month == null && sL == null && sH != null) {//0001
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and  basesalary <= " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year == null && month == null && sL != null && sH == null) {//0010
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and  basesalary >= " + sL + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year == null && month == null && sL != null && sH != null) {//0011
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and  basesalary between " + sL + " and " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year == null && month != null && sL == null && sH == null) {//0100
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and  Month(month) = '" + month + "'" + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year == null && month != null && sL == null && sH != null) {//0101
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and Month(month) = '" + month + "' and basesalary <= " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year == null && month != null && sL != null && sH != null) {//0111
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and Month(month) = '" + month + "' and basesalary between " + sL + " and " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month == null && sL == null	&& sH == null) {//1000
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "'" + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month == null && sL == null	&& sH != null) {//1001
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "' and basesalary <= " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month == null && sL != null	&& sH == null) {//1010
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "' and basesalary >= " + sL + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month == null && sL != null	&& sH == null) {//1011
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "' and basesalary between " + sL + " and " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month != null && sL == null && sH == null) {//1100
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "' " + "and Month(month) = '" + month + "'" + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month != null && sL == null && sH != null) {//1101
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "' " + "and Month(month) = '" + month 
				+ "' and basesalary <= " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month != null && sL != null && sH == null) {//1110
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "' " + "and Month(month) = '" + month 
				+ "' and basesalary >= " + sL + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			} else if (year != null && month != null && sL != null && sH != null) {//1111
				String sql = "select * from gz_salarymessage where name like '%" + name
				+ "%' and YEAR(month) = '" + year + "' " + "and Month(month) = '" + month 
				+ "' and basesalary between " + sL + " and " + sH + "  order by month";
				set = MySqlOperation.SMfindBySQL(conn, sql);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("****************************");
		System.out.println(name);
		
		System.out.println(req.getSession().getAttribute("uid"));
		System.out.println("-----------");
		System.out.println(set==null?"set == null":"set != null");
		if(set != null) {
			List<Map<String, Object>> smlist = new ArrayList<Map<String, Object>>();
			try {
				while(set.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					java.util.Date date = set.getDate("month");
					SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
					String sd = format.format(date);
					map.put("month", sd);
					map.put("name", set.getString("name"));
					map.put("basesalary", set.getDouble("basesalary"));
					map.put("buckshee", set.getDouble("buckshee"));
					
					map.put("rentdeduct", set.getDouble("rentdeduct"));
					map.put("leavededuct", set.getDouble("leavededuct"));
					map.put("factsalary", set.getDouble("factsalary"));
					map.put("payyanglaoinsure", set.getDouble("payyanglaoinsure"));
					
					map.put("payshiyeinsure", set.getDouble("payshiyeinsure"));
					map.put("payyilaioinsure", set.getDouble("payyilaioinsure"));
					map.put("payshebaofee", set.getDouble("payshebaofee"));
					map.put("payhousingsurplus", set.getDouble("payhousingsurplus"));
					
					map.put("taxbefore", set.getDouble("taxbefore"));
					map.put("taxget", set.getDouble("taxget"));
					map.put("taxlv", set.getString("taxlv"));
					map.put("taxrm", set.getDouble("taxrm"));				
					
					map.put("tax", set.getDouble("tax"));
					map.put("taxafter", set.getDouble("taxafter"));
					map.put("remark", set.getString("remark"));
					
					smlist.add(map);
				}
				
				System.out.println("****************************");
				
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				req.setAttribute("year", year);
				req.setAttribute("month", month);
				req.setAttribute("sl", salaryL);
				req.setAttribute("sh", salaryH);
				req.setAttribute("name", name);
				
				req.getSession().setAttribute("smlist", smlist);
				String url = "zfbx_manager/gongzims.jsp";
				req.getRequestDispatcher(url).forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
