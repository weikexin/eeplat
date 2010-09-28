package com.exedosoft.plat.action;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * 消息发送者
 * 
 * @author IBM
 * 
 */

public class DOMsgSender extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 101111111111L;

	private static DOService msgInsert = DOService.getService("DO.MSG.Insert");

	private static DOService msgDelete = DOService.getService("DO.MSG.Delete");

	private static DOService msgReadInsert = DOService
			.getService("DO.MSG.READ.Insert");

	private static DOService msgReadDelete = DOService
			.getService("DO.MSG.READ.delete.by.msguid");

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub

		String msgstatus = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("msgstatus");

		String recieveids = "";

		// /////////界面上的状态
		if ("2".equals(msgstatus) || "3".equals(msgstatus)) {

			// ////////先做删除
			msgReadDelete.invokeUpdate();
			msgDelete.invokeUpdate();
			recieveids = DOGlobals.getInstance().getSessoinContext()
					.getFormInstance().getValue("recieveids_u");
		} else {
			recieveids = DOGlobals.getInstance().getSessoinContext()
					.getFormInstance().getValue("recieveids");
		}

		System.out.println("所有接收人::::::::::" + recieveids);

		if (recieveids == null || recieveids.equals("")) {
			this.setEchoValue("您没有选择收件人");
			return null;
		}

		// //////////转换为数据库的状态,回复也是新增
		if ("2".equals(msgstatus) || "5".equals(msgstatus)) {
			DOGlobals.getInstance().getSessoinContext().getFormInstance()
					.putValue("msgstatus", "1");
		} else if ("3".equals(msgstatus)) {
			DOGlobals.getInstance().getSessoinContext().getFormInstance()
					.putValue("msgstatus", "0");
		}

		Map map = new HashMap();
		String[] recieveArray = recieveids.split(",");
		if (recieveArray != null && recieveArray.length > 0) {
			System.out.println("Msg Send Service:::" + msgInsert);
			System.out.println("Send Msg Error::::::::::::::");

			BOInstance aInstance = msgInsert.invokeUpdate();
			msgReadInsert.beginBatch();
			for (int i = 0; i < recieveArray.length; i++) {
				String recieveuid = recieveArray[i];
				if (recieveuid != null && recieveuid.trim().length() > 0) {
					map.put("recieveuid", recieveuid);
					msgReadInsert.addBatch(map);
				}
			}
			msgReadInsert.endBatch();
		}
		return null;
	}

}
