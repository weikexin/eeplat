package com.exedosoft.plat.ui.jquery.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * @author aa
 * 
 */
public class TUploadify extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TUploadify.class);
	
	public TUploadify(){
		this.templateFile = "form/TUploadify.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		
		try {
			if(DOGlobals.getInstance().getServletContext().getRequest().getSession()!=null){
				data.put("sessionid",DOGlobals.getInstance().getServletContext().getRequest().getSession().getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

}
