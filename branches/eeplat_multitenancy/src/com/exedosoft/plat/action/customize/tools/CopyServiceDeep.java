package com.exedosoft.plat.action.customize.tools;

import java.util.Iterator;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOParameterService;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.DOServiceRule;

public class CopyServiceDeep extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5689928718730452223L;

	@Override
	public String excute() throws ExedoException {

		if (this.service == null || this.service.getTempSql() == null) {
			System.out.println("未配置SQL 语句");
			this.setEchoValue("未配置SQL 语句");
			return NO_FORWARD;
		}

		DOBO boService = DOBO.getDOBOByName("do_service");

		BOInstance biService = boService.getCorrInstance();
		DOService dos = DOService.getServiceByID(biService.getUid());

		copyService(biService, dos);
		this.setEchoValue("复制成功,请点击左侧树节点进行操作!");
		return DEFAULT_FORWARD;

	}

	public static BOInstance copyService(BOInstance biService, DOService dos) {

		DOService thisService = DOService.getService("DO_Service_COPYDEEP");

		Transaction t = thisService.currentTransaction();
		try {
			t.begin();
			DOBO boServicePara = DOBO.getDOBOByName("DO_Parameter_Service");
			DOBO boServiceRule = DOBO.getDOBOByName("DO_Service_Rule");
			biService.setUid(null);
			biService.putValue("name", biService.getValue("name") + "_copy");
			biService.putValue("l10n", biService.getValue("l10n") + "_copy");
			BOInstance biNewService = thisService.invokeUpdate(biService);

			for (Iterator<DOParameterService> it = dos
					.retrieveParaServiceLinks().iterator(); it.hasNext();) {
				DOParameterService dps = it.next();
				BOInstance bi = boServicePara.getInstance(dps.getObjUid());
				bi.setUid(null);
				boServicePara.getDInsertService().invokeUpdate(bi);
			}
			for (Iterator<DOServiceRule> it = dos.retrieveServiceRules()
					.iterator(); it.hasNext();) {
				DOServiceRule dsr = it.next();
				BOInstance bi = boServiceRule.getInstance(dsr.getObjUid());
				bi.setUid(null);
				boServiceRule.getDInsertService().invokeUpdate(bi);
			}
			t.end();
			return biNewService;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

}
