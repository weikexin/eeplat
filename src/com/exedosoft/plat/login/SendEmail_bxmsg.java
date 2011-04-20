package com.exedosoft.plat.login;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.exedosoft.plat.login.zidingyi.excel.MySqlOperationII;
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

/**
 * 
 * this page must be the first page of huidian system. the classify default
 * config must initiazation.
 * 
 * @author aa
 * 
 */
public class SendEmail_bxmsg extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public String excute() {
		String baoxiaoempuid = null;// 报销人;
		String baoxiaoid = null;// 报销单编号;
		String baoxiaostate = null;// 报销单状态;

		String receivepeople = null;// 报销人部门领导(经理)或下一个审批人;
		
		//发送邮件，以管理员身份发送
		String manager_email = "uii2008@sohu.com";// 管理员邮箱地址;
		String baoxiaoemail = null;// 报销人地址;
		 String emailTo = null;// 收信人地址 ;

		List users = new ArrayList();
		
		try {
			users = service.invokeSelect();
		} catch (Exception e) {
			return this.DEFAULT_FORWARD;
		}

		// 判断是否有数据接受；
		if (users != null && users.size() > 0) {
			 // 历遍所有的数据；
			
			 for (int n = 0; n < users.size(); n++) {
			 String s = users.get(n).toString();
			 String st = s.substring(s.indexOf("{") + 1, s.lastIndexOf("}"));
			 String[] sarray = st.split(",");
							
			 
			
			
			 // 对每条数据进行处理，取得有效属性；
			 for (int i = 0; i < sarray.length; i++) {
			 String temp = sarray[i];
			 String[] nv = temp.split("=");
			
			 if (nv.length == 2 && "baoxiaoempuid".equals(nv[0].trim()))
			 baoxiaoempuid = nv[1];
			 if (nv.length == 2 && "baoxiaoid".equals(nv[0].trim()))
			 baoxiaoid = nv[1];
			 if (nv.length == 2 && "receivepeople".equals(nv[0].trim()))
			 receivepeople = nv[1];
			 if (nv.length == 2 && "baoxiaostate".equals(nv[0].trim()))
			 baoxiaostate = nv[1];			
			 }
			 
			 /**
			  * // 取得邮箱地址;
			  */
			 
			// 报销人邮箱地址
			baoxiaoemail = getEmail(baoxiaoempuid);
			// 接收人邮箱地址
			emailTo = getEmail(receivepeople);
			
			//LDAP sn 取得cn	
//			String baoxiaoemp =  LDAPPeopleUtil.getLDAPCNBySN(baoxiaoempuid);
			
			//do_org_user_link user_uid 取得user_cn		
			String baoxiaoemp = null;
			try {
				Connection conii = MySqlOperationII.getConnection();
				baoxiaoemp =  MySqlOperationII.getUserCNByUserUid(conii, baoxiaoempuid);
				conii.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/**
			 * 设置邮件主题和内容
			 */
			// 提交、审批的报销单
			String emailTitle = "您有可审批报销单";// 邮件主题;
			String emailText = "报销单编号: " + baoxiaoid + "。\n报销人: "
					+ baoxiaoemp + "。\n报销单状态： " + baoxiaostate + "。\n可以审批。";// 邮件内容;

			// 退回的报销单
			String emailTitle_back = "您的报销单被退回";// 邮件主题;
			String emailText_back = "报销单编号: " + baoxiaoid + "。\n报销人: "
					+ baoxiaoemp + "。\n报销单状态： " + baoxiaostate + "。";// 邮件内容;

			// 审批通过的报销单
			String emailTitle_success = "您的报销单通过了审批";// 邮件主题;
			String emailText_success = "报销单编号: " + baoxiaoid + "。\n报销人: "
					+ baoxiaoemp + "。\n报销单状态： " + baoxiaostate + "。";// 邮件内容;
			//链接网址
			String webaddress = "\n\n\t登录紫枫报销系统：\nhttp://127.0.0.1:8080/yiyi/zfbx_manager";
			

			// //根据报销单状态，发送邮件；
			try {
				if (baoxiaostate.contains("退回")) {
					sendEmail(manager_email, baoxiaoemail, emailTitle_back,
							emailText_back+webaddress);
				} else if (baoxiaostate.contains("总经理审批通过")) {
					sendEmail(manager_email, baoxiaoemail, emailTitle_success,
							emailText_success+webaddress);
				} else if (baoxiaostate != null) {
					sendEmail(manager_email, emailTo, emailTitle, emailText+webaddress);
				} else {
					return this.DEFAULT_FORWARD;
				}
			} catch (Exception error) {
				System.out.println("*.I am sorry to tell you the fail for "
						+ error);
				return this.DEFAULT_FORWARD;
			}

			 }

			 return this.DEFAULT_FORWARD;

		} else {
			return this.DEFAULT_FORWARD;
		}
	}
	////////////////////////////////////////////////////////////////
	// 发送邮件
	private void sendEmail(String from, String to, String title, String text)
			throws AddressException, MessagingException {
		
		//**************************************************8
		//测试用
		to = "yuanxx@zephyr.com.cn";
		//*****************************************************8
		
		String smtpHost = "smtp." + from.substring(from.lastIndexOf("@")+1);
		String password = "yyfxyxx2008";
		
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
		   myTransport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
		   myTransport.close();
		   // javax.mail.Transport.send(msg); // 这行不能使用。
		   System.out.println("2.Your message had send!");
		

	}

	// 根据用户名取得邮箱地址
	private String getEmail(String user) {
		String mail = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPAttributeSet set = null;
		LDAPSearchResults rs = null;
		final String MY_HOST = "localhost";
		final int MY_PORT = 389;
		final String ENTRYDN = "o=zephyr.com.cn";
		final String[] attrNames = { "sn", "mobile", "mail" };

		try {
			lc = new LDAPConnection();
			lc.connect(MY_HOST, MY_PORT);
			String password = "secret";
			try {
				lc.bind(3, "cn=Manager,o=zephyr.com.cn", password
						.getBytes("UTF8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "sn=" + user,
					attrNames, false);

			while (rs.hasMore()) {
				try {
					fullEntry = rs.next();
					set = fullEntry.getAttributeSet();
					Iterator<?> attrs = set.iterator();
					while (attrs.hasNext()) {
						LDAPAttribute attribute = (LDAPAttribute) attrs.next();
						String name = attribute.getName();
						String value = attribute.getStringValue();
						if ("mail".equals(name)) {
							mail = value;
						}
					}
				} catch (LDAPException e) {
					System.out.println("Error:   " + e.toString());
					continue;
				}
			}
		} catch (LDAPException e) {
			System.err.print("连接异常！   ");
			e.printStackTrace();
		} finally {
			if (lc != null && lc.isConnected()) {
				try {
					lc.disconnect();
				} catch (LDAPException e) {
					System.err.print("连接异常！   1" + e.toString());
				}
			}
		}
		return mail;
	}

	public static void main(String[] args) {
		
		
	}
}
