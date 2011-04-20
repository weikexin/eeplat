package com.exedosoft.plat.agent.job;

import com.exedosoft.plat.agent.Job;
import com.exedosoft.plat.agent.Message;
import com.exedosoft.plat.agent.Task;
import com.exedosoft.plat.agent.config.TaskCommandMap;
import com.exedosoft.plat.agent.message.OutPool;
import com.exedosoft.plat.agent.message.ProcessQueue;
import com.exedosoft.plat.agent.message.SimpleMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author     Administrator
 */
public class MessageJob implements Job {

	private Message message;

	public MessageJob(Message aMessage) {
		message = aMessage;
	}

	public List<Task> getTasks() {

		return TaskCommandMap.getTaskConfigs(message.getCommandID());

	}

	public void getCurExcTask() {
		// TODO Auto-generated method stub

	}

	public void removeMessage() {
		ProcessQueue.getPool().removeMessage(this.message);
	}

	public void run() {

		if (this.getTasks() == null) {
			return;
		}

		List<Task> excuteOverList = new ArrayList<Task>();

		Message aMsg = null;
		for (Iterator it = this.getTasks().iterator(); it.hasNext();) {

			Task aTask = (Task) it.next();
			try {
				aMsg = aTask.perform(this.message);
				OutPool.getPool().addMessage(message.getCommandID(), aMsg);
			} catch (Exception e) {
				System.out.println("?惗??丆惓嵼夞?丅丅丅丅丅丅丅");
				for (Iterator<Task> itFail = excuteOverList.iterator(); itFail
						.hasNext();) {
					Task aFailTask = itFail.next();
					OutPool.getPool().addMessage(message.getCommandID(),
							aFailTask.rollBack(this.message));
				}
				break;
			}

			excuteOverList.add(aTask);
		}
		this.outMessage(aMsg);
		this.removeMessage();

	}

	private void outMessage(Message aMsg) {

		try {

			SimpleMessage sm = (SimpleMessage) this.message;
			if (sm.getASocket() == null) {
				return;
			}
			if (sm.getASocket().isClosed()) {
				return;
			}
			if (aMsg == null) {
				sm.getASocket().close();
				return;
			}

			ObjectOutputStream serverOutputStream = new ObjectOutputStream(sm
					.getASocket().getOutputStream());
			serverOutputStream.writeObject(aMsg);
			sm.getASocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
