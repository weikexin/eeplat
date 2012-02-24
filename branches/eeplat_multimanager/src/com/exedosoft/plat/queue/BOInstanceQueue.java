package com.exedosoft.plat.queue;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.exedosoft.plat.bo.BOInstance;

/**
 * @author   IBM
 */
public class BOInstanceQueue implements IBOInstanceQueue {

	// private List<Message> messages;

	protected BlockingQueue<BOInstance> msgs;

	private static BOInstanceQueue aPool;

	public BOInstanceQueue() {
		msgs = new LinkedBlockingQueue<BOInstance>();
	}

	public synchronized static BOInstanceQueue getQueue() {
		if (aPool == null) {
			aPool = new BOInstanceQueue();
		}
		return aPool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.pool.AgentPool#addMessage(com.jbbis.hulft.agent.Message)
	 */
	public synchronized void putMessage(BOInstance aMessage) {
		if (aMessage == null) {
			return;
		}
		
	
	}
	
	public synchronized void putMessages(Collection messages) {
		if (messages == null) {
			return;
		}
		msgs.addAll(messages);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.pool.AgentPool#removeMessage(com.jbbis.hulft.agent.Message)
	 */
	public synchronized void removeMessage(BOInstance aMessage) {
		if (aMessage == null) {
			return;
		}
		msgs.remove(aMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.pool.AgentPool#getMessages()
	 */
	public BlockingQueue<BOInstance> getMessages() {
		return msgs;
	}

	public synchronized BOInstance takeMessage() {
		return msgs.poll();
	}

	public boolean isEmpty() {
		return msgs.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.MessageQueue#offerMessage(com.jbbis.hulft.agent.Message)
	 */
	public boolean offerMessage(BOInstance aMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.MessageQueue#pollMessage(long,
	 *      java.util.concurrent.TimeUnit)
	 */
	public BOInstance offerMessage(long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

}
