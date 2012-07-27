package com.exedosoft.plat;

import com.exedosoft.plat.bo.aop.DOFormModelAOP;
import com.exedosoft.plat.ui.DOFormModel;

public class FAOP implements DOFormModelAOP {

	@Override
	public Boolean isAccess(DOFormModel formModel) {
		// TODO Auto-generated method stub
		System.out.println("DOFormModel::::::::::::::" + formModel);
		
//		String url = formModel.getNote();
//		SessionBean sessionBean = null;
//		
//		boolean b = callYSAuth(url,sessionBean);
		return Boolean.FALSE;
	}
	
	

}
