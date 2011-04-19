<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
		<title>jQuery Mobile: Demos and Documentation</title>
		<link rel="stylesheet"  href="http://code.jquery.com/mobile/1.0a4.1/jquery.mobile-1.0a4.1.min.css" />
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.5.min.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/mobile/1.0a4.1/jquery.mobile-1.0a4.1.min.js"></script>
    </head>
	 <title>云鹤平台应用登录/title>
	</head>
	<body>
	    
	<div data-role="page" data-theme="b">
	 
	    <div data-role="header" data-position="inline"  data-nobackbtn="true">
	        <h1>云鹤平台应用登录</h1>
	    </div>
	 
	    <div data-role="content" data-theme="c" data-inset="true">
	 
	        <form  method="get">
	             
	            <fieldset>
	                 
	                <label for="username">用户名:</label>
	                <input type="text" name="username" id="username" value=""  />
	 
	 
	                <label for="password">密码:</label>
	                <input type="password" name="password" id="password" value="" />

	 
	                 <a href="index.html" data-role="button" data-inline="true" data-theme="b">确定</a>
	 
	  	 
	            </fieldset>
	             
	        </form>
	 
	    </div>
	 
	</div>
	</body>
	</html>