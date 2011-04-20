package com.exedosoft.plat;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.aop.DOFormModelAOP;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.util.DOGlobals;

public class XesFormAccessAOP implements DOFormModelAOP {

	public Boolean isAccess(DOFormModel formModel) {

		if (DOGlobals.getInstance().getSessoinContext().getUser() != null) {

			BOInstance aRole = (BOInstance) DOGlobals.getInstance()
					.getSessoinContext().getUser().getObjectValue("roleObj");
			if(aRole!=null){
				if ("5".equals(aRole.getValue("degree"))) {
					return Boolean.TRUE;
				}
			}
		}
		return null;
	}
}
