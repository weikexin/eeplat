package com.exedosoft.plat;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedosoft.plat.util.DOGlobals;


public class DOFilter implements javax.servlet.Filter {

	public void destroy() {
	//	ExecutorManager.shutdown();

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {

		DOGlobals.getInstance().refreshContext(
				
				new DOServletContext((HttpServletRequest) request,
						(HttpServletResponse) response));
		
		DOGlobals.getInstance().getSessoinContext().getThreadContext().initDataBus();
		
		arg2.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		
	//	ExecutorManager.initPool();
		
		// TODO Auto-generated method stub

	}

}
