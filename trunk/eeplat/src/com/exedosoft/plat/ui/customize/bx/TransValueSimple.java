package com.exedosoft.plat.ui.customize.bx;

import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;

public class TransValueSimple extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel property) {
		// TODO Auto-generated method stub
		DOFormModel fm = (DOFormModel) property;
		String sn = fm.getData().getValue("sn");
		if (sn != null)
			if (!sn.trim().equals("")) {
				return LDAPPeopleUtil.getLDAPCNBySN(sn);
			}
		//工资条发送邮箱
		if(sn == null || sn.trim().equals(""))
			sn = fm.getData().getValue("toname");
		if (sn != null)
			if (!sn.trim().equals("")) {
				return LDAPPeopleUtil.getLDAPCNBySN(sn);
			}
		//项目
		if(sn == null || sn.trim().equals(""))
			sn = fm.getData().getValue("projectmanageruid");
		if (sn != null)
			if (!sn.trim().equals("")) {
				return LDAPPeopleUtil.getLDAPCNBySN(sn);
			}
		
		//客户
		if(sn == null || sn.trim().equals(""))
			sn = fm.getData().getValue("mainsaleruid");
		if (sn != null)
			if (!sn.trim().equals("")) {
				return LDAPPeopleUtil.getLDAPCNBySN(sn);
			}
		return "&nbsp;";
	}

}
