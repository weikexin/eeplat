package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.login.LoginDelegateList;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;

public class DelegateSelect extends TPaneTemplate {

	public DelegateSelect() {
		this.templateFile = "panel/DelegateSelect.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel){
		
		DOPaneModel pm = (DOPaneModel) doimodel;
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("model", pm);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		
		List<BOInstance> delegages = LoginDelegateList.getDelegates();

		data.put("delegages",delegages);
		data.put("pml",SessionContext.getServletContext().getRequest().getParameter("pml"));

		return data;
	}
}
