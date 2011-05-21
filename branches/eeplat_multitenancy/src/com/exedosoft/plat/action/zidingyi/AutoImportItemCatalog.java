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

public class AutoImportItemCatalog extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		try {
			DOBO itemBO = DOBO.getDOBOByName("architem");
			BOInstance itembi = (BOInstance) itemBO.getCorrInstance();
			String item_uid = itembi.getUid();
			String archtype = itembi.getValue("archtype");
			String archsubtype = itembi.getValue("archsubtype");
			String rollarchcode = itembi.getValue("rollarchcode");

			String version_uid = null;
			DOService linkversion = DOService
					.getService("archcatalog_browse_huoquxmversionno_caogao");
			List<BOInstance> listversion = linkversion.invokeSelect();
			if (listversion != null && listversion.size() > 0) {
				BOInstance bi = listversion.get(0);
				version_uid = bi.getValue("version");
			}
			
			
			

			//首先判断是否需要删除该版本的旧目录记录
			DOService panduankser = DOService
			.getService("archcatalog_browse_xinz_item_drml_panduan");
			List<BOInstance> panduanList = panduankser.invokeSelect(item_uid,version_uid,archsubtype);
			//新增的时候也为零，但无影响，修改时，若为0，则项目二级类型已更改，故需删除旧目录在新导入
			if(panduanList == null || panduanList.size() <= 0) {
				DOService delser = DOService
				.getService("archcatalog_delete_by_item_version_uid");
				delser.invokeUpdate(item_uid,version_uid);
				
			//若已经存在，则不用新增，直接退出
			} else {
//				//更新item总页数、总件数
//				DOService updateitem = DOService.getService("architem_update_gx_ys_js");
//				updateitem.invokeUpdate();
				return DEFAULT_FORWARD;
			}
			
			DOService linkser = DOService
					.getService("dicitemcatalog_browse_by_parentuid");
			List<BOInstance> linkList = linkser.invokeSelect(archsubtype);
			
			if (linkList != null && linkList.size() > 0) {
				DOService insertSer = DOService.getService("archcatalog_insert_xzitem_auto");
				for (int n = 0; n < linkList.size(); n++) {
					String uid = (String) UUIDHex.getInstance().generate();
					BOInstance bi = linkList.get(n);
					String catalogname = bi.getValue("typename");
					int orderno = (int)bi.getIntValue("orderno");
					String xuhao = "";
					if(orderno < 10) {
						xuhao = "00"+orderno;
					} else if(orderno < 100) {
						xuhao = "0"+orderno;
					} else {
						xuhao = ""+orderno;
					}
					String archcode = rollarchcode.trim() + "-"+xuhao;
					insertSer.invokeUpdate(uid,item_uid,version_uid,archcode,catalogname,archsubtype);
				}
			}
			//更新item总页数、总件数
			DOService updateitem = DOService.getService("architem_update_gx_ys_js");
			updateitem.invokeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {
	}

}
