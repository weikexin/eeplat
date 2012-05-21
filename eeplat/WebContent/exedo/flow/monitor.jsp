<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.bo.DOBO"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
  		DOBO bo = DOBO.getDOBOByName("do_pt_processtemplate");
		BOInstance curPt = bo.getCorrInstance();
  
        String ptName = "test";
        if(curPt!=null){
			ptName = curPt.getName();
		}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
     "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>EEPlat工作流监控</title>
<style type="text/css">


#canvas {
    height: 1000px;
    margin: 0 auto;
    text-align: left;
    width: 680px;
}

html,body{
    width:680px;
	height:1000px;
	overflow:hidden;
	align:left;
}

v:img{ position:relative;top:0;left:0;width:35px;height:35px}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-1.6.2.min.js"></script>
<script language="javascript">
  globalURL = "/<%=DOGlobals.URL%>/";
</script>  
<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/platAjax.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/raphael-min.js"  ></script>
<script type="text/javascript" src="monitor.js"></script>

<body>

 <div id="canvas"></div>

 <script>

	var paper = Raphael("canvas");
	loadWfMonitor(paper);
	
	
  
</script>

</body>
</html>
