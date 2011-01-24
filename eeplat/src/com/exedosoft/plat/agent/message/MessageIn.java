package com.exedosoft.plat.agent.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.exedosoft.plat.agent.Message;
;

public class MessageIn implements Runnable {

	private Socket aSocket;

	public MessageIn(Socket ss) {

		aSocket = ss;
		System.out.println("愙庴?媮岪丆惓??堦槩JOB!");
	}

	public void run() {

		try {
			ObjectInputStream serverInputStream = new ObjectInputStream(aSocket
					.getInputStream());
			SimpleMessage sm = (SimpleMessage) serverInputStream.readObject();
			System.out.println("愙庴摓堦槩Message::");
			System.out.println(sm);
			
			if(sm.getMessageType()==Message.MSG_REQ){
				
				outMessage(sm.getCommandID());
				aSocket.close();
				return;
			}
			
			if (ProcessQueue.getPool().isEmpty()) {
				ProcessQueue.getPool().putMessage(sm);
			} else {
				InQueue.getPool().putMessage(sm);
			}
			
			if(sm.getMessageType()==Message.MSG_RES_REQ){
				sm.setASocket(aSocket);
			}else{
			  aSocket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void outMessage(String aCmdID){
		
		try {
			ObjectOutputStream serverOutputStream = new ObjectOutputStream(
					aSocket.getOutputStream());
			Message msg = OutPool.getPool().getAndRemoveMessage(aCmdID);
			serverOutputStream.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
