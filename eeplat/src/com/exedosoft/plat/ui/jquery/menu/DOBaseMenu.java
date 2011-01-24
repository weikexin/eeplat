package com.exedosoft.plat.ui.jquery.menu;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOUIDecoration;
import com.exedosoft.plat.util.DOGlobals;

public abstract class DOBaseMenu implements DOIView {

	/**
	 * @param buffer
	 * @param aMenu
	 * @param echoStr
	 *            TODO
	 */
	protected void appendLink(StringBuffer buffer, DOMenuModel aMenu,
			String echoStr) {

		// //如果拥有LinkPane

		if (aMenu.getMenuType() != null
				&& aMenu.getMenuType().intValue() == DOMenuModel.MENUTYPE_LINK) {

			buffer.append("window.open('")
					.append(aMenu.getNote())
					.append("','','height=760,   width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')");

			// window.open("/abp/guotu/gt_digiboard.jsp","login","height=760,   width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no")
		} else if (aMenu.getLinkPane() != null) {

			String targetname = "_opener_tab";
			if (aMenu.getTargetPane() != null) {
				aMenu.getTargetPane().getName();
			}
			DOPaneModel doPm = aMenu.getLinkPane();
			String pml = doPm.getName();
			if (doPm.getResource() != null) {
				String resPath = doPm.getResource().getResourcePath();
				if (resPath.indexOf(".htm") != -1) {
					pml = new StringBuilder("/").append(DOGlobals.URL)
							.append("/")
							.append(doPm.getResource().getResourcePath())
							.toString();
				}
			}

			buffer.append("loadPml({'pml':'").append(pml)
					.append("','pmlName':'")
					.append(aMenu.getLinkPane().getName())
					.append("','title':'").append(aMenu.getL10n())
					.append("','target':'").append(targetname).append("'} );");

		} else if (aMenu.getLinkService() != null) {
			// ////下一步支持
		} else if (aMenu.getDoClickJs() != null) {

			// ////下一步支持
		}

		else {
			buffer.append(getDecoration(aMenu));
		}
	}

	/**
	 * @param popMenus
	 * @param buffer
	 */
	// protected void appendPopMenus(List popMenus, StringBuffer buffer) {
	// for (Iterator it = popMenus.iterator(); it.hasNext();) {
	//
	// DOMenuModel aPopMenu = (DOMenuModel) it.next();
	// buffer.append("<div dojoType='dijit.PopupMenuItem' ").append(
	// " widgetId='submenu").append(aPopMenu.getName()).append(
	// "'>\n");
	// for (Iterator itChild = aPopMenu.retrieveChildren().iterator(); itChild
	// .hasNext();) {
	// DOMenuModel menuChild = (DOMenuModel) itChild.next();
	//
	// if (menuChild.getMenuType() != null
	// && menuChild.getMenuType().intValue() == DOMenuModel.MENUTYPE_SEPARATOR)
	// {
	// buffer.append("<div dojoType='dijit.MenuSeparator'></div>\n");
	//
	// } else {
	//
	// buffer.append("<div dojoType='dijit.MenuItem' caption='")
	// .append(menuChild.getL10n()).append("' ");
	// if (menuChild.retrieveChildren() != null
	// && menuChild.retrieveChildren().size() > 0) {
	// buffer.append(" submenuId='submenu").append(
	// menuChild.getName()).append("' ");
	// }
	//
	// // //如果拥有LinkPane或linkService
	// if (menuChild.getLinkPane() != null
	// || menuChild.getLinkService() != null) {
	// buffer.append(" onclick=\"javascript:");
	// appendLink(buffer, menuChild, null);
	// buffer.append("\"");
	// }
	//
	// if (menuChild.getIcon() != null) {
	// buffer.append(" iconSrc='").append(
	// DOGlobals.PRE_FULL_FOLDER).append("images/")
	// .append(menuChild.getIcon()).append("' ");
	// }
	// buffer.append(">\n</div>\n");
	// }
	// }
	// buffer.append("</div>");
	//
	// }
	// }
	//
	// protected void addPopMenuToList(DOMenuModel menuModel, List list) {
	//
	// if (menuModel.retrieveChildren() != null
	// && menuModel.retrieveChildren().size() > 0) {
	// list.add(menuModel);
	// for (Iterator it = menuModel.retrieveChildren().iterator(); it
	// .hasNext();) {
	// DOMenuModel child = (DOMenuModel) it.next();
	// addPopMenuToList(child, list);
	// }
	// }
	// }

	public static String getDecoration(DOMenuModel property) {

		List decorations = DOUIDecoration.getEvent4MenuModel(property);

		StringBuffer buffer = new StringBuffer();
		for (Iterator it = decorations.iterator(); it.hasNext();) {

			DOUIDecoration dec = (DOUIDecoration) it.next();
			buffer.append(" ").append(dec.getDecoPoint());

		}
		return buffer.toString();
	}

}
