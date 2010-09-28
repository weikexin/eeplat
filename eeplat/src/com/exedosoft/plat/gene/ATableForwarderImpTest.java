package com.exedosoft.plat.gene;

import junit.framework.TestCase;

public class ATableForwarderImpTest extends TestCase {

	public ATableForwarderImpTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testForwarder(){
		
		ATableForwarderImp ai = new ATableForwarderImp("dm_log","297e276a0e125ee4010e126061b90006","297e276a0e3c7b6b010e3cab6f5e009d");
		forwardAI(ai);
		
		
		
		
		
		
		
	}
	
	private void forwardAI(ATableForwarderImp ai){
		
		ai.forwardDOBO();
		ai.forwardProperty();
		ai.forwardParameter();
		ai.forwardService();
		ai.forwardRule();
		ai.forwardUI();
		
	}

}
