package com.exedosoft.plat.ui.jquery.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 将查询结果导出成excel的控制器，实现无论该按钮处在条件表格中，还是结果列表中，都可以使用。
 * 
 * @author ZhangYunHe
 * 
 */
public class TExportDataToExcel extends DOViewTemplate {

	public TExportDataToExcel() {
		this.templateFile = "form/TExportDataToExcel.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOFormModel btn = (DOFormModel) doimodel;

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		data.put("model", btn);

		DOGridModel grid = btn.getGridModel();// 我的父节点,表格
		DOPaneModel panel = grid.getContainerPane();// 获得表格的上级，内容面板
		List children = panel.getParent().retrieveChildren();// 内容面板的上级，主面板，从主面板中获得“条件面板”和“结果面板”
		if (children != null && children.size() == 2) {
			if (grid.getName().toLowerCase().indexOf("conditionpanel") != -1) {// 判断我位于“条件面板”
				// 如果“导出”按钮处在“条件表单”中，需要获得“结果列表”中的“服务”的ID
				DOPaneModel result = (DOPaneModel) children.get(1);// 结果面板
				if (result != null) {
					if (result.getDOGridModel() != null) {
						data.put("serviceID", result.getDOGridModel()
								.getService().getObjUid());// 服务的ID
					}
				}
			}

			if (grid.getName().toLowerCase().indexOf("resultpanel") != -1) {// 判断我是否位于“结果面板”
				// 如果“导出”按钮处在“结果列表”中，需要获得“条件表单”中的form的ID
				DOPaneModel condition = (DOPaneModel) children.get(0);
				if (condition != null) {
					if (condition.getDOGridModel() != null) {
						data.put("formID", "a"+condition
								.getDOGridModel().getObjUid());// FORM的ID
					}
				}
			}
		}

		return data;
	}

}
