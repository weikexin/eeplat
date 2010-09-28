package com.exedosoft.plat.agent;

/**
 * ??一个Command的命令。<br>
 * 一个Command的命令在后台?生一个Job.<br>
 * 一个Job 可能?有多个Task.<br>
 * 一个Job是一个?程。<br>
 * 多个Task是?序?行的。<br> 
 *
 * @author Administrator
 *
 */
public interface Job extends Runnable {
	
	/**
	 * ?取一个Job??的Tasks;
	 *
	 */
	java.util.List<Task> getTasks();
	
	void removeMessage();
	

}
