package com.exedosoft.plat.cms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author lenovo
 * 
 *  根据当前配置的主题返回html内容 
 *  为达到URL地址不变的情况下更换主题的目的，使用了跳转。
 *
 */
public class LoadHtmlContent implements TemplateDirectiveModel{
	
	HttpServletRequest request ;
	HttpServletResponse response ;
	public LoadHtmlContent(HttpServletRequest request ,HttpServletResponse response){
		this.request = request ;
		this.response = response ;
	}
	

	public void execute(Environment environment, Map map,
			TemplateModel[] atemplatemodel,
			TemplateDirectiveBody templatedirectivebody)
			throws TemplateException, IOException {
		String templatePath = SessionAttribute.getInstance().getCurrentTheme();
		String templateFileName = "index.ftl" ;
		
		//获取参数
		BOInstance params = ParameterPase.paseParams(map, request.getParameterMap());
		
		if (params.getValue("page_id") != null){
			
			templateFileName = "single.ftl";
		}
		
		if (params.getValue("cat_id") != null){
			templateFileName = "cat_detail.ftl";
		}
		
		try {
			request.getRequestDispatcher("theme/" + templatePath + "/" +   templateFileName).forward(request,response) ;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
