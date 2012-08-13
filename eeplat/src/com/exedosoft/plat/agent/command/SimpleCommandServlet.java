package com.exedosoft.plat.agent.command;

import java.io.IOException;
import java.net.ServerSocket;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.agent.CommandServlet;
import com.exedosoft.plat.agent.config.HumletGlobals;
import com.exedosoft.plat.agent.message.MessageIn;
import com.exedosoft.plat.bo.DOResource;
/**
 * CommandServlet
 * 
 * @author Administrator
 * 
 */
public class SimpleCommandServlet implements CommandServlet {

	private ServerSocket ss;

	private boolean listening = true;

	public SimpleCommandServlet() {
		
		Init();
		Listen();
	}

	public void Init() {
		try {
			
			ss = new ServerSocket(HumletGlobals.getPort(), HumletGlobals.getCmdSvrBlockMax());

		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public void Listen() {
		try {
			
			while (listening){
				new Thread(new MessageIn(ss.accept())).start();
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static void main(String args[]) {
	//	new SimpleCommandServlet();
		DOResource   drs = DAOUtil.INSTANCE().getBySql(DOResource.class,"select * from do_resource where resourceName like 'jspheader_%'");
		System.out.println("DOResource:::::::" + drs);
	}

}
