package com.exedosoft.plat.action.zidingyi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class DownloadFiles extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		DOBO theBO = DOBO.getDOBOByName("architem");
		BOInstance bi = theBO.getCorrInstance();
		
		String version_uid = DOGlobals.getInstance().getSessoinContext().getInstance().getFormInstance().getValue("version");
		if(version_uid != null && "".equals(version_uid.trim())) {
			
		} else {
			DOService linkversion = DOService.getService("archcatalog_browse_huoquxmversionno_zuixin");
			List<BOInstance> listversion = linkversion.invokeSelect();
			if(listversion != null && listversion.size() > 0) {
				BOInstance vbi = listversion.get(0);
				version_uid = vbi.getValue("version");
			}
		}
		String item_uid = bi.getUid();
		
		List<String> filesName = new ArrayList<String>();
		DOService linkfiles = DOService.getService("archfiles_browse_by_form_item_version");
		List<BOInstance> listfiles = linkfiles.invokeSelect(item_uid,version_uid);
		if(listfiles != null && listfiles.size() > 0) {
			for(int v = 0; v < listfiles.size(); v++) {
				BOInstance vbi = listfiles.get(v);
				String fileName = vbi.getValue("filename");
				filesName.add(fileName);
			}
			
		}
		
		
		String rollarchcode = bi.getValue("rollarchcode");
		String dirName = rollarchcode;
		String dTimeStr = OperationUtil.getDateTimeString();
		String zipFileName = dTimeStr+".rar";
		
		File oldZip = new File(DOGlobals.UPLOAD_TEMP+zipFileName);
		if(oldZip.exists()) {
			oldZip.delete();
		}
		
		
		String systemPath = DOGlobals.UPLOAD_TEMP;
		String fgf = "/";
		if (systemPath != null) {
			systemPath = systemPath.trim();
			if (systemPath.endsWith("/")) {
				fgf = "/";
			} else if (systemPath.endsWith("\\")) {
				fgf = "\\\\";
			} else if (systemPath.indexOf("/") != -1) {
				fgf = "/";
				systemPath = systemPath + fgf;
			} else if (systemPath.indexOf("\\\\") != -1) {
				fgf = "\\\\";
				systemPath = systemPath + fgf;
			}
		}
		String zipDirectory = systemPath + "MSFL-PROJECT";

		if (rollarchcode.indexOf("MSFL-FK-") != -1) {
			zipDirectory = systemPath + "MSFL-PROJECT" + fgf + "MSFL-FK" + fgf;
		} else if (rollarchcode.indexOf("MSFL-PS-") != -1) {
			zipDirectory = systemPath + "MSFL-PROJECT" + fgf + "MSFL-PS" + fgf;
		} else if (rollarchcode.indexOf("MSFL-ZH-") != -1) {
			zipDirectory = systemPath + "MSFL-PROJECT" + fgf + "MSFL-ZH" + fgf;
		} else if (rollarchcode.indexOf("MSFL-SS-") != -1) {
			zipDirectory = systemPath + "MSFL-PROJECT" + fgf + "MSFL-SS" + fgf;
		} else if (rollarchcode.indexOf("MSFL-BL-") != -1) {
			zipDirectory = systemPath + "MSFL-PROJECT" + fgf + "MSFL-BL" + fgf;

			// 其他类型
		} else if (rollarchcode.indexOf("-") != -1) {
			String l1 = rollarchcode.substring(0, rollarchcode.indexOf("-"));
			String l2str = rollarchcode.substring(rollarchcode.indexOf("-") + 1);
			String l2 = l2str;
			if(l2str.indexOf("-") != -1) {
				l2 = l2str.substring(0, l2str.indexOf("-"));
			}
			
			String lx = l1 + "-" + l2;
			zipDirectory = systemPath + "MSFL-PROJECT" + fgf + lx + fgf;
		} else {
			zipDirectory = systemPath + "MSFL-PROJECT" + fgf + rollarchcode + fgf;
		}
		
		
		
		BOInstance itembi = new BOInstance();
		try {
			//String entryPath = doZip(zipDirectory);
			//String urlPath = entryPath.substring(entryPath.indexOf("upload"));
			ZipUtil files = new ZipUtil();
			String entryPath = files.doZip(zipDirectory,dirName,zipFileName,filesName);
			itembi.putValue("entrypath", entryPath);
			itembi.putValue("filename", zipFileName);
			this.setInstance(itembi);
			return DEFAULT_FORWARD;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(oldZip.exists()) {
				oldZip.delete();
			}
			itembi.putValue("entrypath", null);
			itembi.putValue("filename", null);
			this.setInstance(itembi);
			return DEFAULT_FORWARD;
		}
	}
}
