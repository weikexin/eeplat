package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOActionConfig;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.gene.jquery.GeneUICompByTableJquery;
import com.exedosoft.plat.gene.jquery.PropertyManager;
import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.TServiceUf;
import com.exedosoft.plat.ui.jquery.grid.GridSupportMore;

import com.exedosoft.wf.pt.ProcessTemplate;

public class DOGeneConfigBuildRelationWf extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4555077593344493040L;

	private static DOController gridSupportMore = DOController
			.getControllerByName(GridSupportMore.class.getName());

	private static DOController formServiceUf = DOController
			.getControllerByName(TServiceUf.class.getName());

	@Override
	public String excute() throws ExedoException {

		DOBO bo = DOBO.getDOBOByName("do_bo");
		BOInstance instance = bo.getCorrInstance();
		if (instance == null) {
			this.setEchoValue("没有数据!");
			return NO_FORWARD;
		}

		String ptUid = this.actionForm.getValue("pt_uid");

		System.out.println("模板ID::" + ptUid);
		if (ptUid == null && ptUid == null) {
			this.setEchoValue("流程模板没有定义!");
			return NO_FORWARD;
		}

		DOService insertService = DOService.getService(instance.getName()
				+ "_insert");
		if (insertService != null) {

			// //////////////////做insert 服务的copy
			DOActionConfig ac = DOActionConfig
					.getActionConfig(com.exedosoft.plat.action.wf.DOStartWf.class
							.getCanonicalName());

			DOBO thisBO = DOBO.getDOBOByID(instance.getUid());

			DOService selectService = DOService.getService("DO_Service_Browse");

			BOInstance biService = selectService.getInstance(insertService
					.getObjUid());

			BOInstance biNewService = CopyServiceDeep.copyService(biService,
					insertService);

			DOService newService = DOService.getServiceByID(biNewService
					.getUid());

			newService.setName(instance.getName() + "_subflow");
			newService.setL10n(thisBO.getL10n() + "提交流程");
			newService.setActionConfig(ac);

			DAOUtil.INSTANCE().store(newService);
			// //////////////////做insert 服务的copy

			// //创建面板
			DOService aBrowseService = DOService.getService(instance.getName()
					+ "_browse");

			DOGridModel gridM = GeneUICompByTableJquery.genePaneAndGrid(
					aBrowseService, gridSupportMore, thisBO.getName()
							+ "_subflowAndinsert", "提交工作流");
			DOFormModel formM = new DOFormModel();
			formM.setL10n("提交流程");

			formM.setLinkService(newService);
			formM.setIsNewLine(1);
			formM.setNameColspan(Integer.valueOf(0));
			formM.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);

			formM.setAlign("center");

			DOPaneModel pm = DOPaneModel.getPaneModelByName("PM_"
					+ newService.getBo().getName() + "_subflowAndInsert");
			formM.setGridModel(gridM);
			formM.setOrderNum(Integer.valueOf(1000));
			formM.setController(formServiceUf);
			formM.setLinkPaneModel(pm);
			formM.setTargetPaneModel(pm);
			DAOUtil.INSTANCE().store(formM);
			
			
			DOService ptUpdateSimple = DOService.getService("do_pt_processtemplate_update_simple");
			ProcessTemplate  pt  = ProcessTemplate.getPTByID(ptUid);
			if(pt!=null){
				pt.setDoBO(thisBO);
				pt.setPane(pm);
				DAOUtil.INSTANCE().store(pt,ptUpdateSimple);
			}

		}

		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {
		
		CacheFactory.getCacheData().fromSerialObject();

		DOService ptUpdateSimple = DOService.getService("do_pt_processtemplate_update_simple");
		
		System.out.println(ptUpdateSimple);

	}

}
