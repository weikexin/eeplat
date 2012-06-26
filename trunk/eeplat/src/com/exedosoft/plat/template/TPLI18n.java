package com.exedosoft.plat.template;

import java.util.List;

import com.exedosoft.plat.util.I18n;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class TPLI18n implements TemplateMethodModelEx {

	public Object exec(List paras) throws TemplateModelException {

		Object aPara = paras.get(0);
		
		return I18n.instance().get(aPara.toString());
	}

}
