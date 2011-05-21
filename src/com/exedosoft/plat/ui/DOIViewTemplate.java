package com.exedosoft.plat.ui;

import java.util.Map;

public interface DOIViewTemplate extends DOIView {

	public String getHtmlCode(DOIModel doimodel);
	
	public Map<String, Object> putData(DOIModel fm);
}
