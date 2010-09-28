package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueHtmlXss implements DOIView {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null && !fm.getValue().trim().equals("")) {
			
			return StringUtil.unFilterXss(fm.getValue());
		} else {
			return "&nbsp;";
		}
	}

}
