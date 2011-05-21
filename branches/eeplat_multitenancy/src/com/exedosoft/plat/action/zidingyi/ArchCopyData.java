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

public class ArchCopyData extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		try {
			DOBO itemBO = DOBO.getDOBOByName("architem");
			BOInstance itembi = (BOInstance) itemBO.getCorrInstance();
			String arch_uid = itembi.getUid();
			String cg_version = null;
			String yx_version = null;
			DOService linkversion_cg = DOService
					.getService("archcatalog_browse_huoquxmversionno_caogao");
			List<BOInstance> listversion_cg = linkversion_cg.invokeSelect(arch_uid);
			if (listversion_cg != null && listversion_cg.size() > 0) {
				BOInstance bi = listversion_cg.get(0);
				cg_version = bi.getValue("version");
			}
			
			DOService linkversion_yx = DOService
			.getService("archcatalog_browse_huoquxmversionno_zuixin");
			List<BOInstance> listversion_yx = linkversion_yx.invokeSelect(arch_uid);
			if (listversion_yx != null && listversion_yx.size() > 0) {
				BOInstance bi = listversion_yx.get(0);
				yx_version = bi.getValue("version");
			}
			//取得有效版本的所有目录
			DOService serCata_yx = DOService
			.getService("archcatalog_browse__cx_curr_item");
			List<BOInstance> listCata_yx = serCata_yx.invokeSelect(yx_version,arch_uid);
			if (listCata_yx != null && listCata_yx.size() > 0) {
				for(int i = 0; i < listCata_yx.size(); i++) {
					BOInstance catabi = listCata_yx.get(i);
					String catauid = catabi.getValue("objuid");
					String item_uid=catabi.getValue("item_uid");
					String archcode=catabi.getValue("archcode");
					String titlename=catabi.getValue("titlename");
					String pagecount=catabi.getValue("pagecount");
					String pagenumber=catabi.getValue("pagenumber");
					String subtype_uid=catabi.getValue("subtype_uid");
					//复制该目录archcatalog_insert_copy_use
					//archcatalog(objuid,item_uid,version_uid,archcode,titlename,pagecount,pagenumber,subtype_uid) values(?,?,?,?,?,?,?,?)
					DOService insertCata_cp = DOService
					.getService("archcatalog_insert_copy_use");
					String catacpuid = (String) UUIDHex.getInstance().generate();
					insertCata_cp.invokeUpdate(catacpuid,item_uid,cg_version,archcode,titlename,pagecount,pagenumber,subtype_uid);
					
					//查找该目录的所有文件
					DOService serFiles_yx = DOService
					.getService("archfiles_browse_by_catalog_uid");
					List<BOInstance> listFiles_yx = serFiles_yx.invokeSelect(catauid);
					for(int l = 0; l < listFiles_yx.size(); l++) {
						BOInstance filebi = listFiles_yx.get(l);
						String filepath=filebi.getValue("filepath");
						String filename=filebi.getValue("filename");
						String orderno=filebi.getValue("orderno");
						String filestatus=filebi.getValue("filestatus");
						//复制文件insert into archfiles(objuid,catalog_uid,filepath,filename,orderno,filestatus) values(?,?,?,?,?,?)
						DOService insertFile_cp = DOService
						.getService("archfiles_insert_file_copy");
						String fileuid = (String) UUIDHex.getInstance().generate();
						insertFile_cp.invokeUpdate(fileuid,catacpuid,filepath,filename,orderno,filestatus);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {
		String serialno = "ASDFas.dfasdf";
		String fileName = serialno;
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.indexOf("."));
			System.out.println(fileName);
		}
		System.out.println(fileName);
	}

}
