package com.exedosoft.plat.agent.config;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.agent.Task;
import com.exedosoft.plat.agent.task.SimpleTask;
import com.exedosoft.plat.agent.task.SimpleTask1;

/**
 * @author   Administrator
 */
public class TaskCommandMap {
	
	private String eventID;
	
	/**
	 * @uml.property  name="commandName"
	 */
	private String commandName;
	
	/**
	 * @uml.property  name="taskID"
	 */
	private String taskID;
	


	public String getCommandID() {
		return eventID;
	}

	public void setCommandID(String commandID) {
		this.eventID = commandID;
	}

	/**
	 * @return   Returns the commandName.
	 * @uml.property   name="commandName"
	 */
	public String getCommandName() {
		return commandName;
	}

	/**
	 * @param commandName   The commandName to set.
	 * @uml.property   name="commandName"
	 */
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	


	public synchronized static List<Task> getTaskConfigs(String commandID) {
		
		List<Task> list = new ArrayList<Task>();
		SimpleTask st = new SimpleTask();
		list.add(st);
		SimpleTask1 st1 = new SimpleTask1();
		list.add(st1);
		return list;		
	}

	/**
	 * @return   Returns the taskID.
	 * @uml.property   name="taskID"
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * @param taskID   The taskID to set.
	 * @uml.property   name="taskID"
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	 

}
