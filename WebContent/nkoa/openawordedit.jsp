<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ page import="com.exedosoft.plat.util.Escape"%>
<%@ page import="com.exedosoft.plat.bo.DOBO"%>
<%@page import="com.exedosoft.plat.util.Escape"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.StringUtil"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%
	String docName = request.getParameter("docName");
    docName = Escape.unescape(docName);
    
    System.out.println("DocName:::" + docName);
	String contextBIUid = request.getParameter("contextBIUid");
	System.out.println("contextBIUid::" + contextBIUid);
	String userName = "";

%>


<HTML>
<HEAD>


<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/platAjax.js"  ></script>

<SCRIPT LANGUAGE="JScript" 	src="<%=request.getContextPath()%>/nkoa/ntkoocx.js" charset="gb2312"></SCRIPT>
<script language="JavaScript" charset="gb2312">  
  //无提示关闭IE窗口，目前适用于IE所有版本(目前最新为IE7.0）
        function CloseWindowNoAsk() 
        { 
            var ua=navigator.userAgent 
            var ie=navigator.appName == "Microsoft Internet Explorer" ? true : false 
            
            if(ie) 
            { 
                var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE ")))) 
                if(IEversion< 5.5) 
               { 
                    var str  = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">' 
                    str += '<param name="Command" value="Close"></object>'; 
                    document.body.insertAdjacentHTML("beforeEnd", str); 
                    document.all.noTipClose.Click(); 
                } 
                else 
               { 
                    window.opener =null; 
                    window.open("","_self");
                    window.close(); 
                } 
            } 
            else 
            { 
                window.close(); 
            }
        }
  var   WARNING_TIMEOUT   =   1860;     //   second   
  var   SL_warningPopupTimeout   =   null;   
  var   mytime   =   0; 

  
  var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
     
  TANGER_OCX_OBJ.ShowCommandBar("NTKO安全签名印章",false);
  
  
  
  function   openWarningWindow()   {   
			  CloseWindowNoAsk();   
  }   
    
  function   startWarningTimer()   {   
	  stopWarningTimer();   
	  SL_warningPopupTimeout   =   window.setInterval("openWarningWindow()",   (WARNING_TIMEOUT)*1000);   
  }   
    
  function   stopWarningTimer()   {   
  if   (SL_warningPopupTimeout   !=   null)   
  window.clearInterval(SL_warningPopupTimeout);   
  }   
  document.attachEvent("onmousemove", startWarningTimer);     //addEventListener   
  document.attachEvent("onkeydown",   startWarningTimer);   
</script>


<TITLE>编辑文档</TITLE>
<style>
button.op {
	width: 90px;
	background-color: #9DC2DB;
	border: 1px #EEEEEE solid;
	cursor: hand;
}

html,body {
	overflow: hidden; /* erase window level scrollbars */
	height: 100%;
}
</style>

