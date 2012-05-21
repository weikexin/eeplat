package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;
import com.exedosoft.plat.util.xml.DOMXmlUtil;

public class DOImport extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;
	private static Log log = LogFactory.getLog(DOImport.class);

	@Override
	public String excute() throws ExedoException {

		if (this.service == null || this.service.getTempSql() == null) {
			System.out.println("未配置SQL 语句");
			this.setEchoValue("未配置SQL 语句");
			return NO_FORWARD;
		}

		Transaction t = this.service.currentTransaction();
		try {
			t.begin();

			// 数据源ID
			// String datasourceuid = this.actionForm.getValue("datasourceuid");
			// 业务包UID
			String bpuid = this.actionForm.getValue("bpuid");

			String fileName = this.actionForm.getValue("fileName");

			if (fileName == null || fileName.trim().equals("")) {
				this.setEchoValue("你还没有选择文件！");
				return NO_FORWARD;
			}

			// if (datasourceuid == null || datasourceuid.trim().equals("")) {
			// this.setEchoValue("你没有选择数据源！ ");
			// return NO_FORWARD;
			// }

			fileName = DOGlobals.UPLOAD_TEMP.trim() + "/" + fileName.trim();
			System.out.println("FileName::" + fileName);
			boolean isImport = importXml(bpuid, fileName);
			if (!isImport) {
				return NO_FORWARD;
			}
			this.setEchoValue("翻译完成!");

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			t.end();
		}

		this.setEchoValue("导入成功!");
		return DEFAULT_FORWARD;

	}

	public boolean importXml(String bpuid, String fileName) {
		try {
			Document doc = DOMXmlUtil.getInstance().getDocument(fileName);

			DOService appInsert = DOService.getService("DO_Application_Insert");

			DOService packageInsert = DOService
					.getService("DO_BusiPackage_copy");

			DOService doboInsert = DOService.getService("DO_BO_Insert");

			DOService parameterInsert = DOService
					.getService("DO_Parameter_copy");

			DOService propertyInsert = DOService
					.getService("DO_BO_Property_copy");

			DOService doRuleInsert = DOService.getService("DO_Rule_Insert");

			DOService serviceInsert = DOService.getService("DO_Service_copy");

			DOService serviceRuleInsert = DOService
					.getService("DO_Service_Rule_copy");

			DOService paraServiceInsert = DOService
					.getService("DO_Parameter_Service_Copy_Batch");

			DOService paneLinksInsert = DOService
					.getService("DO_UI_PaneLinks_copy");

			DOService formInsert = DOService
					.getService("DO_UI_FormModel_COPY_TO_GRID");

			DOService formTargetInsert = DOService
					.getService("DO_UI_FormTargets_Insert");

			DOService formLinksInsert = DOService
					.getService("DO_UI_FormLinks_Insert");

			DOService gridInsert = DOService
					.getService("DO_UI_GridModel_Insert");

			DOService paneModelInsert = DOService
					.getService("DO_UI_PaneModel_copy");

			DOService treeModelInsert = DOService
					.getService("DO_UI_TreeModel_Insert");

			DOService menuModelInsert = DOService
					.getService("DO_UI_MenuModel_copy");

			DOService controllerInsert = DOService
					.getService("DO_UI_Controller_Insert");

			DOService actionInsert = DOService
					.getService("DO_ActionConfig_Insert");

			NodeList children = doc.getDocumentElement().getChildNodes();

			String tenancy = null;
			String curTenancy = null;
			String createTableSql = null;
			List<String> coljsons = new ArrayList<String>();
			// //////////////////////////////////////////////////////////////////////////////
			for (int i = 0; i < children.getLength(); i++) {
				Node aNode = children.item(i);
				if (aNode instanceof Element) {

					if (aNode.getNodeName().equals("tenant")) {
						tenancy = aNode.getFirstChild().getNodeValue();
					} else if (aNode.getNodeName().equals("create_table_sql")) {
						createTableSql = aNode.getFirstChild().getNodeValue();
					}
				}
			}
			// //////处理多租户表的导入

			if (tenancy != null && createTableSql != null) {

				// ///更新另外一个库
				DODataSource dss = DOGlobals.getInstance().getSessoinContext()
						.getTenancyValues().getDataDDS();
				Connection con = dss.getConnection();
				PreparedStatement pstmt = con.prepareStatement(createTableSql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
				// ///////////////////////////////////end create
				// view

			}

			boolean isApp = false;
			// ////////////////////////////////////////////////////////////////////////
				for (int i = 0; i < children.getLength(); i++) {
					Node aNode = children.item(i);
					if (aNode instanceof Element) {

						if (aNode.getNodeName().equals("app")) {

							NodeList lis = aNode.getChildNodes();
							for (int lii = 0; lii < lis.getLength(); lii++) {
								Node aLi = lis.item(lii);
								if (aNode instanceof Element) {
									String aJson = aNode.getFirstChild()
											.getNodeValue();
									BOInstance biApp = BOInstance
											.fromJSONString(aJson);

									DOBO boApp = DOBO
											.getDOBOByName("do_application");
									BOInstance exists = boApp.getInstance(biApp
											.getValue("objuid"));
									if (exists != null) {
										log.info("待导入的工程已经存在，请删除后再导入!" + exists);
										this.setEchoValue("待导入的工程已经存在，请删除后再导入!");
										return false;
									}
									appInsert.invokeUpdate(biApp);
									isApp = true;
								}

							}

						} else if (aNode.getNodeName().equals("package")) {
							insertANode(packageInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("bo")) {

							NodeList lis = aNode.getChildNodes();
							for (int lii = 0; lii < lis.getLength(); lii++) {
								Node aLi = lis.item(lii);
								if (aNode instanceof Element) {
									String aJson = aNode.getFirstChild()
											.getNodeValue();
									BOInstance bi = BOInstance
											.fromJSONString(aJson);

									DOBO bo = DOBO.getDOBOByName("do_bo");
									BOInstance exists = bo.getInstance(bi
											.getValue("objuid"));
									if (exists != null) {
										log.info("待导入的业务对象已经存在，请删除后再导入!"
												+ exists);
										this.setEchoValue("待导入的业务对象已经存在，请删除后再导入!");
										return false;
									}
									bi.putValue("datasourceuid", "");
									if (!isApp) {
										bi.putValue("bpuid", bpuid);
									}
									doboInsert.invokeUpdate(bi);
								}

							}

						} else if (aNode.getNodeName().equals("property")) {
							insertANode(propertyInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("parameter")) {
							insertANode(parameterInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("rule")) {
							insertANode(doRuleInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("service")) {
							insertANode(serviceInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals(
								"parameter_service")) {
							insertANode(paraServiceInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("rule_service")) {
							insertANode(serviceRuleInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("pane")) {
							insertANode(paneModelInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("pane_links")) {
							insertANode(paneLinksInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("grid")) {
							insertANode(gridInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("form")) {
							insertANode(formInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("form_relation")) {
							insertANode(formLinksInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("form_target")) {
							insertANode(formTargetInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("menu")) {
							insertANode(menuModelInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("tree")) {
							insertANode(treeModelInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("controller")) {
							insertANode(controllerInsert, aNode, curTenancy);
						} else if (aNode.getNodeName().equals("action")) {
							insertANode(actionInsert, aNode, curTenancy);
						}
					}
				}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private static void insertANode(DOService insertService, Node aNode,
			String curTenancy) throws JSONException, ExedoException {
		
		if(insertService==null){
			log.error("服务没有定义..................................");
			return;
		}
		
		NodeList lis = aNode.getChildNodes();
		for (int lii = 0; lii < lis.getLength(); lii++) {
			Node aLi = lis.item(lii);
			if (aLi instanceof Element) {
				String aJson = aLi.getFirstChild().getNodeValue();
				if (aJson != null && !aJson.trim().equals("")) {
					BOInstance bi = BOInstance.fromJSONString(aJson);
					if (insertService.getBo()
							.getInstance(bi.getValue("objuid")) == null) {
						insertService.invokeUpdate(bi);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

	}

}
