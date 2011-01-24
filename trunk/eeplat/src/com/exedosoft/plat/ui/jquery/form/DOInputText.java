package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;


public class DOInputText extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		StringBuffer buffer = new StringBuffer();

		buffer
				.append(
						"<input  style='border:#B3B3B3 1px solid;'   onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\"  type='text' name='")
				.append(fm.getColName()).append("' id='").append(
						fm.getFullColID()).append("'");
		buffer.append(getDecoration(fm));

		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

		String theValue = fm.getValue();

		DOPaneModel cPaneModel = null;
		if (fm.getGridModel() != null) {
			cPaneModel = fm.getGridModel().getContainerPane();
		}


		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}

		buffer.append(this.appendValidateConfig(fm));
		appendHtmlJs(buffer, fm);

		buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");

		if (fm.isNotNull()) {

			
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			if (fm.getStyle() != null && !"".equals(fm.getStyle())) {
				buffer.append("&nbsp;&nbsp;&nbsp;<span style=\"").append(
						fm.getStyle()).append("\">").append(fm.getNote())
						.append("</span>");
			} else {
				buffer.append(fm.getNote());
			}
		}

		return buffer.toString();
	}
	
	public static void main(String[] args){
		
		DOFormModel fm = DOFormModel.getFormModelByID("636f5ca21e864c18835150a787d8c1bc");
		System.out.println("Is not null:::" + fm.isNotNull());

	}

}
