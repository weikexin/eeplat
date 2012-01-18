package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.DOAccess;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.DOGlobals;

public class JudgeCW implements DOAccess {

	public boolean isAccess(BOInstance aBI) {


		SessionContext us = (SessionContext) DOGlobals.getInstance()
		.getServletContext().getRequest().getSession().getAttribute(
		"userInfo");
		BOInstance user = us.getUser();
		if(user.getName().equals("cwry")){
			return true;
		}
		return false;
	}

}
