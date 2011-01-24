package com.exedosoft.plat.ui.jquery.grid;

import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;


public class OutGridExcelSelected implements DOIView {

	public String getHtmlCode(DOIModel aModel) {

		DOGridModel gridModel = (DOGridModel) aModel;

		List list = null;
		if (list == null || list.size() == 0) {
			if (gridModel.getService() != null) {
				list = gridModel.getService().invokeSelect();
			}
		}

		StringBuffer buffer = new StringBuffer();
		

		List properties = gridModel.getNormalGridFormLinks();
		/**
		 * 处理表头
		 */
		for (Iterator itCol = properties.iterator(); itCol.hasNext();) {
			DOFormModel link = (DOFormModel) itCol.next();
			buffer.append(link.getL10n());
			buffer.append("\t");
		}
		buffer.append("\n");

		
		//BOInstance formInstance = DOGlobals.getInstance().getSessoinContext().getFormInstance();
		
		String[] selects =  DOGlobals.getInstance().getServletContext().getRequest().getParameterValues("checkinstance");
		
		
		List lSelects = Arrays.asList(selects);
		
		
		/**
		 * 处理内容
		 */
		for (Iterator it = list.iterator(); it.hasNext();) {
			BOInstance ins = (BOInstance) it.next();
			if(!lSelects.contains(ins.getUid())){
				continue;
			}
			for (Iterator itCol = properties.iterator(); itCol.hasNext();) {
				DOFormModel link = (DOFormModel) itCol.next();
				link.setData(ins);
				String value = link.getHtmlValue();
				value = value == "&nbsp;" ? "" : value;
				buffer.append(value);
				buffer.append("\t");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

}
