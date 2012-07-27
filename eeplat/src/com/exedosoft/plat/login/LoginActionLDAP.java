package com.exedosoft.plat.login;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;



public class LoginActionLDAP extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5564360845175975061L;

	private static Log log = LogFactory.getLog(LoginActionLDAP.class);
	public String excute() {

		String userName = this.actionForm.getValue("name");
		String pwd = this.actionForm.getValue("password");
		
		if (check(userName, pwd)) {
			BOInstance user = new BOInstance();
			user.putValue("name", userName);
			user.setUid(userName);
			try {
				user.putValue(this.service.getBo().getKeyCol(), userName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SessionContext sc = LoginMain.makeLogin(user, DOGlobals.getInstance()
					.getServletContext().getRequest());
			String deptuid = "";
			
			DOService findDeptByUserId = DOService.getService("findDeptByUserId"); 
			deptuid = findDeptByUserId.invokeSelectGetAValue(sc.getUser().getUid());
			sc.getUser().putValue("deptuid", deptuid);
			//保存用户名和密码
			sc.getUser().putValue("username_email", userName);
			sc.getUser().putValue("password_email", pwd);
			return "success";
		} else {
			this.setEchoValue(I18n.instance().get("当前工作流上下文丢失,请重新操作!"));

			this.setEchoValue("用户名或密码错误，请重试!");
			return "notpass";
		}
	}

	public static boolean check(String userName, String pwd) {

		DODataSource dss = DODataSource.parseConfigHelper("/ds_ldap_url.xml",
				"ds_ldap_url");
		InitialContext iCnt = null;
		Hashtable envi = new Hashtable();
		try {
			envi.put("java.naming.factory.initial",
					"com.sun.jndi.ldap.LdapCtxFactory");
			envi.put("java.naming.provider.url", dss.getDriverUrl());
			envi.put(Context.SECURITY_AUTHENTICATION, "simple");
			envi.put(Context.SECURITY_PRINCIPAL, "uid=" + userName
					+ dss.getUserName());
			envi.put(Context.SECURITY_CREDENTIALS, pwd);
			iCnt = new InitialContext(envi);
			System.out.println("认证通过!");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (iCnt != null) {
					iCnt.close();
				}
			} catch (Exception ie) {

			}
		}
		return true;

	}
	
	public static void main(String[] args){
		
		LoginActionLDAP.check("yuanxx", "yyfxyxx2008");
	}
}



// ///////////海洋局专用
//
// if (this.actionForm.getValue("inner_user") != null
// && Integer.parseInt(maxDegree) < 6) {
// this.setEchoValue("对不起，您只能以媒体用户进入!");
// return "notpass";
// }

// ////////////海洋局专用

// BOInstance aDegreeIns = new BOInstance();
// aDegreeIns.putValue("secret_name", user.getName());
// aDegreeIns.putValue("secret_id", maxDegree);
//
// DOBO aDegreeBO = DOBO.getDOBOByName("sea.docsecret");
// DOGlobals.getInstance().getSessoinContext().putCorrInstance(
// aDegreeBO, aDegreeIns);
// //////////////////海洋局文档系统
