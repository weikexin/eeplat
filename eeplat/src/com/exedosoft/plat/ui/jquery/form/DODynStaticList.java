package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DODynStaticList extends DOStaticList {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel fm = (DOFormModel) iModel;
		List list = StringUtil.getStaticList(fm.getInputConfig());
		return formSelectStr(fm, list);
	}

	String formSelectStr(DOFormModel fm, List list) {

		StringBuffer buffer = new StringBuffer();

		String value = fm.getValue();

		buffer.append("<select ");

		buffer.append(" name=\"").append(fm.getFullColName()).append(
				"\" ");

		if (isReadOnly(fm)) {
			buffer.append(" disabled=\"disabled\" ");
		}

		if (fm.getLinkForms()!=null
				&& fm.getLinkForms().size()>0) {
			
			DOFormModel linkFm = (DOFormModel)fm.getLinkForms().get(0);
			buffer
					.append("onChange=\"changeServiceName(this,'")
					.append(linkFm.getInputConfig())
					.append("','")
					.append(linkFm.getFullColID())
					.append("')\"");
		}
		// ////增加装饰
		buffer.append(getDecoration(fm));

		// ///////end 增加装饰
		buffer.append(" title='").append(fm.getL10n()).append("'");
		// ///////end 增加装饰

		buffer.append(" >\n");
		buffer.append("<option/>\n");
		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				String[] half = (String[]) it.next();
				buffer.append("<option value=\"").append(half[0]);
				buffer.append("\"");

				if (value != null) { // ////////修改的情况
					if (value.equals(half[0])) {
						buffer.append(" selected=\"selected\"  ");
					}
				} else { // //////添加的情况
					String defaultValue = getDefaultListValue(fm);
					if (defaultValue != null && defaultValue.equals(half[0])) {
						buffer.append(" selected=\"selected\"  ");
					}

				}
				buffer.append(">");
				buffer.append(half[1]);
				buffer.append("</option>\n");
			}
		}

		buffer.append("</select>");
		return buffer.toString();
	}


}
