package com.exedosoft.plat.ui;

import java.util.Map;

public interface DOIViewTemplateExt extends DOIView {

	public String getHtmlCode(DOIModel doimodel);

	public String getBeforScript(DOIModel doimodel);

	public String getAfterScript(DOIModel doimodel);
	
	public String getWholeCode(DOIModel doimodel);
	
	public Map<String, Object> putData(DOIModel fm);
}
