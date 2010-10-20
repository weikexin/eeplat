package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOInputCheckBoxListWithOther extends DOStaticList {

	public DOInputCheckBoxListWithOther() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		StringBuffer buffer = new StringBuffer();

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
				if(!half[1].contains("其他")) {
					buffer.append("<input name=\"").append(
							property.getFullColName());

					buffer.append("\" value=\"").append(half[0]);

					buffer.append("\"  type=\"checkbox\"");
					buffer.append(getDecoration(property));

					if (isReadOnly(property)) {
						buffer.append(" onclick='return false;'  ");
						buffer.append("  DISABLED ");

					}
					if (DOStaticList.isChecked(half[0], property.getValue())) {
						buffer.append(" checked ");
					}
					// if (isReadOnly(property)) {
					// buffer.append(" disable ");
					// }
					buffer.append("/>");
					buffer.append(half[1]);
				} else {
					buffer.append("<input name=\"").append(
							property.getFullColName());

					buffer.append("\" value=\"").append(half[0]);

					buffer.append("\"  type=\"checkbox\"");
					buffer.append(getDecoration(property));

					if (isReadOnly(property)) {
						buffer.append(" onclick='return false;'  ");
						buffer.append(" DISABLED ");

					}
					
					if (DOStaticList.isChecked(half[0], property.getValue())) {
						buffer.append(" checked ");
					}
					// if (isReadOnly(property)) {
					// buffer.append(" disable ");
					// }
					buffer.append("/>");
					buffer.append(half[1]);
					
					
					if (isReadOnly(property)) {
						if (DOStaticList.isChecked(half[0], property.getValue())) {
							String[] strs = property.getValue().split(";");
							buffer.append("<span style='text-decoration:underline'>" +strs[strs.length-1] +"</span>");
						}
					} else {

						buffer
						.append(
								"<input  style='border:#B3B3B3 1px solid;'   onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\"  type='text' name='")
						.append(property.getFullColName()).append("'");
						buffer.append(" title='").append("其他").append("'");
						if (DOStaticList.isChecked(half[0], property.getValue())) {
							String[] strs = property.getValue().split(";");
							buffer.append(" value='"+ strs[strs.length-1] +"'");
						}
						buffer.append(" size=\"").append("13").append("\"/>");
					}
					
					

				}			
			}
		}
		return buffer.toString();

	}

}
