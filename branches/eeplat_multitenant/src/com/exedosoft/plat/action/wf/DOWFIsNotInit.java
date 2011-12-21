package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.DOAccess;
import com.exedosoft.plat.bo.BOInstance;

public class DOWFIsNotInit  {
	public boolean isAccess(BOInstance aBI) {
		System.out.println("A Instance" + aBI);
		System.out.println(aBI.getValue("node_uid")+"\\\\\\\\\\\\\\\\\\");
		if ("2".equals(aBI.getValue("ExeStatus"))) {
			return false;

		}
		// TODO Auto-generated method stub
		return true;	
		}
}
