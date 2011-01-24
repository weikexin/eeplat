package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.SessionContext;


/**
 * 支持的一种界面风格，把修改、删除等操作直接放到列表的每条记录的后面。
 * DOOPeration是一zhon
 * 
 * @author aa
 * 
 */
public class SuiteBottom implements DOIView {

	public String getHtmlCode(DOIModel aModel) {
		
		SessionContext us = DOGlobals.getInstance().getSessoinContext();
		String readonly = null;
		if(us!=null && us.getUser()!=null){
			readonly = us.getUser().getValue("readonly");
		}
		
		
		

		DOFormModel property = (DOFormModel) aModel;

		DOGridModel gm = property.getGridModel();
		if (gm != null) {
			List list = gm.getRightOutGridFormLinks();
			if (list != null) {
				StringBuffer buffer = new StringBuffer("&nbsp;");
				for (Iterator it = list.iterator(); it.hasNext();) {
					DOFormModel rform = (DOFormModel) it.next();
					rform.setData(property.getData());
               
					if (rform.isAccess(property.getData())) {

						// ////put 删除,修改,等操作
						String rename = rform.getRename();
						if (rename == null || "".equals(rename.trim())) {
							rename = String.valueOf(rform
									.getL10n().hashCode());
						}

						property.getData().putValue(
								rename,
								rform.getL10n().trim());


						if(readonly == null){
							buffer.append(rform.getHtmlValue());
						}else{
							buffer.append("<a href='#'><font color='#C0C0C0'>" + rform.getL10n() + "</font></a>");
						}
						buffer.append("&nbsp;");
					}
				}
				return buffer.toString();
			}
		}
		return "&nbsp;";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
