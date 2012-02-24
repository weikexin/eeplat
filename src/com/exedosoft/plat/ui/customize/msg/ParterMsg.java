package com.exedosoft.plat.ui.customize.msg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

import java.sql.Date;

/**
 * @author aa
 */
public class ParterMsg extends DOViewTemplate {

	private static Log log = LogFactory.getLog(ParterMsg.class);

	private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM-dd HH:mm");
	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("今天 HH:mm");
	private static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public ParterMsg() {
		this.templateFile = "customize/msg/ParterMsg.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		data.put("data", getListData(gm, data));
		data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}

		return data;
	}

	public static List<BOInstance> getListData(DOGridModel gridModel,
			Map<String, Object> data) {
		List<BOInstance> list;
		int pageNo = 1;
		int pageNum = 0;

		boolean isDirectLink = true;

		if (gridModel.getContainerPane() != null) {
			if (DOGlobals.getInstance().getSessoinContext().getFormInstance() != null
					&& !gridModel
							.getContainerPane()
							.getObjUid()
							.equals(DOGlobals.getInstance().getSessoinContext()
									.getFormInstance().getValue("panemodeluid"))) {
				isDirectLink = false;

			}

		}

		if (DOGlobals.getInstance().getSessoinContext().getFormInstance() != null
				&& DOGlobals.getInstance().getSessoinContext()
						.getFormInstance().getValue("pageNo") != null) {
			try {
				pageNo = Integer.parseInt(DOGlobals.getInstance()
						.getSessoinContext().getFormInstance()
						.getValue("pageNo"));
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

			if ("0".equals(gridModel.getTemplate()) && !isDirectLink) {
				list = new ArrayList();
			} else {
				list = gridModel.getService().invokeSelect();
			}
		} else {
			data.put("rowSize", pageNum);
			int resultSize = gridModel.getService().getResultSize();
			int pageSize = StringUtil.getPageSize(resultSize, pageNum);
			data.put("pageSize", pageSize);
			data.put("resultSize", resultSize);
			data.put("pageNo", pageNo);

			if ("0".equals(gridModel.getTemplate()) && !isDirectLink) {
				list = new ArrayList();
			} else {
				list = gridModel.getService().invokeSelect(pageNo, pageNum);
			}
		}

		// ///处理第二服务（统计用）
		DOService secondService = gridModel.getSecondService();
		if (secondService != null) {
			for (Iterator itSubject = list.iterator(); itSubject.hasNext();) {
				BOInstance aSubject = (BOInstance) itSubject.next();
				aSubject.putValue("mDate", getTimeStr(aSubject.getDateValue("msg_time")));
				List commnents = secondService.invokeSelect(aSubject.getUid());
				aSubject.putValue("comments", commnents);
				for(Iterator itComm = commnents.iterator() ; itComm.hasNext();){
					BOInstance aComm = (BOInstance)itComm.next();
					aComm.putValue("mDate", getTimeStr(aComm.getDateValue("c_time")));
				}
			}
		}

		return list;
	}

	public static String getTimeStr(java.sql.Date aDate) {

		try {
			Date current = new Date(System.currentTimeMillis());

			long beforTimes = System.currentTimeMillis() - aDate.getTime();

			if (beforTimes < 51 * 1000) {
				return  Math.round(beforTimes / 10000 + 0.5) + "0秒前";
			}

			if (beforTimes < 60 * 60 * 1000) {
				return Math.round (beforTimes / (1000 * 60) + 0.5) + "分钟前";
			}

			if (aDate.getYear() == current.getYear()
					&& aDate.getMonth() == current.getMonth()
					&& aDate.getDate() == current.getDate()) {
				return dateFormat2.format(aDate);

			} else if (aDate.getYear() == current.getYear()) {
				return dateFormat1.format(aDate);
			} else {
				return dateFormat3.format(aDate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return dateFormat3.format(aDate);
		}

	}

	public static void main(String[] args) {

		int i = (int) Math.round( 0.51);
		System.out.println("i:::::::::" + i);
	}

}
