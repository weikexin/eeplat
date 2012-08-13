package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOValueResultListPlain extends DOBaseForm {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;
		BOInstance bi = null;
		
		DOBO corrBO = property.getLinkBO();
		
		String theValue = property.getValue();

		if (corrBO == null && property.getLinkService() != null) {
			corrBO = property.getLinkService().getBo();
		}
	

		if (theValue != null
				&& !"".equals(theValue.trim())) {
			
			bi = DOValueResultList.getAInstance(property,corrBO, theValue);
		}
	

		if (bi != null) {
			if(bi.getName()!=null && !"".equals(bi.getName())){
				return bi.getName();
			}
		}
		return "&nbsp;";
		
//		if (theValue == null || "null".equals(theValue)) {
//			return "&nbsp;";
//		}
//		return theValue;

	}
}
