package com.exedosoft.wf.wfi;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;

import junit.framework.TestCase;

public class NodeInstanceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetNodeInstanceByID() {
		
		NodeInstance ni = NodeInstance.getNodeInstanceByID("40288a011410b8f30114117332710053");
		System.out.println(ni.isAccess());
		
		DOBO ptNI = DOBO.getDOBOByName("do_wfi_nodeinstance");
		BOInstance aNI = ptNI.getInstance("40288a011410b8f30114110e664e004a");
		System.out.println(aNI);
		//fail("Not yet implemented");
	}

}
