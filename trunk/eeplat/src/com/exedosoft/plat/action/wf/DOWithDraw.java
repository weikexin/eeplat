package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.wfi.NodeInstance;

public class DOWithDraw extends DOAbstractAction {

	private static final long serialVersionUID = -7288193881377838285L;

	public String excute() {
		DOBO ptNI = DOBO.getDOBOByName("do_wfi_nodeinstance");
		if (ptNI.getCorrInstance() == null) {
			this.setEchoValue(I18n.instance().get("当前工作流上下文丢失,请重新操作!"));
			return null;
		}
		NodeInstance ni = NodeInstance.getNodeInstanceByID(ptNI
				.getCorrInstance().getUid());
		try {
			ni.withDraw();
		} catch (WFException e) {
			this.setEchoValue(e.getMessage());
			return NO_FORWARD;
		}
		return DEFAULT_FORWARD;
	}

}
