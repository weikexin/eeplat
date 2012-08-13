package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOValueStaticListWithColor extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;
		StringBuffer sb=new StringBuffer();
		
		List list = StringUtil.getStaticList(property.getInputConfig());
		String value = property.getValue();
		if (value == null) {
			value = getDefaultListValue(property);
		}
		for (Iterator it = list.iterator(); it.hasNext();) {
			String[] halfs = (String[]) it.next();
			if (value != null && value.equals(halfs[0]) && halfs.length >= 3) {
				String color = "color:"+halfs[2];
				sb.append("<span style='").append(color).append("'>").append(halfs[1]).append("</span>");
				return sb.toString();
			}
		}
		return "&nbsp;";
	}
	
	/**
	 * 静态下拉列表中,缺省的值
	 * 
	 * @param property
	 *            TODO
	 * @return
	 */
	String getDefaultListValue(DOFormModel property) {

		if (property.getInputConfig() != null) {
			if (property.getInputConfig().indexOf("@") != -1) {
				return property.getInputConfig().substring(
						property.getInputConfig().indexOf("@") + 1);
			}
		}
		return null;
	}
	
}
