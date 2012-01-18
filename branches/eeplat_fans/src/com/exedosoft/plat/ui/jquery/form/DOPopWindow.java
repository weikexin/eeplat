package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.id.UUIDHex;

public class DOPopWindow extends DOBaseForm {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		StringBuffer buffer = new StringBuffer();

		String widgetID = "a" + UUIDHex.getInstance().generate();

		buffer
				.append("<div dojoType='DropdownPopWindow' id='")
				.append(widgetID)
				.append("' href='")
				.append(property.getLinkPaneModel().getFullCorrHref(null, null))
				.append("&dropDownID=").append(widgetID);

		buffer.append("' aHiddenName='").append(property.getFullColName());

		if (property.getValue() != null
				&& !property.getValue().trim().equals("")) {

			DOBO doBO = null;
			if (property.getLinkBO() != null) {
				doBO = property.getLinkBO();
			} else if (property.getLinkService() != null) {
				doBO = property.getLinkService().getBo();
			}

			BOInstance bi = DOValueResultList.getAInstance(property, doBO,
					property.getValue());

			System.out.println("BOInstance===========" + bi);

			if (bi != null) {

				buffer.append("' aShowValue='").append(bi.getName()).append(
						"' aHiddenValue='").append(bi.getUid());
			}
		}

		buffer.append("' executeScripts='true'  containerToggle='wipe'></div>");
		return buffer.toString();

		// DOFormModel property = (DOFormModel) iModel;
		//
		// StringBuffer buffer = new StringBuffer();
		// buffer.append("<input type='hidden' name='")
		// .append(property.getColName())
		// .append("'> <input type='text' ");
		//
		// if (property.getIsNull() != null
		// && !property.getIsNull().booleanValue()) {
		// buffer.append(" required='true' ");
		// }
		// if (property.getValue() != null) {
		// buffer.append(" value = '").append(property.getValue()).append("'");
		// }
		// // if (isReadOnly(property)) {
		// buffer.append(" readonly ");
		//
		// //}
		// buffer.append("
		// size=\"").append(getInputSize(property)).append("\">");
		//		
		// buffer.append("<img
		// src='js/dojo/src/widget/templates/images/combo_box_arrow.png' " )
		// .append(" onclick=\"javascript:doAjax.refresh('_opener','")
		// .append(property.getLinkPaneModel().getFullCorrHref(null))
		// .append("&dropDownID=")
		// .append(property.getColName())
		// .append("')\" ")
		// .append("style='vertical-align:middle; cursor:pointer; cursor:hand;'
		// />");
		//		
		// return buffer.toString();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
