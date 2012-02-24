package com.exedosoft.plat.ui.jquery.form;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.js.RunJs;
import com.exedosoft.plat.js.RunJsFactory;
import com.exedosoft.plat.multitenancy.Tenant;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class DODownLoadExcelReport extends DOViewTemplate {

	private static Log log = LogFactory.getLog(DODownLoadExcelReport.class);

	public DODownLoadExcelReport() {
		this.templateFile = "form/DODownLoadExcel.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		// DOPaneModel pm = (DOPaneModel)doimodel;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);

		DOFormModel fm = (DOFormModel) doimodel;

		String genScript = fm.getController().getGenScript();
		if (genScript != null && !"".equals(genScript.trim())) {
			RunJsFactory.getRunJs().evaluate(genScript);
		}


		
		Map datas = new HashMap();
		if(DOGlobals.getInstance().getSessoinContext().getFormInstance().getObjectValue("datas")!=null){
			datas = (Map)DOGlobals.getInstance().getSessoinContext().getFormInstance().getObjectValue("datas");
		}
		String tempXls = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("tempXls");

		String aFolder = "test";
		try {
			aFolder = DOGlobals.getInstance().getSessoinContext().getUser()
					.getName();
		} catch (Exception e) {

		}
		String destFileName = DOGlobals.UPLOAD_TEMP + "/" + aFolder;
		File aFile = new File(destFileName);
		aFile.mkdir();
		destFileName = destFileName + "/excelReport.xls";

		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf") + 7);

		String templateFileName = "simple.xls";
		
		
		if(fm.getInputConfig()!=null &&
				(fm.getInputConfig().toLowerCase().endsWith(".xls") ||
						fm.getInputConfig().toLowerCase().endsWith(".xlsx") 
				)){
			templateFileName = fm.getInputConfig();
		}
		
		templateFileName = prefix + "/xls/" + templateFileName;
		
		if(tempXls!=null){
			templateFileName = Tenant.getTenantFilePath() + tempXls;		
		}

		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS( templateFileName, datas, destFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuffer strLink = new StringBuffer();

		strLink.append(DOGlobals.PRE_FULL_FOLDER).append(
				"file/downloadfile_hd.jsp?filePath=").append(
						destFileName);

			strLink.append("&fileName=excelReport.xls");
		data.put("excelurl", strLink.toString());

		return data;
	}

	// public String getHtmlCode(DOIModel aModel) {
	//
	// DOFormModel fm = (DOFormModel) aModel;
	//
	// if (fm.getLinkPaneModel() == null) {
	// return "&nbsp;";
	// }
	//
	// StringBuffer strLink = new StringBuffer();
	//
	// strLink.append("<a href=\"");
	//
	// strLink.append(DOGlobals.PRE_FULL_FOLDER).append(
	// "file/downloadfile_excel.jsp?paneModelUid=").append(
	// fm.getLinkPaneModel().getObjUid());
	// if (fm.getLinkService() != null) {
	// strLink.append("&contextServiceName=").append(
	// fm.getLinkService().getName());
	// }
	//
	// strLink.append("\">下载</a>");
	//
	// return strLink.toString();
	//
	// }

}
