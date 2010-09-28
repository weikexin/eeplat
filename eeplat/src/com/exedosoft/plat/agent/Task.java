package com.exedosoft.plat.agent;

/**
 * 
 * @author Administrator
 *
 */
public interface Task {
	
	Message perform(Message aMessage) throws Exception;
	
	Message rollBack(Message aMessage);
	
	
}
