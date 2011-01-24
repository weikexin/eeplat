package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.DOValueService;

public class DOValueResultListService extends DOBaseForm {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		// // System.out.println("The Line Data================" +
		// property.getData());

		String theValue = property.getValue();

		// / System.out.println("The Value:::" + theValue);

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
				BOInstance data = property.getData();
				if (bi != null) {
					// ///////////////////////////////////采用弹出方式
					DOPaneModel theModel = property.getLinkPaneModel();
					if (theModel != null
							&& theModel.getLinkType() != null
							&& (theModel.getLinkType().intValue() == DOPaneModel.LINKTYPE_TREEMODEL)) {
						theModel = null;
					}
					
					if(bi.getName()!=null){
						return DOValueService.stardardOnlyPane(property,bi.getName()).toString();
					}else{
						return DOValueService.stardardOnlyPane(property,property.getValue()).toString();
					}
					
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
						
						if(bi.getName()!=null){
							return DOValueService.stardardOnlyPane(property,bi.getName()).toString();
						}else{
							return DOValueService.stardardOnlyPane(property,property.getValue()).toString();
						}
					}
				}
				return buffer.toString();

			}

		}//68047655
		
		if (theValue == null || "null".equals(theValue)) {
			return "&nbsp;";
		}
		return DOValueService.stardardOnlyPane(property,theValue).toString();
	}

	static BOInstance getAInstance(DOFormModel property, DOBO corrBO,
			String theValue) {
		BOInstance bi = null;
		if (property.getLinkService() != null
			) {
			bi = property.getLinkService().getInstance(theValue);

		} else if (corrBO != null) {
			bi = corrBO.getInstance(theValue);
		}
		return bi;
	}

}
