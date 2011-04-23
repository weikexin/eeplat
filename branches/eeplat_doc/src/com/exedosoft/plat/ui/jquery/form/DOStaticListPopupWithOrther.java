package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOStaticListPopupWithOrther extends DOBaseForm {

	public DOStaticListPopupWithOrther() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel fm = (DOFormModel) iModel;

		String theValue = fm.getValue();
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("	<input type='hidden'   name='").append(
				fm.getFullColName()).append("' id='").append(
				fm.getFullColName()).append("'");
		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		} else {
			buffer.append(" value='1'");
		}

		buffer.append(this.appendValidateConfig(fm));
		buffer.append("/>");
		buffer.append("<div style='height: 25px;width: 230px;' align='left'>");
		buffer.append("<select id='address' name='address' onchange='citychange()' style='border:#B3B3B3 1px solid;size:1.25;position: relative;top: 2px;'>");
		buffer.append("<option value='1'>公司或本地</option>");
		buffer.append("<option value='2'>出差地A</option>");
		buffer.append("<option value='3'>出差地B</option>");
		//buffer.append("<option value='4'>休假</option>");
		buffer.append("/select>");
		buffer.append("&nbsp;&nbsp;&nbsp; ");
		
		String wseladdress = getCurrValue(theValue,fm);
		if(wseladdress == null) {
			buffer.append("<input id='wseladdress' name='wseladdress' style='border:#B3B3B3 1px solid;display: none;width:80px;position: relative;left: 15px;' " +
			"  onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\" type='text' value=''");
			buffer.append(" title='").append(fm.getL10n().trim()).append("'/>");
		} else {
			buffer.append("<input id='wseladdress' name='wseladdress' style='border:#B3B3B3 1px solid;display: none;width:80px;position: relative;left: 15px;' " +
			"  onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\" type='text' value='").append(wseladdress).append("' ");
	buffer.append(" title='").append(fm.getL10n().trim()).append("'/>");
		}
		
		String wvacationtype = null;
		if(fm.getData() != null) {
			wvacationtype = fm.getData().getValue("wvacationtype");
		}
		if(wvacationtype == null) {
			buffer.append("<select id='wvacationtype' name='wvacationtype' style='border:#B3B3B3 1px solid;size:1.25;position: relative;left: 15px;top: 2px;display: none;'>");
			buffer.append("<option value='' selected='selected'></option>");
			buffer.append("<option value='1'>公休假日</option>");
			buffer.append("<option value='2'>法定休假日</option>");
			buffer.append("<option value='3'>病事假</option>");
			buffer.append("</select>");
		} else {
			buffer.append("<select id='wvacationtype' name='wvacationtype' style='border:#B3B3B3 1px solid;size:1.25;position: relative;left: 15px;top: 2px;display: none;'>");
			buffer.append("<option value='1' ");
			if(wvacationtype.trim().equals("1"))
				buffer.append("selected='selected'");
			buffer.append(">公休假日</option>");
			buffer.append("<option value='2' ");
			if(wvacationtype.trim().equals("2"))
				buffer.append("selected='selected'");
			buffer.append(">法定休假日</option>");
			buffer.append("<option value='3' ");
			if(wvacationtype.trim().equals("3"))
				buffer.append("selected='selected'");
			buffer.append(">病事假</option>");
			buffer.append("</select>");
		}
		
		
		
		
		
		
		
		
		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			buffer.append(fm.getNote());
		}

		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red' style='position: relative;left: 15px;'>*</font>");
		}
		buffer.append("</div>");
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("var seladdress = document.getElementById(\"wseladdress\");");
		buffer.append("var waddress = document.getElementById(\"waddress\");");
		buffer.append("var address = document.getElementById(\"address\");");
		buffer.append("var wvacationtype = document.getElementById(\"wvacationtype\");");
		buffer.append("if(address != null) {address.value = waddress.value;}");  
		buffer.append("var index = address.value;");
		buffer.append("if(index == 2 || index == 3) {");
		buffer.append("wvacationtype.style.display='none';");
		buffer.append("seladdress.style.display='';");
		buffer.append("} else if(index == 4) {");
		buffer.append("seladdress.style.display='none';");
		buffer.append("wvacationtype.style.display='';");
		buffer.append("} else {");
		buffer.append("seladdress.value = '';");
		buffer.append("seladdress.style.display='none';");
		buffer.append("wvacationtype.value = '';");
		buffer.append("wvacationtype.style.display='none';");
		buffer.append(" }");
		buffer.append("function citychange() {");
		buffer.append("if(address != null)  {index = address.value;}");
		buffer.append("waddress.value = index;");
		buffer.append("if(index == 2 || index == 3) {");
		buffer.append("wvacationtype.style.display='none';");
		buffer.append("seladdress.style.display='';");
		buffer.append("} else if(index == 4) {");
		buffer.append("seladdress.style.display='none';");
		buffer.append("wvacationtype.style.display='';");
		buffer.append("} else {");
		buffer.append("seladdress.value = '';");
		buffer.append("seladdress.style.display='none';");
		buffer.append("wvacationtype.value = '';");
		buffer.append("wvacationtype.style.display='none';");
		buffer.append(" }");
		buffer.append(" }");
		
		buffer.append("</script>");

		return buffer.toString();
	}

	String getCurrValue(String waddress, DOFormModel fm) {
		String seladdress = null;
		if(waddress != null && !"1".equals(waddress)) {
			if(fm.getData() != null)
				seladdress = fm.getData().getValue("wseladdress");
		}
		return seladdress;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
