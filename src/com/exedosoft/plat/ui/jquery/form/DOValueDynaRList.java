package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOValueDynaRList extends DOBaseForm {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel fm = (DOFormModel) iModel;

		if (fm.getLinkForms() == null || fm.getLinkForms().size() == 0) {
			return "&nbsp;";
		}

		String typeColName = ((DOFormModel) fm.getLinkForms().get(0))
				.getColName();
		String typeValue = fm.getData().getValue(typeColName);

		String serviceName = StringUtil.getValueByKey(fm.getInputConfig(),
				typeValue);

		String theValue = fm.getValue();

		if (theValue == null || theValue.trim().equals("")) {
			return "&nbsp;";
		}

		if (serviceName != null && !"".equals(serviceName.trim())) {
			
			DOService service = DOService.getService(serviceName);
			if (service != null) {
				BOInstance bi = null;
				if (service.retrieveParaServiceLinks() != null
						&& service.retrieveParaServiceLinks().size() == 1) {
					bi = service.getInstance(theValue);
				}
				if(bi==null){
					DOBO corrBO = service.getBo();
					bi = corrBO.getInstance(theValue);
				}
				if (bi != null) {
					return bi.getThisLink().replace("popupDialog", "createNewTab");
				}
			}
		}
		if (theValue == null || "null".equals(theValue)) {
			return "&nbsp;";
		}
		return fm.getValue();

	}
}
