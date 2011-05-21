package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOInputTextLong extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();

		buffer.append("<input type='text' name='")
				.append(property.getFullColName())
				.append("' id='")
				.append(property.getFullColID()).append("' dojoType='");
		if (property.getExedojoType() != null
				&& !"".equals(property.getExedojoType().trim())) {
			buffer.append(property.getExedojoType()).append("'");
		} else {
			buffer.append("ValidationTextBox'");
		}
		
		buffer.append(this.appendValidateConfig(property));
		

		
		//////增加装饰
		buffer.append(getDecoration(property));

		/////////end 增加装饰
		buffer.append(" title='")
		.append(property.getL10n())
		.append("'");

		if (property.isNotNull()) {
			buffer.append("  required='true' ");
		}
		if (property.getValue() != null) {
			String longValue = property.getValue();
			if(longValue.indexOf(".")>-1){
				longValue = longValue.substring(0,longValue.indexOf("."));
			}
			buffer.append(" value = '").append(longValue).append("'");
		}
		if (isReadOnly(property)) {
			buffer.append(" readonly='readonly' ");

		}
		buffer.append(" size=\"").append(getInputSize(property)).append("\"/>");

		return buffer.toString();
	}

}
