package com.exedosoft.plat.action.zidingyi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.DOAccess;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

	public class LrryModifyRefuse implements DOAccess {

		public boolean isAccess(BOInstance data) {
				if(data != null)  {
					String user_uid = DOGlobals.getInstance().getSessoinContext().getInstance().getUser().getUid();
					DOService service =DOService.getService("do_org_user_role_browse_if_this_role");
					List<BOInstance> list = service.invokeSelect(user_uid, "系统录入员");
					if(list != null && list.size() > 0) {
						String spstatus = data.getValue("spstatus");
						String keyinman = data.getValue("keyinman");
						if(user_uid != null && keyinman != null && keyinman.trim().equals(user_uid.trim())){
							if(spstatus != null && "xgth".equals(spstatus.trim())) {
								return true;
							}
						}
					}
				}
				return false;
				
			}
	}

