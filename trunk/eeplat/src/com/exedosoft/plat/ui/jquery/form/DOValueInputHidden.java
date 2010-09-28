package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueInputHidden implements DOIView {

	public String getHtmlCode(DOIModel aModel) {
		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null) {
			DOFormModel property = (DOFormModel) aModel;

			StringBuffer buffer = new StringBuffer();
			buffer.append("<input name=\"").append(property.getFullColName())
					.append("\" type=\"hidden\"");
			if (property.getValue() != null
					&& !property.getValue().trim().equals("")) {
				buffer.append(" value=\"").append(property.getValue()).append(
						"\"");
			}
			buffer.append("/>").append(property.getValue());
			return buffer.toString();
		} else {
			return "&nbsp;";
		}
	}

}
