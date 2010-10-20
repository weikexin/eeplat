package com.exedosoft.plat.ui.jquery.form.my97;

import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;
import com.exedosoft.plat.util.StringUtil;

public class DatePickerBetweenTSuit extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {
		DOFormModel property = (DOFormModel) aModel;
		
		List list = property.getLinkForms();
		
		DOFormModel fm1 = (DOFormModel)list.get(0);
		fm1.setData(property.getData());
		DOFormModel fm2 = (DOFormModel)list.get(1);
		fm2.setData(property.getData());

		StringBuffer buffer = new StringBuffer();

		getAInputTimeStr(fm1, buffer, "");
		buffer.append("&nbsp;жа &nbsp;");
		getAInputTimeStr(fm2, buffer, "");
		
		return buffer.toString();
	}
	
	
	/**
	 * @param fm
	 * @param buffer
	 */
	private void getAInputTimeStr(DOFormModel fm, StringBuffer buffer,
			String aNext) {

		buffer.append("<input type='text' name='").append(fm.getFullColName())
				.append("' id='").append(fm.getFullColName()).append("'");

		buffer.append(getDecoration(fm));


		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

		

		String theValue = fm.getValue();
		

		DOPaneModel cPaneModel = null;
		
		System.out.println("================");
		System.out.println("readonly========" + isReadOnly(fm));
		System.out.println("readonly========" + isReadOnly(fm));
		System.out.println("================");
		
		
		if (fm.getGridModel() != null) {
			cPaneModel = fm.getGridModel().getContainerPane();
		}

		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}

		
		
		buffer.append(this.appendValidateConfig(fm));
		
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		} else {
			buffer.append(" onClick=\"WdatePicker(");
			
			if(fm.getInputConstraint()!=null){
				buffer.append(fm.getInputConstraint());
			}
			buffer.append(")\" ");
		}

		buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");

		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
	}


}
