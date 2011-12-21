package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueSimpleDiv implements DOIView {
	

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		String value = StringUtil.unFilterXss(fm.getValue());
		String areaConfig = fm.getInputConfig();
		String width = "400px";
		String height = "200px";
		if (areaConfig != null && !areaConfig.equals("")) {
			String[] configs = areaConfig.split(";");
			if (configs != null && configs.length == 2) {
				if (configs[0] != null || !"".equals(configs[0].trim())) {
					width = configs[0];
				}
				if (configs[1] != null || !"".equals(configs[1].trim())) {
					height = configs[1];
				}
			}
		}
		
		
		StringBuffer sb=new StringBuffer();
		sb.append("<div style=\"width: ").append(width).append("; height:").append(height).append(";background-color: white;border:#E6E6F2 1px solid;\">");
		if (value != null && !value.trim().equals("")) {
			
			if(fm.getStyle()!=null&&!"".equals(fm.getStyle()))
			{
				sb.append("<span style='").append(fm.getStyle()).append("'>").append(value).append("</span>");
			} else {
				sb.append(value);
			}
		}
		sb.append("</div>");
		return sb.toString();
	}

}
