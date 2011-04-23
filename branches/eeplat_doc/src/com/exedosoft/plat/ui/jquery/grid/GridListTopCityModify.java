package com.exedosoft.plat.ui.jquery.grid;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

/**
 * @author aa
 */
public class GridListTopCityModify extends DOViewTemplate {

	private static Log log = LogFactory.getLog(GridListTopCityModify.class);

	public GridListTopCityModify() {
		this.templateFile = "grid/GridListTopCityModify.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		data.put("data", getListData(gm, data));
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if(gm.getContainerPane()!=null){
			data.put("pmlName", gm.getContainerPane().getName());
		}
		data.put("formName", "a" + gm.getObjUid());

		if (gm.getContainerPane()!=null && gm.getContainerPane().getParent() != null) {

			////自动判断条件面板
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
						
			////如果配置了隐藏面板（这里的含义是  拥有表单的面板）
			DOPaneModel hpm =gm.getContainerPane().getHiddenPane(); 
			if(hpm!=null){
				if (hpm.getDOGridModel() != null) {
					String formName = "a"
							+ hpm.getDOGridModel().getObjUid();
					data.put("formName", formName);
				}
			}
			
		}

		return data;
	}

	public static List<BOInstance> getListData(DOGridModel gridModel,
			Map<String, Object> data) {
		List<BOInstance> list;
		int pageNo = 1;
		int pageNum = 0;

		if (DOGlobals.getInstance().getSessoinContext().getFormInstance()!=null && DOGlobals.getInstance().getSessoinContext().getFormInstance()
				.getValue("pageNo") != null) {
			try {
				pageNo = Integer.parseInt(DOGlobals.getInstance()
						.getSessoinContext().getFormInstance().getValue("pageNo"));
			} catch (Exception e) {

			}
		}
		// pageNo = DOGlobals.getInstance().getSessoinContext().splitPageContext
		// .getPageNo(gridModel.getService());
		// log.info("SplitPage Filter Table Get PageNO:::" + pageNo);

		if (gridModel.getRowSize() != null) {
			pageNum = gridModel.getRowSize().intValue();
		}

		if (pageNum <= 0) {
			list = gridModel.getService().invokeSelect();
		} else {
			data.put("rowSize", pageNum);
			int resultSize = gridModel.getService().getResultSize();
			int pageSize = StringUtil.getPageSize(resultSize, pageNum);
			data.put("pageSize", pageSize);
			data.put("resultSize", resultSize);
			data.put("pageNo", pageNo);

			list = gridModel.getService().invokeSelect(pageNo, pageNum);

		}
		
		/////处理第二服务（统计用）
		DOService secondService = gridModel.getSecondService();
		if(secondService!=null){
			List secondResult = secondService.invokeSelect();
			if(secondResult.size() > 0){
				BOInstance statistics = (BOInstance)secondResult.get(0);
				data.put("statistics", statistics.getMap());
				StringBuilder sb = new StringBuilder();
				List<DOFormModel> listFm = gridModel.getStatisticOutGridFormLinks();
				if(listFm!=null && listFm.size()>0){
					for(Iterator<DOFormModel> it = listFm.iterator();it.hasNext();){
						DOFormModel aFm = it.next();
						aFm.setData(statistics);
						sb.append("&nbsp;&nbsp;&nbsp;&nbsp;").append(aFm.getL10n()).append(":").append(aFm.getValue()).append("&nbsp;&nbsp;&nbsp;&nbsp;");
					}
				}
				data.put("statistics_details", sb.toString());
				
			}
		}
		
		return list;
	}

}
