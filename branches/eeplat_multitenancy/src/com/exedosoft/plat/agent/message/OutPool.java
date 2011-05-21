package com.exedosoft.plat.agent.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.agent.Message;

/**
 * @author   IBM
 */
public class OutPool {

	private static OutPool aPool;

	private static Map<String,Message> msgs;

	private OutPool() {
		
 		 msgs = new HashMap<String,Message>();
		 Collections.synchronizedMap(msgs);

	}

	public synchronized static OutPool getPool() {
		if (aPool == null) {
			aPool = new OutPool();
		}
		return aPool;
	}

	public synchronized Message getMessage(String cmdID) {
		
		if (cmdID == null) {
			return null;
		}
		Message msg = msgs.get(cmdID);
	//	msgs.remove(cmdID);
		return msg;
	}

	
	public synchronized Message getAndRemoveMessage(String cmdID) {
		
		if (cmdID == null) {
			return null;
		}
		Message msg = msgs.get(cmdID);
		msgs.remove(cmdID);
		return msg;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.pool.AgentPool#addMessage(com.jbbis.hulft.agent.Message)
	 */
	public synchronized void addMessage(String cmdID,Message aMessage) {
		if (aMessage == null) {
			return;
		}
		msgs.put(cmdID,aMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.pool.AgentPool#removeMessage(com.jbbis.hulft.agent.Message)
	 */
	public synchronized void removeMessage(String cmdID) {
		if (cmdID == null) {
			return;
		}
		msgs.remove(cmdID);
	}

	
	public boolean isEmpty() {
		return msgs.isEmpty();
	}

	

}
