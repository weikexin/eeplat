package com.exedosoft.plat.action.customize.archive;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;

public class SaveArchiveAndDetails extends DOAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7282175273013557576L;

	@Override
	public String excute() throws ExedoException {

		BOInstance  formData =  DOGlobals.getInstance().getRuleContext().getFormInstance();

			
		DOService insertArchAccount = DOService.getService("Arch.Account.Insert");
		//事务开始
		
//		Transaction t = Transaction.getTransaction(insertArchAccount);
//		
//		t.begin();
		///事务控制有问题
		
		insertArchAccount.invokeUpdate(formData);
		
		String[] imageTypes = formData.getValueArray("imagetype");
		String[] imageTitles = formData.getValueArray("imagetitle");
		String[] imageAlls = formData.getValueArray("ImageAll");
		
		
		DOService insertArchDetails = DOService.getService("Arch.AccountImage.Insert");
		for(int i = 0; i < imageTypes.length ; i++){
			Map<String,String> map = new HashMap<String,String>();
			map.put("imagetype", imageTypes[i]);
			map.put("imagetitle", imageTitles[i]);
			map.put("imageall", imageAlls[i]);
			
			System.out.println("imageAll" + i +":::" + imageAlls[i]);
			insertArchDetails.invokeUpdate(map);
		}
		
		return "保存成功";
	}
	
	public static void main(String[] args){
		
		DOPaneModel pm = DOPaneModel.getPaneModelByName("aaa_MainContent");
		System.out.println(pm);
		
		System.out.println(pm.getResource());
		
//		BOInstance aValue = null;
//		try {
//			aValue = BOInstance.fromJSONString("{'resend_date':'5'}");
//			System.out.println("BOInstance::::" + aValue);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
	}

}
