package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.json.JSONException;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;

public class DOImport extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;
	private static Log log = LogFactory.getLog(DOImport.class);

	@Override
	public String excute() throws ExedoException {

		if (this.service == null || this.service.getTempSql() == null) {
			this.setEchoValue(I18n.instance().get("未配置SQL 语句"));
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
				this.setEchoValue(I18n.instance().get("你还没有选择文件！"));

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
			this.setEchoValue(I18n.instance().get("翻译完成!"));

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			t.end();
		}

		this.setEchoValue(I18n.instance().get("导入成功!"));

		return DEFAULT_FORWARD;

	}

	public boolean importXml(String bpuid, String fileName) {
		try {

			SAXReader saxReader = new SAXReader();

			Document document = saxReader.read(fileName);

			Element rootElement = document.getRootElement();

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
					.getService("DO_UI_FormModel_COPY_TO_GRID_EN");

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

			String tenancy = null;
			String curTenancy = null;
			String createTableSql = null;
			List<String> coljsons = new ArrayList<String>();
			// //////////////////////////////////////////////////////////////////////////////

			// for (Iterator it = rootElement.iterator(); it.hasNext();) {
			// Node node = (Node) it.next();
			// if (node instanceof Element) {
			// Element elem = (Element) node;
			// Attribute attrKey = elem.attribute("name");
			// for (int i = 0; i < elem.nodeCount(); i++) {
			// Node childNode = elem.node(i);
			// if (childNode instanceof Element) {
			for (Iterator it = rootElement.iterator(); it.hasNext();) {
				Node aNode = (Node) it.next();
				if (aNode instanceof Element) {
					Element elem = (Element) aNode;

					if (elem.getName().equals("tenant")) {
						tenancy = elem.getTextTrim();
					} else if (elem.getName().equals("create_table_sql")) {
						createTableSql = elem.getTextTrim(); // //////处理多租户表的导入

						if ("true".equals(DOGlobals.getValue("multi.tenancy"))
								&& tenancy != null && createTableSql != null) {

							createTable(createTableSql);
							// ///////////////////////////////////end create
							// view

						}

					}
				}
			}

			boolean isApp = false;
			// ////////////////////////////////////////////////////////////////////////
			for (Iterator it = rootElement.iterator(); it.hasNext();) {
				Node aNode = (Node) it.next();
				if (aNode instanceof Element) {
					Element elem = (Element) aNode;

					if (elem.getName().equals("app")) {

						for (Iterator itlii = elem.iterator(); itlii.hasNext();) {
							Node aLi = (Node) itlii.next();
							if (aNode instanceof Element) {
								String aJson = ((Element) aNode).getTextTrim();
								BOInstance biApp = BOInstance
										.fromJSONString(aJson);

								DOBO boApp = DOBO
										.getDOBOByName("do_application");
								BOInstance exists = boApp.getInstance(biApp
										.getValue("objuid"));
								if (exists != null) {
									log.info("待导入的工程已经存在，请删除后再导入!" + exists);
									this.setEchoValue(I18n.instance().get(
											"待导入的工程已经存在，请删除后再导入!"));
									return false;
								}
								appInsert.invokeUpdate(biApp);
								isApp = true;
							}

						}

					} else if (elem.getName().equals("package")) {
						insertANode(packageInsert, aNode, curTenancy);
					} else if (elem.getName().equals("bo")) {

						for (Iterator itlii = elem.iterator(); itlii.hasNext();) {
							Node aLi = (Node) itlii.next();

							if (aNode instanceof Element) {
								String aJson = ((Element) aNode).getTextTrim();
								BOInstance bi = BOInstance
										.fromJSONString(aJson);

								DOBO bo = DOBO.getDOBOByName("do_bo");
								BOInstance exists = bo.getInstance(bi
										.getValue("objuid"));
								if (exists != null) {
									log.info("待导入的业务对象已经存在，请删除后再导入!" + exists);
									this.setEchoValue(I18n.instance().get(
											"待导入的业务对象已经存在，请删除后再导入!"));
									return false;
								}
								bi.putValue("datasourceuid", "");
								if (!isApp) {
									bi.putValue("bpuid", bpuid);
								}
								doboInsert.invokeUpdate(bi);
							}

						}

					} else if (aNode.getName().equals("property")) {
						insertANode(propertyInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("parameter")) {
						insertANode(parameterInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("rule")) {
						insertANode(doRuleInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("service")) {
						insertANode(serviceInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("parameter_service")) {
						insertANode(paraServiceInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("rule_service")) {
						insertANode(serviceRuleInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("pane")) {
						insertANode(paneModelInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("pane_links")) {
						insertANode(paneLinksInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("grid")) {
						insertANode(gridInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("form")) {
						insertANode(formInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("form_relation")) {
						insertANode(formLinksInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("form_target")) {
						insertANode(formTargetInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("menu")) {
						insertANode(menuModelInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("tree")) {
						insertANode(treeModelInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("controller")) {
						insertANode(controllerInsert, aNode, curTenancy);
					} else if (aNode.getName().equals("action")) {
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

	private void createTable(String createTableSql) {
		// ///更新另外一个库
		DODataSource dss = DOGlobals.getInstance()
				.getSessoinContext().getTenancyValues()
				.getDataDDS();
		Connection con = dss.getConnection();
		try {

			PreparedStatement pstmt = con
					.prepareStatement(createTableSql);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}

	private static void insertANode(DOService insertService, Node aNode,
			String curTenancy) throws JSONException, ExedoException {

		if (insertService == null) {
			log.error("服务没有定义..................................");
			return;
		}

		for (Iterator itlii = ((Element) aNode).iterator(); itlii.hasNext();) {
			Node aLi = (Node) itlii.next();

			if (aNode instanceof Element) {
				String aJson = aLi.getText();
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
