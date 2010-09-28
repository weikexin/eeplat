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
		
		System.out.println("Úó?‹@C³??ˆê˜¢JOB!");
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
		
		System.out.println("Job³İ?—”˜........................");

		String temp;
		try {
			while (s.isConnected() == true) {
				if ((len = in.read(b)) != -1) {
					temp = new String(b, 0, len);
					rev += temp;
					System.out.println("JOB“¾““I”˜:" + rev);
					temp = null;
					Thread.sleep(1000);
				}
			}
			in.close();
			s.close();
			System.out.println("‰ï?socket›ß’f?I");
		} catch (SocketException se) {
			System.out.println("‹q?’[›ß’f?I");
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
