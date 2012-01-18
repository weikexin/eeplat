package com.exedosoft.plat.login.zidingyi.excel;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import com.exedosoft.plat.bo.DODataSource;
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

public class LDAPPeopleUtil {

	/**
	 * @param args
	 */

	private static String ENTRYDN = null;
	final static String[] attrNames = { "sn", "mobile", "mail", "cn" };

	private static LDAPConnection getConnection() {
		DODataSource dss1 = DODataSource.parseConfigHelper("/ds_ldap_url.xml",
				"ds_ldap_url");
		String dn = dss1.getUserName();
		if (dn != null && dn.trim().length() > 0)
			if (dn.trim().substring(0, 1).matches("^\\W$"))
				ENTRYDN = dn.trim().substring(1);
			else
				ENTRYDN = dn.trim();
		String MY_URL = dss1.getDriverUrl();
		if (MY_URL != null && MY_URL.trim().length() > 0) {
			if (MY_URL.trim().contains("//")
					&& MY_URL.trim().substring(MY_URL.trim().length() - 1).equals("/"))
				MY_URL = MY_URL.trim().substring(MY_URL.trim().indexOf("//")+2,
						MY_URL.trim().length() - 1);
			else if (MY_URL.trim().contains("//")
					&& !MY_URL.trim().substring(MY_URL.trim().length() - 1).equals("/"))
				MY_URL = MY_URL.trim().substring(MY_URL.trim().indexOf("//")+2);
		}
		DODataSource dss2 = DODataSource.parseConfigHelper("/ds_ldap.xml",
				"ds_ldap");

		final String MY_USER = dss2.getUserName();
		final String password = dss2.getPassword();

		String url = dss2.getDriverUrl();
		String sPort1 = null;
		if (MY_URL.substring(MY_URL.length() - 1).equals("/"))
			sPort1 = url.substring(url.indexOf(MY_URL.substring(0, MY_URL
					.lastIndexOf("/")))
					+ MY_URL.length());
		else
			sPort1 = url.substring(url.indexOf(MY_URL.substring(0))
					+ MY_URL.length() + 1);
		String sPort2 = sPort1.substring(0, sPort1.indexOf("/"));
		int MY_PORT = 389;
		if (sPort2.matches("^\\d+$"))
			MY_PORT = Integer.parseInt(sPort2);
		LDAPConnection lc = null;
		try {
			lc = new LDAPConnection();
			lc.connect(MY_URL, MY_PORT);
			lc.bind(3, MY_USER, password.getBytes("UTF8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (LDAPException e) {
			e.printStackTrace();
		}

		return lc;

	}

	// 根据sn取得邮箱地址
	public static String getLDAPEmailBySN(String user) {
		String mail = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPAttributeSet set = null;
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();

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
						if (name != null && "mail".equals(name.trim())) {
							mail = value.trim();
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

	// 根据cn取得邮箱地址
	public static String getLDAPEmailByCN(String user) {
		String mail = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPAttributeSet set = null;
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "cn=" + user,
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
						if (name != null && "mail".equals(name.trim())) {
							mail = value.trim();
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

	// 根据中文姓名(cn)取得sn
	public static String getLDAPSNByCN(String cn) {
		String sn = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPAttributeSet set = null;
		LDAPSearchResults rs = null;
		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "cn=" + cn,
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
						if (name != null && "sn".equals(name.trim())) {
							sn = value.trim();
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
		return sn;
	}

	// 根据sn取得cn
	public static String getLDAPCNBySN(String sn) {
		String cn = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPAttributeSet set = null;
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "sn=" + sn,
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
						if (name != null && "cn".equals(name.trim())) {
							cn = value.trim();
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
		return cn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sn = LDAPPeopleUtil.getLDAPSNByCN("袁谐");
		String cn = LDAPPeopleUtil.getLDAPCNBySN("yuanxx");
		System.out.println(sn);
		System.out.println(cn);
	}

}
