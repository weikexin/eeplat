package com.exedosoft.plat.agent;

import java.util.Collection;

/**
 * ?个客?端?用?行一个Command.
 * Command向Agent?送消息。
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
