package com.exedosoft.plat.ui.mobile.menu;

import java.util.Iterator;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.jquery.menu.DOBaseMenu;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.DOGlobals;

/**
 *Mobile下标准菜单
 */
public class JqueryMenu extends DOBaseMenu {

	public String getHtmlCode(DOIModel aModel) {

		DOMenuModel rootMenu = (DOMenuModel) aModel;
		StringBuffer buffer = new StringBuffer();

//		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
//		<li data-role="list-divider">Overview</li>
//		<li><a href="docs/about/intro.html">Intro to jQuery Mobile</a></li>
//		<li><a href="docs/about/features.html">Features</a></li>
//
//		<li><a href="docs/about/accessibility.html">Accessibility</a></li>
//		<li><a href="docs/about/platforms.html">Supported platforms</a></li>
//	</ul>
//		
		
		
		for (Iterator it = rootMenu.retrieveChildren().iterator(); it.hasNext();) {
			DOMenuModel aMenu = (DOMenuModel) it.next();

			buffer.append("<ul data-role='listview' data-inset='true' data-theme='c' data-dividertheme='b'>");
			buffer.append("<DIV class=mTitle id=").append(aMenu.getObjUid())
					.append(" name=").append(aMenu.getL10n()).append("><a>");
			buffer.append(aMenu.getL10n()).append("</a></div>");
			buffer.append("<div class=mHi>");
			if (aMenu.retrieveChildren() != null) {

				for (Iterator itChild = aMenu.retrieveChildren().iterator(); itChild
						.hasNext();) {
					DOMenuModel aChildMenu = (DOMenuModel) itChild.next();
					buffer.append("<div class=mMenu id=\""
							+ aChildMenu.getObjUid() + "\" name =\""
							+ aChildMenu.getL10n() + "\"");
					// 自定义属性paneid
					if (aChildMenu.getMenuType() != null
							&& aChildMenu.getMenuType().intValue() == DOMenuModel.MENUTYPE_LINK) {
						buffer.append(" paneid=\"")
								.append(aChildMenu.getNote()).append("\"");
					} else

					if (aChildMenu.getLinkPane() != null) {
						BOInstance bi = DOGlobals.getInstance()
								.getRuleContext().getInstance();
						buffer.append(" paneid=\"").append(
								aChildMenu.getLinkPane().getFullCorrHref(bi,
										null)).append("\"");
					}
					buffer.append(">").append("<A>").append(
							aChildMenu.getL10n()).append("</A></div>");
				}

			}
			buffer.append("</div>");
		}

		buffer
				.append("<script language=\"javascript\">$(function() {bindMenuHoverCss();bindClickMenu();resscrEvt();});</script>");

		return buffer.toString();
	}
}
