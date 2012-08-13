package com.exedosoft.plat.agent.message;

import java.util.concurrent.LinkedBlockingQueue;

import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.config.HumletGlobals;
import com.exedosoft.plat.agent.job.MessageJob;


/**
 * @author   IBM
 */
public class ProcessQueue extends MSGAbstractQueue {

	private static ProcessQueue aPool;

	private ProcessQueue() {
		msgs = new LinkedBlockingQueue<Message>(HumletGlobals.getProcessMax());

	}

	public synchronized static ProcessQueue getPool() {
		if (aPool == null) {
			aPool = new ProcessQueue();
		}
		return aPool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jbbis.hulft.agent.pool.AgentPool#addMessage(com.jbbis.hulft.agent.Message)
	 */
	public synchronized void putMessage(Message aMessage) {
		super.putMessage(aMessage);
		new Thread(new MessageJob(aMessage)).start();

	}

	public synchronized void removeMessage(Message aMessage) {

		super.removeMessage(aMessage);
		int needMessage = HumletGlobals.getProcessMax()
				- this.getMessages().size();
		if (needMessage > 0) {
			for (int i = 0; i < needMessage; i++) {
				if (!InQueue.getPool().isEmpty()) {
					Message pollMessage = InQueue.getPool().takeMessage();
					putMessage(pollMessage);
				}else{
					break;
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
