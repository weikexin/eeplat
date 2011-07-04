package com.exedosoft.plat.ui.jquery.grid;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * @author aa
 */
public class GridListBar extends DOViewTemplate {

	private static Log log = LogFactory.getLog(GridListBar.class);

	
	private BOInstance instance;
	
	public GridListBar(BOInstance aIntance) {
		this.templateFile = "grid/GridListBar.ftl";
		instance = aIntance;
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
			data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		data.put("formName", "a" + gm.getObjUid());
		data.put("instance", instance);

		return data;
	}
}
