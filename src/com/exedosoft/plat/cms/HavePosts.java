package com.exedosoft.plat.cms;

import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author lenovo
 *  判断有文章?
 */
public class HavePosts implements TemplateMethodModel{


	public Object exec(List arg0) throws TemplateModelException {
		DOService service = DOService.getService("cms_posts_list");
		BOInstance bo = new BOInstance();
		List l = service.invokeSelect(bo);
		if (l.size() > 0){
			return true;
		}else {
			
			return false;
		}
	}

}
