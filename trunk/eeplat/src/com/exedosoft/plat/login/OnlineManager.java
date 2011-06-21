package com.exedosoft.plat.login;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;
/**
 * 如果点击退出，则马上可以登录，否者需要等两分钟
 * 
 * LogOff 删除
 * 
 * @author Administrator
 *
 */

public class OnlineManager {
	
	private static Map<String, Long> sessionOnline = new HashMap<String, Long>();
	
	static{
		sessionOnline = Collections.synchronizedMap(sessionOnline);
	}
	
	public static void putUser(String userId){
		sessionOnline.put(userId, System.currentTimeMillis());
	}
	
	public static void removeUser(String userId){
		sessionOnline.remove(userId);
	}
	
	public static void checkSessionOnline(){
		
		for(Iterator<Entry<String, Long>> it = sessionOnline.entrySet().iterator(); it.hasNext();){
			Entry<String, Long> e = it.next();
			long internal = System.currentTimeMillis() - e.getValue();
			if(internal > 2*60*1000){
				System.out.println("Remove User=====================" + e.getKey());
				sessionOnline.remove(e.getKey());
			}
		}
	}
	
	


}
