package com.exedosoft.plat.util.xml;

import java.net.URL;



import junit.framework.TestCase;

/**
 * @author   IBM
 */
public class SimpleXMLUtilTest extends TestCase {
	
	SimpleXMLUtil xmlUtil =  null;	
	protected void setUp() throws Exception {
		
        URL url =  SimpleXMLUtilTest.class.getResource("inshib.cfg.xml");
		String file = url.getFile();
		System.out.println(file);
		xmlUtil = new SimpleXMLUtil(file);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'com.exedosoft.plat.util.XMLProperties.getProperty(String)'
	 */
	public void testGetProperty() {
		
		System.out.println(xmlUtil.getProperty("connection.driver_class"));
	}

}
