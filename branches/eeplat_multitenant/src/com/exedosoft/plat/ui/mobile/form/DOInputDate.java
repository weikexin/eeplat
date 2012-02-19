package com.exedosoft.plat.ui.mobile.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;

public class DOInputDate extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		StringBuffer buffer = new StringBuffer();

		buffer.append("<input  type='date' name='").append(fm.getColName())
				.append("' id='").append(fm.getFullColID()).append("'");
		buffer.append(getDecoration(fm));

		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

		String theValue = fm.getValue();

		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}

		buffer.append("  data-role='datebox'  data-options='{\"mode\":\"datebox\",\"focusMode\": true}'");

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

}
