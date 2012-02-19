package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOInputCheckBoxList extends DOStaticList {

	public DOInputCheckBoxList() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		StringBuffer buffer = new StringBuffer();

		if (property.getLinkService() != null) {
			int i = 0;
			for (Iterator it = property.getLinkService().invokeSelect()
					.iterator(); it.hasNext();) {

				i++;
				 
				BOInstance instance = (BOInstance) it.next();

				buffer.append("<input name=\"").append(
						property.getFullColName());

				buffer.append("\" value=\"").append(instance.getUid());

				buffer.append("\" class=\"custom\"   type=\"checkbox\"");

				buffer.append(getDecoration(property));

				if (DOStaticList.isChecked(instance.getUid(),
						property.getValue())) {
					buffer.append(" checked ");
				}
				if (isReadOnly(property)) {
					buffer.append(" DISABLED  ");
				}
				buffer.append("/>");

				if (!"jquery_mobile".equals(DOGlobals.getInstance()
						.getSessoinContext().getUser().getValue("jslib"))) {
					buffer.append(instance.getThisLink());
				} else {

					buffer.append("<label for='")
							.append(property.getFullColID()).append(i).append("'> ")
							.append(instance.getThisLink()).append("</label>");
				}
				// buffer.append(instance.getName());

			}
		} else if (property.getInputConfig() != null) {

			int i = 0;
			List list = StringUtil.getStaticList(property.getInputConfig());
			for (Iterator it = list.iterator(); it.hasNext();) {
				i ++;
				String[] half = (String[]) it.next();
				buffer.append("<input name=\"").append(
						property.getFullColName());

				buffer.append("\" value=\"").append(half[0]);

				buffer.append("\" class=\"custom\"   type=\"checkbox\"");
				buffer.append(getDecoration(property));

				if (DOStaticList.isChecked(half[0], property.getValue())) {
					buffer.append(" checked ");
				}
				if (isReadOnly(property)) {
					buffer.append(" disabled ");
				}
				buffer.append("/>");
				
				if (!"jquery_mobile".equals(DOGlobals.getInstance()
						.getSessoinContext().getUser().getValue("jslib"))) {
					buffer.append(half[1]);
				} else {
					buffer.append("<label for='")
							.append(property.getFullColID()).append(i).append("'> ")
							.append(half[1]).append("</label>");
				}
			}
		}
		return buffer.toString();

	}

}
