package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.gene.jquery.PropertyManager;
import com.exedosoft.plat.util.I18n;

public class DOGeneConfigAddProperty extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4555077593344493040L;

	@Override
	public String excute() throws ExedoException {
		
		DOBO bo = DOBO.getDOBOByName("do_bo");
		BOInstance instance = bo.getCorrInstance();
		if(instance==null){
			this.setEchoValue(I18n.instance().get("没有数据!"));
			return NO_FORWARD;
		}
		
		String colName =  this.actionForm.getValue("col_name");
		String type = this.actionForm.getValue("dbtype");
		String dbsize = this.actionForm.getValue("dbsize");
		String l10n = this.actionForm.getValue("l10n");
		
		if(colName==null || type ==null || dbsize==null){
			this.setEchoValue(I18n.instance().get("字段名称或者类型或者长度没有定义!"));
			return NO_FORWARD;
		}

		
		DOBO thisBO = DOBO.getDOBOByID(instance.getUid());
		
		PropertyManager pm = new PropertyManager();
		pm.addProperty(thisBO, colName,l10n,Integer.parseInt( type ),Integer.parseInt(dbsize));

		return DEFAULT_FORWARD;
	}

}
