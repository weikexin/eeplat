package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOValueScriptPlain extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {
		DOFormModel fm = (DOFormModel) aModel;
		
		String value = fm.getPlainScriptValue();
		if (value != null && !value.trim().equals("")) {

			return value;
		} else {
			return "&nbsp;";
		}
	}

}
