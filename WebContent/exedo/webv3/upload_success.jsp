<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.Escape"%>
<html >
<head>
<link rel=stylesheet href="css/mac/mac.css" type="text/css"/>
<title>上传文件</title>
<%

 request.setCharacterEncoding("UTF-8");
 String colName  = request.getParameter("colName");
 String fileName  = request.getParameter("fileName");
 String blobColName  = request.getParameter("blobColName");
 
 
 System.out.println("Firstname=================" + fileName);
 fileName = Escape.unescape(fileName);
 System.out.println(fileName);
 


%>
<script language="javascript">
try{
window.opener.document.getElementById("<%=colName%>").value = "<%=fileName%>";
window.opener.document.getElementById("<%=blobColName%>").value = "<%=fileName%>";
}catch(e){
}
window.close();


</script>