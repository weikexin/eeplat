package com.exedosoft.plat.agent;
import java.io.Serializable;

public interface Message  extends Serializable{
	
	/**
	 * 摨??媮?摎
	 */
	public static  int MSG_RES_REQ = 1;
	
	/**
	 * 涼攄丆晄梫媮夞摎
	 */
	public static  int MSG_RES = 2;
	
	/**
	 *徚懅杤桳??Task,抦?梫庢Messages
	 *扅梫媮夞摎
	 * @return
	 */
	
	public static int MSG_REQ = 3;
	
	
	
	
	

	public String getMessageID();
	
	
	public int getMessageType();
	
	/**
	 * 
	 * 摉Command ?憲Message ?丆廀梫Command??帺屓丆??
	 * 暈?婍抂夝愅Message 徚懅?丆嵥擻?嶕摓??揑Tasks
	 * 
	 * @return
	 */

	public String getCommandID();


	/**
	 */
	public abstract String getEventID();
	

	
	
}
