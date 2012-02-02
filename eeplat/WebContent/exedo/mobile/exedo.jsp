<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%
	String paneModelContent = (String) request
			.getAttribute("paneModelContent");
%>

<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1" />
<title>云鹤平台应用</title>


<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/jquery.mobile.css" />
<link rel="stylesheet"  href="<%=request.getContextPath()%>/exedo/mobile/css/openid.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/facebook/jquery.neosmart.fb.wall.css" type="text/css" /> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/facebook/status.css" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.mobile.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/facebook/jquery.elastic.js" ></script>	


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/exedo/mobile/js/jquery.ui.datepicker.mobile.css" />

<script language="javascript">
		  globalURL = "/<%=DOGlobals.URL%>/";
		  //reset type=date inputs to text
		  $( document ).bind( "mobileinit", function(){
		    $.mobile.page.prototype.options.degradeInputs.date = true;
		  });	
		</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/exedo/mobile/js/jQuery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/exedo/mobile/js/jquery.ui.datepicker.mobile.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/exedo/mobile/js/platAjax.js"></script>
<script language="javascript">


  			jQuery(function($){
 			     $.datepicker.regional['zh-CN'] = {
 			        clearText: '清除',
 			        clearStatus: '清除已选日期',
 			        closeText: '关闭',
 			        closeStatus: '不改变当前选择',
 			        prevText: '<上月',
 			        prevStatus: '显示上月',
 			        prevBigText: '<<',
 			        prevBigStatus: '显示上一年',
 			        nextText: '下月>',
 			        nextStatus: '显示下月',
 			        nextBigText: '>>',
 			        nextBigStatus: '显示下一年',
 			        currentText: '今天',
 			        currentStatus: '显示本月',
 			        monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],
 			        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],
 			        monthStatus: '选择月份',
 			        yearStatus: '选择年份',
 			        weekHeader: '周',
 			        weekStatus: '年内周次',
 			        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
 			        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
 			        dayNamesMin: ['日','一','二','三','四','五','六'],
 			        dayStatus: '设置 DD 为一周起始',
 			        dateStatus: '选择 m月 d日, DD',
 			        dateFormat: 'yy-mm-dd',
 			        firstDay: 1,
 			        initStatus: '请选择日期',
 			        duration:'fast',
 			       // showButtonPanel:true,
  			        defaultDate:null,
 			        isRTL: false};
 			        $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
 			    });

		</script>
</head>
<body>

<%=paneModelContent%>

</body>
</html>
