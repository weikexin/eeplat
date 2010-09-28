package com.exedosoft.plat.ui.jquery.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOViewTemplate;

/**
 * Pane和Service的调用分开
 * @author aa
 * 
 */
public class TPane extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TPane.class);
	
	public TPane(){
		this.templateFile = "form/TPane.ftl";
	}
	


}
