package com.exedosoft.plat.ui.jquery.form;

import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;

public class DOValueResultList extends DOBaseForm {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		String theValue = property.getValue();

		if (theValue == null && property.getData() != null
				&& property.getRelationProperty() != null) {
			theValue = property.getData().getValue(
					property.getRelationProperty().getColName());
		}

		if (theValue != null && !"".equals(theValue.trim())) {

			DOBO corrBO = property.getLinkBO();

			if (corrBO == null && property.getLinkService() != null) {
				corrBO = property.getLinkService().getBo();
			}
			if (theValue.indexOf(";") == -1) {
				BOInstance bi = getAInstance(property, corrBO, theValue);

				// /
				// System.out.println("BOINSTANCE++++++++++++++++++++++++++++++++++++++"
				// + bi);

				if (bi != null) {
					// ///////////////////////////////////采用弹出方式
					DOPaneModel theModel = property.getLinkPaneModel();
					if (theModel != null
							&& theModel.getLinkType() != null
							&& (theModel.getLinkType().intValue() == DOPaneModel.LINKTYPE_TREEMODEL)) {
						theModel = null;
					}
					return this.getAjaxLink(bi, theModel, "_opener",
							property.getEscapeDOClickJs());// /property.getContainerPaneName()
				}
			} else {
				String[] strs = theValue.split(";");
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < strs.length; i++) {
					String aInsUid = strs[i];
					BOInstance bi = corrBO.getInstance(aInsUid);
					if (bi != null) {
						DOPaneModel theModel = property.getLinkPaneModel();
						if (theModel != null
								&& theModel.getLinkType() != null
								&& (theModel.getLinkType().intValue() == DOPaneModel.LINKTYPE_TREEMODEL)) {
							theModel = null;
						}
						buffer.append(
								this.getAjaxLink(bi, theModel,
										"_opener", property
												.getDoClickJs()))// property.getContainerPaneName()
								.append(";");
					}
				}
				return buffer.toString();

			}

		}
		if (theValue == null || "null".equals(theValue)) {
			return "&nbsp;";
		}

		return theValue;
	}

	public static BOInstance getAInstance(DOFormModel property, DOBO corrBO,
			String theValue) {
		BOInstance bi = null;
		DOService linkService = property.getLinkService();

		if (linkService != null && linkService.hasOnePara()) {
			List list = linkService.invokeSelect(theValue);
			if(list.size() == 1){
				bi = (BOInstance)list.get(0);
			}else {
				bi = corrBO.getInstance(theValue);
			}
		}
		if (corrBO != null && bi == null) {
			bi = corrBO.getInstance(theValue);
		}
		return bi;
	}

}