<script type="text/javascript">

 function  initNKOA(){
 	
 		//TANGER_OCX_OBJ.Titlebar = true;  //显示标题栏
	TANGER_OCX_OBJ.Titlebars = false;  //隐藏标题栏
	//TANGER_OCX_OBJ.Menubar=false;  //隐藏菜单
	TANGER_OCX_OBJ.Menubar=true;  //显示菜单
	 TANGER_OCX_OBJ.Toolbars=false;  //隐藏工具栏
	// TANGER_OCX_OBJ.Toolbars=true;  //显示工具栏
	 TANGER_OCX_OBJ.IsShowToolMenu=false;  //隐藏工具菜单
      // TANGER_OCX_OBJ.IsShowToolMenu=true;  //显示工具菜单
	//TANGER_OCX_OBJ.IsNoCopy=true;  //禁止拷贝
	TANGER_OCX_OBJ.IsNoCopy=false;  //允许拷贝		
	//TANGER_OCX_EnableFileNewMenu(false);  //禁止新建
	TANGER_OCX_EnableFileNewMenu(true);  //允许新建
	//TANGER_OCX_EnableFileSaveMenu(false);  //禁止保存
	TANGER_OCX_EnableFileSaveMenu(true);  //允许保存
	//TANGER_OCX_EnableFileSaveAsMenu(false);  //禁止另存
	TANGER_OCX_EnableFileSaveAsMenu(true);  //允许另存
	//TANGER_OCX_EnableFilePrintMenu(false);  //禁止打印
	//TANGER_OCX_EnableFilePrintMenu(true);  //允许打印
	
	
	TANGER_OCX_SetMarkModify(true);//保留痕迹
    TANGER_OCX_ShowRevisions(true);

	TANGER_OCX_OBJ.ShowCommandBar("Reviewing",true);

	
	 var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
    
     var app = mydoc.Application; 
     var sel = app.Selection; 
	 sel.TypeBackspace();
     TANGER_OCX_OBJ.ShowCommandBar("Task Pane",false);
	 TANGER_OCX_OBJ.ShowCommandBar("NTKO安全签名印章",false);
        // myview.Reviewers("余丙俊").Visible = False;
	    // myview.SplitSpecial =18;
	//AddPictureFromLocal();//添加本地图片
	//AddPictureFromURL('200_80Logo.jpg');//从URL添加图片
	//AddSignFromLocal();//添加本地印章
	//AddSignFromURL('zhang_caiwu.gif.esp');//添加财务章
	//AddSignFromURL('zhang_hetong.gif.esp');//添加合同章
	//AddSignFromURL('zhang_xingzheng.gif.esp');//添加行政章
	
	//DoHandDraw();//手工绘图
	//DoCheckSign();//印章验证
	//TANGER_OCX_AddDocHeader('某某政府机关红头文件');//文件套红
	//TANGER_OCX_ChgLayout();//页面布局
	//TANGER_OCX_PrintDoc();//打印
	
  
 }


</script>

<SCRIPT type="text/javascript">

	window.onbeforeunload = function()
	{
		 var filename ="<%=docName%>";
		 filename = escape(filename);
		 
	   
		TANGER_OCX_SetMarkModify(false);
		 if(issave)
		{TANGER_OCX_SaveEditToServer();}
         
		
	}
	if(issave)
	{setInterval("TANGER_OCX_SaveEditToServer();", 300000); }
</SCRIPT>
</HEAD>
<BODY>
<center>


<FORM id="myForm" name="myForm" METHOD="POST"
	ENCTYPE="multipart/form-data" ACTION="../exedo/webv3/upload_action.jsp">

<table width=100% height=100% border=0 cellpadding=0 cellspacing=0
	style="border: 0px #9dc2db solid">



	<tr width=100%>

		<!--  <td valign=top width=90>
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
	<button class="op" onClick="AddPictureFromLocal()">添加本地图片</button>
	<button class="op" onclick="openWordPicPane()">插入图片</button>
	<button class="op" onclick="AddSignFromLocal()">添加本地印章</button>
	<button class="op" onclick="AddSignFromURL('zhang_caiwu.gif.esp')">添加财务章</button>
	<button class="op" onclick="AddSignFromURL('zhang_hetong.gif.esp')">添加合同章</button>
	<button class="op" onclick="AddSignFromURL('zhang_xingzheng.gif.esp')">添加行政章</button>
	<button class="op" onclick="DoHandSign2()">手写签名</button>
	<button class="op" onclick="DoHandDraw()">手工绘图</button>
	<button class="op" onclick="DoCheckSign()">印章验证</button>
	<button class="op" onclick="TANGER_OCX_AddDocHeader('某某政府机关红头文件')">文件套红</BUTTON>
	<button class="op" onclick="TANGER_OCX_ChgLayout()">页面布局</button>
	<button class="op" onclick="TANGER_OCX_PrintDoc()">打印</button>
