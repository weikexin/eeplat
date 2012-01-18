<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理平台登录</title>

<script language="javascript">
  globalURL = "/<%=DOGlobals.URL%>/";
</script>  

<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js" ></script>

<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/login.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/estop/estop.css" type="text/css" />

<style type="text/css">
<!--
#numImg{ cursor:pointer}
html,body{
 overflow:hidden;
}
-->
</style>
</head>

<body>

<div id="login">

<div align="center" style="margin:7em 7em 0 7em" > <img border=0   src="<%=request.getContextPath()%>/exedo/webv3/images/logo.png"/>
</div>
<br/>


<form name="loginform" id="loginform"  method="post">
	<p>
		<label>用户名 </label> <br />
		<input type="text" name="name" id="name" class="input"  size="20" tabindex="10" /></label>
	</p>
	<p>
		<label>密码 </label><br />
		<input type="password" name="password" id="password" class="input"  size="20" tabindex="20" /></label>
	</p>
	<p class="forgetmenot">
	
	     <label>验证码</label>
       <input type="text" name="randcode"  style="height:18px; width:40px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <img src='<%=request.getContextPath()%>/exedo/webv3/image.jsp' height="18px"  border=0 id="numImg" title="看不清，换一张!" />
	
		
	</p>
	<p class="submit">
			<img src="<%=request.getContextPath()%>/exedo/webv3/images/login/btn_sign-in.png" class="btn"/>
    </p>
</form>

</div>
</body>
<script language="javascript">



$(function(){
//回车事件

  $(document).keypress(function (e) {
		     var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
		     if(keyCode==13){
		        submitForm();
		     }
		})
	 }

	);

  $(function(){
	  
	  $("body").css("height",$(window).height());
  	  $(".btn:first").bind("click",function(){
  	  		var userName = $("input:eq(0)").val();
  	  		var passWord = $("input:eq(1)").val();
  	  		var randCode = $("input:eq(2)").val();
			if(userName==""){
				alert("请填写用户名!");
				return;
			}
			if(passWord==""){
				alert("请填写密码!");
				return;
			}
			if(randCode==""){
				alert("请填写验证码!");
				return;
			}
  	  		submitForm();
  	  })
  	  
  	  $(".btn:last").bind("click",function(){
  	  		$("input:eq(0)").val("");
  	  		$("input:eq(1)").val("");
  	  		$("input:eq(2)").val("");
  	  })
  });
  $(document).ready(function(){
  		$("#numImg").bind("click",function(){
      			imgChange(this);
		})
  })
  
  //换验证码
  function imgChange(obj){

  		$(obj).attr("src","<%=request.getContextPath()%>/exedo/webv3/image.jsp?"+ Math.random());

  }

 
  //登录

  function submitForm(){

  	 

	   loading("登录中，请稍后......");

	   var paras =  $('#loginform').serialize();

	   paras = paras + "&contextServiceName=do_org_user_findbynameandpwd"

	   $.post(globalURL + "ssocontroller",paras,

			function (data, textStatus){

			   var retValue = unescape(data.returnValue);

			

			   if('success'==retValue){
				   if(data.returnPath!=null && $.trim(data.returnPath)!=''){
					   window.location= unescape(data.returnPath);;
					 }else{
			        	window.location= globalURL + "pane_jyhd.pml?isApp=true";
					 }
			   }else{
				  	alert(retValue);
				   	imgChange($("#numImg"));
				   	closeWin();
			   }
	  },"json");
  }
</script>
</html>
