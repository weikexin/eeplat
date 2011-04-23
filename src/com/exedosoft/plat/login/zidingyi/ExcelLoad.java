package com.exedosoft.plat.login.zidingyi;

import java.io.FileInputStream;
import java.io.InputStream;
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

public class ExcelLoad extends DOAbstractAction {

	public String excute() {
//		System.out.println("??????????????????+++++++++++++++=");
		String path = null;
		String fileName = null;
		String sqlDate = null;
		String sqlFile = null;
		
		String isCover = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("iscover");
		path = DOGlobals.getInstance().getValue("uploadfiletemp");
		fileName = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("excel_load");
		System.out.println("===============" + isCover);
		System.out.println("===============" + path);
		System.out.println("===============" + fileName);
		System.out.println("===============" + fileName);
		
//		System.out.println(path);
		java.util.Date date = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String sdate = format.format(date);
		sqlDate = sdate;
		
		if(fileName != null && fileName.trim().length() > 0) {
//			System.out.println("fileName" + fileName);
			//批量导入时，只取最后一个文件
			if(fileName.indexOf(";") != -1) {
				String[] fileArr = fileName.split(";");
				fileName = fileArr[fileArr.length-1];
				if(fileName != null && fileName.trim().length() <= 0)
					fileName = fileArr[fileArr.length-2];
			} else if(fileName.indexOf("%3B") != -1) {
				String[] fileArr = fileName.split("%3B");
				fileName = fileArr[fileArr.length-1];
				if(fileName != null && fileName.trim().length() <= 0)
					fileName = fileArr[fileArr.length-2];
			}
			fileName = fileName.trim();
			sqlFile = fileName;
			
			
			if(path != null && path.trim().length() > 0) {
				path = path.trim();
				if(path.lastIndexOf("\\\\") != -1) {
					fileName = sdate + "\\\\" + fileName;
				} else if(path.lastIndexOf("\\") != -1) {
					fileName = sdate + "\\" + fileName;
				} else if(path.lastIndexOf("/") != -1) {
					fileName = sdate + "/" + fileName;
				}
			}
		}else {
			this.setEchoValue("文件名不能为空，请导入工资条！");
			return DEFAULT_FORWARD;
		}
		

		try {
//			System.out.println(sqlDate);
//			System.out.println(sqlFile);
//			System.out.println(fileName);
			Connection conn = MySqlOperation.getConnection();
			ResultSet rs = MySqlOperation.SMfindByFileName(conn, sqlDate, sqlFile);
			if(rs != null && rs.next()) {
				BOInstance bi = new BOInstance();
				bi.putValue("isexist", "isexist");
				this.setInstance(bi);
				this.setEchoValue("文件已存在，若要重新导入，请更改文件名称后再导入！");
				return DEFAULT_FORWARD;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			this.setEchoValue("导入工资条失败！");
			return DEFAULT_FORWARD;
		}
		
//		System.out.println(fileName);
//		System.out.println("??????????????????+++++++++++++++=");

		if (path != null)
			path = path.trim();
		if (fileName != null)
			fileName = fileName.trim();
		String pathFile = path + fileName;
		boolean empty = false;
		try {
			empty = readExl(pathFile, isCover, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.setEchoValue("导入工资条失败！");
			return DEFAULT_FORWARD;
		}

		// 修改文件名//无法实现，该文件正被使用
		// changeFile(pathFile);

		if (!empty) {
			BOInstance bi = new BOInstance();
			bi.putValue("empty", "notempty");
			this.setInstance(bi);
			return this.DEFAULT_FORWARD;
		} else {
			this.setEchoValue("导入工资条成功！");
			return DEFAULT_FORWARD;
		}

	}

	// List<String> list = new ArrayList<String>();
	InputStream fs = null;
	Workbook workBook = null;

	public boolean readExl(String filePath, String isCover, String fileName) throws Exception {

		String month = null;
		int countMonth = 0;

//		System.out.println("+++++执行readExl++++++");

		// 加载excel文件
		fs = new FileInputStream(filePath);
		// 得到 workbook
		workBook = Workbook.getWorkbook(fs);

		// 取得sheet，如果你的workbook里有多个sheet 可以利用 wb.getSheets()方法来得到所有的。
		// getSheets() 方法返回 Sheet[] 数组 然后利用数组来操作。就是多次循环的事。
		Sheet sheet = workBook.getSheet(0);// 这里只取得第一个sheet的值，默认从0开始
//		System.out.println(sheet.getColumns());// 查看sheet的列
//		System.out.println(sheet.getRows());// 查看sheet的行
		Cell cell = null;// 就是单个单元格
		// 开始循环，取得 cell 里的内容，这里都是按String来取的 为了省事，具体你自己可以按实际类型来取。或者都按
		// String来取。然后根据你需要强制转换一下。
		List<SalaryMessage> smList = new ArrayList<SalaryMessage>();
		for (int j = 0; j < sheet.getRows(); j++) {
			StringBuffer sb = new StringBuffer();
			SalaryMessage sm = new SalaryMessage();
			cell = sheet.getCell(0, j);
			String temp = cell.getContents();
			if (temp != null)
				temp = temp.trim();
			if (temp.contains("月份") || temp.contains("姓名")
					|| temp.contains("月工资"))
				continue;
			for (int i = 0; i < sheet.getColumns(); i++) {
				cell = sheet.getCell(i, j);
				String value = null;
				Date date = null;
				value = cell.getContents();
				if (i == 0) {
						if (value.contains("年") && value.contains("月")) {
							date = castDate(value);
						} else if (value.contains("-")) {
							date = castDateSimple(value);
						} else if (value.matches("^\\d+$")) {
							long days = Long.parseLong(value);
							date = getDate(days);
						}
//					System.out.println(i + "date=" + date);
					if(date == null)
						continue;
				} else {
					value = cell.getContents();
//					System.out.println(i + "value=" + value);
				}

				if (date == null)
					sb.append(value);
				else
					sb.append(date);
				sb.append(",\t");// 将单元格的每行内容用逗号隔开

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
				
				switch (i) {
				case 0:
					if(!format.format(date).equals(month)) {
						month = format.format(date);
						countMonth ++;
					}
					sm.setMonth(format.format(date));
					break;
				case 1:
					if (value != null)
						value = value.trim();
					sm.setName(value);
					break;
				case 2:
					sm.setBasesalary(castDouble(value));
					break;
				case 3:
					sm.setBuckshee(castDouble(value));
					break;
				case 4:
					sm.setRentdeduct(castDouble(value));
					break;
				case 5:
					sm.setLeavededuct(castDouble(value));
					break;
				case 6:
					sm.setFactsalary(castDouble(value));
					break;
				case 7:
					sm.setPayyanglaoinsure(castDouble(value));
					break;
				case 8:
					sm.setPayshiyeinsure(castDouble(value));
					break;
				case 9:
					sm.setPayyilaioinsure(castDouble(value));
					break;
				case 10:
					sm.setPayshebaofee(castDouble(value));
					break;
				case 11:
					sm.setPayhousingsurplus(castDouble(value));
					break;
				case 12:
					sm.setTaxbefore(castDouble(value));
					break;
				case 13:
					sm.setTaxget(castDouble(value));
					break;
				case 14:
					sm.setTaxlv(value);
					break;
				case 15:
					sm.setTaxrm(castDouble(value));
					break;
				case 16:
					sm.setTax(castDouble(value));
					break;
				case 17:
					sm.setTaxafter(castDouble(value));
					break;
				case 18:
					sm.setRemark(value);
					break;
				}
			}
			if(sm.getMonth() != null && (sm.getName() != null && sm.getName().trim().length() > 0))
				smList.add(sm);
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
			SalaryMessage sm = null;
			ResultSet rs = null;
			long time = 0;
			
//			System.out.println("isCover=" + isCover);
//			System.out.println("isCover=" + isCover);
//			System.out.println("isCover=" + isCover);
			
			if (isCover == null) {
				boolean notEmpty = false;
				for (int i = 0; i < smList.size(); i++) {
					sm = smList.get(i);
					if (sm != null) {
						time = new java.util.Date().getTime() + i;
						String name = null;
						name = sm.getName();
						if (name != null)
							name = name.trim();
						rs = MySqlOperation.SMfindByNameAndDate(conn, name, sm
								.getMonth());
						while (rs != null && rs.next()) {
							notEmpty = true;
							break;
						}
						if (notEmpty)
							break;
					}
				}

				// 是否有重复的数据，若有，则提示；
				if (notEmpty) {
					BOInstance bi = new BOInstance();
					bi.putValue("notmpty", "notmpty");
					this.setInstance(bi);
					return false;
				}
				// 是否有重复的数据，若无，则继续往下执行
				/**
				 * ***************************
				 * */

			} else if (isCover.trim().equals("cover")) {
				// 全部覆盖
//				System.out.println("全部覆盖");
				//新增或更新工资条历史记录
				GZtiaoLiShi.InsertLiShi(fileName,month);
				///
				for (int i = 0; i < smList.size(); i++) {
					boolean flag = false;
					sm = smList.get(i);
					if (sm != null) {
						time = new java.util.Date().getTime() + i;
						String name = null;
						name = sm.getName();
						if (name != null)
							name = name.trim();

						rs = MySqlOperation.SMfindByNameAndDate(conn, name, sm
								.getMonth());
						while (rs != null && rs.next()) {
							flag = true;
						}
//						System.out.println("该条记录： " + name + "\t"
//								+ sm.getMonth());
						rs.beforeFirst();
						if (flag) {
							while (rs != null && rs.next()) {
//								System.out.println("数据重复：" + name + "\t"
//										+ sm.getMonth());
								MySqlOperation.SMDeleteByNameAndDate(conn,
										name, sm.getMonth());
//								System.out.println("delete: " + name);
							}
							MySqlOperation.insert(conn, sm, time);
						} else {
//							System.out.println("数据不重复：" + name + "\t"
//									+ sm.getMonth());
							MySqlOperation.insert(conn, sm, time);
						}
					}
				}
				conn.commit();
				if (conn != null && !conn.isClosed())
					conn.close();
//				System.out.println("存储成功！cover");
				return true;
			} else if (isCover.trim().equals("nocover")) {
				// 全部不覆盖
				//新增或更新工资条历史记录
				GZtiaoLiShi.InsertLiShi(fileName,month);
				///
				for (int i = 0; i < smList.size(); i++) {
					boolean flag = false;
					sm = smList.get(i);
					if (sm != null) {
						time = new java.util.Date().getTime() + i;
						String name = null;
						name = sm.getName();
						if (name != null)
							name = name.trim();
						rs = MySqlOperation.SMfindByNameAndDate(conn, name, sm
								.getMonth());
						while (rs != null && rs.next()) {
							flag = true;
						}
						if (flag) {
							// 数据重复，跳过， 不做任何操作
//							System.out.println("数据重复：" + name + "\t"
//									+ sm.getMonth());
						} else {
							// 数据不重复，添加

//							System.out.println("数据不重复：" + name + "\t"
//									+ sm.getMonth());
							MySqlOperation.insert(conn, sm, time);
						}
					}
				}
				conn.commit();
				if (conn != null && !conn.isClosed())
					conn.close();
//				System.out.println("存储成功！nocover");
				return true;
			} else {
				// 暂时没有考虑到的情况
				System.out.println("暂时没有考虑到的情况暂时没有考虑到的情况");
				return true;
			}
			// 继续往下执行
			rs.beforeFirst();
			//新增或更新工资条历史记录
			GZtiaoLiShi.InsertLiShi(fileName,month);
			///
			for (int i = 0; i < smList.size(); i++) {
				sm = smList.get(i);
				if (sm != null) {
					time = new java.util.Date().getTime() + i;
					MySqlOperation.insert(conn, sm, time);
					
				}
			}

			//
			conn.commit();
			if (conn != null && !conn.isClosed())
				conn.close();
//			System.out.println("存储成功！noall");
		} catch (SQLException e) {
			System.out.println("存储失败！");
			e.printStackTrace();
			conn.rollback();
		}
		return true;
	}

	private Double castDouble(String value) {
		Double number = 0D;
		if (value != null && value.length() > 0
				&& value.matches("^\\d+.\\d+|\\d+$")) {
			number = Double.parseDouble(value);
		}
		return number;
	}

	private Date castDate(String value) {
		String year = null;
		String month = null;
		Date date = null;
		if (value.contains("\"年\"") && value.contains("\"月\"")) {
			year = value.substring(0, value.indexOf("\"年\""));
			month = value.substring(value.indexOf("\"年\"") + 3, value
					.indexOf("\"月\""));
		} else if (value.contains("年") && value.contains("月")) {
			year = value.substring(0, value.indexOf("年"));
			month = value.substring(value.indexOf("年") + 1, value.indexOf("月"));
		}
		if (year != null && month != null) {
			if(month.trim().length()<2)
				month = "0"+month.trim();
//			System.out.println(year.trim() + "=" + month.trim() + "=");
			date = Date.valueOf(year.trim() + "-" + month.trim() + "-15");
		}
		return date;
	}
	
	private Date castDateSimple(String value) {
		String year = null;
		String month = null;
		String day = null;
		Date date = null;
		year = value.substring(0, value.indexOf("-"));
		month = value.substring(value.indexOf("-") + 1,value.lastIndexOf("-"));
		day = value.substring(value.lastIndexOf("-") + 1);
		if(year != null && year.trim().length() == 2) {
			if(Integer.parseInt(year) > 80) {
				year = "19"+year;
			} else {
				year = "20"+year;
			}
		}
		date = Date.valueOf(year.trim() + "-" + month.trim() + "-" + day.trim());
		return date;
	}

	private Date getDate(long dates) {
		Date date = null;
//		Date date0 = Date.valueOf("1900-01-00");
//		Date date1 = Date.valueOf("1970-01-01");

		// dates是离1900-01-00的天数
		// 计算离1970-01-00的天数
//		System.out.println((date1.getTime() - date0.getTime()) / 3600000 / 24 + "++++++++++++++++????????????????");
		long timebewteen = 25568L;
		
		dates = dates - timebewteen;
		
		// nowTime
		Calendar cal = Calendar.getInstance();
		//
//		Date date2 = Date.valueOf("1970-01-00");
//		long d1 = date2.getTime();
//		System.out.println(d1 + "=1=");
		
		Date date2 = Date.valueOf("1970-01-01");
		long d1 = date2.getTime() - 24*3600*1000L;
//		System.out.println(d1 + "=4=");
		
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

	public static void main(String[] args) {
		ExcelLoad exl = new ExcelLoad();
//		exl.getDate(1234567);
		exl.castDate("2008\"年\"2\"月\"13\"日\"");
	}

}
