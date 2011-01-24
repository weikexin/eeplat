package com.exedosoft.plat.ui.jquery.form;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class DODownLoadCommon extends DOViewTemplate {

	private static Log log = LogFactory.getLog(DODownLoadCommon.class);

	public DODownLoadCommon() {
		this.templateFile = "form/DODownLoadCommon.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		// DOPaneModel pm = (DOPaneModel)doimodel;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);

		DOFormModel fm = (DOFormModel) doimodel;

		StringBuffer strLink = new StringBuffer();

		strLink.append(DOGlobals.PRE_FULL_FOLDER).append(
				"file/downloadfile_common.jsp?formModelUid=").append(
				fm.getObjUid());
		
		BOInstance biData = fm.getData();
		if(biData==null){
			biData = fm.getGridModel().getCategory().getCorrInstance();
		}
		
		if(biData!=null){
				strLink.append("&fileName=")
				.append( biData.getName());
				if(fm.getInputConfig()==null){
					strLink.append(".xml");
				}else{
					strLink.append(fm.getInputConfig());
				}
		}

		data.put("link_url", strLink.toString());
		
		return data;
	}

//	public String getHtmlCode(DOIModel aModel) {
//
//		DOFormModel fm = (DOFormModel) aModel;
//
//		if (fm.getLinkPaneModel() == null) {
//			return "&nbsp;";
//		}
//
//		StringBuffer strLink = new StringBuffer();
//
//		strLink.append("<a href=\"");
//
//		strLink.append(DOGlobals.PRE_FULL_FOLDER).append(
//				"file/downloadfile_excel.jsp?paneModelUid=").append(
//				fm.getLinkPaneModel().getObjUid());
//		if (fm.getLinkService() != null) {
//			strLink.append("&contextServiceName=").append(
//					fm.getLinkService().getName());
//		}
//
//		strLink.append("\">下载</a>");
//
//		return strLink.toString();
//
//	}

}
