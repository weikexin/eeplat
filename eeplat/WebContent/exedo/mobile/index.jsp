<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
        <title>EEPlat Login</title>
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/jquery.mobile.css" />
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/openid.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile.js" ></script>
		<script language="javascript">
		  globalURL = "/<%=DOGlobals.URL%>/";
		</script>  
		

    </head>
	<body>
	    
	<div data-role="page" data-theme="b">
	 
	    <div data-role="header" data-position="inline"  data-nobackbtn="true" data-theme="b">
	        <h1>EEPlat Login</h1>
	    </div>
	 
	    <div data-role="content" data-theme="c">
	        <form  id="loginform" method="get">
	           <div data-role="fieldcontain">
		            <label for="username">用户名:</label>
	                <input type="text" name="name" id="username" value=""  />
               </div>
				<div data-role="fieldcontain">
				    <label for="password">密码:</label>
				    <input type="password" name="password" id="password" value="" />
				</div>	
   
	            <a id="asub" href="#" data-role="button" data-inline="true" data-theme="b">确定</a>
	             
	        </form>
	 
	    </div>
	 
	</div>
	</body>
	<script language="javascript">
	
	  $(function(){
	  	  $("#asub").bind("click",function(){
	  	  		var userName = $("input:eq(0)").val();
	  	  		var passWord = $("input:eq(1)").val();
				if(userName==""){
					alert("请填写用户名!");
					return;
				}
				if(passWord==""){
					alert("请填写密码!");
					return;
				}
	  	  		submitForm();
	  	  })
	  });
	
	  //登录
	  function submitForm(){
		   var paras =  $('#loginform').serialize();
		   paras = paras + "&contextServiceName=do_org_user_findbynameandpwd&mobileclient=true";
		   $.ajax({
			   url: globalURL + "ssocontroller",
			   data: paras,
			   dataType:"json",
			   success: function(data){
				   var retValue = unescape(data.returnValue);
				   if('success'==retValue){
				       // window.location= "AppList.jsp";
					   window.location= globalURL + "jyhd_mobile_pane.pml";
				   }else{
					   	alert(retValue);
				   }
				 }
			 });
		   

	  }
</script>
	</html>