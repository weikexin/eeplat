package com.exedosoft.plat.cms;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIViewTemplate;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

public class Test extends DOViewTemplate implements DOIViewTemplate{
	
	public Test(){
		this.templateFile = "cms/theme/test/index.ftl";
	}
	@SuppressWarnings("unchecked")
	
	@Override
	public Map<String, Object> putData(DOIModel fm) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list_category", new ListCategoryDirective());
		data.put("list_posts", new ListPostsDirective());
		data.put("rootPath", DOGlobals.PRE_FULL_FOLDER);
	
		
		return data;
	}
	
	public boolean preTemplateProcess(HttpServletRequest request,
			HttpServletResponse response, Template template,
			TemplateModel templatemodel) throws ServletException, IOException {

		request.getSession().setAttribute("list_category", new ListCategoryDirective(request.getParameterMap()));
	//	request.getSession().setAttribute("list_posts", new ListPostsDirective(request));
		request.getSession().setAttribute("rootPath", DOGlobals.PRE_FULL_FOLDER);
		return true;
	}

}
