package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.MVCController;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.WFUtil;
import com.exedosoft.wf.wfi.NodeInstance;
public class DOBackToStart extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5700698860853309182L;

	public String excute() {
		
		DOBO ptNI = DOBO.getDOBOByName("do_wfi_nodeinstance");
		if (ptNI.getCorrInstance() == null) {
			
			this.setEchoValue(I18n.instance().get("当前工作流上下文丢失,请重新操作!"));
			return NO_FORWARD;
		}
		NodeInstance ni = NodeInstance.getNodeInstanceByID(ptNI
				.getCorrInstance().getUid());
		WFUtil.refreshWFPara(ni.getProcessInstance());
		try {
			this.service.invokeUpdate();
		} catch (ExedoException e1) {
			this.setEchoValue(e1.getMessage());
			return NO_FORWARD;
		}

		try {
			ni.backToStart();
		} catch (WFException e) {
			this.setEchoValue(e.getMessage());
			return NO_FORWARD;
		}
		return DEFAULT_FORWARD;
	}

}
