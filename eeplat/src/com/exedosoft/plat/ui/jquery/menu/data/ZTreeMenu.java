/**
 * 
 */
package com.exedosoft.plat.ui.jquery.menu.data;

import java.util.ArrayList;
import java.util.List;

/**
 * ZTree的实体类，供FreeMarker模板使用
 * @author 小飞猪
 */
public class ZTreeMenu {

	private String name;
	private String desc;
	private String icon;
	private String template;
	private List<ZTreeMenu> children;
	
	public void initChild(){
		children = new ArrayList<ZTreeMenu>();
	}
	
	public void addChild(ZTreeMenu child){
		children.add(child);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<ZTreeMenu> getChildren() {
		return children;
	}

	public void setChildren(List<ZTreeMenu> children) {
		this.children = children;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
