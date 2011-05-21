package com.exedosoft.plat.login;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.autorun.RunInsertWLogLastMonth;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.util.DOGlobals;

import com.exedosoft.plat.DAOUtil;




public class LoginActionLDAP2 extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5564360845175975061L;

	private static Log log = LogFactory.getLog(LoginActionLDAP2.class);
	public String excute() {

		List users = new ArrayList();

		if (DOGlobals.LOGIN_CA == DOGlobals.LOGIN_CA_YES) {

			// HttpServletRequest request =
			// DOGlobals.getInstance().getServletContext().getRequest();
			// String caID =
			// (String)request.getSession().getAttribute("UserId");
			// if(caID==null || "".equals(caID.trim())){
			// this.setEchoValue("对不起，您没有通过CA认证！");
			// return "notpass";
			// }
			// System.out.println(caID);
			// users = findByCaCode.invokeSelect(caID);
		} else {
			System.out.println(this.actionForm);
			String userName = this.actionForm.getValue("name");
			String userpassword = this.actionForm.getValue("password");
			String SHApwd = "{SHA}"+LDAPPeopleUtil.passwordKey(userpassword);
			users = service.invokeSelect(userName,SHApwd);
		}

		if (users != null && users.size() > 0) {
			BOInstance user = (BOInstance) users.get(0);
			Date invalidTime = user.getDateValue("invalidTime");
			if (invalidTime != null) {
				System.out.println("该用户的过期时间::" + invalidTime);
				if (invalidTime.before(new Date(System.currentTimeMillis()))) {
					this.setEchoValue("该用户账户已经过期！");
					return NO_FORWARD;
				}
			}
			SessionContext sc = LoginMain.makeLogin(user, DOGlobals.getInstance()
					.getServletContext().getRequest());
			String deptuid = "";
			
			DOService findDeptByUserId = DOService.getService("findDeptByUserId"); 
			deptuid = findDeptByUserId.invokeSelectGetAValue(sc.getUser().getUid());
			sc.getUser().putValue("deptuid", deptuid);
			
			String fdstate = user.getValue("fdstate");
			if ("0".equals(fdstate)) {
				this.setEchoValue("该用户已冻结，请跟系统管理员联系！");
				return NO_FORWARD;
			}
			LoginMain.makeLogin(user, DOGlobals.getInstance()
					.getServletContext().getRequest());
			
			return "success";
		} else {
			this.setEchoValue("用户名或密码错误，请重试!");
			return "notpass";
		}

	}

}
