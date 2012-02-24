package com.exedosoft.plat.ui.mobile.pane;

import com.exedosoft.plat.ui.jquery.pane.TPaneTemplate;

/**
 *  页面像dojo一样存在加载后解析的情况，所以用$.mobile.changePage,
 *  不要考虑用PC的情况解决mobile的情况
 * @author Administrator
 *
 */
public class PagePane extends TPaneTemplate {

	public PagePane() {
		this.templateFile = "mobile/panel/PagePane.ftl";
	}

}
