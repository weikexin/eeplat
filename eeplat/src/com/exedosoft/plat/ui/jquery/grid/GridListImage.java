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
 * 
 * @author aa
 * 
 */
public class GridListImage extends DOViewTemplate {

	private static Log log = LogFactory.getLog(GridListImage.class);

	public GridListImage() {
		this.templateFile = "grid/GridListImage.ftl";
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
		//每行显示的列数。如果界面没输入，默认为3
		int datarowSize ;
		String rowTmp = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("rowSize");
		if ( rowTmp != null && ! "".equals(rowTmp)){
			datarowSize = Integer.parseInt(rowTmp);
		}else {
			datarowSize = 3;
		}
		//获取显示图像按钮
		List  list = gm.getAllGridFormLinks();
		DOFormModel fm ;
		for ( int i = 0 ; i < list.size(); i++ ){
			fm = (DOFormModel) list.get(i);
			if ( fm.getL10n().equals("显示图像")){
				data.put("fm", fm);
				break ;
			}else{
				data.put("fm", "");
			}
		}
		data.put("datarowSize", datarowSize);
	
		return data;
	}

	public static List<BOInstance> getListData(DOGridModel gridModel,
			Map<String, Object> data) {
		List<BOInstance> list;
		int pageNo = 1;
		int pageNum = 0;

		if (DOGlobals.getInstance().getSessoinContext().getFormInstance()
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
