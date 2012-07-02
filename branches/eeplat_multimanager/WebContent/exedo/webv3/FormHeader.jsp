<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%	
	String userName = "";
    String company = "";
	SessionContext exedoContext = (SessionContext) session
			.getAttribute("userInfo");
	if (exedoContext != null && exedoContext.getUser() != null) {
		userName = exedoContext.getUser().getName();
		company = exedoContext.getUser().getValue("company");
		if("a".equals(userName)){
			company = "开发者";
		}
	}

%>
<style> 
  .welcome {
	   color:#3C3C3C;
	   left:188px;
	   position:absolute;
	   top:17px;
  }
  .inner {
	   color:#3C3C3C;
  }
  .control{
  	color:#929393;
  	text-decoration:none;
  	position:absolute;
  	right:10px;
  	top:15px;
  	cursor:pointer;
  	line-height:167%;
  }
  .selectt{
  	width:134px;
  	display:inline;
  	color:black;
    font-weight:bold;
  }
</style>
<script language="javascript">

  function logOff(){
	  if(confirm('确定要退出吗？')){
	  	window.location.href="multi_manager/logoff.jsp"
	  }
  }

  function helpme(){
   		window.open('http://code.google.com/p/eeplat');
  }



  function refreshMe(){
				$.get("exedo/webv3/ClearCache.jsp",function(data){
			  alert("清除缓存成功!");
		});
  }
  

  function changeApp(obj){
	  alert("切换工程::" + obj.value);
  }</script>
<div>
	<div algin="left" id='logoPic' style='z-index:1; height: 0; left: 0px; position: absolute; top: 1px; width: 0;'><img name='logoImg' height="40" src='<%=request.getContextPath()%>/exedo/webv3/images/logo_small_l.png' border='0'></div>
	
	 <div class="control">
	    <span class="inner">
			欢迎您，<%=userName%> ！
		</span> [ <%=company%> ]
	
		&nbsp;&nbsp;
			<a  onclick="loadPml({'pml':'PM_DO_Application_Insert','target':'_opener_tab','title':'新建工程'})">新建工程</a> | <a onclick="refreshMe()" >清空缓存</a> | <a onclick="helpme();" >帮助</a>  | <a onclick="logOff()" >退出</a>
	</div>
</div>

