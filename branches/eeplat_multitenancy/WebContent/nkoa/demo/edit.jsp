<%@ page contentType="text/html;charset=gb2312" %>
<%@ page language="java" import="java.sql.*,java.util.*" %>
<%@ page language="java" import="com.exedosoft.plat.bo.DODataSource" %>

<HTML>
<HEAD>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<SCRIPT LANGUAGE="JavaScript" src="ntkoocx.js"></SCRIPT>
<TITLE>NTKO Office文档控件JSP示例-编辑文档</TITLE>
<style>
button.op{
width:90px;
background-color:#9DC2DB;
border:1px #EEEEEE solid;
cursor: hand;
}
</style>
</HEAD>
<%
  Connection conn = null; 
  Statement stmt = null; 
  ResultSet rs = null; 
  String filename="";
  String docid = request.getParameter("docid");

  if ( null == docid) //新文档
  {
  	docid = "";
  	Calendar rightNow = Calendar.getInstance();
  	filename = (new Integer(rightNow.get(Calendar.MONTH))).toString()+ rightNow.get(Calendar.YEAR) 
  		+ rightNow.get(Calendar.DAY_OF_MONTH)
  		+ rightNow.get(Calendar.MINUTE) + rightNow.get(Calendar.SECOND)+".doc"; //缺省文件名
  }
  else //修改已有文档
  {
	//与DBMS建立连接 
	try
	{ 
		conn = DODataSource.getDefaultCon(); 
		stmt = conn.createStatement();		
	} 
	catch(SQLException ex)
	{ 
		System.err.println("error: " + ex.getMessage()); 
	}
	//获取文件信息
  	try
	{ 			
		rs = stmt.executeQuery("select * from MyUploadTable where id="+docid); 		
	} 
	catch(SQLException ex)
	{ 
		System.err.println("aq.executeQuery: " + ex.getMessage()); 
	}
	while(rs.next())
	{
		try
		{
			long id = rs.getLong("id");			
			filename = rs.getString("filename").trim();//new String( rs.getBytes("filename"),"gb2312");
		}
		catch(Throwable e)
		{
			System.out.println(e.toString());
			throw new ServletException(e.toString());
		}
		break;
	}
	rs.close();
  }  
%>
<BODY >
<center>
<h3>NTKO Office文档控件JSP示例</h3>

<FORM id="myForm" METHOD="POST" ENCTYPE="multipart/form-data" ACTION="uploadedit.jsp">
<TABLE BORDER=0 width = 500>	
	<tr>
		<td align="center"><INPUT type=BUTTON VALUE="保存文档到数据库" onclick="TANGER_OCX_SaveEditToServer();"></td>
		<td align="center"><INPUT type=BUTTON VALUE="关闭窗口" onclick="javascript:window.close();"></td>
	</tr>
</TABLE>
<TABLE BORDER=0 width = 700>
	<tr>
		<td>记录ID：<input name="docid" disabled=true value="<%= docid %>"></td>		
		<td>文件名：<input id="filename" name="filename" MAXLENGTH=50 size=30 value="<%= filename %>"></td>	
	</tr>
</TABLE>

