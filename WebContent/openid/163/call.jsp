<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="t4j.*" %>
<%@ page language="java" import="t4j.http.*" %>
<%@ page language="java" import=" com.eeplat.social.openapi.t163.RequestTokenExtend" %>
<%@ page language="java" import="com.eeplat.social.openapi.callback.GlobalConfig" %>

<%

    TBlog tblog = new TBlog();
	RequestToken resToken=tblog.getOAuthRequestToken();
	if(resToken!=null){

		session.setAttribute("resToken",resToken);
		RequestTokenExtend  rte =  new RequestTokenExtend(resToken);
		String url = rte.getAuthenticationURL(GlobalConfig.getCallBack("t163.cb"));
	    response.sendRedirect(url);

	}else{
		out.println("request error");
	}
%>