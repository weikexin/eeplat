package com.exedosoft.plat.ui.mobile.menu;

import java.util.Iterator;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.jquery.menu.DOBaseMenu;

/**
 * Mobile下标准菜单
 */
public class JqueryMenu extends DOBaseMenu {

	public String getHtmlCode(DOIModel aModel) {

		DOMenuModel rootMenu = (DOMenuModel) aModel;
		StringBuffer buffer = new StringBuffer();

		for (Iterator it = rootMenu.retrieveChildren().iterator(); it.hasNext();) {
			DOMenuModel aMenu = (DOMenuModel) it.next();
			if (aMenu.getIntEnvType() != DOMenuModel.MENU_ENV_PC) {

				buffer.append("<ul data-role='listview' data-inset='true' data-theme='c' data-dividertheme='b'>\n");
				buffer.append("  <li data-role='list-divider'>")
						.append(aMenu.getL10n()).append("</li>\n");

				if (aMenu.retrieveChildren() != null) {

					for (Iterator itChild = aMenu.retrieveChildren().iterator(); itChild
							.hasNext();) {
						DOMenuModel aChildMenu = (DOMenuModel) itChild.next();
						// 自定义属性paneid
						if (aChildMenu.getIntEnvType() != DOMenuModel.MENU_ENV_PC && aChildMenu.getLinkPane()!=null) {
							buffer.append(
									"<li><a href='")
									.append(aChildMenu.getLinkPane().getCorrHref())
									.append("'>")
									.append(aChildMenu.getL10n())
									.append("</a></li>");
						}
					}

				}
				buffer.append("</ul>");

			}
		}


		return buffer.toString();
	}
}
