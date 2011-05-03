<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%
	String paneModelContent = (String) request
			.getAttribute("paneModelContent");
%>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
		<title>云鹤平台应用</title>
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile-1.0a4.1.min.css" />
		<script language="javascript">
		  globalURL = "/<%=DOGlobals.URL%>/";
		</script>  
		<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/mobile/js/jquery-1.5.2.min.js" ></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile-1.0a4.1.min.js" ></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/platAjax.js"  ></script>
   			
    </head>
	 <title>云鹤平台应用登录/title>
	</head>
<body>

 <%=paneModelContent%>
	
</body>
</html>
