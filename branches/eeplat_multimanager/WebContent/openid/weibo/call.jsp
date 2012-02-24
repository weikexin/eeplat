<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="weibo4j.*" %>
<%@ page language="java" import="weibo4j.http.*" %>
<%@ page language="java" import="com.eeplat.social.openapi.callback.GlobalConfig" %>


<%

////改变所用的jslib
if("true".equals(request.getParameter("mobileclient"))){
	session.setAttribute("mobileclient", "true");
}

	Weibo weibo = new Weibo();
	RequestToken resToken = weibo.getOAuthRequestToken(GlobalConfig.getCallBack("weibo.cb"));
	if(resToken!=null){

		session.setAttribute("resToken",resToken);
		response.sendRedirect(resToken.getAuthorizationURL());

	}else{
		out.println("request error");
	}
%>
