<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>

<%
			String paneModelContent = (String) request
			.getAttribute("paneModelContent");
	

			SessionContext context = (SessionContext)session.getAttribute("userInfo");
			if(null==session.getAttribute("userInfo") ||  context.getUser()==null){
			 response.sendRedirect(request.getContextPath() + "/exedo/webv3/logoff.jsp");
			}
	
%>

<%=paneModelContent%>

<%
//System.out.println(paneModelContent);
System.out.println("Out Over:::::" + System.currentTimeMillis());
%>






