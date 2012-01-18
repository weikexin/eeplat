<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<html>

<%

	String paneModelContent = (String) request
			.getAttribute("paneModelContent");
	String paneModelTitle = (String) request
			.getAttribute("paneModelTitle");
	SessionContext context = (SessionContext) session
			.getAttribute("userInfo");
	if (null == session.getAttribute("userInfo")
			|| context.getUser() == null) {
		response.sendRedirect(request.getContextPath()
		+ "/exedo/webv3/logoff.jsp");
	}
	if (paneModelTitle == null) {
		paneModelTitle = "信息管理平台";
	}

	if (paneModelContent == null) {
		paneModelContent = "欢迎使用快速开发平台!";
	}
	
	String mainStyle= "";
	try{

		mainStyle = DOGlobals.getInstance().getSessoinContext().getUser().getValue("style");
	}catch(Exception e){
		
	}
	if(mainStyle=="" || mainStyle==null){
		mainStyle = "_lan";
	}
	System.out.println(mainStyle);
%>
<head>

 
<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 

<!-- 插件的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/toolbar/toolbar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/combox/selects.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/combox/selects_static.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/fileuploader/jquery.uploadify.v2.1.0.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/fileuploader/swfobject.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/treetable/jquery.treeTable.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/facebook/jquery.elastic.js" ></script>		

<script language="javascript">

globalURL = "/<%=DOGlobals.URL%>/";

            
//窗口大小改变的时候，重新给div限制高度
$(window).resize(function(){
   	resscrEvt($(this).height(),$(this).width());
}); 

$(function(){

	  var oldPageX = 0;

	//初始化左右拖动
	  window.parent.$(".resizeTd").mousedown(function(e){

		  var old_gLeW = $(".gLe").width();
		  var old_gRiW = $(".gRi").width();
		  $(document).bind('mousemove',function(e){
//////////////不能太小

	
				  window.parent.$(".gLe").width(old_gLeW + e.pageX - oldPageX );
				  window.parent.$(".gRi").width(old_gRiW - e.pageX + oldPageX );
				  window.parent.$(".gFpage").width(old_gLeW + e.pageX - oldPageX-1);
				  
				  oldPageX = e.pageX;
				  window.parent.resscrEvt();

			  window.parent.status = e.pageX + ";" + oldPageX;	   
		  }).bind('mouseup',function(e){
			  $(document).unbind('mousemove');
			  $(document).unbind('mouseup');	
			  }
		  );
	 });
	
	
});
<%
  DOService aService = DOService.getService("DO_BO_Icon_List");
  if(aService!=null){
	  List list = aService.invokeSelect();
	  for(Iterator it = list.iterator(); it.hasNext();){
		   BOInstance bi = (BOInstance)it.next();
		   if(bi!=null && bi.getValue("formulaScript")!=null)
		   out.println(bi.getValue("formulaScript"));
	  }
  }
%>
</script>
</head>

<body  lang=zh>
<div id='dmLayer'></div>
<input  type="hidden" id="mainStyle" value="<%=mainStyle %>"/> 
fdssssssssssssssssssffdfdsfds
</body>
</html>
