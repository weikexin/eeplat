package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;

public class DOBarCode extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		if (property.getValue() != null) {
			
			String barCodeValue = property.getValue();

			StringBuffer aPath = new StringBuffer("/")
			.append(DOGlobals.URL)
			.append("/dobarcode/barcode?data=")
			.append(barCodeValue)
			.append("&height=50");
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("<a href=");
			buffer.append(aPath).append(">");
			// //////////对picture类型的特殊处理

			buffer.append("<img src=").append(aPath).append(" border='0'>");

			buffer.append("</a>");
			return buffer.toString();
		}
		return "&nbsp;";
	}

}
