package com.exedosoft.plat.reporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;

public class TimerAxis {

	private static Log log = LogFactory.getLog(TimerAxis.class);

	/**
	 * 获取时间轴，包含一个月的数据
	 * 
	 * @param calendar
	 * @param theYear
	 * @param theMonth
	 * @return
	 */
	public static String getMonthTimerAxis(Calendar calendar, String theYear,
			String theMonth) {

		if (calendar == null || theYear == null || theMonth == null) {
			return "传入的参数不能为空！";

		}

		calendar.set(Calendar.YEAR, Integer.parseInt(theYear));
		
		calendar.set(Calendar.MONTH, Integer.parseInt(theMonth) - 1);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		
		
		// ///////////////这个月有多少天
		StringBuilder out = new StringBuilder();
		int daysofmonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i <= daysofmonth; i++) {
			out
					.append("<th  nowrap='nowrap'  align='center' sort='asc' title='");
			out.append(theMonth);
			out.append("月");
			out.append(i);
			out.append("日' ");

			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				out.append(" style='background:white'");
			}

			out.append(" >");
			// out.append("<a href='javascript:loadReporterPaneMine(");
			// out.append(i);
			// out.append(",");
			// out.append(theMonth);
			// out.append(",");
			// out.append(theYear);
			// out.append(")'>");
			// pane_gt_reporter_list_by_mine.jsp
			out.append(i);
			out.append("</th>");
			// </a>
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return out.toString();
	}

	/**
	 * 获取一个时间轴，获取一年十二个月的数据
	 * 
	 * @param theYear
	 * @return
	 */
	public static String getYearTimerAxis(String theYear) {

		StringBuilder out = new StringBuilder();
		for (int i = 1; i <= 12; i++) {
			out
					.append("<th  nowrap='nowrap'  align='center' sort='asc' title='");
			out.append(theYear);
			out.append("年");
			out.append(i);
			out.append("月' ");
			out.append(" >");

			// out.append("<a href='javascript:loadReporterPaneMine(");
			// out.append(i);
			// out.append(",");
			// out.append(i);
			// out.append(",");
			// out.append(theYear);
			// out.append(")'>");
			// pane_gt_reporter_list_by_mine.jsp
			out.append(i);
			out.append("月</th>");
			// </a>
		}

		return out.toString();

	}

	/**
	 * 时间轴 左滚动的提示
	 * 
	 * @param aMonth
	 * @param aYear
	 * @return
	 */
	public static String getLeftAlt(String aMonth, String aYear) {

		if (aMonth == null || aYear == null) {
			log.warn("WHEN Get Left ALT MONTH or YEAR MayBe NULL");
			return "";
		}
		int iMonth = Integer.parseInt(aMonth);
		int iYear = Integer.parseInt(aYear);

		iMonth = iMonth + 1;
		if (iMonth == 13) {
			iMonth = 1;
			iYear = iYear + 1;
		}

		if ("-1".equals(aMonth)) {
			iYear = iYear + 1;
		}

		StringBuilder sb = new StringBuilder().append(iYear).append("年");

		if (!"-1".equals(aMonth)) {
			sb.append(iMonth).append("月");
		}
		return sb.toString();
	}

	/**
	 * 时间轴 右滚动的提示
	 * 
	 * @param aMonth
	 * @param aYear
	 * @return
	 */
	public static String getRightAlt(String aMonth, String aYear) {

		if (aMonth == null || aYear == null) {
			log.warn("WHEN Get Right ALT MONTH or YEAR MayBe NULL");
			return "";
		}
		int iMonth = Integer.parseInt(aMonth);
		int iYear = Integer.parseInt(aYear);

		iMonth = iMonth - 1;
		if (iMonth == 0) {
			iMonth = 12;
			iYear = iYear - 1;
		}

		if ("-1".equals(aMonth)) {
			iYear = iYear - 1;
		}

		StringBuilder sb = new StringBuilder().append(iYear).append("年");

		if (!"-1".equals(aMonth)) {
			sb.append(iMonth).append("月");
		}
		return sb.toString();
	}

}
