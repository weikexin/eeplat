package com.exedosoft.plat.agent.command.tcp;

import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.message.SimpleMessage;

import junit.framework.TestCase;

public class MessageCommandTest extends TestCase {

	public MessageCommandTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'com.jbbis.hulft.agent.command.tcp.MessageCommand.excute(Message)'
	 */
	public void testExcute() {
		MessageCommand mc1 = new MessageCommand();
		SimpleMessage sm1 = SimpleMessage.wrapperSimpeMessage();
		sm1.setCommndID(mc1.getCommandID());
		sm1.setMessageType(Message.MSG_REQ);
		Message aMsg1 = mc1.excute(sm1);
		System.out.println("收到反?消息。。。。。。。。。。");
		System.out.println(aMsg1);

		

	}

}
