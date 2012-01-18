<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="com.eeplat.social.openapi.qq.URIUtil" %>
<%
	response.sendRedirect(URIUtil.wrappRedirectUri());
%>