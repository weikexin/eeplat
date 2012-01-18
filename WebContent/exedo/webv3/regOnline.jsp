<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.login.OnlineManager"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%

	SessionContext context = (SessionContext) session
	.getAttribute("userInfo"); 

    System.out.println("Reg Online++++++++++++++++++++++++++++++++++++++++");
    if(context.getUser()!=null){
    	OnlineManager.putUser(context.getUser().getUid());
    }else{
    	System.out.println("Error==========================Session is null");
    }
%>
