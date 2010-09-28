<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理平台登录</title>
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery.js" ></script>
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/form/jquery.form.js" ></script>	
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js" ></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/estop/estop.css" type="text/css" />


<style type="text/css">
<!--
html,body,div{overflow:hidden;font-size: 12px;}
body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px}
.STYLE3 {color: #528311;font-size: 12px; }
.STYLE4 {color: #42870a;font-size: 12px;}
#numImg{ cursor:pointer}
.btn{
	width:40px;
	height:22px;
	border:0px;
	cursor:pointer
}
-->
</style>
<script language="javascript">
//开启遮罩
function loading(){
	showMainMsg("body",90,14,"center","loading","","n");
}
//关闭遮罩

function loadClose(){
	closeWin();
}
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
  		$(obj).attr("src","image.jsp?"+ Math.random());
  }
  //登录
  function submitForm(){
  	 
	   loading();
  

	   var paras =  $('#loginid').formSerialize();
	   paras = paras + "&contextServiceName=do_org_account_findbynameAndPassword"
	   $.post(globalURL + "ssocontroller",paras,
			function (data, textStatus){
			   var retValue = unescape(data.returnValue);
			
			   if('success'==retValue){
			        window.location= globalURL + "abp_base_pane.pml?isApp=true";
			   }else if('delegate'==retValue){
			        window.location= globalURL + "PM_do_org_user_delegate_index.pml?isApp=true";
			   }
			   else{
			   		
				   	alert(retValue);
				   	imgChange($("#numImg"));
				   	closeWin();
			   }
				
	  },"json");
	  
  }
</script>
</head>
<body>
<form id="loginid" name="loginid" method="post" >

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf"></td>
  </tr>
  <tr>
    <td height="100%" background="images/login/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="266" background="images/login/login_04.gif"></td>
        </tr>
        <tr>
          <td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="424" height="95" background="images/login/login_06.gif"></td>
                <td width="183" background="images/login/login_07.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="21%" height="30"><div align="center"><span class="STYLE3">用户</span></div></td>
                      <td width="79%" height="30" colspan="2"><input type="text" name="name"  style="height:16px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
                    </tr>
                    <tr>
                      <td height="30"><div align="center"><span class="STYLE3">密码</span></div></td>
                      <td height="30" colspan="2"><input type="password" name="password"  style="height:16px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
                    </tr>
                    <tr>
                      <td height="30"><div align="center"><nobar><span class="STYLE3">验证码</span><nobar></div></td>
                      <td height="30"><input type="text" name="randcode"  style="height:16px; width:40px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
                      <td><img src='image.jsp' height="18px"  border=0 id="numImg" title="看不清，换一张!" ></td>
                    </tr>
                  </table></td>
                <td width="255" background="images/login/login_08.gif"></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="247" valign="top" background="images/login/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="424" height="30"></td>
                <td width="183" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="21%" height="30"></td>
                      <td width="79%" height="30"><img src="images/login/dl.gif" class="btn"><img src="images/login/cz.gif" class="btn"></td>
					  
                    </tr>
                  </table></td>
                <td width="255" ></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> </tr>
              <tr>
                <td></td>
                <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="46%" height="20"></td>
                      <td width="54%" class="STYLE4">版本 2010 V5.0 </td>
                    </tr>
                  </table></td>
                <td></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td bgcolor="#a2d962" >&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>