package com.exedosoft.plat.ui.mobile.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.form.DOStaticList;
import com.exedosoft.plat.util.StringUtil;

public class DOInputCheckBoxList extends DOStaticList {

	public DOInputCheckBoxList() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<fieldset data-role='controlgroup'>\n");
		buffer.append("<legend>")
		.append(property.getL10n())
		.append("</legend>\n");


		if (property.getLinkService() != null) {
			for (Iterator it = property.getLinkService().invokeSelect()
					.iterator(); it.hasNext();) {

				BOInstance instance = (BOInstance) it.next();

				buffer.append("<input name=\"").append(
						property.getFullColName());

				buffer.append("\" value=\"").append(instance.getUid());

				buffer.append("\"  type=\"checkbox\"");

				buffer.append(getDecoration(property));

				if (DOStaticList.isChecked(instance.getUid(), property
						.getValue())) {
					buffer.append(" checked ");
				}
				// if (isReadOnly(property)) {
				// buffer.append(" disable ");
				// }
				buffer.append("/>");
				buffer.append(instance.getThisLink());
				//buffer.append(instance.getName());

			}
		} else if(property.getInputConfig()!=null){

			List list = StringUtil.getStaticList(property.getInputConfig());
			for (Iterator it = list.iterator(); it.hasNext();) {
				String[] half = (String[]) it.next();
				buffer.append("<input name=\"").append(
						property.getFullColName());

				buffer.append("\" value=\"").append(half[0]);

				buffer.append("\"  type=\"checkbox\"");
				buffer.append(getDecoration(property));

				if (DOStaticList.isChecked(half[0], property.getValue())) {
					buffer.append(" checked ");
				}
				// if (isReadOnly(property)) {
				// buffer.append(" disable ");
				// }
				buffer.append("/>");
				buffer.append(half[1]);
			}
		}
		buffer.append("</fieldset>");
		return buffer.toString();

	}

}
