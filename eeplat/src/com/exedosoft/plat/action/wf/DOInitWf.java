package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.wf.WFEngine;
import com.exedosoft.wf.WFEngineFactory;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.pt.ProcessTemplate;
import com.exedosoft.wf.wfi.ProcessInstance;

public class DOInitWf extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8581592331861604274L;

	public String excute() {
		if (service.getProcessTemplate() == null) {
			this.setEchoValue(I18n.instance().get("服务未定义工作流模板"));
			return null;
		}
		try {
			service.invokeUpdate();
		} catch (ExedoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ProcessTemplate pt = this.service.getProcessTemplate();
		BOInstance corrInstance = pt.getDoBO().getCorrInstance();
		if ( !ProcessInstance.isExistsOfInstanceUid(corrInstance.getUid())) {

			WFEngine wfi = WFEngineFactory.getWFEngine();

			try {

				wfi.initProcess(pt);
			} catch (WFException e) {
				e.printStackTrace();
				this.setEchoValue(e.getLocalizedMessage());
				return NO_FORWARD;
			}
		}
		return DEFAULT_FORWARD;
	}

}
