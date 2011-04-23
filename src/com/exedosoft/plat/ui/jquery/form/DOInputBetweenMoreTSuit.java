package com.exedosoft.plat.ui.jquery.form;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;
import com.exedosoft.plat.ui.jquery.form.my97.DatePicker;
import com.exedosoft.plat.util.StringUtil;

public class DOInputBetweenMoreTSuit extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {
		DOFormModel property = (DOFormModel) aModel;
		//目前仅支持Text、Date、CheckBox
		List list = property.getLinkForms();
		StringBuffer buffer = new StringBuffer();
		
		boolean isLast = false;
		for(int i = 0; i < list.size(); i++) {
			DOFormModel fm = (DOFormModel)list.get(i);
			fm.setData(property.getData());
			String note = fm.getNote();
			String[] strs = null;
			
			if(i == list.size() - 1)
				isLast = true;
			
			if(isReadOnly(fm) && (fm.getUiType() == null || fm.getUiType().equals("") || !fm.getUiType().equals("CheckBox"))) {
				if(note != null && note.trim().length() > 0) {
					if(note.indexOf("&&") != -1) {
						strs = note.split("&&");
						buffer.append("&nbsp;"+strs[0]);
					} else {
						buffer.append("&nbsp;"+note);
					}
						
					getAInputTimeStr(fm, buffer, "",isLast);
					if(strs != null && strs.length == 2)
						buffer.append(strs[1]);
				} else {
					getAInputTimeStr(fm, buffer, "",isLast);
				}
				
			} else if(fm.getUiType() == null || fm.getUiType().equals("Text")) {
				if(note != null && note.trim().length() > 0) {
					if(note.indexOf("&&") != -1) {
						strs = note.split("&&");
						buffer.append("&nbsp;"+strs[0]);
					} else {
						buffer.append("&nbsp;"+note);
					}
						
					getAInputTimeStr(fm, buffer, "",isLast);
					if(strs != null && strs.length == 2)
						buffer.append(strs[1]);
				} else {
					getAInputTimeStr(fm, buffer, "",isLast);
				}
				
			} else if(fm.getUiType() != null && fm.getUiType().equals("Date")) {
				
				if(note != null && note.trim().length() > 0) {
					if(note.indexOf("&&") != -1) {
						strs = note.split("&&");
						buffer.append("&nbsp;"+strs[0]);
					} else {
						buffer.append("&nbsp;"+note);
					}
					DOInputBetweenMoreTSuit datePicker = new DOInputBetweenMoreTSuit();
					String subbuff = datePicker.getDateHtmlCode(fm,isLast);
					buffer.append(subbuff);
					if(strs != null && strs.length == 2)
						buffer.append(strs[1]);
				} else {
					DOInputBetweenMoreTSuit datePicker = new DOInputBetweenMoreTSuit();
					String subbuff = datePicker.getDateHtmlCode(fm,isLast);
					buffer.append(subbuff);
				}
			} else if(fm.getUiType() != null && fm.getUiType().equals("CheckBox")) {
				List listConf = StringUtil.getStaticList(fm.getInputConfig());
				if(note != null && note.trim().length() > 0) {
					//文字补充
					if(note.indexOf("&&") != -1) {
						strs = note.split("&&");
						buffer.append("&nbsp;"+strs[0]);
					} else {
						buffer.append("&nbsp;"+note);
					}

					//checkbox
					for (Iterator it = listConf.iterator(); it.hasNext();) {
						String[] half = (String[]) it.next();
						buffer.append("<input name=\"").append(
								fm.getFullColName());

						buffer.append("\" value=\"").append(half[0]);

						buffer.append("\"  type=\"checkbox\"");
						buffer.append(getDecoration(fm));
						
						if (isReadOnly(fm)) {
							buffer.append(" onclick='return false;'  ");
							buffer.append("  DISABLED ");

						}

						if (DOStaticList.isChecked(half[0], fm.getValue())) {
							buffer.append(" checked ");
						}
						
						buffer.append("/>");
						buffer.append(half[1]);
					}
					
					//文字补充
					if(strs != null && strs.length == 2)
						buffer.append(strs[1]);
				} else {
					//checkbox
					for (Iterator it = listConf.iterator(); it.hasNext();) {
						String[] half = (String[]) it.next();
						buffer.append("<input name=\"").append(
								fm.getFullColName());

						buffer.append("\" value=\"").append(half[0]);

						buffer.append("\"  type=\"checkbox\"");
						buffer.append(getDecoration(fm));
						
						if (isReadOnly(fm)) {
							buffer.append(" onclick='return false;'  ");
							buffer.append("  DISABLED ");

						}

						if (DOStaticList.isChecked(half[0], fm.getValue())) {
							buffer.append(" checked ");
						}

						buffer.append("/>");
						buffer.append(half[1]);
					}
				}
			} 
		}
		return buffer.toString();
	}
	
	
	/**
	 * @param fm
	 * @param buffer
	 */
	private void getAInputTimeStr(DOFormModel fm, StringBuffer buffer,
			String aNext,boolean isLast) {
		String theValue = fm.getValue();	
		
		if (isReadOnly(fm)) {
			if(theValue == null) {
				buffer.append("<span style='width:6px;'>&nbsp;&nbsp;&nbsp;&nbsp;</span>");
			} else {
				buffer.append("<span>&nbsp;" +theValue +"&nbsp;</span>");	
			}
		} else {


			buffer.append("<input type='text' name='").append(fm.getFullColName())
					.append("' id='").append(fm.getFullColName()).append("'");

			buffer.append(getDecoration(fm));


			buffer.append(" title='").append(fm.getL10n().trim()).append("'");

			


			DOPaneModel cPaneModel = null;
			
			if (fm.getGridModel() != null) {
				cPaneModel = fm.getGridModel().getContainerPane();
			}

			if (theValue != null) {

				buffer.append(" value='").append(theValue).append("'");
			}

			
			
			buffer.append(this.appendValidateConfig(fm));


			buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");

			if (fm.isNotNull() && isLast) {
				buffer.append("&nbsp;<font color='red'>*</font>");
			}
		}
	}

	public String getDateHtmlCode(DOIModel aModel, boolean isLast) {

		DOFormModel property = (DOFormModel) aModel;

		String conf = property.getInputConfig();
		String cons = property.getInputConstraint();
		System.out.println(":::config:::::::" + conf);
		System.out.println(":::constraint:::" + cons);
		
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

		if (property.isNotNull() && isLast) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}

		return buffer.toString();
	}
}
