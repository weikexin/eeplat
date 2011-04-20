package com.exedosoft.plat.action.zidingyi;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.id.UUIDHex;

public class UploadCatalogFile extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		try {
			DOBO  itemBO = DOBO.getDOBOByName("architem");
			BOInstance itembi = (BOInstance)itemBO.getCorrInstance();
			String item_uid = itembi.getUid();
			String rollarchcode = itembi.getValue("rollarchcode");
			
			List<BOInstance> list = new ArrayList<BOInstance>();
			String version_uid = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("version");
			DOService linkversion = DOService.getService("archcatalog_browse_huoquxmversionno_caogao");
			List<BOInstance> listversion = linkversion.invokeSelect();
			if(listversion != null && listversion.size() > 0) {
				BOInstance bi = listversion.get(0);
				version_uid = bi.getValue("version");
			}
			
			DOService linkser = DOService.getService("archcatalog_browse__cx_curr_item");
			List<BOInstance> linkList = linkser.invokeSelect(version_uid,item_uid);
			
			BOInstance bi = new BOInstance();
			String strs = rollarchcode;
			if(linkList != null && linkList.size() >0) {
				for(int n = 0; n < linkList.size(); n++) {
					BOInstance catalogbi = linkList.get(n);
					String archcode = catalogbi.getValue("archcode");
					strs = strs + "@" + archcode;
				}
			}
			bi.putValue("strs", strs);
			list.add(bi);
			this.setInstances(list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return DEFAULT_FORWARD;
	}
	
	public void insertArchFiles(String catalog_uid, String filepath, String filename,String orderno) throws ExedoException {
		String objuid = (String) UUIDHex.getInstance().generate();
		DOService insertkser = DOService.getService("archfiles_insert_uploadadd");
		insertkser.invokeUpdate(objuid,catalog_uid,filepath,filename,orderno);	
	}
	
	public static void main(String[] args){
		
	}

}
