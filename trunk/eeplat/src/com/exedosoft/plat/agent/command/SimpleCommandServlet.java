package com.exedosoft.plat.agent.command;

import java.io.IOException;
import java.net.ServerSocket;

import com.exedosoft.plat.agent.CommandServlet;
import com.exedosoft.plat.agent.config.HumletGlobals;
import com.exedosoft.plat.agent.message.MessageIn;
/**
 * CommandServlet揑銥徣??丅
 * 
 * @author Administrator
 * 
 */
public class SimpleCommandServlet implements CommandServlet {

	private ServerSocket ss;

	private boolean listening = true;

	public SimpleCommandServlet() {
		
		System.out.println("銥徣CommandServlet惓嵼??......");
		Init();// 弶巒壔
		Listen();// ?欉
	}

	public void Init() {
		try {
			
			ss = new ServerSocket(HumletGlobals.getPort(), HumletGlobals.getCmdSvrBlockMax());
			System.out.println("銥徣CommandServlet弶巒壔姰惉......");

		} catch (IOException ie) {
			System.out.println("澷朄嵼抂岥丗"+ HumletGlobals.getPort() + "丂?欉");
			ie.printStackTrace();
		}
	}

	public void Listen() {
		try {
			
			while (listening){
				System.out.println("銥徣CommandServlet惓嵼?欉......");
				new Thread(new MessageIn(ss.accept())).start();
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new SimpleCommandServlet();
	}

}
