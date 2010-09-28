package com.exedosoft.plat.queue;

import java.util.concurrent.TimeUnit;

import com.exedosoft.plat.bo.BOInstance;



public interface IBOInstanceQueue {

	/**
	 * 将消息添加到此?列中，如果没有可用空?，将一直等待。
	 * 
	 * @param aMessage
	 *            消息
	 */
	public abstract void putMessage(BOInstance aMessage);

	/**
	 * ?索并移除此?列的第一个消息，如果此?列不存在任何消息，?一直等待
	 * 
	 * @return ?列的第一个消息
	 */
	public abstract BOInstance takeMessage();

	/**
	 * 如果可能的?，将消息插入此?列中。
	 * 
	 * @param aMessage
	 *            消息
	 * @return 成功返回 true，其他情况?返回 false
	 */
	public abstract boolean offerMessage(BOInstance aMessage);

	/**
	 * 将指定的消息插入此?列中，如果没有可用空?，将等待指定的等待??（如果有必要）。
	 * 
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public abstract BOInstance offerMessage(long timeout, TimeUnit unit);

	/**
	 * 从?列中移除一个消息。
	 * 
	 * @param aMessage
	 *            待移除的消息
	 */
	public abstract void removeMessage(BOInstance aMessage);

	/**
	 * 判断?列是否?空。
	 * 
	 * @return ?空返回true,否?返回false.
	 */
	public abstract boolean isEmpty();

}
