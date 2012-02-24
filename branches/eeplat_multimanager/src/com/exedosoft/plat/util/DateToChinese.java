/**
 * 
 */
package com.exedosoft.plat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lizonghai
 *
 */
public class DateToChinese {

	static String[] chinese={"零","一","二","三","四","五","六","七","八","九"};
	static String[] len={"十"};
	static String[] ydm={"年","月","日"};
	
	public static String getDateToChinese(String s)
	{
		int sleng=s.length();
		String result="";
		for(int i=0;i<sleng;i++)
		{
			result+=chinese[Integer.parseInt(s.substring(i, i+1))];
		}
		return result; 
	}
	
	
	private static SimpleDateFormat dateFormat = null; 
	static 
	{ 
//	 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写； 
	dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
//	 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01 
	dateFormat.setLenient(false); 
	} 

	public static boolean isValidDate(String s) 
	{ 
	try 
	{ 
	dateFormat.parse(s); 
	return true; 
	} 
	catch (Exception e) 
	{ 
//	 如果throw java.text.ParseException或者NullPointerException，就说明格式不对 
	return false; 
	} 
	} 
 
//	 下面这个方法则可以将一个日期按照你指定的格式输出 
	public static String formatDate(Date d) 
	{ 
	return dateFormat.format(d); 
	} 

	//输出最终的日期结果
	public static String baoDateToChinese(String s)
	{
		String toresult="";
		String d[]=s.split(" ");
		s=d[0];
		boolean b=isValidDate(s);
		if(b)
		{
			String c[]=s.split("-");
			for(int i=0;i<c.length;i++)
			{
				toresult+=n2c(c[i])+ydm[i];
			}
			
		}
		else
		{
			toresult="您输入的日期格式不正确,请确保输入的日期格式为yyyy-mm-dd,如2008-02-26";
		}
		return toresult;
	}
	
	//输出最终的日期结果
	public static String DateToChineseWithNum(String s)
	{
		String toresult="";
		String d[]=s.split(" ");
		s=d[0];
		boolean b=isValidDate(s);
		if(b)
		{
			String c[]=s.split("-");
			for(int i=0;i<c.length;i++)
			{
				toresult+=c[i]+ydm[i];
			}
			
		}
		else
		{
			toresult="您输入的日期格式不正确,请确保输入的日期格式为yyyy-mm-dd,如2008-02-26";
		}
		return toresult;
	}
	
	public static String getCurrentDayStr() {

		return DateToChineseWithNum(String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
	}
	
	public static String n2c(String s)
	{
	    if(s.length()==2)
	    {  
	         if("1".equals(s.substring(0,1)))
	         {
	            if("0".equals(s.substring(1,2)))return len[0];
	            return len[0]+chinese[Integer.parseInt(s.substring(1, 2))];
	          }
	         if("0".equals(s.substring(0,1)))
	        	 return chinese[Integer.parseInt(s.substring(1, 2))];
	     if("0".equals(s.substring(1, 2)))return chinese[Integer.parseInt(s.substring(0, 1))]+len[0];
	        return chinese[Integer.parseInt(s.substring(0, 1))]+len[0]+chinese[Integer.parseInt(s.substring(1, 2))];
	     }
		return getDateToChinese(s);
	}
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO 自动生成方法存根

		//System.out.print(DateToChinese.getDateToChinese("174923"));
		System.out.println(DateToChinese.baoDateToChinese("2008-12-23 "));
		System.out.println("另一种格式");
		System.out.println(DateToChinese.DateToChineseWithNum("2008-12-23 "));
		Date date=new Date();
		System.out.println(date.toLocaleString());
		System.out.println(StringUtil.getCurrentDayStr());
		System.out.println(getCurrentDayStr());
		

	}

}
