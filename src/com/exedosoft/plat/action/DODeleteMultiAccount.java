package com.exedosoft.plat.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;

import com.exedosoft.plat.login.MultiAccount;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0 
 */

public class DODeleteMultiAccount extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798265805984034747L;
	private static Log log = LogFactory.getLog(DODeleteMultiAccount.class);

	public DODeleteMultiAccount() {
	}

	public String excute()  {

		DOBO userBO = DOBO.getDOBOByName("do_org_user");
		BOInstance userInstance = userBO.getCorrInstance();
		if(userInstance!=null){
			MultiAccount.deleteUser(userInstance.getUid());
		}
		return DEFAULT_FORWARD;
	}

}
