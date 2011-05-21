package com.exedosoft.plat.action.zidingyi;

import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class ReSortCatalog extends DOAbstractAction{


	public String excute() throws ExedoException {
		String objuid =DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("objuid");
		String type =DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("type");
		String pml =DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("pml");
		String target =DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("target");
		
		String currobjuid = objuid;
		String curritem_uid = null;
		String currversion_uid = null;
		String currArchCode = null;
		Long currPageCount = 0L;
		Long currPageNumber = 0L;
		
		String nextObjuid = null;
		String nextArchCode = null;
		Long nextPageCount = 0L;
		Long nextPageNumber = 0L;
		
		BOInstance catalog = new BOInstance();
		
		String archcode = null;
		int pagenumber = 0;
		DOService currSer = DOService.getService("archcatalog_browse_by_objuid");
		List<BOInstance> currList = currSer.invokeSelect(currobjuid);
		if(currList != null && currList.size() > 0) {
			BOInstance currBi = currList.get(0);
			currArchCode = currBi.getValue("archcode");
			currPageCount = currBi.getIntValue("pagecount");
			currPageNumber = currBi.getIntValue("pagenumber");
			curritem_uid = currBi.getValue("item_uid");
			currversion_uid = currBi.getValue("version_uid");
		}
		
		
		
		DOService updateSer = DOService.getService("archcatalog_update_paixushiyong");
		DOService updateSer2 = DOService.getService("archcatalog_update_paixushiyong_2");
		//
		if(type != null && "sortup".equals(type)) {
			DOService nextSer = DOService.getService("archcatalog_browse_upper_jilu");
			List<BOInstance> nextList = nextSer.invokeSelect(curritem_uid,currversion_uid,currArchCode);
			if(nextList != null && nextList.size() > 0) {
				BOInstance nextBi = nextList.get(0);
				nextObjuid = nextBi.getUid();
				nextArchCode = nextBi.getValue("archcode");
				nextPageCount = nextBi.getIntValue("pagecount");
				nextPageNumber = nextBi.getIntValue("pagenumber");
			} else {
				catalog.putValue("msg", "这已经是第一条记录。");
				this.setInstance(catalog);
				return DEFAULT_FORWARD;
			}
			//向上移动，以另外一条记录查找前面的数据
			Long upperPagecounts = 0L;
			Long upperPagenumber = 0L;
			DOService upperallSer = DOService.getService("archcatalog_browse_upperall_jilu");
			List<BOInstance> upperallList = upperallSer.invokeSelect(curritem_uid,currversion_uid,nextArchCode);
			if(upperallList != null && upperallList.size() > 0) {
				for(int i = 0; i < upperallList.size(); i++) {
					BOInstance upperbi = upperallList.get(i);
					Long upperPagecount = upperbi.getIntValue("pagecount");
					if(upperPagecount != null)
						upperPagecounts += upperPagecount;
					upperPagenumber = upperPagecounts+1;
				}
			}
			if(upperPagenumber == 0)
				upperPagenumber = 1L;
			
			//向上移动，更新本条记录//
			archcode = nextArchCode;
			pagenumber += nextPageNumber;
			
			if(currPageCount == 0)
				updateSer2.invokeUpdate(archcode,currobjuid);
			else {
				if(nextPageNumber == 0)
					updateSer.invokeUpdate(archcode,upperPagenumber+"",currobjuid);
				else
					updateSer.invokeUpdate(archcode,pagenumber+"",currobjuid);
			}
				
			//再更新另一条记录
			archcode = currArchCode;
			pagenumber += currPageCount;
			if(nextPageCount == 0)
				updateSer2.invokeUpdate(archcode,nextObjuid);
			else {
				if(currPageCount == 0)
					updateSer.invokeUpdate(archcode,upperPagenumber+"",nextObjuid);
				else
					updateSer.invokeUpdate(archcode,pagenumber+"",nextObjuid);
			}
				
			
		} else if(type != null && "sortdown".equals(type)) {
			DOService nextSer = DOService.getService("archcatalog_browse_next_jilu");
			List<BOInstance> nextList = nextSer.invokeSelect(curritem_uid,currversion_uid,currArchCode);
			if(nextList != null && nextList.size() > 0) {
				BOInstance nextBi = nextList.get(0);
				nextObjuid = nextBi.getUid();
				nextArchCode = nextBi.getValue("archcode");
				nextPageCount = nextBi.getIntValue("pagecount");
				nextPageNumber = nextBi.getIntValue("pagenumber");
			} else {
				catalog.putValue("msg", "这已经是最后一条记录。");
				this.setInstance(catalog);
				return DEFAULT_FORWARD;
			}
			
			//向上移动，以本条记录查找前面的数据
			Long upperPagecounts = 0L;
			Long upperPagenumber = 0L;
			DOService upperallSer = DOService.getService("archcatalog_browse_upperall_jilu");
			List<BOInstance> upperallList = upperallSer.invokeSelect(curritem_uid,currversion_uid,currArchCode);
			if(upperallList != null && upperallList.size() > 0) {
				for(int i = 0; i < upperallList.size(); i++) {
					BOInstance upperbi = upperallList.get(i);
					Long upperPagecount = upperbi.getIntValue("pagecount");
					if(upperPagecount != null)
						upperPagecounts += upperPagecount;
					upperPagenumber = upperPagecounts+1;
				}
			}
			if(upperPagenumber == 0)
				upperPagenumber = 1L;
			
			
			
			//向下移动，先更新另一条记录//
			archcode = currArchCode;
			pagenumber += currPageNumber;
			if(nextPageCount == 0)
				updateSer2.invokeUpdate(archcode,nextObjuid);
			else {
				if(currPageCount == 0)
					updateSer.invokeUpdate(archcode,upperPagenumber+"",nextObjuid);
				else
					updateSer.invokeUpdate(archcode,pagenumber+"",nextObjuid);
			}
				
			//再更新本一条记录
			archcode = nextArchCode;
			pagenumber += nextPageCount;
			if(currPageCount == 0)
				updateSer2.invokeUpdate(archcode,currobjuid);
			else  {
				if(currPageCount == 0)
					updateSer.invokeUpdate(archcode,upperPagenumber+"",currobjuid);
				else
					updateSer.invokeUpdate(archcode,pagenumber+"",currobjuid);
			}
		}
			catalog.putValue("pml", pml);
			catalog.putValue("target", target);
			this.setInstance(catalog);
			return DEFAULT_FORWARD;
		}
		
	}