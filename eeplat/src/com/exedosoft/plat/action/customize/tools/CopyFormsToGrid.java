package com.exedosoft.plat.action.customize.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOFormTarget;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;

public class CopyFormsToGrid extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3155689928718730452L;

	@Override
	public String excute() throws ExedoException {

		if (this.service==null || this.service.getTempSql() == null) {

			setEchoValue(I18n.instance().get("未配置SQL 语句"));
			return NO_FORWARD;
		}


		String gridModelUid = this.actionForm.getValue("gridModelUid");
		if (gridModelUid == null) {
			setEchoValue(I18n.instance().get("没有选择表格！"));
			return NO_FORWARD;
		}

		String[] checks = this.actionForm.getValueArray("checkinstance");
		if (checks == null) {
			setEchoValue(I18n.instance().get("没有数据！"));

			return NO_FORWARD;
		}
		
		DOService copyService = this.service;
		if("en".equals(DOGlobals.getValue("lang.local"))){
			copyService = DOService.getService("DO_UI_FormModel_COPY_TO_GRID_EN");
		}
		 
		
		Transaction t = copyService.currentTransaction();
		try {
			t.begin();
			DOBO boForm = DOBO.getDOBOByName("do_ui_formmodel");
			DOBO boFormTarget = DOBO.getDOBOByName("DO_UI_FormTargets");
			DOBO boFormLink = DOBO.getDOBOByName("DO_UI_FormLinks");
			for(int i = 0; i < checks.length ; i++){
				BOInstance biForm = boForm.getInstance(checks[i]);
				DOFormModel aFm = DOFormModel.getFormModelByID(biForm.getUid());
				
				biForm.putValue("objuid", null);
				biForm.putValue("gridModelUid", gridModelUid);
				BOInstance newBiForm = copyService.invokeUpdate(biForm);
				// //保存FormModel
				for(Iterator<DOFormTarget> itTargetGrid = aFm.getTargetGridModels().iterator();itTargetGrid.hasNext(); ){
					DOFormTarget aFt = itTargetGrid.next();
					BOInstance biFt = boFormTarget.getInstance(aFt.getObjUid());
					biFt.putValue("objuid", null);
					biFt.putValue("formUid", newBiForm.getUid());
					boFormTarget.getDInsertService().invokeUpdate(biFt);						
				}

					// //FormModel linkForms
				for(Iterator<DOFormModel> itLinkForms = aFm.getLinkForms().iterator(); itLinkForms.hasNext();){
						DOFormModel linkForm = itLinkForms.next();
						Map<String,String> paras = new HashMap<String,String>();
						paras.put("formuid", newBiForm.getUid());
						paras.put("linkformuid", linkForm.getObjUid());
						boFormLink.getDInsertService().invokeUpdate(paras);
				}
				
				
			}
			t.end();
		} catch (Exception e) {
			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DEFAULT_FORWARD;

	}

}
