package com.exedosoft.plat.ui.jquery.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.wf.wfi.ProcessInstance;

/**
 * 模板本身定义的模板优先
 * 
 * @author aa
 * 
 */
public class TAPaneWFTask extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TAPaneWFTask.class);

	public TAPaneWFTask() {
		this.templateFile = "form/TAPaneWFTask.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		// DOPaneModel pm = (DOPaneModel)doimodel;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);

		DOFormModel aFm = (DOFormModel) doimodel;
		BOInstance bi = aFm.getData();
		if (bi != null && bi.getValue("contextPiUid") != null) {

			ProcessInstance pi = ProcessInstance.getProcessInstance(bi
					.getValue("contextPiUid"));
			if(pi==null){
				pi = ProcessInstance.getHisProcessInstance(bi.getValue("contextPiUid"));
			}
			
			if (pi!=null && pi.getProcessTemplate() != null
					&& pi.getProcessTemplate().getDoBO() != null
					&& pi.getProcessTemplate().getDoBO().getMainPaneModel() != null) {
				String instanceUid = bi.getValue("instance_uid");
				if(instanceUid!=null){
					data.put("busiBOName", pi.getProcessTemplate().getDoBO().getName());
					data.put("instance_uid", instanceUid);
				}
				data.put("paneModel", pi.getProcessTemplate().getDoBO()
						.getMainPaneModel());
			}
		} else if (aFm.getLinkPaneModel() != null) {
			data.put("paneModel", aFm.getLinkPaneModel());
		}
		return data;
	}

}
