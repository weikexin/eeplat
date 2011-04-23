<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%

  request.getSession().removeAttribute("userInfo");
  if(!request.getSession().isNew()){
	  request.getSession().invalidate();
  }

 
%>
<script>
	  window.top.location="<%=request.getContextPath()%>/zfbx_manager/";
</script>