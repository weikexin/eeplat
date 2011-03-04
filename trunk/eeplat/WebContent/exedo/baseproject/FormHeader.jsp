<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<%	
	String userName = "";
	SessionContext exedoContext = (SessionContext) session
			.getAttribute("userInfo");
	if (exedoContext != null && exedoContext.getUser() != null) {
		userName = exedoContext.getUser().getName();
	}
%>
<style> 

.formHeader{
	float:right; margin-right:2px;text-align:right; height:60px; width:140px
}
.formHeader .themeLabel{
	text-align:center; display:none
}
.formHeader .userName{
	border:1px; border-style:solid;border-color:#666666; border-style:solid; background-color:#FFFFFF; margin-top:5px;; text-align:center; display:none
}
.formHeader img{
	  width:22px; height:22px;background-color:#FFFFFF;cursor:pointer;border-bottom:1;border-bottom-color:#ffffff;border-style:solid
}
.formHeader .themeLabel img{
	width:18px; height:18px; background-color:#FFFFFF;cursor:pointer; margin-top:4px;border:1;border-color:#ffffff;border-style:solid
}
</style>
<script language="javascript">
$(function() {
	
	var updateStyleService = "wh.employee.only_update_style";
	var updatePar = "notes";
	
	
	$(".formHeader img").hover(
		  function () {
		  	$(this).css("border-bottom-color","#333333");
		  },
		  function () {
			$(this).css("border-bottom-color","#ffffff");
		  }
	);

	$(".themeLabel img").hover(
		  function () {
		  	$(this).css("border-color","#333333");
		  },
		  function () {
			$(this).css("border-color","#ffffff");
		  }
	);
	$(".formHeader").hover(
		  function () {},
		  function () {
			$(".formHeader .themeLabel").hide();
			$(".formHeader .userName").hide();
		  }
	); 
	$(".formHeader img:eq(0)").hover(
		  function () {
		  	$(".formHeader .themeLabel").hide();
			$(".formHeader .userName").show();
		  },
		  function () {}
	); 
	
	$(".formHeader img:eq(1)").bind("click",function(){
		alert("消息");
	})
	
	$(".formHeader img:eq(2)").hover(
		  function () {
		  	$(".formHeader .themeLabel").show();
			$(".formHeader .userName").hide();
		  },
		  function () {}
	);
	$(".formHeader img:eq(3)").bind("click",function(){
		$.get("exedo/webv3/ClearCache.jsp",function(data){
			  alert("清除缓存成功!");
		});
	})
	$(".formHeader img:eq(4)").bind("click",function(){
		window.location.href="logoff.jsp";
	})
	
	$(".themeLabel img").bind("click",function(){
		var str = $(this).attr("value");
		alert("其它皮肤正在开发中...............");
//		var url = globalURL + "servicecontroller?contextServiceName="+updateStyleService+"&"+updatePar+"="+str+"&callType=uf&dataBus=setUserContext&contextKey=style&contextValue="+str;
//		$.get(url,function(){});
		
//		window.location.reload(); 
	})
	


})
</script>
<div algin="left" id='logoPic' style='z-index:1; height: 0; left: 0px; position: absolute; top: 1px; width: 0;'><img name='logoImg' height="40" src='<%=request.getContextPath()%>/exedo/webv3/images/logo_small_l.png' border='0'></div> 

<div class="formHeader">
	<img src="exedo/webv3/images/main/formHeader/user.png"/>
	<img src="exedo/webv3/images/main/formHeader/forward_all.png" title="查看消息"/>
	<img src="exedo/webv3/images/main/formHeader/color.png"/>
	<img src="exedo/webv3/images/main/formHeader/help.png" title="清除缓存"/>
	<img src="exedo/webv3/images/main/formHeader/stop.png" title="退出"/>
	<div class="themeLabel">
		<img value="_lan" src="exedo/webv3/images/main/formHeader/lan.gif"/>
		<img value="_lan" src="exedo/webv3/images/main/formHeader/qianlv.gif"/>
		<img value="_lan" src="exedo/webv3/images/main/formHeader/zi.gif"/>
		<img value="_lan" src="exedo/webv3/images/main/formHeader/shenlv.gif"/>
		<img value="_lan" src="exedo/webv3/images/main/formHeader/hong.gif"/>
		<!-- 
		<img value="_ru" src="exedo/webv3/images/main/formHeader/qianlv.gif"/>
		<img value="_fenghong" src="exedo/webv3/images/main/formHeader/zi.gif"/>
		<img value="_fengru" src="exedo/webv3/images/main/formHeader/shenlv.gif"/>
		<img value="_red" src="exedo/webv3/images/main/formHeader/hong.gif"/>
		 -->
	</div>
	<div class="userName">登录人:<%=userName%></div>
</div>

