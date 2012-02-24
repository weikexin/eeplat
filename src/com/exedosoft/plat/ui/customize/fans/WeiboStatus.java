package com.exedosoft.plat.ui.customize.fans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weibo4j.Paging;
import weibo4j.Status;
import weibo4j.Weibo;
import weibo4j.WeiboException;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * @author aa
 */
public class WeiboStatus extends DOViewTemplate {

	private static Log log = LogFactory.getLog(WeiboStatus.class);

	private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM-dd HH:mm");
	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("今天 HH:mm");
	private static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public WeiboStatus() {
		this.templateFile = "customize/fans/WeiboStatus.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {
		
		System.out.println("Enter this ======================1");

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		data.put("data", getListData(gm, data));
		data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}

		return data;
	}

	public static List<BOInstance> getListData(DOGridModel gridModel,
			Map<String, Object> data) {
		
		
		System.out.println("Enter this ======================2");

		
		Weibo weibo = null;
		try {
			 weibo = (Weibo)DOGlobals.getInstance().getSessoinContext().getServletContext().getRequest().getSession().getAttribute("weibo_session");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		System.out.println("Status:::" +  weibo.getUserTimeline("1778742953"));

		List<BOInstance> list = new ArrayList<BOInstance>();
		

		try {
			Paging paging = new Paging(1,30);
			List<Status> statuses = weibo.getUserTimeline("1778742953",paging);
			
			for(Status s:statuses){
				
				BOInstance aBI = new BOInstance();
				aBI.putValue("owner_pic", "http://tp2.sinaimg.cn/1778742953/180/5619186079/0"); 
				aBI.putValue("msg_owner_name", "张靓颖"); 
				aBI.putValue("msg_content",s.getText() + "<br/> <img border=0  src='" + s.getThumbnail_pic() + "'/>");
				aBI.putValue("mdate",getTimeStr(s.getCreatedAt()));		
				list.add(aBI);
				
				System.out.println("Status:::" + s);
				
				
				
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
	
		return list;
	}

	public static String getTimeStr(Date aDate) {

		try {
			Date current = new Date(System.currentTimeMillis());

			long beforTimes = System.currentTimeMillis() - aDate.getTime();

			if (beforTimes < 51 * 1000) {
				return Math.round(beforTimes / 10000 + 0.5) + "0秒前";
			}

			if (beforTimes < 60 * 60 * 1000) {
				return Math.round(beforTimes / (1000 * 60) + 0.5) + "分钟前";
			}

			if (aDate.getYear() == current.getYear()
					&& aDate.getMonth() == current.getMonth()
					&& aDate.getDate() == current.getDate()) {
				return dateFormat2.format(aDate);

			} else if (aDate.getYear() == current.getYear()) {
				return dateFormat1.format(aDate);
			} else {
				return dateFormat3.format(aDate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return dateFormat3.format(aDate);
		}

	}

	public static void main(String[] args) {

		int i = (int) Math.round( 0.51);
		System.out.println("i:::::::::" + i);
	}

}
