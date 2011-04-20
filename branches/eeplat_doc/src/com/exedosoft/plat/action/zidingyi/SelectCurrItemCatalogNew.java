package com.exedosoft.plat.action.zidingyi;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class SelectCurrItemCatalogNew extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		try {
			DOBO  itemBO = DOBO.getDOBOByName("architem");
			BOInstance itembi = (BOInstance)itemBO.getCorrInstance();
			String item_uid = itembi.getUid();
			
			List<BOInstance> list = new ArrayList<BOInstance>();
			String version_uid = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("version");
			String versionno = null;
			if(version_uid == null) {
				DOService linkversion = DOService.getService("archcatalog_browse_huoquxmversionno_zuixin");
				List<BOInstance> listversion = linkversion.invokeSelect();
				if(listversion != null && listversion.size() > 0) {
					BOInstance bi = listversion.get(0);
					versionno = bi.getValue("version");
				}
				version_uid = versionno;
			} else if("".equals(version_uid.trim())) {
				this.setEchoValue("«Î—°‘Ò∞Ê±æ∫≈°£");
				this.setInstances(list);
				return DEFAULT_FORWARD;
			}
			
			DOService linkser = DOService.getService("archcatalog_browse__cx_curr_item");
			List<BOInstance> linkList = linkser.invokeSelect(version_uid,item_uid);
			if(linkList != null && linkList.size() >0) {
				for(int n = 0; n < linkList.size(); n++) {
					BOInstance bi = linkList.get(n);
					list.add(bi);
				}
			}
			
			this.setInstances(list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return DEFAULT_FORWARD;
	}
	
	public static void main(String[] args){
	}

}
