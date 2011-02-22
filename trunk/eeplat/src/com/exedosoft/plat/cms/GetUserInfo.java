package com.exedosoft.plat.cms;

import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author lenovo
 *  判断有文章?
 */
public class GetUserInfo implements TemplateMethodModel{

	public Object exec(List arg0) throws TemplateModelException {
		return SessionAttribute.getInstance().getAttributeMap();
	}

}
