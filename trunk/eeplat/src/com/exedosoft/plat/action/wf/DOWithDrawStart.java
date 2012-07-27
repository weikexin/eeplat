package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.wf.WFEngine;
import com.exedosoft.wf.WFEngineFactory;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.pt.ProcessTemplate;
import com.exedosoft.wf.wfi.ProcessInstance;

public class DOWithDrawStart extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4731834707336161805L;

	public String excute() {

		if(service.getProcessTemplate()==null){
			this.setEchoValue(I18n.instance().get("服务未定义工作流模板"));
			return null;
		}
		ProcessTemplate pt = this.service.getProcessTemplate();	
		BOInstance bi = pt.getDoBO().getCorrInstance();
		String wfUid = bi.getValue("wf_uid");
		if(wfUid==null){
			wfUid = bi.getValue("wf_id");
		}
		WFEngine wfi = WFEngineFactory.getWFEngine();
		if(wfUid != null){
		
			ProcessInstance pi = null;
			try {
				pi = wfi.loadProcessInstance(wfUid);
				pi.withDrawStartNode();
			} catch (WFException e) {
				e.printStackTrace();
				this.setEchoValue(e.getLocalizedMessage());
				return NO_FORWARD;
			}
			if(pi==null){
				return NO_FORWARD;
			}
			
		}
		return DEFAULT_FORWARD;
	}

}
