package com.exedosoft.plat.queue;

import java.util.concurrent.TimeUnit;

import com.exedosoft.plat.bo.BOInstance;



public interface IBOInstanceQueue {

	/**
	 * 彨徚懅揧壛摓崯?楍拞丆擛壥杤桳壜梡嬻?丆彨堦捈摍懸丅
	 * 
	 * @param aMessage
	 *            徚懅
	 */
	public abstract void putMessage(BOInstance aMessage);

	/**
	 * ?嶕涹堏彍崯?楍揑戞堦槩徚懅丆擛壥崯?楍晄懚嵼擟壗徚懅丆?堦捈摍懸
	 * 
	 * @return ?楍揑戞堦槩徚懅
	 */
	public abstract BOInstance takeMessage();

	/**
	 * 擛壥壜擻揑?丆彨徚懅潎擖崯?楍拞丅
	 * 
	 * @param aMessage
	 *            徚懅
	 * @return 惉岟曉夞 true丆懘懠忣檝?曉夞 false
	 */
	public abstract boolean offerMessage(BOInstance aMessage);

	/**
	 * 彨巜掕揑徚懅潎擖崯?楍拞丆擛壥杤桳壜梡嬻?丆彨摍懸巜掕揑摍懸??乮擛壥桳昁梫乯丅
	 * 
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public abstract BOInstance offerMessage(long timeout, TimeUnit unit);

	/**
	 * 樃?楍拞堏彍堦槩徚懅丅
	 * 
	 * @param aMessage
	 *            懸堏彍揑徚懅
	 */
	public abstract void removeMessage(BOInstance aMessage);

	/**
	 * 敾抐?楍惀斲?嬻丅
	 * 
	 * @return ?嬻曉夞true,斲?曉夞false.
	 */
	public abstract boolean isEmpty();

}
