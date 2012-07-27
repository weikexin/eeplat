package com.exedosoft.plat.action;

import com.exedosoft.plat.ActionFactory;
import com.exedosoft.plat.bo.DOActionConfig;
import com.exedosoft.plat.util.I18n;


public class CoreExportExcelAction extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6701045786423369919L;

	public String excute() {
		
		System.out.println("进入Export Excel Action::::::::::::::::::::::::::");

		try {
			
			String[] checks =  this.actionForm.getValueArray("checkinstance");
			if (checks == null || checks.length == 0) {
				setEchoValue(I18n.instance().get("您没有选择数据"));
				return NO_FORWARD;
			}
		///	this.initTransConnection();

			for (int i = 0; i < checks.length; i++) {
				String check = checks[i];
				this.service.invokeUpdate(check);
			}
///			this.ifCloseTransConnection();


		} catch (Exception e) {
			this.setEchoValue(e.getMessage());
			return NO_FORWARD;
		}
		return  DEFAULT_FORWARD;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOActionConfig doa = DOActionConfig.getActionConfig("com_exedosoft_plat_login_RemoveDelegateAction");
		String aValue =  ActionFactory.getActionValue(doa);
		System.out.println("VVVVVVVVVVVVV::" + aValue);

	}

}
