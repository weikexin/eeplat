<%@ page contentType="text/html;charset=UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@page import="com.exedosoft.plat.CacheFactory"%>
<%@page import="com.exedosoft.plat.cache.CacheDataMap"%>
<% 
CacheFactory.getCacheData().cacheAllConfigData();
((CacheDataMap)CacheFactory.getCacheData()).cacheRelations();
out.println("缓存成功！");
%>