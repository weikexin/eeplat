package com.exedosoft.plat.login;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.org.SessionParterFactory;

public class LoginDelegateAction extends DOAbstractAction {

	private static Log log = LogFactory.getLog(LoginDelegateAction.class);

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub

		DOBO boUser = DOBO.getDOBOByName("do_org_user");
		String delegateuid = this.actionForm.getValue("delegateuid");
		BOInstance delegateUser = boUser.getInstance(delegateuid);
		SessionContext.getInstance().setDeleGate(delegateUser);
		BOInstance user = SessionContext.getInstance().getUser();

		List allAuthParter = SessionParterFactory.getSessionParter()
				.getParterAuths(delegateuid);
		user.putValue(LoginMain.ALLAUTH, allAuthParter);

		List allAuthMenus = SessionParterFactory.getSessionParter()
				.getMenuAuthConfigByAccount(delegateuid);
		if (allAuthMenus != null && !allAuthMenus.isEmpty()) {
			user.putValue(LoginMain.ALLAUTHMENUS, allAuthMenus);
		}

		return this.DEFAULT_FORWARD;
	}

}
