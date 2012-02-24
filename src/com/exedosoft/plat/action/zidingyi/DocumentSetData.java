package com.exedosoft.plat.action.zidingyi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class DocumentSetData extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		// crm_document_xsjh_update_path_size
		try {
			List<BOInstance> datas = new ArrayList<BOInstance>();
			datas = this.service.invokeSelect();
			if(datas != null && datas.size() > 0) {
				BOInstance bi = datas.get(0);
				String id = bi.getUid();
				String docname = bi.getValue("docname");
				if(docname != null && !"".equals(docname.trim())) {
					docname = docname.trim();
					String dateStr = OperationUtil.getDateString();
					//文件路径
					String docpath = dateStr+"/"+docname;
					//计算文件大小
					double docsize = 0.00;
					String syspath = DOGlobals.getInstance().getValue("uploadfiletemp");
					File file = new File(syspath+docpath);
					if(file.exists() && file.isFile()) {
						double size = file.length()/1024;
						docsize = OperationUtil.round(size, 2);
					}
					DOService update = DOService.getService("crm_document_xsjh_update_path_size");
					update.invokeUpdate(docpath,docsize+" KB",id);
				}
			}
		} catch (Exception e) {
			return this.DEFAULT_FORWARD;
		}

		return DEFAULT_FORWARD;
	}

}
