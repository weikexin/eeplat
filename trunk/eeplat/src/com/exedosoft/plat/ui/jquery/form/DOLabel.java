package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOLabel extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {


		DOFormModel fm = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();
		if(fm.getNote()!=null)
		{
			if(fm.getStyle()!=null)
			{
				buffer.append("<span style='").append(fm.getStyle()).append("'>");
				buffer.append(fm.getNote());
				buffer.append("</span>");
				return buffer.toString();
			}
			buffer.append(fm.getNote());
		}
		return buffer.toString();
	}

}
