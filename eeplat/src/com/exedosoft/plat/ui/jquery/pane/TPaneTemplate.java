package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;

public class TPaneTemplate extends DOViewTemplate {

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
