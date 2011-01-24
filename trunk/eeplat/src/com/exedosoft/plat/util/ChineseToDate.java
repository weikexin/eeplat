package com.exedosoft.plat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;


/**
 * for连杰. 第一步只支持28-七月-2008 这种格式。 返回 java.sql.Date
 */
public class ChineseToDate {

	static String[] chinese = { "一", "二", "三", "四", "五", "六", "七", "八", "九",
			"十" };

	private static SimpleDateFormat dateFormat = null;
	static {
		// 指定日期格式为四位年/两位月份/两位日期，注意dd-MM-yyyy区分大小写；
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		// 设置lenient为false.
		// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		dateFormat.setLenient(false);
	}

	/**
	 * for连杰. 第一步只支持类似 28-七月-2008 这种格式。 返回 java.sql.Date
	 * @throws ParseException 

	 */
	public static java.sql.Date getChineseToSqlDate(String s) throws ParseException
			 {

		// 把月去掉
		s = s.replaceAll("月", "");
		s = s.replaceAll("十一", "11");
		s = s.replaceAll("十二", "12");
		for (int i = 0; i < chinese.length; i++) {
			s = s.replaceAll(chinese[i], String.valueOf(i + 1));
		}
		java.util.Date d = dateFormat.parse(s);
		return new java.sql.Date(d.getTime());
	}

	/**
	 * @param args
	 * @throws ParseException 
	 */

	public static void main(String[] args) throws ParseException {
		// TODO 自动生成方法存根
		//System.out.println(ChineseToDate.getChineseToSqlDate("28-七月-2008"));
		
		HashMap t = new HashMap();
		t.put("a", null);

	}

}
