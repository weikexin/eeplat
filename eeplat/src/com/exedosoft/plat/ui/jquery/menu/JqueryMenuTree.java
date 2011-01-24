package com.exedosoft.plat.ui.jquery.menu;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class JqueryMenuTree extends DOBaseMenu {

	public String getHtmlCode(DOIModel aMenu) {
		DOMenuModel aModel = (DOMenuModel) aMenu;
		return this.newTee(aModel);
	}

	public String newTee(DOMenuModel menuModel) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script type=\"text/javascript\">")
		.append("$(function() {$('#menuTree').lightTreeview({")
		.append("collapse: true,")
		.append("line: true,")
		.append("nodeEvent: true,")
		.append("unique: true,")
		.append("style: 'black',")
		.append("animate: 400});});")
		.append("</script>");
		
		
		


		sb.append("<h2>&nbsp;&nbsp;"+menuModel.getL10n()+"</h2>");
		
		sb.append("<ul id=\"menuTree\" class=\"lightTreeview treeview-black\">");
		
		for (Iterator it = menuModel.retrieveChildren().iterator(); it.hasNext();) {
			DOMenuModel aMenu = (DOMenuModel) it.next();
			sb.append("<li>");
			
			
			
			sb.append("<div class=\"treeview-folder\" id=\"")
			.append(aMenu.getObjUid())
			.append("\" >").append(aMenu.getL10n()).append("</div>");
			
			sb.append("<ul style=\"display:none\">");
			if (aMenu.retrieveChildren() != null) {
				
				for (Iterator itChild = aMenu.retrieveChildren().iterator(); itChild.hasNext();){
					DOMenuModel aChildMenu = (DOMenuModel) itChild.next();
					
					sb.append("<li>");
					
					sb.append("<div class=\"treeview-file\" id=\" ").append(aChildMenu.getObjUid())
					.append("\" name=\"").append(aChildMenu.getL10n()).append("\"");
					//自定义属性paneid
					if(aChildMenu.getLinkPane() != null){
						BOInstance bi = DOGlobals.getInstance().getRuleContext().getInstance();
						sb.append(" paneid=\"").append(aChildMenu.getLinkPane().getFullCorrHref(bi, null))
						.append("\"");
					}
					sb.append(">")
					.append(aChildMenu.getL10n()).append("</div>");
					
					sb.append("</li>");
					
				}
				
			}
			sb.append("</ul>");
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}

	/**
	 * draw static tree
	 */
	public String drawStaticTree(DOMenuModel menuModel) {

		String sysTreeRoot = DOGlobals.getInstance().getSessoinContext()
				.getSysTreeRoot();
		if (sysTreeRoot == null) {
			sysTreeRoot = menuModel.getL10n();
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("<script language='javascript'>");
		buffer.append("var tree11 = new WebFXTree('").append(sysTreeRoot)
				.append("');\n");
		buffer.append("tree11.setBehavior('classic');\n");

		List children = menuModel.retrieveChildren();

		if (menuModel != null && children != null && !children.isEmpty()) {
			for (Iterator it = children.iterator(); it.hasNext();) {
				DOMenuModel item = (DOMenuModel) it.next();
				drawStaticTreeHelp(item, buffer, "tree11");
			}
		}

		buffer.append("document.write(tree11.toHtml());\n");
		buffer.append("tree11.expandAll();");
		buffer.append("</script>");
		return buffer.toString();
	}

	/**
	 * draw static tree helper
	 * 
	 * @param item
	 * @param buffer
	 * @param us
	 */
	private void drawStaticTreeHelp(DOMenuModel item, StringBuffer buffer,
			String parentItemName) {

		String itemName = item.getName();
		itemName = StringUtil.get_Name(itemName);

		buffer.append("var ").append(itemName).append(" = new WebFXTreeItem('")
				.append(item.getL10n()).append("'");

		// //锟斤拷锟接碉拷锟絃inkPane锟斤拷linkService
		if (item.getLinkPane() != null || item.getLinkService() != null) {
			buffer.append(",\"javascript:");
			this.appendLink(buffer, item, item.getEchoJs());
			buffer.append("\"");
		}

		buffer.append(");\n");

		// ////parent item and the child

		buffer.append(parentItemName).append(".add(").append(itemName).append(
				");\n");

		List children = item.retrieveChildren();
		if (children != null && !children.isEmpty()) {
			for (Iterator it = children.iterator(); it.hasNext();) {
				DOMenuModel itemChild = (DOMenuModel) it.next();
				drawStaticTreeHelp(itemChild, buffer, itemName);
			}
		}
	}

}
