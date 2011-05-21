package com.exedosoft.plat.login.zidingyi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.id.UUIDHex;

public class WLogBuzhuTongjiUtil {

	public static String[] getCw_type(String emp_uid, String wdate) {
		// 没有变更地点信息时，需取上一条记录判断
		// str[0]:cw_type
		// str[1]:waddress
		// 计算补助时，需要这两个参数计算

		String[] str = new String[2];

		// ///cw_worklog_browse_getcw_type_last_byform
		// //select cw_type,waddress from cw_worklog where emp_uid = ? and wdate
		// < ? order by wdate desc limit 0,1

		DOService cw_typeSevr = DOService
				.getService("cw_worklog_browse_getcw_type_last_byform");
		List cw_typeList = new ArrayList();
		try {
			cw_typeList = cw_typeSevr.invokeSelect(emp_uid, wdate);
			if (cw_typeList != null && cw_typeList.size() > 0) {
				// 有记录，则取cw_type
				BOInstance bi = (BOInstance) cw_typeList.get(0);
				str[0] = bi.getValue("cw_type");
				if ("gscc".equals(str[0])) {
					str[0] = "cc";
				}
				str[1] = bi.getValue("waddress");
			} else {
				// 若前面没有记录，则默认为在公司，即补助为0
				str[0] = "gs";
				str[1] = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	public static double getBZbasic(String waddress) {
		// 没有变更地点信息时，需取上一条记录判断
		double subsidy = 0.00;

		// ///cw_citysubsidy_browse_subsidy_by_typecode
		// //select subsidy from cw_citysubsidy where typecode = ?

		DOService cw_typeSevr = DOService
				.getService("cw_citysubsidy_browse_subsidy_by_typecode");
		List cw_typeList = new ArrayList();
		try {
			cw_typeList = cw_typeSevr.invokeSelect(waddress);
			if (cw_typeList != null && cw_typeList.size() > 0) {
				// 有记录，则取cw_type
				BOInstance bi = (BOInstance) cw_typeList.get(0);
				subsidy = bi.getDoubleValue("subsidy");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subsidy;
	}

	public static boolean ifHolidays(String wdate, boolean isSunday) {
		// 没有变更地点信息时，需取上一条记录判断
		boolean flag = false;

		if (isSunday) {
			// 计算双休日补助，双休日若不是法定工作日，则不用更改，否则需按 *2/3计算；
			// 计算双休日是否为法定工作日：select count(*) as result from cw_holidays h where
			// h.daytype='1' and h.holiday = wdate ， result > 0,法定节假日
			DOService sundaySevr = DOService
					.getService("cw_holidays_browse_if_sunday_by_holiday");

			List sundayList = new ArrayList();
			try {
				sundayList = sundaySevr.invokeSelect(wdate);
				if (sundayList != null && sundayList.size() > 0) {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			// 计算日期是否为法定节假日，若是，则需按*1.5计算,否则不用更改；
			// 计算双休日是否为法定工作日：select count(*) as result from cw_holidays h where
			// h.daytype='2' and h.holiday = wdate ， result > 0,法定工作日
			DOService workdaySevr = DOService
					.getService("cw_holidays_browse_if_workday_by_holiday");
			List workdayList = new ArrayList();
			try {
				workdayList = workdaySevr.invokeSelect(wdate);
				if (workdayList != null && workdayList.size() > 0) {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	//
	public static double getQueshMoney(int nowDays, Date wdate, double subsidy,
			SimpleDateFormat format, String cw_type) {
		double money = 0;
		// wdate为不为缺省日期
		// 按照cw_type 和 节假日 和 补助标准 计算补助
		// 计算缺省日期
		/*
		 * 用于计算缺省日期的参数 cw_type: 前一记录的类型：公司'gs'、出差'cc'、休假'xj'、带薪休假'xx'
		 */
		// 公司'gs'、休假'xj'没有补助，不用计算
		if ("cc".equals(cw_type) || "xx".equals(cw_type)) {
			for (int i = 0; i < nowDays; i++) {
				long time = wdate.getTime() - (nowDays - i) * 24 * 60 * 60
						* 1000L;
				Date qsDate = new Date(time);
				String stqsDate = format.format(qsDate);
				Calendar calQs = Calendar.getInstance();
				calQs.setTime(qsDate);

				// 为ture，该日为法定节假日，是则标准补助需 *1.5
				if (WLogBuzhuTongjiUtil.ifHolidays(format.format(wdate), false)) {
					money = money + subsidy * 1.5;
				} else {
					money = money + subsidy;
				}
			}
		}
		return money;
	}

	// 计算这一天的补助是否需加成
	public static double getHolidaysMoney(Date wdate, double money) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		double hmoney = 0.00;
		Calendar calQs = Calendar.getInstance();
		calQs.setTime(wdate);
		if (money == 0) {
			return hmoney;
		}

		// 为ture，该日为法定节假日，是则标准补助需 *1.5
		if (WLogBuzhuTongjiUtil.ifHolidays(format.format(wdate), false)) {
			money = money * 1.5;
		}
		return hmoney;
	}

	// 存入该记录出差或休假的数据
	public static void insertOrUpdateCW_XC(String wlog_uid, String xc_type,
			String xc_days, String xc_money,String xc_endtime) {
		try {
				String id = (String) UUIDHex.getInstance().generate();
				DOService cw_xcSer = DOService
						.getService("cw_xiujiaorchuchai_insert_cw_worklog");
				cw_xcSer.invokeUpdate(id, wlog_uid, xc_type, xc_days, xc_money,xc_endtime);

		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			System.out.println("插入cw_xiujiaorchuchai_insert表记录失败：：：：：：：：：");
			e.printStackTrace();
		}

	}
	
	// 删除出差或休假的数据
	public static void deleteCW_XC(String wlog_uid) {
		try {
				DOService cw_xcSer = DOService
						.getService("cw_xiujiaorchuchai_delete_cw_worklog");
				cw_xcSer.invokeUpdate(wlog_uid);

		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			System.out.println(" 删除出差或休假的数据表记录失败：：：：：：：：：");
			e.printStackTrace();
		}

	}

	// 查询该记录出差或休假的数据
	public static List<BOInstance> findCW_XC(String wlog_uid) {
		// cw_xiujiaorchuchai_insert
		DOService cw_xcSer = DOService
				.getService("cw_xiujiaorchuchai_browse_by_wlog");
		List<BOInstance> list = new ArrayList<BOInstance>();
		list = cw_xcSer.invokeSelect(wlog_uid);
		return list;
	}

	public static void main(String[] args) {
		List<BOInstance> list = WLogBuzhuTongjiUtil.findCW_XC(
				"2c90b0e72ea360b1012ea3678c870002");
		System.out.println(list.size());
	}

	public static List<BOInstance> findCW_XC(String wlog_uid, String string) {
		// cw_xiujiaorchuchai_insert
		DOService cw_xcSer = DOService
				.getService("cw_xiujiaorchuchai_browse_by_wlog_type");
		List<BOInstance> list = new ArrayList<BOInstance>();
		list = cw_xcSer.invokeSelect(wlog_uid);
		return list;
	}

}
