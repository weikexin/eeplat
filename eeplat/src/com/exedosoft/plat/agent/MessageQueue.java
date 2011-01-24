package com.exedosoft.plat.agent;

import java.util.concurrent.TimeUnit;

/**
 * 
 * 徚懅?楍丆曪妵揧壛徚懅丆堏彍徚懅摍曽朄丅
 * 
 * 徚懅?忢?棟揑峫?丠丠 惀漞弌?忢丆?????棟丅 ?惀?堦?棟?嵄?忢丆巈?峫?堦壓丅
 * 
 * 
 * @author Administrator
 * 
 */
public interface MessageQueue {
	

	/**
	 * 彨徚懅揧壛摓崯?楍拞丆擛壥杤桳壜梡嬻?丆彨堦捈摍懸丅
	 * 
	 * @param aMessage
	 *            徚懅
	 */
	public abstract void putMessage(Message aMessage);

	/**
	 * ?嶕涹堏彍崯?楍揑戞堦槩徚懅丆擛壥崯?楍晄懚嵼擟壗徚懅丆?堦捈摍懸
	 * 
	 * @return ?楍揑戞堦槩徚懅
	 */
	public abstract Message takeMessage();

	/**
	 * 擛壥壜擻揑?丆彨徚懅潎擖崯?楍拞丅
	 * 
	 * @param aMessage
	 *            徚懅
	 * @return 惉岟曉夞 true丆懘懠忣檝?曉夞 false
	 */
	public abstract boolean offerMessage(Message aMessage);

	/**
	 * 彨巜掕揑徚懅潎擖崯?楍拞丆擛壥杤桳壜梡嬻?丆彨摍懸巜掕揑摍懸??乮擛壥桳昁梫乯丅
	 * @param timeout
	 * @param unit
	 * @return
	 */	
	public abstract Message offerMessage(long timeout, TimeUnit unit);
	/**
	 * 樃?楍拞堏彍堦槩徚懅丅
	 * 
	 * @param aMessage
	 *            懸堏彍揑徚懅
	 */
	public abstract void removeMessage(Message aMessage);

	/**
	 * 敾抐?楍惀斲?嬻丅
	 * 
	 * @return ?嬻曉夞true,斲?曉夞false.
	 */
	public abstract boolean isEmpty();

}
