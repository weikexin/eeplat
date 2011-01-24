package com.exedosoft.plat.ui.jquery.form.my97;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;

/**
 * my97 是非常强大的日期控件。 可以通过配置类改变其显示行为。 默认是日期显示，可以显示成日期时间，时间等。
 * 日期时间的配置：{dateFmt:'yyyy-MM-dd HH:mm:ss'}
 * 时间的配置如：{skin:'whyGreen',dateFmt:'H:mm:ss'}
 * 
 * @author IBM
 * 
 */
public class DatePickerTomorrow extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();

		String theValue = null;
		String regx = "yyyy-MM-dd";
		int numDay = 0;
		if (property.getScriptValue() != null
				&& !"".equals(property.getScriptValue())) {
			numDay = Integer.parseInt(property.getScriptValue().trim());
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++" + regx);
		SimpleDateFormat format = new SimpleDateFormat(regx);
		Date dt = new Date();
		try {
			// dt = format.parse("2008-01-01 12:00:00");
			// Calendar calendar=Calendar.getInstance();
			// calendar.setTime(dt);
			// calendar.add(Calendar.HOUR , 3);
			// theValue=format.format(new java.util.Date());
			theValue = format.format(new Date(dt.getTime() + numDay * 24 * 60
					* 60 * 1000));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		buffer.append("<input ").append(" id='")
				.append(property.getFullColID()).append("' name='").append(
						property.getFullColName()).append("'");

		buffer.append(this.appendValidateConfig(property));

		buffer.append(getDecoration(property));

		buffer.append(" title='").append(property.getL10n()).append("'");

		// if (property.getValue() != null) {
		// String dataValue = property.getValue();
		// dataValue = dataValue.substring(0, 10);
		buffer.append(" value='").append(theValue).append("'");
		// }

		buffer.append(" onClick=\"WdatePicker(");

		if (property.getInputConstraint() != null) {
			buffer.append(property.getInputConstraint());
		} else {
			buffer.append("{dateFmt:'yyyy-M-d'}");
		}

		buffer.append(")\" ");

		if (isReadOnly(property)) {
			buffer.append(" readonly='readonly' ");

		}

		buffer.append(" size=\"").append(getInputSize(property)).append("\"/>");

		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}

		return buffer.toString();
	}

}
