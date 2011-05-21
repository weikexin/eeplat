package com.exedosoft.plat.login.zidingyi.excel;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ldap.LDAPManager;
import com.exedosoft.plat.login.zidingyi.Employee;
import com.exedosoft.plat.util.StringUtil;
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
	final static String[] attrNames = { "uid", "sn", "mobile", "mail",
			"telephonenumber", "cn", "userPassword", "employeenumber",
			"mailmessagestore", "mailquotacount", "mailquotasize", "mailhost" };

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
					&& MY_URL.trim().substring(MY_URL.trim().length() - 1)
							.equals("/"))
				MY_URL = MY_URL.trim().substring(
						MY_URL.trim().indexOf("//") + 2,
						MY_URL.trim().length() - 1);
			else if (MY_URL.trim().contains("//")
					&& !MY_URL.trim().substring(MY_URL.trim().length() - 1)
							.equals("/"))
				MY_URL = MY_URL.trim().substring(
						MY_URL.trim().indexOf("//") + 2);
		}
		DODataSource dss2 = DODataSource.parseConfigHelper("/ds_ldap.xml",
				"ds_ldap");

		final String MY_USER = dss2.getUserName();
		final String password = dss2.getPassword();

		String url = dss2.getDriverUrl();
		String sPort1 = null;
		if (MY_URL.substring(MY_URL.length() - 1).equals("/"))
			sPort1 = url.substring(url.indexOf(MY_URL.substring(0,
					MY_URL.lastIndexOf("/")))
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

	// 根据sn取得uid
	public static String getLDAPUidBySN(String user) {
		String uid = null;
		LDAPEntry fullEntry = new LDAPEntry();
		LDAPConnection lc = new LDAPConnection();
		LDAPSearchResults rs = new LDAPSearchResults();
		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "sn=" + user,
					attrNames, false);
			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry.getAttribute("uid");
					if (attribute != null) {
						uid = attribute.getStringValue();
						break;
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
		return uid;
	}

	public static LDAPSearchResults getLDAPRsByuid(String user) {
		String uid = null;
		LDAPEntry fullEntry = new LDAPEntry();
		LDAPConnection lc = new LDAPConnection();
		LDAPSearchResults rs = new LDAPSearchResults();
		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "uid=" + user,
					attrNames, false);

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
		return rs;
	}

	// 根据uid取得sn
	public static String getLDAPSnByUid(String uid) {
		String sn = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();

			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "uid=" + uid,
					attrNames, false);

			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry.getAttribute("sn");
					if (attribute != null) {
						sn = attribute.getStringValue();
						break;
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

	// 根据uid取得cn
	public static String getLDAPCnByUid(String uid) {
		String cn = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();

			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "uid=" + uid,
					attrNames, false);

			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry.getAttribute("cn");
					if (attribute != null) {
						cn = attribute.getStringValue();
						break;
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

	// 根据uid取得employeenumber
	public static String getLDAPEmpnumberByUid(String uid) {
		String employeenumber = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();

			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "uid=" + uid,
					attrNames, false);

			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry
							.getAttribute("employeenumber");
					if (attribute != null) {
						employeenumber = attribute.getStringValue();
						break;
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
					System.err.print("连接异常！   getLDAPEmpnumberByUid ::::"
							+ e.toString());
				}
			}
		}
		return employeenumber;
	}

	// 根据sn取得邮箱地址
	public static String getLDAPEmailBySN(String user) {
		String mail = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();

			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "sn=" + user,
					attrNames, false);

			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry.getAttribute("mail");
					if (attribute != null) {
						mail = attribute.getStringValue();
						break;
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
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "cn=" + user,
					attrNames, false);

			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry.getAttribute("mail");
					if (attribute != null) {
						mail = attribute.getStringValue();
						break;
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
		LDAPSearchResults rs = null;
		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "cn=" + cn,
					attrNames, false);

			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry.getAttribute("sn");
					if (attribute != null) {
						sn = attribute.getStringValue();
						break;
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
		LDAPSearchResults rs = null;

		try {
			lc = getConnection();
			rs = lc.search(ENTRYDN, LDAPConnection.SCOPE_SUB, "sn=" + sn,
					attrNames, false);
			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry.getAttribute("cn");
					if (attribute != null) {
						cn = attribute.getStringValue();
						break;
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

	// 取加密后的密码
	public static String getPwdBySn(String sn) {
		String pwd = null;
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPSearchResults rs = null;

		try {
			lc = LDAPPeopleUtil.getConnection();
			rs = lc.search(LDAPPeopleUtil.ENTRYDN, LDAPConnection.SCOPE_SUB,
					"sn=" + sn, LDAPPeopleUtil.attrNames, false);

			while (rs != null && rs.hasMore()) {
				try {
					fullEntry = rs.next();
					LDAPAttribute attribute = fullEntry
							.getAttribute("userPassword");
					if (attribute != null) {
						pwd = attribute.getStringValue();
						break;
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
		return pwd;
	}

	// 加密
	public static String passwordKey(String str) {
		String ret = "";
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(str.getBytes());
			ret = new sun.misc.BASE64Encoder().encode(sha.digest());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return ret;
	}

	public static void insertOrupdateLogin(String sn) {
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPSearchResults rs = null;
		try {
			lc = LDAPPeopleUtil.getConnection();
			rs = lc.search(LDAPPeopleUtil.ENTRYDN, LDAPConnection.SCOPE_SUB,
					"sn=" + sn, LDAPPeopleUtil.attrNames, false);
			// uid,sn,cn,mobile,telephonenumber,mailmessagestore,mail,mailquotacount,mailquotasize,mailhost,employeenumber
			// sn=?,cn=?,mobile=?,telephonenumber=?,mailmessagestore=?,mail=?,mailquotacount=?,mailquotasize=?,mailhost=?,employeenumber=?
			String uid = "";
			String e_sn = "";
			String e_cn = "";
			String mobile = "";
			String telephonenumber = "";
			String e_mailmessagestore = "";
			String mail = "";
			String mailquotacount = "";
			String mailquotasize = "";
			String mailhost = "";
			String employeenumber = "";
			while (rs != null && rs.hasMore()) {
				uid = "";
				e_sn = "";
				e_cn = "";
				mobile = "";
				telephonenumber = "";
				e_mailmessagestore = "";
				mail = "";
				mailquotacount = "";
				mailquotasize = "";
				mailhost = "";
				employeenumber = "";
				try {
					fullEntry = rs.next();
					LDAPAttribute att_uid = fullEntry.getAttribute("uid");
					if (att_uid != null) {
						uid = att_uid.getStringValue();
					}
					LDAPAttribute att_sn = fullEntry.getAttribute("sn");
					if (att_sn != null) {
						e_sn = att_sn.getStringValue();
					}
					LDAPAttribute att_cn = fullEntry.getAttribute("cn");
					if (att_cn != null) {
						e_cn = att_cn.getStringValue();
					}
					LDAPAttribute att_mobile = fullEntry.getAttribute("mobile");
					if (att_mobile != null) {
						mobile = att_mobile.getStringValue();
					}
					LDAPAttribute att_telephonenumber = fullEntry
							.getAttribute("telephonenumber");
					if (att_telephonenumber != null) {
						telephonenumber = att_telephonenumber.getStringValue();
					}
					LDAPAttribute att_mailmessagestore = fullEntry
							.getAttribute("mailmessagestore");
					if (att_mailmessagestore != null) {
						e_mailmessagestore = att_mailmessagestore
								.getStringValue();
					}
					LDAPAttribute att_mail = fullEntry.getAttribute("mail");
					if (att_mail != null) {
						mail = att_mail.getStringValue();
					}
					LDAPAttribute att_mailquotacount = fullEntry
							.getAttribute("mailquotacount");
					if (att_mailquotacount != null) {
						mailquotacount = att_mailquotacount.getStringValue();
					}
					LDAPAttribute att_mailquotasize = fullEntry
							.getAttribute("mailquotasize");
					if (att_mailquotasize != null) {
						mailquotasize = att_mailquotasize.getStringValue();
					}

					LDAPAttribute att_mailhost = fullEntry
							.getAttribute("mailhost");
					if (att_mailhost != null) {
						mailhost = att_mailhost.getStringValue();
					}
					LDAPAttribute att_employeenumber = fullEntry
							.getAttribute("employeenumber");
					if (att_employeenumber != null) {
						employeenumber = att_employeenumber.getStringValue();
					}

				} catch (LDAPException e) {
					System.out.println("Error:   " + e.toString());
					continue;
				}
				DOService ifhser = DOService.getService("zf_employee_browse");
				List<BOInstance> linkList = ifhser.invokeSelect(uid);
				DOService linkser = null;
				try {
					if (linkList != null && linkList.size() > 0) {

						linkser = DOService
								.getService("zf_employee_update_copy");
						linkser.invokeUpdate(e_sn, e_cn, mobile,
								telephonenumber, e_mailmessagestore, mail,
								mailquotacount, mailquotasize, mailhost,
								employeenumber, uid);
					} else {

						linkser = DOService
								.getService("zf_employee_insert_copy");
						linkser.invokeUpdate(uid, e_sn, e_cn, mobile,
								telephonenumber, e_mailmessagestore, mail,
								mailquotacount, mailquotasize, mailhost,
								employeenumber);
					}

				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	}

	public static void insertOrupdateZf_employee() {
		LDAPEntry fullEntry = null;
		LDAPConnection lc = null;
		LDAPSearchResults rs = null;
		try {
			lc = LDAPPeopleUtil.getConnection();
			rs = lc.search(LDAPPeopleUtil.ENTRYDN, LDAPConnection.SCOPE_SUB,
					"sn=*", LDAPPeopleUtil.attrNames, false);
			// uid,sn,cn,mobile,telephonenumber,mailmessagestore,mail,mailquotacount,mailquotasize,mailhost,employeenumber
			// sn=?,cn=?,mobile=?,telephonenumber=?,mailmessagestore=?,mail=?,mailquotacount=?,mailquotasize=?,mailhost=?,employeenumber=?
			String uid = "";
			String e_sn = "";
			String e_cn = "";
			String mobile = "";
			String telephonenumber = "";
			String e_mailmessagestore = "";
			String mail = "";
			String mailquotacount = "";
			String mailquotasize = "";
			String mailhost = "";
			String employeenumber = "";
			String userpassword = "";
			while (rs != null && rs.hasMore()) {
				uid = "";
				e_sn = "";
				e_cn = "";
				mobile = "";
				telephonenumber = "";
				e_mailmessagestore = "";
				mail = "";
				mailquotacount = "";
				mailquotasize = "";
				mailhost = "";
				employeenumber = "";
				userpassword = "";
				try {
					fullEntry = rs.next();
					LDAPAttribute att_uid = fullEntry.getAttribute("uid");
					if (att_uid != null) {
						uid = att_uid.getStringValue();
					}
					LDAPAttribute att_sn = fullEntry.getAttribute("sn");
					if (att_sn != null) {
						e_sn = att_sn.getStringValue();
					}
					LDAPAttribute att_cn = fullEntry.getAttribute("cn");
					if (att_cn != null) {
						e_cn = att_cn.getStringValue();
					}
					LDAPAttribute att_mobile = fullEntry.getAttribute("mobile");
					if (att_mobile != null) {
						mobile = att_mobile.getStringValue();
					}
					LDAPAttribute att_telephonenumber = fullEntry
							.getAttribute("telephonenumber");
					if (att_telephonenumber != null) {
						telephonenumber = att_telephonenumber.getStringValue();
					}
					LDAPAttribute att_mailmessagestore = fullEntry
							.getAttribute("mailmessagestore");
					if (att_mailmessagestore != null) {
						e_mailmessagestore = att_mailmessagestore
								.getStringValue();
					}
					LDAPAttribute att_mail = fullEntry.getAttribute("mail");
					if (att_mail != null) {
						mail = att_mail.getStringValue();
					}
					LDAPAttribute att_mailquotacount = fullEntry
							.getAttribute("mailquotacount");
					if (att_mailquotacount != null) {
						mailquotacount = att_mailquotacount.getStringValue();
					}
					LDAPAttribute att_mailquotasize = fullEntry
							.getAttribute("mailquotasize");
					if (att_mailquotasize != null) {
						mailquotasize = att_mailquotasize.getStringValue();
					}

					LDAPAttribute att_mailhost = fullEntry
							.getAttribute("mailhost");
					if (att_mailhost != null) {
						mailhost = att_mailhost.getStringValue();
					}
					LDAPAttribute att_employeenumber = fullEntry
							.getAttribute("employeenumber");
					if (att_employeenumber != null) {
						employeenumber = att_employeenumber.getStringValue();
					}

					LDAPAttribute att_userpassword = fullEntry
							.getAttribute("userpassword");
					if (att_userpassword != null) {
						userpassword = att_userpassword.getStringValue();
					}

				} catch (LDAPException e) {
					System.out.println("Error:   " + e.toString());
					continue;
				}
				DOService ifhser = DOService.getService("zf_employee_browse");
				List<BOInstance> linkList = ifhser.invokeSelect(uid);
				DOService linkser = null;
				try {
					if (linkList != null && linkList.size() > 0) {

						linkser = DOService
								.getService("zf_employee_update_copy");
						linkser.invokeUpdate(e_sn, e_cn, mobile,
								telephonenumber, e_mailmessagestore, mail,
								mailquotacount, mailquotasize, mailhost,
								employeenumber, userpassword,uid);
					} else {

						linkser = DOService
								.getService("zf_employee_insert_copy");
						linkser.invokeUpdate(uid, e_sn, e_cn, mobile,
								telephonenumber, e_mailmessagestore, mail,
								mailquotacount, mailquotasize, mailhost,
								employeenumber,userpassword);
					}

				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LDAPPeopleUtil.insertOrupdateZf_employee();

	}

}
