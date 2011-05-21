package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;
import com.exedosoft.plat.login.zidingyi.excel.OperationUtil;
import com.exedosoft.plat.util.DOGlobals;

public class WLogIndexMessage {
	/**
	 * 用于首页显示信息
	 * */

	/**
	 * 获取本次补助信息 messages[0]:出差天数 messages[1]:补助金额统计; messages[2]:出差地点;
	 * messages[3]:休假天数; messages[4]:带薪休假天数
	 * */
	public static String[] getBZMessage() {
		String[] messages = new String[5];
		String loginEmp = DOGlobals.getInstance().getSessoinContext()
				.getInstance().getUser().getUid();

		// 查询是否有出差记录。
		String sql = "select cw_type, wdate, wseladdress from cw_worklog where wdate = (select max(l.wdate) from cw_worklog l where l.emp_uid = '"
				+ loginEmp + "' and l.cw_type like '%cc%')";
		System.out.println("sql::::::::::"+sql);
		Connection conn = MySqlOperation.getConnection();
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// 是否有出差记录
			boolean isHaveCC = false;
			// 最晚的出差记录是否为'gscc'
			boolean isGSCC = false;
			// 最晚的出差记录日期
			Date lastedWdate = null;
			// 当前状态是否为出差
			boolean isChuchai = false;
			ResultSet rs = MySqlOperation.wLogBySql(conn, sql);
			String wseladdress = "";
			while (rs.next()) {
				isHaveCC = true;
				lastedWdate = rs.getDate("wdate");
				wseladdress = rs.getString("wseladdress");
				String cwtype = rs.getString("cw_type");
				if (cwtype != null && "gscc".equals(cwtype)) {
					isGSCC = true;
				}
			}
			rs.close();

			if (!isHaveCC) {
				// 没有出差记录，则当前状态为不在出差状态;
				isChuchai = false;
			} else {
				// 有出差记录，则判断当前状态始发为出差状态;
				// 判断是否有在公司的记录的日期晚于最晚的出差记录日期，若有，则当前状态为不在出差。
				// 判断是否有在公司的记录的日期晚于最晚的出差记录日期的标志
				boolean isHaveGs = false;
				sql = "select w.* from cw_worklog w where w.cw_type = 'gs' and w.emp_uid = '"
						+ loginEmp + "' and w.wdate > '" + lastedWdate + "' ";
				rs = MySqlOperation.wLogBySql(conn, sql);

				while (rs.next()) {
					isHaveGs = true;
					break;
				}
				rs.close();
				if (isHaveGs) {
					isChuchai = false;
				} else {
					isChuchai = true;
				}
			}

			// 若当前没有出差，则直接返回messages[0] = "0";messages[1] = "0";
			if (!isChuchai) {
				messages[0] = "0";
				messages[1] = "0";
				messages[2] = "";
				messages[3] = "0";
				messages[4] = "0";
			} else {
				// 若当前为出差，则求取时间最大的而且cw_type不为'cc'的记录，则大于该时间的最小日期即为出差的开始时间;
				// 服务需要判断是否有在公司(cw_type='gs')的记录,若没有则取时间最小的为mindate
				if (isGSCC) {
					String gsccDate = format.format(lastedWdate);
					sql = "select * from cw_worklog w where  emp_uid = '"
							+ loginEmp + "' and wdate >= '" + gsccDate
							+ "' order by w.wdate";
				} else {
					sql = "select w.* from cw_worklog w where  w.emp_uid = '"
							+ loginEmp
							+ "' and (if((select max(l.wdate) from cw_worklog l where l.cw_type like '%gs%' and l.emp_uid = '"
							+ loginEmp
							+ "') is null, w.wdate >= (select min(l.wdate) from cw_worklog l where l.emp_uid = '"
							+ loginEmp
							+ "'),if((select cw_type from cw_worklog where emp_uid = w.emp_uid and wdate = (select max(l.wdate) from cw_worklog l where l.cw_type like '%gs%' and l.emp_uid = '"
							+ loginEmp
							+ "'))='gs',w.wdate >(select max(l.wdate) from cw_worklog l where l.cw_type like '%gs%' and l.emp_uid = '"
							+ loginEmp
							+ "'),w.wdate >= (select max(l.wdate) from cw_worklog l where l.cw_type like '%gs%' and l.emp_uid = '"
							+ loginEmp + "')))) order by w.wdate";
				}

				rs = MySqlOperation.wLogBySql(conn, sql);
				Date mindate = new Date();
				Date tempdate = null;
				Date maxdate = new Date();
				long time = 0;

				int days = 1;
				double ccmoney = 0.00;
				double ccdays = 0.00;
				double xiujiaDays = 0;
				double xinjiaDays = 0;
				String cw_type = "gs";
				Calendar cal = Calendar.getInstance();
				// 缺省记录需要的参数
				String lastcw_type = "cc";
				double subsidy = 60;

				while (rs.next()) {
					String wlog_uid = rs.getString("WorklogUID");
					if (wlog_uid == null)
						wlog_uid = rs.getString("workloguid");
					cw_type = rs.getString("cw_type");
					if ("gscc".equals(cw_type)) {
						cw_type = "cc";
					}
					Date wdate = rs.getDate("wdate");
					String waddress = rs.getString("waddress");
					double wallowance = rs.getDouble("wallowance");
					String ifhavexc = rs.getString("ifhavexc");
					cal.setTime(wdate);
					// 排除休假情况
					if ("cc".equals(cw_type) && days == 1) {
						mindate = wdate;
						// 计算这一天的补助，然后days++
						// 判断这一天是否为节假日

						if (ifhavexc != null) {
							List<BOInstance> list = WLogBuzhuTongjiUtil
									.findCW_XC(wlog_uid);
							if (list != null && list.size() > 0) {
								for (int l = 0; l < list.size(); l++) {
									BOInstance bi = list.get(l);
									double xc_days = bi
											.getDoubleValue("xc_days");
									double xc_money = bi
											.getDoubleValue("xc_money");

									// if (cal.get(Calendar.DAY_OF_WEEK) ==
									// Calendar.SUNDAY
									// || cal.get(Calendar.DAY_OF_WEEK) ==
									// Calendar.SATURDAY) {
									//
									// // 为ture，该双休日为法定工作日，故补助不必更改，否则需 *2/3
									// if (!WLogBuzhuTongjiUtil.ifHolidays(
									// format.format(wdate), true)) {
									// xc_money = xc_money * 2 / 3;
									// }
									// } else {
									// 为ture，该日为法定节假日，是则需 *1.5
									if (WLogBuzhuTongjiUtil.ifHolidays(
											format.format(wdate), false)) {
										xc_money = xc_money * 1.5;
									}
									// }

									ccmoney = ccmoney + xc_money;
									ccdays = ccdays + xc_days;
								}
							}
							
							if ("xj".equals(cw_type)) {
								List<BOInstance> xjlist = WLogBuzhuTongjiUtil
										.findCW_XC(wlog_uid, "x");
								if (xjlist != null && xjlist.size() > 0) {
									for (int l = 0; l < xjlist.size(); l++) {
										BOInstance bi = xjlist.get(l);
										double xc_days = bi
												.getDoubleValue("xc_days");
										double xc_money = bi
												.getDoubleValue("xc_money");
										xiujiaDays = xiujiaDays + xc_days;
									}
								}
						} else if ("xx".equals(cw_type)) {
								List<BOInstance> xxlist = WLogBuzhuTongjiUtil
										.findCW_XC(wlog_uid, "x");
								if (xxlist != null && xxlist.size() > 0) {
									for (int l = 0; l < xxlist.size(); l++) {
										BOInstance bi = xxlist.get(l);
										double xc_days = bi
												.getDoubleValue("xc_days");
										double xc_money = bi
												.getDoubleValue("xc_money");
										xinjiaDays = xinjiaDays + xc_days;
									}
								}
						}
							
						}
						// 添加出差天数
						days++;
						// 更新缺省需要的参数
						lastcw_type = "cc";
						subsidy = WLogBuzhuTongjiUtil.getBZbasic(waddress);
					} else if (days > 1) {
						// 首先判断始发为连续天数
						// 计算此后的补助，需按cw_type类型计算，休假则补助为零，薪假则需计算补助
						time = mindate.getTime() + (days - 1) * 24 * 60 * 60
								* 1000L;
						tempdate = new Date(time);
						int tempdays = (int) ((wdate.getTime() - tempdate
								.getTime()) / (24 * 60 * 60 * 1000L));

						// tempdays > 0,即有缺省日期，需计算
						if (tempdays > 0) {
							ccmoney = ccmoney
									+ WLogBuzhuTongjiUtil.getQueshMoney(
											tempdays, wdate, subsidy, format,
											lastcw_type);

							ccdays = ccdays + tempdays;
							days = days + tempdays;

							if ("xj".equals(lastcw_type)) {
								xiujiaDays = xiujiaDays + tempdays;
							} else if ("xx".equals(lastcw_type)) {
								xinjiaDays = xinjiaDays + tempdays;
							}
						}
						// 计算缺省日期后，再计算本条记录
						if (ifhavexc != null) {
							List<BOInstance> list = WLogBuzhuTongjiUtil
									.findCW_XC(wlog_uid);
							if (list != null && list.size() > 0) {
								for (int l = 0; l < list.size(); l++) {
									BOInstance bi = list.get(l);
									double xc_days = bi
											.getDoubleValue("xc_days");
									double xc_money = bi
											.getDoubleValue("xc_money");
									// if (cal.get(Calendar.DAY_OF_WEEK) ==
									// Calendar.SUNDAY
									// || cal.get(Calendar.DAY_OF_WEEK) ==
									// Calendar.SATURDAY) {
									//
									// // 为ture，该双休日为法定工作日，故补助不必更改，否则需 *2/3
									// if (!WLogBuzhuTongjiUtil.ifHolidays(
									// format.format(wdate), true)) {
									// xc_money = xc_money * 2 / 3;
									// }
									// } else {
									// 为ture，该日为法定节假日，是则需 *1.5
									if (WLogBuzhuTongjiUtil.ifHolidays(
											format.format(wdate), false)) {
										xc_money = xc_money * 1.5;
									}
									// }

									ccmoney = ccmoney + xc_money;
									ccdays = ccdays + xc_days;
								}
							}
						}
						// 更新缺省需要的参数
						lastcw_type = cw_type;
						subsidy = WLogBuzhuTongjiUtil.getBZbasic(waddress);
						// 添加出差天数
						days++;
						if ("xj".equals(cw_type)) {
								List<BOInstance> list = WLogBuzhuTongjiUtil
										.findCW_XC(wlog_uid, "x");
								if (list != null && list.size() > 0) {
									for (int l = 0; l < list.size(); l++) {
										BOInstance bi = list.get(l);
										double xc_days = bi
												.getDoubleValue("xc_days");
										double xc_money = bi
												.getDoubleValue("xc_money");
										xiujiaDays = xiujiaDays + xc_days;
									}
								}
						} else if ("xx".equals(cw_type)) {
								List<BOInstance> list = WLogBuzhuTongjiUtil
										.findCW_XC(wlog_uid, "x");
								if (list != null && list.size() > 0) {
									for (int l = 0; l < list.size(); l++) {
										BOInstance bi = list.get(l);
										double xc_days = bi
												.getDoubleValue("xc_days");
										double xc_money = bi
												.getDoubleValue("xc_money");
										xinjiaDays = xinjiaDays + xc_days;
									}
								}
						}
					}

				}
				rs.close();

				// 记录结束后，查看最后一条记录是否为今天，若不是，则继续计算缺省的日期
				// days
				// 代表下一个天数，即days=1，代表的是mindate，目前mindate+(days-1)仍旧为下一个天数，如mindate+1，代表的是mindate的下一天
				time = mindate.getTime() + (days - 1) * 24 * 60 * 60 * 1000L;
				tempdate = new Date(time);

				// 有因为 WLogBuzhuTongjiUtil.getQueshMoney(tempdays,maxdate,
				// subsidy, format, lastcw_type);中的maxdate为不缺省日期，故maxdate需大一天
				// 故tempdays = (int) ((maxdate.getTime() - tempdate.getTime()) /
				// (24 * 60 * 60 * 1000L));无需再+1
				time = maxdate.getTime() + 24 * 60 * 60 * 1000L;
				maxdate = new Date(time);
				int tempdays = (int) ((maxdate.getTime() - tempdate.getTime()) / (24 * 60 * 60 * 1000L));
				if (tempdays > 0) {
					ccmoney = ccmoney
							+ WLogBuzhuTongjiUtil.getQueshMoney(tempdays,
									maxdate, subsidy, format, lastcw_type);
					ccdays = ccdays + tempdays;
					days = days + tempdays;
					if ("xj".equals(lastcw_type)) {
						xiujiaDays = xiujiaDays + tempdays;
					} else if ("xx".equals(lastcw_type)) {
						xinjiaDays = xinjiaDays + tempdays;
					}
				}
				messages[0] = OperationUtil.round(ccdays, 2);
				messages[1] = OperationUtil.round(ccmoney, 2);
				messages[2] = wseladdress;
				messages[3] = OperationUtil.round(xiujiaDays, 2);
				messages[4] = OperationUtil.round(xinjiaDays, 2);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return messages;
	}

	public static List<String[]> getEmpMessage() {
		List<String[]> list = new ArrayList<String[]>();
		String[] empMgs = new String[2];
		String loginEmp = DOGlobals.getInstance().getSessoinContext()
				.getInstance().getUser().getUid();
		String loginDept = DOGlobals.getInstance().getSessoinContext()
				.getInstance().getUser().getValue("deptuid");
		//do_org_user_link_browse_dept_by_form
		DOService deptSer = DOService.getService("do_org_user_link_browse_dept_by_form");
		List<BOInstance> deptList = new ArrayList<BOInstance>();
		deptList = deptSer.invokeSelect(loginDept);
		if(deptList != null && deptList.size() > 0) {
			//cw_worklog_browse_get_new_jilu_by_emp_uid
			String[] str = null;
			for(int i = 0; i < deptList.size(); i++) {
				str = new String[2];
				BOInstance empBi = deptList.get(i);
				String user_uid = empBi.getValue("user_uid");
				DOService empser = DOService.getService("zf_employee_browse");
				List<BOInstance> emp = new ArrayList<BOInstance>();
				emp = empser.invokeSelect(user_uid); 
				String userName = null;
				if(emp.size()>0) {
					BOInstance empBi2 = emp.get(0);
					userName = empBi2.getValue("cn");
				}
				if(userName == null || "".equals(userName.trim())){
					userName = user_uid;
				}
				DOService wlogSer = DOService.getService("cw_worklog_browse_get_new_jilu_by_emp_uid");
				List<BOInstance> wlogList = new ArrayList<BOInstance>();
				wlogList = wlogSer.invokeSelect(user_uid);
				
				String address = "未知";
				if(wlogList != null && wlogList.size() > 0) {
					BOInstance wlogBi = wlogList.get(0);
					String waddress = wlogBi.getValue("waddress");
					String wseladdress = wlogBi.getValue("wseladdress");
					String wdate = wlogBi.getValue("wdate");
					if(waddress != null && "1".equals(waddress.trim())){
						address = "公司或本地";
					} else if(waddress != null) {
						address = wseladdress;
					}
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String nowsd = format.format(new Date());
					if(wdate.compareTo(nowsd)== 0) {
						
					} else if(wdate.compareTo(nowsd) > 0){
						
					} else {
						
					}
				}
				str[0] = userName;
				str[1] = address;
				list.add(str);
			}
		}
		
		return list;
	}
	
	public static String[] getReWuList() {
		String[] rwList = new String[2];
		String loginEmp = DOGlobals.getInstance().getSessoinContext()
				.getInstance().getUser().getUid();
		String loginDept = DOGlobals.getInstance().getSessoinContext()
				.getInstance().getUser().getValue("deptuid");
		List<String> roles = new ArrayList<String>();
		DOService deptmSer = DOService.getService("do_org_user_link_browse_find_depm_by_dept");
		List<BOInstance> deptmList = new ArrayList<BOInstance>();
		deptmList = deptmSer.invokeSelect(loginDept);
		if(deptmList != null && deptmList.size() > 0) {
			for(int i = 0; i < deptmList.size(); i++) {
				String role = null;
				BOInstance roleBi = deptmList.get(i);
				role = roleBi.getValue("user_uid");
				if(role != null)
					roles.add(role);
			}
			
		}
		
		DOService rwlbSer  = null;
		
		//登录人为部门经理
		if(roles != null && loginEmp != null && roles.contains(loginEmp.trim())) {
			rwlbSer = DOService.getService("cw_worklog_auto_condition_logsp_dept");
			rwList[1] = "bmjl";
		}
		//登录人为总经理
		else if(roles != null && loginEmp != null && !roles.contains(loginEmp.trim())) {
			DOService deptzSer = DOService.getService("do_org_user_link_browse_find_zjl");
			List<BOInstance> deptzList = new ArrayList<BOInstance>();
			deptzList = deptzSer.invokeSelect(loginDept);
			if(deptzList != null && deptzList.size() > 0) {
				for(int i = 0; i < deptzList.size(); i++) {
					String role = null;
					BOInstance roleBi = deptzList.get(i);
					role = roleBi.getValue("user_uid");
					if(role != null)
						roles.add(role);
				}
			}
			if(roles != null && loginEmp != null && roles.contains(loginEmp.trim())) {
				rwlbSer = DOService.getService("cw_worklog_auto_condition_logsp_zongjl");
				rwList[1] = "zjl";
			}
		}
		//任务列表服务不为空，则该登录人为部门经理或总经理
		if(rwlbSer != null) {
			List<BOInstance> wlogList = new ArrayList<BOInstance>();
			wlogList = rwlbSer.invokeSelect();
			if(wlogList != null)
				rwList[0] = wlogList.size()+"";
		} else {
			return null;
		}
		
		
		return rwList;
	}
	

	public static void main(String[] args) {
		String wdate = "2010-11-09";
		String nowsd = "2010-11-10";
		if(wdate.compareTo(nowsd)== 0) {
			System.out.println(wdate.compareTo(nowsd));
		} else if(wdate.compareTo(nowsd) > 0){
			System.out.println(wdate.compareTo(nowsd));
		} else {
			System.out.println(wdate.compareTo(nowsd));
		}
		
		
		

	}

}
