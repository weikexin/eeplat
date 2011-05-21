package com.exedosoft.plat.login.zidingyi;


import java.sql.Connection;


import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;

import com.exedosoft.plat.login.zidingyi.excel.MySqlOperation;
import com.exedosoft.plat.util.DOGlobals;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DelExcelFile extends DOAbstractAction {

	public String excute() {
		String path = DOGlobals.getInstance().getValue("uploadfiletemp");
		DOBO  theBO = DOBO.getDOBOByName("gz_salarymessage");
		BOInstance bi = theBO.getCorrInstance();
		String fileName = bi.getValue("name");
		String filePath = path + fileName;
		
		String deltype = DOGlobals.getInstance().getSessoinContext()
		.getFormInstance().getValue("deltype");
		
//		System.out.println("path+++++++++++++++++++=" + path);
//		System.out.println("fileName+++++++++++++++=" + fileName);
//		System.out.println("filePath+++++++++++++++=" + filePath);
		try {
			
			if("all".equals(deltype)) {
				//删除文件
				if(FileOperation.isExistsFile(filePath)) {
					/**
					 * @param type:0,文件不存在;1,文件为目录;2,文件成功删除;3,文件删除失败
					 */
					int type = FileOperation.delFile(filePath);
					if(type == 3) {
						return "文件无法删除！";
					}
				} 
				
				//删除记录
				DOService delSer = service.getService("gz_salarymessage_delete_xlsAndData");
				if(delSer != null) {
					delSer.invokeUpdate(bi.getValue("month"));
					return "已成功删除文件和数据记录。";
				}
			} else if("file".equals(deltype)) {
				//删除文件
				if(FileOperation.isExistsFile(filePath)) {
					/**
					 * @param type:0,文件不存在;1,文件为目录;2,文件成功删除;3,文件删除失败
					 */
					int type = FileOperation.delFile(filePath);
					if(type == 3) {
						return "文件无法删除！";
					}
				} 
				
				//删除记录
				DOService delSer = service.getService("gz_salarymessage_delete");
				if(delSer != null) {
					delSer.invokeUpdate(bi.getValue("objuid"));
					return "已成功删除文件及其记录。";
				}
			} else if("data".equals(deltype)) {
				//删除记录
				Connection conn = MySqlOperation.getConnection();
				int datasNumber = MySqlOperation.SMRemoveByDate(conn, bi.getValue("month"));
				conn.close();
				return "已成功删除该月份的所有数据记录，共" + datasNumber + "条。";
			} 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "无法删除！";
		}
		return "删除出错！";
	}

	
	public static void main(String[] args) {
		
	}

}
