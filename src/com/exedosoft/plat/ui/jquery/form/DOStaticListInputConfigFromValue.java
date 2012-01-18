package com.exedosoft.plat.ui.jquery.form;

import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

/**
 * 静态列表应该单独作为一个表
 * @author IBM
 *
 */
public class DOStaticListInputConfigFromValue extends DOStaticList {

	public DOStaticListInputConfigFromValue() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		List list = StringUtil.getStaticList(property.getValue());

		return formSelectStr(property, list);
	}
}

	
