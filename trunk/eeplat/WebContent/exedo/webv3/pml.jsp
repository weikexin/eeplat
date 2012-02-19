<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ page language="java" import="com.exedosoft.plat.SessionContext"%>
<%@ page language="java" import="com.exedosoft.plat.login.LoginMain" %>
<%@ page language="java" import="com.exedosoft.plat.bo.BOInstance" %>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	
%>
<%

  String pml = request.getParameter("pml");
  if(pml!=null){
	  
	  
		SessionContext context = (SessionContext) session
				.getAttribute("userInfo");
		if (null == session.getAttribute("userInfo")
				|| context.getUser() == null) {
			
			
			BOInstance biUser = new BOInstance();
			biUser.putValue("objuid", "cust_id");///第三方用户的ID
			biUser.putValue("name", "cust_name");////第三方用户的名称 
		//	biUser.putValue("otherpara", "cust_name");////ID和名称是最重要的，也可以增加其他的属性
			LoginMain.makeLogin(biUser, request);
		}
	    
		application.getRequestDispatcher("/" +
				pml + ".pml?isApp=true").forward(
				request, response);
		return;
  }
%>
