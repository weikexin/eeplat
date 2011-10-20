package com.exedosoft.plat.ui.mobile.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class GridDetail extends DOViewTemplate {

	public GridDetail() {
		this.templateFile = "mobile/grid/GridDetail.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {
		
		DOGridModel gridModel = (DOGridModel)doimodel;

		List list = new ArrayList();
		if(gridModel.getService()!=null){
			list = gridModel.getService().invokeSelect();
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		
		if(gridModel.getAbstractGridFormLinks().size()>0){
			data.put("formlinks", gridModel.getAbstractGridFormLinks());
		}else{
			data.put("formlinks", gridModel.getNormalGridFormLinks());
		}
		
		if (list.size() > 0) {
			BOInstance ins = (BOInstance) list.get(0);
			data.put("data", ins);
		}
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		return data;
	}
}
