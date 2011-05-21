package com.exedosoft.plat.cms;

import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author lenovo
 *  判断指定IDh号的文章是否有评论?
 */
public class HaveComments implements TemplateMethodModel{


	public Object exec(List arg0) throws TemplateModelException {
		String posts_id = (String) arg0.get(0);
		if (posts_id == null){
			throw new TemplateModelException("文章ID号未定义.have_comments指令用法参考:have_comments(${dataMap.posts_id}) ");
		}
		
		DOService service = DOService.getService("cms_comment_list_by_posts_id");
		BOInstance bo = new BOInstance();
		bo.putValue("comment_posts_id", posts_id);
		List l = service.invokeSelect(bo);
		if (l.size() > 0){
			return true;
		}else {
			
			return false;
		}
	}

}
