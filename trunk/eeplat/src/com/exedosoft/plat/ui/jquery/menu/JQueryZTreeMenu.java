/**
 * 
 */
package com.exedosoft.plat.ui.jquery.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.jquery.menu.DOBaseMenu;
import com.exedosoft.plat.ui.jquery.menu.data.ZTreeMenu;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.FreeMarkerUtils;


/**
 * 使用JQuery ZTree 实现的菜单控制器
 * @author 小飞猪
 */
public class JQueryZTreeMenu extends DOBaseMenu {

	private final static Log log = LogFactory.getLog(JQueryZTreeMenu.class);
	
	/* (non-Javadoc)
	 * @see com.exedosoft.plat.ui.DOIView#getHtmlCode(com.exedosoft.plat.ui.DOIModel)
	 */
	@Override
	public String getHtmlCode(DOIModel aMenu) {
		DOMenuModel aModel = (DOMenuModel)aMenu;
		return this.drawStaticTree(aModel);
	}

	/**
	 * draw static tree
	 */
	public String drawStaticTree(DOMenuModel menuModel) {
		
		if(menuModel==null){
			return null;
		}
		String sysTreeRoot =  menuModel.getL10n(); 
		ZTreeMenu rootMenu = createZTreeMenu(menuModel);
		if(menuModel.getMenuType()!=null 
				&& menuModel.getMenuType().intValue() == DOMenuModel.MENUTYPE_NAVIGATION
				&& DOGlobals.getInstance().getSessoinContext().getSysTreeRoot()!=null){
			sysTreeRoot = DOGlobals.getInstance().getSessoinContext().getSysTreeRoot();
			rootMenu.setName(sysTreeRoot);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", rootMenu);
		String result = null;
		try {
			result = FreeMarkerUtils.processTemplate(JQueryZTreeMenu.class, "ztree.ftl", map);
		} catch (Exception e) {
			log.warn("在转换菜单树时发生异常:"+e.getMessage(),e);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private ZTreeMenu createZTreeMenu(DOMenuModel item){
		ZTreeMenu menu = new ZTreeMenu();
		menu.setName(item.getL10n());
		List<DOMenuModel> children = item.retrieveChildren();
		if (children != null && !children.isEmpty()) {
			menu.initChild();
			for(DOMenuModel itemChild : children){
				menu.addChild(createZTreeMenu(itemChild));
			}
		} 
		if (item.getLinkPane() != null || item.getLinkService()!=null) {
			StringBuffer buffer = new StringBuffer();
			this.appendLink(buffer, item, item.getEchoJs());
			menu.setDesc(buffer.toString());
		}
		if(item.getIcon()!=null){
			menu.setIcon(item.getIcon());
			if(item.getTemplate()!=null)
				menu.setTemplate(item.getTemplate());
		}
		return menu;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
