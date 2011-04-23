package com.exedosoft.plat.login.zidingyi;



import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class InsertAppText extends DOAbstractAction {

	public String excute() {
		String acontent = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("acontent");
		if(acontent != null && "".equals(acontent.trim())) {
			DOService appSer = DOService.getService("cw_apptext_insert_xz_zdy_use");
			try {
				appSer.invokeUpdate(acontent);
			} catch (ExedoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.DEFAULT_FORWARD;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
