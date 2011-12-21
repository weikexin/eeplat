package com.exedosoft.plat.template;

import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class BindData2FormModel implements TemplateMethodModelEx {

	public Object exec(List paras) throws TemplateModelException {

		// TemplateModel model =
		// Environment.getCurrentEnvironment().getVariable("lineData");
		StringModel p1 = (StringModel) paras.get(0);
		StringModel p2 = (StringModel) paras.get(1);
		if (p2 != null) {
			BOInstance data = null;
			if (p1 != null) {
				data = (BOInstance) p1.getWrappedObject();
			}
			DOFormModel fm = (DOFormModel) p2.getWrappedObject();
			fm.setData(data);
			
			if (data != null) {
				if (!fm.isAccess(data)) {
					return "false";
				}
			} else {
				if (!fm.isAccess()) {
					return "false";
				}

			}

		}

		return "";
	}

}
