<%@ page pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<html>
<head>
<% 
 String uuids =  (String)request.getAttribute("uuids");
 System.out.println(uuids);

%>
<title>
文件下载
</title>
</head>
<body>
文档下载使用Applet技术， 采用了数字签名，有标示为smartdot的证书请选择信任。<br>
<applet
  codebase = "."
  archive  = "filedown.jar"
  code     = "com.anolesoft.plat.nettrans.FileDownLoad2.class"
  name     = "FileDownApplet"
  width    = "400"
  height   = "300"
  hspace   = "0"
  vspace   = "0"
  align    = "middle"
>
<param name = "param0" value = "<%=uuids%>">
<param name = "param1" value = "http://127.0.0.1/goa">
</applet>
</body>
</html>

