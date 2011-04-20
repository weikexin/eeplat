package com.zephyr.erpscan.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.zephyr.erpscan.framework.util.IConstants;
import com.zephyr.erpscan.object.User;

/**
 * 
 * @author t
 *
 */
public class CheckLoginUserFilter extends HttpServlet implements Filter {
	
	Logger log = Logger.getLogger(CheckLoginUserFilter.class);

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		
		HttpSession session = hRequest.getSession();
		
		User user = (User) session.getAttribute(IConstants.SESSION_USER);
		
		String uri = hRequest.getRequestURI();
		
		log.debug("uri:"+uri);
		
		if(uri.indexOf("login")!=-1 || uri.indexOf("logout")!=-1
				|| uri.indexOf(".gif")!=-1 || uri.indexOf(".css")!=-1
				|| uri.indexOf(".jpg")!=-1
				|| uri.indexOf("OutputPic.jpg")!=-1
				|| uri.indexOf("display.do")!=-1
				|| uri.indexOf("print.jsp")!=-1
				|| uri.indexOf("printt.jsp")!=-1
				|| uri.indexOf("print1.jsp")!=-1
				|| uri.indexOf("testdb.jsp")!=-1
				|| uri.indexOf("bussinessFlow.swf")!=-1
				|| uri.indexOf("bussinessFlow.html")!=-1
				|| uri.indexOf("writeoffCash.swf")!=-1
				|| uri.indexOf("writeoffCash.html")!=-1
				|| uri.indexOf("code.jsp")!=-1
				|| uri.indexOf("callout.jsp")!=-1
		   ){
		//log.info("do login or logout");
		}
		// check login user here
		else if(user==null || user.getName()==null){
			log.info("user session timeout.");
			hResponse.sendRedirect("./login.jsp");
			return;
		}
		
	
		
		chain.doFilter(request, response);
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is a filter servlet for ERPScan project";
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
