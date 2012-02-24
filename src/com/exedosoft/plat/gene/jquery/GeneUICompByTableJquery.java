package com.exedosoft.plat.gene.jquery;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.DOInputHidden;
import com.exedosoft.plat.ui.jquery.form.DOInputText;
import com.exedosoft.plat.ui.jquery.form.DOTextArea;
import com.exedosoft.plat.ui.jquery.form.DOValueDateTime;
import com.exedosoft.plat.ui.jquery.form.DOValueSimple;
import com.exedosoft.plat.ui.jquery.form.TClose;
import com.exedosoft.plat.ui.jquery.form.TPane;
import com.exedosoft.plat.ui.jquery.form.TPaneSelected;
import com.exedosoft.plat.ui.jquery.form.TServiceSelectedUf;
import com.exedosoft.plat.ui.jquery.form.TServiceUf;
import com.exedosoft.plat.ui.jquery.form.my97.DatePicker;
import com.exedosoft.plat.ui.jquery.grid.GridConditionAutoTr;
import com.exedosoft.plat.ui.jquery.grid.GridList;
import com.exedosoft.plat.ui.jquery.grid.GridSupportMore;
import com.exedosoft.plat.ui.jquery.pane.ContentPane;
import com.exedosoft.plat.ui.jquery.pane.ContentPaneScroll;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.safe.TenancyValues;

public class GeneUICompByTableJquery {

	private static Log log = LogFactory.getLog(GeneUICompByTableJquery.class);

	private static final String SQL_SELECT_SERVICE = "select service.* from DO_Service service,DO_BO bo where service.bouid = bo.objuid and  bo.name = ?";

	private static DOController gridList = DOController
			.getControllerByName(GridList.class.getName());

	private static DOController gridCondition = DOController
			.getControllerByName(GridConditionAutoTr.class.getName());

	private static DOController gridSupportMore = DOController
			.getControllerByName(GridSupportMore.class.getName());

	private static DOController formInputText = DOController
			.getControllerByName(DOInputText.class.getName());

	private static DOController formTextArea = DOController
			.getControllerByName(DOTextArea.class.getName());

	private static DOController formValueSimple = DOController
			.getControllerByName(DOValueSimple.class.getName());

	private static DOController formValueDate = DOController
			.getControllerByName(DOValueDateTime.class.getName());

	private static DOController formDateMy97 = DOController
			.getControllerByName(DatePicker.class.getName());

	private static DOController contentPane = DOController
			.getControllerByName(ContentPane.class.getName());

	private static DOController formServiceUf = DOController
			.getControllerByName(TServiceUf.class.getName());

	private static DOController paneOverFlow = DOController
			.getControllerByName(ContentPaneScroll.class.getName());

	private static DOController formCloseButton = DOController
			.getControllerByName(TClose.class.getName());

	// private DOController formDelete =
	// DOController.getControllerByName(TDelete.class.getName());

	private static DOController formPane = DOController
			.getControllerByName(TPane.class.getName());

	private static DOController formHidden = DOController
			.getControllerByName(DOInputHidden.class.getName());

	private static DOController formItemPane = DOController
			.getControllerByName(TPaneSelected.class.getName());

	private static DOController formItemServiceUf = DOController
			.getControllerByName(TServiceSelectedUf.class.getName());

	// /////////////////////不能在这个就获取bp,因为类变量，当这个实例实例化时就执行了

	private String geneATable = "";

	private String boUID;

	private static Hashtable<String, String> multiL10ns = new Hashtable<String, String>();

	public GeneUICompByTableJquery(String aTable, String aBOUID) {

		this.geneATable = aTable;
		this.boUID = aBOUID;
	}

