package com.exedosoft.plat.ui.jquery.menu;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.DOGlobals;

/**
 *菜单风格的控制器
 */
public class JqueryMenuXP extends DOBaseMenu {
	
	private static Log log = LogFactory.getLog(JqueryMenuXP.class);


	public String getHtmlCode(DOIModel aModel) {

		DOMenuModel rootMenu = (DOMenuModel) aModel;
		StringBuffer buffer = new StringBuffer();

		for (Iterator it = rootMenu.retrieveChildren().iterator(); it.hasNext();) {
			DOMenuModel aMenu = (DOMenuModel) it.next();
			
			log.info("===========================" +  DOGlobals.getValue("jslib"));
			log.info("==========================" + aMenu.getController());
			log.info("==========================" + aMenu.getController().getCorrByConfig());

			
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
