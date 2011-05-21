package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueSimpleCnByUid implements DOIView {
	

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		String value = fm.getValue();
		

		if (value != null && !value.trim().equals("")) {
			String sn = LDAPPeopleUtil.getLDAPSnByUid(value);
			String cn = LDAPPeopleUtil.getLDAPCNBySN(sn);
			
			if(fm.getStyle()!=null&&!"".equals(fm.getStyle()))
			{
				//return "<span style='"+fm.getStyle()+"'>"+value+"<";;
				StringBuffer sb=new StringBuffer();
				sb.append("<span style='").append(fm.getStyle()).append("'>").append(cn).append("</span>");
				return sb.toString();
			}
			if(cn != null)
				return cn;
			else if(sn != null) 
				return sn;
			else
				return "&nbsp;";
		} else {
			return "&nbsp;";
		}
	}

}
