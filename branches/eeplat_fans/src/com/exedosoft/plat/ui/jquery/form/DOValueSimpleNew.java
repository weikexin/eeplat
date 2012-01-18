package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;

public class DOValueSimpleNew implements DOIView {

	public String getHtmlCode(DOIModel aModel) {
		// newrecord
		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null) {
			if (fm.getData().getValue("newrecord") == null) {
				
				StringBuffer buffer = new StringBuffer(fm.getValue());
				buffer.append("<img src='").append(DOGlobals.PRE_FULL_FOLDER)
				.append("images/").append("newrecord.gif").append(
						"'/>");
				
				return buffer.toString();

			} else {
				return fm.getValue();
			}
		} else {
			return "&nbsp;";
		}
	}

}
