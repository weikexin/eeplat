package com.exedosoft.plat.agent;

import java.util.concurrent.TimeUnit;

/**
 * 
 * 消息?列，包括添加消息，移除消息等方法。
 * 
 * 消息?常?理的考?？？ 是抛出?常，?????理。 ?是?一?理?些?常，仔?考?一下。
 * 
 * 
 * @author Administrator
 * 
 */
public interface MessageQueue {
	

	/**
	 * 将消息添加到此?列中，如果没有可用空?，将一直等待。
	 * 
	 * @param aMessage
	 *            消息
	 */
	public abstract void putMessage(Message aMessage);

	/**
	 * ?索并移除此?列的第一个消息，如果此?列不存在任何消息，?一直等待
	 * 
	 * @return ?列的第一个消息
	 */
	public abstract Message takeMessage();

	/**
	 * 如果可能的?，将消息插入此?列中。
	 * 
	 * @param aMessage
	 *            消息
	 * @return 成功返回 true，其他情况?返回 false
	 */
	public abstract boolean offerMessage(Message aMessage);

	/**
	 * 将指定的消息插入此?列中，如果没有可用空?，将等待指定的等待??（如果有必要）。
	 * @param timeout
	 * @param unit
	 * @return
	 */	
	public abstract Message offerMessage(long timeout, TimeUnit unit);
	/**
	 * 从?列中移除一个消息。
	 * 
	 * @param aMessage
	 *            待移除的消息
	 */
	public abstract void removeMessage(Message aMessage);

	/**
	 * 判断?列是否?空。
	 * 
	 * @return ?空返回true,否?返回false.
	 */
	public abstract boolean isEmpty();

}
