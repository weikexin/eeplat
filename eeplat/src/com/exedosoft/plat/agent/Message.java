package com.exedosoft.plat.agent;
import java.io.Serializable;

public interface Message  extends Serializable{
	
	/**
	 * 同??求?答
	 */
	public static  int MSG_RES_REQ = 1;
	
	/**
	 * 广播，不要求回答
	 */
	public static  int MSG_RES = 2;
	
	/**
	 *消息没有??Task,知?要取Messages
	 *只要求回答
	 * @return
	 */
	
	public static int MSG_REQ = 3;
	
	
	
	
	

	public String getMessageID();
	
	
	public int getMessageType();
	
	/**
	 * 
	 * 当Command ?送Message ?，需要Command??自己，??
	 * 服?器端解析Message 消息?，才能?索到??的Tasks
	 * 
	 * @return
	 */

	public String getCommandID();


	/**
	 */
	public abstract String getEventID();
	

	
	
}
