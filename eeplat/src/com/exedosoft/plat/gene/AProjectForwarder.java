package com.exedosoft.plat.gene;

import java.util.List;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.BusiPackage;
import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.org.DOAuthorization;

import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOMenuLinks;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOPaneLinks;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.pane.ContentPane;
import com.exedosoft.plat.ui.jquery.pane.LayOutSplitPane;


/**
 * 使用分布式配置库，实现起来太复杂
 * 但是可以动态切换配置库
 * 还是使用导入、导出方式，实现团队协作开发
 * 
 * 导入、导出的方式可以使用xml文件，sql文件。
 * 导入、导出的粒度：
 * 1，工程
 * 2，业务包
 * 3，业务对象
 * 4，服务
 * 5，面板
 * 6，表格
 * 
 * 还有一种 可以按日期。
 * 就是某个日期之后的，全部导出去
 * 
 *   其它更小粒度（最后实现是否考虑）
 *   
 *   属性  参数 表格元素  树 树节点  菜单  
 * 
 * 
 * 
 * 
 * 
 * @author IBM
 *
 */

public class AProjectForwarder {

	DOController ccLayOutPane = DOController
			.getControllerByName(LayOutSplitPane.class.getName());

	DOController ccSplitePane = DOController
			.getControllerByName(LayOutSplitPane.class.getName());

	DOController cContentPane = DOController
			.getControllerByName(ContentPane.class.getName());

	/**
	 * 顶层菜单控制器
	 */
	DOController ccTopPane = DOController
			.getControllerByName("topMenuController");

	/**
	 * ContentPane,但是有滚动条
	 */
	DOController ccTreePane = DOController
			.getControllerByName("exedo_pane_controller_overflow");
	
	

	/**
	 * MenuController,但是有滚动条
	 */
	DOController menuController = DOController
			.getControllerByName("com.exedosoft.plat.ui.defaultimp.menu.DOJOMenuXP");

