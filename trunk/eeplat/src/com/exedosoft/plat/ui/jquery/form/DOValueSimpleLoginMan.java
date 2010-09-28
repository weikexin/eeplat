package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.action.customize.tools.DOGeneConfigTable;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;

public class DOValueSimpleLoginMan implements DOIView {
	

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;
		//String value = fm.getValue();
		
		
		String theValue = DOGlobals.getInstance().getSessoinContext().getUser().getUid();
		
		BOInstance bi = null;
		
		DOBO corrBO = property.getLinkBO();
		


		if (corrBO == null && property.getLinkService() != null) {
			corrBO = property.getLinkService().getBo();
		}
	

		if (theValue != null
				&& !"".equals(theValue.trim())) {
			
			bi = DOValueResultList.getAInstance(property,corrBO, theValue);
		}
	

		if (bi != null) {
			return bi.getName();
		}
		
		if (theValue == null || "null".equals(theValue)) {
			return "&nbsp;";
		}
		return theValue;
}
}
