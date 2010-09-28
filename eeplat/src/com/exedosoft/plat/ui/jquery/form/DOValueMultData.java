package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueMultData implements DOIView {

	public DOValueMultData() {
		
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fModel = (DOFormModel) aModel;
		DOService linkService = fModel.getLinkService();
		if (linkService == null) {
			return "&nbsp;";
		}
		if (linkService.getBo().getValueCol() == null) {
			return "&nbsp;";
		}
	
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		for (Iterator it = linkService.invokeSelect().iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			buffer.append(bi.getThisLink());
			buffer.append(";");
			if(i  > 5){
				buffer.append("<br>");
				i = 0;
			}
			i++;
		}
		return buffer.toString();	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
