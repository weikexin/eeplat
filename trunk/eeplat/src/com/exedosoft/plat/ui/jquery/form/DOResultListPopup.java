package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;

public class DOResultListPopup extends DOBaseForm {

	public DOResultListPopup() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		return getPopupForm(property);
	}

	/**
	 * 获取动态列表形式的Select Form
	 * 
	 * @param property
	 *            TODO
	 * @param db
	 * @return
	 */
	String getPopupForm(DOFormModel fm) {

		if (fm.getLinkService() == null) {
			return "&nbsp;";
		}
		StringBuffer buffer = new StringBuffer();

		String theValue = fm.getValue();

		// / System.out.println("The Value:::" + theValue);

		if (theValue == null && fm.getData() != null
				&& fm.getRelationProperty() != null) {
			theValue = fm.getData().getValue(
					fm.getRelationProperty().getColName());

		}

		BOInstance data = null;
		
		if (theValue != null && !"".equals(theValue.trim())) {

			DOBO corrBO = fm.getLinkBO();

			if (corrBO == null && fm.getLinkService() != null) {
				corrBO = fm.getLinkService().getBo();
			}
			data = DOValueResultList
					.getAInstance(fm, corrBO, theValue);
		}
				
		
		

		buffer.append("	<input class='resultlistpopup' type='hidden'   name='").append(
				fm.getColName()).append("' id='").append(
				fm.getFullColID()).append("' serviceName='")
				.append(fm.getLinkService().getName())
				.append("' ");
		appendHtmlJs(buffer, fm);


		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}
		
		buffer.append(this.appendValidateConfig(fm));
		buffer.append("/>");

		buffer.append("<input  type='text' style='border:#B3B3B3 1px solid;margin-top:1px'  onchange=\"if(this.value==''){this.previousSibling.value=''};");

		buffer.append("\"")
		.append(" onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\" name='").append(fm.getFullColID())
				.append("_show' id='").append(fm.getFullColID()).append(
						"_show' class='").append(fm.getFullColID()).append(
								"_show' ");
		buffer.append(getDecoration(fm));



		
		if (data != null) {
			buffer.append(" value='").append(data.getName())
					.append("'");
		}
		
		if(data!=null){
			buffer.append(" title='").append(data.getName()).append("'");
		}else{
			buffer.append(" title='").append(fm.getL10n()).append("'");
		}	

		
		
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}



		buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");

		buffer
				.append(
						"<IMG  style='CURSOR: pointer;padding-bottom:2px;margin-left:-21px;' onclick=\"invokePopup(this")
		.append(",'").append(fm.getTargetForms())
		.append("','");
		buffer.append(
				fm.getLinkService().getBo().getValueCol()).append("',1,15");

		if (fm.getInputConstraint() != null) {
			buffer.append(",'").append(fm.getInputConstraint()).append("'");
		}
		buffer.append(")\"  src='").append(DOGlobals.PRE_FULL_FOLDER).append(
				"images/darraw.gif' align=absMiddle>");

		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			buffer.append(fm.getNote());
		}

		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		
		
		if(fm.getOnChangeJs()!=null && !"".equals(fm.getOnChangeJs())){
			buffer.append("<script>");
			
			buffer.append("if($.browser.mozilla) $('#")
			.append(fm.getFullColID())
			.append("')[0].addEventListener('DOMAttrModified',function(){")
			.append(fm.getOnChangeJs())
			.append("},false);");
			
			buffer.append("</script>");

		}
		return buffer.toString();
	}

}
