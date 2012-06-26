package com.exedosoft.plat.ui.jquery.grid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;

public class OutGridExcelTable extends  GridList {
	
	public OutGridExcelTable() {
		this.templateFile = "grid/OutGridExcelTable.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		List list = gm.getService().invokeSelect();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		data.put("data", list);
		data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		return data;
	}


}
