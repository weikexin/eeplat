package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOValueStaticListHidden extends DOStaticList {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<input name=\"").append(property.getFullColName())
				.append("\" type=\"hidden\"");
		if (property.getValue() != null
				&& !property.getValue().trim().equals("")) {
			buffer.append(" value=\"").append(property.getValue()).append(
					"\"");
		}
		buffer.append("/>").append(getStaticListValue(property));
		return buffer.toString();
	}

}
