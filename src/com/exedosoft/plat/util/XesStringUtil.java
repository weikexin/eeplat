package com.exedosoft.plat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class XesStringUtil {
	
	public static String getCurrentDayStr() {

		return String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance());
	}
}
