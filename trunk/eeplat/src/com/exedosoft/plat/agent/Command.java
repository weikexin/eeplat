package com.exedosoft.plat.agent;

import java.util.Collection;

/**
 * ?槩媞?抂?梡?峴堦槩Command.
 * Command岦Agent?憲徚懅丅
 * 
 * @author Administrator
 *
 */
public interface Command {
	
	Message  excute(Message aMsg);
	
    String getCommandID();

	/**
	 */
	public abstract Collection getEvents();

}
