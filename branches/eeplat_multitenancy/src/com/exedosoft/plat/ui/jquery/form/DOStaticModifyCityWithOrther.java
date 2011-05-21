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

public class DOStaticModifyCityWithOrther extends DOBaseForm {

	public DOStaticModifyCityWithOrther() {
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
		buffer.append("<div style='height: 25px;width: 330px;' align='left'>ÀàÐÍ£º&nbsp;");
		buffer.append("<select id='leixingtype' name='leixingtype' onchange='xjchange()' style='border:#B3B3B3 1px solid;size:1.25;position: relative;top: 2px;'>");
		buffer.append("<option value='1'>³ö²î</option>");
		buffer.append("<option value='2'>ÖÐ×ª</option>");
		buffer.append("<option value='3'>·µ»Ø</option>");
		buffer.append("<option value='4'>ÐÝ¼Ù³ö</option>");
		buffer.append("<option value='5'>ÐÝ¼Ù¹é</option>");
		buffer.append("</select>");
		buffer.append("&nbsp;&nbsp;");
		
		String vacationtype = getCurrValue(theValue,fm);
		if(vacationtype == null) {
			buffer.append("<select id='vacationtype' name='vacationtype' style='border:#B3B3B3 1px solid;size:1.25;position: relative;top: 2px;display: none;'>");
			buffer.append("<option value='' selected='selected'></option>");
			buffer.append("<option value='1'>ÐÝ¼Ù</option>");
			buffer.append("<option value='2'>ÊÂ¼Ù</option>");
			buffer.append("<option value='3'>µ¹ÐÝ</option>");
			buffer.append("<option value='4'>²¡¼Ù</option>");
			buffer.append("</select>");
		} else {
			buffer.append("<select id='vacationtype' name='vacationtype' style='border:#B3B3B3 1px solid;size:1.25;position: relative;top: 2px;display: none;'>");
			buffer.append("<option value='1' ");
			if(vacationtype.trim().equals("1"))
				buffer.append("selected='selected'");
			buffer.append(">ÐÝ¼Ù</option>");
			buffer.append("<option value='2' ");
			if(vacationtype.trim().equals("2"))
				buffer.append("selected='selected'");
			buffer.append(">ÊÂ¼Ù</option>");
			buffer.append("<option value='3' ");
			if(vacationtype.trim().equals("3"))
				buffer.append("selected='selected'");
			buffer.append(">µ¹ÐÝ</option>");
			buffer.append("<option value='4' ");
			if(vacationtype.trim().equals("4"))
				buffer.append("selected='selected'");
			buffer.append(">²¡¼Ù</option>");
			buffer.append("</select>");
		}
		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			buffer.append(fm.getNote());
		}

		buffer.append("<font color='red' style='position: relative;left: 5px;'>*</font>");
		
		buffer.append("</div>");
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("var modifytype = document.getElementById(\"modifytype\");");
		buffer.append("var leixingtype = document.getElementById(\"leixingtype\");");
		buffer.append("var vacationtype = document.getElementById(\"vacationtype\");");
		buffer.append("leixingtype.value = modifytype.value;");  
		buffer.append("var index = leixingtype.value;");
		buffer.append("if(index == 4) {");
		buffer.append("vacationtype.style.display='';");
		buffer.append("} else {");
		buffer.append("vacationtype.style.display='none';");
		buffer.append(" }");
		buffer.append("function xjchange() {");
		buffer.append("index = leixingtype.value;");
		buffer.append("modifytype.value = index;");
		buffer.append("if (index == 4) {");
		buffer.append("vacationtype.style.display = '';");
		buffer.append("} else {");
		buffer.append("vacationtype.value = '';");
		buffer.append("vacationtype.style.display = 'none';");
		buffer.append("}");
		buffer.append("if($.trim(lasttype.value) == \"\") {");
		buffer.append("ifcitymodify();}");
		buffer.append("else {ifbcitynochange();}");
		buffer.append(" }");
		
		buffer.append("</script>");

		return buffer.toString();
	}

	String getCurrValue(String modifytype, DOFormModel fm) {
		String vacationtype = null;
		if(modifytype != null && "4".equals(modifytype)) {
			if(fm.getData() != null)
				vacationtype = fm.getData().getValue("vacationtype");
		} 
		return vacationtype;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
