package com.exedosoft.plat.ui.jquery.menu;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOWebFxTree extends DOBaseMenu{

	public String getHtmlCode(DOIModel aMenu) {
		DOMenuModel aModel = (DOMenuModel)aMenu;
		return this.drawStaticTree(aModel);
	}

	/**
	 * draw static tree
	 */
	public String drawStaticTree(DOMenuModel menuModel) {
		
		String sysTreeRoot = sysTreeRoot =  menuModel.getL10n(); 
		if(menuModel.getMenuType()!=null 
				&& menuModel.getMenuType().intValue() == DOMenuModel.MENUTYPE_NAVIGATION
				&& DOGlobals.getInstance().getSessoinContext().getSysTreeRoot()!=null){
			sysTreeRoot = DOGlobals.getInstance().getSessoinContext().getSysTreeRoot();
		}
		

		StringBuffer buffer = new StringBuffer();
		buffer.append("<div class=tree id='")
		.append(menuModel.getObjUid())
		.append("'></div> \n <script language='javascript'>webFXTreeHandler.resetContext();");
		buffer.append("var tree11 = new WebFXTree('")
		.append(sysTreeRoot)
		.append("');\n");
		buffer.append("tree11.setBehavior('classic');\n");
		
		List children = menuModel.retrieveChildren();
		
		if (menuModel != null && children!=null
				&& !children.isEmpty()) {
			for (Iterator it = children.iterator(); it
					.hasNext();) {
				DOMenuModel item = (DOMenuModel) it.next();
				drawStaticTreeHelp(item, buffer, "tree11");
			}
		}
		buffer.append("$('#")
		.append(menuModel.getObjUid())
		.append("').append(tree11.toHtml());");
		//buffer.append("tree11.expandAll();");
		buffer.append("resscrEvt();\n</script>");
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
		itemName =  StringUtil.get_Name(itemName);

		buffer.append("var ").append(itemName).append(
				" = new WebFXTreeItem('").append(item.getL10n()).append("'");

		
		if (item.getLinkPane() != null || item.getLinkService()!=null) {
			buffer.append(",\"javascript:");
			this.appendLink(buffer,item,  item.getEchoJs());
			buffer.append("\"");
		}
		
		buffer.append(");\n");
				
		//////parent item and the child
		
		
		buffer.append(parentItemName).append(".add(").append(itemName)
				.append(");\n");
		
		List children = item.retrieveChildren();
		if (children != null
				&& !children.isEmpty()) {
			for (Iterator it = children.iterator(); it
					.hasNext();) {
				DOMenuModel itemChild = (DOMenuModel) it.next();
				drawStaticTreeHelp(itemChild, buffer, itemName);
			}
		}
	}

}
