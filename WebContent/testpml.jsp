<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	
%>
<%

  String pml = request.getParameter("pml");
  if(pml!=null){
	    request.setAttribute("greenChannel","greenChannel_Ajax_Test");
		application.getRequestDispatcher("/" +
				pml + ".pml?isApp=true").forward(
				request, response);
		return;
  }
%>
<body style="margin-left:50px;margin-top:100px">
	
	请输入要测试的面板名称：<input id="pml" type="text" size="50" name="pml" >
	&nbsp;&nbsp;
	<input type="button" name="test" onclick="loadPml()" value="测试">	

</body>

<script>

	function loadPml(){
		var theValue = document.getElementById("pml").value;
		window.location = "testpml.jsp?pml=" + theValue;
	}
</script>