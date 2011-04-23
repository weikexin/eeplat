package com.exedosoft.plat.login.zidingyi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;

import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;
import com.exedosoft.plat.util.DOGlobals;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelLoadVacation extends DOAbstractAction {

	public String excute() {
		// System.out.println("??????????????????+++++++++++++++=");
		String path = null;
		String fileName = null;

		String isCover = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("iscover");
		String btn = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("vacation_load");
		path = DOGlobals.getInstance().getValue("uploadfiletemp")
				+ "vacationdate/";
		fileName = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("vacation_load");
		if(fileName != null) {
			fileName = unicode2GBK(fileName);
		}
		
		
		
		
		
		// fileName="国家法定节假日1.xls";

		if (fileName != null && fileName.trim().length() > 0) {
			// System.out.println("fileName" + fileName);
			// 批量导入时，只取最后一个文件
			if (fileName.indexOf(";") != -1) {
				String[] fileArr = fileName.split(";");
				fileName = fileArr[fileArr.length - 1];
				if (fileName != null && fileName.trim().length() <= 0)
					fileName = fileArr[fileArr.length - 2];
			} else if (fileName.indexOf("%3B") != -1) {
				String[] fileArr = fileName.split("%3B");
				fileName = fileArr[fileArr.length - 1];
				if (fileName != null && fileName.trim().length() <= 0)
					fileName = fileArr[fileArr.length - 2];
			}
			fileName = fileName.trim();

		} else {
			this.setEchoValue("文件名不能为空，请导入节假日时间表！");
			return DEFAULT_FORWARD;
		}

		System.out.println(fileName);

		if (path != null)
			path = path.trim();
		if (fileName != null)
			fileName = fileName.trim();
		String pathFile = path + fileName;
		String[] empty = new String[2];
		//1，成功;0，失败;2，数据错误
		try {

			empty = readExl(pathFile, isCover, fileName, btn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return DEFAULT_FORWARD;
		}

		// 修改文件名//无法实现，该文件正被使用
		// changeFile(pathFile);

		if ("0".equals(empty[0])) {
			BOInstance bi = new BOInstance();
			bi.putValue("empty", "notempty");
			bi.putValue("btn", btn);
			this.setInstance(bi);
			return this.DEFAULT_FORWARD;
		} else if ("1".equals(empty[0])) {
			return DEFAULT_FORWARD;
		} else if("2".equals(empty[0])){
			BOInstance bi = new BOInstance();
			String msg = empty[1];
			bi.putValue("msg", msg);
			bi.putValue("empty", "errordata");
			this.setInstance(bi);
			return this.DEFAULT_FORWARD;
		} else /*if("3".equals(empty[0]))*/{
			BOInstance bi = new BOInstance();
			String msg = empty[1];
			bi.putValue("msg", msg);
			bi.putValue("empty", "errordata2");
			this.setInstance(bi);
			return this.DEFAULT_FORWARD;
		} 

	}

	// List<String> list = new ArrayList<String>();
	InputStream fs = null;
	Workbook workBook = null;

	public String[] readExl(String filePath, String isCover, String fileName,
			String btn) throws Exception {

		String month = null;
		int countMonth = 0;

		// System.out.println("+++++执行readExl++++++");

		// 加载excel文件
		fs = new FileInputStream(filePath);
		// 得到 workbook
		workBook = Workbook.getWorkbook(fs);

		// 取得sheet，如果你的workbook里有多个sheet 可以利用 wb.getSheets()方法来得到所有的。
		// getSheets() 方法返回 Sheet[] 数组 然后利用数组来操作。就是多次循环的事。
		Sheet sheet = workBook.getSheet(0);// 这里只取得第一个sheet的值，默认从0开始
		// System.out.println(sheet.getColumns());// 查看sheet的列
		// System.out.println(sheet.getRows());// 查看sheet的行
		Cell cell = null;// 就是单个单元格
		// 开始循环，取得 cell 里的内容，这里都是按String来取的 为了省事，具体你自己可以按实际类型来取。或者都按
		// String来取。然后根据你需要强制转换一下。
		List<String[]> bzList = new ArrayList<String[]>();
		String[] dateStr = null;
		for (int j = 0; j < sheet.getRows(); j++) {
			cell = sheet.getCell(0, j);
			String temp = cell.getContents();
			if (temp != null)
				temp = temp.trim();
			if (temp.contains("节假日") || temp.contains("工作日"))
				continue;

			dateStr = new String[4];
			for (int i = 0; i < sheet.getColumns(); i++) {
				cell = sheet.getCell(i, j);
				String value = null;
				Date date = null;
				value = cell.getContents();
				if ((i == 0 || i == 1 || i == 2 || i == 3) && value != null) {
					if (value.contains("年") && value.contains("月")) {
						date = castDate(value);
					} else if (value.contains("/")) {
						date = castDate(value);
					} else if (value.contains("-")) {
						date = castDateSimple(value);
					} else if (value.matches("^\\d+$")) {
						long days = Long.parseLong(value);
						date = getDate(days);
					}

				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				if (date != null) {
					if(i == 2 || i == 3) {
						Calendar calQs = Calendar.getInstance();
						calQs.setTime(date);
						if (calQs.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
								|| calQs.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
							BOInstance bi = new BOInstance();
							String msg = "";
							if(calQs.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
								msg = "errordata: " + date + " 是星期天";
							else if(calQs.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
								msg = "errordata: " + date + " 是星期六";
							String[] empty = new String[2];
							empty[0] = "2";
							empty[1] = msg;
							return empty;
						}
						
						
					} else if(i == 1) {
						Calendar calQs = Calendar.getInstance();
						calQs.setTime(date);
						if (calQs.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
								&& calQs.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
							BOInstance bi = new BOInstance();
							String msg = "errordata: " + date + " 不是双休日";
							String[] empty = new String[2];
							empty[0] = "3";
							empty[1] = msg;
							return empty;
						}
					}
				}
				switch (i) {
				case 0:
					if (date != null && format.format(date) != null) {
						month = format.format(date);
						dateStr[i] = month;
					} else {
						dateStr[i] = null;
					}
					break;
				case 1:
					if (date != null && format.format(date) != null) {
						month = format.format(date);
						dateStr[i] = month;
					} else {
						dateStr[i] = null;
					}
					break;
				case 2:
					if (date != null && format.format(date) != null) {
						month = format.format(date);
						dateStr[i] = month;
					} else {
						dateStr[i] = null;
					}
					break;
				case 3:
					if (date != null && format.format(date) != null) {
						month = format.format(date);
						dateStr[i] = month;
					} else {
						dateStr[i] = null;
					}
					break;
				}
			}
			bzList.add(dateStr);
			// list.add(sb.toString());// 将每行的字符串用一个String类型的集合保存。
		}
		workBook.close();// 记得关闭

		// // 迭代集合查看每行的数据
		// for (String ss : list) {
		// System.out.println(ss);
		// }

		// 存储到数据库中
		Connection conn = MySqlOperation.getConnection();
		try {
			conn.setAutoCommit(false);
			String[] bzstr = null;
			ResultSet rs = null;

			// System.out.println("isCover=" + isCover);
			// System.out.println("isCover=" + isCover);
			// System.out.println("isCover=" + isCover);

			if (isCover == null) {
				boolean notEmpty = false;
				for (int i = 0; i < bzList.size(); i++) {
					bzstr = bzList.get(i);
					if (bzList != null) {
						for (int j = 0; j < bzstr.length && bzstr[j] != null; j++) {
							rs = MySqlOperation.findVacationByDate(conn,
									bzstr[j]);
							while (rs != null && rs.next()) {
								notEmpty = true;
								break;
							}
						}

						if (notEmpty)
							break;
					}
				}

				// 是否有重复的数据，若有，则提示；
				if (notEmpty) {
					BOInstance bi = new BOInstance();
					bi.putValue("notmpty", "notmpty");
					bi.putValue("btn", btn);
					this.setInstance(bi);
					String[] empty = new String[2];
					empty[0] = "0";
					empty[1] = "";
					return empty;
				}
				// 是否有重复的数据，若无，则继续往下执行
				/**
				 * ***************************
				 * */

			} else if (isCover.trim().equals("cover")) {
				// 全部覆盖
				// System.out.println("全部覆盖");
				// /
				for (int i = 0; i < bzList.size(); i++) {

					bzstr = bzList.get(i);
					if (bzList != null) {
						for (int j = 0; j < bzstr.length && bzstr[j] != null; j++) {
							java.util.Date date = new java.util.Date();
							long objuid = date.getTime() + j;
							boolean flag = false;
							rs = MySqlOperation.findVacationByDate(conn,
									bzstr[j]);
							while (rs != null && rs.next()) {
								flag = true;
								break;
							}
							if (flag) {
								MySqlOperation.deleteVacationByDate(conn,
										bzstr[j]);
								MySqlOperation.insertVacation(conn, (j + 1)
										+ "", bzstr[j], objuid);
							} else {
								// System.out.println("数据不重复：" + name + "\t"
								// + sm.getMonth());
								MySqlOperation.insertVacation(conn, (j + 1)
										+ "", bzstr[j], objuid);
							}
						}
					}
				}
				conn.commit();
				if (conn != null && !conn.isClosed())
					conn.close();
				// System.out.println("存储成功！cover");
				String[] empty = new String[2];
				empty[0] = "1";
				empty[1] = "";
				return empty;
			} else if (isCover.trim().equals("nocover")) {
				// 全部不覆盖
				// /
				for (int i = 0; i < bzList.size(); i++) {

					bzstr = bzList.get(i);
					if (bzList != null) {
						for (int j = 0; j < bzstr.length && bzstr[j] != null; j++) {
							System.out.println("=========++++++++++++++"
									+ bzstr[j]);
							java.util.Date date = new java.util.Date();
							long objuid = date.getTime() + j;
							boolean flag = false;
							rs = MySqlOperation.findVacationByDate(conn,
									bzstr[j]);
							while (rs != null && rs.next()) {
								flag = true;
								break;
							}
							if (flag) {

							} else {
								// System.out.println("数据不重复：" + name + "\t"
								// + sm.getMonth());
								MySqlOperation.insertVacation(conn, (j + 1)
										+ "", bzstr[j], objuid);
							}
						}
					}
				}
				conn.commit();
				if (conn != null && !conn.isClosed())
					conn.close();
				// System.out.println("存储成功！nocover");
				String[] empty = new String[2];
				empty[0] = "1";
				empty[1] = "";
				return empty;
			} else {
				// 暂时没有考虑到的情况
				System.out.println("暂时没有考虑到的情况暂时没有考虑到的情况");
				String[] empty = new String[2];
				empty[0] = "1";
				empty[1] = "";
				return empty;
			}
			// 继续往下执行
			for (int i = 0; i < bzList.size(); i++) {
				bzstr = bzList.get(i);
				if (bzList != null) {
					for (int j = 0; j < bzstr.length && bzstr[j] != null; j++) {
						java.util.Date date = new java.util.Date();
						long objuid = date.getTime();
						MySqlOperation.insertVacation(conn, (j + 1) + "",
								bzstr[j], objuid);
					}
				}
			}
			conn.commit();
			if (conn != null && !conn.isClosed())
				conn.close();
			// System.out.println("存储成功！noall");
		} catch (SQLException e) {
			System.out.println("存储失败！");
			e.printStackTrace();
			conn.rollback();
		}
		String[] empty = new String[2];
		empty[0] = "1";
		empty[1] = "";
		return empty;
	}
	
	

	private Date castDate(String value) {
		String year = "";
		String month = "";
		String day = "";
		Date date = null;
		if (value.contains("\"年\"") && value.contains("\"月\"")
				&& value.contains("\"日\"")) {
			year = value.substring(0, value.indexOf("\"年\""));
			month = value.substring(value.indexOf("\"年\"") + 3,
					value.indexOf("\"月\""));
			day = value.substring(value.indexOf("\"月\"") + 3,
					value.indexOf("\"日\""));
		} else if (value.contains("年") && value.contains("月")
				&& value.contains("日")) {
			year = value.substring(0, value.indexOf("年"));
			month = value.substring(value.indexOf("年") + 1, value.indexOf("月"));
			day = value.substring(value.indexOf("月") + 1, value.indexOf("日"));
		} else if (value.contains("/")) {
			String[] str = value.split("/");
			if(str.length == 3) {
				year = str[0];
				month = str[1];
				day = str[2];
			}
		}
		
		if(month.trim().length() == 1)
			month = "0"+month.trim();
		if(day.trim().length() == 1)
			day = "0"+day.trim();
		
		date = Date
				.valueOf(year.trim() + "-" + month.trim() + "-" + day.trim());
		return date;
	}

	private Date castDateSimple(String value) {
		String year = null;
		String month = null;
		String day = null;
		Date date = null;
		year = value.substring(0, value.indexOf("-"));
		month = value.substring(value.indexOf("-") + 1, value.lastIndexOf("-"));
		day = value.substring(value.lastIndexOf("-") + 1);
		if (year != null && year.trim().length() == 2) {
			if (Integer.parseInt(year) > 80) {
				year = "19" + year;
			} else {
				year = "20" + year;
			}
		}
		if(month.trim().length() == 1)
			month = "0"+month.trim();
		if(day.trim().length() == 1)
			day = "0"+day.trim();
		date = Date
				.valueOf(year.trim() + "-" + month.trim() + "-" + day.trim());
		return date;
	}

	private Date getDate(long dates) {
		Date date = null;
		// Date date0 = Date.valueOf("1900-01-00");
		// Date date1 = Date.valueOf("1970-01-01");

		// dates是离1900-01-00的天数
		// 计算离1970-01-00的天数
		// System.out.println((date1.getTime() - date0.getTime()) / 3600000 / 24
		// + "++++++++++++++++????????????????");
		long timebewteen = 25568L;

		dates = dates - timebewteen;

		// nowTime
		Calendar cal = Calendar.getInstance();
		//
		// Date date2 = Date.valueOf("1970-01-00");
		// long d1 = date2.getTime();
		// System.out.println(d1 + "=1=");

		Date date2 = Date.valueOf("1970-01-01");
		long d1 = date2.getTime() - 24 * 3600 * 1000L;
		// System.out.println(d1 + "=4=");

		long c = cal.getTimeInMillis();
		// 计算现在离1970-01-00的天数
		long days = (c - d1) / 3600000 / 24;
		// temp = 现在离1970-01-00的天数 - Excel中的时间离1970-01-00的天数
		// temp < 0, 则Excel中的时间大于现在时间。
		// temp > 0, 则Excel中的时间小于现在时间。
		int temp = (int) (days - dates);

		// ///
		java.util.Date dTest = (java.util.Date) cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = format.format(dTest);

		cal.add(Calendar.DATE, (0 - temp));

		dTest = (java.util.Date) cal.getTime();
		sDate = format.format(dTest);

		if (sDate != null)
			date = Date.valueOf(sDate);
		return date;
	}

	//Unicode编码转换为中文
	public String unicode2GBK(String str) {
		StringBuffer ret = new StringBuffer();
		if(str.contains("%u")) {
			String[] s = str.split("%u");
			ret.append(s[0]);
			for(int i = 1 ; i < s.length; i++ ) {
				ret.append((char)Integer.parseInt(s[i].substring(0, 4), 16));
				ret.append(s[i].substring(4));
			}
		} else {
			ret.append(str);
		}
		return ret.toString();
	}

	public static void main(String[] args) {
		ExcelLoadVacation exl = new ExcelLoadVacation();
//		// exl.getDate(1234567);
//		exl.castDate("2008\"年\"2\"月\"13\"日\"");
		
		String s = exl.unicode2GBK("Vac.xls");
		System.out.println(s);
		
	}

}
