package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueLink implements DOIView {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;
		StringBuffer buffer = new StringBuffer();
		// //////////对picture类型的特殊处理

		String[] oneLink = property.getValue().split(",");
		for (int i = 0; i < oneLink.length; i++) {
			buffer.append("<a class='exedo_link' href='/").append("/").append(oneLink[i]).append(
					"'>").append(oneLink[i]).append("</a>");
			if (i < oneLink.length - 1) {
				buffer.append(",&nbsp;");
			}
		}

		return buffer.toString();

	}

	

}
