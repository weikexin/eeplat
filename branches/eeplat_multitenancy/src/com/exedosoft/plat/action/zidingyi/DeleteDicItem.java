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

public class DeleteDicItem extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		try {
			String type = DOGlobals.getInstance().getSessoinContext()
					.getInstance().getFormInstance().getValue("type");

			// 删除词典中二级类型及其目录
			if (type != null && "delsub".equals(type.trim())) {
				DOService linksub = DOService
						.getService("dicitemcatalog_browse_sub");
				List<BOInstance> listsub = linksub.invokeSelect();
				if (listsub != null && listsub.size() > 0) {
					BOInstance bi = listsub.get(0);
					String objuid = bi.getValue("objuid");
					String parentuid = bi.getValue("parentuid");
					// 删除二级类型
					DOService deleteSub = DOService
							.getService("dicitemcatalog_delete_by_objuid_sub");
					deleteSub.invokeUpdate(objuid);
					// 删除二级类型目录
					DOService deleteCatalog = DOService
							.getService("dicitemcatalog_delete_by_parentuid_catal");
					deleteCatalog.invokeUpdate(objuid);

					// 删除后，刷新总线
					DOBO itemBO = DOBO.getDOBOByName("dicitemcatalog");
					itemBO.refreshContext(parentuid);
				}
				
			
			} else if (type != null && "delcatal".equals(type.trim())) {//
				// 删除二级类型目录
				DOBO itemBO = DOBO.getDOBOByName("dicitemcatalog");
				BOInstance bi = itemBO.getCorrInstance();
				String objuid = bi.getValue("objuid");
				String parentuid = bi.getValue("parentuid");
				DOService deleteCatalog = DOService
						.getService("dicitemcatalog_delete_catalog");
				deleteCatalog.invokeUpdate(objuid);

				// 删除后，刷新总线
				itemBO.refreshContext(parentuid);
				
				
			
			} else if (type != null && "updatecatal".equals(type.trim())) {
				// 修改词典中二级类型
				DOService linksub = DOService
						.getService("dicitemcatalog_browse_sub");
				List<BOInstance> listsub = linksub.invokeSelect();
				if (listsub != null && listsub.size() > 0) {
					BOInstance bi = listsub.get(0);
					String objuid = bi.getValue("objuid");
					// 修改二级类型
					String parentuid = DOGlobals.getInstance().getSessoinContext()
					.getInstance().getFormInstance().getValue("parentuid");
					String typename = DOGlobals.getInstance().getSessoinContext()
					.getInstance().getFormInstance().getValue("typename");
					String note = DOGlobals.getInstance().getSessoinContext()
					.getInstance().getFormInstance().getValue("note");
					DOService updateSub = DOService
							.getService("dicitemcatalog_update_sub");
					updateSub.invokeUpdate(parentuid,typename,note,objuid);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {
	}

}
