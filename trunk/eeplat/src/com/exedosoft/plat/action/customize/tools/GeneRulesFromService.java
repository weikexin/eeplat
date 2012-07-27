package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.util.I18n;

public class GeneRulesFromService extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5689928718730452223L;

	@Override
	public String excute() throws ExedoException {

		if (this.service == null ) {
			this.setEchoValue(I18n.instance().get("没有定义服务!"));
			return NO_FORWARD;
		}

		Transaction t = this.service.currentTransaction();
		try {
			t.begin();
			DOBO boService = DOBO.getDOBOByName("do_service");
			BOInstance biService = boService.getCorrInstance();
			BOInstance paras = new BOInstance();
			paras.putValue("serviceUid", biService.getUid());
			paras.putValue("onlyRun", "1");
			paras.putValue("condition", "true");
			paras.putValue("conditionType", "1");//1 代表script 
			paras.putValue("name", "Rule_" + biService.getValue("name"));
			paras.putValue("l10n", "Rule_" + biService.getValue("l10n"));
			paras.putValue("salience", "5");
			paras.putValue("bouid", biService.getValue("bouid"));
			this.service.invokeUpdate(paras);
			t.end();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		this.setEchoValue(I18n.instance().get("成功生成规则!"));
		return DEFAULT_FORWARD;

	}

}
