package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.gene.jquery.PropertyManager;

public class DOGeneConfigBuildRelation extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4555077593344493040L;

	@Override
	public String excute() throws ExedoException {
		
		DOBO bo = DOBO.getDOBOByName("do_bo");
		BOInstance instance = bo.getCorrInstance();
		if(instance==null){
			this.setEchoValue("没有数据!");
			return NO_FORWARD;
		}
		
		String colName =  this.actionForm.getValue("col_name");
		String dobouid = this.actionForm.getValue("bouid");
		
		if(colName==null && dobouid ==null){
			this.setEchoValue("字段名称或者类型没有定义!");
			return NO_FORWARD;
		}
		
		DOBO linkBO = DOBO.getDOBOByID(dobouid);
		
		PropertyManager pm = new PropertyManager();
		pm.buildRelation(colName, linkBO);

		return DEFAULT_FORWARD;
	}

}
