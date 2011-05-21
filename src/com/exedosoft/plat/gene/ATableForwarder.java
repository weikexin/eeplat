package com.exedosoft.plat.gene;

public interface ATableForwarder {

	public void forwardDOBO();

	public void forwardProperty();

	/**
	 * forward Parameter
	 * 
	 */
	public void forwardParameter();

	/**
	 * 
	 * forward service and service-parameter links
	 * 
	 */

	public void forwardService();

	/**
	 * forward Rule and service-rule link
	 */

	public void forwardRule();

	public void forwardUI();
	
	public void forwardAll();


}