	public void geneConfig() {

		dealMultiL10ns();

		DODataSource dss = DODataSource.parseGlobals();
		Transaction t = dss.getTransaction();

		// ///////////////////second generator grid and panes
		try {
			t.begin();
			List sers = DAOUtil.INSTANCE().select(DOService.class,
					SQL_SELECT_SERVICE, this.geneATable);

			/**
			 * 根据Service生成ui组件。
			 */

			DOService aService = DOService.getService(geneATable + "_list");
			genePaneAndGrid(aService, gridList, geneATable + "_list", "信息列表");

			aService = DOService.getService(geneATable + "_browse");

			genePaneAndGrid(aService, gridSupportMore, geneATable + "_browse",
					"浏览信息");
			genePaneAndGrid(aService, gridSupportMore, geneATable + "_update",
					"修改信息");
			genePaneAndGrid(aService, gridSupportMore, geneATable
					+ "_dulplicate", "复制");
			genePaneAndGrid(aService, gridSupportMore, geneATable + "_insert",
					"新增数据");

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			t.end();
		}
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

	private void dealMultiL10ns() {

//		if (multiL10ns.size() == 0) {
			// /获取 对应的l10n
			TenancyValues tv = (TenancyValues) DOGlobals.getInstance()
					.getSessoinContext().getTenancyValues();
			if (tv != null) {
				DOService findTableService = DOService
						.getService("multi_tenancy_table_findrealtable");
				BOInstance tenant = findTableService.getInstance(geneATable);

				DOService findCols = DOService
						.getService("multi_tenancy_column_findbytableid");
				List list = findCols.invokeSelect(tenant.getUid());
				for (Iterator it = list.iterator(); it.hasNext();) {
					BOInstance aBI = (BOInstance) it.next();
					if (aBI.getValue("l10n") != null) {
						multiL10ns.put(aBI.getValue("col_name"), aBI
								.getValue("l10n"));
					}
				}
			}
//		}

	}

	/**
	 * @param dao
	 * @param aService
	 * @throws ExedoException
	 * @throws DAOException
	 */
	public static DOGridModel genePaneAndGrid(DOService aService,
			DOController controller, String aName, String title)
			throws ExedoException {

		DOGridModel gridM = new DOGridModel();
		gridM.setCategory(aService.getBo());
		gridM.setCaption(title);
		gridM.setName("GM_" + aName);
		gridM.setL10n("GM_" + aName);

		if (!aName.endsWith("insert")) {
			gridM.setService(aService);
		}

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

			// /// 这一块从,multi_tenancy_column 这个表中取

			/**
			 * * 客户端验证配置，分为３部分，以;隔开 １，类型：Integer RealNumber EMail Text Others 2,
			 * 长度 ３, 其他Script 约束
			 * 
			 */

			if (prop.isNumberType()) {
				String exedoType = "RealNumber";
				formM.setExedojoType(exedoType);
			} else if (!prop.isDateOrTimeType()) {
				formM.setExedojoType(";" + prop.getDbSize().intValue());
			}

			if (multiL10ns.get(prop.getColName()) != null) {
				formM.setL10n(multiL10ns.get(prop.getColName()));
			} else {
				formM.setL10n(prop.getColName());
			}
			formM.setGridModel(gridM);

			formM.setOrderNum(Integer.valueOf(i * 5));
			if (prop.isDateOrTimeType()) {
				if (aName.endsWith("browse") || aName.endsWith("list")) {
					formM.setController(formValueDate);
				} else {
					formM.setController(formDateMy97);
				}
			} else {
				if (aName.endsWith("browse") || aName.endsWith("list")) {
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

			if (prop.getColName().equalsIgnoreCase("eversion")) {
				formM.setController(formHidden);
				formM.setIsHidden(DOFormModel.HIDDEN_YES);
				formM.setDefaultValue("1");
				formM.setIsOutGridAction(DOFormModel.OUTGRID_LEFT);
			}

			DAOUtil.INSTANCE().store(formM);
			i++;
		}

		if (aName.endsWith("_browse")) {
			geneCloseButtonForm(aService, gridM);

		} else if (aName.endsWith("_update") || aName.endsWith("_insert")
				|| aName.endsWith("_dulplicate")) {
			geneSaveButtonForm(aService, aName, gridM);
		}

		// 对每个Grid赋給一个Pane

		DOPaneModel pane = new DOPaneModel();
		pane.setCategory(aService.getBo());
		pane.setName("PM_" + aName);

		// ///下一步考虑 是不是名称采用和Servie 一致
		pane.setTitle(title);
		pane.setL10n("PM_" + aName);
		pane.setLinkType(Integer.valueOf(DOPaneModel.LINKTYPE_GRIDMODEL));
		pane.setLinkUID(gridM.getObjUid());
		pane.setController(contentPane);
		DAOUtil.INSTANCE().store(pane);

		return gridM;
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
	private static void geneSaveButtonForm(DOService aService, String aName,
			DOGridModel gridM) throws ExedoException {

		DOFormModel formM = new DOFormModel();
		formM.setL10n("保存");
		String uName = "_update";
		if (aName.endsWith("insert") || aName.endsWith("_dulplicate")) {
			uName = "_insert";
		}

		DOService linkService = DOService.getService(aService.getBo().getName()
				+ uName);
		formM.setLinkService(linkService);
		formM.setIsNewLine(1);
		formM.setNameColspan(Integer.valueOf(0));
		formM.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);

		formM.setAlign("center");

		DOPaneModel pm = DOPaneModel.getPaneModelByName("PM_"
				+ aService.getBo().getName() + "_list");
		formM.setGridModel(gridM);
		formM.setOrderNum(Integer.valueOf(1000));
		formM.setController(formServiceUf);
		formM.setLinkPaneModel(pm);
		formM.setTargetPaneModel(pm);
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
	private static void geneCloseButtonForm(DOService aService,
			DOGridModel gridM) throws ExedoException {
		DOFormModel formM = new DOFormModel();
		formM.setL10n("关闭");
		// DOService linkService =
		// DOService.getService(aService.getBo().getName()
		// + ".delete");
		// formM.setLinkService(linkService);
		formM.setIsNewLine(1);
		formM.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);

		// formM.setValueColspan(Integer.valueOf(2));
		formM.setAlign("center");

		formM.setGridModel(gridM);
		formM.setOrderNum(Integer.valueOf(1000));
		formM.setController(formCloseButton);

		DAOUtil.INSTANCE().store(formM);

	}

	public static void main(String[] args) {

		System.out.println(gridSupportMore);

		System.out.println(contentPane);

	}

}
