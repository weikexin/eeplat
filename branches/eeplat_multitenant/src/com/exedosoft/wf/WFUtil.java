package com.exedosoft.wf;

import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.wf.wfi.ProcessInstance;

public class WFUtil {

	public WFUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void refreshWFPara(ProcessInstance pi) {
	
			DOBO piBO = DOBO.getDOBOByName("do_wfi_processinstance");
			piBO.refreshContext(pi.getObjUid());
			DOBO busiBO = pi.getProcessTemplate().getDoBO();
			if (busiBO != null) {
				if (busiBO != null) {
					busiBO.refreshContext(pi.getInstanceUid());
				}
			}
	
			DOBO busiBO2 = pi.getProcessTemplate().getDoBO2();
			if (busiBO2 != null) {
				if (busiBO2 != null) {
					busiBO2.refreshContext(pi.getInstanceUid2());
				}
			}
	
			DOBO busiBO3 = pi.getProcessTemplate().getDoBO3();
			if (busiBO3 != null) {
				if (busiBO3 != null) {
					busiBO3.refreshContext(pi.getInstanceUid3());
				}
			}
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
