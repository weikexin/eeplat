package com.exedosoft.plat.login.zidingyi.autorun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.WLogBuzhuTongjiUtil;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;
import com.exedosoft.plat.login.zidingyi.excel.OperationUtil;
import com.exedosoft.plat.util.id.UUIDHex;

public class RunInsertWLogLastMonth /*extends TimerTask*/ {
//	Timer timer = new Timer();
//
//	public void startNowLoop() {
//		Date date = new Date();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
//		if(month == 0) {
//			year = year - 1;
//			month = 12;
//		}
//		int day = OperationUtil.getMonthDay(year, month);
//		cal.set(year, month-1, day);
//		Date wdate = cal.getTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String sdate = format.format(wdate);
////		solveResult(sdate);
//		
//		timer.schedule(this, date, 10*1000L);
//	}
//	
//	int n = 0;
//	
//	@Override
//	public void run() {
//		n ++	;
//		System.out.println("执行中：：：：：：");
//		Calendar cal = Calendar.getInstance();
//		if((cal.get(Calendar.DAY_OF_MONTH) == 1 && cal.get(Calendar.HOUR_OF_DAY) == 1) || (n > 3)) {
//			Date date = new Date();
//			long time = date.getTime() - 24*60*60*1000L;
//			Date wdate = new Date(time);
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			String sdate = format.format(wdate);
//			solveResult(sdate);
//		}
//	}


