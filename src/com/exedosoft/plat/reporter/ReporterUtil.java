package com.exedosoft.plat.reporter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReporterUtil {
	
	private static Log log = LogFactory.getLog(ReporterUtil.class);
	
	
	
	/**
	 * 获取前一天的日期
	 */
	
	public static String getBeforADay(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		Calendar cc = Calendar.getInstance();
		cc.add(Calendar.DAY_OF_MONTH, -1);
		return sdf.format(cc.getTime());
	}
	
	/**
	 * 最后的统计行输出
	 * @param daysofmonth
	 * @param sumCols
	 * @return
	 */

	public static String getStatTR(long[] sumCols) {

		StringBuilder out = new StringBuilder();

		out.append("<tr>");
		out.append("<td  nowrap='nowrap'  align='center' sort='asc'  >");
		out.append("合计");
		out.append("</td>");
		long lrowTotal = 0;
		for (int i = 1; i <= sumCols.length; i++) {
			out.append("<td  nowrap='nowrap'  align='center' sort='asc'  >");
			if(sumCols[i - 1]>0){
				out.append(sumCols[i - 1]);
			}else{
				out.append("&nbsp");
			}
			out.append("</td>");
			lrowTotal = lrowTotal + sumCols[i - 1];
		}
		out.append("<td  nowrap='nowrap'  align='center' sort='asc'  >");
		
		if(lrowTotal>0){
			out.append(lrowTotal);
		}else{
			out.append("&nbsp;");
		}
		
		out.append("</td>");

		out.append("</tr>");
		return out.toString();
	}
	/**
	 * 最后的统计数字输出
	 * @param sumCols
	 * @return
	 */
	
	public static long getStatNumber(long[] sumCols){
		
		long ret = 0;
		for(int i = 0;i < sumCols.length; i++){
			ret = ret + sumCols[i];
		}
        return ret;		
	}

}
