package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;
import com.exedosoft.plat.login.zidingyi.excel.OperationUtil;
import com.exedosoft.plat.util.DOGlobals;

public class WLogBuzhuTongjiSelf extends DOAbstractAction {
	public String excute() {

		// 需要接收的数据：查询条件
		String seltype = null;// 查询类型：0，天；1，月；2，年;
		String selname = null;// 员工姓名 ;
		String selrange = null;// 查询范围：dept，部门；all，所有 ;
		String seldept_uid = null;// 部门uid ;

		List users = new ArrayList();
		try {
			selrange = "geren";
			DOService service = DOService
					.getService("cw_worklog_browse_allowance_ck_geren");
			users = service.invokeSelect();
		} catch (Exception e) {
			this.setEchoValue("查询失败！error" + e.toString());
			return "notpass";
		}

		// 历遍所有的数据；
		if (users != null && users.size() > 0) {
			BOInstance bi = (BOInstance) users.get(0);
			seltype = bi.getValue("seltype");
			selname = bi.getValue("selname");
			selrange = bi.getValue("selrange");
			seldept_uid = bi.getValue("seldept_uid");
			if (seltype != null
					&& (seltype.trim().length() == 0 || "0".equals(seltype
							.trim())))
				seltype = null;
			if (selname != null && selname.trim().length() == 0)
				selname = null;

			if (selname == null) {
				String user_uid = DOGlobals.getInstance().getSessoinContext()
						.getInstance().getUser().getUid();
				selname = user_uid;
			}

			String year = null;
			String month = null;
			if (seltype == null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				String strDate = sdf.format(calendar.getTime());
				seltype = strDate;
			}

			String[] one = seltype.split("-");
			year = one[0];
			month = one[1];

			String sql = "";
			Connection conn = MySqlOperation.getConnection();
			try {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				List<String> listemp_uid = new ArrayList<String>();
				
				//个人查询，直接加入即可
				listemp_uid.add(selname);
				
				List<BOInstance> list = new ArrayList<BOInstance>();
				for (int cemp = 0 ; cemp < listemp_uid.size(); cemp++) {
					String emp_uid = listemp_uid.get(cemp);
					//是否存在小于这一天的记录，若没有，则不用新增
					DOService findminDate = DOService
					.getService("cw_worklog_browse_get_new_jilu_by_date");
					List<BOInstance> listmin = new ArrayList<BOInstance>();
					int monthDays = OperationUtil.getMonthDay(Integer.parseInt(year),
							Integer.parseInt(month));
					String mindate = year+"-"+month+"-"+monthDays;
					listmin = findminDate.invokeSelect(emp_uid,mindate);
					if(listmin == null || listmin.size() <= 0) {
						continue;
					}
					//若未审批完毕，则不统计，但显示
					boolean ifappstatus = false;
					String appstatus = null;
					if (emp_uid != null && !"".equals(emp_uid.trim())) {
						String workloguid = emp_uid + "-" + year + "-" + month;
						String date = year + "-" + month;
						double money = 0.00;
						sql = "select * from cw_worklog where  emp_uid = '"
								+ emp_uid
								+ "' and year(wdate) = "
								+ year
								+ " and month(wdate)="
								+ month
								+ " order by wdate ";

						 System.out.println("========================");
						 System.out.println(sql);
						 System.out.println("========================");
						
						/*
						 * 用于计算缺省日期的参数 cw_type: 前一记录的类型：公司'gs'、出差'cc'、休假'xj'
						 */

						ResultSet rs = MySqlOperation.wLogBySql(conn, sql);
						int day = 1;
						String waddress = "1";
						String cw_type = "gs";
						while (rs != null && rs.next()) {
							String wlog_uid = rs.getString("WorklogUID");
							if(wlog_uid == null)
								wlog_uid = rs.getString("workloguid");
							Date wdate = rs.getDate("wdate");
							cal.setTime(wdate);
							String ifhavexc = rs.getString("ifhavexc");
							
							if(!ifappstatus){
								appstatus = rs.getString("appstatus");
								if(!"3".equals(appstatus)) {
									ifappstatus = true;
								}
							}
							if (cal.get(Calendar.DAY_OF_MONTH) == day) {
								if(ifhavexc != null && ifhavexc.indexOf("c") != -1){
									List<BOInstance> listxc = WLogBuzhuTongjiUtil.findCW_XC(wlog_uid);
									if(listxc != null && listxc.size() > 0) {
										for(int l = 0; l < listxc.size(); l ++) {
											BOInstance bixc = listxc.get(l);
											double xc_days = bixc.getDoubleValue("xc_days");
											double xc_money = bixc.getDoubleValue("xc_money");
//											if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
//													|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//
//												// 为ture，该双休日为法定工作日，故补助不必更改，否则需 *2/3
//												if (!WLogBuzhuTongjiUtil.ifHolidays(
//														format.format(wdate), true)) {
//													xc_money = xc_money * 2 / 3;
//												}
//											} else {
												// 为ture，该日为法定节假日，是则需 *1.5
												if (WLogBuzhuTongjiUtil.ifHolidays(
														format.format(wdate), false)) {
													xc_money = xc_money * 1.5;
												} 
//											}
											
											money = money + xc_money;
										}
									}
								}

								// 更新 cw_type 和 waddress
								cw_type = rs.getString("cw_type");
								if("gscc".equals(cw_type)) {
									cw_type = "cc";
								}
								waddress = rs.getString("waddress");
							} else {
								// 1号为缺省日期时，则需向上一个月的最后记录取cw_type
								if (day == 1) {
									String[] str = WLogBuzhuTongjiUtil.getCw_type(emp_uid,
											format.format(wdate));
									// 更新 cw_type 和 waddress
									cw_type = str[0];
									waddress = str[1];
								}
								// 取得补助标准
								double subsidy = WLogBuzhuTongjiUtil.getBZbasic(waddress);
								int nowDays = cal.get(Calendar.DAY_OF_MONTH)
										- day;
								// 按照cw_type 和 节假日 和 补助标准 计算补助
								// 计算缺省日期
								money = money + WLogBuzhuTongjiUtil.getQueshMoney(nowDays, wdate, subsidy, format, cw_type);

								// 计算完缺省日期后，再计算这一天的
								if(ifhavexc != null && ifhavexc.indexOf("c") != -1){
									List<BOInstance> listxc = WLogBuzhuTongjiUtil.findCW_XC(wlog_uid);
									if(listxc != null && listxc.size() > 0) {
										for(int l = 0; l < listxc.size(); l ++) {
											BOInstance bixc = listxc.get(l);
											double xc_days = bixc.getDoubleValue("xc_days");
											double xc_money = bixc.getDoubleValue("xc_money");
											
//											if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
//													|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//
//												// 为ture，该双休日为法定工作日，故补助不必更改，否则需 *2/3
//												if (!WLogBuzhuTongjiUtil.ifHolidays(
//														format.format(wdate), true)) {
//													xc_money = xc_money * 2 / 3;
//												}
//											} else {
												// 为ture，该日为法定节假日，是则需 *1.5
												if (WLogBuzhuTongjiUtil.ifHolidays(
														format.format(wdate), false)) {
													xc_money = xc_money * 1.5;
												} 
//											}
											
											money = money + xc_money;
										}
									}
								}

								// 更新 cw_type 和 waddress
								cw_type = rs.getString("cw_type");
								if("gscc".equals(cw_type)) {
									cw_type = "cc";
								}
								waddress = rs.getString("waddress");

								// 计算完成后，day更新为缺省天数，再加上这一天
								day = day + nowDays + 1;
							}
						}
						
						//取得当前月的总天数及现在日期，若当前月与现在月份不一致，则用当前月的总天数，否则只能使用现在日期
						int intYear = Integer.parseInt(year);
						int intMonth = Integer.parseInt(month);
						int currentDays = OperationUtil.getMonthDay(intYear, intMonth);
						Date nowDate = new Date();
						
						String strNowDate = format.format(nowDate);
						String strDate[] = strNowDate.split("-");
						String nowYear = strDate[0];
						String nowMonth = strDate[1];
						String nowDay = strDate[2];
						int intNowYear = Integer.parseInt(nowYear);
						int intNowMonth = Integer.parseInt(nowMonth);
						int intNowDay = Integer.parseInt(nowDay);
						
						if(intNowYear == intYear && intNowMonth == intMonth) {
							//day小于现在日期天数+1，则需缺省日期
							if(intNowDay > day-1) {
								int countDays = intNowDay - day+1;
								double subsidy = WLogBuzhuTongjiUtil.getBZbasic(waddress);
								//wdate为不为缺省日期， 故nowDate需加一天，
								long time = nowDate.getTime() + 24 * 60 * 60 * 1000L;
								Date nextDay = new Date(time);
								// 按照cw_type 和 节假日 和 补助标准 计算补助
								// 计算缺省日期
								money = money + WLogBuzhuTongjiUtil.getQueshMoney(countDays, nextDay, subsidy, format, cw_type);
							}
						} else if ((intNowYear == intYear && intNowMonth > intMonth) ||intNowYear > intYear) {
							//day小于月的总天数+1，则需缺省日期
							if(currentDays > day-1) {
								int countDays = currentDays - day+1;
								double subsidy = WLogBuzhuTongjiUtil.getBZbasic(waddress);
								//wdate为不为缺省日期， 故nowDate需加一天，
								String strdate = year+"-"+month+"-"+currentDays;
								Date currdate = format.parse(strdate);
								long time = currdate.getTime() + 24 * 60 * 60 * 1000L;
								Date nextDay = new Date(time);
								// 按照cw_type 和 节假日 和 补助标准 计算补助
								// 计算缺省日期
								money = money + WLogBuzhuTongjiUtil.getQueshMoney(countDays, nextDay, subsidy, format, cw_type);
							}
						} else {
							//其它情况，日期未到，无需计算
						}
						
						
						if(rs != null) {
							rs.close();
						}
						BOInstance biresult = new BOInstance();
						biresult.setBo(this.service.getBo());
						biresult.setUid(workloguid);
						biresult.putValue("workloguid", workloguid);
						biresult.putValue("name", emp_uid);
						String dMoney = OperationUtil.round(money, 2);
						biresult.putValue("money", dMoney);
						if(ifappstatus) {
							if(appstatus == null)
								appstatus = "&nbsp;";
							biresult.putValue("appstatus", appstatus);
						} else {
							biresult.putValue("appstatus", "3");
						}
						biresult.putValue("date", date);
						list.add(biresult);
					}
				}

				
				this.setInstances(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setEchoValue("查询失败！1001");
			} catch (ParseException e) {
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

		} else {
			this.setEchoValue("查询失败！1002");
		}

		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {
	}

}
