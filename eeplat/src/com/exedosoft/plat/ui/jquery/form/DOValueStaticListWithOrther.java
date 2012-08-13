package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOValueStaticListWithOrther extends DOStaticList {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;
		BOInstance bi = property.getData();
		String wseladdress = null;
		String wvacationtype = null;
		if(bi != null) {
			wseladdress = bi.getValue("wseladdress");
			wvacationtype = bi.getValue("wvacationtype");
		}
		if(wseladdress == null || "".equals(wseladdress.trim()))
			wseladdress = "&nbsp;";
		if(wvacationtype == null || "".equals(wvacationtype.trim()))
			wvacationtype = "&nbsp;";
		
		String value = property.getValue();
		if(value == null)
			return "&nbsp;";
		else if("1".equals(value.trim())) {
			return "公司或本地";
		} else if("2".equals(value.trim())) {
			return "出差地A:&nbsp;"+wseladdress;
		} else if("3".equals(value.trim())) {
			return "出差地B:&nbsp;"+wseladdress;
		} else if("4".equals(value.trim())) {
			String vacation = "&nbsp;";
			if(wvacationtype.trim().equals("1")) {
				vacation = "公休假日";
			} else if(wvacationtype.trim().equals("2")) {
				vacation = "法定休假日";
			} else if(wvacationtype.trim().equals("3")) {
				vacation = "病事假";
			}
			return "休假:&nbsp;"+vacation;
		}
		return "&nbsp;";
	}
}
