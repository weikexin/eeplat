package com.exedosoft.plat.agent;

/**
 * ??堦槩Command揑柦椷丅<br>
 * 堦槩Command揑柦椷嵼岪戜?惗堦槩Job.<br>
 * 堦槩Job 壜擻?桳懡槩Task.<br>
 * 堦槩Job惀堦槩?掱丅<br>
 * 懡槩Task惀?彉?峴揑丅<br> 
 *
 * @author Administrator
 *
 */
public interface Job extends Runnable {
	
	/**
	 * ?庢堦槩Job??揑Tasks;
	 *
	 */
	java.util.List<Task> getTasks();
	
	void removeMessage();
	

}
