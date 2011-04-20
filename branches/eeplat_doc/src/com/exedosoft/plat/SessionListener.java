package com.exedosoft.plat;

import java.util.Vector;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.login.LoginMain;
import java.sql.Timestamp;

public class SessionListener implements HttpSessionListener {

	public static Vector appData = new Vector();

	public SessionListener() {
	}

	public void sessionCreated(HttpSessionEvent se) {

	}

	public void sessionDestroyed(HttpSessionEvent se) {

		LoginMain.globalSessions.values().remove(se.getSession());

		// /删除过期数据
//		DOService dellogService = DOService
//				.getService("do_log_delete_guoqi");
//		try {
//			dellogService.invokeUpdate();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			// e1.printStackTrace();
//		}

		DOService updateService = DOService
				.getService("do_log_update_by_sessionid");

		try {
			BOInstance aInstance = new BOInstance();
			aInstance.putValue("logoffTime", new Timestamp(System
					.currentTimeMillis()));
			aInstance.putValue("sessionid", se.getSession().getId());
			updateService.invokeUpdate(aInstance);
			// aInstance.invokeUpdate();
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
