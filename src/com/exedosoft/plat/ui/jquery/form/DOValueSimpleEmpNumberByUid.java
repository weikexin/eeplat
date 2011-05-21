package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueSimpleEmpNumberByUid implements DOIView {
	

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		String value = fm.getValue();
		

		if (value != null && !value.trim().equals("")) {
			String employeenumber = LDAPPeopleUtil.getLDAPEmpnumberByUid(value);
			if(fm.getStyle()!=null&&!"".equals(fm.getStyle()))
			{
				//return "<span style='"+fm.getStyle()+"'>"+value+"<";;
				StringBuffer sb=new StringBuffer();
				sb.append("<span style='").append(fm.getStyle()).append("'>").append(employeenumber).append("</span>");
				return sb.toString();
			}
			if(employeenumber != null)
				return employeenumber;
			else
				return "&nbsp;";
		} else {
			return "&nbsp;";
		}
	}

}
