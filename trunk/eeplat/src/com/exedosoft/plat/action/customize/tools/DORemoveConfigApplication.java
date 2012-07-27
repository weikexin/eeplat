package com.exedosoft.plat.action.customize.tools;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;

@SuppressWarnings("unused")
public class DORemoveConfigApplication extends DOAbstractAction {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8093868865224942852L;
	private static Log log = LogFactory.getLog(DORemoveConfigApplication.class);


	public String excute() {
		
		//DOService DO_BusiPackage_deletebyapplicationuid = DOService.getService("DO_BusiPackage_deletebyapplicationuid");
		
		DOBO bo = DOBO.getDOBOByName("DO_Application");
		BOInstance<?> biApp = bo.getCorrInstance();
		
		DOService deleteApp = DOService.getService("DO_Application_Delete");
		DOService deletePackages = DOService.getService("DO_BusiPackage_deletebyapplicationuid");
		DOService findBOByPackage = DOService.getService("DO_BO_FindByBPUid");
		
		Transaction t = deleteApp.currentTransaction();
		t.begin();
		try {
			DOService service = DOService.getService("DO_BusiPackage_ofapplicationuid");
			List<BOInstance<?>> listP = service.invokeSelect(biApp.getUid());
			for(BOInstance<?> biPackage : listP){
				List<BOInstance<?>> listBO = findBOByPackage.invokeSelect(biPackage.getUid());
				for(BOInstance<?>  biBO : listBO){
					DORemoveConfigDOBO.removeBO(biBO);
				}
			}

			///delete packages 
			deletePackages.invokeUpdate(biApp.getUid());
			///delete App
			deleteApp.invokeUpdate(biApp.getUid());
			
		}catch (ExedoException e) {
			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.end();
		
		//DO_BusiPackage_deletebyapplicationuid 
		
		//DO_BusiPackage_ofapplicationuid 
		
		//

	
		return DEFAULT_FORWARD;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
