package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOInputHidden extends DOBaseForm {

	public DOInputHidden() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;
		StringBuffer buffer = new StringBuffer();

		buffer.append("<input name=\"").append(property.getColName()).append(
				"\" type=\"hidden\" id=\"").append(property.getFullColID()).append("\" ");
		
		String value = null;
		if (property.getValue() != null
				&& !property.getValue().trim().equals("")) {
			value = property.getValue();
			
		}
		if(value==null){
			value = property.getInputConfig();
		}
		
		if(value==null){
			value = property.getDefaultValue();
		}
		
		buffer.append(" value=\"").append(value).append("\"");
		buffer.append("/>");

		return buffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
