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

	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
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
<title><%=paneModelTitle %></title>


<script language="javascript">

globalURL = "/<%=DOGlobals.URL%>/";

</script>

<!-- 
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/button/style/button.css"  type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/button/style/icon.css"  type="text/css"/>
 -->
 
<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<!-- Jquery插件的css -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/toolbar/core.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/toolbar/toolbar.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/fileuploader/uploadify.css"    type="text/css" />  
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/treetable/jquery.treeTable.css" type="text/css" /> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/facebook/jquery.neosmart.fb.wall.css" type="text/css" /> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/facebook/status.css" type="text/css" /> 

 
 <!-- 平台主体及其它集成的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/xtree2.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/estop/estop.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/redmond/jquery-ui-1.8.16.custom.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/main/main<%=mainStyle%>.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/workbench/workbench_style.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CodeMirror/codemirror.css"
	type="text/css" />	
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CodeMirror/default.css"
	type="text/css" />	

 
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

<!-- 平台主体及其他集成的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/treev2/xtree2.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/treev2/xloadtree2.js" ></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/FusionChartsFree/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/platAjax.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/my.js"  ></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/CodeMirror/codemirror-compressed.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/CodeMirror/complete.js"></script>
<script language="javascript">

globalURL = "/<%=DOGlobals.URL%>/";

            
//窗口大小改变的时候，重新给div限制高度
$(window).resize(function(){
   	resscrEvt($(this).height(),$(this).width());
}); 

$(function(){

	////为第一个Tab 绑定事件
	var tabSelector = "#dvTab table[tabId='workbench_container']";
	var tabBtnSelector = tabSelector+" .btn";
	bindTabClickCss(tabSelector);
	bindTabCloseCss(tabBtnSelector);
	bindTabCloseWindow(tabBtnSelector);
	
	
	//初始化左右拖动
	  $(".resizeTd").mousedown(function(e){
		  var oldPageX = e.pageX;
		  var old_gLeW = $(".gLe").width();
		  var old_gRiW = $(".gRi").width();
		  $(document).bind('mousemove',function(e){
//////////////不能太小
			  if(e.pageX > 10){
				  $(".gLe").width(old_gLeW + e.pageX - oldPageX);
				  $(".gRi").width(old_gRiW - e.pageX + oldPageX);
				  $(".gFpage").width(old_gLeW + e.pageX - oldPageX-1);
				  resscrEvt();
			  }
			  window.status = e.pageX;	   
		  }).bind('mouseup',function(e){
			  $(document).unbind('mousemove');
			  $(document).unbind('mouseup');	
			  }
		  );
	 });
	
	
	  resscrEvt();
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
<%=paneModelContent%>
</body>
</html>
