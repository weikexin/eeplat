package com.exedosoft.plat.ui.jquery.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * Pane和Service的调用分开
 * @author aa
 * 
 */
public class TPaneApplication extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TPaneApplication.class);
	
	public TPaneApplication(){
		this.templateFile = "form/TPaneApplication.ftl";
	}
	

	public Map<String, Object> putData(DOIModel doimodel) {

		// DOPaneModel pm = (DOPaneModel)doimodel;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		DOBO bo = DOBO.getDOBOByName("do_application");
		BOInstance bi = bo.getCorrInstance();
		DOApplication doa = DOApplication.getApplicationByID(bi.getUid());
		String pml = "";
		if(doa.getPaneModel()!=null){
			pml = doa.getPaneModel().getName();
		}else if(doa.getDobo()!=null && doa.getDobo().getMainPaneModel()!=null){
			pml = doa.getDobo().getMainPaneModel().getName();
		}else{
			pml = "pane_" + doa.getName();
		}
		data.put("appPml", pml);
		data.put("appName", doa.getName());
		return data;
	}
	


}
