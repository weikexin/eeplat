package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;


public class DONKWordOpen extends TPaneTemplate {

	public DONKWordOpen() {
		this.templateFile = "panel/DONKWordOpen.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		
		DOBO bo = pm.getCategory();
		BOInstance corrInstance = bo.getCorrInstance();
		String docName = corrInstance.getValue("docName");///字段名称
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", pm);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		data.put("docName", docName);
			
		StringBuffer sbItems = new StringBuffer();
		DOBasePaneView.genePaneContext(sbItems, pm);
		data.put("items_html", sbItems.toString());
		
		
		return data;
	}
}