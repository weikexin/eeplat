package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.gene.jquery.PropertyManager;
import com.exedosoft.plat.util.I18n;

public class DOGeneConfigRemoveProperty extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4555077593344493040L;

	@Override
	public String excute() throws ExedoException {
		
		DOBO bo = DOBO.getDOBOByName("do_bo");
		BOInstance instance = bo.getCorrInstance();
		if(instance==null){
			this.setEchoValue(I18n.instance().get("没有数据!"));

			return NO_FORWARD;
		}
		
		DOBO boProperty = DOBO.getDOBOByName("DO_BO_Property");
		BOInstance propertyInstance = boProperty.getCorrInstance();
		if(propertyInstance==null){
			this.setEchoValue(I18n.instance().get("没有数据!"));
			return NO_FORWARD;
		}
		DOBOProperty dop = DOBOProperty.getDOBOPropertyByID(propertyInstance.getUid());
		DOBO thisBO = DOBO.getDOBOByID(instance.getUid());
		
		PropertyManager pm = new PropertyManager();
		pm.removeProperty(thisBO, dop);
		return DEFAULT_FORWARD;
	}

}
