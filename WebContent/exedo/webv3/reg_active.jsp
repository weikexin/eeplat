<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="com.exedosoft.plat.bo.DOBO"%>
<% 

  DOBO tenantBO = DOBO.getDOBOByName("multi_tenancy");
  String tenantId = request.getParameter("tenantId");
  if(tenantId!=null){
	  tenantBO.refreshContext(tenantId);
	  DOService activeS = DOService.getService("multi_tenancy_generator");
	  activeS.invokeAll();
  }
  
  out.println("账号被成功激活，请<a href='http://www.eeplat.com/'>登录</a>。");


%>