<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.exedosoft.plat.bo.DOApplication"%>
<%@page import="com.exedosoft.plat.bo.DOService"%>
<%@page import="com.exedosoft.plat.bo.BOInstance"%>
<%@page import="com.exedosoft.plat.util.DOGlobals"%>
<%@page import="com.exedosoft.plat.DAOUtil"%>
<%@page import="java.util.Iterator"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>


<%
			SessionContext context = (SessionContext)session.getAttribute("userInfo");
			if(null==session.getAttribute("userInfo") ||  context.getUser()==null){
			 response.sendRedirect(request.getContextPath() + "/exedo/webv3/logoff.jsp");
			}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1" />
<title>请选择您要进入的工程</title>

<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/jquery.mobile.css" />
<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/openid.css" />
<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/jquery.mobile.datebox.min.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile.datebox.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile.datebox.i18n.zh-CN.utf8.js" ></script>

<script language="javascript">
		  globalURL = "/<%=DOGlobals.URL%>/";
</script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/exedo/mobile/js/platAjax.js"></script>

<!--Other Scripts  -->
<body>
<%

   List appList =  DAOUtil.INSTANCE().select(DOApplication.class,
		"select * from DO_Application");

	if (appList.size() == 0) {
		out
				.println("<script language='javascript'>window.location.href='login.jsp';</script>");//如果没有子系统则直接返回到登陆页面 
		return;
	} else if (appList.size() == 1) {

		DOApplication aApp = (DOApplication) appList.get(0);
		response.sendRedirect(aApp.getOuterLinkPaneStr());//如果只有一个系统则直接进入
		return;
	}
%>
<div data-role="page"  id="appList" name="appList">

	<div data-role="header" data-theme="b">
		<h1>请选择要进入的工程</h1>
	</div>
  	<div data-role="content">
 	     <ul data-role='listview' >
     		<%
				for (Iterator it = appList.iterator(); it.hasNext();) {
			
					DOApplication anApp = (DOApplication) it.next();
			%> 
			        	        <li> <a href="<%=anApp.getMobilePaneStr()%>"> <%=anApp.getL10n()%> </a>  </li>
			 <%} %>
			
		</ul>
 	</div>   
</div>	
</body>
</html>
