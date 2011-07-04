package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class DORemoveConfigDOBO extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8093868865224942852L;
	private static Log log = LogFactory.getLog(DORemoveConfigDOBO.class);

	public String excute() {

		// DOService DO_BusiPackage_deletebyapplicationuid =
		// DOService.getService("DO_BusiPackage_deletebyapplicationuid");

		DOBO bo = DOBO.getDOBOByName("DO_BO");
		BOInstance instance = bo.getCorrInstance();
		DOService deletes = DOService.getService("DO_Parameter_deletebybouid");
		Transaction t = deletes.currentTransaction();
		t.begin();
		try {

			// ////////////////////////如果是多租户的情况，需要删除多租户元数据表的情况，还要drop掉视图
			// //多租户表
			if ("2".equals(instance.getValue("type"))) {

				BOInstance theTenancy = (BOInstance) DOGlobals.getInstance()
						.getSessoinContext().getTenancyValues().getTenant();

				System.out.println("instance:::" + instance);
				DOBO tenancyBO = DOBO.getDOBOByID(instance.getUid());
				System.out.println("tenancyBO:::" + tenancyBO);
				DOService findTenancyTable = DOService
						.getService("multi_tenancy_table_findrealtable");
				BOInstance biTenancyTable = findTenancyTable.getInstance(
						instance.getValue("sqlstr"), theTenancy
								.getValue("name"));

				if (biTenancyTable != null) {
					DOService delTenancyTable = DOService
							.getService("multi_tenancy_table_delete");
					delTenancyTable.invokeUpdate(biTenancyTable.getUid());

					DOService delTenacyCols = DOService
							.getService("multi_tenancy_column_deletebytableid");
					delTenacyCols.invokeUpdate(biTenancyTable.getUid());
				}

				if (tenancyBO != null) {
					// /////////////删除视图
					StringBuffer sb = new StringBuffer("drop view ").append(
							theTenancy.getValue("name")).append("_").append(
							tenancyBO.getSqlStr());

					Connection con = tenancyBO.getDataBase().getConnection();
					try {
						PreparedStatement pstmt = con.prepareStatement(sb
								.toString());
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// ////////////////////////////////////////////end 处理多租户的情况

			deletes.invokeUpdate(instance.getUid());

			deleteRubbish(instance, "DO_BO_Property_deletebybouid");
			deleteRubbish(instance, "DO_Rule_deletebybouid");
			deleteRubbish(instance, "DO_Service_deletebybouid");
			deleteRubbish(instance, "DO_Service_Rule_DeleteRubbish");
			deleteRubbish(instance, "DO_Parameter_Service_deleterubbish");
			deleteRubbish(instance, "DO_UI_PaneModel_deletebyboduid");
			deleteRubbish(instance, "DO_UI_PaneLinks_Deleterubbish");

			deleteRubbish(instance, "DO_UI_GridModel_deletebycategoryUid");
			deleteRubbish(instance, "DO_UI_FormModel_deleterubbish");
			deleteRubbish(instance, "DO_UI_FormTargets_deleterubbish");
			deleteRubbish(instance, "DO_UI_FormLinks_deleterubbish");

			deleteRubbish(instance, "DO_UI_MenuModel_deletebycategoryUid");
			deleteRubbish(instance, "DO_UI_MenuModel_Deleterubbish");
			deleteRubbish(instance, "DO_UI_MenuModel_Deleterubbish");
			deleteRubbish(instance, "DO_UI_MenuModel_Deleterubbish");
			deleteRubbish(instance, "DO_UI_MenuModel_Deleterubbish");

			deleteRubbish(instance, "DO_UI_TreeModel_deletebycategoryUid");
			deleteRubbish(instance, "DO_UI_TreeModel_Deleterubbish");
			deleteRubbish(instance, "DO_UI_TreeModel_Deleterubbish");
			deleteRubbish(instance, "DO_UI_TreeModel_Deleterubbish");
			deleteRubbish(instance, "DO_UI_TreeModel_Deleterubbish");
			deleteRubbish(instance, "DO_UI_TreeModel_Deleterubbish");

			// DOService formTargetInsert = DOService
			// .getService("DO_UI_FormTargets_Insert");
			//
			// DOService formLinksInsert = DOService
			// .getService("DO_UI_FormLinks_Insert");
			//
			// DOService treeModelInsert = DOService
			// .getService("DO_UI_TreeModel_Insert");

			deleteRubbish(instance, "DO_BO_Delete");
			// /清楚缓存
			CacheFactory.getCacheData().put(instance.getUid(), null);

		} catch (ExedoException e) {
			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.end();

		return null;
	}

	private void deleteRubbish(BOInstance instance, String serviceName)
			throws ExedoException {
		DOService deletes = DOService.getService(serviceName);
		deletes.invokeUpdate(instance.getUid());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
