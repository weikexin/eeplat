<%@ page pageEncoding="UTF-8"%><%@ page import="com.exedosoft.plat.ui.DODownLoadFile"%><% response.setContentType("application/x-download");//设置为下载application/x-download
///////////一定要把回车去掉
     String name =  request.getParameter("name");
     String rename = request.getParameter("rename");////可以为null
     String thisuuid = request.getParameter("thisuuid");
     String bouid = request.getParameter("bouid");
     String downLoadSizeCol = request.getParameter("downLoadSizeCol");
     
     DODownLoadFile.outStream(bouid,thisuuid,name,rename,downLoadSizeCol,response); %>
     
