package com.exedosoft.plat.agent.command.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.message.SimpleMessage;

public class MessageCommand extends TCPCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2014609221966451652L;

	
	public String getCommandID() {
		// TODO Auto-generated method stub
		return "aMessage";
	}

	
	@Override
	public Message excute(Message aMsg) {

		
		Message aMessage = null;
		try {
			ObjectOutputStream serverOutputStream = new ObjectOutputStream(
					getSocket().getOutputStream());
			System.out.println("Client 正在?送消息。。。。。");
			serverOutputStream.writeObject(aMsg);
			System.out.println("Client 消息??完?！！");
			
			if(aMsg.getMessageType() != Message.MSG_RES){
			  aMessage = this.getInMessage();
			}


		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				getSocket().close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return aMessage;

	}

	
	private Message getInMessage(){
		
		SimpleMessage sm = null;
		try {
			ObjectInputStream serverInputStream = new ObjectInputStream(getSocket()
					.getInputStream());
			sm = (SimpleMessage) serverInputStream.readObject();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sm;

		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MessageCommand mc = new MessageCommand();
		
		SimpleMessage sm = SimpleMessage.wrapperSimpeMessage();
		sm.setCommndID(mc.getCommandID());
		
		Message aMsg = mc.excute(sm);
		System.out.println("收到反?消息。。。。。。。。。。");
		System.out.println(aMsg);

		
		MessageCommand mc1 = new MessageCommand();
		SimpleMessage sm1 = SimpleMessage.wrapperSimpeMessage();
		sm1.setCommndID(mc1.getCommandID());
		sm1.setMessageType(Message.MSG_RES);
		Message aMsg1 = mc1.excute(sm1);
		System.out.println("收到反?消息。。。。。。。。。。");
		System.out.println(aMsg1);


	}

}
