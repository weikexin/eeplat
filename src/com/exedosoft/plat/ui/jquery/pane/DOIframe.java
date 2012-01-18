package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;


public class DOIframe extends TPaneTemplate {

	public DOIframe() {
		this.templateFile = "panel/DOIframe.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {
		
		
		System.out.println("111111111111111111111111111111111111111111");

		DOPaneModel pm = (DOPaneModel) doimodel;
		String resPath = "";
		
		if(pm.getResource()!=null){
		  resPath = pm.getResource().getResourcePath();	
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", pm);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		if(resPath.startsWith("http")){
			data.put("resPath", resPath);
		}else{
			data.put("resPath",new StringBuilder("/").append(DOGlobals.URL).append("/").append(resPath).toString());
		}
		
		return data;
	}
	
	public static void main(String[] args){
		DOPaneModel aPm = DOPaneModel.getPaneModelByID("402881e93107a32d013107a4f5920002");
		
		System.out.println(aPm.getHtmlCode());
		
	}
}