package com.exedosoft.plat.agent.message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.MessageQueue;

public abstract class MSGAbstractQueue implements MessageQueue {

	
	//private List<Message>  messages;
	
	protected BlockingQueue<Message> msgs;
	
	public  MSGAbstractQueue(){
    }
	
	
	/* (non-Javadoc)
	 * @see com.jbbis.hulft.agent.pool.AgentPool#addMessage(com.jbbis.hulft.agent.Message)
	 */
	public synchronized void putMessage(Message aMessage){
		if(aMessage==null){
			return;
		}
		msgs.add(aMessage);

	}
	

	/* (non-Javadoc)
	 * @see com.jbbis.hulft.agent.pool.AgentPool#removeMessage(com.jbbis.hulft.agent.Message)
	 */
	public synchronized void removeMessage(Message aMessage){
		if(aMessage==null){
			return;
		}
		msgs.remove(aMessage);		
	}
	
	/* (non-Javadoc)
	 * @see com.jbbis.hulft.agent.pool.AgentPool#getMessages()
	 */
	public BlockingQueue<Message> getMessages(){
		return msgs;
	}
	
	public synchronized Message  takeMessage(){
		return msgs.poll();
	}
	
	public boolean isEmpty(){
		return msgs.isEmpty();
	}


	/* (non-Javadoc)
	 * @see com.jbbis.hulft.agent.MessageQueue#offerMessage(com.jbbis.hulft.agent.Message)
	 */
	public boolean offerMessage(Message aMessage) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see com.jbbis.hulft.agent.MessageQueue#pollMessage(long, java.util.concurrent.TimeUnit)
	 */
	public Message offerMessage(long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}


}
