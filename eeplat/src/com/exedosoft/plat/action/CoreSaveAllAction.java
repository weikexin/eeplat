package com.exedosoft.plat.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ActionFactory;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOParameterService;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * 
 * 只一块要修改=======checkinstance objuid 数据问题挺多。
 * 
 * 前台是不所有信息全部传过来了。 保存的时候又根据checkinstance进行了区分。
 * 
 * 这样就造成了不一致。 怎样才能一致： 1,修改前台＝＝＝＝＝＝＝＝＝＝＝难度比较大 2,修改后台 3,前后台结合修改
 * 
 * 根据DOService 改。。。。。。。。。。。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: </
 * 
 * @author not attributable
 * @version 1.0
 */

public class CoreSaveAllAction extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7581994809740062108L;

	private static Log log = LogFactory.getLog(CoreSaveAllAction.class);

	public CoreSaveAllAction() {
	}

	/**
	 * Save 的情况，所以Parameter 取值时不考虑auto_condition（查询） 的情况
	 */

	public String excute() {

		System.out.println("进入批量修改Action::::::::::::::::::::::::::");

		if (this.service.getTempSql() == null) {
			System.out.println("未配置SQL 语句");
			this.setEchoValue("未配置SQL 语句");
		}

		String aKey = "checkinstance";
		String[] keys = this.actionForm.getValueArray(aKey);

		if (keys == null || keys.length == 0) {
			System.out.println("Key:" + aKey);
			System.out.println("没有数据");
			this.setEchoValue("没有数据");
			return NO_FORWARD;
		}

		List listKeys = Arrays.asList(keys);
		// checkinstance_hidden
		String[] key_hiddens = this.actionForm
				.getValueArray("checkinstance_hidden");
		// ////////////////清楚缓存

		// ///////////界面对应的业务对象即使需要刷新的业务对象

		DOBO refreshBO = null;

		try {
			DOFormModel buttonForm = null;

			String invokeButtonID = this.actionForm.getValue("invokeButtonUid");
			if (invokeButtonID != null && !"".equals(invokeButtonID.trim())) {// ////////////首先根据启发按钮获取
				buttonForm = DOFormModel.getFormModelByID(invokeButtonID);
			}
			if (buttonForm != null
					&& buttonForm.getTargetGridModels().size() <= 1) {
				if (buttonForm.getGridModel().getService() != null) {
					refreshBO = buttonForm.getGridModel().getService().getBo();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (refreshBO == null) {
			refreshBO = this.service.getBo();
			log.info("No InvokeButton's ID transed and refresh BO'name ::=="
					+ refreshBO.getName());
		}

		Connection con = null;

		try {
			con = this.service.getBo().getDataBase().getContextConnection();
			log.info("当前执行的SQL语句:" + this.service.getTempSql());
			PreparedStatement pstmt = con.prepareStatement(this.service
					.getTempSql());

			int batchSize = 0;

			for (int len = 0; len < key_hiddens.length; len++) {

				String keyHidden = key_hiddens[len];
				if (!listKeys.contains(keyHidden)) {
					continue;
				}
				log
						.info("Select Data's ID::::::::::::::::::::::::"
								+ keyHidden);

				BOInstance oldInstance = refreshBO.refreshContext(keyHidden);
				String newKeyValue = null;

				int i = 1;
				for (Iterator it = this.service.retrieveParaServiceLinks()
						.iterator(); it.hasNext();) {
					DOParameterService dops = (DOParameterService) it.next();
					DOParameter dop = dops.getDop();

					String value = null;

					if (dop.getType() != null
							&& dop.getType().intValue() == DOParameter.TYPE_FORM
							&& dop.getDefaultValue() == null) {
						log.info("批量修改参数的名称:::" + dop.getName());

						String[] valueArray = this.actionForm.getValueArray(dop
								.getName());

						if (valueArray == null || valueArray.length <= len) {
							value = this.actionForm.getValue(dop.getName());
						} else {
							value = valueArray[len];
							if ("".equals(value)) {
								value = null;
							}
						}
					} else {
						value = dop.getValue();

					}

					if (dop.getType() != null
							&& dop.getType().intValue() == DOParameter.TYPE_KEY) {
						newKeyValue = value;
					}

					value = dops.getAfterPattermValue(value);
					this.service.putStatementAValue(pstmt, i, dops, value);
					i++;
				}
				batchSize++;
				if (oldInstance != null) {
					this.logOperation(this.service, oldInstance, newKeyValue);
				}
				pstmt.addBatch();
			}
			log.info("批量修改::batchSize:::" + batchSize);
			pstmt.executeBatch();

		} catch (SQLException ex1) {
			ex1.printStackTrace();
			this.setEchoValue(ex1.getMessage());
			return NO_FORWARD;
		} finally {
			try {
				this.service.getBo().getDataBase().ifCloseConnection(con);
			} catch (SQLException ex1) {
				this.setEchoValue(ex1.getMessage());
				return NO_FORWARD;
			}
		}
		return DEFAULT_FORWARD;
	}

	/**
	 * 日志也要设计成一个扩展结构，不仅仅是多do_log_data 这个表的增加。
	 * 
	 * @param uid
	 */
	public static void logOperation(DOService theService,
			BOInstance oldInstance, String uid) {

		if (theService.getIsLog() == null
				|| theService.getIsLog().intValue() == DOService.LOG_NO) {
			return;
		}

		BOInstance bi = theService.getBo().getInstance(uid);
		if (bi == null) {
			return;
		}
		SessionContext.getInstance().getThreadContext().put("old_instance",
				oldInstance);

		if (theService.getFilterClass() != null
				&& !"".equals(theService.getFilterClass().trim())) {

			DOAction ca = ActionFactory.getAction(theService.getFilterClass());
			ca.setService(theService);
			if (bi != null) {
				ca.setInstance(bi);
			}
			try {
				ca.excute();
			} catch (ExedoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		String optionType = null;

		if (theService.getLogType() != null
				&& !"".equals(theService.getLogType().trim())) {
			optionType = theService.getLogType();
		}

		switch (theService.getType().intValue()) {

		case DOService.TYPE_DELETE:
			optionType = "删除";
			break;
		case DOService.TYPE_INSERT:
			optionType = "新增";
			break;
		case DOService.TYPE_UPDATE_AUTO_PARA:
		case DOService.TYPE_UPDATE:

			optionType = "修改";
			break;
		}
		// insert into
		// do_log_data(objuid,table_name,col_name,who_uid,bo_uid,col_uid,oper_type,oper_data,
		// oper_time,oper_data_uid,oper_pane_uid,old_value,new_value)
		// values(?,?,?,?,?,?,?,?,?,?,?,?,?)

		DOService aLogService = DOService.getService("do_log_data_insert");
		BOInstance aLog = new BOInstance();
		aLog.putValue("TABLE_NAME", theService.getBo().getL10n());
		aLog.putValue("OPER_TYPE", optionType);
		aLog.putValue("OPER_DATA", bi.getName());
		aLog.putValue("oper_data_uid", bi.getUid());
		if (oldInstance != null) {
			aLog.putValue("old_value", oldInstance.getName() + "-------详细信息："
					+ oldInstance.toString());
		}

		try {
			aLogService.invokeUpdate(aLog);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}