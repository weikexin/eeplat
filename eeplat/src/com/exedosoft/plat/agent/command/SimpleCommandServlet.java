package com.exedosoft.plat.agent.command;

import java.io.IOException;
import java.net.ServerSocket;

import com.exedosoft.plat.agent.CommandServlet;
import com.exedosoft.plat.agent.config.HumletGlobals;
import com.exedosoft.plat.agent.message.MessageIn;
/**
 * CommandServlet的缺省??。
 * 
 * @author Administrator
 * 
 */
public class SimpleCommandServlet implements CommandServlet {

	private ServerSocket ss;

	private boolean listening = true;

	public SimpleCommandServlet() {
		
		System.out.println("缺省CommandServlet正在??......");
		Init();// 初始化
		Listen();// ?听
	}

	public void Init() {
		try {
			
			ss = new ServerSocket(HumletGlobals.getPort(), HumletGlobals.getCmdSvrBlockMax());
			System.out.println("缺省CommandServlet初始化完成......");

		} catch (IOException ie) {
			System.out.println("无法在端口："+ HumletGlobals.getPort() + "　?听");
			ie.printStackTrace();
		}
	}

	public void Listen() {
		try {
			
			while (listening){
				System.out.println("缺省CommandServlet正在?听......");
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
