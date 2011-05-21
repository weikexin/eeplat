package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOStaticListPopupCity extends DOBaseForm {

	public DOStaticListPopupCity() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel fm = (DOFormModel) iModel;

		String theValue = fm.getValue();
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("	<input type='hidden'   name='").append(
				fm.getFullColName()).append("' id='").append(
				fm.getFullColName()).append("'");
		String city = "";
		String citytype = fm.getFullColName();
		if(citytype != null && "begincitytype".equals(citytype)) {
			city = "begincity";
		} else if(citytype != null && "endcitytype".equals(citytype)) {
			city = "endcity";
		}
		
		if (theValue != null) {
			buffer.append(" value='").append(theValue).append("'");
		} else {
			buffer.append(" value='1'");
		}

		buffer.append(this.appendValidateConfig(fm));
		buffer.append("/>");
		buffer.append("<div style='height: 25px;float: left;' align='left'>");
		if(citytype != null && "begincitytype".equals(citytype)) {
			buffer.append("<select id='beginaddress' name='beginaddress' onchange='beginchange()' style='border:#B3B3B3 1px solid;size:1.25;position: relative;top: 2px;'>");
			
		} else if(citytype != null && "endcitytype".equals(citytype)) {
			buffer.append("<select id='endaddress' name='endaddress' onchange='endchange()' style='border:#B3B3B3 1px solid;size:1.25;position: relative;top: 2px;'>");
			
		}
		buffer.append("<option value='1'>公司或本地</option>");
		buffer.append("<option value='2'>出差地A</option>");
		buffer.append("<option value='3'>出差地B</option>");
		buffer.append("/select>");
		buffer.append("&nbsp;&nbsp;&nbsp; ");
		
		String cccity = null;
		if(fm.getData() != null) {
			if(citytype != null && "begincitytype".equals(citytype)) {
				cccity = fm.getData().getValue("begincity");	
			} else if(citytype != null && "endcitytype".equals(citytype)) {
				cccity = fm.getData().getValue("endcity");
			}
		}
			
		if(cccity == null) {
			buffer.append("<input id='").append(city).append("' name='").append(city).append("' style='border:#B3B3B3 1px solid;display: none;width:80px;position: relative;left: 15px;' " +
			"  onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\" type='text' value=''");
			buffer.append(" title='").append(fm.getL10n().trim()).append("'/>");
			
		} else {
			buffer.append("<input id='").append(city).append("' name='").append(city).append("' style='border:#B3B3B3 1px solid;display: none;width:80px;position: relative;left: 15px;' " +
			"  onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\" type='text' value='").append(cccity).append("' ");
			buffer.append(" title='").append(fm.getL10n().trim()).append("'/>");
			
			if(citytype != null && "begincitytype".equals(citytype)) {
				buffer.append("<input id='").append(city+"dis").append("' name='").append(city+"dis").append("' style='border:#B3B3B3 1px solid;display: none;width:80px;position: relative;left: 15px;' " +
				"  onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\" type='text' value='").append(cccity).append("' ");
				buffer.append(" title='").append(fm.getL10n().trim()).append("'/>");
			}
			
		}
		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			buffer.append(fm.getNote());
		}

		if (fm.isNotNull() && citytype != null && "endcitytype".equals(citytype)) {
			buffer.append("&nbsp;<font color='red' style='position: relative;left: 15px;'>*</font>");
		}
		
		
		if(citytype != null && "begincitytype".equals(citytype)) {
			buffer.append("</div>");
			buffer.append("<script type=\"text/javascript\">");
			buffer.append("var beginseladdressdis = document.getElementById(\"").append(city+"dis").append("\");");
			buffer.append("var beginseladdress = document.getElementById(\"").append(city).append("\");");
			buffer.append("var beginwaddress = document.getElementById(\"").append(citytype).append("\");");
			buffer.append("var beginaddress = document.getElementById(\"beginaddress\");");
			buffer.append("beginaddress.value = beginwaddress.value;"); 
			buffer.append("function beginchange() {");
			buffer.append("var beginindex = beginaddress.value;");
			buffer.append("beginwaddress.value = beginindex;");
			buffer.append("if (beginindex == 2 || beginindex == 3) {");
			buffer.append("beginseladdress.style.display = '';");
			buffer.append("if($.trim(lasttype.value) != \"\") {beginaddress.disabled=true;beginseladdressdis.value=beginseladdress.value;beginseladdress.style.display = 'none';beginseladdressdis.style.display = '';beginseladdressdis.disabled=true;}");
			buffer.append("} else {");
			buffer.append("if($.trim(lasttype.value) == \"\") {");
			buffer.append("beginseladdress.value = '';");
			buffer.append("beginseladdress.style.display = 'none';");
			buffer.append("}else {beginaddress.disabled=true;if(beginseladdressdis != null) {beginseladdressdis.style.display = 'none';}}}");
			buffer.append(" }");
		} else if(citytype != null && "endcitytype".equals(citytype)) {
			buffer.append("</div>");
			buffer.append("<script type=\"text/javascript\">");
			buffer.append("var endseladdress = document.getElementById(\"").append(city).append("\");");
			buffer.append("var endwaddress = document.getElementById(\"").append(citytype).append("\");");
			buffer.append("var endaddress = document.getElementById(\"endaddress\");");
			buffer.append("endaddress.value = endwaddress.value;"); 
			buffer.append("function endchange() {");
			buffer.append("var endindex = endaddress.value;");
			buffer.append("endwaddress.value = endindex;");
			buffer.append("if (endindex == 2 || endindex == 3) {");
			buffer.append("endseladdress.style.display = '';");
			buffer.append("} else {");
			buffer.append("endseladdress.value = '';");
			buffer.append("endseladdress.style.display = 'none';");
			buffer.append("}");
			buffer.append("}");
		}
		
		
		
		buffer.append("</script>");

		return buffer.toString();
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
