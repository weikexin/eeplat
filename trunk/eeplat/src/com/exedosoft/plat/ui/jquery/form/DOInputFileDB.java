package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;

public class DOInputFileDB extends DOBaseForm {

	public String getHtmlCode(DOIModel property) {
	
		DOFormModel fm = (DOFormModel) property;
		
		if(fm.getData()==null){
			return "无法下载";
		}		
		
		String renameValue = fm.getInputConfig();
		String inputConfig = fm.getInputConfig();
		String downLoadSizeCol = null;
		
		if (inputConfig != null && inputConfig.indexOf(";") != -1) {
			String[] blobParas = inputConfig.split(";");
			renameValue = blobParas[0];
			if(blobParas.length==3){
				downLoadSizeCol = blobParas[2];
			}
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<a href='")
		.append(DOGlobals.PRE_FULL_FOLDER)		
		.append("file/downloadfile_db.jsp?name=")
		.append(fm.getRelationProperty().getColName())
		.append("&rename=")
		.append(renameValue)
		.append("&thisuuid=")
		.append(fm.getData().getUid())
		.append("&bouid=")
		.append(fm.getData().getBo().getObjUid());
		
		if(downLoadSizeCol!=null){
			buffer.append("&downLoadSizeCol=")
			.append(downLoadSizeCol);
		}
		buffer.append("'>下载</a>");
		//property
		// TODO Auto-generated method stub
		return buffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
