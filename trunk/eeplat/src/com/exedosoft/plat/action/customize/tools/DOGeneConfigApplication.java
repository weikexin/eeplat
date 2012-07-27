package com.exedosoft.plat.action.customize.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.gene.jquery.AProjectForwarderJquery;
import com.exedosoft.plat.util.I18n;

public class DOGeneConfigApplication extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123234L;

	private static Log log = LogFactory.getLog(DOGeneConfigApplication.class);

	/**
	 * Save 的情况，所以Parameter 取值时不考虑auto_condition（查询） 的情况
	 */

	public String excute() {
		
		DOBO bo = DOBO.getDOBOByName("do_application");
		String applicationUid = bo.getCorrInstance().getUid();
		if(applicationUid==null){
			this.setEchoValue(I18n.instance().get("没有找到需要初始化的应用!"));
			return NO_FORWARD;
		}

		AProjectForwarderJquery  af = new AProjectForwarderJquery();
		af.forwardBaseUI(applicationUid);
		this.setEchoValue(I18n.instance().get("初始化成功!"));
		return DEFAULT_FORWARD;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
