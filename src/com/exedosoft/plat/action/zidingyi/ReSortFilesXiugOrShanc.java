package com.exedosoft.plat.action.zidingyi;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class ReSortFilesXiugOrShanc extends DOAbstractAction {

	public String excute() throws ExedoException {
		String currobjuid = null;
		String currcatalog_uid = null;
		String currfilename = null;
		String currorderno = null;

		List<BOInstance> list = new ArrayList<BOInstance>();
		list = this.service.invokeSelect();
		if (list != null && list.size() > 0) {
			BOInstance bi = list.get(0);
			currobjuid = bi.getValue("objuid");
			currcatalog_uid = bi.getValue("catalog_uid");
			currfilename = bi.getValue("filename");
			currorderno = bi.getValue("orderno");

			DOService nextSer = DOService
					.getService("archfiles_browse_by_catalog_uid_orderno_next");
			List<BOInstance> nextList = nextSer.invokeSelect(currcatalog_uid,currorderno);
			if (nextList != null && nextList.size() > 0) {
				DOService updateSer = DOService
				.getService("archfiles_update_filename_orderno");
				for(int i = 0; i < nextList.size(); i++) {
					BOInstance nextBi = nextList.get(0);
					String nextObjuid = nextBi.getUid();
					String nextfilename = nextBi.getValue("filename");
					String nextorderno = nextBi.getValue("orderno");
					updateSer.invokeUpdate(currfilename, currorderno, nextObjuid);
					currfilename = nextfilename;
					currorderno = nextorderno;
				}
			} 

			// 删除时用，删除当前记录
			DOService deleteSer = DOService
					.getService("archfiles_delete_by_form_uid");
			deleteSer.invokeUpdate(currobjuid);

		}
		return DEFAULT_FORWARD;
	}

}