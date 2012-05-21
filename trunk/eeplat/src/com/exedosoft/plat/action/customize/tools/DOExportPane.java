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

import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.StringUtil;

public class DOExportPane extends DOExportSimple {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;

	@Override
	public String excute() throws ExedoException {

		StringBuilder sb = new StringBuilder("<export>");

		DOBO bo = DOBO.getDOBOByName("DO_UI_PaneModel");
		DOBO boGrid = DOBO.getDOBOByName("DO_UI_GridModel");
		BOInstance biPane = bo.getCorrInstance();
		if (biPane != null) {
			DOPaneModel pm = DOPaneModel.getPaneModelByID(biPane.getUid());
			// 面板
			sb.append("<pane><li>").append(StringUtil.filter(biPane.toJSONString())).append("</li></pane>");

			DOService servPaneLinks = DOService
					.getService("DO_UI_PaneLinks_findbyparentPaneUid");
			sb.append("\n<pane_links>");
			List list = servPaneLinks.invokeSelect(biPane.getUid());
			appendLi(sb, list);
			sb.append("</pane_links>");

			// /面板下面的表格
			DOGridModel gm = pm.getGridModel();
			if (gm != null) {
				BOInstance biGrid = boGrid.getInstance(gm.getObjUid());
				sb.append("<grid>").append(biGrid.toJSONString()).append(
						"</grid>");

				// /表格下面的表格元素
				DOService servForms = DOService
						.getService("DO_UI_FormModel_findbyGridModelUid");
				// /表格元素连接的表格元素
				DOService servFormRelations = DOService
						.getService("DO_UI_FormLinks_findbyformuid");

				DOService servFormTargets = DOService
						.getService("DO_UI_FormTargets_findbyformUid");

				sb.append("\n<form>");
				StringBuilder sbRelations = new StringBuilder(
						"\n<form_relation>");
				StringBuilder sbTargets = new StringBuilder("\n<form_target>");

				List forms = servForms.invokeSelect(biGrid.getUid());
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
				sbRelations.append("</form_relation>");
				sbTargets.append("</form_target>");
				sb.append("</form>");
				sb.append(sbRelations);
				sb.append(sbTargets);
			}
		}
		sb.append("</export>");
		this.setEchoValue(sb.toString());

		return DEFAULT_FORWARD;

	}

	

}
