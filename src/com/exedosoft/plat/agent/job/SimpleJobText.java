package com.exedosoft.plat.agent.job;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import com.exedosoft.plat.agent.Job;
import com.exedosoft.plat.agent.Message;

public class SimpleJobText implements Job {

	private Socket s;

	private InputStream in;

	private String rev;

	private byte b[];

	private int len;

	public SimpleJobText(Socket ss) {
		
		System.out.println("愙庴?媮岪丆惓??堦槩JOB!");
		s = ss;
		b = new byte[1024];
		try {
			in = s.getInputStream();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		rev = "";
	}

	public void run() {
		
		System.out.println("Job惓嵼?棟悢悩........................");

		String temp;
		try {
			while (s.isConnected() == true) {
				if ((len = in.read(b)) != -1) {
					temp = new String(b, 0, len);
					rev += temp;
					System.out.println("JOB摼摓揑悢悩:" + rev);
					temp = null;
					Thread.sleep(1000);
				}
			}
			in.close();
			s.close();
			System.out.println("夛?socket涍抐?両");
		} catch (SocketException se) {
			System.out.println("媞?抂涍抐?両");
			System.exit(0);
		} catch (IOException io) {
			io.printStackTrace();
			System.exit(0);
		} catch (InterruptedException ire) {
			ire.printStackTrace();
		}
	}

	public List getTasks() {
		
		return null;
	}



	public void removeMessage() {
		// TODO Auto-generated method stub
		
	}

}
