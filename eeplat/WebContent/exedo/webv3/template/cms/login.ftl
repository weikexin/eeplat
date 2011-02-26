<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 加入jstl标签 用于实现页面跳转的功能
		wanghongliang 2011-01-18
-->
<#assign  s=JspTaglibs["/WEB-INF/c.tld"]>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="UTF-8">
<head>
	<title>${bloginfo('name')} &rsaquo; 登录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel='stylesheet' id='login-css'  href='css/login.css?ver=20100601' type='text/css' media='all' />
<link rel='stylesheet' id='colors-fresh-css'  href='css/colors-fresh.css?ver=20100610' type='text/css' media='all' />
<style type="text/css" media="screen">
    body { font: 13px "Lucida Grande",Verdana,Arial,"Bitstream Vera Sans",sans-serif,"新宋体","宋体"; }
    .submit input, .button, input.button, .button-primary, input.button-primary, .button-secondary, input.button-secondary, .button-highlighted, input.button-highlighted, #postcustomstuff .submit input { font-size: 12px !important; }
</style>
<meta name='robots' content='noindex,nofollow' />
</head>
<body class="login">
<div id="login"><h1><a href="http://wordpress.org/" title="">whl blog</a></h1>
<#if (get_user_info().userinfo.login_result)?exists >
	<#if "${get_user_info().userinfo.login_result}"=="success">
		<@s.redirect url="${optionsMap.redirect_url}"/>
	<#else>
		<p class="message">	${get_user_info().userinfo.login_result}</p>
	</#if>
</#if>
<form name="loginform" id="loginform" action="${bloginfo('site_url')}/login.ftl?action=login" method="post">
	<p>
		<label>用户名<br />
		<input type="text" name="log" id="user_login" class="input" value="" size="20" tabindex="10" /></label>
	</p>
	<p>
		<label>密码<br />
		<input type="password" name="pwd" id="user_pass" class="input" value="" size="20" tabindex="20" /></label>

	</p>
	<p class="forgetmenot"><label><input name="rememberme" type="checkbox" id="rememberme" value="forever" tabindex="90" /> 记住我</label></p>
	<p class="submit">
		<input type="submit" name="wp-submit" id="wp-submit" class="button-primary" value="登录" tabindex="100" />
		<input type="hidden" name="redirect_to" value="http://localhost/wordpress/wp-admin/" />
		<input type="hidden" name="testcookie" value="1" />
	</p>
</form>

<p id="nav">
<a href="http://localhost/wordpress/wp-login.php?action=lostpassword" title="忘记密码">忘记密码?</a>
</p>
</div>

<script type="text/javascript">
function wp_attempt_focus(){
setTimeout( function(){ try{
d = document.getElementById('user_login');
d.value = '';
d.focus();
} catch(e){}
}, 200);
}

wp_attempt_focus();
if(typeof wpOnload=='function')wpOnload();
</script>
</body>
</html>
