package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIViewTemplate;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;

public class TDBPaneTemplate implements DOIViewTemplate {

	public String getHtmlCode(DOIModel doimodel) {

		String s = "";
		try {
			DOPaneModel pm = (DOPaneModel) doimodel;

			Map<String, Object> data = this.putData(doimodel);

			s = HtmlTemplateGenerator.getContentFromDBTemplate(pm
					.getController().getObjUid(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);

		return data;
	}

}
