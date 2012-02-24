package com.exedosoft.wf.wfi;

import junit.framework.TestCase;

import com.exedosoft.wf.WFException;
import com.exedosoft.wf.pt.ProcessTemplate;

public class WFEngineImplTest extends TestCase {

	/*
	 * Test method for 'com.exedosoft.wf.wfi.WFEngineImpl.startProcess(ProcessTemplate)'
	 */
	public void testStartProcess() {
		WFEngineImpl wfi = new WFEngineImpl();
		ProcessTemplate pt = ProcessTemplate.getPTByID("567888");
		try {
			wfi.startProcess(pt);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
