package com.exedosoft.plat.login.zidingyi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;

public class SendGZmessage extends DOAbstractAction {

	

	public String excute() {
		
		// 以管理员身份发送邮箱
		String manager_email = "uii2008@sohu.com";// 管理员邮箱地址;
		String emailTo = null;// 收信人地址 ;
		StringBuffer noEmail = new StringBuffer();// 没有邮箱的员工;
		String name = null; // 实际员工姓名;
		String emailName = null; // 代收人姓名;
		String eamilSelf = null;//员工自定义邮箱;
		int countAll = 0;
		int countSend = 0;
		int countFail = 0;
		// 接收的参数
		Date resmonth = null;
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
					Date month = Date.valueOf(nv[1]);
					resmonth = month;
				}
				if (nv.length == 2 && "name".equals(nv[0].trim())) {
					resname = nv[1];
				}

			}
			
			System.out.println("resmonth===================="
					+ resmonth);
			System.out.println("resname===================="
					+ resname);

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
						sm.setMonth(rs.getDate("month"));
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
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy年MM月");
						String stMonth = format.format(sm.getMonth());
						
						String title = sm.getName()+ " " + stMonth + " 的工资信息";
						// 邮件内容
						
						content.append("月份:\t\t\t" + stMonth + "\n姓名:\t\t\t"
								+ sm.getName() + "\n");
						content.append("月工资:\t\t" + sm.getBasesalary()
								+ "\n其他:\t\t\t" + sm.getBuckshee() + "\n");
						content.append("租房扣减:\t\t" + sm.getRentdeduct()
								+ "\n病（事）假扣减:\t" + sm.getLeavededuct() + "\n");
						content.append("应发额:\t\t" + sm.getFactsalary()
								+ "\n代缴个人养老保险:\t" + sm.getPayyanglaoinsure()
								+ "\n");
						content.append("代缴个人失业保险:\t" + sm.getPayshiyeinsure()
								+ "\n代缴个人医疗保险:\t" + sm.getPayyilaioinsure()
								+ "\n");
						content.append("个人应缴社保小计:\t" + sm.getPayshebaofee()
								+ "\n代缴个人住房公积金:\t" + sm.getPayhousingsurplus()
								+ "\n");
						content.append("税前应发:\t\t" + sm.getTaxbefore()
								+ "\n应税所得G=F-2000:\t" + sm.getTaxget() + "\n");
						
						content.append("税率H:\t\t" + sm.getTaxlv()
								+ "\n速算扣除\t\t" + sm.getTaxrm()
								+ "\n税:\t\t\t" + sm.getTax() + "\n");
						
						
						content.append("税后实发:\t\t" + sm.getTaxafter()
								+ "\n备注:\t\t\t" + sm.getRemark() + "\n");

						content
								.append("\n\t查询所有工作信息(请链接一下地址)：\nhttp://127.0.0.1:8080/yiyi/allsm?uid="
										+ sm.getObjuid());
						String contentText = content.toString();
						System.out.println(contentText);
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
									emailTo = LDAPPeopleUtil
											.getLDAPEmailBySN(emailName);
								} else {
									//使用本人邮箱地址
									emailTo = LDAPPeopleUtil.getLDAPEmailByCN(name);
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
								String password = "yyfxyxx2008";
								System.out.println("$$$$$发送邮件参数信息查看$$$$$$");
								System.out.println("发送邮件======================"
										+ manager_email);
								System.out.println("实际接收人===================="
										+ name);
								System.out.println("代收接收人===================="
										+ emailName);
								System.out.println("接收邮箱======================"
										+ emailTo);
								System.out.println("实际接收邮箱=================="
										+ "yuanxx@zephyr.com.cn");
								System.out
										.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
								countSend++;
								sendEmail(manager_email, password,
										"yuanxx@zephyr.com.cn", title,
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
	public static void sendEmail(String from, String password, String to,
			String title, String text) throws AddressException,
			MessagingException {

		// **************************************************8
		// 测试用
		//to = "yuanxx@zephyr.com.cn";
		// *****************************************************8

		String smtpHost = "smtp." + from.substring(from.lastIndexOf("@") + 1);

		// System.out.println("$$$$$$$$$$LoginActionLDAP()$$$$$$$$$$$$" + from +
		// "===" + password + "$$$$$$$$$$$$$$$$$$$$$$$4");
		// System.out.println("$$$$$$$$$$$$$$$发送邮件信息查看$$$$$$$$$$$$$$4");
		// System.out.println("发送人邮箱地址==========================" + from);
		// System.out.println("接收人邮箱地址==========================" + to);
		// System.out.println("简单邮件传送协议服务器==========================" +
		// smtpHost);
		// System.out.println("邮件主题==========================" + title);
		// System.out.println("邮件内容==========================" + text);
		// System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");

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
		msg.setText(text);
		System.out.println("1.Please wait for sending two...");

		// 发送邮件
		Transport myTransport = myMailSession.getTransport("smtp");
		myTransport.connect(smtpHost, from, password);
		myTransport.sendMessage(msg, msg
				.getRecipients(Message.RecipientType.TO));
		myTransport.close();
		// javax.mail.Transport.send(msg); // 这行不能使用。
		System.out.println("2.Your message had send!");
	}

	public static Double castDouble(String value) {
		Double number = 0.00;
		if (value != null && value.trim().length() > 0
				&& value.matches("^\\d+.\\d+|\\d+$")) {
			number = Double.parseDouble(value);
		}
		System.out.println(number);
		return number;
	}

	public static void main(String[] args) {
		SendGZmessage sg = new SendGZmessage();
		try {
			sg.sendEmail("uii2008@sohu.com", "yyfxyxx2008", "yuanxxasdfasdf@zephyr.com.cn", "test", "testast");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
