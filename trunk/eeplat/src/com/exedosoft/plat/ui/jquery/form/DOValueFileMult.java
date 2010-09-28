package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueFileMult implements DOIView {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		if (property.getValue() != null) {

			String[] paths = property.getValue().split(" ");
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < paths.length; i++) {

				buffer.append("<a target='_blank' class='exedo_link' href=");
				buffer.append(StringUtil.getAttachMentFile(paths[i])).append(
						">");
				// //////////对picture类型的特殊处理
				buffer.append(paths[i]);

				buffer.append("</a><br/>");
			}
			return buffer.toString();
		}
		return "&nbsp;";

	}



}
