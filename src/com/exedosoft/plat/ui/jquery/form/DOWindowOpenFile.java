package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.id.UUIDHex;

public class DOWindowOpenFile extends DOBaseForm {

	public String getHtmlCode(DOIModel property) {
		
		DOFormModel fm = (DOFormModel) property;

		// property.getLinkService()

		StringBuffer buffer = new StringBuffer("<button dojoType='");

		if (fm.getExedojoType() != null
				&& !"".equals(fm.getExedojoType().trim())) {
			buffer.append(fm.getExedojoType()).append("'");
		} else {
			buffer.append("Button'");
		}
		String buttonId = UUIDHex.getInstance().generate();
		buffer.append(" id='").append(buttonId).append("'");

		StringBuffer href = new StringBuffer("window.open('/").append(
				DOGlobals.URL).append("/").append(fm.getInputConfig());
		href.append("','','width=800,height=600,top=100,scrollbars=yes')");

		buffer.append(" onclick=\"javascript:").append(href).append("\"");

		buffer.append(" >\n");

		// buffer.append("<img height='18' src='");
		// if (fm.getImage() != null && !"".equals(fm.getImage().trim())) {
		// buffer.append(fm.getImage()).append("'");
		// } else {
		// buffer.append(DOGlobals.PRE_FOLDER).append("images/ok.gif'");
		// }
		// buffer.append(" alt='").append(fm.getL10n()).append("' ");
		//
		// buffer.append("/>\n");
		// buffer.append(fm.)
		buffer.append(fm.getL10n());
		buffer.append("\n</button>");
		return buffer.toString();

	}

}
