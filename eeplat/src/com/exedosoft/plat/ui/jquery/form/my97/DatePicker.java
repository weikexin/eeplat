package com.exedosoft.plat.ui.jquery.form.my97;

import java.text.SimpleDateFormat;

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
public class DatePicker extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();

		buffer.append("<input ").append(" id='")
				.append(property.getFullColID()).append("' name='").append(
						property.getFullColName()).append("'");

		buffer.append(this.appendValidateConfig(property));

		buffer.append(getDecoration(property));

		buffer.append(" title='").append(property.getL10n()).append("'");

		if (property.getValue() != null) {
			String dataValue = property.getValue();
//			if (!isValidDate(dataValue)) {
//				if (dataValue.indexOf(" ") != -1) {
//					dataValue = dataValue.split(" ")[0];
//				}
//			}
			buffer.append(" value='").append(dataValue).append("'");
		}

		buffer.append(" onClick=\"WdatePicker(");

		if (property.getInputConstraint() != null) {
			buffer.append(property.getInputConstraint());
		} else {
			buffer.append("{dateFmt:'yyyy-M-d'}");
		}

		buffer.append(")\" ");

		buffer.append(" size=\"").append(getInputSize(property)).append("\"/>");

		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}

		return buffer.toString();
	}

	public static boolean isValidDate(String aDateValue) {

		if (aDateValue == null) {
			return false;
		}
		
//		if (aDateValue.length() <= 7) {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M");
//
//			try {
//				aDateValue = aDateValue+"-01 00:00:00";
//				dateFormat.parse(aDateValue);
//				return true;
//			} catch (Exception e) {
//				return false;
//			}
//		}

		if (aDateValue.length() <= 10) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");

			try {
				dateFormat.parse(aDateValue);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		if (aDateValue.length() <= 16) {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat(
					"yyyy-M-dd HH:mm");

			try {
				dateFormat1.parse(aDateValue);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		if (aDateValue.length() <= 19) {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat(
					"yyyy-M-dd HH:mm:ss");
			try {
				dateFormat2.parse(aDateValue);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) {

		String str = "1998-08-03";

		System.out.print(DatePicker.isValidDate(str));

	}

}
