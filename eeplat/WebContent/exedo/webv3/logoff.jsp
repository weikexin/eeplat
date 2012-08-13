<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.login.OnlineManager"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
	SessionContext context = (SessionContext) session
			.getAttribute("userInfo");
	if (context.getUser() != null) {
		OnlineManager.removeUser(context.getUser().getUid());
	}

	request.getSession().removeAttribute("userInfo");
	if (!request.getSession().isNew()) {
		request.getSession().invalidate();

	}
	//response.sendRedirect("/wh/exedo/webv3/");
%>
<script>
 <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
 	  window.top.location="<%=request.getContextPath()%>/index/"  ;
 <% } else {%>	  
	 window.top.location="<%=request.getContextPath()%>/exedo/webv3/"  ;
 <% } %>	  
</script>