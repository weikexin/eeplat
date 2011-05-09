package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.Escape;


public class DOInputText extends DOBaseForm {

	@Override
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

		//点击执行js
		if(fm.getDoClickJs() != null && !"".equals(Escape.unescape(fm.getDoClickJs().trim()))) {
			buffer.append(" onClick='").append(Escape.unescape(fm.getDoClickJs())).append("'");
		}
		
		
		//值改变,即键入时执行js
		if(fm.getOnChangeJs() != null && !"".equals(Escape.unescape(fm.getOnChangeJs().trim()))) {
			buffer.append(" onchange='").append(Escape.unescape(fm.getOnChangeJs())).append("' oninput='").append(Escape.unescape(fm.getOnChangeJs())).append("' onpropertychange='").append(Escape.unescape(fm.getOnChangeJs())).append("'");
		}
		
		//获得焦点执行js
		if(fm.getEscapeOnFocusJs() != null && !"".equals(Escape.unescape(fm.getEscapeOnFocusJs().trim()))) {
			buffer.append(" onfocus='").append(Escape.unescape(fm.getEscapeOnFocusJs())).append("'");
		}
		
		//失去焦点执行js
		if(fm.getEscapeOnBlurJs() != null && !"".equals(Escape.unescape(fm.getEscapeOnBlurJs().trim()))) {
			buffer.append(" onblur='").append(Escape.unescape(fm.getEscapeOnBlurJs())).append("'");
		}

		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}
		
		//输入限长字符或手机时，输入限长度
		if(fm.getExedojoType() != null && fm.getExedojoType().trim().length()>0) {
			String maxlen = fm.getExedojoType().trim();
			System.out.println("fm.getExedojoType().trim()===="+ maxlen);
			if(maxlen.startsWith(";")){
				maxlen = maxlen.substring(1).trim();
				if(maxlen.matches("^\\d+$")) {
					buffer.append(" maxlength='").append(maxlen).append("' ");
				}
			} else if("MoBile".equals(maxlen)) {
				buffer.append(" maxlength='11' ");
			} 
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
