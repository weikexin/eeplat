package com.exedosoft.plat.agent.message;

import java.util.concurrent.LinkedBlockingQueue;

import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.config.HumletGlobals;



/**
 * @author   IBM
 */
public class InQueue  extends MSGAbstractQueue {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7519754722953274117L;
	private  static InQueue aPool;
	
	private InQueue(){
		msgs = new LinkedBlockingQueue<Message>(HumletGlobals.getInQueueMax());
	}
	
	public synchronized static InQueue getPool(){
		if(aPool==null){
			aPool = new InQueue();
		}		
		return aPool;
	}


	

}
