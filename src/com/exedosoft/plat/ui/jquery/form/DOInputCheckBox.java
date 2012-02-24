package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;

public class DOInputCheckBox extends DOStaticList {

	public DOInputCheckBox() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {
		
		DOFormModel property = (DOFormModel)iModel;			

		StringBuffer buffer = new StringBuffer();
		buffer.append("<input name=\"").append(property.getFullColName())
		.append("\" id=\"").append(property.getFullColID());
		
		String configValue = "";
		if (property.getInputConfig() != null
				&& !"".equals(property.getInputConfig())) {
			configValue = property.getInputConfig().substring(0,
					property.getInputConfig().indexOf(","));
			buffer.append("\" value=\"").append(configValue);
		} else {
			buffer.append("\" value=\"").append("1");
		}
		
		buffer.append("\" class=\"custom\"   type=\"checkbox\"");
		
		buffer.append(getDecoration(property));

		
		// /////////////判断是否缺省选中
		boolean isDefaultChecked = false;
		if (property.getInputConfig() != null
				&& property.getInputConfig().indexOf("@") != -1) {
			String defaultCheck = property.getInputConfig().substring(
					property.getInputConfig().indexOf("@") + 1);
			if (configValue.equalsIgnoreCase(defaultCheck)) {
				isDefaultChecked = true;
			}
		}

		if (property.getValue() != null || isDefaultChecked) {
			buffer.append(" checked ");
		}
		if (isReadOnly(property)) {
			buffer.append(" DISABLED  ");
		}
		if (property.getDoClickJs() != null
				&& !"".equals(property.getDoClickJs().trim())) {
			buffer.append(" onclick='").append(
					property.getEscapeDOClickJs()).append("'");
		}
		
		buffer.append("/>");
		
		
		
		
		if ("jquery_mobile".equals(DOGlobals.getInstance()
				.getSessoinContext().getUser().getValue("jslib"))) {
			
			if("".equals(configValue)){
				configValue = "&nbsp";
			}
			
			buffer.append("<label for='").append(property.getFullColID())
			.append("'> ").append(configValue).append("</label>");
			
		}
		return buffer.toString();
	}

}
