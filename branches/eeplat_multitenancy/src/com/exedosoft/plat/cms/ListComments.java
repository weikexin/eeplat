package com.exedosoft.plat.cms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author lenovo
 * 根据各种条件列出文章。暂时先这么处理了 ，以后需要优化
 */
public class ListComments extends CMSFreemarkerServlet implements TemplateMethodModel{
	
	private Map sessionMap;
	
	public ListComments(Map sessionMap){
		this.sessionMap = sessionMap ;
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void execute(Environment environment, Map map,
			TemplateModel[] atemplatemodel,
			TemplateDirectiveBody templatedirectivebody)
			throws TemplateException, IOException {
	
	}

	public Object exec(List list) throws TemplateModelException {
		
		List data = new ArrayList();
		String posts_id = (String) list.get(0);
		if(posts_id == null){
			throw new TemplateModelException("文章ID未定义");
		}
		
		DOService aService = DOService.getService("cms_comment_list_by_posts_id");
		BOInstance bo = new BOInstance();
		bo.putValue("comment_posts_id", posts_id);
		List l  = aService.invokeSelect(bo);
		for (int i = 0; i < l.size(); i++ ){
			bo = (BOInstance) l.get(i);
			data.add(bo.getMap());
		}
		return data;
	}

}
