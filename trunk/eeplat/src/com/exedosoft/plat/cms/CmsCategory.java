package com.exedosoft.plat.cms;

/**
 * @author lenovo
 * 内容分类
 */
public class CmsCategory {
	
	public String cat_name;
	public String cat_description;
	public String cat_parent;
	
	
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String catName) {
		cat_name = catName;
	}
	public String getCat_description() {
		return cat_description;
	}
	public void setCat_description(String catDescription) {
		cat_description = catDescription;
	}
	public String getCat_parent() {
		return cat_parent;
	}
	public void setCat_parent(String catParent) {
		cat_parent = catParent;
	}

}
