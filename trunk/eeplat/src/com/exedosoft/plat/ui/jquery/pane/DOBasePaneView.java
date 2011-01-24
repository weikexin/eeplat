package com.exedosoft.plat.ui.jquery.pane;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ui.DOBaseView;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOMenuLinks;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOTreeModel;

public abstract class DOBasePaneView extends DOBaseView {

	private static Log log = LogFactory.getLog(DOBasePaneView.class);


	/**
	 * @param buffer
	 * @param pm
	 */
	protected void appendAttrs(StringBuffer buffer, DOPaneModel pm) {

		if (pm.getL10n() != null) {
			buffer.append(" label='").append(pm.getL10n()).append("' ");
		}

		if (pm.getLayoutAlign() != null) {
			buffer.append("  layoutAlign='").append(pm.getLayoutAlign())
					.append("'");
		}
		if (pm.getAlign() != null) {
			buffer.append("  align='").append(pm.getAlign()).append("'");
		}

		if (pm.getController().getStyle() != null) {
			buffer.append(" style='").append(pm.getController().getStyle())
					.append("' ");
		}
		if (pm.getController().getStandbyAttrs() != null) {
			buffer.append(" ").append(pm.getController().getStandbyAttrs())
					.append(" ");
		}
	}

	public static void genePaneContext(StringBuffer buffer, DOPaneModel pm) {

		// log.info("Enter DOBasePaneView=======================");
		// log.info("LinkType:::" + pm.getLinkType());
		// log.info("LinkUID::::" + pm.getLinkUID());

		// buffer.append("<script>TANGER_RemoveNode();</script>");

		if (pm.retrieveChildren() != null && pm.retrieveChildren().size() > 0) {

			// log.info("The Enter PaneModel'Name::::::::" + pm.getName());
			// log.info("The Enter PaneModel'childrens:::" +
			// pm.retrieveChildren());

			for (Iterator it = pm.retrieveChildren().iterator(); it.hasNext();) {
				DOPaneModel model = (DOPaneModel) it.next();

				// System.out.println("Current:::::::" +
				// System.currentTimeMillis());
				// System.out.println("Chilld PaneModel'name:::::::" +
				// model.getName());
				// System.out.println("Chilld PaneModel'Controller:::::::" +
				// model.getController().getViewJavaClass());

				buffer.append(model.getHtmlCode());
			}
		} else {

			// ////////////////锟饺达拷印锟剿碉拷锟斤拷息

			// List menus = DOMenuLinks.getMenusByPaneModelUid(pm.getObjUid());
			// DOMenuModel showMenu = null;
			//
			// if (menus != null) {
			// for (Iterator it = menus.iterator(); it.hasNext();) {
			// DOMenuModel mm = (DOMenuModel) it.next();
			// mm.setContainerPane(pm);
			// buffer.append(mm.getHtmlCode());
			// }
			// showMenu = DOMenuLinks
			// .getShowMenuByPaneModelUid(pm.getObjUid());
			// if (showMenu != null) {
			// buffer.append(showMenu.getLinkPane().getHtmlCode());
			//
			// }
			// }

			if (pm.getLinkType() != null) {
				switch (pm.getLinkType().intValue()) {

				case DOPaneModel.LINKTYPE_MENU:
					if (pm.getLinkUID() != null
							&& !"".equals(pm.getLinkUID().trim())) {
						try {
							DOMenuModel mm = pm.getMenuModel();
							mm.setContainerPane(pm);
							buffer.append(mm.getHtmlCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;

				case DOPaneModel.LINKTYPE_GRIDMODEL:
					if (pm.getLinkUID() != null
							&& !"".equals(pm.getLinkUID().trim())) {
						try {
							DOGridModel gm = pm.getDOGridModel();
							gm.setContainerPane(pm);
							buffer.append(gm.getHtmlCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				case DOPaneModel.LINKTYPE_TREEMODEL:

					if (pm.getLinkUID() != null
							&& !"".equals(pm.getLinkUID().trim())) {

						try {
							DOTreeModel tm = pm.getTreeModel();
							tm.setContainerPane(pm);
							if (pm.getDropDownID() != null) {
								tm.setDropDownID(pm.getDropDownID());
							}
							buffer.append(tm.getHtmlCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;

				}
			}

		}
	}

}
