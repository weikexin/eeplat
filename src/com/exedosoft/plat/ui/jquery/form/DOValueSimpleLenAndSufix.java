package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueSimpleLenAndSufix implements DOIView {
	

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		String value = fm.getValue();
		

		if (value != null && !value.trim().equals("")) {
			String cof = fm.getInputConfig();
			if(cof != null && cof.indexOf(";") != -1) {
				String[] str = cof.split(";");
				String len = str[0];
				String sufix = str[1];
				if(len.trim().matches("^\\d+$")) {
					int length = Integer.parseInt(cof);
					value = value.substring(0, length);
				}
				value = value+sufix;
			} else if(cof.trim().matches("^\\d+$")){
				int length = Integer.parseInt(cof);
				value = value.substring(0, length);
			}

			if(fm.getStyle()!=null&&!"".equals(fm.getStyle()))
			{
				//return "<span style='"+fm.getStyle()+"'>"+value+"<";;
				StringBuffer sb=new StringBuffer();
				sb.append("<span style='").append(fm.getStyle()).append("'>").append(value).append("</span>");
				return sb.toString();
			}

			return value;
		} else {
			return "&nbsp;";
		}
	}

}
