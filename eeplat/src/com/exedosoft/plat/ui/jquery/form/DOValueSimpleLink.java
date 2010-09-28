package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueSimpleLink implements DOIView {
	
	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null && !fm.getValue().trim().equals("")) {
			
			if(fm.getValue().indexOf("http://")==-1){
			
			StringBuffer buffer1 = new StringBuffer();			
			buffer1.append("<a  class='exedo_link' target='_blank' href='http://");
			buffer1.append(fm.getValue()).append("'>");
			buffer1.append(fm.getValue());
			buffer1.append("<a>");	
				
			return buffer1.toString();
				
			}
			else{

				StringBuffer buffer = new StringBuffer();			
				buffer.append("<a  class='exedo_link' target='_blank' href='");
				buffer.append(fm.getValue()).append("'>");
				buffer.append(fm.getValue());
				buffer.append("<a>");
				
				return buffer.toString();
			}
		} else {
			return "&nbsp;";
		}
	}

}
