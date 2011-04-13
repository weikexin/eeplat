package com.exedosoft.plat.util;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.wf.wfi.NodeInstance;
import com.exedosoft.wf.wfi.ProcessInstance;

public class DEMONodeInstanceAuth extends DOAbstractAction {

	private static final long serialVersionUID = -1657838986411108134L;

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		
		BOInstance para = DOGlobals.getInstance().getRuleContext().getInstance();
		if(para.getValue("corr_nodeinstance")!=null){
			NodeInstance ni = (NodeInstance)para.getObjectValue("corr_nodeinstance");
			ProcessInstance pi = ni.getProcessInstance();
			
			///看提交人
			NodeInstance preConflict = ni.getNodeInstanceByPTNodeID(pi.getObjUid(), "tt2_n16", "" + NodeInstance.STATUS_FINISH);
			
			if(preConflict!=null){
				NodeInstance.storePersionAuth(ni.getObjUid(), preConflict.getPerformer(), Boolean.FALSE);
			}
			
		}
		
		
		return DEFAULT_FORWARD;
	}

}
