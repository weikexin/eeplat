package com.exedosoft.plat.ui.customize.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.gene.jquery.PropertyManager;
import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.grid.GridList;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;
import com.exedosoft.safe.TenancyValues;

public class TableManager extends GridList {

	private static Log log = LogFactory.getLog(TableManager.class);

	public TableManager() {

		this.templateFile = "customize/tools/TableManager.ftl";

		if (DOGlobals.getInstance().getSessoinContext().getTenancyValues() != null) {
			BOInstance biTenancy = (BOInstance) DOGlobals.getInstance()
					.getSessoinContext().getTenancyValues().getTenant();
			if (biTenancy != null) {

				DOBO dobo = DOBO.getDOBOByName("do_bo");
				BOInstance biTenancyTable = null;
				if (biTenancy != null && dobo.getCorrInstance() != null
						&& "2".equals(dobo.getCorrInstance().getValue("type"))) {
					this.templateFile = "customize/tools/TableManagerTenancy.ftl";
				}
			}
		}

	}

	//
	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;

		Map<String, Object> data = new HashMap<String, Object>();

		TenancyValues tv = DOGlobals.getInstance().getSessoinContext()
				.getTenancyValues();
		// //当多租户情况下，更换表格元素

		if (tv != null) {
			BOInstance biTenancy = tv.getTenant();

			if (biTenancy != null) {
				DOController.flushController(gm.getController().getObjUid());
			}
		}

		data.put("model", gm);

		if (tv != null) {
			DOBO dobo = DOBO.getDOBOByName("do_bo");

			BOInstance biTenancyTable = null;
			if (tv.getTenant() != null && dobo.getCorrInstance() != null
					&& "2".equals(dobo.getCorrInstance().getValue("type"))) {
				DOGridModel gmTenancy = DOGridModel
						.getGridModelByName("GM_multi_tenancy_column_oftable");
				data.put("model", gmTenancy);
			}
		}

		data.put("data", getListData(gm, data));
		data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		data.put("formName", "a" + gm.getObjUid());

		if (gm.getContainerPane() != null
				&& gm.getContainerPane().getParent() != null) {

			// //自动判断条件面板
			List children = gm.getContainerPane().getParent()
					.retrieveChildren();

			if (children != null && children.size() == 2) {
				DOPaneModel conditionPane = (DOPaneModel) children.get(0);
				DOPaneModel resultModel = (DOPaneModel) children.get(1);
				if (conditionPane != null) {
					if (conditionPane.getDOGridModel() != null) {
						String formName = "a"
								+ conditionPane.getDOGridModel().getObjUid();
						data.put("formName", formName);
					}
				}

			}

			// //如果配置了隐藏面板（这里的含义是 拥有表单的面板）
			DOPaneModel hpm = gm.getContainerPane().getHiddenPane();
			if (hpm != null) {
				if (hpm.getDOGridModel() != null) {
					String formName = "a" + hpm.getDOGridModel().getObjUid();
					data.put("formName", formName);
				}
			}

		}

		return data;
	}

	public static List<BOInstance> getListData(DOGridModel gridModel,
			Map<String, Object> data) {

		DOBO dobo = DOBO.getDOBOByName("do_bo");

		TenancyValues tv =  DOGlobals.getInstance()
				.getSessoinContext().getTenancyValues();

		if (tv != null && tv.getTenant()!=null && dobo.getCorrInstance() != null
				&& "2".equals(dobo.getCorrInstance().getValue("type"))) {
			DOBO theBO = DOBO.getDOBOByID(dobo.getCorrInstance().getUid());
			DOService findColumns = DOService
					.getService("multi_tenancy_column_findbytableid");

			DOService findTable = DOService
					.getService("multi_tenancy_table_findrealtable");

			// multi_tenancy_column_insert

			BOInstance theTable = findTable.getInstance(theBO.getSqlStr());
			if (theTable != null) {
				List list = findColumns.invokeSelect(theTable.getUid());
				return list;
			}
			return new ArrayList();
		} else {
			return GridList.getListData(gridModel, data);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
