package com.exedosoft.plat.cms;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import freemarker.ext.servlet.FreemarkerServlet;

/**
 * @author lenovo
 *  配置FREEMARKER公共常量
 */
public class CmsSetting {
	
	public static void cmsSetting(ServletContextEvent evt){
			@SuppressWarnings("unused")
			ServletContextEvent servlet =  (ServletContextEvent) evt.getServletContext();
			System.out.println(servlet);
		
	}

}