<style>
button.op{
width:90px;
background-color:#9DC2DB;
border:1px #EEEEEE solid;
cursor: hand;
}
</style>
<table width=100% height=700 border=1 cellpadding=0 cellspacing=0 style="border:1px #9dc2db solid">
<tr height=26><td colspan=2><font color="red">提示：本地印章需要使用专门的印章制作工具，<a href="SignPicTool.exe">SignPicTool.exe</a></font></td>
</tr>
<tr height=26><td colspan=2><font color="red">提示：所有远程印章(财务章，合同章，行政章)的口令是8个1：11111111</font></td>
</tr>
<tr width=100%><td valign=top width=90>
	<button class="op" onclick="TANGER_OCX_OBJ.Titlebar = true;">显示标题栏</button>
	<button class="op" onclick="TANGER_OCX_OBJ.Titlebar = false;">隐藏标题栏</button>
	<button class="op" onclick="TANGER_OCX_OBJ.Menubar=false;">隐藏菜单</button>
	<button class="op" onclick="TANGER_OCX_OBJ.Menubar=true;">显示菜单</button>
	<button class="op" onclick="TANGER_OCX_OBJ.Toolbars=false;">隐藏工具栏</button>
	<button class="op" onclick="TANGER_OCX_OBJ.Toolbars=true;">显示工具栏</button>
	<button class="op" onclick="TANGER_OCX_OBJ.IsShowToolMenu=false;">隐藏工具菜单</button>
	<button class="op" onclick="TANGER_OCX_OBJ.IsShowToolMenu=true;">显示工具菜单</button>
	<button class="op" onclick="TANGER_OCX_OBJ.IsNoCopy=true;">禁止拷贝</button>
	<button class="op" onclick="TANGER_OCX_OBJ.IsNoCopy=false;">允许拷贝</button>		
	<button class="op" onclick="TANGER_OCX_EnableFileNewMenu(false)">禁止新建</button>
	<button class="op" onclick="TANGER_OCX_EnableFileNewMenu(true)">允许新建</button>
	<button class="op" onclick="TANGER_OCX_EnableFileSaveMenu(false)">禁止保存</button>
	<button class="op" onclick="TANGER_OCX_EnableFileSaveMenu(true)">允许保存</button>
	<button class="op" onclick="TANGER_OCX_EnableFileSaveAsMenu(false)">禁止另存</button>
	<button class="op" onclick="TANGER_OCX_EnableFileSaveAsMenu(true)">允许另存</button>
	<button class="op" onclick="TANGER_OCX_EnableFilePrintMenu(false)">禁止打印</button>
	<button class="op" onclick="TANGER_OCX_EnableFilePrintMenu(true)">允许打印</button>
	<button class="op" onclick="TANGER_OCX_SetMarkModify(true)">保留痕迹</button>
	<button class="op" onclick="TANGER_OCX_SetMarkModify(false)">不留痕</button>
	<button class="op" onClick="AddPictureFromLocal()">添加本地图片</button>
	<button class="op" onclick="AddPictureFromURL('200_80Logo.jpg')">从URL添加图片</button>
	<button class="op" onclick="AddSignFromLocal()">添加本地印章</button>
	<button class="op" onclick="AddSignFromURL('zhang_caiwu.gif.esp')">添加财务章</button>
	<button class="op" onclick="AddSignFromURL('zhang_hetong.gif.esp')">添加合同章</button>
	<button class="op" onclick="AddSignFromURL('zhang_xingzheng.gif.esp')">添加行政章</button>
	<button class="op" onclick="DoHandSign()">手写签名</button>
	<button class="op" onclick="DoHandDraw()">手工绘图</button>
	<button class="op" onclick="DoCheckSign()">印章验证</button>
	<button class="op" onclick="TANGER_OCX_AddDocHeader('某某政府机关红头文件')">文件套红</BUTTON>
	<button class="op" onclick="TANGER_OCX_ChgLayout()">页面布局</button>
	<button class="op" onclick="TANGER_OCX_ShowRevisions(true)">显示痕迹</button>
	<button class="op" onclick="TANGER_OCX_ShowRevisions(false)">隐藏痕迹</button>
	<button class="op" onclick="TANGER_OCX_PrintDoc()">打印</button>
</td>
<td width=100% valign="top">		
	<object id="TANGER_OCX" classid="clsid:A39F1330-3322-4a1d-9BF0-0BA2BB90E970" codebase="OfficeControl.cab#version=5,0,0,6" width="100%" height="550">
        <param name="BorderStyle" value="1">
	 	<param name="BorderColor" value="14402205">        
	 	<param name="TitlebarColor" value="14402205">
        <param name="TitlebarTextColor" value="0">	 
        <param name="Caption" value="NTKO OFFICE文档控件JSP示例。V4.0.0.6版本。http://www.ntko.com">
        <param name="IsShowToolMenu" value="-1">
        <param name="IsNoCopy" value="-1">
		<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>
	</object>
	<!-- 以下函数相应控件的两个事件:OnDocumentClosed,和OnDocumentOpened -->
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
		TANGER_OCX_OnDocumentClosed();
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);
	</script>
	
	<script language="JScript">
		TANGER_OCX_OpenDoc("<%= docid %>");
	</script>
</td>
</tr>

</table>	
</FORM>
</center>
</BODY>
</HTML>