package com.exedosoft.wf.pt;

import junit.framework.TestCase;

public class ProcessTemplateTest extends TestCase {

	/*
	 * Test method for 'com.exedosoft.wf.pt.ProcessTemplate.getPTByName(String)'
	 */
	public void testGetPTByName() {
		
		ProcessTemplate pt = ProcessTemplate.getPTByName("test1111");
		System.out.println(pt);
		
		System.out.println(pt.retrieveNodes());

		

	}

}
