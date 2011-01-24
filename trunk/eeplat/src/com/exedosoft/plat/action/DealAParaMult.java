package com.exedosoft.plat.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0 
 */

public class DealAParaMult extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798265805984034747L;
	private static Log log = LogFactory.getLog(DealAParaMult.class);

	public DealAParaMult() {
	}

	public String excute()  {

		if (this.service==null || this.service.getTempSql() == null) {
			System.out.println("未配置SQL 语句");
			this.setEchoValue("未配置SQL 语句");
		}


		String[] checks = this.actionForm.getValueArray("checkinstance");
		if (checks == null) {
			System.out.println("没有数据");
			this.setEchoValue("没有数据");
			return NO_FORWARD;
		}
		
		try {
			this.service.beginBatch();
			for(int i = 0; i < checks.length ; i++){
				Map aMap = new HashMap();
				aMap.put(this.service.getBo().getKeyCol(), checks[i]);
				this.service.addBatch(aMap);			
			}
			this.service.endBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DEFAULT_FORWARD;
	}

}
