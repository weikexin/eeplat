<%@ page contentType="text/html;charset=UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@page import="com.exedosoft.plat.cache.busi.BusiCache"%>
<% 
BusiCache.flushAll();
//CacheFactory.getCacheData().fromSerialObject();
out.println("清除数据缓存成功！");
%>