package com.exedosoft.plat.login.zidingyi;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.search.customize.SearchTransCode;
import com.exedosoft.plat.util.DOGlobals;
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

public class EmpInsert extends DOAbstractAction {

	public String excute() {
		String sn = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("sn");
		String userpassword = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("userpassword");
		String mailmessagestore = "/home/vmail/zephyr.com.cn/"+sn;
		
		String SHApwd = null;
        DOBO  theBO = DOBO.getDOBOByName("zf_employee");
        BOInstance bi = theBO.getCorrInstance();
        String uid = null;
        if(bi != null)
        	uid = bi.getUid();
		
		
		//仅修改密码
		if(sn == null || "".equals(sn.trim())) {
			if(userpassword != null && !"".equals(userpassword.trim())) {
				SHApwd = "{SHA}"+LDAPPeopleUtil.passwordKey(userpassword);
				DOService ser = DOService.getService("zf_employee_update_pwd_only");
				try {
					ser.invokeUpdate(SHApwd,uid);
					this.setEchoValue("重设密码成功！");
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					this.setEchoValue("重设密码失败，请重试！");
					e.printStackTrace();
				}
			}
		} else {
			
			//修改基本信息
			if(userpassword == null || "".equals(userpassword.trim())) {
				DOService ser = DOService.getService("zf_employee_update_xinxi");
				try {
					ser.invokeUpdate(mailmessagestore,uid);
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.setEchoValue("信息修改失败，请重试！");
				}
				
				LDAPSearchResults rs = LDAPPeopleUtil.getLDAPRsByuid(uid);
				LDAPEntry fullEntry = new LDAPEntry();
				//uid,sn,cn,mobile,telephonenumber,mailmessagestore,mail,mailquotacount,mailquotasize,mailhost,employeenumber
				//sn=?,cn=?,mobile=?,telephonenumber=?,mailmessagestore=?,mail=?,mailquotacount=?,mailquotasize=?,mailhost=?,employeenumber=?
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
					try {
						fullEntry = rs.next();
						LDAPAttribute att_sn = fullEntry.getAttribute("sn");
						if(att_sn != null) {
							e_sn = att_sn.getStringValue();
						}
						LDAPAttribute att_cn = fullEntry.getAttribute("cn");
						if(att_cn != null) {
							e_cn = att_cn.getStringValue();
						}
						LDAPAttribute att_mobile = fullEntry.getAttribute("mobile");
						if(att_mobile != null) {
							mobile = att_mobile.getStringValue();
						}
						LDAPAttribute att_telephonenumber = fullEntry.getAttribute("telephonenumber");
						if(att_telephonenumber != null) {
							telephonenumber = att_telephonenumber.getStringValue();
						}
						LDAPAttribute att_mailmessagestore = fullEntry.getAttribute("mailmessagestore");
						if(att_mailmessagestore != null) {
							e_mailmessagestore = att_mailmessagestore.getStringValue();
						}
						LDAPAttribute att_mail = fullEntry.getAttribute("mail");
						if(att_mail != null) {
							mail = att_mail.getStringValue();
						}
						LDAPAttribute att_mailquotacount = fullEntry.getAttribute("mailquotacount");
						if(att_mailquotacount != null) {
							mailquotacount = att_mailquotacount.getStringValue();
						}
						LDAPAttribute att_mailquotasize = fullEntry.getAttribute("mailquotasize");
						if(att_mailquotasize != null) {
							mailquotasize = att_mailquotasize.getStringValue();
						}
						
						LDAPAttribute att_mailhost = fullEntry.getAttribute("mailhost");
						if(att_mailhost != null) {
							mailhost = att_mailhost.getStringValue();
						}
						LDAPAttribute att_employeenumber = fullEntry.getAttribute("employeenumber");
						if(att_employeenumber != null) {
							employeenumber = att_employeenumber.getStringValue();
						}
						
					} catch (LDAPException e) {
						System.out.println("Error:   " + e.toString());
						continue;
					}
				}
				
				DOService linkser = DOService.getService("zf_employee_update_copy");
				try {
					linkser.invokeUpdate(e_sn,e_cn,mobile,telephonenumber,e_mailmessagestore,mail,mailquotacount,mailquotasize,mailhost,employeenumber,uid);
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				//新增记录
				SHApwd = "{SHA}"+LDAPPeopleUtil.passwordKey(userpassword);
				uid = sn;
				DOService ser = DOService.getService("zf_employee_update_pwd_and_orther");
				try {
					ser.invokeUpdate(mailmessagestore,SHApwd,uid);
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.setEchoValue("新增记录失败，请重试！");
				}
				
				LDAPSearchResults rs = LDAPPeopleUtil.getLDAPRsByuid(uid);
				LDAPEntry fullEntry = new LDAPEntry();
				//uid,sn,cn,mobile,telephonenumber,mailmessagestore,mail,mailquotacount,mailquotasize,mailhost,employeenumber
				//sn=?,cn=?,mobile=?,telephonenumber=?,mailmessagestore=?,mail=?,mailquotacount=?,mailquotasize=?,mailhost=?,employeenumber=?
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
					try {
						fullEntry = rs.next();
						LDAPAttribute att_sn = fullEntry.getAttribute("sn");
						if(att_sn != null) {
							e_sn = att_sn.getStringValue();
						}
						LDAPAttribute att_cn = fullEntry.getAttribute("cn");
						if(att_cn != null) {
							e_cn = att_cn.getStringValue();
						}
						LDAPAttribute att_mobile = fullEntry.getAttribute("mobile");
						if(att_mobile != null) {
							mobile = att_mobile.getStringValue();
						}
						LDAPAttribute att_telephonenumber = fullEntry.getAttribute("telephonenumber");
						if(att_telephonenumber != null) {
							telephonenumber = att_telephonenumber.getStringValue();
						}
						LDAPAttribute att_mailmessagestore = fullEntry.getAttribute("mailmessagestore");
						if(att_mailmessagestore != null) {
							e_mailmessagestore = att_mailmessagestore.getStringValue();
						}
						LDAPAttribute att_mail = fullEntry.getAttribute("mail");
						if(att_mail != null) {
							mail = att_mail.getStringValue();
						}
						LDAPAttribute att_mailquotacount = fullEntry.getAttribute("mailquotacount");
						if(att_mailquotacount != null) {
							mailquotacount = att_mailquotacount.getStringValue();
						}
						LDAPAttribute att_mailquotasize = fullEntry.getAttribute("mailquotasize");
						if(att_mailquotasize != null) {
							mailquotasize = att_mailquotasize.getStringValue();
						}
						
						LDAPAttribute att_mailhost = fullEntry.getAttribute("mailhost");
						if(att_mailhost != null) {
							mailhost = att_mailhost.getStringValue();
						}
						LDAPAttribute att_employeenumber = fullEntry.getAttribute("employeenumber");
						if(att_employeenumber != null) {
							employeenumber = att_employeenumber.getStringValue();
						}
						
					} catch (LDAPException e) {
						System.out.println("Error:   " + e.toString());
						continue;
					}
				}
				
				DOService linkser = DOService.getService("zf_employee_insert_copy");
				try {
					linkser.invokeUpdate(uid,e_sn,e_cn,mobile,telephonenumber,e_mailmessagestore,mail,mailquotacount,mailquotasize,mailhost,employeenumber);
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
        
//		System.out.println("=======================================");
//		System.out.println("uid================="+uid);
//		System.out.println("sn================="+sn);
//		System.out.println("cn================="+cn);
//		System.out.println("mobile============="+mobile);
//		System.out.println("telephonenumber===="+telephonenumber);
//		System.out.println("mail==============="+mail);
//		System.out.println("mailquotacount====="+mailquotacount);
//		System.out.println("mailquotasize======"+mailquotasize);
//		System.out.println("userpassword======="+userpassword);
//		System.out.println("SHApwd============="+SHApwd);
//		System.out.println("mailmessagestore==="+mailmessagestore);
//		System.out.println("mailhost==========="+mailhost);
//		System.out.println("=======================================");

		
		return DEFAULT_FORWARD;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
