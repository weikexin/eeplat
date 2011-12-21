<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
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
		paneModelContent="欢迎使用快速开发平台!";
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
  
<!-- Jquery插件的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/toolbar/core.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/toolbar/toolbar.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/tab/ui.tabs.css" type="text/css"  media="print, projection, screen"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/dialog/dialog.css"     type="text/css"  />  
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/dialog/jqModal.css"    type="text/css" />  
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/fileuploader/uploadify.css"    type="text/css" />  
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/treetable/jquery.treeTable.css" type="text/css" /> 

 
 <!-- 平台主体及其它集成的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/xtree2.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/estop/estop.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/main/main<%=mainStyle%>.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/workbench/workbench_style.css" type="text/css" />

 
<!-- 插件的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/tab/ui.tabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/toolbar/toolbar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/dialog/jqModal.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/dialog/jqDnR.js" ></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/form/jquery.form.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/tablesorter/jquery.tablesorter.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/tablesorter/jquery.metadata.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/combox/selects.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/combox/selects_static.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/fileuploader/jquery.uploadify.v2.1.0.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/fileuploader/swfobject.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/treetable/jquery.treeTable.min.js" ></script>	



<!-- 平台主体及其他集成的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/treev2/xtree2.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/treev2/xloadtree2.js" ></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/codepress/codepress.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/FusionChartsFree/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/platAjax.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/my.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/form_use.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/msfldg/rzzl.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/msfldg/msfldg.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/msfldg/msfl_cidiangl.js"  ></script>
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
});


</script>
</head>

<body  lang=zh>
<form id="zephyrOcx" name="zephyrOcx">
		<object id="DOcxtest1" name="DOcxtest1" on
				classid="clsid:4E1C94DD-78DE-4DCB-B0B6-3745C728EFA8"
				width=0
				height=0
				align=middle
				hspace=0
				vspace=0 viewastext>
    	</object>
	</form>
<div id='dmLayer'></div>
<input  type="hidden" id="mainStyle" value="<%=mainStyle %>"/> 
<%=paneModelContent%>

	
<noscript><iframe src="*.htm"></iframe></noscript>	
</body>
</html>
