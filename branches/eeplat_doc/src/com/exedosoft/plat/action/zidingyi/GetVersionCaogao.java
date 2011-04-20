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

public class GetVersionCaogao extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		try {
			DOBO  itemBO = DOBO.getDOBOByName("architem");
			BOInstance itembi = (BOInstance)itemBO.getCorrInstance();
			String item_uid = "00000";
			//判断登录角色
			String user_uid = DOGlobals.getInstance().getSessoinContext().getInstance().getUser().getUid();
			DOService roleser = DOService.getService("do_org_user_role_browse_if_this_role_uid");
			//系统录入员 40288031287fd27f01287fa563bc1004
			List<BOInstance> roleList = roleser.invokeSelect(user_uid, "40288031287fd27f01287fa563bc1004");
			if(itembi == null) {
				//判断登录角色
				//系统录入员 40288031287fd27f01287fa563bc1004
				if(roleList != null && roleList.size() > 0) {
					DOService itemser = DOService.getService("architem_auto_condition_financing");
					List<BOInstance> linkList = itemser.invokeSelect();
					if(linkList != null && linkList.size() > 0){
						BOInstance bi = linkList.get(0);
						String uid = bi.getUid();
						itemBO.refreshContext(uid);
						item_uid = uid;
					} 
				} else {
					DOService itemser = DOService.getService("architem_auto_condition_arch_sp");
					List<BOInstance> linkList = itemser.invokeSelect();
					if(linkList != null && linkList.size() > 0){
						BOInstance bi = linkList.get(0);
						String uid = bi.getUid();
						itemBO.refreshContext(uid);
						item_uid = uid;
					}
				}
				
			} else {
				String spstatus = itembi.getValue("spstatus");
				//zzlr,正在录入;sqrk,申请入库;rkth,入库退回;zsrk,正式入库;sqxg,申请修改;
				//xgth,退回申请(申请修改);xglr,修改录入;xsrk,申请入库(修改);
				//xsth,退回入库申请(修改);xzrk,正式入库(修改);
				
				//判断登录角色
				//系统录入员 40288031287fd27f01287fa563bc1004
				if(roleList != null && roleList.size() > 0) {
					if(spstatus != null && ("zzlr".equals(spstatus.trim()) || "rkth".equals(spstatus.trim()) 
							|| "xglr".equals(spstatus.trim()) || "xsth".equals(spstatus.trim()))) {
						item_uid = itembi.getUid();
					} else {
						DOService itemser = DOService.getService("architem_auto_condition_financing");
						List<BOInstance> linkList = itemser.invokeSelect();
						if(linkList != null && linkList.size() > 0){
							BOInstance bi = linkList.get(0);
							String uid = bi.getUid();
							itemBO.refreshContext(uid);
							item_uid = uid;
						}
					}
					
				} else {
					
					if(spstatus != null && ("sqrk".equals(spstatus.trim()) || "xsrk".equals(spstatus.trim()))) {
						item_uid = itembi.getUid();
					} else {
						DOService itemser = DOService.getService("architem_auto_condition_arch_sp");
						List<BOInstance> linkList = itemser.invokeSelect();
						if(linkList != null && linkList.size() > 0){
							BOInstance bi = linkList.get(0);
							String uid = bi.getUid();
							itemBO.refreshContext(uid);
							item_uid = uid;
						}
					}
				}
			}
			
			
			List<BOInstance> list = new ArrayList<BOInstance>();
			String version_uid =  null;
			
			
			DOService linkser = DOService.getService("archcatalog_browse_huoquxmversionno_caogao");
			List<BOInstance> linkList = linkser.invokeSelect(item_uid);
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
	public static void main(String[] args) {
	}

}
