package com.exedosoft.plat.action.zidingyi;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.exedosoft.plat.util.id.UUIDHex;

public class OperationUtil {


	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * @param args
	 *            获取月的总天数
	 */
	public static int getMonthDay(int year, int month) {
		int days = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = year + "-02-29";
			try {
				date = format.parse(dateStr);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
					days = 28;
				} else if (cal.get(Calendar.DAY_OF_MONTH) == 29) {
					days = 29;
				}

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return days;
	}

	public static String getDateString() {
		String dstr = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		dstr = format.format(date);
		return dstr;
	}

	public static String getDateTimeString() {
		String dstr = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
		Date date = new Date();
		dstr = format.format(date);
		return dstr;
	}

	public static void main(String[] args) {

	}
}
