package com.exedosoft.plat.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOParameterService;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
*  编辑数据增删改
*
 */

public class CoreSaveEditAction extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7581994809740062108L;

	private static Log log = LogFactory.getLog(CoreSaveEditAction.class);

	public CoreSaveEditAction() {
	}

	/**
	 * Save 的情况，所以Parameter 取值时不考虑auto_condition（查询） 的情况
	 */

	public String excute() {

		log.info("进入批量保存Action::::::::::::::::::::::::::");


		BOInstance uiForm = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance();
		String formUid = uiForm.getValue("formUid");
		DOFormModel aFm = DOFormModel.getFormModelByID(formUid);

		if (aFm == null) {
			this.setEchoValue("未配置FormModel");
			return NO_FORWARD;
		}

		/*
		 * 必须定义两个服务，新增的服务和修改 的服务
		 */
		DOService insertService = aFm.getLinkService();
		DOService updateService = aFm.getSecondService();

		if (insertService == null || updateService == null) {
			this.setEchoValue("必须定义两个服务，新增的服务和修改的服务!");
			return NO_FORWARD;
		}

		Connection con = null;
		int batchSize = 0;
		try {

			con = insertService.getBo().getDataBase().getContextConnection();
 			// ////////////////////////////////////新增
			String inputName = uiForm.getValue("inputName");
			if (inputName != null && !inputName.trim().equals("")) {
				String[] inputValues = uiForm.getValueArray(inputName);
				log.info("当前执行的SQL语句:" + insertService.getTempSql());
				PreparedStatement pstmt = con.prepareStatement(insertService
						.getTempSql());

				List<String> newIds = new ArrayList<String>();
				for (int len = 0; len < inputValues.length; len++) {
					int i = 1;
					for (Iterator it = insertService.retrieveParaServiceLinks()
							.iterator(); it.hasNext();) {
						DOParameterService dops = (DOParameterService) it
								.next();
						DOParameter dop = dops.getDop();

						String value = null;

						if (dop.getType() != null
								&& dop.getType().intValue() == DOParameter.TYPE_FORM
								&& dop.getDefaultValue() == null) {// //form类型直接从节目获取
							log.info("批量修改新增的名称:::" + dop.getName());
							String[] valueArray = uiForm.getValueArray(dop
									.getName());
							value = valueArray[len];
							if ("".equals(value)) {
								value = null;
							}
						} else {
							value = dop.getValue();
						}
						
						if (dop.getType() != null
								&& dop.getType().intValue() == DOParameter.TYPE_KEY) {
							newIds.add(value);
						}
						value = dops.getAfterPattermValue(value);
						insertService.putStatementAValue(pstmt, i, dops, value);
						i++;
					}
					batchSize++;
					pstmt.addBatch();
				}
				log.info("批量新增::batchSize:::" + batchSize);
				pstmt.executeBatch();
				
				/////多租户的处理
				for(String anID : newIds){
					insertService.dealMultiTenancy(null, con, anID);
				}
			}
			// ////////////////////////////////////end 新增=====================

			// ////////////修改   begin==============================================
			DOBO refreshBO = aFm.getGridModel().getService().getBo();

			String[] ids = uiForm.getValueArray("id");
			if (ids != null && ids.length > 0) {

				PreparedStatement pstmt = con.prepareStatement(updateService
						.getTempSql());
				for (int ii = 0; ii < ids.length; ii++) {
					String idstr = ids[ii];
					System.out.println("one isstr::" + idstr);
					if (idstr != null) {
						String[] vals = idstr.split(";﹕#");
						HashMap<String, String> paras = new HashMap<String, String>();
						String id = "";
						if (vals != null && vals.length > 0) {
							id = vals[0];
							for (int j = 1; j < vals.length; j++) {
								String aKeyValue = vals[j];
								System.out.println("aKeyValue::::::" + aKeyValue);
								String[] arrayKV = aKeyValue.split("﹕﹕");
								String aValue = null;
								if(arrayKV.length > 1){
									 aValue = arrayKV[1];
								}
								paras.put(arrayKV[0],aValue);
							}
						}
						
						System.out.println("paras:::::" + paras);

						BOInstance oldInstance = refreshBO.refreshContext(id);
						String newKeyValue = null;
						int i = 1;
						for (Iterator it = updateService
								.retrieveParaServiceLinks().iterator(); it
								.hasNext();) {
							DOParameterService dops = (DOParameterService) it
									.next();
							DOParameter dop = dops.getDop();
							String value = null;
							if (dop.getType() != null
									&& dop.getType().intValue() == DOParameter.TYPE_FORM
									&& dop.getDefaultValue() == null) {
								log.info("批量修改参数的名称:::" + dop.getName());
								value = paras.get(dop.getName());
								if ("".equals(value)) {
									value = null;
								}
							} else {
								value = dop.getValue();
							}

							if (dop.getType() != null
									&& dop.getType().intValue() == DOParameter.TYPE_KEY) {
								newKeyValue = value;
							}
							value = dops.getAfterPattermValue(value);
							updateService.putStatementAValue(pstmt, i, dops,
									value);
							i++;
						}
						batchSize++;
						if (oldInstance != null) {
							CoreSaveAllAction.logOperation(updateService,
									oldInstance, newKeyValue);
						}
						pstmt.addBatch();
					}
					log.info("批量修改::batchSize:::" + batchSize);
					pstmt.executeBatch();
				}
			}

		} catch (SQLException ex1) {
			ex1.printStackTrace();
			this.setEchoValue(ex1.getMessage());
			return NO_FORWARD;
		} finally {
			updateService.clearCache();
			try {
				insertService.getBo().getDataBase().ifCloseConnection(con);
			} catch (SQLException ex1) {
				ex1.printStackTrace();
				this.setEchoValue(ex1.getMessage());
				return NO_FORWARD;
			}
		}
		return DEFAULT_FORWARD;
	}

}
