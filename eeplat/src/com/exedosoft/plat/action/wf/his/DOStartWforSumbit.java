package com.exedosoft.plat.action.wf.his;

import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.wf.WFEngine;
import com.exedosoft.wf.WFEngineFactory;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.pt.ProcessTemplate;
import com.exedosoft.wf.wfi.NodeInstance;
import com.exedosoft.wf.wfi.ProcessInstance;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.I18n;

public class DOStartWforSumbit extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4748534122977819475L;

	public String excute() throws ExedoException {

		WFEngine wfi = WFEngineFactory.getWFEngine();
		if (service.getProcessTemplate() == null) {
			this.setEchoValue(I18n.instance().get("服务未定义工作流模板"));
			return null;
		}

		service.invokeUpdate();

		ProcessTemplate pt = this.service.getProcessTemplate();

		String wfUid = null;

		wfUid = getWfUid(pt, wfUid);

		if (wfUid == null) {
			DOService findPI = DOService
					.getService("do.wfi.processinstance.browse.findbyinstanceUid");
			if (findPI != null) {
				String curInstnaceUid = pt.getDoBO().getCorrInstance().getUid();
				List list = findPI.invokeSelect(pt.getObjUid(), curInstnaceUid);
				if (list != null && list.size() > 0) {
					BOInstance boPI = (BOInstance) list.get(0);
					wfUid = boPI.getUid();
				}
			}
		}

		// do.wfi.processinstance.browse.findbyinstanceUid
		// if(wfuid==null && pt.getDoBO2()!=null && pt.getDo)

		if (wfUid == null) {
			try {
				wfi.startProcess(pt);
			} catch (WFException e) {
				e.printStackTrace();
				this.setEchoValue(e.getLocalizedMessage());
				return NO_FORWARD;
			}
		} else {
			ProcessInstance pi = null;
			try {
				pi = wfi.loadProcessInstance(wfUid);
				NodeInstance ni = pi.getFirstActivityNode();
				ni.perform();
			} catch (WFException e) {
				e.printStackTrace();
				this.setEchoValue(e.getLocalizedMessage());
				return NO_FORWARD;
			}
			if (pi == null) {
				return NO_FORWARD;
			}

		}

		return DEFAULT_FORWARD;
	}

	public String getWfUid(ProcessTemplate pt, String wfUid) {

		if (pt.getDoBO() != null && pt.getDoBO().getCorrInstance() != null) {
			wfUid = pt.getDoBO().getCorrInstance().getValue("wf_uid");
			if (wfUid == null) {
				wfUid = pt.getDoBO().getCorrInstance().getValue("wf_id");
			}
		}

		if (wfUid == null && pt.getDoBO2() != null
				&& pt.getDoBO2().getCorrInstance() != null) {
			wfUid = pt.getDoBO2().getCorrInstance().getValue("wf_uid");
			if (wfUid == null) {
				wfUid = pt.getDoBO2().getCorrInstance().getValue("wf_id");
			}
		}

		if (wfUid == null && pt.getDoBO3() != null
				&& pt.getDoBO3().getCorrInstance() != null) {
			wfUid = pt.getDoBO3().getCorrInstance().getValue("wf_uid");
			if (wfUid == null) {
				wfUid = pt.getDoBO3().getCorrInstance().getValue("wf_id");
			}
		}

		return wfUid;
	}

}