</td>-->
		<td width=100% valign="top">
				<object id="TANGER_OCX" classid="clsid:A39F1330-3322-4a1d-9BF0-0BA2BB90E970" codebase="OfficeControl.cab#version=5,0,0,6" width="100%" height="550">
			        <param name="BorderStyle" value="1">
				 	<param name="BorderColor" value="14402205">        
				 	<param name="TitlebarColor" value="14402205">
			        <param name="TitlebarTextColor" value="0">	 
			        <param name="Caption" value="NTKO OFFICE文档控件">
			        <param name="IsShowToolMenu" value="-1">
			        <param name="IsNoCopy" value="-1">
					<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>
				</object>
			</td>
	</tr>

</table>


<TABLE BORDER=0 width='700' height='1' style="visibility: hidden">

	<tr height='1'>
		<td align="center">
		<button class="op"
			onclick="AddSignFromLocal('D:\\',true,true,1,100,0);">手写签名</button>
		</td>
		<td align="center">
		<button class="op" onclick="openWordPicPane()">插入图片</button>
		</td>
		<td align="center">
		<button class="op" onclick="DO_GetRevisions('<%=userName%>')">保存文档</button>
		</td>
		<td align="center">
		<button class="op" onclick="javascript:window.close()">关闭窗口</button>
		<input type="hidden" id="filename" name="filename" MAXLENGTH=50
			size=30 value="<%=docName%>"></td>



	</tr>
</TABLE>
</FORM>





<!-- 以下函数相应控件的两个事件:OnDocumentClosed,和OnDocumentOpened --> 
    <script
	language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
	    var filename ="<%=docName%>";
		 filename = escape(filename);

		 TANGER_OCX_SetMarkModify(false);

        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
		
       //  mydoc.Unprotect("sjzx123");
		 if(issave)
		{TANGER_OCX_SaveEditToServer();}
	</script>
	
	
	 <script language="JScript" for=TANGER_OCX
	event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);	
		var mydoc1 = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
		initNKOA();
		
		TANGER_OCX_SetDocUser("<%=userName%>");
		var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; 
		app.UserInitials="<%=userName%>";		
		initSpCustomMenus();
	//	mydoc.Protect(1,false,"sjzx123",false,false);
	
	</script>
	
		<!-- 打开文档 -->
	
	<script language="JScript">
	

	TANGER_OCX_OpenDoc(PathUrl+"<%=docName%>"); 
		 
	</script> 
	
   <script language="JScript" for="TANGER_OCX"
	event="OnCustomMenuCmd2(menuPos,submenuPos,subsubmenuPos,menuCaption,menuID)"><!--
				if (menuID =="1")
				{
				     //var isSaveLiuhen="";
				     //var strSave="f";
				 
				    // if (isSaveLiuhen == strSave)
				         
				    	 if(issave)
						{TANGER_OCX_SaveEditToServer();}else
					   {
						//alert("批注消失，请勿保存"); 
						 }
				
				}
				
				if (menuID =="17")
				{
				
				alert("插入数据");
				testAddTable("hello,teddy");
				
//					var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
//					mydoc.Unprotect("sjzx123");
//					TANGER_OCX_SetMarkModify(false);
				
//					try{
//						TANGER_OCX_OBJ.AddSecSignFromEkey ("<%=userName%>",0,0,1,2,false,false,false,false,123456,1);
//					} catch(e){  
//				
//					}
//				     mydoc.Protect(1,false,"sjzx123",false,false);
				
				}
				if (menuID =="18")
				{
					var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
					mydoc.Unprotect("sjzx123");
					TANGER_OCX_SetMarkModify(true);
				    TANGER_OCX_OBJ.ClearSigns("<%=userName%>","123456",0);
				    mydoc.Protect(1,false,"sjzx123",false,false);
				}
				if (menuID =="6")
				{  
				
				var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
					mydoc.Unprotect();
					TANGER_OCX_SetMarkModify(false);
					TANGER_OCX_OBJ.DoHandSign2("<%=userName%>");
				    mydoc.Protect(1,false,"",false,false);
				   
				}
				
				if (menuID =="7")
				{
				   	var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
					mydoc.Unprotect();
				   // mydoc.Protect(1,false,"",false,false);
					TANGER_OCX_SaveEditToServer();
				
				}
				
				if (menuID =="27")
				{
				
				}
	</script>
	

</center>
</BODY>
</HTML>