package com.exedosoft.plat.ui.jquery.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 模板本身定义的模板优先
 * @author aa
 * 
 */
public class TAPaneWF extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TAPaneWF.class);
	
	public TAPaneWF(){
		this.templateFile = "form/TAPaneWF.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		// DOPaneModel pm = (DOPaneModel)doimodel;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		
		DOFormModel aFm = (DOFormModel)doimodel;
		BOInstance bi = aFm.getData();

		if(bi!=null && bi.getValue("paneuid")!=null){
			DOPaneModel paneModel = DOPaneModel.getPaneModelByID(bi.getValue("paneuid"));
			data.put("paneModel", paneModel);
		}else if(aFm.getLinkPaneModel()!=null){ 
			data.put("paneModel", aFm.getLinkPaneModel());
		}
		return data;
	}


}
