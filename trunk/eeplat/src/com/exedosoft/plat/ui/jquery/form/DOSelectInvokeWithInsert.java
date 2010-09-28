package com.exedosoft.plat.ui.jquery.form;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOSelectInvokeWithInsert extends DOBaseForm {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		StringBuffer buffer = new StringBuffer();

		buffer.append("<input name=\"").append(property.getFullColName())
				.append("\" type=\"hidden\"").append(" id=\"").append(
						property.getFullColName()).append("\" ");

		if (property.getValue() != null
				&& !property.getValue().trim().equals("")) {
			buffer.append(" value=\"").append(property.getValue()).append("\"");
		}

		buffer.append(" />");

		if (property.getInputConfig() != null) {
			
			////////////////隐藏的inputType formModel Name
			buffer.append("<input name=\"").append(property.getInputConfig())
					.append("\" type=\"hidden\"").append(" id=\"").append(
							property.getInputConfig()).append("\" ");

			if (property.getValue() != null
					&& !property.getValue().trim().equals("")) {
				buffer.append(" value=\"").append(property.getValue()).append(
						"\"");
			}
			buffer.append(" />");
		}

		// onkeydown
		buffer.append("<input name=\"").append(property.getFullColName())
				.append("show\" type=\"text\"").append(" id=\"").append(
						property.getFullColName()).append("show\"")
		// .append(" onkeydown=\"")
		// .append("recieveKeyValue('")
		// .append(property.getFullColName())
		// .append("',this.value)\"")
		;

		buffer.append(this.appendValidateConfig(property));

		if (property.getValue() != null
				&& !property.getValue().trim().equals("")) {

			DOBO doBO = null;

			if (property.getLinkBO() != null) {
				doBO = property.getLinkBO();
			} else if (property.getLinkService() != null) {
				doBO = property.getLinkService().getBo();
			}

			BOInstance bi = DOValueResultList.getAInstance(property, doBO,
					property.getValue());

			System.out.println("BOInstance===========" + bi);

			if (bi != null) {
				buffer.append(" value=\"").append(bi.getName()).append("\"");
			}

		}

		buffer.append(" readonly=\"readonly\" ");

		// ////增加装饰
		buffer.append(getDecoration(property));

		// ///////end 增加装饰
		buffer.append(" title='").append(property.getL10n()).append("'");

		buffer.append(" size=\"").append(getInputSize(property)).append("\"/>");

		buffer.append(DOValueService.stardardOnlyPane(property, "查找"));
		
		
		
		buffer.append("&nbsp;&nbsp;");
	
	
		DOFormModel newFm = DOFormModel.getFormModelByID("2dc40ca2088c4e87a99478fd50db9c5b") ;
		
		buffer.append(DOValueService.stardardOnlyPane(newFm, " 新增 "));
		
	//	buffer.append("&nbsp;&nbsp;&nbsp;<a href='#' onclick='doAjax.refresh(\"dojo.openwindow\", \"pane_tbstudent.list.insertxuers.tbstudent.tbstudenthopeclass.pml?isGet=true\");'>新增</a>");

		// buffer.append(DOValueService.stardardOnlyPane(property, "新增"));

		return buffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		list.add(null);
		System.out.println(list);

	}

}
