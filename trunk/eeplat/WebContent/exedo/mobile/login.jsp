<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
        <title>EEPlat PaaS Login</title>
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/jquery.mobile.css" />
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/openid.css" />

		<script language="javascript">
		  globalURL = "/<%=DOGlobals.URL%>/";
		</script>  
		<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile.js" ></script>
    </head>
	<body>
	    
	<div data-role="page" data-theme="b">
	 
	    <div data-role="header" data-position="inline"  data-nobackbtn="true" data-theme="b">
	        <h1>EEPlat PaaS Login</h1>
	    </div>
	 
	    <div data-role="content" data-theme="c">
		
			<a   href="<%=request.getContextPath()%>/openid/weibo/call.jsp?mobileclient=true" data-role="button" rel="external" data-icon="openid-weibo">新浪微博登录</a> 
		
			<a href="index.html" data-role="button" data-icon="openid-qq">QQ账号登录</a> 
		
			<a href="index.html" data-role="button" data-icon="openid-renren">人人账号登录</a> 
			
	  	    <a href="index.html" data-role="button" data-icon="openid-163">网易账号登录</a> 

	    </div>
	</div>
	</body>
	</html>