	public void forwardBaseUI(String projectUid) {

		DOApplication project = DOApplication.getApplicationByID(projectUid);
//		HbmDAO dao = new HbmDAO();
//		dao.setAutoClose(false);

		// //定义主业务包,同名业务包
		BusiPackage bp = new BusiPackage();
		bp.setApplication(project);
		bp.setL10n(project.getL10n());
		bp.setName(project.getName());

		DOBO aBO = new DOBO();
		aBO.setType(DOBO.TYPE_BUSINESS);
		aBO.setName(project.getName() + "global.bo");
		aBO.setL10n(project.getL10n() + "全局业务对象");
		
		DODataSource dds = DODataSource.parseGlobals();
		
		Transaction t = dds.getTransaction();
		
		t.begin();
		
		try {
			// //////////保存业务包
			DAOUtil.INSTANCE().store(bp);

			// //保存业务对象
			aBO.setPakage(bp);
			DAOUtil.INSTANCE().store(aBO);

			/**
			 * 存储应用的根面板
			 */
			DOPaneModel pmRoot = new DOPaneModel();
			pmRoot.setCategory(aBO);
			pmRoot.setName("pane_" + project.getName());
			pmRoot.setL10n("根面板" + project.getL10n());
			pmRoot.setTitle(project.getDescription());
			pmRoot.setLayOutType(Integer.valueOf(DOPaneModel.LAYOUT_VERTICAL));

			// //////////////ccLayOutPane
			pmRoot.setController(ccLayOutPane);

			DAOUtil.INSTANCE().store(pmRoot);

			// ///////业务对象发布为一个应用
		//	project.setDobo(aBO);
			DAOUtil.INSTANCE().store(project);

			/**
			 * 从pane_mainMenuBar copy信息数据
			 */
			DOPaneModel fromPmTop = DOPaneModel
					.getPaneModelByName("BaseAnoleHeader");
			/**
			 * 建立新的头面板
			 */
			DOPaneModel pmTop = new DOPaneModel();

			pmTop.setController(fromPmTop.getController());
			pmTop.setLayOutType(fromPmTop.getLayOutType());
			pmTop.setLinkType(fromPmTop.getLinkType());
			pmTop.setLinkUID(fromPmTop.getLinkUID());
			pmTop.setLayoutAlign("top");
			pmTop.setCategory(aBO);

			pmTop.setTargetPane(fromPmTop.getTargetPane());
			pmTop.setL10n(project.getL10n() + "头面板");
			pmTop.setName(project.getName() + "header_pane");
			DAOUtil.INSTANCE().store(pmTop);

			/**
			 * 建立主面板和头面板的关联关系
			 */
			DOPaneLinks pmTopLink = new DOPaneLinks();
			pmTopLink.setParentPane(pmRoot);
			pmTopLink.setChildPane(pmTop);
			pmTopLink.setOrderNum(new Integer(5));
			DAOUtil.INSTANCE().store(pmTopLink);

			/**
			 * 创建下方的面板
			 */
			DOPaneModel pmBottom = new DOPaneModel();
			pmBottom.setCategory(aBO);
			pmBottom.setL10n(project.getL10n() + "工作区域面板");
			pmBottom.setName(project.getName() + "main_pane");
			pmBottom.setLayOutType(Integer
					.valueOf(DOPaneModel.LAYOUT_HORIZONTAL));
			// ///////splitPane

			pmBottom.setController(ccSplitePane);
			pmBottom.setLayoutAlign("client");
			DAOUtil.INSTANCE().store(pmBottom);
			/**
			 * 建立主面板和工作区域面板的关联
			 */

			DOPaneLinks pmBottomLink = new DOPaneLinks();
			pmBottomLink.setParentPane(pmRoot);
			pmBottomLink.setChildPane(pmBottom);
			pmBottomLink.setOrderNum(new Integer(10));
			DAOUtil.INSTANCE().store(pmBottomLink);

			/**
			 * 创建左边索引面板
			 */

			DOPaneModel fromPmLeft = DOPaneModel
					.getPaneModelByName("pane_zf_left_xp");
			DOPaneModel pmLeft = new DOPaneModel();

			

			pmLeft.setController(fromPmLeft.getController());
			pmLeft.setLayOutType(fromPmLeft.getLayOutType());
			pmLeft.setLinkType(fromPmLeft.getLinkType());
			pmLeft.setLinkUID(fromPmLeft.getLinkUID());
			pmLeft.setLayoutAlign(fromPmLeft.getLayoutAlign());
			pmLeft.setCategory(aBO);

			pmLeft.setTargetPane(fromPmLeft.getTargetPane());
			pmLeft.setL10n(project.getL10n() + "左边索引面板");
			pmLeft.setName(project.getName() + "leftindex_pane");

			pmLeft.setController(ccTreePane);
			
			DAOUtil.INSTANCE().store(pmLeft);
			

			DOPaneLinks pmLeftLink = new DOPaneLinks();
			pmLeftLink.setParentPane(pmBottom);// //bottomPane为父亲
			pmLeftLink.setChildPane(pmLeft);
			pmLeftLink.setOrderNum(new Integer(15));
			DAOUtil.INSTANCE().store(pmLeftLink);

			DOPaneModel pmContent = new DOPaneModel();// ///主内容显示区。
			pmContent.setCategory(aBO);
			pmContent.setName(project.getName() + "_MainContent");
			pmContent.setL10n(project.getL10n() + "基本内容显示");


			pmContent.setController(cContentPane);
			DAOUtil.INSTANCE().store(pmContent);

			DOPaneLinks pmContentLink = new DOPaneLinks();
			pmContentLink.setParentPane(pmBottom);// //bottomPane为父亲
			pmContentLink.setChildPane(pmContent);
			pmContentLink.setOrderNum(new Integer(17));
			DAOUtil.INSTANCE().store(pmContentLink);

			/**
			 * 保存左边的左边的索引菜单
			 */
			pmLeft.setTargetPane(pmContent);
			
			
			DOMenuModel dmRoot = new DOMenuModel();
			dmRoot.setCategory(aBO);
			dmRoot.setController(menuController);
			//////////同名DOMenuModel
			dmRoot.setName(project.getName()+"_root");
			dmRoot.setL10n(project.getL10n()+"_根菜单");
			DAOUtil.INSTANCE().store(dmRoot);
			
			
			DOMenuModel dmBP = new DOMenuModel();
			dmBP.setCategory(aBO);
			dmBP.setParentMenu(dmRoot);
			dmBP.setController(menuController);
			//////////同名DOMenuModel
			dmBP.setName(project.getName());
			dmBP.setL10n(project.getL10n()+"_根业务对象");
			DAOUtil.INSTANCE().store(dmBP);
			
			DOMenuLinks links = new DOMenuLinks();
			links.setLinkType(DOMenuLinks.LINKTYPE_PANEMODEL);
			links.setLinkUID(pmLeft.getObjUid());
			links.setMenuModel(dmRoot);
			links.setOrderNum(Integer.valueOf(1000));
			DAOUtil.INSTANCE().store(links);
				
			
			DAOUtil.INSTANCE().store(pmLeft);

			
		 

		} catch (Exception e) {
			
			t.rollback();
			e.printStackTrace();
		}
		finally {
			t.end();
		}
	}
	
	public static void main(String[] args){
//		AProjectForwarder  af = new AProjectForwarder();
//		af.forwardBaseUI("297e276a0d1f7763010d1f776e5f0001");

		
		
	}
}
