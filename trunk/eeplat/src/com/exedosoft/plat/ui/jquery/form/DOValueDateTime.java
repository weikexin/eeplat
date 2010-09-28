package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueDateTime implements DOIView {

	public String getHtmlCode(DOIModel aModel) {
		DOFormModel fm = (DOFormModel)aModel;
	
		if(fm.getValue()!=null && fm.getRelationProperty()!=null){
			return StringUtil.getDateStr(fm.getData().getDateValue(fm.getRelationProperty()), "yyyy-MM-dd HH:mm");
		}
		else{
			return "&nbsp;";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
