package com.exedosoft.plat.ui.jquery.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOResultListCode extends DOStaticList {
	

	public String getHtmlCode(DOIModel iModel) {
		
		DOFormModel property = (DOFormModel)iModel;			
		return getDynaListForm(property);
	}

	/**
	 * 获取动态列表形式的Select Form
	 *
	 * @param property
	 *            TODO
	 * @param db
	 * @return
	 */
	String getDynaListForm(DOFormModel property) {

		List halfs = new ArrayList();
		for (Iterator it = property.getLinkService().invokeSelect().iterator(); it.hasNext();) {
			BOInstance svo = (BOInstance) it.next();
			
			String[] half = new String[2];
			half[0] = (String) svo.getUid();
			half[1] = (String) svo.getName();

			halfs.add(half);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(formSelectStr(property, halfs));

		return buffer.toString();
	}

	/***************************************************************************
	 * 自动链接查找页面
	 *
	 * @param property
	 *            TODO
	 * @param db
	 *            TODO
	 * @param buffer
	 *            TODO
	 */
	void getSearchPageJS(DOFormModel property, DOBO db, StringBuffer buffer) {

//		DOService dos = null;
//
//		if (property.getLinkBO() != null) {
//			if (property.getLinkBO().getSearchService() != null) {
//				dos = property.getLinkBO().getSearchService();
//			}
//		}
//
//		if (dos == null) {
//			return;
//		}
//
//		buffer.append("\n<script language='JavaScript'>\n");
//		String uid = "";
//		if (db != null) {
//			uid = db.getObjUid().toString();
//		}
//		buffer.append("var js") // ///javascript变量 每个列应该有一个不同那个的名字
//				.append(property.getColName()).append("S=\"contextClassUid=")
//				.append(uid).append("\";\n").append(" function redirectPage")
//				.append(property.getColName()).append("S(){ \n")
//				.append("if(js").append(property.getColName()).append(
//						"S!='contextClassUid='){\n").append("window.open('")
//				.append(DOGlobals.getUnifyHref(dos, null, property.getInstanceAtList()));
//		buffer
//				.append("&popPropName=")
//				.append(property.getColName())
//				.append("&' + js")
//				.append(property.getColName())
//				.append(
//						"S,'','width=500,height=370,left=230,top=130,scrollbars=yes');}\n}\n")
//				.append("document.writeln(\"<a onClick='redirectPage").append(
//						property.getColName()).append(
//						"S();'  style='cursor:hand'>查找</a>\");\n");
//		buffer.append("</script>");
	}

	

}
