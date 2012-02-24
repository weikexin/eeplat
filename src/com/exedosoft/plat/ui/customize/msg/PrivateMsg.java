package com.exedosoft.plat.ui.customize.msg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

import java.sql.Date;

/**
 * @author aa
 */
public class PrivateMsg extends ParterMsg {

	private static Log log = LogFactory.getLog(PrivateMsg.class);



	public PrivateMsg() {
		this.templateFile = "customize/msg/PrivateMsg.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		List list = getListData(gm,data);
		for(Iterator it = list.iterator(); it.hasNext();){
			BOInstance bi = (BOInstance)it.next();
			bi.putValue("trans_time", getTimeStr(bi.getDateValue("send_time")));
			
		}
		data.put("data", list);
		data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}

		return data;
	}

	
	
	public static void main(String[] args) {

		 System.out.println(Integer.parseInt("b1",16));
	}

}