	public static void insertWlog() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		if(month == 0) {
			year = year - 1;
			month = 12;
		}
		int day = OperationUtil.getMonthDay(year, month);
		cal.set(year, month-1, day);
		Date wdate = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = format.format(wdate);
		solveResult(sdate);
	}
	
	/*
	 * 得到返回结果后，处理放回结果。
	 */
	private static void solveResult(String date) {
		
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection conn = MySqlOperation.getConnection();
		String sql = "select emp_uid from cw_worklog group by emp_uid ";
		System.out.println("sql:::::: " + sql);
		try { 
			ResultSet rsEmp = MySqlOperation.wLogBySql(conn, sql);
			while (rsEmp != null && rsEmp.next()) {
				String emp_uid = rsEmp.getString("emp_uid");
				if (emp_uid != null && !"".equals(emp_uid.trim())) {
					
					//是否存在小于这一天的记录，若没有，则不用新增
					DOService findminDate = DOService
					.getService("cw_worklog_browse_get_new_jilu_by_date");
					List<BOInstance> listmin = new ArrayList<BOInstance>();
					listmin = findminDate.invokeSelect(emp_uid,date);
					if(listmin == null || listmin.size() <= 0) {
						continue;
					}
					
					DOService findNotThisDate = DOService
							.getService("cw_worklog_browse_by_form_wdate_emp_uid");
					List<BOInstance> list = new ArrayList<BOInstance>();
					list = findNotThisDate.invokeSelect(date, emp_uid);
					// 若没有记录，则自动新增一条记录
					if (list == null || list.size() <= 0) {
						System.out.println("记录不存在:::::: " + list);
						//首先查找当前人状态，出差或是在公司、休假等
						String objuid = (String) UUIDHex.getInstance().generate();
						String wemp_uid = emp_uid;
						String wdept_uid = "";
						String spuser_uid = "";
						String wDate = date;
						String appstatus = "2";
						String waddress = "";
						String wseladdress = "";
						String cw_type = "";
						String cw_xjsp = "";
						String xj_type = "";
						String is_xinjia = "";
						String auto_jlsp = "y";
						String tjdate = format.format(new Date());
						String wvacationtype = "";
						String wallowance = "0.00";
						
						
						
						
						DOService getNewData = DOService
						.getService("cw_worklog_browse_get_new_jilu_by_date");
						List<BOInstance> listdata = new ArrayList<BOInstance>();
						listdata = getNewData.invokeSelect(emp_uid,date);
						if(listdata != null && listdata.size() > 0) {
							BOInstance bidata = listdata.get(0);
							String wlog_uid = bidata.getValue("workloguid");
							wdept_uid = bidata.getValue("wdept_uid");
							cw_type = bidata.getValue("cw_type");
							cw_xjsp = bidata.getValue("cw_xjsp");
							xj_type = bidata.getValue("xj_type");
							is_xinjia = bidata.getValue("is_xinjia");
							wdept_uid = bidata.getValue("wdept_uid");
							waddress = bidata.getValue("waddress");
							wseladdress = bidata.getValue("wseladdress");
							wvacationtype = bidata.getValue("wvacationtype");
							
							//是否有地点变更信息
							DOService getModifyCity = DOService
							.getService("cw_modifycity_browse_by_wlog");
							List<BOInstance> listmcity = new ArrayList<BOInstance>();
							listmcity = getModifyCity.invokeSelect(wlog_uid);
							
							if(listmcity != null && listmcity.size() > 0) {
								BOInstance bicity = listmcity.get(listmcity.size() - 1);
								String modifytype = bicity.getValue("modifytype");
								String modifytime2 = bicity.getValue("modifytime2");
								String begincitytype  = bicity.getValue("begincitytype");
								String begincity  = bicity.getValue("begincity");
								String bzbasiceend = bicity.getValue("bzbasiceend");
								String bzbasice = bicity.getValue("bzbasice");
								String vacationtype = bicity.getValue("vacationtype");
								
								waddress = begincitytype;
								wseladdress = begincity;
								wvacationtype = vacationtype;
								
								if(modifytime2 == null || "".equals(vacationtype.trim())) {
									if("1".equals(modifytype) || "2".equals(modifytype)) {
										wallowance = bzbasiceend;
									} else if("3".equals(modifytype)){
										wallowance = bzbasice;
									} else if("4".equals(modifytype)){
										cw_xjsp = "y";
										if("xj".equals(is_xinjia)) {
											wallowance = "0.00";
										} else if("xx".equals(is_xinjia)) {
											wallowance = bzbasice;
										} else {
											wallowance = "0.00";
										}
										
									} else if("5".equals(modifytype)){
										cw_xjsp = "y";
										wallowance = "0.00";
									}
								} else {
									if("1".equals(modifytype) || "2".equals(modifytype)) {
										wallowance = bzbasiceend;
									} else if("3".equals(modifytype)){
										wallowance = "0.00";
									} else if("4".equals(modifytype)){
										cw_xjsp = "y";
										if("xj".equals(is_xinjia)) {
											wallowance = "0.00";
										} else if("xx".equals(is_xinjia)) {
											wallowance = bzbasice;
										} else {
											wallowance = "0.00";
										}
									} else if("5".equals(modifytype)){
										wallowance = bzbasiceend;
									}
								}
							} else {
								Double douallowance = WLogBuzhuTongjiUtil.getBZbasic(waddress);
								wallowance = douallowance.toString();
								
								if(cw_type.indexOf("cc") != -1) {
									//wallowance = wallowance;
								} else if(cw_type.indexOf("xj") != -1) {
									if("xx".equals(is_xinjia)) {
										//wallowance = wallowance;
									} else {
										wallowance = "0.00";
									}
								} else {
									if("2".equals(waddress.trim()) || "3".equals(waddress.trim())) {
										//wallowance = wallowance;
									} else {
										wallowance = "0.00";
									}
								}
							}
						}
						
						//已固定的数据
						//spuser_uid
						DOService getSpUser = DOService
						.getService("do_org_user_role_browse_get_dept_jingli");
						List<BOInstance> listspuser = new ArrayList<BOInstance>();
						listspuser = getSpUser.invokeSelect(wdept_uid);
						if(listspuser != null && listspuser.size() > 0) {
							BOInstance bi = listspuser.get(0);
							spuser_uid = bi.getValue("user_uid");
							if(emp_uid.equals(spuser_uid)) {
								DOService getSpZongjl = DOService
								.getService("do_org_user_role_browse_get_zong_jingli");
								List<BOInstance> listspzongjl = new ArrayList<BOInstance>();
								listspzongjl = getSpZongjl.invokeSelect();
								if(listspzongjl != null && listspzongjl.size() > 0) {
									auto_jlsp = null;
									
									BOInstance bizjl = listspzongjl.get(0);
									spuser_uid = bizjl.getValue("user_uid");
								}
							}
						}
						
						
						DOService insertThisDate = DOService
						.getService("cw_worklog_insert_logManage_auto");
						insertThisDate.invokeUpdate(objuid,wemp_uid,wdept_uid,spuser_uid,wDate,appstatus,waddress,wseladdress,
								cw_type,cw_xjsp,xj_type,is_xinjia,auto_jlsp,tjdate,wvacationtype,wallowance);
						
						System.out.println("新增记录服务:::::: " + insertThisDate);
						System.out.println("新增记录参数:::::: " + objuid+"	"+wemp_uid+"	"+wdept_uid+"	"+spuser_uid+"	"+wDate+"	"+appstatus+"	"+waddress+"	"+wseladdress+"	"+
								cw_type+"	"+cw_xjsp+"	"+xj_type+"	"+is_xinjia+"	"+auto_jlsp+"	"+tjdate+"	"+wvacationtype+"	"+wallowance);
						
						
					} else {
						System.out.println("记录已存在:::::: " + list);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
