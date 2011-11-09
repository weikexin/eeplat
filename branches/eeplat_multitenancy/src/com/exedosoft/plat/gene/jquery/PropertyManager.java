package com.exedosoft.plat.gene.jquery;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.jquery.form.DOInputText;
import com.exedosoft.plat.ui.jquery.form.DOResultListPopup;
import com.exedosoft.plat.ui.jquery.form.DOTextArea;
import com.exedosoft.plat.ui.jquery.form.DOValueDate;
import com.exedosoft.plat.ui.jquery.form.DOValueResultList;
import com.exedosoft.plat.ui.jquery.form.DOValueSimple;
import com.exedosoft.plat.ui.jquery.form.my97.DatePicker;

import java.sql.Types;

public class PropertyManager {

	private static Log log = LogFactory.getLog(PropertyManager.class);
	
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

	
	/**
	 * 为业务对象删除一个属性
	 * @param aDOBO
	 * @param colName
	 */
	
	public void removeProperty(DOBO aDOBO, DOBOProperty dop) {
		
		try {
			if(dop!=null){
				
				List<DOParameter>  paras =  DOParameter.getParameterByPropertyUid(dop.getObjUid());
				for(Iterator<DOParameter> it = paras.iterator(); it.hasNext();){
					
					DOParameter aPara = it.next();
					DOService insertService = aDOBO.getDInsertService();
					insertService.removeMetaParameter(aPara);
					CacheFactory.getCacheRelation().getData().remove(insertService.getObjUid()
							+ CacheFactory.TYPE_PARASERVICE);
					
					DOService updateService = aDOBO.getDUpdateService();
					updateService.removeMetaParameter(aPara);
					CacheFactory.getCacheRelation().getData().remove(updateService.getObjUid()
							+ CacheFactory.TYPE_PARASERVICE);
					DAOUtil.INSTANCE().delete(aPara);
				}
//////////////////////////////	DO_Parameter_Service_deleterubbish 这个 sql需要改写	
				DOService deletes = DOService.getService("DO_Parameter_Service_deleterubbish");
				deletes.invokeUpdate();
			
				
//////////////////////表格元素
				for(int i = 0 ;i < 10;i++){
					DOFormModel aFm = DOFormModel.getFormModelByProperty(dop.getObjUid());
					if(aFm!=null){
						DAOUtil.INSTANCE().delete(aFm);
					}
				}
				DAOUtil.INSTANCE().delete(dop);
			}
			
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 为业务对象增加一个属性
	 * 
	 * @param aDOBO
	 * @param colName
	 * @param type
	 */
	public void addProperty(DOBO aDOBO, String colName, int type,int size) {

		// 增加属性
		DOBOProperty pro = new DOBOProperty();
		try {
			pro.setColName(colName);
			pro.setL10n(colName);
			pro.setDbType(type);
			pro.setDbSize(size);
			pro.setDoBO(aDOBO);
			pro.setPropName(colName);
			DAOUtil.INSTANCE().store(pro);

			// 增加参数

			DOParameter dop = new DOParameter();
			dop.setL10n(colName);
			dop.setName(colName);
			dop.setType(DOParameter.TYPE_FORM);
			dop.setProperty(pro);
			dop.setParaBO(aDOBO);
			DAOUtil.INSTANCE().store(dop);

			// /服务相关
			// /insert service
			DOService insertService = aDOBO.getDInsertService();
			if (insertService != null) {
				insertService.addMetaParameter(dop);
			} else {
				log.error("Insert Error:::" + aDOBO.getName() + "__colName::"
						+ colName);
			}
			CacheFactory.getCacheRelation().getData().remove(insertService.getObjUid()
					+ CacheFactory.TYPE_PARASERVICE);

			DOService updateService = aDOBO.getDUpdateService();
			if (updateService != null) {
				updateService.addMetaParameter(dop);
			} else {
				log.error("Update Error:::" + aDOBO.getName() + "__colName::"
						+ colName);
			}
			CacheFactory.getCacheRelation().getData().remove(updateService.getObjUid()
					+ CacheFactory.TYPE_PARASERVICE);
			
			
			DOGridModel gmBrowse = DOGridModel.getGridModelByName("GM_" + aDOBO.getName()+"_browse");
			if(gmBrowse!=null){
				geneForm(pro,gmBrowse);
			}
			
			DOGridModel gmUpdate = DOGridModel.getGridModelByName("GM_" + aDOBO.getName()+"_update");
			if(gmUpdate!=null){
				geneForm(pro,gmUpdate);
			}

			DOGridModel gmList = DOGridModel.getGridModelByName("GM_" + aDOBO.getName()+"_list");
			if(gmList!=null){
				geneForm(pro,gmList);
			}	
			
			DOGridModel gmInsert = DOGridModel.getGridModelByName("GM_" + aDOBO.getName()+"_insert");
			if(gmInsert!=null){
				geneForm(pro,gmInsert);
			}		
			
			DOGridModel gmDulplicate = DOGridModel.getGridModelByName("GM_" + aDOBO.getName()+"_dulplicate");
			if(gmDulplicate!=null){
				geneForm(pro,gmDulplicate);
			}		
			
			

			// ///////////////////////UI暂时可以不用先增加
			// //增加UI

		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private  void geneForm(DOBOProperty prop, DOGridModel gridM){
		
		DOFormModel formM = new DOFormModel();
		formM.setRelationProperty(prop);
		
		String aName = gridM.getName().toLowerCase();
		
		/**
		 *  * 客户端验证配置，分为３部分，以;隔开 １，类型：Integer RealNumber EMail Text Others 2, 长度 ３,
			 * 其他Script 约束
			 * 
		 */
			
		
		if (prop.isNumberType()) {
			String  exedoType = "RealNumber";
			formM.setExedojoType(exedoType);
		}else if(!prop.isDateOrTimeType()){
			formM.setExedojoType(";"+ prop.getDbSize().intValue());
		}

		formM.setL10n(prop.getColName());
		formM.setGridModel(gridM);

		
		formM.setOrderNum(1000);
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
				
				if(prop.getDbSize()!=null && prop.getDbSize().intValue()>500){
					formM.setController(formTextArea);
					formM.setIsNewLine(DOFormModel.NEWLINE_YES);
				}else{
					formM.setController(formInputText);
				}
			}

		}
		try {
			DAOUtil.INSTANCE().store(formM);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	/**
	 * 
	 * 建立外键关系
	 * @param propertyName
	 * @param aBO
	 */
	
	public void buildRelation(String propertyName,DOBO aBO){
		
		if(propertyName == null || aBO==null){
			log.error("传入的参数有误!!!!!!!!!!");
			return;
		}
		
		//////property  ---- linkBO
		///从property往下追
		Transaction t = DODataSource.parseGlobals().getTransaction();
		t.begin();
		List<DOBOProperty> lists = DAOUtil.INSTANCE().select(DOBOProperty.class, "select * from DO_BO_Property  where col_name = ?", propertyName);
		
		////可以根据外键关系创建主子表的关联关系
		try {
			for(Iterator<DOBOProperty> it = lists.iterator(); it.hasNext();){
				DOBOProperty dop = it.next();
				if(dop.getLinkBO()==null){
					dop.setLinkBO(aBO);
					DAOUtil.INSTANCE().store(dop);
				}
				List<DOFormModel>  forms = DAOUtil.INSTANCE().select(DOFormModel.class, "select * from DO_UI_FormModel  where relationPropertyUid = ?", dop.getObjUid());
				for(Iterator<DOFormModel>  itFm = forms.iterator(); itFm.hasNext();){
					DOFormModel fm = itFm.next();
					if(fm.getLinkService()==null){
						if(fm.getGridModel().getName().toLowerCase().contains("browse") || fm.getGridModel().getName().toLowerCase().contains("_list") || fm.getGridModel().getName().toLowerCase().contains("_result") ){
							fm.setLinkService(aBO.getDSeleByIdService());
							fm.setController(DOController.getControllerByName(DOValueResultList.class.getCanonicalName()));
						}else{
							fm.setLinkService(aBO.getDSeleAllService());
							fm.setController(DOController.getControllerByName(DOResultListPopup.class.getCanonicalName()));
						}
						DAOUtil.INSTANCE().store(fm);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t.rollback();
		}
		
		t.end();
		
		
		
		
	}

	public static void main(String[] args) throws ExedoException {
		
		DOBO boPane = DOBO.getDOBOByName("DO_UI_PaneModel");
		DOBO boGrid = DOBO.getDOBOByName("DO_UI_GridModel");
		DOBO boProperty = DOBO.getDOBOByName("DO_BO_Property");
		DOBO boService = DOBO.getDOBOByName("DO_Service");
		DOBO boController = DOBO.getDOBOByName("DO_UI_Controller");
		
		DOBO aBO = DOBO.getDOBOByName("do_bo");
		
		DOBO boPackage = DOBO.getDOBOByName("DO_BusiPackage");
		DOBO boDataSource = DOBO.getDOBOByName("DO_DataSource");

		DOBO boApplication = DOBO.getDOBOByName("DO_Application");
		
		DOBO boCodeMain = DOBO.getDOBOByName("DO_CODE_MAIN");
		
		DOBO boActionConfig = DOBO.getDOBOByName("DO_ActionConfig");
		
		DOBO boParameter = DOBO.getDOBOByName("DO_Parameter");
		
		DOBO boFormModel = DOBO.getDOBOByName("DO_UI_FormModel");
		
		PropertyManager pm = new PropertyManager();
		
		//relationPropertyUid
		pm.buildRelation("applicationUID", boApplication);
		

//		List<DOService> lists = DAOUtil.INSTANCE().select(DOService.class,
//				"select * from do_service where type = 7");
//		for (Iterator<DOService> it = lists.iterator(); it.hasNext();) {
//			DOService dos = it.next();
//			DOBOProperty dop = DOBOProperty.getDOBOPropertyByName(dos
//					.getBo().getName(), "objuid");
//			
//			DOParameter p = DOParameter.getParameterByProperty(dop,15);
//			if(p!=null){
//				dos.removeMetaParameter(p);
//			}
//		}
	}
	

	// DOBO bo = DOBO.getDOBOByName("do.bo");
	// a.addProperty(bo, "creator", Types.VARCHAR);
	// a.addProperty(bo, "modifier", Types.VARCHAR);
	// a.addProperty(bo, "mVersion", Types.VARCHAR);
	//		
	// a.addProperty(bo, "creatDate", Types.DATE);
	// a.addProperty(bo, "modifyDate", Types.DATE);

}
