package com.exedosoft.plat.ui.mobile.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.ui.DOFormModel;

import com.exedosoft.plat.bo.BOInstance;

/**
 * 
 * Pane和Service的调用分开
 * @author aa
 */
public class MSuite extends DOViewTemplate {

	private static Log log = LogFactory.getLog(MSuite.class);
	
	public MSuite(){
		this.templateFile = "mobile/form/MSuite.ftl";
	}
	
	
	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		DOFormModel aFm = (DOFormModel)doimodel;
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		data.put("emptydata", new BOInstance());
		
		List linkForms = aFm.getLinkForms();
		if(linkForms!=null && linkForms.size() > 0){
			DOFormModel first = (DOFormModel)linkForms.get(0);
			if(first.getController().getCategory().getName().equals("c_form_button")){
				data.put("buttongroup","true");
			}
			
		}

		
		return data;
	}
	



}
