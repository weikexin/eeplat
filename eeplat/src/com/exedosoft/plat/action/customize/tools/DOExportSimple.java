package com.exedosoft.plat.action.customize.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.plat.util.StringUtil;

public class DOExportSimple extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;
	
	@Override
	public String excute() throws ExedoException {

		if (this.service == null || this.service.getTempSql() == null) {
			this.setEchoValue(I18n.instance().get("未配置SQL 语句"));
			return NO_FORWARD;
		}

		StringBuilder sb = new StringBuilder("<export>");
		DOBO bo = DOBO.getDOBOByName("do_bo");
		DOBO boGrid = DOBO.getDOBOByName("DO_UI_GridModel");
		BOInstance selectBI = bo.getCorrInstance();

		sb.append("<bo>").append(selectBI.toJSONString()).append("</bo>\n");

		Transaction t = this.service.currentTransaction();
		List<String> allIDs = new ArrayList<String>();
		try {
			t.begin();

			// /属性
			DOService servProperties = DOService
					.getService("DO_BO_Property_findbybouid");
			appendJSONS(sb, "property", servProperties);
			// /参数
			DOService servParameters = DOService
					.getService("DO_Parameter_findbybouid");
			appendJSONS(sb, "parameter", servParameters);
			// 规则
			DOService servRules = DOService.getService("DO_Rule_findbybouid");
			appendJSONS(sb, "rule", servRules);

			// 业务对象下面的服务
			DOService servServices = DOService
					.getService("DO_Service_Browse_findbybouid");
			List services = appendJSONS(sb, "service", servServices);

			// 服务下面的参数
			DOService servParaService = DOService
					.getService("DO_Parameter_Service_findbyserviceUid");
			sb.append("\n<parameter_service>");
			for (Iterator it = services.iterator(); it.hasNext();) {
				BOInstance bi = (BOInstance) it.next();
				List paraServices = servParaService.invokeSelect(bi.getUid());
				appendLi(sb, paraServices);
			}
			sb.append("</parameter_service>");

			// 服务下面的规则
			DOService servRuleService = DOService
					.getService("DO_Service_Rule_findbyserviceuid");
			sb.append("\n<rule_service>");
			for (Iterator it = services.iterator(); it.hasNext();) {
				BOInstance bi = (BOInstance) it.next();
				List ruleServices = servRuleService.invokeSelect(bi.getUid());
				appendLi(sb, ruleServices);
			}
			sb.append("</rule_service>");

			// 业务对象下面的面板
			DOService servPanes = DOService
					.getService("DO_UI_PaneModel_selectbyboduid");
			List panes = appendJSONS(sb, "pane", servPanes);

			DOService servPaneLinks = DOService
					.getService("DO_UI_PaneLinks_findbyparentPaneUid");
			sb.append("\n<pane_links>");
			List<BOInstance> listGridOfPane = new ArrayList();
			for (Iterator it = panes.iterator(); it.hasNext();) {
				BOInstance aPane = (BOInstance) it.next();
				if (aPane!=null && aPane.getValue("linkType")!=null && aPane.getValue("linkType").equals(
						DOPaneModel.LINKTYPE_GRIDMODEL)) {
					BOInstance aGrid = boGrid.getInstance(aPane
							.getValue("linkUID"));
					if (aGrid != null) {
						listGridOfPane.add(aGrid);
					}
				}
				List list = servPaneLinks.invokeSelect(aPane.getUid());
				appendLi(sb, list);
			}
			sb.append("</pane_links>");

			// /业务对象下面的表格 应该用面板下面表格比较好
			DOService servGrids = DOService
					.getService("DO_UI_GridModel_findbycategoryUid");

			List<BOInstance> grids = servGrids.invokeSelect();
			listGridOfPane.removeAll(grids);
			grids.addAll(listGridOfPane);

			sb.append("\n<grid>");
			appendLi(sb, grids);
			sb.append("</grid>");

			// /表格下面的表格元素
			DOService servForms = DOService
					.getService("DO_UI_FormModel_findbyGridModelUid");
			// /表格元素连接的表格元素
			DOService servFormRelations = DOService
					.getService("DO_UI_FormLinks_findbyformuid");

			DOService servFormTargets = DOService
					.getService("DO_UI_FormTargets_findbyformUid");

			sb.append("\n<form>");
			StringBuilder sbRelations = new StringBuilder("\n<form_relation>");
			StringBuilder sbTargets = new StringBuilder("\n<form_target>");

			for (Iterator it = grids.iterator(); it.hasNext();) {
				BOInstance bi = (BOInstance) it.next();
				List forms = servForms.invokeSelect(bi.getUid());
				appendLi(sb, forms);
				for (Iterator itForm = forms.iterator(); itForm.hasNext();) {
					BOInstance biForm = (BOInstance) itForm.next();
					List formRelations = servFormRelations.invokeSelect(biForm
							.getUid());
					appendLi(sbRelations, formRelations);
					List formTargets = servFormTargets.invokeSelect(biForm
							.getUid());
					appendLi(sbTargets, formTargets);
				}
			}
			sbRelations.append("</form_relation>");
			sbTargets.append("</form_target>");
			sb.append("</form>");
			sb.append(sbRelations);
			sb.append(sbTargets);

			// 菜单
			sb.append("\n<menu>");
			DOService servMenus = DOService
					.getService("DO_UI_MenuModel_findbycategoryUid_top");
			DOService servChildMenu = DOService
					.getService("DO_UI_MenuModel_selflink");

			List allMenus = new ArrayList();
			List topMenus = servMenus.invokeSelect();
			allMenus.addAll(topMenus);
			for (Iterator it = topMenus.iterator(); it.hasNext();) {
				BOInstance itMenu = (BOInstance) it.next();
				getChildBIs(allMenus, itMenu, servChildMenu);
			}
			appendLi(sb, allMenus);
			sb.append("</menu>");

			// 树
			sb.append("\n<tree>");
			DOService servTrees = DOService
					.getService("DO_UI_TreeModel_findbycategoryUid");
			DOService servChildTree = DOService
					.getService("DO_UI_TreeModel_findbyparentuid");

			List allTrees = new ArrayList();
			List topTrees = servTrees.invokeSelect();
			allTrees.addAll(topTrees);

			for (Iterator it = topTrees.iterator(); it.hasNext();) {
				BOInstance itTree = (BOInstance) it.next();
				getChildBIs(allTrees, itTree, servChildTree);
			}
			appendLi(sb, allTrees);
			sb.append("</tree>");

			t.end();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}

		sb.append("</export>");
		this.setEchoValue(sb.toString());
		return DEFAULT_FORWARD;

	}

	protected void getChildBIs(List menus, BOInstance parent, DOService servChild) {

		if (parent == null) {
			return;
		}
		for (Iterator it = servChild.invokeSelect(parent.getUid()).iterator(); it
				.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			menus.add(bi);
			getChildBIs(menus, bi, servChild);
		}
	}

	protected List appendJSONS(StringBuilder sb, String label, DOService service) {

		sb.append("<").append(label).append(">");
		List list = service.invokeSelect();
		appendLi(sb, list);
		sb.append("</").append(label).append(">\n");
		return list;
	}

	protected  void appendLi(StringBuilder sb, List list) {
		
		/// toJSONSTring 需要进行转义
		for (Iterator it = list.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			sb.append("<li>").append(StringUtil.filter(bi.toJSONString())).append("</li>\n");
		}
	}

}
