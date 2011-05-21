package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;



/**
 *	左侧菜单样式的控制器
 */
public class MainPage extends TPaneTemplate {

	public MainPage() {
		this.templateFile = "panel/MainPage.ftl";
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", pm);
			
		StringBuffer sbItems = new StringBuffer();
		DOBasePaneView.genePaneContext(sbItems, pm);
		data.put("items_html", sbItems.toString());
		
		
		return data;
	}

}
