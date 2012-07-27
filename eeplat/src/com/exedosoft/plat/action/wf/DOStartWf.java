package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.wf.WFEngine;
import com.exedosoft.wf.WFEngineFactory;
import com.exedosoft.wf.pt.ProcessTemplate;

public class DOStartWf extends DOAbstractAction {

	private static final long serialVersionUID = 7566742608663452803L;

	public String excute() {


		if (service.getProcessTemplate() == null) {
			this.setEchoValue(I18n.instance().get("服务未定义工作流模板"));
			return NO_FORWARD;
		}
		Transaction t = new Transaction(service.getBo().getDataBase());
		t.begin();
		try {
			service.invokeUpdate();

			WFEngine wfi = WFEngineFactory.getWFEngine();
			ProcessTemplate pt = this.service.getProcessTemplate();
//			this.service.getProcessTemplate().getDoBO().refreshContext(arg0)
			wfi.startProcess(pt);
		} catch (Exception e) {
			e.printStackTrace();
			this.setEchoValue(e.getLocalizedMessage());
			t.rollback();
			return NO_FORWARD;
		} finally {
			t.end();
		}
		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {

		// CacheFactory.getCacheData().fromSerialObject();
		DOService dos = DOService.getService("t_expense_subflow");

		// DOService findService =
		// CacheFactory.getCacheData().getDOServiceByName("t_expense_insert");
		// 649963588b784069ba6bc67934c6fd06
		// BOInstance bi =
		// findService.getInstance("40288031288b267b01288b34253b0005");
		// dos = (DOService) bi.toObject(DOService.class);
		System.out.println(dos.getProcessTemplate());
		System.out.println(dos.getActionConfig());

	}

}
