package com.exedosoft.plat.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.util.DOGlobals;

public abstract class DOViewTemplate implements DOIViewTemplate {

	protected String templateFile = "panel/Panel.ftl";

	public String getHtmlCode(DOIModel doimodel) {

		Map<String, Object> data = this.putData(doimodel);

		String s = "";
		try {
			s = HtmlTemplateGenerator.getContentFromTemplate(this.templateFile,
					data);
		} catch (IOException e) {
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
