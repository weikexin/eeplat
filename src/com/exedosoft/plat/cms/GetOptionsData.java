package com.exedosoft.plat.cms;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class GetOptionsData implements TemplateMethodModel{


	public Object exec(List list) throws TemplateModelException {
		return SessionAttribute.getInstance().getOptionsMap();
	}

}
