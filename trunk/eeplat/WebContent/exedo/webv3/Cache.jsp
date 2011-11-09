<%@ page contentType="text/html;charset=UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@page import="com.exedosoft.plat.CacheFactory"%>
<% 
CacheFactory.getCacheData().cacheAllConfigData();
out.println("缓存成功！");
%>