package com.exedosoft.plat.multitenancy;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

public class RegisterMail {

	public static void sendRegisterMail(String tenantId,String emailTo) {

		/**
		 * 设置邮件主题和内容
		 */
		// 提交、审批的报销单
		String emailTitle = "EEPlat PaaS应用平台邮箱认证";// 邮件主题;
		StringBuffer emailText = new StringBuffer("尊敬的EEPlat PaaS应用平台用户您好！<br/>\n")
				.append("感谢您的支持，请您对邮箱进行认证。请点击以下链接进行确认：<br/>\n")
				.append(
						"<a  target='_opener' href='http://www.eeplat.com/multimanager/reg_active.jsp?tenantId=")

				.append(tenantId)
				.append(
						"'>http://www.eeplat.com/multimanager/reg_active.jsp?tenantId=")
				.append(tenantId).append("</a>").append(
						"<br/>如果您无法点击打开这个链接，您也可以复制这个网址到浏览器地址栏打开。");

		// //根据报销单状态，发送邮件；
		try {

			sendEmail(emailTo, emailTitle, emailText.toString());

		} catch (Exception error) {
			System.out
					.println("*.I am sorry to tell you the fail for " + error);
		}
	}

	// //////////////////////////////////////////////////////////////
	// 发送邮件
	public static void sendEmail(String to, String title, String text)
			throws AddressException, MessagingException, IOException {

		// **************************************************8
		// 测试用
		//to = "yaoyx@bst.org.cn";
		// *****************************************************8

		String smtpHost = "smtp.ym.163.com";
		String from = "support@eeplat.com";
		String password = "123567890";

		final Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");

		Session myMailSession = Session.getInstance(props);
		myMailSession.setDebug(true); // 打开DEBUG模式
		Message msg = new MimeMessage(myMailSession);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setContent("I have a email!", "text/html");
		msg.setSentDate(new java.util.Date());
		msg.setSubject(title);
		//msg.setText(text);
		
		msg.setDataHandler(new   DataHandler(
				new   ByteArrayDataSource(text,   "text/html "))); 
		System.out.println("1.Please wait for sending to...");

		// 发送邮件
		Transport myTransport = myMailSession.getTransport("smtp");
		myTransport.connect(smtpHost, from, password);
		myTransport.sendMessage(msg, msg
				.getRecipients(Message.RecipientType.TO));
		myTransport.close();
		// javax.mail.Transport.send(msg); // 这行不能使用。
		System.out.println("2.Your message had send!");

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stu

		sendRegisterMail("Hello kitty","toweikexin@126.com");

	}

}
