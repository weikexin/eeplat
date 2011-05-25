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
			String datasourceuid = this.actionForm.getValue("datasourceuid");
			// 业务包UID
			String bpuid = this.actionForm.getValue("bpuid");

			String fileName = this.actionForm.getValue("fileName");

			if (fileName == null) {
				this.setEchoValue("你还没有选择文件！");
				return NO_FORWARD;
			}
			
			if(datasourceuid==null){
				this.setEchoValue("你没有选择数据源！");
				return NO_FORWARD;
			}

			fileName = DOGlobals.UPLOAD_TEMP.trim()
					+ StringUtil.getCurrentDayStr() + "/" + fileName.trim();
			System.out.println("FileName::" + fileName);
			try {
				Document doc = DOMXmlUtil.getInstance().getDocument(fileName);

				DOService doboInsert = DOService.getService("DO_BO_Insert");

				DOService parameterInsert = DOService
						.getService("DO_Parameter_copy");

				DOService propertyInsert = DOService
						.getService("DO_BO_Property_copy");

				DOService doRuleInsert = DOService.getService("DO_Rule_Insert");

				DOService serviceInsert = DOService
						.getService("DO_Service_copy");

				DOService serviceRuleInsert = DOService
						.getService("DO_Service_Rule_Insert");

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
				String tenancyTableJson = null;
				List<String> coljsons = new ArrayList<String>();
				// //////////////////////////////////////////////////////////////////////////////
				for (int i = 0; i < children.getLength(); i++) {
					Node aNode = children.item(i);
					if (aNode instanceof Element) {

						if (aNode.getNodeName().equals("tenancy")) {
							tenancy = aNode.getFirstChild().getNodeValue();
						} else if (aNode.getNodeName().equals("tenancy_table")) {
							tenancyTableJson = aNode.getFirstChild()
									.getNodeValue();
						} else if (aNode.getNodeName()
								.equals("tenancy_columns")) {
							NodeList lis = aNode.getChildNodes();
							for (int lii = 0; lii < lis.getLength(); lii++) {
								Node aLi = lis.item(lii);
								if (aLi instanceof Element) {
									coljsons.add(aLi.getFirstChild()
											.getNodeValue());
								}
							}
						}
					}
				}
				// //////处理多租户表的导入

				boolean needImport = false;
				if (tenancy != null && tenancyTableJson != null) {
					if (DOGlobals.getInstance().getSessoinContext().getUser() != null) {
						BOInstance biTenancy = (BOInstance) DOGlobals
								.getInstance().getSessoinContext().getUser()
								.getObjectValue("tenancy");
						curTenancy = biTenancy.getValue("name");

						if (biTenancy != null && curTenancy != null) {

							BOInstance tenancyTable = BOInstance
									.fromJSONString(tenancyTableJson);
							// 新的tennacyTable表
							tenancyTable.putValue("id", null);
							tenancyTable.putValue("table_name", tenancyTable
									.getValue("table_name").replace(tenancy,
											curTenancy));
							tenancyTable.putValue("corr_view", tenancyTable
									.getValue("table_name").replace(tenancy,
											curTenancy));
							tenancyTable.putValue("tenancy_uid", biTenancy
									.getUid());
							DOService findTenancyByTable = DOService
									.getService("multi_tenancy_table_findbytablename");
							List finded = findTenancyByTable
									.invokeSelect(tenancyTable
											.getValue("table_name"));
							if (finded.size() == 0) {
								needImport = true;
								DOService insertTenancyTable = DOService
										.getService("multi_tenancy_table_insert");
								BOInstance newTenancy = insertTenancyTable
										.invokeUpdate(tenancyTable);

								DOService insertTenancyCol = DOService
										.getService("multi_tenancy_column_insert");

								StringBuffer sb = new StringBuffer(
										"create view  ");
								sb.append(tenancyTable.getValue("table_name"))
										.append(" as select ");
								int i = 0;

								for (Iterator<String> it = coljsons.iterator(); it
										.hasNext();) {
									String aJson = it.next();
									BOInstance colBI = BOInstance
											.fromJSONString(aJson);
									colBI.putValue("id", null);
									colBI.putValue("tenancy_table_uid",
											newTenancy.getUid());
									insertTenancyCol.invokeUpdate(colBI);

									sb.append(colBI.getValue("real_col"))
											.append(" as ").append(
													colBI.getValue("col_name"));

									if (i < (coljsons.size() - 1)) {
										sb.append(", ");
									}
									i++;
								}
								// ///////////////////////////////////begin
								// create view

								sb
										.append(
												"  from  t001 where tenancyId='")
										.append(biTenancy.getUid())
										.append("' and tenancyTableId='")
										.append(
												tenancyTable
														.getValue("table_name"))
										.append("'");
								log.info(" the View::::" + sb);

								// ///更新另外一个库
								DOBO bo = DOBO.getDOBOByName("do_datasource");
								DODataSource dss = DODataSource.getDataSourceByID(datasourceuid);
								Connection con = dss.getConnection();
								PreparedStatement pstmt = con
										.prepareStatement(sb.toString());
								pstmt.executeUpdate();
								pstmt.close();
								con.close();
								// ///////////////////////////////////end create
								// view
							}
						}
					}
				}

				// ////////////////////////////////////////////////////////////////////////
				if (needImport)// /需要导入
					for (int i = 0; i < children.getLength(); i++) {
						Node aNode = children.item(i);
						if (aNode instanceof Element) {

							if (aNode.getNodeName().equals("bo")) {

								NodeList lis = aNode.getChildNodes();
								for (int lii = 0; lii < lis.getLength(); lii++) {
									Node aLi = lis.item(lii);
									if (aNode instanceof Element) {
										String aJson = aNode.getFirstChild()
												.getNodeValue();
										aJson = aJson.replace(tenancy,
												curTenancy);
										BOInstance bi = BOInstance
												.fromJSONString(aJson);

										DOBO bo = DOBO.getDOBOByName("do_bo");
										BOInstance exists = bo.getInstance(bi
												.getValue("objuid"));
										if (exists != null) {
											log.info("待导入的业务对象已经存在，请删除后再导入!"
													+ exists);
											this
													.setEchoValue("待导入的业务对象已经存在，请删除后再导入!");
											return NO_FORWARD;
										}
										bi.putValue("datasourceuid",
												datasourceuid);
										bi.putValue("bpuid", bpuid);
										doboInsert.invokeUpdate(bi);
									}

								}

							} else if (aNode.getNodeName().equals("property")) {
								insertANode(propertyInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("parameter")) {
								insertANode(parameterInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("rule")) {
								insertANode(doRuleInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("service")) {
								insertANode(serviceInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals(
									"parameter_service")) {
								insertANode(paraServiceInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals(
									"rule_service")) {
								insertANode(serviceRuleInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("pane")) {
								insertANode(paneModelInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("pane_links")) {
								insertANode(paneLinksInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("grid")) {
								insertANode(gridInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("form")) {
								insertANode(formInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals(
									"form_relation")) {
								insertANode(formLinksInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName()
									.equals("form_target")) {
								insertANode(formTargetInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("menu")) {
								insertANode(menuModelInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("tree")) {
								insertANode(treeModelInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("controller")) {
								insertANode(controllerInsert, aNode, tenancy,
										curTenancy);
							} else if (aNode.getNodeName().equals("action")) {
								insertANode(actionInsert, aNode, tenancy,
										curTenancy);
							}
						}
					}
				// //////处理多租户表的导入
				// if (tenancy != null && tenancyTableJson != null) {
				// if (DOGlobals.getInstance().getSessoinContext().getUser() !=
				// null) {
				// BOInstance biTenancy = (BOInstance) DOGlobals
				// .getInstance().getSessoinContext().getUser()
				// .getObjectValue("tenancy");
				// if (biTenancy != null) {
				// String curTenancy = biTenancy.getValue("name");
				// BOInstance tenancyTable = BOInstance
				// .fromJSONString(tenancyTableJson);
				// // 新的tennacyTable表
				// tenancyTable.putValue("id", null);
				// tenancyTable.putValue("table_name", tenancyTable
				// .getValue("table_name").replace(tenancy,
				// curTenancy));
				// tenancyTable.putValue("corr_view", tenancyTable
				// .getValue("table_name").replace(tenancy,
				// curTenancy));
				// tenancyTable.putValue("tenancy_uid", biTenancy
				// .getUid());
				// DOService findTenancyByTable = DOService
				// .getService("multi_tenancy_table_findbytablename");
				// List finded = findTenancyByTable
				// .invokeSelect(tenancyTable
				// .getValue("table_name"));
				// if (finded.size() == 0) {
				// DOService insertTenancyTable = DOService
				// .getService("multi_tenancy_table_insert");
				// BOInstance newTenancy = insertTenancyTable
				// .invokeUpdate(tenancyTable);
				//
				// DOService insertTenancyCol = DOService
				// .getService("multi_tenancy_column_insert");
				//
				// for (Iterator<String> it = coljsons.iterator(); it
				// .hasNext();) {
				// String aJson = it.next();
				// BOInstance colBI = BOInstance
				// .fromJSONString(aJson);
				// colBI.putValue("id", null);
				// colBI.putValue("tenancy_table_uid",
				// newTenancy.getUid());
				// insertTenancyCol.invokeUpdate(colBI);
				// }
				// }
				// }
				// }
				// }

			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setEchoValue("翻译完成!");

			t.end();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}

		this.setEchoValue("导入成功!");
		return DEFAULT_FORWARD;

	}

	private static void insertANode(DOService insertService, Node aNode,
			String tenancy, String curTenancy) throws JSONException,
			ExedoException {
		NodeList lis = aNode.getChildNodes();
		for (int lii = 0; lii < lis.getLength(); lii++) {
			Node aLi = lis.item(lii);
			if (aLi instanceof Element) {
				String aJson = aLi.getFirstChild().getNodeValue();
				aJson = aJson.replace(tenancy, curTenancy);
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
