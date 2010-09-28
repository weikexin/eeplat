package com.exedosoft.plat.agent.message;

import java.net.Socket;

import com.exedosoft.plat.agent.Message;


/**
 * @author   Administrator
 */
public class SimpleMessage implements Message{

	private static final long serialVersionUID = 2846707868637918747L;

	/**
	 * @uml.property  name="messageID"
	 */
	private String messageID;
	
	/**
	 * @uml.property  name="messageName"
	 */
	private String messageName;

	/**
	 * @uml.property  name="sourceHost"
	 */
	private String sourceHost;
	
	/**
	 * @uml.property  name="targetHost"
	 */
	private String targetHost;
	
	/**
	 * @uml.property  name="messageType"
	 */
	private int messageType;
		
	/**
	 * @uml.property  name="messsgeContext"
	 */
	private String messsgeContext;
	
	/**
	 * @uml.property  name="commandID"
	 */
	private String commandID;
	
	/**
	 * @uml.property  name="aSocket"
	 */
	private transient Socket aSocket;
	

	/**
	 * @return   Returns the messageID.
	 * @uml.property   name="messageID"
	 */
	public String getMessageID() {
		return messageID;
	}

	/**
	 * @param messageID   The messageID to set.
	 * @uml.property   name="messageID"
	 */
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	/**
	 * @return   Returns the messageName.
	 * @uml.property   name="messageName"
	 */
	public String getMessageName() {
		return messageName;
	}

	/**
	 * @param messageName   The messageName to set.
	 * @uml.property   name="messageName"
	 */
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	/**
	 * @return   Returns the messageType.
	 * @uml.property   name="messageType"
	 */
	public int getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType   The messageType to set.
	 * @uml.property   name="messageType"
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return   Returns the messsgeContext.
	 * @uml.property   name="messsgeContext"
	 */
	public String getMesssgeContext() {
		return messsgeContext;
	}

	/**
	 * @param messsgeContext   The messsgeContext to set.
	 * @uml.property   name="messsgeContext"
	 */
	public void setMesssgeContext(String messsgeContext) {
		this.messsgeContext = messsgeContext;
	}

	/**
	 * @return   Returns the sourceHost.
	 * @uml.property   name="sourceHost"
	 */
	public String getSourceHost() {
		return sourceHost;
	}

	/**
	 * @param sourceHost   The sourceHost to set.
	 * @uml.property   name="sourceHost"
	 */
	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}

	/**
	 * @return   Returns the targetHost.
	 * @uml.property   name="targetHost"
	 */
	public String getTargetHost() {
		return targetHost;
	}

	/**
	 * @param targetHost   The targetHost to set.
	 * @uml.property   name="targetHost"
	 */
	public  void setTargetHost(String targetHost) {
		this.targetHost = targetHost;
	}

	
	public static SimpleMessage wrapperSimpeMessage(){
		
		SimpleMessage sm = new SimpleMessage();
		sm.setMessageID("test");
		sm.setMessageName("test");
		sm.setMesssgeContext("only test,hello world!");
		sm.setMessageType(Message.MSG_RES_REQ);
		return sm;
	}
	

	/**
	 * @return   Returns the commandID.
	 * @uml.property   name="commandID"
	 */
	public String getCommandID() {
		return commandID;
	}

	public void setCommndID(String commandID) {
		this.commandID = commandID;
	}

	public String getEventID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * @return   Returns the aSocket.
	 * @uml.property   name="aSocket"
	 */
	public Socket getASocket() {
		return aSocket;
	}

	/**
	 * @param aSocket   The aSocket to set.
	 * @uml.property   name="aSocket"
	 */
	public void setASocket(Socket socket) {
		aSocket = socket;
	}
	
	public String toString(){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Message Details:::\n")
		.append("ID:")
		.append(this.getMessageID())
		.append("\nName:")
		.append(this.getMessageName())
		.append("\nType:")
		.append(this.getMessageType())
		.append("\nContext:")
		.append(this.getMesssgeContext());
		
		return buffer.toString();
	}

	

}
