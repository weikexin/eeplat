package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.DOAccess;
import com.exedosoft.plat.bo.BOInstance;

public class DOWFIsInit implements DOAccess {

	public boolean isAccess(BOInstance aBI) {
		
		System.out.println("A Instance" + aBI);

		if ("1".equals(aBI.getValue("ExeStatus"))) {
			return true;

		}
		// TODO Auto-generated method stub
		return false;
	}

}
