<%
////在Mobile下面这个页面没用了都是走的exedo.jsp
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>

<%
////在Mobile下面这个页面没用了都是走的exedo.jsp
			String paneModelContent = (String) request
			.getAttribute("paneModelContent");
////在Mobile下面这个页面没用了都是走的exedo.jsp
			SessionContext context = (SessionContext)session.getAttribute("userInfo");
			if(null==session.getAttribute("userInfo") ||  context.getUser()==null){
			 response.sendRedirect(request.getContextPath() + "/exedo/webv3/logoff.jsp");
			}
%>
<%=paneModelContent%>
<%
System.out.println(paneModelContent);
System.out.println("Out Over:::::" + System.currentTimeMillis());
%>






