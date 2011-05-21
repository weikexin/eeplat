<%@ page contentType="text/html;charset=gb2312" %>
<%@ page language="java" import="java.io.*,java.sql.*" %>
<%@ page language="java" import="com.exedosoft.plat.bo.DODataSource" %>

<HTML>
<HEAD>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<TITLE>NTKO Office文档控件JSP示例</TITLE>
</HEAD>
<BODY>
<Center>
<h1>NTKO Office文档控件JSP示例</h1>
<hr width=80%>
<h3><a href="edit.jsp" target="_blank">创建文档</A>&nbsp;&nbsp;
<button onclick="window.location.reload();">刷新</button></h3>
<hr width=80%><B>以下是数据库中保存的文档</B><hr width=80%>
<table width=700 border=1 cellpadding=0 cellspacing=0>
<tr bgcolor="#9cc3de"><td width=50>id</td><td width=350>文件名</td><td width=150>大小</td><td width=150>操作</td></tr>
<!--读取数据库中的文件-->



<%
	//读取数据库中的文件列表
	
	Connection conn = null; 
	Statement stmt = null; 
	ResultSet rs = null; 


	
	//与DBMS建立连接 
	try
	{ 
		conn = DODataSource.getDefaultCon(); 
		stmt = conn.createStatement();		
	} 
	catch(SQLException ex)
	{ 
		System.err.println("aq.executeQuery: " + ex.getMessage()); 
	}
	rs = stmt.executeQuery("select * from MyUploadTable"); 
	while(rs.next())
	{
		long id = rs.getLong("id");
		String filename = rs.getString("filename").trim();//new String( rs.getBytes("filename"),"gb2312");
		long filesize = rs.getLong("filesize");
		String outStr = "<tr><td>" + id + "</td><td>" + filename +"</td><td>" + filesize + "字节</td><td><a href=\"edit.jsp?docid=" + id + "\" target=_blank>编辑</a></td><tr>";
		out.println(outStr);		
	}

	rs.close();
%>
</table>
<BR><BR><BR>
<h3>@版权所有：刘伶训</h3>
<h3><a href="http://www.ntko.com" target="_blank">http://www.ntko.com</h3>
<h3><a href="mailto:tanger@ntko.com">tanger@ntko.com</a></h3>
</center>
</BODY>
</HTML>