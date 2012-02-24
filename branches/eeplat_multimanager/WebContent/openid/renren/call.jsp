<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="com.eeplat.social.openapi.renren.URIUtil" %>
<%

////改变所用的jslib
if("true".equals(request.getParameter("mobileclient"))){
	session.setAttribute("mobileclient", "true");
}

		response.sendRedirect(URIUtil.wrappRedirectUri());
%>