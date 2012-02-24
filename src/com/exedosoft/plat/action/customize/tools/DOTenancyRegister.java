package com.exedosoft.plat.action.customize.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.multitenancy.RegisterMail;

public class DOTenancyRegister extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 558266835833057020L;

	private static Log log = LogFactory
			.getLog(DOTenancyRegister.class);



	@Override
	public String excute() throws ExedoException {
		
        DOBO tenancyBO = DOBO.getDOBOByName("multi_tenancy");
        BOInstance tenancyBI = tenancyBO.getCorrInstance();
        

        DOBO accountBO = DOBO.getDOBOByName("multi_account");
        BOInstance accountBI = accountBO.getCorrInstance();
        
        log.info("正在为租户发送邮件:::" + tenancyBI.getUid() + "," + accountBI.getValue("name"));
		RegisterMail.sendRegisterMail(tenancyBI.getUid(),accountBI.getValue("name"));
		return DEFAULT_FORWARD;

	}

	
	public static void main(String[] args) {


	}
}
