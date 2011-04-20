package com.exedosoft.plat.action.zidingyi;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class ReSortFiles extends DOAbstractAction {

	public String excute() throws ExedoException {
		String objuid = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("objuid");
		String type = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("type");
		
		//退出时，刷新archfiles,
		if(type != null && "close".equals(type)) {
			DOBO  fileBO = DOBO.getDOBOByName("archfiles");
			DOGlobals.getInstance().getSessoinContext().getDataBus().remove(fileBO.getObjUid());
			return DEFAULT_FORWARD;
		}
		
		
		String pml = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("pml");
		String target = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("target");

		String currobjuid = objuid;
		String currcatalog_uid = null;
		Long currorderno = 0L;

		String nextObjuid = null;

		BOInstance archfile = new BOInstance();

		DOService currSer = DOService
				.getService("archfiles_browse_by_form_objuid");
		List<BOInstance> currList = currSer.invokeSelect(currobjuid);
		if (currList != null && currList.size() > 0) {
			BOInstance currBi = currList.get(0);
			currcatalog_uid = currBi.getValue("catalog_uid");
			currobjuid = currBi.getValue("objuid");
			currorderno = currBi.getIntValue("orderno");
		}

		DOService updateSer = DOService
				.getService("archfiles_update_filename_orderno");
		//

		String strOrder = currorderno.toString();
		DOService upperSer = DOService
				.getService("archfiles_browse_by_catalog_uid_orderno_upper");
		List<BOInstance> upperList = upperSer.invokeSelect(currcatalog_uid,
				strOrder);
		DOService nextSer = DOService
				.getService("archfiles_browse_by_catalog_uid_orderno_next");
		List<BOInstance> nextList = nextSer.invokeSelect(currcatalog_uid,
				strOrder);
		int sum = 0;
		if (type != null && "sortup".equals(type)) {
			if (upperList != null && upperList.size() > 0) {
				//更新前面的记录
				for(int i = 0; i < upperList.size() - 1; i++) {
					BOInstance nextBi = upperList.get(i);
					nextObjuid = nextBi.getUid();
					sum ++ ;
					updateSer.invokeUpdate(sum+"", nextObjuid);
				}
				//更新本条记录
				sum++;
				updateSer.invokeUpdate(sum+"", currobjuid);
				//更新移动的记录
				sum++;
				BOInstance moveBi = upperList.get(upperList.size()-1);
				nextObjuid = moveBi.getUid();
				updateSer.invokeUpdate(sum+"", nextObjuid);
				
				//更新后面的记录
				for(int i = 0; i < nextList.size(); i++) {
					BOInstance nextBi = nextList.get(i);
					nextObjuid = nextBi.getUid();
					sum ++ ;
					updateSer.invokeUpdate(sum+"", nextObjuid);
				}
				
			} else {
				archfile.putValue("msg", "这已经是第一条记录。");
				this.setInstance(archfile);
				return DEFAULT_FORWARD;
			}
			

		} else if (type != null && "sortdown".equals(type)) {

			if (nextList != null && nextList.size() > 0) {
				//更新前面的记录
				for(int i = 0; i < upperList.size(); i++) {
					BOInstance nextBi = upperList.get(i);
					nextObjuid = nextBi.getUid();
					sum ++ ;
					updateSer.invokeUpdate(sum+"", nextObjuid);
				}
				
				//更新移动的记录
				sum++;
				BOInstance moveBi = nextList.get(0);
				nextObjuid = moveBi.getUid();
				updateSer.invokeUpdate(sum+"", nextObjuid);
				//更新本条记录
				sum++;
				updateSer.invokeUpdate(sum+"", currobjuid);
				
				//更新后面的记录
				for(int i = 1; i < nextList.size(); i++) {
					BOInstance nextBi = nextList.get(i);
					nextObjuid = nextBi.getUid();
					sum ++ ;
					updateSer.invokeUpdate(sum+"", nextObjuid);
				}
			} else {
				archfile.putValue("msg", "这已经是最后一条记录。");
				this.setInstance(archfile);
				return DEFAULT_FORWARD;
			}
		}
		DOBO  fileBO = DOBO.getDOBOByName("archfiles");
		fileBO.refreshContext(currobjuid);
		archfile.putValue("pml", pml);
		archfile.putValue("target", target);
		this.setInstance(archfile);
		return DEFAULT_FORWARD;
	}
}