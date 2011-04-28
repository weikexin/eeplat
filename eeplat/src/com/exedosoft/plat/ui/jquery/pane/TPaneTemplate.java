package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class TPaneTemplate extends DOViewTemplate {

	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", pm);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
			
		StringBuffer sbItems = new StringBuffer();
		List children = pm.retrieveChildren();
		boolean isMobile = false;
		
		try {
			if("jquery_mobile".equals(DOGlobals.getInstance().getSessoinContext().getUser().getValue("jslib"))){
				isMobile = true;
}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		if(isMobile && children!=null && children.size() > 0){
			DOBasePaneView.genePaneContext(sbItems, (DOPaneModel)children.get(0));
			
			//////需要追加，后面面板的链接 
			
			/////第一个很容易添加，如果多个，需要找parent 然后再查找兄弟
			
		}else{
			DOBasePaneView.genePaneContext(sbItems, pm);
		}
	
		
		data.put("items_html", sbItems.toString());
		

		
		return data;
	}
}
