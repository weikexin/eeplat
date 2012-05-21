package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

/**
 *除了下拉框不分页外，取当前对应值时，如果没有值，会从当前定义的
 *服务对应的业务对象中取当前值。
 *这和DOResultListPopup DOResultList等不一样。
 *
 *					theValue = fm.getData().getValue(
					fm.getRelationProperty().getColName());
					
	这个地方有改动。
 * @author IBM
 *
 */
public class DOResultListPopupDB extends DOResultListPopup {

	public DOResultListPopupDB() {
		super();
		max_pagesize = 1000;
		default_data = false;
	}

	


}
