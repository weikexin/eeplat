package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOPaneModel;

public class DOExportPaneHtml extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;

	@Override
	public String excute() throws ExedoException {

	
		DOBO bo = DOBO.getDOBOByName("DO_UI_PaneModel");
		BOInstance bi = bo.getCorrInstance();
		if(bi!=null){
			DOPaneModel pm = DOPaneModel.getPaneModelByID(bi.getUid());
			this.setEchoValue(pm.getHtmlCode());
		}
		return DEFAULT_FORWARD;

	}
	

}
