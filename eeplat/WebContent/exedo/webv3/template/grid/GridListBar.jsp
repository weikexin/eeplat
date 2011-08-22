<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.ui.jquery.grid.GridListBar"%>
<%@ page import="com.exedosoft.plat.ui.DOGridModel"%>
<%@ page import="com.exedosoft.plat.bo.DOBO"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%

    String gridModelUid = request.getParameter("gridModelUid");
    String contextKey = request.getParameter("contextKey");
    String contextValue = request.getParameter("contextValue");
    String contextNIUid = request.getParameter("contextNIUid");
    String contextPIUid = request.getParameter("contextPIUid");

    
    if(gridModelUid == null || contextKey==null || contextValue==null){
    	out.print("");
    	return ;
    }
    
    DOBO aBO = DOBO.getDOBOByName(contextKey);
    if(aBO==null){
    	out.print("");
    	return ;
    }
    
    BOInstance instance = aBO.getInstance(contextValue);
    
    if(instance==null){
    	out.print("");
    	return ;
    }
    
    
    if(contextNIUid!=null){
    	instance.putValue("contextNIUid",contextNIUid);
    }
    
    if(contextPIUid!=null){
    	instance.putValue("contextPIUid",contextPIUid);
    }
    
    DOGridModel gm = DOGridModel.getGridModelByID(gridModelUid);
    
    if(gm == null){
    	out.print("");
    	return ;
    }
    
    GridListBar glb = new GridListBar(instance);
    String htmlCode = glb.getHtmlCode(gm);
    
    out.println(htmlCode);

%>
