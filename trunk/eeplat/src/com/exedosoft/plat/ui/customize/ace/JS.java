package com.exedosoft.plat.ui.customize.ace;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * @author aa
 */
public class JS extends DOViewTemplate {

	private static Log log = LogFactory.getLog(JS.class);


	public JS() {
		this.templateFile = "customize/ace/js.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		
		String  browseAgent = "notie";
		try {
			browseAgent = DOGlobals.getInstance().getServletContext().getRequest().getHeader("user-agent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   //判断user-agent的信息
		   if((browseAgent!=null)&& (browseAgent.indexOf("MSIE"))!=-1) {
			   data.put("browseAgent", "ie");
		   }else{
			   data.put("browseAgent", "notie");
		   }
		
		return data;
	}

	public static void main(String[] args) {

		int i = (int) Math.round( 0.51);
		System.out.println("i:::::::::" + i);
	}

}
