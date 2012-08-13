<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=I18n.instance().get("Welcome Login")%></title>

<script language="javascript">
  globalURL = "/<%=DOGlobals.URL%>/";
</script>  

<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js" ></script>
<% if ("en".equals(DOGlobals.getValue("lang.local"))){ %>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/lang_en.js"  ></script>
<% }else{ %>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/lang_zh.js"  ></script>
<% }%>

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

img{ vertical-align:middle;}

a { pointer: cursor;  	text-decoration:	none;}

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
		<label><%=I18n.instance().get("UserName")%> </label> <br />
		<input type="text" name="name" id="name" class="input"  size="20" tabindex="10" value="demo"/></label>
	</p>
	<p>
		<label><%=I18n.instance().get("Paasword")%></label><br />
		<input type="password" name="password" id="password" class="input"  size="20" tabindex="20" value="1"/></label>
	</p>

	<p>
		<label><%=I18n.instance().get("Tenant")%>  (badminton,openclub,Recruiting to test)</label><br />
		<input type="tenancyId" name="tenancyId" id="tenancyId" class="input"  size="20" tabindex="20" value="Recruiting"/></label>
	</p>
	
	<p class="forgetmenot">
	
	     <label><%=I18n.instance().get("Verification")%></label>
       <input type="text" name="randcode"  style="height:18px; width:40px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <img src='<%=request.getContextPath()%>/exedo/webv3/image.jsp' height="18px"  border=0 id="numImg" title="看不清，换一张!" />

	<br/><br/>	
	
	
  
	<p class="submit">
		<div style="float:left;width:30px;heigth:15px">&nbsp;</div>
		<div class="buttons"  style="margin-top:10px;">
			<a  class="btn">
				<img src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/button/style/icons/apply.png" alt=""/>
				<%=I18n.instance().get("Sign In")%>
			</a>
		</div>	
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
 
  	  		submitForm();
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

 		var userName = $("input:eq(0)").val();
	  		var passWord = $("input:eq(1)").val();
	  		var randCode = $("input:eq(2)").val();
		if(userName==""){
			alert(EELang.accountRequired);
			return;
		}
		if(passWord==""){
			alert(EELang.pwdRequired);
			return;
		}
		if(randCode==""){
			alert(EELang.verificationRequired);
			return;
		}

	   loading(EELang.loading);

	   var paras =  $('#loginform').serialize();

	   paras = paras + "&contextServiceName=do_org_user_findbynameandpwd"

	   $.post(globalURL + "ssocontroller",paras,

			function (data, textStatus){

			   var retValue = unescape(data.returnValue);

			

			   if('success'==retValue){
				   if(data.returnPath!=null && $.trim(data.returnPath)!=''){
					   window.location= unescape(data.returnPath);
					 }else{
						 window.location= globalURL + "pane_jyhd.pml?isApp=true";//pane_wolfvillage
					 }
			   }else if('delegate'==retValue){
			        window.location= globalURL + "PM_do_org_user_delegate_index.pml?isApp=true&pml=pane_jyhd";
			   }
			   else{
				  	alert(retValue);
				   	imgChange($("#numImg"));
				   	closeWin();
			   }
	  },"json");
  }
</script>
</html>
