<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>


<%
   DOService theService = DOService.getService("multi_tenancy_list");
   List datas = theService.invokeSelect();
   

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>预付卡管理系统登录</title>

<script language="javascript">
  globalURL = "/<%=DOGlobals.URL%>/";
</script>  

<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery.js" ></script>
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/form/jquery.form.js" ></script>	
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js" ></script>

<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/login.css" type="text/css" />

<style type="text/css">
<!--
#numImg{ cursor:pointer}
.btn{
	width:40px;
	height:22px;
	border:0px;
	cursor:pointer
}
-->
</style>
</head>
<body class="login">
<div id="login">

<h1 style="margin-left:20px"><a  title="云鹤平台"><img border=0 height=61px width=284px src="<%=request.getContextPath()%>/exedo/webv3/images/eeplat.png"/></a></h1>


<br/>
<form name="loginform" id="loginform"  method="post">
	<p>
		<label>企业（公司）</label> <br />
		
		 <select class="input"  id="company"  name="tenancy_uid">
		 <% for(Iterator it = datas.iterator(); it.hasNext();){
			 BOInstance  bi = (BOInstance)it.next();
			 %>
			 <option value="<%=bi.getUid()%>"> <%=bi.getName()%> </option>
			 
		 <%} %>
		 
		 </select>
	</p>
	<p>
		<label>用户名 </label> <br />
		<input type="text" name="name" id="name" class="input" value="" size="20" tabindex="10" />
	</p>
	<p>
		<label>密码 </label><br />
		<input type="password" name="password" id="password" class="input" value="" size="20" tabindex="20" />
	</p>
	<p class="forgetmenot">
	
	     <label>验证码</label>
       <input type="text" name="randcode"  style="height:16px; width:40px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
        <img src='<%=request.getContextPath()%>/exedo/webv3/image.jsp' height="18px"  border=0 id="numImg" title="看不清，换一张!" >
	
	
	
	</p>
	<p class="submit">
		<img src="<%=request.getContextPath()%>/exedo/webv3/images/login/dl.gif" class="btn"><img src="<%=request.getContextPath()%>/exedo/webv3/images/login/cz.gif" class="btn">
	</p>
</form>

<div>
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


	   var paras =  $('#loginform').formSerialize();
	   paras = paras + "&contextServiceName=do_org_user_findbynameandpwd"
	   $.post(globalURL + "ssocontroller",paras,
			function (data, textStatus){
			   var retValue = unescape(data.returnValue);
			
			   if('success'==retValue){
			        window.location= globalURL + "abp_base_pane.pml?isApp=true";
			   }else{
			   		
				   	alert(retValue);
				   	imgChange($("#numImg"));
			   }
				
	  },"json");
	  
  }
</script>
</html>
