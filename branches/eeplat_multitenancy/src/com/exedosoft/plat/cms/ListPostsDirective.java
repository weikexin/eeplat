package com.exedosoft.plat.cms;

import java.io.IOException;
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
import freemarker.template.TemplateModel;

/**
 * @author lenovo
 * 根据各种条件列出文章。暂时先这么处理了 ，以后需要优化
 */
public class ListPostsDirective extends CMSFreemarkerServlet implements TemplateDirectiveModel{
	
	private Map sessionMap;
	private HttpServletRequest request ;
	public ListPostsDirective(HttpServletRequest request,Map map){
		this.request = request;
		this.sessionMap = map;
	}
	
	public ListPostsDirective(){
		
	}
	public ListPostsDirective(Map map){
		this.sessionMap = map ;
	
	}
	

	public void execute(Environment environment, Map map,
			TemplateModel[] atemplatemodel,
			TemplateDirectiveBody templatedirectivebody)
			throws TemplateException, IOException {
		//整合参数
		BOInstance paramBO = new BOInstance();
		paramBO = ParameterPase.paseParams(map, sessionMap);
		
		Map data = new HashMap();
		TemplateModel mdoel ;
		String serviceName = "cms_posts_list";
		BOInstance bo = new BOInstance();
		//打开单独一篇文章的参数
		if (paramBO.getValue("page_id") != null){
			serviceName="cms_posts_browse" ;
			bo.putValue("cms_posts_currunt", paramBO.getValue("page_id"));
		}
		//单独的分类
		if (paramBO.getValue("cat_id") != null){
			serviceName = "cms_posts_list_by_catid";
			bo.putValue("posts_category", paramBO.getValue("cat_id"));
		}
		//列出最热（评论最多的文章）
		if (paramBO.getValue("order_by") != null){
			serviceName = "cms_posts_list_order_by_comments";
		}
		DOService aService = DOService.getService(serviceName);
		List l = aService.invokeSelect(bo);
		
		for (int i =0 ;i < l.size(); i++){
			
			bo = (BOInstance) l.get(i);
			data.put("posts_title", bo.getValue("posts_title") );
			data.put("posts_author", bo.getValue("posts_author") );
			data.put("posts_modified_date", bo.getValue("posts_modified_date") );
			data.put("posts_category", bo.getValue("posts_category") );
			data.put("posts_url", "?page_id=" + bo.getValue("posts_id"));
			data.put("posts_except", bo.getValue("posts_except") );
			data.put("posts_content", bo.getValue("posts_content") );
			data.put("posts_date", bo.getValue("posts_modified_date") );
			data.put("posts_id", bo.getValue("posts_id") );
			//需要把HashMap用包装器包装成TemplateModel 
			mdoel = environment.getConfiguration().getObjectWrapper().wrap(data);
			environment.setGlobalVariable("dataMap",  mdoel);
			templatedirectivebody.render(environment.getOut());
		}
		
	}

}
