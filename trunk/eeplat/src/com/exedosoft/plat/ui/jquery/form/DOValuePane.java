package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

/**
 * 在目标 Form 上建立连接，可以弹出一个配置Pane.
 * 
 * @author aa
 * 
 */
public class DOValuePane extends DOBaseForm {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		if (property.getData() != null && property.getLinkPaneModel() != null) {

			StringBuffer buffer = new StringBuffer("<a href=\"");
	
			DOValueService.appendJSPaneLink(property,buffer);
			buffer.append("\" > ").append(property.getValue()).append("</a>");

			return buffer.toString();
		}
		return property.getValue();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
