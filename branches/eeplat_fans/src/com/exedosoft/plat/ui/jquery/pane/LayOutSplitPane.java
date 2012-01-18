package com.exedosoft.plat.ui.jquery.pane;

/**
 * 左右分割面板样式的控制器,布局界面考虑用jquery.layout改写。
 */
public class LayOutSplitPane extends TPaneTemplate {
	
	
	public LayOutSplitPane() {
		this.templateFile = "panel/LayOutSplitPane.ftl";
	}
//	
//	
//
//	public String getHtmlCode(DOIModel iModel) {
//		
//		
//		DOPaneModel pm = (DOPaneModel) iModel;
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("<TABLE class=gMain  cellspacing='0' cellpadding='0'>").append("<TBODY><TR>");
//		
//		genePaneContext(buffer, pm);
//		
//		buffer.append("</TR></TBODY></TABLE>");
//		return buffer.toString();
//		
//	
//	}

}
