package com.exedosoft.plat.action.ldap;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.util.DOGlobals;

public class InsertZf_employeeByLdap extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		LDAPPeopleUtil.insertOrupdateZf_employee();
		BOInstance bi = new BOInstance();
		bi.putValue("none", "none");
		this.setInstance(bi);
		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {
		
	}

}
