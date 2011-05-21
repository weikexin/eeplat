package com.exedosoft.plat.ui.jquery.form;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.StringUtil;

public class DOInputTextBetween extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();

		getAInputTimeStr(fm, buffer, "");
		buffer.append("&nbsp; 至 &nbsp;");
		getAInputTimeStr(fm, buffer, "2");

		return buffer.toString();
	}

	/**
	 * @param fm
	 * @param buffer
	 */
	private void getAInputTimeStr(DOFormModel fm, StringBuffer buffer,
			String aNext) {

		buffer.append("<input type='text' name='").append(fm.getFullColName()).append(aNext)
				.append("' id='").append(fm.getFullColName()).append(aNext).append("'");

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

		buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");

		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
