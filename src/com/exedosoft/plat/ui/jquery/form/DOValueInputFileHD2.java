package com.exedosoft.plat.ui.jquery.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;


public class DOValueInputFileHD2 extends DOBaseForm {
	
	private static Log log = LogFactory.getLog(DOValueInputFileHD2.class);


	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		if (property.getValue() != null) {
			String theValue = property.getValue();
			StringBuffer fileUrl = StringUtil.getAttachMentFile(theValue);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<a  class='exedo_link'  href=");
			buffer.append(fileUrl).append(">");
			buffer.append(property.getL10n());
			buffer.append("</a>");
			return buffer.toString();
		} 
		return "无法下载";
	}


	
	
	public static void main(String[] args){
		
		String aa = "200007\\99.doc";
		
	    System.out.println(aa.substring(aa.indexOf("\\")+1));
		
	}
	
	
	

}
