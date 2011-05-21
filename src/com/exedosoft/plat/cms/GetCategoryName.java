package com.exedosoft.plat.cms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author lenovo
 * 获取分类名
 * 注意，分类ID不能在模板的自定义指令中作为参数传入，只能从REQUEST中取 
 * 
 */
public class GetCategoryName implements TemplateMethodModel{
	
	private HttpServletRequest request; 
	public GetCategoryName(HttpServletRequest request){
		this.request = request ;
	}

	public Object exec(List list) throws TemplateModelException {
		String cat_id = request.getParameter("cat_id") ;
		if (cat_id == null ){
			throw new TemplateModelException("分类ID未定义");
		}
		DOService s = DOService.getService("cms_category_browse");
		BOInstance bo = new BOInstance();
		bo.putValue("cms_category_currunt", cat_id) ;
		List l =  s.invokeSelect(bo);
		if (!l.isEmpty()){
			bo = (BOInstance) l.get(0);
			return bo.getValue("cat_name");
		}else {
			return "" ;
		}
	}

}
