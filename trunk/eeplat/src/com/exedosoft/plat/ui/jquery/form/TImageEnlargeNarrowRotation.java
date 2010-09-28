package com.exedosoft.plat.ui.jquery.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class TImageEnlargeNarrowRotation extends DOViewTemplate {

	public TImageEnlargeNarrowRotation() {
		this.templateFile = "form/TImageEnlargeNarrowRotation.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOFormModel fm = (DOFormModel) doimodel;
		String path = fm.getValue();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("absPath", "/"+DOGlobals.URL+"/upload/"+path);
		data.put("webmodule", DOGlobals.URL);

		return data;
	}

}
