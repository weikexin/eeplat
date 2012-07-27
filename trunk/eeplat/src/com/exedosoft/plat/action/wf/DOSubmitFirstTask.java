package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.MVCController;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.wf.WFEngine;
import com.exedosoft.wf.WFEngineFactory;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.WFUtil;
import com.exedosoft.wf.wfi.ProcessInstance;

public class DOSubmitFirstTask extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566742608663452803L;

	@Override
	public String excute() throws ExedoException {

		DOBO ptPI = DOBO.getDOBOByName("do_wfi_processinstance");//do.wfi.processinstance
		System.out.println("ptPI=========" +  ptPI.getCorrInstance());
		
		if (ptPI.getCorrInstance() == null) {
			this.setEchoValue(I18n.instance().get("当前工作流上下文丢失,请重新操作!"));
			return null;
		}
		
		WFEngine  engine = WFEngineFactory.getWFEngine();
		
		ProcessInstance pi = null;
		try {
			pi = engine.loadProcessInstance(ptPI.getCorrInstance().getUid());
		} catch (WFException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		WFUtil.refreshWFPara(pi);
		try {
			this.service.invokeUpdate();
		} catch (ExedoException e1) {
			e1.printStackTrace();
			this.setEchoValue(e1.getLocalizedMessage());
			return NO_FORWARD;
		}

		try {
			pi.getFirstActivityNode().perform();
		} catch (WFException e) {
			this.setEchoValue(e.getLocalizedMessage());
			return NO_FORWARD;
		}
		return DEFAULT_FORWARD;
	}

}
