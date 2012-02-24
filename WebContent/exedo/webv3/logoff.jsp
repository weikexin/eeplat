<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js"></script>
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

      if(isMobile()){
		  window.top.location="<%=request.getContextPath()%>/m.jsp"  ;
      }else{
		  window.top.location="<%=request.getContextPath()%>/"  ;
      }
</script>