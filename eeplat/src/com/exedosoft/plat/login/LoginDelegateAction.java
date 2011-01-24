package com.exedosoft.plat.login;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;

public class LoginDelegateAction  extends DOAbstractAction {

	private static Log log = LogFactory.getLog(LoginDelegateAction.class);
	

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		

		DOBO boUser = DOBO.getDOBOByName("do_org_user");
		String delegateuid = this.actionForm.getValue("delegateuid");
		BOInstance delegateUser = boUser.getInstance(delegateuid);
		SessionContext.getInstance().setDeleGate(delegateUser);
		return this.DEFAULT_FORWARD;
	} 

	
	
	
	


}
