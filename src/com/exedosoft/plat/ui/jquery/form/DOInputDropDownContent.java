package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.id.UUIDHex;

public class DOInputDropDownContent extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {
		
		DOFormModel fm = (DOFormModel) aModel;

		String widgetID = "a" + UUIDHex.getInstance().generate();

		StringBuffer buffer = new StringBuffer();
		buffer.append("<div dojoType='dropdowncontainer'")
		.append(" id='")
		.append(widgetID)
		.append("'>")
//		.append("<div class='popup' dojoType='ContentPane'>	")
//		.append(fm.getLinkPaneModel().getHtmlCode())
//		.append("</div>")
		.append("<div class='popup' dojoType='ContentPane' 	id='")
				.append(fm.getLinkPaneModel().getName()).append("'");
		
		buffer.append(" executeScripts='true' href='")
		.append(fm.getLinkPaneModel().getCorrHref(null, null))
		.append("&dropDownID=")
		.append(widgetID)
		.append("'></div>");

	
	//	buffer.append("</div>");
		
	
		return buffer.toString();
	}

}
