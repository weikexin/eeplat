package com.exedosoft.plat.action;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.OrgParter;

/**
 * 
 * 
 * 做增加，不做修改和删附1�7
 * 
 * 可以对增加做扫描
 * 
 * @author anolesoft
 * 
 */

public class DOData4BO extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String excute() {

		String boUid = null;

		if (this.service.getPareMap()!= null && this.service.getPareMap().size() > 0) {
			boUid = (String) this.service.getPareMap().values().toArray()[this.service
					.getPareMap().size() - 1];
		}

		DOService.initParaValues();
		
		List list = new ArrayList();
		if(boUid!=null){
			DOBO bo = DOBO.getDOBOByID(boUid);
			list = bo.getDSeleAllService().invokeSelect();
		}

		this.setInstances(list);
		// TODO Auto-generated method stub
		return DEFAULT_FORWARD;
	}

}
