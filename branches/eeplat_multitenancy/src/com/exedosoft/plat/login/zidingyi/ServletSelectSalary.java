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
		
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(salaryL);
//		System.out.println(salaryH);

		if(year != null && year.trim().length() <= 0)
			year = null;
		if (month != null && month.trim().length() <= 0)
			month = null;
		else if(month != null && month.trim().length() == 1)
			month = "0"+month;
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
			 List<String[]> paras = new ArrayList<String[]>();
			 String[] likeName = {"like",name};
			 String[] eqsL = {"string", salaryL};
			 String[] eqsH = {"string", salaryH};
			 String[] eqYear = {"string", year};
			 String[] eqMonth = {"string", month};
			if(name != null)
				name = name.trim();
			if (year == null && month == null && sL == null && sH == null)//0000
				set = MySqlOperation.SMfindByName(conn, name);
			else if (year == null && month == null && sL == null && sH != null) {//0001
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and  basesalary <= ? order by month";
				paras.add(likeName);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year == null && month == null && sL != null && sH == null) {//0010
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and  basesalary >= ?  order by month";
				paras.add(likeName);
				paras.add(eqsL);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year == null && month == null && sL != null && sH != null) {//0011
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and  basesalary between ? and ?  order by month";
				paras.add(likeName);
				paras.add(eqsL);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year == null && month != null && sL == null && sH == null) {//0100
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and  substring(month,6)  = ?  order by month";
				paras.add(likeName);
				paras.add(eqMonth);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year == null && month != null && sL == null && sH != null) {//0101
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,6) = ? and basesalary <= ? order by month";
				paras.add(likeName);
				paras.add(eqMonth);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year == null && month != null && sL != null && sH != null) {//0111
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,6) = ?" +
						" and basesalary between ? and ? order by month";
				paras.add(likeName);
				paras.add(eqMonth);
				paras.add(eqsL);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month == null && sL == null	&& sH == null) {//1000
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,1,4) = ? order by month";
				paras.add(likeName);
				paras.add(eqYear);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month == null && sL == null	&& sH != null) {//1001
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,1,4) = ? and basesalary <= ? order by month";
				paras.add(likeName);
				paras.add(eqYear);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month == null && sL != null	&& sH == null) {//1010
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,1,4) = ? and basesalary >= ? order by month";
				paras.add(likeName);
				paras.add(eqYear);
				paras.add(eqsL);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month == null && sL != null	&& sH == null) {//1011
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,1,4) = ? and basesalary between ? and ?  order by month";
				paras.add(likeName);
				paras.add(eqYear);
				paras.add(eqsL);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month != null && sL == null && sH == null) {//1100
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and month.substring(0,4) = ? and month.substring(6) = ? order by month";
				paras.add(likeName);
				paras.add(eqYear);
				paras.add(eqMonth);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month != null && sL == null && sH != null) {//1101
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,1,4) = ? and substring(month,6) = ? and basesalary <= ?  order by month";
				paras.add(likeName);
				paras.add(eqYear);
				paras.add(eqMonth);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month != null && sL != null && sH == null) {//1110
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,1,4) = ? and substring(month,6) = ? and basesalary >= ? order by month";
				paras.add(likeName);
				paras.add(eqYear);
				paras.add(eqMonth);
				paras.add(eqsL);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			} else if (year != null && month != null && sL != null && sH != null) {//1111
				String sql = "select * from gz_salarymessage where taxlv != '1234567890987654321' and name like ? and substring(month,1,4) = ? and substring(month,6) = ? and basesalary between ? and ? order by month";
				paras.add(likeName);
				paras.add(eqYear);
				paras.add(eqMonth);
				paras.add(eqsL);
				paras.add(eqsH);
				set = MySqlOperation.SMfindBySQL(conn, sql,paras);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println("****************************");
//		System.out.println(name);
//		
//		System.out.println(req.getSession().getAttribute("uid"));
//		System.out.println("-----------");
//		System.out.println(set==null?"set == null":"set != null");
		if(set != null) {
			List<Map<String, Object>> smlist = new ArrayList<Map<String, Object>>();
			try {
				while(set.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					String date = set.getString("month");
					String sd = date+" ÔÂ·Ý";
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
				
//				System.out.println("****************************");
				
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
