package com.exedosoft.plat.gene.jquery;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.BusiPackage;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOParameterService;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOPaneLinks;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.DOInputText;
import com.exedosoft.plat.ui.jquery.form.DOTextArea;
import com.exedosoft.plat.ui.jquery.form.DOValueDate;
import com.exedosoft.plat.ui.jquery.form.DOValueSimple;
import com.exedosoft.plat.ui.jquery.form.TClose;
import com.exedosoft.plat.ui.jquery.form.TPaneSelected;
import com.exedosoft.plat.ui.jquery.form.TServiceSelected;
import com.exedosoft.plat.ui.jquery.form.TPane;
import com.exedosoft.plat.ui.jquery.form.TService;
import com.exedosoft.plat.ui.jquery.form.TServiceSelectedUf;
import com.exedosoft.plat.ui.jquery.form.TServiceUf;
import com.exedosoft.plat.ui.jquery.form.my97.DatePicker;
import com.exedosoft.plat.ui.jquery.grid.GridConditionAutoTr;
import com.exedosoft.plat.ui.jquery.grid.GridList;
import com.exedosoft.plat.ui.jquery.grid.GridSupportMore;
import com.exedosoft.plat.ui.jquery.pane.ContentPane;
import com.exedosoft.plat.ui.jquery.pane.ContentPaneScroll;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class GeneUIMain {

	private static Log log = LogFactory.getLog(GeneUIMain.class);

	private final String SQL_SELECT_SERVICE = "select service.* from DO_Service service,DO_BO bo where service.bouid = bo.objuid  bo.name = ?";
	
	private DOController gridList = DOController.getControllerByName(GridList.class.getName());

	private DOController gridCondition = DOController
			.getControllerByName(GridConditionAutoTr.class.getName());

	private DOController gridSupportMore = DOController.getControllerByName(GridSupportMore.class
			.getName());

	private DOController formInputText = DOController.getControllerByName(DOInputText.class
			.getName());

	private DOController formTextArea = DOController.getControllerByName(DOTextArea.class
			.getName());

	private DOController formValueSimple = DOController.getControllerByName(DOValueSimple.class
			.getName());

	private DOController formValueDate = DOController.getControllerByName(DOValueDate.class
			.getName());

	private DOController formDateMy97 = DOController
			.getControllerByName(DatePicker.class.getName());

	private DOController contentPane= DOController.getControllerByName(ContentPane.class.getName());

	private DOController formServiceUf = DOController.getControllerByName(TServiceUf.class
			.getName());

	private DOController paneOverFlow = DOController.getControllerByName(ContentPaneScroll.class
			.getName());

	private DOController formCloseButton = DOController.getControllerByName(TClose.class
			.getName());

//	private DOController formDelete = DOController.getControllerByName(TDelete.class.getName());

	private DOController formPane = DOController.getControllerByName(TPane.class.getName());

	private DOController formItemPane = DOController.getControllerByName(TPaneSelected.class.getName());
	
	private DOController formItemServiceUf = DOController.getControllerByName(TServiceSelectedUf.class.getName());
	

	private String geneATable = "";

	private DOBO category = null;

	
	String mainPaneName = "";
	String condtionPaneName = "";
	String resultPaneName = "";

	String condtionGridName = "";
	String resultGridName = "";
	
	
	public GeneUIMain(String aTable) {
		aTable = StringUtil.get_Name(aTable);
		this.geneATable = aTable;
		category = DOBO.getDOBOByName(aTable);
		
		mainPaneName = "PM_" + geneATable + "_Main";
		condtionPaneName = "PM_" + geneATable + "_Condition";
		resultPaneName = "PM_" + geneATable + "_Result";

		condtionGridName = "GM_" + geneATable + "_Condition";
		resultGridName = "GM_" + geneATable + "_Result";
	}

	public void geneConfig() {


		// /面板命名方式:PM_DO_LOG_Insert PM_DO_Parameter_Service_Browse

		// /表格命名方式 GM_DO_Application_List_List

		if (category == null) {
			return;
		}
		
		////需要用到的业务对象
		DOBO bo  = DOBO.getDOBOByName("DO_BO");
		bo.refreshContext(category.getObjUid());

		List properties = category.retrieveProperties();
		if (properties.size() == 0) {
			return;
		}

		// 总面板面呈
		DODataSource dds = DODataSource.parseGlobals();
		DAOUtil.INSTANCE().currentDataSource(dds);
		Transaction t = dds.getTransaction();

		try {


			if (DOPaneModel.getPaneModelByName(mainPaneName) != null) {
				System.err.println("面板已经存在------------");
				return;
			}
			t.begin();
			
			
			/**
			 * 生成总面板
			 */
			DOPaneModel pmTotal = new DOPaneModel();
			pmTotal.setName(mainPaneName);
			pmTotal.setL10n(mainPaneName);
			pmTotal.setCategory(category);
			pmTotal.setController(paneOverFlow);
			
			DOService aService = DOService.getService("do_ui_panemodel_copy");
			DAOUtil.INSTANCE().store(pmTotal,aService);
			
			DOPaneModel pmResult = geneResult(properties);
			DOPaneModel pmCondition = geneCondition(properties, pmResult);
			
			
			aService = DOService.getService("DO_UI_PaneLinks_copy");
			DOPaneLinks link1 = new DOPaneLinks();
			link1.setParentPane(pmTotal);
			link1.setChildPane(pmCondition);
			link1.setOrderNum(5);
			DAOUtil.INSTANCE().store(link1,aService);
			
			DOPaneLinks link2 = new DOPaneLinks();
			link2.setParentPane(pmTotal);
			link2.setChildPane(pmResult);
			link2.setOrderNum(10);
			DAOUtil.INSTANCE().store(link2,aService);
			
			
			////搞一下菜单
			
			DOPaneModel _opener_tab = DOPaneModel.getPaneModelByName("_opener_tab");
			
		

			String menuName = "";
			
			
			try {
				String bpUid = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("bpUid");
				BusiPackage dbp = BusiPackage.getPackageByID(bpUid);
				menuName =  dbp.getApplication().getName() + "_bp" ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			DOMenuModel parentMenu = DOMenuModel.getMenuModelByName(menuName);
			DOBO boMenu = DOBO.getDOBOByName("DO_UI_MenuModel");
			if(parentMenu!=null){
				boMenu.refreshContext(parentMenu.getObjUid());
			}
			DOMenuModel dmm = new DOMenuModel();
			dmm.setName(geneATable);
			dmm.setL10n(geneATable);
			dmm.setLinkPane(pmTotal);
			dmm.setTargetPane(_opener_tab);
			dmm.setParentMenu(parentMenu);
			dmm.setOrderNum(5);
			DAOUtil.INSTANCE().store(dmm);
			
			
			t.end();
			// conditionGrid.setService(sService)

		} catch (Exception e) {
			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private DOPaneModel geneCondition(List properties, DOPaneModel pmResult) throws ExedoException {
		/**
		 * 生成表格
		 */
		DOGridModel conditionGrid = new DOGridModel();
		conditionGrid.setL10n(condtionGridName);
		conditionGrid.setName(condtionGridName);
		conditionGrid.setCaption("请输入查询条件");
		conditionGrid.setCategory(category);
		conditionGrid.setController(gridCondition);
		conditionGrid = DAOUtil.INSTANCE().store(conditionGrid);

		/**
		 * 生成面板
		 */
		DOPaneModel pmCondition = new DOPaneModel();
		pmCondition.setTitle("请输入查询条件");
		pmCondition.setName(condtionPaneName);
		pmCondition.setL10n(condtionPaneName);
		pmCondition.setCategory(category);
		pmCondition.setController(contentPane);
		pmCondition
				.setLinkType(Integer.valueOf(DOPaneModel.LINKTYPE_GRIDMODEL));
		pmCondition.setLinkUID(conditionGrid.getObjUid());
		DAOUtil.INSTANCE().store(pmCondition);

		int i = 1;
		for (Iterator<DOBOProperty> it = properties.iterator(); it.hasNext(); i = i + 5) {
			DOBOProperty p = it.next();
			if (!p.isKeyCol()) {
				DOFormModel fm = new DOFormModel();
				DOFormModel old = DOFormModel.getFormModelByProperty(p.getObjUid());
				if(old!=null){
					fm.setL10n(old.getL10n());
				}else{
					fm.setL10n(p.getL10n());
				}
				fm.setRelationProperty(p);
				fm.setGridModel(conditionGrid);
				fm.setOrderNum(Integer.valueOf(i));
				fm.setIsNull(DOFormModel.NULL_YES);
				if (p.isDateOrTimeType()) {
					fm.setController(formDateMy97);
				} else {
					fm.setController(formInputText);
				}

				if (p.isInt() || p.isLong()) {
					fm.setExedojoType("Integer");
				} else if (p.isNumberType()) {
					fm.setExedojoType("RealNumber");
				}
				DAOUtil.INSTANCE().store(fm);
			}
		}

		DOFormModel fm = new DOFormModel();
		fm.setL10n("查询");
		fm.setController(formPane);
		fm.setGridModel(conditionGrid);
		fm.setOrderNum(Integer.valueOf(i));
		fm.setLinkPaneModel(pmResult);
		fm.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);
		DAOUtil.INSTANCE().store(fm);
		
		return pmCondition;
	}

	private DOPaneModel geneResult(List properties) throws ExedoException {

		/**
		 * 相关服务生成
		 */
		DOService rService = new DOService();
		rService.setName(geneATable + "_auto_condition")                                                                                                                                         ;
		rService.setL10n(geneATable + "_auto_condition");
		rService.setBo(category);
		rService.setMainSql("select * from " + this.geneATable
				+ " /*condition*/ ");
		rService.setType(DOService.TYPE_SELECT_AUTO_CONDITION);
		DAOUtil.INSTANCE().store(rService);

		List paras = this.category.retrieveParameties();
		int i = 1;
		for (Iterator<DOParameter> it = paras.iterator(); it.hasNext(); i = i + 5) {
			DOParameter dop = it.next();
			if (dop.getProperty().isKeyCol()) {
				continue;
			}
			DOParameterService dps = new DOParameterService();
			dps.setDop(dop);
			dps.setDos(rService);
			dps.setOrderNum(Integer.valueOf(i));
			dps.setPatterm("#col# like ?;%#value#%");
			
			DOService aService = DOService.getService("DO_Parameter_Service_Copy_Batch");
			DAOUtil.INSTANCE().store(dps,aService);
		}
		/**
		 * END 相关服务生成
		 */

		/**
		 * 生成表格
		 */
		DOGridModel gmResult = new DOGridModel();
		gmResult.setL10n(resultGridName);
		gmResult.setName(resultGridName);
		gmResult.setCaption("查询结果");
		gmResult.setCategory(category);
		gmResult.setController(gridList);
		gmResult.setService(rService);
		gmResult.setRowSize(10);
		gmResult = DAOUtil.INSTANCE().store(gmResult);

		/**
		 * 生成面板
		 */
		DOPaneModel pmResult = new DOPaneModel();
		pmResult.setTitle("查询结果");
		pmResult.setName(resultPaneName);
		pmResult.setL10n(resultPaneName);
		pmResult.setCategory(category);
		pmResult.setController(contentPane);
		pmResult.setLinkType(Integer.valueOf(DOPaneModel.LINKTYPE_GRIDMODEL));
		pmResult.setLinkUID(gmResult.getObjUid());
		pmResult = DAOUtil.INSTANCE().store(pmResult);

		i = 1;
		for (Iterator<DOBOProperty> it = properties.iterator(); it.hasNext(); i = i + 5) {
			DOBOProperty p = it.next();
			if (!p.isKeyCol()) {
				DOFormModel fm = new DOFormModel();
				DOFormModel old = DOFormModel.getFormModelByProperty(p.getObjUid());
				if(old!=null){
					fm.setL10n(old.getL10n());
				}else{
					fm.setL10n(p.getL10n());
				}
				fm.setRelationProperty(p);
				fm.setGridModel(gmResult);
				fm.setIsNull(DOFormModel.NULL_YES);
				fm.setOrderNum(Integer.valueOf(i));

				if (p.isDateOrTimeType()) {
					fm.setController(formValueDate);
				} else {
					fm.setController(formValueSimple);
				}
				DAOUtil.INSTANCE().store(fm);
			}
		}

		
		
		DOPaneModel _opener = DOPaneModel.getPaneModelByName("_opener");

	
		DOFormModel fm = new DOFormModel();
		fm.setL10n("查看");

		DOPaneModel pmBrowse = DOPaneModel.getPaneModelByName("PM_"
				+ geneATable + "_browse");
		fm.setLinkPaneModel(pmBrowse);
		fm.setTargetPaneModel(_opener);
		fm.setIsOutGridAction(DOFormModel.OUTGRID_TOP);

		fm.setController(formItemPane);
		fm.setGridModel(gmResult);
		fm.setOrderNum(Integer.valueOf(i));
		i = i + 5;
		DAOUtil.INSTANCE().store(fm);

		fm = new DOFormModel();
		fm.setController(formItemPane);
		fm.setL10n("修改");

		DOPaneModel pmUpdate = DOPaneModel.getPaneModelByName("PM_"
				+ geneATable + "_update");
		
		DOGridModel gm = DOGridModel.getGridModelByName("GM_" + geneATable + "_update");
		List<DOFormModel> fms = gm.getBottomOutGridFormLinks();
		if(fms.size() > 0){
			DOFormModel aFm = fms.get(0);
			aFm.setLinkPaneModel(pmResult);
			aFm.setTargetPaneModel(pmResult);
			DAOUtil.INSTANCE().store(aFm);
		}
		
		fm.setLinkPaneModel(pmUpdate);
		fm.setTargetPaneModel(_opener);
		fm.setIsOutGridAction(DOFormModel.OUTGRID_TOP);

		fm.setGridModel(gmResult);
		fm.setOrderNum(Integer.valueOf(i));
		i = i + 5;
		DAOUtil.INSTANCE().store(fm);

		fm = new DOFormModel();
		fm.setController(formItemServiceUf);
        fm.setEchoJs("confirm(\"你确定要删除吗\")");		
		DOService aService = DOService.getService(this.geneATable + "_delete");
		fm.setLinkService(aService);
		fm.setL10n("删除");
		fm.setGridModel(gmResult);
		fm.setLinkPaneModel(pmResult);
		fm.setTargetPaneModel(pmResult);
		fm.setIsOutGridAction(DOFormModel.OUTGRID_TOP);
		fm.setOrderNum(Integer.valueOf(i));
		i = i + 5;
		DAOUtil.INSTANCE().store(fm);

		fm = new DOFormModel();
		fm.setController(formPane);
		fm.setL10n("新增");

		DOPaneModel pmInsert = DOPaneModel.getPaneModelByName("PM_"
				+ geneATable + "_insert");
		
		gm = DOGridModel.getGridModelByName("GM_" + geneATable + "_insert");
		fms = gm.getBottomOutGridFormLinks();
		if(fms.size() > 0){
			DOFormModel aFm = fms.get(0);
			aFm.setLinkPaneModel(pmResult);
			aFm.setTargetPaneModel(pmResult);
			DAOUtil.INSTANCE().store(aFm);
		}
		fm.setLinkPaneModel(pmInsert);
		fm.setTargetPaneModel(_opener);
		fm.setIsOutGridAction(DOFormModel.OUTGRID_TOP);

		fm.setGridModel(gmResult);
		fm.setOrderNum(Integer.valueOf(i));
		i = i + 5;
		DAOUtil.INSTANCE().store(fm);
		
		return pmResult;
	}
	
	public void deletePanes(){
		
		DOPaneModel pm = DOPaneModel.getPaneModelByName(mainPaneName); 
		if (pm == null) {
			System.err.println("面板已经存在------------");
			return;
		}
		try {
			
			List children = pm.retrieveChildren();
			for(Iterator<DOPaneModel> it = children.iterator(); it.hasNext();){
				DOPaneModel pmChild = it.next();
				DOGridModel gm = pmChild.getDOGridModel();

				
				for(Iterator<DOFormModel> itFm = gm.getAllGridFormLinks().iterator(); itFm.hasNext();){
					DOFormModel fm = itFm.next();
					DAOUtil.INSTANCE().delete("delete form do_ui_formmodel where objuid = ?", fm.getObjUid());
				}
				DAOUtil.INSTANCE().delete("delete from do_ui_gridmodel where objuid = ?", gm.getObjUid());
			//删除子面板 					
				removePane(pmChild);
			}
			
			//删除总面板 
			removePane(pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void removePane(DOPaneModel pm) throws ExedoException {
		///////////////////删除面板 			
		DAOUtil.INSTANCE().delete("delete * from do_ui_panemodel where objuid = ?", pm.getObjUid());
	}

	// private void geneForms(HbmDAO dao) {
	//
	// try {
	// List list = dao.list(selectProperty, this.geneATable);
	// for (Iterator it = list.iterator(); it.hasNext();) {
	// DOBOProperty prop = (DOBOProperty) it.next();
	// DOFormModel formModel = new DOFormModel();
	// formModel.setRelationProperty(prop);
	// if (prop.isNumberType()) {
	// formModel.setExedojoType("RealNumber");
	// }
	//
	// formModel.setL10n(prop.getColName());
	// dao.store(formModel);
	// log.info("正在从" + prop.getColName() + "生成界面组件.....");
	// }
	// } catch (DAOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	/**
	 * @param dao
	 * @param aService
	 * @throws ExedoException
	 * @throws DAOException
	 */
	private void genePaneAndGrid(DOService aService, DOController controller,
			String aName) throws ExedoException {

		DOGridModel gridM = new DOGridModel();
		gridM.setCategory(aService.getBo());
		gridM.setCaption(aService.getL10n());
		gridM.setName("grid_" + aService.getName() + aName);
		gridM.setL10n("grid_" + aService.getName() + aName);

		gridM.setService(aService);
		gridM.setController(controller);
		gridM.setColNum(Integer.valueOf(2));
		gridM.setCategory(aService.getBo());
		DAOUtil.INSTANCE().store(gridM);

		int i = 1;

		// ///服务的属性 原来是aService.retrieveProperties
		for (Iterator itProp = aService.getBo().retrieveProperties().iterator(); itProp
				.hasNext();) {

			DOBOProperty prop = (DOBOProperty) itProp.next();
			if (prop.isKeyCol()) {
				continue;
			}
			DOFormModel formM = new DOFormModel();
			formM.setRelationProperty(prop);

			/**
			 * * 客户端验证配置，分为３部分，以;隔开 １，类型：Integer RealNumber EMail Text Others 2,
			 * 长度 ３, 其他Script 约束
			 * 
			 */

			if (prop.isNumberType()) {

				String exedoType = "RealNumber";
				formM.setExedojoType(exedoType);
			} else if (!prop.isDateOrTimeType()) {
				formM.setExedojoType(";"
						+ (int) (prop.getDbSize().intValue() / 2));
			}

			formM.setL10n(prop.getColName());
			formM.setGridModel(gridM);

			formM.setOrderNum(Integer.valueOf(i * 5));
			if (prop.isDateOrTimeType()) {
				if ("".equals(aName)) {
					formM.setController(formValueDate);
				} else {
					formM.setController(formDateMy97);
				}
			} else {
				if ("".equals(aName)) {
					formM.setController(formValueSimple);
				} else {

					if (prop.getDbSize() != null
							&& prop.getDbSize().intValue() > 500) {
						formM.setController(formTextArea);
						formM.setIsNewLine(DOFormModel.NEWLINE_YES);
					} else {
						formM.setController(formInputText);
					}
				}

			}
			DAOUtil.INSTANCE().store(formM);
			i++;
		}

		if (aService.getName().endsWith(".browse") && "".equals(aName)) {
			geneCloseButtonForm(aService, gridM);

		} else if (aName != null && !aName.equals("")) {
			geneSaveButtonForm(aService, aName, gridM);
		}

		// 对每个Grid赋給一个Pane

		DOPaneModel pane = new DOPaneModel();
		pane.setCategory(aService.getBo());
		pane.setName("pane_" + aService.getName() + aName);

		// ///下一步考虑 是不是名称采用和Servie 一致
		pane.setTitle(aService.getName() + "paneModel" + aName);
		pane.setL10n(aService.getName() + "paneModel" + aName);
		pane.setLinkType(Integer.valueOf(DOPaneModel.LINKTYPE_GRIDMODEL));
		pane.setLinkUID(gridM.getObjUid());
		if (aService.getName().endsWith(".browse")) {
			pane.setController(paneOverFlow);
		} else {
			pane.setController(contentPane);
		}
		DAOUtil.INSTANCE().store(pane);
	}

	/**
	 * @param dao
	 * @param aService
	 * @param aName
	 * @param gridM
	 * @throws ExedoException
	 * @throws DAOException
	 *             SELECT fm.*,ug.controllerUid,ug.orderNum FROM DO_UI_FormModel
	 *             fm ,DO_UI_GridLinks ug where fm.objuid = ug.formModelUid and
	 *             fm.objuid = ?
	 */
	private void geneSaveButtonForm(DOService aService, String aName,
			DOGridModel gridM) throws ExedoException {



		DOFormModel formM = new DOFormModel();
		formM.setL10n("保存");
		DOService linkService = DOService.getService(aService.getBo().getName()
				+ aName);
		formM.setLinkService(linkService);
		formM.setIsNewLine(1);
		formM.setNameColspan(Integer.valueOf(0));
		formM.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);
		// formM.setValueColspan(Integer.valueOf(2));
		formM.setAlign("center");

		formM.setGridModel(gridM);
		formM.setOrderNum(Integer.valueOf(1000));
		formM.setController(formServiceUf);

		DAOUtil.INSTANCE().store(formM);

	}

	/**
	 * @param dao
	 * @param aService
	 * @param aName
	 * @param gridM
	 * @throws ExedoException
	 * @throws DAOException
	 *             SELECT fm.*,ug.controllerUid,ug.orderNum FROM DO_UI_FormModel
	 *             fm ,DO_UI_GridLinks ug where fm.objuid = ug.formModelUid and
	 *             fm.objuid = ?
	 */
	private void geneCloseButtonForm(DOService aService, DOGridModel gridM)
			throws ExedoException {

		// ///装电话小灵通, 83747268
		// //装电话公司电话, 61758100

		DOFormModel formM = new DOFormModel();
		formM.setL10n("关闭");
		// DOService linkService =
		// DOService.getService(aService.getBo().getName()
		// + ".delete");
		// formM.setLinkService(linkService);
		formM.setIsNewLine(1);
		formM.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);
		formM.setNameColspan(Integer.valueOf(0));
		// formM.setValueColspan(Integer.valueOf(2));
		formM.setAlign("center");

		formM.setGridModel(gridM);
		formM.setOrderNum(Integer.valueOf(1000));
		formM.setController(formCloseButton);

		DAOUtil.INSTANCE().store(formM);

	}

	public static void main(String[] args) {
		
		GeneUIMain gm = new GeneUIMain("DO_Service_Redirect");
		gm.geneConfig();
		
//		CacheFactory.getCacheData().cacheAllConfigData();
//		
//		DOController paneOverFlow = DOController.getControllerByName(ContentPaneScroll.class
//				.getName());
//		
//	    DOBO category = DOBO.getDOBOByName("DO_DataSource");
//
//		DOPaneModel pmTotal = new DOPaneModel();
//		pmTotal.setName("aaa");
//		pmTotal.setL10n("aaa");
//		pmTotal.setCategory(category);
//		pmTotal.setController(paneOverFlow);
//		try {
//			DAOUtil.INSTANCE().store(pmTotal);
//		} catch (ExedoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
