package com.exedosoft.plat.cms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.lib.Iterator;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.sun.org.apache.xpath.internal.operations.Lt;


import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author lenovo
 * 核心类，继承freemarker.ext.servlet.FreemarkerServlet
 * 覆盖pretemplateProcess类，在生成页面之前准备数据
 * 
 */
public class CMSFreemarkerServlet extends FreemarkerServlet{
	

	
	boolean result ;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map map = new HashMap();
		map.put("redirect_url", "http://127.0.0.1:8080/yiyi/exedo/webv3/template/cms/index.ftl");
		request.getSession().setAttribute("optionsMap", map);
		super.doGet(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
	
	/* (non-Javadoc)
	 * @see
	 *  freemarker.ext.servlet.FreemarkerServlet#preTemplateProcess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, freemarker.template.Template, freemarker.template.TemplateModel)
	 *  
	 * 
	 */
	public boolean preTemplateProcess(HttpServletRequest request,
			HttpServletResponse response, Template template,
			TemplateModel templatemodel) throws ServletException, IOException {
		
		//根据文件名判断是否为评论
		//这个办法实在是土鳖，为了年前赶时间先弄个能用的东西善始善终先这么着吧
		//TODO:改了这个土鳖方法
		String templateName = processTemplateFileName(template.getName());
		if (templateName.equals("/cms-comments-post.ftl")){
			PostComments.commitComment(request);
		}
		String action = request.getParameter("action");
		if (action != null && action.equals("login")){
			 result = Login.processUserLogin(request);
			
		}else if (action != null && action.equals("logoff")){
			Login.processUserLogOff(request);
		}
		//主页入口 
		request.getSession().setAttribute("load_content", new LoadHtmlContent(request, response));
		//列分类
		request.getSession().setAttribute("list_category", new ListCategoryDirective(request.getParameterMap()));
		//列文章
		request.getSession().setAttribute("list_posts", new ListPostsDirective(request,request.getParameterMap()));
		//列评论
		request.getSession().setAttribute("get_comments", new ListComments(request.getParameterMap()));
		//是否有文章
		request.getSession().setAttribute("have_posts", new HavePosts());
		//站点全局信息
		request.getSession().setAttribute("bloginfo", new BlogInfo());
		//用户登录信息
		request.getSession().setAttribute("is_userlogin", new ISUserLogin());
		//获取全局变量 options表
		request.getSession().setAttribute("get_options", new GetOptionsData());
		//根据ID获取分类名称
		request.getSession().setAttribute("get_category_name", new GetCategoryName(request ));
		
		
		request.getSession().setAttribute("have_comments", new HaveComments());
		
		request.getSession().setAttribute("get_user_info", new GetUserInfo());
		
		request.getSession().setAttribute("rootPath", DOGlobals.PRE_FULL_FOLDER);
		return true;
	}
	
	private String processTemplateFileName(String tpName){
		return tpName.substring(tpName.lastIndexOf("/"), tpName.length());
	}



}
