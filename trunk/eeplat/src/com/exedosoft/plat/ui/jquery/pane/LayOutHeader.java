package com.exedosoft.plat.ui.jquery.pane;

import java.util.Map;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.DOResource;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;


/**
 *	头面板的样式控制器
 */
public class LayOutHeader extends TPaneTemplate {

	public LayOutHeader() {
		this.templateFile = "panel/LayOutHeader.ftl";
		
		
	}
	
//	public Map<String, Object> putData(DOIModel doimodel) {
//
//		Map data = super.putData(doimodel);
//		DOPaneModel paneModel = (DOPaneModel)doimodel;
//		if(paneModel.getResource()!=null){
//			data.put("resourcePath", paneModel.getResource().getResourcePath());
//		}else{
//			DOResource   drs = DAOUtil.INSTANCE().getBySql(DOResource.class,"select * from do_resource where resourceName like 'jspheader_%'");
//			data.put("resourcePath", drs.getResourcePath());
//		}
//		return data;
//	}
	

}
