package com.exedosoft.plat.agent.config;

/**
 * @author   Administrator
 */
public class TaskConfig {
	
	/**
	 * @uml.property  name="taskID"
	 */
	private String taskID;
	
	/**
	 * @uml.property  name="taskName"
	 */
	private String taskName;
	
	/**
	 * @uml.property  name="taskClass"
	 */
	private String taskClass;

	/**
	 * @return   Returns the taskClass.
	 * @uml.property   name="taskClass"
	 */
	public String getTaskClass() {
		return taskClass;
	}

	/**
	 * @param taskClass   The taskClass to set.
	 * @uml.property   name="taskClass"
	 */
	public void setTaskClass(String taskClass) {
		this.taskClass = taskClass;
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

	/**
	 * @return   Returns the taskName.
	 * @uml.property   name="taskName"
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName   The taskName to set.
	 * @uml.property   name="taskName"
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String toString(){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nTaskID: " + this.getTaskID());
		buffer.append("\nTaskName: " + this.getTaskName())
		.append("\nTaskClass:" + this.getTaskClass());
		return buffer.toString();
		
	}
	

}
