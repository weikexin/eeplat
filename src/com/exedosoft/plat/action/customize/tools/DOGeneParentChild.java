package com.exedosoft.plat.action.customize.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOParameterService;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.DOServiceRedirect;
import com.exedosoft.plat.bo.DOActionConfig;


import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOPaneLinks;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.jquery.form.TService;
import com.exedosoft.plat.ui.jquery.pane.LayOutSplitPane;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.Transaction;


public class DOGeneParentChild extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8336023748771461161L;

	private static Log log = LogFactory.getLog(DOGeneParentChild.class);

	public String excute() {
		DOBO bo = DOBO.getDOBOByName("do.bo");

		String boUid = bo.getCorrInstance().getUid();
		log.info("CurBOUID::" + boUid);

		DOBO curBO = DOBO.getDOBOByID(boUid);
		//
		String parentBOUid = this.actionForm
				.getValue("qingxuanzezhuyewuduixiang");
		log.info("ParentBOUid:" + parentBOUid);

		DOBO parentBO = DOBO.getDOBOByID(parentBOUid);

		String aField = this.actionForm.getValue("qingxuanzelianjiedeshuxing");
		log.info("Field::" + aField);

		
		DOBOProperty prop = DOBOProperty.getDOBOPropertyByID(aField);
		DOParameter propPara = DOParameter.getParameterByProperty(prop,DOParameter.TYPE_FORM);
		
		DOBOProperty parentKey = DOBOProperty.getDOBOPropertyByName(parentBO.getName(),parentBO
				.getKeyCol());
		DOParameter keyPara = DOParameter.getParameterByProperty(
				parentKey, DOParameter.TYPE_CURRENT);


		if (boUid == null) {
			return "";
		}
		// ////////////////清楚缓存
//		HbmDAO dao = new HbmDAO();
//		dao.setAutoClose(false);
//		dao.setIsTransaction(true);
		
		DODataSource dds = DODataSource.parseGlobals();
		
		Transaction t = dds.getTransaction();
		
		t.begin();
		
		try {

			StringBuffer aServiceSql = new StringBuffer("select * from ");
			aServiceSql.append(bo.getCorrInstance().getValue("sqlStr")).append(
					" where ").append(prop.getColName()).append(" = ?");
			log.info(" The Service Sql is ::" + aServiceSql);
			DOService aService = new DOService();
			aService.setBo(curBO);
			aService.setMainSql(aServiceSql.toString());
			aService.setName(parentBO.getName() + "have" + curBO.getName());
			aService.setL10n(parentBO.getL10n() + "拥有的" + curBO.getL10n());
			DAOUtil.INSTANCE().store(aService);

			DOParameterService dps = new DOParameterService();
			dps.setDop(keyPara);
			dps.setDos(aService);
			dps.setOrderNum(new Integer(5));
			DAOUtil.INSTANCE().store(dps);

			DOService sInsert = DOService.getService(curBO.getName()
					+ ".insert");
			log.info("Find the insert Service::" + sInsert.getName());
			DOParameterService paraInsert = DOParameterService
					.getDOParaService(propPara.getObjUid(), sInsert.getObjUid());
			if (paraInsert != null) {
				paraInsert.setDop(keyPara);
				DAOUtil.INSTANCE().store(paraInsert);
			}

			DOService sUpdate = DOService.getService(curBO.getName()
					+ ".update");
			log.info("Find the update Service::" + sUpdate.getName());
			DOParameterService paraUpdate = DOParameterService
					.getDOParaService(propPara.getObjUid(), sUpdate.getObjUid());
			if (paraUpdate != null) {
				paraUpdate.setDop(keyPara);
				DAOUtil.INSTANCE().store(paraUpdate);
			}
			
		
			//////////////////////////////////////////end Business
			
			
			
			
			DOPaneModel pmParentInsert = DOPaneModel.getPaneModelByName("pane_"+ parentBO.getName()+".list.insert"); 
			DOPaneModel pmChildList = DOPaneModel.getPaneModelByName("pane_"+ curBO.getName()+".list");
			
			pmChildList.setTargetPane(pmChildList);
			DAOUtil.INSTANCE().store(pmChildList);
			
			DOController ccSplitePane = DOController.getControllerByName(LayOutSplitPane.class.getName());
			DOPaneModel pm = new DOPaneModel();
			pm.setName(curBO.getName()+"And"+parentBO.getName()+"LinkPane");
			pm.setL10n(curBO.getName()+"And"+parentBO.getName()+"LinkPane");
			pm.setController(ccSplitePane);
			pm.setLinkType(Integer.valueOf(DOPaneModel.LAYOUT_VERTICAL));
			pm.setCategory(pmParentInsert.getCategory());
			
			DAOUtil.INSTANCE().store(pm);
			//pane_test.dept.list.insert
			DOGridModel childListGrid  = DOGridModel.getGridModelByName("grid_" + curBO.getName()+".list");
			log.info("get the chidlListGrid" + childListGrid.getName());
			childListGrid.setService(aService);
			childListGrid.setIsCheckBox(Integer.valueOf(1));
			DAOUtil.INSTANCE().store(childListGrid);
			
			//pane_test.dept.list.insert

			DOServiceRedirect sr = DOServiceRedirect.getServiceRedirect(sInsert.getObjUid());
			sr.setPaneModel(pmChildList);
			DAOUtil.INSTANCE().store(sr);

			
			DOPaneLinks dpl1 = new DOPaneLinks();
			dpl1.setParentPane(pm);
			dpl1.setChildPane(pmParentInsert);
			dpl1.setOrderNum(Integer.valueOf(5));
			DAOUtil.INSTANCE().store(dpl1);
			
			DOPaneLinks dpl2 = new DOPaneLinks();
			dpl2.setParentPane(pm);
			dpl2.setChildPane(pmChildList);
			dpl2.setOrderNum(Integer.valueOf(10));
			DAOUtil.INSTANCE().store(dpl2);
			
			
			DOService parentInsert =  DOService.getService(parentBO.getName()
					+ ".insert");
			
			DOService parentUpdate =  DOService.getService(parentBO.getName()
					+ ".update");

			DOPaneModel pmParentUpdate = DOPaneModel.getPaneModelByName("pane_"+ parentBO.getName()+".browse.update");
			
			pmParentUpdate.setTargetPane(pmParentInsert);
			DAOUtil.INSTANCE().store(pmParentUpdate);
			
			DOServiceRedirect srParent = DOServiceRedirect.getServiceRedirect(parentInsert.getObjUid());
			srParent.setPaneModel(pmParentUpdate);
			DAOUtil.INSTANCE().store(srParent);
			
			
			DOServiceRedirect srParentU = DOServiceRedirect.getServiceRedirect(parentUpdate.getObjUid());
			srParentU.setPaneModel(pmParentUpdate);
			DAOUtil.INSTANCE().store(srParentU);
			
			
			DOGridModel parentUpdateGrid  = DOGridModel.getGridModelByName("grid_" + parentBO.getName()+".browse.update");
			
			
			DOController formSaveButton = DOController.getControllerByName(TService.class.getName());
			DOPaneModel pmChildInsert = DOPaneModel.getPaneModelByName("pane_"+ curBO.getName()+".list.insert");

			DOFormModel fm = new DOFormModel();
			fm.setController(formSaveButton);
			fm.setL10n("Insert Child");
			fm.setLinkPaneModel(pmChildInsert);
			fm.setIsOutGridAction(Integer.valueOf(DOFormModel.OUTGRID_BOTTOM));
			
			fm.setController(formSaveButton);
			fm.setGridModel(parentUpdateGrid);
			fm.setOrderNum(new Integer(2000));
			DAOUtil.INSTANCE().store(fm);
			
			
			
			DOActionConfig ac = DOActionConfig.getActionConfig("deleteallaction");
			DOService sDelete = DOService.getService(curBO.getName()
					+ ".delete");
			sDelete.setActionConfig(ac);
			DAOUtil.INSTANCE().store(sDelete);
			
			DOServiceRedirect srChildDelete = DOServiceRedirect.getServiceRedirect(sDelete.getObjUid());
			srChildDelete.setPaneModel(pmChildList);
			DAOUtil.INSTANCE().store(srChildDelete);
				
			DOController deletecontroller = DOController.getControllerByName("deletecontroller");

			DOFormModel deleteAll = new DOFormModel();
			deleteAll.setL10n("删除选中");
			deleteAll.setLinkService(sDelete);
			deleteAll.setIsOutGridAction(Integer.valueOf(DOFormModel.OUTGRID_BOTTOM));
			deleteAll.setController(deletecontroller);
			deleteAll.setGridModel(childListGrid);
			deleteAll.setOrderNum(new Integer(2000));
			DAOUtil.INSTANCE().store(deleteAll);
			

		} catch (Exception ex1) {
			ex1.printStackTrace();
			this.setEchoValue(ex1.getMessage());
			t.rollback();
			return NO_FORWARD;
		} finally {

			t.end();
		}
		return DEFAULT_FORWARD;
	}

	
}
