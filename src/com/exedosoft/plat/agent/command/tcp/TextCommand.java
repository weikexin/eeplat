package com.exedosoft.plat.agent.command.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.config.HumletGlobals;

/**
 * ????暥杮揑Command
 * 
 * @author Administrator
 * 
 */
public class TextCommand extends TCPCommand {




	public TextCommand() {
	
	}

	public Message excute(Message aMsg) {
		
		String sen;
		byte b[];
		try {

			b = new byte[1024];
			OutputStream out =  getSocket().getOutputStream();
			sen = "Hello world両  廐揤恀揑渒旤岲";
			b = sen.getBytes();
			System.out.println("媞?抂??丆涹???揑帤晞孁両");
			out.write(b);
			out.flush();
			out.close();
			getSocket().close();
		} catch (IOException ie) {
			ie.toString();
		}
		return aMsg;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TextCommand tc = new TextCommand();
		tc.excute(null);

	}

	public String getCommandID() {
		// TODO Auto-generated method stub
		return "aTest";
	}
}
