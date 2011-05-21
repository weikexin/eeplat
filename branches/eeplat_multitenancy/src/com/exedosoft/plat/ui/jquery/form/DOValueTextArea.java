package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOValueTextArea extends DOBaseForm {

	public DOValueTextArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();
		String areaConfig = property.getInputConfig();
		int cols = 50;
		int rows = 5;
		if (areaConfig != null && !areaConfig.equals("")) {
			String[] configs = areaConfig.split(";");
			if (configs != null && configs.length == 2) {
				if (Integer.parseInt(configs[0]) != 0) {
					cols = Integer.parseInt(configs[0]);
				}
				if (Integer.parseInt(configs[1]) != 0) {
					rows = Integer.parseInt(configs[1]);
				}
			}
		}
		
		buffer.append("<textarea  name=\"").append(property.getColName()).append("\"");
		buffer.append(" readonly=\"readonly\" id=\"").append(property.getFullColID()).append("\" ");
//		buffer	.append("\" dojoType=\"");
//		if (property.getExedojoType() != null
//				&& !"".equals(property.getExedojoType().trim())) {
//			buffer.append(property.getExedojoType()).append("\"");
//		} else {
//			buffer.append("ValidationTextBox\"");
//		}
	
		buffer.append(" title='").append(property.getL10n().trim()).append("'");

		buffer.append(this.appendValidateConfig(property));
		
				buffer.append(" cols=").append("\"").append(cols)
				.append("\" rows=").append("\"").append(rows).append("\">");
		if (property.getValue() != null) {
			buffer.append(property.getValue());
		}
		
		buffer.append("</textarea>");

		
		return buffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
