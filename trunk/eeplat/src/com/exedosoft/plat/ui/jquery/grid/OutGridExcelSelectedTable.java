package com.exedosoft.plat.ui.jquery.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;


public class OutGridExcelSelectedTable  extends  GridList {
	
	public OutGridExcelSelectedTable() {
		this.templateFile = "grid/OutGridExcelTable.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		List list =  getListData(gm, data);
		
		String[] selects =  DOGlobals.getInstance().getServletContext().getRequest().getParameterValues("checkinstance");
		List lSelects = Arrays.asList(selects);
		List selectedList = new ArrayList();
		for (Iterator it = list.iterator(); it.hasNext();) {
			BOInstance ins = (BOInstance) it.next();
			if(lSelects.contains(ins.getUid())){
				selectedList.add(ins);
			}
		}
		data.put("model", gm);
		data.put("data", selectedList);
		data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		return data;
	}
}
