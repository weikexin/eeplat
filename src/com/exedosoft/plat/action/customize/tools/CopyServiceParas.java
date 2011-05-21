package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;

public class CopyServiceParas extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3155689928718730452L;

	@Override
	public String excute() throws ExedoException {

		if (this.service==null || this.service.getTempSql() == null) {
			System.out.println("未配置SQL 语句");
			this.setEchoValue("未配置SQL 语句");
			return NO_FORWARD;
		}


		String serviceUid = this.actionForm.getValue("serviceUid");
		if(serviceUid==null){
			this.setEchoValue("没有选择服务！");
			return  DEFAULT_FORWARD;
		}
		String[] checks = this.actionForm.getValueArray("checkinstance");
		if (checks == null) {
			System.out.println("没有数据！");
			this.setEchoValue("没有数据！");
			return NO_FORWARD;
		}
		
		try {
			this.service.beginBatch();
			DOBO boParaService = DOBO.getDOBOByName("DO_Parameter_Service");
			for(int i = 0; i < checks.length ; i++){
				BOInstance bi = boParaService.getInstance(checks[i]);
				bi.putValue("objuid", null);
				bi.putValue("serviceUid", serviceUid);
				this.service.addBatch(bi);			
			}
			this.service.endBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DEFAULT_FORWARD;

	}

}
