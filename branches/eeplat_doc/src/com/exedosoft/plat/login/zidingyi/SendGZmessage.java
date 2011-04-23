package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;
import com.lowagie.text.Table;

public class SendGZmessage extends DOAbstractAction {

	

	public String excute() {
		
		// 以管理员身份发送邮箱
		String manager_email = "yxxts_zep@163.com";// 管理员邮箱地址;
		String emailTo = null;// 收信人地址 ;
		StringBuffer noEmail = new StringBuffer();// 没有邮箱的员工;
		String name = null; // 实际员工姓名;
		String emailName = null; // 代收人姓名;
		String eamilSelf = null;//员工自定义邮箱;
		int countAll = 0;
		int countSend = 0;
		int countFail = 0;
		// 接收的参数
		String resmonth = null;
		String resname = null;
		
		Connection conn = MySqlOperation.getConnection();
		List users = new ArrayList();

		try {
			users = service.invokeSelect();
			// String user = service.invokeSelectGetAValue();
			// users.add(user);

		} catch (Exception e) {
			this.setEchoValue("接收工资条信息失败！error" + e.toString());
			return "notpass";
		}

		// 历遍所有的数据；
		if (users != null && users.size() > 0) {
			String s = users.get(0).toString();

			String st = s.substring(s.indexOf("{") + 1, s.lastIndexOf("}"));
			String[] sarray = st.split(",");
			ResultSet rs = null;

			// 对每条数据进行处理，取得有效属性；
			for (int i = 0; i < sarray.length; i++) {
				String temp = sarray[i];
				String[] nv = temp.split("=");
				if (nv.length == 2 && "month".equals(nv[0].trim())) {
					resmonth = nv[1];
				}
				if (nv.length == 2 && "name".equals(nv[0].trim())) {
					resname = nv[1];
				}

			}
			
//			System.out.println("resmonth===================="
//					+ resmonth);
//			System.out.println("resname===================="
//					+ resname);

			// 查询结果
			try {
				if (resmonth != null && (resname == null || resname.length() <= 0)) {
					rs = MySqlOperation.SMfindByDate(conn, resmonth);
				} else if (resmonth == null && resname != null
						&& resname.length() > 0) {
					rs = MySqlOperation.SMfindByName(conn, resname);
				} else if (resmonth != null && resname != null
						&& resname.length() > 0) {
					rs = MySqlOperation.SMfindByNameAndDate(conn, resname,
							resmonth);
				} else if (resmonth == null
						&& (resname == null || resname.length() <= 0)) {
					this.setEchoValue("请选择条件。");
					return "notpass";
				}

				// 取出每条记录信息
				if (rs != null) {
					// month, name, basesalary, buckshee, rentdeduct,
					// leavededuct, 6
					// factsalary, payyanglaoinsure, payshiyeinsure,
					// payyilaioinsure, 4
					// payshebaofee, payhousingsurplus, taxbefore, tax,
					// taxafter, remark 6

					
					while (rs.next()) {

						SalaryMessage sm = new SalaryMessage();
						sm.setObjuid(rs.getString("objuid"));
						sm.setMonth(rs.getString("month"));
						sm.setName(rs.getString("name"));
						sm.setBasesalary(rs.getDouble("basesalary"));
						sm.setBuckshee(rs.getDouble("buckshee"));
						sm.setRentdeduct(rs.getDouble("rentdeduct"));
						sm.setLeavededuct(rs.getDouble("leavededuct"));
						sm.setFactsalary(rs.getDouble("factsalary"));
						sm.setPayyanglaoinsure(rs
								.getDouble("payyanglaoinsure"));
						sm.setPayshiyeinsure(rs.getDouble("payshiyeinsure"));
						sm.setPayyilaioinsure(rs.getDouble("payyilaioinsure"));
						sm.setPayshebaofee(rs.getDouble("payshebaofee"));
						sm.setPayhousingsurplus(rs
								.getDouble("payhousingsurplus"));
						sm.setTaxbefore(rs.getDouble("taxbefore"));
						
						sm.setTaxget(rs.getDouble("taxget"));
						sm.setTaxlv(rs.getString("taxlv"));
						sm.setTaxrm(rs.getDouble("taxrm"));

						sm.setTax(rs.getDouble("tax"));
						sm.setTaxafter(rs.getDouble("taxafter"));
						sm.setRemark(rs.getString("remark"));

						/**
						 * 取得发送内容
						 */
						
						
						// 邮件主题
						StringBuffer content = new StringBuffer();
						String stMonth = sm.getMonth() + " 月份";
						
						String title = sm.getName()+ " " + stMonth + " 的工资信息";
						// 邮件内容
						content.append("<center><h3>" + title + "</h3></center>");
						content.append("<table align='center' border='0' bordercolor='gray' cellspacing='5' cellpadding='5'  bgcolor='oldlace'>");
						content.append("<tr align='center' bgcolor='wheat'>");
						content.append("<th>姓名</th><th>月工资</th><th>其他</th><th>租房扣减</th><th>病(事)假扣减</th><th>应发额</th></tr>");
						content.append("<tr  align='center' bgcolor='floralwhite'>");
						content.append("<td>"+ sm.getName() +"</td><td>" + sm.getBasesalary() +"</td>");
						content.append("<td>"+ sm.getBuckshee() +"</td><td>" + sm.getRentdeduct() +"</td>");
						content.append("<td>"+ sm.getLeavededuct() +"</td><td>" + sm.getFactsalary() +"</td>");
						content.append("</tr><tr align='center' bgcolor='wheat'>");
						content.append("<th>代缴个人养老保险</th><th>代缴个人失业保险</th><th>代缴个人医疗保险</th><th>个人应缴社保小计</th>" +
								"<th>代缴个人住房公积金</th><th>税前应发</th></tr><tr  align='center' bgcolor='floralwhite'>");
						content.append("<td>"+ sm.getPayyanglaoinsure() +"</td>");
						content.append("<td>"+ sm.getPayshiyeinsure() +"</td><td>" + sm.getPayyilaioinsure() +"</td>");
						content.append("<td>"+ sm.getPayshebaofee() +"</td><td>" + sm.getPayhousingsurplus() +"</td>");
						content.append("<td>"+ sm.getTaxbefore() +"</td>");
						
						content.append("</tr><tr align='center' bgcolor='wheat'>");
						content.append("<th>应税所得G=F-2000</th><th>税率H</th><th>速算扣除</th><th>税</th>" +
								"<th>税后实发</th><th>备注</th></tr><tr  align='center' bgcolor='floralwhite'>");
						content.append("<td>"+ sm.getTaxget() +"</td>");
						content.append("<td>"+ sm.getTaxlv() +"</td><td>" + sm.getTaxrm() +"</td>");
						content.append("<td>"+ sm.getTax() +"</td><td>" + sm.getTaxafter() +"</td>");
						content.append("<td>"+ sm.getRemark() +"</td>");
						content.append("</tr></table>");
						

						content.append("<br><a href='http://192.168.0.3:8880/yiyi/allsm?uid="+ sm.getObjuid()
										+"'><h4 align='center'>查看所有工资信息</h4></a>");
						String contentText = content.toString();
//						System.out.println(contentText);
						
						
						/**
						 * // 取得邮箱地址;
						 */

						// 取得代收人姓名，再取其邮箱地址
						name = sm.getName();
						
						try {
							eamilSelf = MySqlOperation.findEmailByName(conn, name);
							
							//使用自定义邮箱
							if (eamilSelf != null && eamilSelf.length() > 0) {
								emailTo = eamilSelf.trim();
							} else {//使用代收人邮箱地址
								
								emailName = MySqlOperation.findTonameByName(conn, name);
								if (emailName != null && emailName.length() > 0) {
									DOService empser = DOService.getService("zf_employee_browse_by_sn");
									List<BOInstance> emp = new ArrayList<BOInstance>();
									emp = empser.invokeSelect(emailName); 
									if(emp.size()>0) {
										BOInstance empBi = emp.get(0);
										emailTo = empBi.getValue("mail");
									}
								} else {
									//使用本人邮箱地址
									DOService empser = DOService.getService("zf_employee_browse_by_cn");
									List<BOInstance> emp = new ArrayList<BOInstance>();
									emp = empser.invokeSelect(name); 
									if(emp.size()>0) {
										BOInstance empBi = emp.get(0);
										emailTo = empBi.getValue("mail");
									}
								}
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// month, name, basesalary, buckshee, rentdeduct,
						// leavededuct, 6
						// factsalary, payyanglaoinsure, payshiyeinsure,
						// payyilaioinsure, 4
						// payshebaofee, payhousingsurplus, taxbefore, tax,
						// taxafter, remark 6
						// /发送邮件
						countAll++;
						if (emailTo == null || emailTo.trim().length() <= 0) {
							countFail++;
							String tsname = "";
							String addname = "[" + name + "]";
							if(noEmail != null)
								tsname = noEmail.toString();
							if(tsname.contains(addname)){
								//已存在，则不加人
							}else if(noEmail == null || noEmail.length() <= 0)
								noEmail.append("员工[" + name + "], ");
							 else
								noEmail.append("[" + name + "],");
						} else {
							try {
								String password = "1234567890";
//								System.out.println("$$$$$发送邮件参数信息查看$$$$$$");
//								System.out.println("发送邮件======================"
//										+ manager_email);
//								System.out.println("实际接收人===================="
//										+ name);
//								System.out.println("代收接收人===================="
//										+ emailName);
//								System.out.println("接收邮箱======================"
//										+ emailTo);
//								System.out.println("实际接收邮箱=================="
//										+ "yuanxx@zephyr.com.cn");
//								System.out
//										.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
								countSend++;
								sendEmail(manager_email, password,
										emailTo, title,
										contentText);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			this.setEchoValue("发送邮件失败");
			return "notpass";
		}

		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (noEmail != null && noEmail.length() > 0) {
			noEmail.append("没有对应的邮箱地址，请与管理员联系。");
			if(countSend > 0)
				this.setEchoValue("发送邮件成功，共发送" + countSend + "封邮件！\n" + "余下" + countFail + "个" + noEmail);
			else
				this.setEchoValue("发送邮件失败！\n" + "共" + countFail + "个" + noEmail);
			
			return "notpass";
		} else {
			this.setEchoValue("发送邮件成功，共发送" + countSend + "封邮件！\n");
			return "notpass";
		}
	}

	// 发送邮件
	private void sendEmail(String from, String password, String to,
			String title, String text) throws AddressException,
			MessagingException {

		// **************************************************8
//		// 测试用
//		if(!"yuanxx@zephyr.com".equals(to))
//			to = "yuanxx@zephyr.com.cn";
//		// *****************************************************8

		String smtpHost = "smtp." + from.substring(from.lastIndexOf("@") + 1);

//		 System.out.println("$$$$$$$$$$LoginActionLDAP()$$$$$$$$$$$$" + from +
//		 "===" + password + "$$$$$$$$$$$$$$$$$$$$$$$4");
//		 System.out.println("$$$$$$$$$$$$$$$发送邮件信息查看$$$$$$$$$$$$$$4");
//		 System.out.println("发送人邮箱地址==========================" + from);
//		 System.out.println("接收人邮箱地址==========================" + to);
//		 System.out.println("简单邮件传送协议服务器==========================" +
//		 smtpHost);
//		 System.out.println("邮件主题==========================" + title);
//		 System.out.println("邮件内容==========================" + text);
//		 System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");

		final Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");

		Session myMailSession = Session.getInstance(props);
		myMailSession.setDebug(true); // 打开DEBUG模式
		Message msg = new MimeMessage(myMailSession);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setContent("I have a email!", "text/plain");
		msg.setSentDate(new java.util.Date());
		msg.setSubject(title);
		Multipart mp = new MimeMultipart("related");// related意味着可以发送html格式的邮件 
		BodyPart bodyPart = new MimeBodyPart();// 正文
		bodyPart.setDataHandler(new DataHandler(text, "text/html;charset=UTF-8"));// 网页格式 
		mp.addBodyPart(bodyPart); 
		msg.setContent(mp);
//		System.out.println("1.Please wait for sending two...");

		// 发送邮件
		Transport myTransport = myMailSession.getTransport("smtp");
		myTransport.connect(smtpHost, from, password);
		myTransport.sendMessage(msg, msg
				.getRecipients(Message.RecipientType.TO));
		myTransport.close();
		// javax.mail.Transport.send(msg); // 这行不能使用。
//		System.out.println("2.Your message had send!");
	}

	public static Double castDouble(String value) {
		Double number = 0.00;
		if (value != null && value.trim().length() > 0
				&& value.matches("^\\d+.\\d+|\\d+$")) {
			number = Double.parseDouble(value);
		}
//		System.out.println(number);
		return number;
	}

	public static void main(String[] args) {
	}

}
