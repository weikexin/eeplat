package com.eeplat.social.weibo;

import java.util.List;

import weibo4j.Status;
import weibo4j.Weibo;


public class Message {
	/*
	 * 获取@当前用户的微博列表 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
   	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
       try {
       	Weibo weibo = new Weibo();
			weibo.setToken(args[0],args[1]);
       	List<Status> list = weibo.getMentions();
       	for(Status status : list) {
       		System.out.println( status.getId() + "  : "+status.getText());
       	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
