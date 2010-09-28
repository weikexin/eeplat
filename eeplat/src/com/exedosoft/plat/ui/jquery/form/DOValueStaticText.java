package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

/*
 * 列表情况下，最后一个字段图片形式表示删除，可以通过Static　Text实现.
 * Input Config中配置图片的链接．
 * 
 */

public class DOValueStaticText extends DOBaseForm {

	public DOValueStaticText() {
		super();
	}


	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;
		return property.getInputConfig();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
