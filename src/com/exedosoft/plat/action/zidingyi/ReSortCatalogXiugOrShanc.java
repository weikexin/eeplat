package com.exedosoft.plat.action.zidingyi;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class ReSortCatalogXiugOrShanc extends DOAbstractAction{


	public String excute() throws ExedoException {
		String currobjuid = null;
		String curritem_uid = null;
		String currversion_uid = null;
		String currArchCode = null;
		int currPageCount = 0;
		int currPageNumber = 0;
		String type = null;
		
		List<BOInstance> list = new ArrayList<BOInstance>();
		list = this.service.invokeSelect();
		if(list != null && list.size() > 0) {
			BOInstance bi = list.get(0);
			currobjuid = bi.getValue("objuid");
			curritem_uid = bi.getValue("item_uid");
			currversion_uid = bi.getValue("version_uid");
			currArchCode = bi.getValue("archcode");
			Long newPagecount = bi.getIntValue("pagecount");
			type = bi.getValue("type");
			String 	rollarchcode = currArchCode.substring(0, currArchCode.lastIndexOf("-"));
			int sum = 0;

			//计算前面记录
			currPageCount = 0;
			currPageNumber = currPageCount + 1;
			//archcatalog_browse_upperall_jilu
			DOService upperallSer = DOService.getService("archcatalog_browse_upperall_jilu");
			List<BOInstance> upperallList = upperallSer.invokeSelect(curritem_uid,currversion_uid,currArchCode);
			DOService updateSer2 = DOService.getService("archcatalog_update_paixushiyong_2");
			DOService updateSer = DOService.getService("archcatalog_update_paixushiyong");
			if(upperallList != null && upperallList.size() > 0) {
				for(int i = 0; i < upperallList.size(); i++) {
					BOInstance upperbi = upperallList.get(i);
					String objuid = upperbi.getValue("objuid");
					Long thisPagecount = upperbi.getIntValue("pagecount");
					sum += 1;
					
					String archcode2 = rollarchcode+"-";
					String catalarchcode = archcode2;
					if(sum < 10) {
						catalarchcode = archcode2+"00"+sum;
					} else if(sum < 100) {
						catalarchcode = archcode2+"0"+sum;
					} else if(sum < 1000) {
						catalarchcode = archcode2+sum;
					}  
					if(thisPagecount == null || thisPagecount == 0)
						updateSer2.invokeUpdate(catalarchcode,objuid);
					else
						updateSer2.invokeUpdate(catalarchcode,currPageNumber+"",objuid);
					
					Long upperPagecount = upperbi.getIntValue("pagecount");
					if(upperPagecount != null)
						currPageCount += upperPagecount;
					currPageNumber = currPageCount+1;
				}
			}
			//更新本条记录的pagenumber
			
			String archcode2 = rollarchcode+"-";
			String catalarchcode = archcode2;
			if(sum+1 < 10) {
				catalarchcode = archcode2+"00"+(sum+1);
			} else if(sum+1 < 100) {
				catalarchcode = archcode2+"0"+(sum+1);
			} else if(sum+1 < 1000) {
				catalarchcode = archcode2+(sum+1);
			} 
			updateSer.invokeUpdate(catalarchcode,currPageNumber+"",currobjuid);
			
			///考虑本条记录
			//修改
			int xgPageCount = currPageCount;
				xgPageCount += newPagecount;
			int xgPageNumber = xgPageCount + 1;
			//删除
			int scPageCount = currPageCount;
			int scPageNumber = scPageCount + 1;
			
			
			
			DOService nextallSer = DOService.getService("archcatalog_browse_nextall_jilu");
			List<BOInstance> nextallList = nextallSer.invokeSelect(curritem_uid,currversion_uid,currArchCode);
			if(nextallList != null && nextallList.size() > 0) {
				for(int i = 0; i < nextallList.size(); i++) {
					
					sum += 1;
					
					
					BOInstance nextbi = nextallList.get(i);
					String objuid = nextbi.getValue("objuid");
					Long nextPagecount = nextbi.getIntValue("pagecount");
					if("xiug".equals(type)) {
						if(sum+1 < 10) {
							catalarchcode = archcode2+"00"+(sum+1);
						} else if(sum+1 < 100) {
							catalarchcode = archcode2+"0"+(sum+1);
						} else if(sum+1 < 1000) {
							catalarchcode = archcode2+(sum+1);
						} 
						
						if(nextPagecount == null || nextPagecount == 0)
							updateSer2.invokeUpdate(catalarchcode,objuid);
						else 
							updateSer.invokeUpdate(catalarchcode,xgPageNumber+"",objuid);
						//更新数据
						if(nextPagecount != null)
							xgPageCount += nextPagecount;
						xgPageNumber = xgPageCount+1;
					} else if("shanc".equals(type)) {
						
						if(sum < 10) {
							catalarchcode = archcode2+"00"+sum;
						} else if(sum < 100) {
							catalarchcode = archcode2+"0"+ sum;
						} else if(sum < 1000) {
							catalarchcode = archcode2+sum;
						} 
						
						
						//不考虑本条记录
						if(nextPagecount == null  || nextPagecount == 0)
							updateSer2.invokeUpdate(catalarchcode,objuid);
						else 
							updateSer.invokeUpdate(catalarchcode,scPageNumber+"",objuid);
						//更新数据
						if(nextPagecount != null)
							scPageCount += nextPagecount;
						scPageNumber = scPageCount + 1;
					} 
				}
			}
			
			//删除时用，删除当前记录
			if("shanc".equals(type)) {
				DOService deleteSer = DOService.getService("archcatalog_delete_by_form_objuid");
				deleteSer.invokeUpdate(currobjuid);
				
				DOService updateitem = DOService.getService("architem_update_gx_ys_js");
				updateitem.invokeUpdate();
			} 
			
		}
		return DEFAULT_FORWARD;
		}
		
	}