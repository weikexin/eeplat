package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOInputCheckBoxListDiv extends DOStaticList {

	public DOInputCheckBoxListDiv() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		StringBuffer buffer = new StringBuffer();

		if (property.getLinkService() != null) {
			buffer.append("<div  style=\"overflow-x:auto;overflow-y:scroll;width:100%;");
			if (property.getWidth() != null && !"".equals(property.getWidth())) {
				buffer.append("overflow-y:scroll;height:").append(property.getWidth()).append(";");
			} else {
				buffer.append("overflow-y:auto;");
			}
			buffer.append("\">");

			for (Iterator it = property.getLinkService().invokeSelect()
					.iterator(); it.hasNext();) {

				BOInstance instance = (BOInstance) it.next();

				buffer.append("<input name=\"").append(
						property.getFullColName());
				
//				buffer.append("\"  id=\"").append(
//						property.getFullColName());
				
				buffer.append("\" value=\"").append(instance.getUid());

				buffer.append("\"  type=\"checkbox\"");

				if (property.getDoClickJs() != null && !"".equals(property.getDoClickJs().trim())) {
					String doclick = property.getDoClickJs().trim();
					if(doclick.endsWith(")")){
						buffer.append("  onclick='").append(property.getDoClickJs()).append("'");
					} else {
						buffer.append("  onclick=\"").append(property.getDoClickJs()).append("('").append(instance.getUid()).append("');\"");
					}
					
				}
				
				buffer.append(getDecoration(property));

				if (DOStaticList.isChecked(instance.getUid(), property
						.getValue())) {
					buffer.append(" checked ");
				}
				 if (isReadOnly(property)) {
					 buffer.append(" DISABLED  ");
				 }
				buffer.append("/>&nbsp;");
				//buffer.append(instance.getAjaxLink("_opener", null));
				buffer.append(instance.getName());

				buffer.append("<br>");

			}
			buffer.append("</div>");  
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
				 if (isReadOnly(property)) {
					 buffer.append(" DISABLED  ");
				 }
				buffer.append("/>");
				buffer.append(half[1]);
				buffer.append("<br>");
			}
		}
		return buffer.toString();

	}

}
