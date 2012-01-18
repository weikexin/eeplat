package com.exedosoft.plat.login;

import java.util.TimerTask;

public class CheckOnlineTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		OnlineManager.checkSessionOnline();

	}

}
