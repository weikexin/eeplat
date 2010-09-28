package com.exedosoft.plat.agent.command.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.config.HumletGlobals;

/**
 * ????文本的Command
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
			sen = "Hello world！  秋天真的很美好";
			b = sen.getBytes();
			System.out.println("客?端??，并???的字符串！");
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
