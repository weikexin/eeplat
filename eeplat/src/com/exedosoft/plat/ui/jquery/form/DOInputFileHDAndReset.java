package com.exedosoft.plat.ui.jquery.form;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;

public class DOInputFileHDAndReset extends DOBaseForm {

	public DOInputFileHDAndReset() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		StringBuffer href = new StringBuffer("window.open('")
		.append(DOGlobals.PRE_FULL_FOLDER)		
		.append("upload.jsp?colName=")
				.append(property.getFullColName());
		
		href.append("','','width=500,height=250,top=200,scrollbars=yes')");

		StringBuffer buffer = new StringBuffer();

		buffer.append("<input name=\"").append(property.getFullColName())
				.append("\" type=\"text\"").append(" id=\"").append(
						property.getFullColName())
						.append("\"");
		
		buffer.append(this.appendValidateConfig(property));
		

		 
		if (property.getValue() != null
				&& !property.getValue().trim().equals("")) {
			buffer.append(" value=\"").append(property.getValue()).append("\"");
		}
	//	if (isReadOnly(property)) {
			buffer.append(" readonly=\"readonly\" ");
	//	}
		
		
		//////增加装饰
		buffer.append(getDecoration(property));

		/////////end 增加装饰
		buffer.append(" title='")
		.append(property.getL10n())
		.append("'");
	
		buffer.append(" size=\"").append(getInputSize(property)).append("\"/>");

		buffer.append("<input type='button' value='上传'");

		buffer.append(" onclick=\"").append(href).append(" \"");
		buffer.append(" />\n");
		
		buffer.append("&nbsp;<input type='button' value='重置'");

		buffer.append(" onclick=\"").append("$('").append(property.getFullColName()).append("').value=''; ").append(" \"");
		buffer.append(" />\n");
		
		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		

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
