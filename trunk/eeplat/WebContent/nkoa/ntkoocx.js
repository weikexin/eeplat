var TANGER_OCX_bDocOpen = false;
var TANGER_OCX_filename;
var TANGER_OCX_actionURL; //For auto generate form fiields
var TANGER_OCX_OBJ; //The Control
var PathUrl= globalURL + "upload/";
var allljxx="";
var issave = true;
var arr=new Array();

//以下为V1.7新增函数示例

//从本地增加图片到文档指定位置
function AddPictureFromLocal()
{
	if(TANGER_OCX_bDocOpen)
	{	
    TANGER_OCX_OBJ.AddPicFromLocal(
	"", //路径
	true,//是否提示选择文件
	true,//是否浮动图片
	100,//如果是浮动图片，相对于左边的Left 单位磅
	100); //如果是浮动图片，相对于当前段落Top
	};	
}

//从URL增加图片到文档指定位置
function AddPictureFromURL(URL)
{
	if(TANGER_OCX_bDocOpen)
	{
    TANGER_OCX_OBJ.AddPicFromURL(
	URL,//URL 注意；URL必须返回Word支持的图片类型。
	false,//是否浮动图片
	0,//如果是浮动图片，相对于左边的Left 单位磅
	0,//如果是浮动图片，相对于当前段落Top
	1, //当前光标处
	100,//无缩放
	1 //文字上方
	);
	};
}

//从本地增加印章文档指定位置
function AddSignFromLocal()
{

   if(TANGER_OCX_bDocOpen)
   {
      TANGER_OCX_OBJ.AddSignFromLocal(
	"匿名用户",//当前登陆用户
	"",//缺省文件
	true,//提示选择
	-10,
	8,
	"SignKey4456",
    1,
    100,
    0
)  
   }
}

//从URL增加印章文档指定位置
function AddSignFromURL(URL)
{
   if(TANGER_OCX_bDocOpen)
   {
      TANGER_OCX_OBJ.AddSignFromURL(
	"匿名用户",//当前登陆用户
	URL,//URL
	50,//left
	50
	)  
   }
}

//开始手写签名
function DoHandSign()
{
   if(TANGER_OCX_bDocOpen)
   {	
	TANGER_OCX_OBJ.DoHandSign(
	"匿名用户",//当前登陆用户 必须
	0,//笔型0－实线 0－4 //可选参数
	0x000000ff, //颜色 0x00RRGGBB//可选参数
	2,//笔宽//可选参数
	100,//left//可选参数
	50); //top//可选参数
	}
}
//开始手工绘图，可用于手工批示
function DoHandDraw()
{
	if(TANGER_OCX_bDocOpen)
	{	
	TANGER_OCX_OBJ.DoHandDraw(
	0,//笔型0－实线 0－4 //可选参数
	0x00ff0000,//颜色 0x00RRGGBB//可选参数
	3,//笔宽//可选参数
	200,//left//可选参数
	50);//top//可选参数
	}
}
//检查签名结果
function DoCheckSign()
{
	if(TANGER_OCX_bDocOpen)
	{		
	var ret = TANGER_OCX_OBJ.DoCheckSign
	(
	/*可选参数 IsSilent 缺省为FAlSE，表示弹出验证对话框,否则，只是返回验证结果到返回值*/
	);//返回值，验证结果字符串
	//alert(ret);
	}	
}
//此函数用来加入一个自定义的文件头部
function TANGER_OCX_AddDocHeader( strHeader )
{
	var i,cNum = 30;
	var lineStr = "";
	try
	{
		for(i=0;i<cNum;i++) lineStr += "_";  //生成下划线
		with(TANGER_OCX_OBJ.ActiveDocument.Application)
		{
			Selection.HomeKey(6,0); // go home
			Selection.TypeText(strHeader);
			Selection.TypeParagraph(); 	//换行
			Selection.TypeText(lineStr);  //插入下划线
			// Selection.InsertSymbol(95,"",true); //插入下划线
			Selection.TypeText("★");
			Selection.TypeText(lineStr);  //插入下划线
			Selection.TypeParagraph();
			//Selection.MoveUp(5, 2, 1); //上移两行，且按住Shift键，相当于选择两行
			Selection.HomeKey(6,1);  //选择到文件头部所有文本
			Selection.ParagraphFormat.Alignment = 1; //居中对齐
			with(Selection.Font)
			{
				NameFarEast = "宋体";
				Name = "宋体";
				Size = 12;
				Bold = false;
				Italic = false;
				Underline = 0;
				UnderlineColor = 0;
				StrikeThrough = false;
				DoubleStrikeThrough = false;
				Outline = false;
				Emboss = false;
				Shadow = false;
				Hidden = false;
				SmallCaps = false;
				AllCaps = false;
				Color = 255;
				Engrave = false;
				Superscript = false;
				Subscript = false;
				Spacing = 0;
				Scaling = 100;
				Position = 0;
				Kerning = 0;
				Animation = 0;
				DisableCharacterSpaceGrid = false;
				EmphasisMark = 0;
			}
			Selection.MoveDown(5, 3, 0); //下移3行
		}
	}
	catch(err){
		//alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}
function strtrim(value)
{
	return value.replace(/^\s+/,'').replace(/\s+$/,'');
}

function TANGER_OCX_doFormOnSubmit()
{
	var form = document.forms[0];
  	if (form.onsubmit)
	{
    	var retVal = form.onsubmit();
     	if (typeof retVal == "boolean" && retVal == false)
       	return false;
	}
	return true;
}

//允许或禁止显示修订工具栏和工具菜单（保护修订）
function TANGER_OCX_EnableReviewBar(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Reviewing").Enabled = boolvalue;
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Track Changes").Enabled = boolvalue;
	TANGER_OCX_OBJ.IsShowToolMenu = boolvalue;	//关闭或打开工具菜单
}

//打开或者关闭修订模式
function TANGER_OCX_SetReviewMode(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.TrackRevisions = boolvalue;
}

//进入或退出痕迹保留状态，调用上面的两个函数
function TANGER_OCX_SetMarkModify(boolvalue)
{
	TANGER_OCX_SetReviewMode(boolvalue);
	TANGER_OCX_EnableReviewBar(!boolvalue);
}

//显示/不显示修订文字
function TANGER_OCX_ShowRevisions(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.ShowRevisions = boolvalue;
}

//打印/不打印修订文字
function TANGER_OCX_PrintRevisions(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.PrintRevisions = boolvalue;
}


//设置页面布局
function TANGER_OCX_ChgLayout()
{
 	try
	{
		TANGER_OCX_OBJ.showdialog(5); //设置页面布局
	}
	catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}

//打印文档
function TANGER_OCX_PrintDoc()
{
	try
	{
		TANGER_OCX_OBJ.PrintOut(false);
	}
	catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}

function TANGER_OCX_SaveEditToServer()
{
	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}
	
	TANGER_OCX_filename = document.all.item("filename").value;
	
	if ( (!TANGER_OCX_filename))
	{
		TANGER_OCX_filename ="";
		return;
	}
	else if (strtrim(TANGER_OCX_filename)=="")
	{
		alert("您必须输入文件名！");
		return;
	}

	TANGER_OCX_SaveDoc();
}

//允许或禁止文件－>新建菜单
function TANGER_OCX_EnableFileNewMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(0) = boolvalue;
}
//允许或禁止文件－>打开菜单
function TANGER_OCX_EnableFileOpenMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(1) = boolvalue;
}
//允许或禁止文件－>关闭菜单
function TANGER_OCX_EnableFileCloseMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(2) = boolvalue;
}
//允许或禁止文件－>保存菜单
function TANGER_OCX_EnableFileSaveMenu(boolvalue)
{
	TANGER_OCX_OBJ.FileSave = boolvalue;
}
//允许或禁止文件－>另存为菜单
function TANGER_OCX_EnableFileSaveAsMenu(boolvalue)
{
	TANGER_OCX_OBJ.FileSaveAs = boolvalue;
}
//允许或禁止文件－>打印菜单
function TANGER_OCX_EnableFilePrintMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(5) = boolvalue;
}
//允许或禁止文件－>打印预览菜单
function TANGER_OCX_EnableFilePrintPreviewMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(6) = boolvalue;
}



function TANGER_OCX_OnDocumentOpened(str, obj)
{
	TANGER_OCX_bDocOpen = true;		
}

function TANGER_OCX_OnDocumentClosed()
{
   TANGER_OCX_bDocOpen = false;
}


function TANGER_OCX_SaveAsHTML()
{
	var newwin,newdoc;

	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}
	try
	{
		//调用控件的PublishAsHTMLToURL函数
		var retHTML = TANGER_OCX_OBJ.PublishAsHTMLToURL
			(
				"uploadhtmls.jsp",
				"HTMLFILES", //文件输入域名称,可任选,所有相关文件都以此域上传
				"",
				document.forms[0].htmlfile.value
				//此处省略了第5个参数，HTML FORM得索引或者id.这样,不会提交表单数据
				//只提交所有得html文件相关得文件
			);
	//	newwin = window.open("","_blank","left=200,top=200,width=400,height=300,status=0,toolbar=0,menubar=0,location=0,scrollbars=1,resizable=1",false);
	//	newdoc = newwin.document;
	//	newdoc.open();
	//	newdoc.write("<center><hr>"+retHTML+"<hr><input type=button VALUE='关闭窗口' onclick='window.close()'></center>");
	//	newdoc.close();	
	//	newwin.focus();
	//	if(window.opener) //如果父窗口存在，刷新并关闭当前窗口
	//	{
	//		window.opener.location.reload();
	//	}
	}
	catch(err){
		alert("不能保存到URL：" + err.number + ":" + err.description);
	}
	finally{
	}
}

////////////////SaveDOC AJAX

function TANGER_OCX_SaveDoc_DOJO(aform)
{
	var newwin,newdoc;



	if(!TANGER_OCX_bDocOpen)
	{
		alert("!TANGER_OCX_bDocOpen");
		return;
	}
	try
	{
	 	if(!TANGER_OCX_doFormOnSubmit())return; //??????????????????????onsubmit??????
	 	//??????????SaveToURL????
		var retHTML = TANGER_OCX_OBJ.SaveToURL
		(
			 globalUrl +  "exedo/webv3/upload_action.jsp",  //
			"wordpage",	//??????????????,??????,????????<input type=file name=..>??name????????????
			"", //??????????????????????????????&??????????myname=tanger&hisname=tom,????????
			document.forms[aform].oa_insice_report_filename.value, //document.forms[aform].oa_insice_report_filename.value
			aform //??????????????????????????????????????????????????????.??????????id????????
		); //??????????????????????????????????????????????????
		//??????????????????????????
	
	}
	catch(err){
		alert("??????????URL??" + err.number + ":" + err.description);
	}
	finally{
	}
}

function TANGER_OCX_SaveDoc()
{
	var newwin,newdoc;

	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}
	
	try
	{
	 	if(!TANGER_OCX_doFormOnSubmit())return; //如果存在，则执行表单的onsubmit函数。
	 	//调用控件的SaveToURL函数
	
		var retHTML = TANGER_OCX_OBJ.SaveToURL
		(
			
			document.forms[0].action,  //此处为uploadedit.asp
			"EDITFILE",	//文件输入域名称,可任选,不与其他<input type=file name=..>的name部分重复即可,即word控件把控件本省作为editfile录入域
			"", //可选的其他自定义数据－值对，以&分隔。如：myname=tanger&hisname=tom,一般为空
			document.forms[0].filename.value, //文件名,此处从表单输入获取，也可自定义
			"myForm" //控件的智能提交功能可以允许同时提交选定的表单的所有数据.此处可使用id或者序号
		); //此函数会读取从服务器上返回的信息并保存到返回值中。
		//打开一个新窗口显示返回数据
	//	newwin = window.open("","_blank","left=200,top=200,width=400,height=300,status=0,toolbar=0,menubar=0,location=0,scrollbars=1,resizable=1",false);
	//	newdoc = newwin.document;
//		newdoc.open();
//		newdoc.write("<html><head><title>返回的数据</title></head><body><center><hr>")
//		newdoc.write(retHTML+"<hr>");
//		newdoc.write("<input type=button VALUE='关闭窗口' onclick='window.close()'>");
//		newdoc.write('</center></body></html>');
	//	newdoc.close();
	//	if(window.opener) //如果父窗口存在，刷新并关闭当前窗口
	//	{
	//		window.opener.location.reload();
	//	}
	//	if(0 == TANGER_OCX_OBJ.StatusCode)window.close();
	}
	catch(err){
		alert("不能保存到URL：" + err.number + ":" + err.description);
	}
	finally{
	}
}


function TANGER_OCX_SaveToServer()
{
	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}
	
	TANGER_OCX_filename = prompt("附件另存为：","新文档.doc");
	if ( (!TANGER_OCX_filename))
	{
		TANGER_OCX_filename ="";
		return;
	}
	else if (strtrim(TANGER_OCX_filename)=="")
	{
		alert("您必须输入文件名！");
		return;
	}
	//alert(TANGER_OCX_filename);
	TANGER_OCX_SaveDoc();
}


function TANGER_OCX_OpenDoc(docid)
{

   //alert(docid);

	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	if(docid != "")
	{	//"readdoc.jsp?docid=" + 
		TANGER_OCX_OBJ.BeginOpenFromURL(docid);
	}
	else
	{
		TANGER_OCX_OBJ.CreateNew("Word.Document");
	}
}


////////////////////设置留痕用户名
function TANGER_OCX_SetDocUser(cuser)
{

//////////////////////
  try{
  if(TANGER_OCX_bDocOpen){
	with(TANGER_OCX_OBJ.ActiveDocument.Application)
	{
		UserName = cuser;//设置WORD里面的用户信息
		UserInitials=UserName;//设置WORD里面的用户信息-缩写信息
		Company="吉林油田";

	}	
   }
  }catch(e)	{
    alert(e);
  }
}





function getStandardDate(d){

    var s = "";
    s += d.getYear() + "-";
    
    var intMonth = d.getMonth() + 1;
    var sMonth = "" + intMonth; 
   // alert(sMonth.length);
  
    
    if(sMonth.length == 1){
       s += "0" + sMonth+ "-";
    }else{
       s += (d.getMonth() + 1) + "-";
    }
    s += d.getDate() + " ";
    s += d.getHours() + ":";
	s += d.getMinutes();
    return s;
}



////////获取给定用户的所有批注
function  DO_GetRevisions(cuser,cbiuid){


	TANGER_OCX_SaveEditToServer();

  //var postData = {'invokePaneName': this.invokePaneName ,'invokeButtonName':this.invokeButtonName,'contextServiceUid': this.actionName,'contextInstanceUid':this.contextInstanceUid,'contextClassUid':this.contextClassUid};

	
    var deleData = {'contextServiceName': 'word.revisions.deleteAll'};
	eval( "deleData." + encodeURIComponent("bsbiuid") + "=['" + escape(cbiuid) +"']" );
	eval( "deleData." + encodeURIComponent("authorname") + "=['" + escape(cuser) +"']" );
	doAjax.ajaxPostData(deleData);
	
	
	
//	doAjax.ajaxPostData();
////,
 
	
	
	var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
	var app = mydoc.Application; //得到Application对象
	//alert("应用名称：" + app.Name); //通过Application对象的Name属性得到应用名称
	app.UserName = cuser; //通过Application对象的UserName属性设定痕迹保留用户名
	if(TANGER_OCX_OBJ.DocType == 1) //如果打开的是Word文档
	{ 
		var cou = mydoc.Revisions.Count;
		//alert("文档中的修订数量:" + cou ); //通过Document的Revisions集合访问修订并得到其数量
		
		var rev = "";
		for(var i=1;i<=cou;i++)
		{
			if	(TANGER_OCX_OBJ.ActiveDocument.Revisions(i).Author==cuser){
			    var postData = {'contextServiceName': 'word.revisions.insert'};
				var typ="";
				var author;
				
				var range=TANGER_OCX_OBJ.ActiveDocument.Revisions(i).Range;//获取修改的内容
				var revDate = TANGER_OCX_OBJ.ActiveDocument.Revisions(i).Date;////留痕的时间，但是是date类型不可用
				var author = TANGER_OCX_OBJ.ActiveDocument.Revisions(i).Author;	/////////作者		
				var strDate;////留痕时间 把revDate转化为Date类型，再转化为字符串
				var revType = TANGER_OCX_OBJ.ActiveDocument.Revisions(i).TYPE;
				if(revDate!=null){
				
					var  newDate = new Date(revDate);
					strDate = getStandardDate(newDate);
				}
				
				eval( "postData." + encodeURIComponent("authorname") + "=['" + escape(author) +"']" );
				eval( "postData." + encodeURIComponent("revisiondate") + "=['" + escape(strDate) +"']" );
				eval( "postData." + encodeURIComponent("revisiontype") + "=['" + escape(revType) +"']" );
				eval( "postData." + encodeURIComponent("revisiondoc") + "=['" + escape(range) +"']" );
				eval( "postData." + encodeURIComponent("bsbiuid") + "=['" + escape(cbiuid) +"']" );
				
				//cbiuid
				
				doAjax.ajaxPostData(postData);
			
				//判断修订类型,值1为插入,值2为删除
				if(1==TANGER_OCX_OBJ.ActiveDocument.Revisions(i).TYPE){
					typ="插入修订"
				}	
				else{
					typ="删除修订"
				}
				rev+=(author+":"+typ+"内容 "+range+",时间" + strDate + "\n");
			}
			
			
			
			
			//wdNoRevision  0 
			//wdRevisionConflict  7 
			//wdRevisionDelete  2 
			//wdRevisionDisplayField  5 
			//wdRevisionInsert  1 
			///wdRevisionParagraphNumber  4 
			//wdRevisionParagraphProperty  10 
			//wdRevisionProperty  3 
			//wdRevisionReconcile  6 
			//wdRevisionReplace  9 
			//wdRevisionSectionProperty  12 
			//wdRevisionStyle  8 
			//wdRevisionStyleDefinition  13 
			//wdRevisionTableProperty  11 
			
		}
	}
	

	
}



	
	function openWordPicPane(){
	
	     doAjax.initExedo();
	     doAjax.formName='aabpdesgn200707162033456530000005';
	     doAjax.invokeButtonName='chakan';
	     doAjax.invokePaneName='wordpicmanager.Result.Panel';
	     doAjax.refresh('dojo.openwindow','/abp/mvccontroller?paneModelUid=abpdesgn200707162035583900000016&contextInstanceUid=33&contextClassUid=4028c38113cef4ce0113cef5deba0030','')
	      
	}
	
	
	function addPictureFromSelect(){
	
	 try{
	    var form = document.forms["aabpdesgn200707162033456530000005"];
	        
		    var el;
		    var sData = "";
		    var sDataTitle = "";
		    for (var i = 0; i < form.elements.length; i++) {
		        el = form.elements[i];
	
		        if(el.tagName.toLowerCase() == "input" && 
		        (el.type.toLowerCase() == "checkbox" ||  el.type.toLowerCase() == "radio")){
		             if(el.checked){
		            	 sData  = el.value;
		            	 sDataTitle = el.title;
			        //     multData = multData +  el.value + ",";
			        //     multDataTitle = multDataTitle +   el.title + ",";
			             break;
		             }
		             
		        } 
		    }
		    
		    AddPictureFromURL(PathUrl + sDataTitle);
		  //	TANGER_OCX_OpenDoc("http://127.0.0.1/abp/upload/<%=docName%>"); 
		   // alert(sData);
		  //   alert(sDataTitle);
		 //   if(this.actionName!=null && this.action!="")    {
		 //     this.ajaxAction();
		  //  }
	    }catch(e){
	    
	     alert(e);
	    }

  }
	


function initSpCustomMenus()
{

	TANGER_OCX_OBJ.AddCustomMenu2(8,"审核工具"); 
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,0,-1,false,"保存到服务器",false,1);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,1,-1,false,"插入数据",false,17);

}
function initspMenu()
{
	TANGER_OCX_OBJ.AddCustomMenu2(9,"保存"); 
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,0,-1,false,"保存到服务器",false,0);
}
//增加自定义菜单 add by zhou 2008\3\20   by yanglin09\05\04
function initCustomMenusbsjy()
{
	TANGER_OCX_OBJ.AddCustomMenu2(8,"辅助编写"); 
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,0,-1,false,"保存到服务器",false,1);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,1,-1,false,"电子签章",false,19);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,2,-1,false,"清除签章",false,20);
}

//增加邻井信息 add by zhou 2007\11\02
	    dojo.require("dojo.io.*");
		dojo.require("dojo.event.*");
		dojo.hostenv.writeIncludes();
//获取邻井钻探成果表内容 by zjm 080527
function getljxx(ljjh) {
             
             
       
		  
	   	   dojo.io.bind({
				    url: "json_ljcg_data.jsp?data1="+ljjh,
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { ljxxCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
		
		
			
		}
		
function ljxxCallback(data){
	  
	   	eval("var aaa = " + data);
			
		arr=aaa;
	
	}
//添加邻井钻探成果
function addljxx(juids) {

   var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(4);
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getljxx(juidshuzu[i]);
        var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
          
          var r =Rows.Count;

		 Cell(r,12).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][4]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][5]);
             Cell(r,7).Range.InsertAfter(arr[r-numr][6]);
             Cell(r,8).Range.InsertAfter(arr[r-numr][7]);
             Cell(r,9).Range.InsertAfter(arr[r-numr][8]);
             Cell(r,10).Range.InsertAfter(arr[r-numr][9]);
             Cell(r,11).Range.InsertAfter(arr[r-numr][10]);
			 Cell(r,12).Range.InsertAfter(arr[r-numr][11]);
			 Cell(r,13).Range.InsertAfter(arr[r-numr][12]);
			 Cell(r,13).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	

}

/*function addljxx(ljjh) {
             
             
       
		  
	   	   dojo.io.bind({
				    url: "/abp/liaohe/get_ljxx.jsp?ljjh="+escapeWith__(ljjh),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { ljxxCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
		
		
			
		}
		
function ljxxCallback(data){
	  
	   
	     eval("var LjcxAjax = " + data);
	   jh = LjcxAjax.JH; 
	   wzcw = LjcxAjax.WZ_CW;
	   wzjs = LjcxAjax.WZ_JS;
	   dzfc = LjcxAjax.DZFC;
	   jsjg = LjcxAjax.JSJG; 
	   syjs = LjcxAjax.SYJG;
	     
	   allljxx =allljxx +"\n"+ jh+"\n"+" 完钻层位："+ wzcw +"完钻井深："+ wzjs+"\n" +" 地质分层："+ dzfc+"\n"+" 解释结果："+ jsjg+"\n"+"  试油结果："+ syjs;
 
       setljxx();
       
	}

function setljxx()
{
 
  TANGER_OCX_OBJ.SetBookmarkValue("LJXX",allljxx);
}*/	



	
//获取表格数据

function getJinInfoData(value)
		{
			dojo.io.bind({
				    url: "json_data.jsp?data1="+escapeWith__(value),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { JinInfoCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function JinInfoCallback(data)
		{
			data = data.replace("\r","").replace("\n","");
			eval("var aaa = " + data);
		arr=aaa;
		
		}	



//获取邻井钻井液使用情况
function getJinInfoZJYData(value)
		{
			dojo.io.bind({
				    url: "json_zjy_data.jsp?data1="+escapeWith__(value),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { JinInfoCallbackzjy(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function JinInfoCallbackzjy(data)
		{
			eval("var aaa = " + data);
			
		    arr=aaa;
			
		}

//获取邻井实测压力使用情况
function getJinInfoSCYLData(value)
		{
			dojo.io.bind({
				    url: "json_scyl_data.jsp?data1="+escapeWith__(value),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { JinInfoCallbackscyl(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function JinInfoCallbackscyl(data)
		{
			eval("var aaa = " + data);
			
		    arr=aaa;
		}


//获取邻井测温情况
function getJinInfoCWData(value)
		{
			dojo.io.bind({
				    url: "json_cw_data.jsp?data1="+escapeWith__(value),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { JinInfoCallbackcw(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function JinInfoCallbackcw(data)
		{
			eval("var aaa = " + data);
			
		    arr=aaa;
		}

//根据自己分层获取邻井钻井液使用情况
function getZJYDatabyid(value)
		{   
			dojo.io.bind({
				    url: "json_zjy_data_byid.jsp?data1="+value,
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { JinInfoCallbackzjy(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function JinInfoCallbackzjy(data)
		{
			eval("var aaa = " + data);
			
		    arr=aaa;
			
		}

//添加钻井液表格
function zjyAddTable(juids)
{       
	    
        var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(11);
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getZJYDatabyid(juidshuzu[i]);
        var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
          
          var r =Rows.Count;

		 Cell(r,6).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][4]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][5]);
			 Cell(r,6).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	
 
}
//添加产层实测压力
function scylAddTable(juids)
{       
        var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(8);
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getJinInfoSCYLData(juidshuzu[i]);
        var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
          
          var r =Rows.Count;

		 Cell(r,7).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][4]);
			 Cell(r,7).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	
 
} 

//添加邻井测温情况   by zjm 0509

function cwAddTable(juids)
{       
var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var cwTable = tables(12);
            with(cwTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getJinInfoCWData(juidshuzu[i]);
        var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
         
		 Cell(r,6).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][4]);
			 Cell(r,6).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        myRange = mydoc.Range(Cell(1,4).Range.Start,Cell(1,5).Range.End);
        myRange.Cells.Merge();
         Cell(1,1).Merge((Cell(2,1)));
         Cell(1,2).Merge((Cell(2,2)));
		 Cell(1,3).Merge((Cell(2,3)));
        }
		}
	
} 
//添加表格数据

function testAddTable(juids)
{

        var juidshuzu;
        juidshuzu = juids.split(",");
        getJinInfoData(juidshuzu[0]);
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
        var sel = app.Selection; //得到Selection对象
		sel.EndKey(6,0);
        sel.TypeParagraph();
        sel.Style = mydoc.Styles("style1")
        var  isdate= new Date(); 
		var year=isdate.getFullYear();//
		var biaoti = arr[0][0]+arr[0][1]+year+"年度井位部署表";
        sel.TypeText(biaoti);
        sel.TypeParagraph();
        sel.Style = mydoc.Styles("正文")
        

        var tables = mydoc.Tables;
        
        var newTable = tables.Add(sel.Range,4,23,1,0);
        newTable.Rows.Alignment=1;

		
        with(newTable)
        {
            if(style != "网格型")
               {
                  style = "网格型";
               }
			Columns(1).PreferredWidthType =3;
			Columns(1).PreferredWidth = 20;
			Columns(2).PreferredWidthType =3;
			Columns(2).PreferredWidth = 20;
			Columns(3).PreferredWidthType =3;
			Columns(3).PreferredWidth = 20;
			Columns(4).PreferredWidthType =3;
			Columns(4).PreferredWidth = 20;
			Columns(5).PreferredWidthType =3;
			Columns(5).PreferredWidth = 70;
			Columns(6).PreferredWidthType =3;
			Columns(6).PreferredWidth = 40;
			Columns(7).PreferredWidthType =3;
			Columns(7).PreferredWidth = 40;
			Columns(8).PreferredWidthType =3;
			Columns(8).PreferredWidth = 40;
			Columns(9).PreferredWidthType =3;
			Columns(9).PreferredWidth = 40;
			Columns(10).PreferredWidthType =3;
			Columns(10).PreferredWidth = 40;
			Columns(11).PreferredWidthType =3;
			Columns(11).PreferredWidth = 40;
			Columns(12).PreferredWidthType =3;
			Columns(12).PreferredWidth = 40;
			Columns(13).PreferredWidthType =3;
			Columns(13).PreferredWidth = 40;
			Columns(14).PreferredWidthType =3;
			Columns(14).PreferredWidth = 100;
			Columns(15).PreferredWidthType =3;
			Columns(15).PreferredWidth = 40;
			Columns(16).PreferredWidthType =3;
			Columns(16).PreferredWidth = 40;
			Columns(17).PreferredWidthType =3;
			Columns(17).PreferredWidth = 40;
			Columns(18).PreferredWidthType =3;
			Columns(18).PreferredWidth = 30;
			Columns(19).PreferredWidthType =3;
			Columns(19).PreferredWidth = 30;
			Columns(20).PreferredWidthType =3;
			Columns(20).PreferredWidth = 30;
			Columns(21).PreferredWidthType =3;
			Columns(21).PreferredWidth = 30;
			Columns(22).PreferredWidthType =3;
			Columns(22).PreferredWidth = 100;
			Columns(23).PreferredWidthType =3;
			Columns(23).PreferredWidth = 100;
   
        Cell(1,1).Range.InsertAfter("序号");
        Cell(1,2).Range.InsertAfter("坳陷");
        Cell(1,3).Range.InsertAfter("凹陷");
        Cell(1,4).Range.InsertAfter("区带或地区");
        Cell(1,5).Range.InsertAfter("圈闭情况");
        Cell(1,14).Range.InsertAfter("部署情况");
        Cell(1,20).Range.InsertAfter("钻井取心");
        
        Cell(2,5).Range.InsertAfter("圈闭名称");
        Cell(2,6).Range.InsertAfter("层位");
        Cell(2,7).Range.InsertAfter("类型");
        Cell(2,8).Range.InsertAfter("面积km2");
        Cell(2,9).Range.InsertAfter("幅度m");
        Cell(2,10).Range.InsertAfter("埋深");
        
        Cell(2,11).Range.InsertAfter("预测资源量");
        Cell(2,13).Range.InsertAfter("落实程度");
        Cell(2,14).Range.InsertAfter("测线位置");
        Cell(2,15).Range.InsertAfter("井号");
        Cell(2,16).Range.InsertAfter("井别");
        Cell(2,17).Range.InsertAfter("目的层");
        Cell(2,18).Range.InsertAfter("设计井深m");
        Cell(2,19).Range.InsertAfter("完钻层位");
        
        Cell(2,20).Range.InsertAfter("层位");
        Cell(2,21).Range.InsertAfter("取心进尺m");
        Cell(2,22).Range.InsertAfter("对比井段");
        Cell(2,23).Range.InsertAfter("备注");
        
        Cell(3,11).Range.InsertAfter("油*104t");
        Cell(3,12).Range.InsertAfter("气*108m3");
        AutoFitBehavior (0);

		 sel.Find.Forward =true;
         sel.Find.Wrap=1;
         sel.Find.Execute("2");
         sel.Font.Superscript=9999998;

         sel.Find.Forward =true;
         sel.Find.Wrap=1;
         sel.Find.Execute("4");
         sel.Font.Superscript=9999998;
         
         
         sel.Find.Forward =true;
         sel.Find.Wrap=1;
         sel.Find.Execute("8");
         sel.Font.Superscript=9999998;
         
         sel.Find.Forward =true;
         sel.Find.Wrap=1;
         sel.Find.Execute("3");
         sel.Font.Superscript=9999998;


        for(i=0;i<juidshuzu.length-1;i++)
        {
			  //sel.EndKey(6,0);
              //sel.MoveLeft(1,1,0);
              //sel.MoveRight(12,1,1);

        var numr = Rows.Count;
		//alert(numr);
        getJinInfoData(juidshuzu[i]);
        var r =Rows.Count;
        
        for(r;r<numr+arr.length;r++)
          
          {
             Cell(r,1).Range.InsertAfter(i+1);
             for(c=2;c<24;c++)
             {
                Cell(r,c).Range.InsertAfter(arr[r-numr][c-2]);
             }
              sel.EndKey(6,0);
              sel.MoveLeft(1,1,0);
              sel.MoveRight(12,1,1);
          }
        
        }
		
		sel.Rows.Delete();
                //合并表头
        myRange = mydoc.Range(Cell(1,5).Range.Start,Cell(1,13).Range.End);
        myRange.Cells.Merge();
        
        myRange = mydoc.Range(Cell(1,6).Range.Start,Cell(1,11).Range.End);
        myRange.Cells.Merge();
        
        
        myRange = mydoc.Range(Cell(1,7).Range.Start,Cell(1,10).Range.End);
        myRange.Cells.Merge();

          
          
         
        
        myRange = mydoc.Range(Cell(2,11).Range.Start,Cell(2,12).Range.End);
        myRange.Cells.Merge();
        
      
        Cell(1,1).Merge((Cell(3,1)));
        Cell(1,2).Merge((Cell(3,2)));
        Cell(1,3).Merge((Cell(3,3)));
        Cell(1,4).Merge((Cell(3,4)));
       
        
        Cell(2,5).Merge((Cell(3,5)));
        Cell(2,6).Merge((Cell(3,6)));
        Cell(2,7).Merge((Cell(3,7)));
        Cell(2,8).Merge((Cell(3,8)));
        Cell(2,9).Merge((Cell(3,9)));
        Cell(2,10).Merge((Cell(3,10)));
        Cell(2,12).Merge((Cell(3,13)));
   
        Cell(2,13).Merge((Cell(3,14)));
        Cell(2,14).Merge((Cell(3,15)));
        Cell(2,15).Merge((Cell(3,16)));
        Cell(2,16).Merge((Cell(3,17)));
        Cell(2,17).Merge((Cell(3,18)));
        Cell(2,18).Merge((Cell(3,19)));
        Cell(2,19).Merge((Cell(3,20)));
        Cell(2,20).Merge((Cell(3,21)));
        Cell(2,21).Merge((Cell(3,22)));
        Cell(2,22).Merge((Cell(3,23)));
		

		var rstr = "×"
		ReplaceText(rstr);
         sel.EndKey(6,0);
         var qmurl ="";
		 
         qmurl = PathUrl+juidshuzu[0]+".jpg"
         AddPictureFromURL(qmurl);
//sel.InsertBreak(2);

//Cell(2,2).Range.Orientation =1;
//if(Cell(3,2).Range.Text==Cell(2,2).Range.Text)
//{
//Cell(3,2).Range.Text="";
//Cell(2,2).Merge((Cell(3,2)));
//}
 
}
 

}
	
	//图件查询
			function getTJData(value,tutype)
		{
		 
			dojo.io.bind({
				    url: "json_tz_data.jsp?data1="+value+"&data2="+tutype,
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { TJCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function TJCallback(data)
		{
			eval("var aaa = " + data);
			arr=aaa;
			
		}
	//其他图件查询 add by zjm 20080804
		function getQTJData(value)
		{
		 alert("4");
			dojo.io.bind({
				    url: "json_qtt_data.jsp?data1="+value,
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { QTJCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function QTJCallback(data)
		{
			eval("var aaa = " + data);
			arr=aaa;
		} 
//添加部署图数据到纪要

function insertTJtojy(juids)
{
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
        var sel = app.Selection; //得到Selection对象
		sel.EndKey(6,0);
        sel.InsertBreak(2); 
        

        var juidshuzu;
        juidshuzu = juids.split(",");
        for(i=0;i<juidshuzu.length-1;i++)
        {
        
		getTJData(juidshuzu[i],1);

        for(j=0;j<arr.length;j++)
          
          {
			 var url = PathUrl+arr[j][0];
			 
             AddPictureFromURL(url);
			 //AddPictureFromURL("http://10.70.36.26:8082/abp/upload/20080320/0_2007-04-18-09-14-53_Page_03_Image_0001.jpg")
          }

        getTJData(juidshuzu[i],2);

		 
        for(k=0;k<arr.length;k++)
          
          {
             var pouurl = PathUrl+arr[k][0];
             AddPictureFromURL(pouurl);
          }
       
		}
       
		
 
}






//添加其他图图数据到纪要 add  by zjm 20080804

function insertQTJ(juids)
{
       
        var juidshuzu;
        juidshuzu = juids.split(",");
        for(i=0;i<juidshuzu.length-1;i++)
        {
    
		getQTJData(juidshuzu[i]);

        for(j=0;j<arr.length;j++)
          
          {
			 var url = Prl+arr[j][0];
		
             AddPictureFromURL(url);
          }
		}
  
}
	//地质设计附属图件查询 by zjm 2008 5 5
			function getFJData(value)
		{
		 
			dojo.io.bind({
				    url: "json_fj_data.jsp?data1="+value,
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { TJCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); 
					}
		  });
		}
		function TJCallback(data)
		{
			eval("var aaa = " + data);
			arr=aaa;
			
		}

//添加地质设计附图到地质设计

function addfstj(juid)
{
		//alert("插入附图2");
	 	var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
        var sel = app.Selection; //得到Selection对象
		sel.EndKey(6,0);
        //sel.InsertBreak(2); 
		var myrange = mydoc.Range(sel.Start,mydoc.Content.End);
        var pageset = myrange.PageSetup;
		pageset.Orientation = 1;
        pageset.PageWidth = 600;
        //pageset.PageHeight = 500;
        
       
		getFJData(juid);

        for(j=0;j<arr.length;j++)
          
          {
          //alert(arr[j][0]);
			 //var url = "http://127.0.0.1:8006/abp/upload/"+arr[j][0];
			  var url = PathUrl+arr[j][0];
              AddPictureFromURL(url);
              //sel.TypeText ="ahdjahdjkahdakj大厦大厦大大";
             //AddPictureFromURL(arr[j][0]);
			 //AddPictureFromURL("http://10.70.36.26:8082/abp/upload/20080320/0_2007-04-18-09-14-53_Page_03_Image_0001.jpg")
          }
}


//导入历史设计文档
function insertLSwd(cflj)
 
{      
        
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
        var sel = app.Selection; //得到Selection对象
		sel.EndKey(6,0);
        sel.InsertBreak(2); 
        var lj = "http://127.0.0.1:1323/Jurassic.BankServer/FilePreDeal.aspx?AttachmentNo=34360dc0-0ec2-4051-ae0d-db0fc6584a43";
		//var lj = "http://10.77.1.60/Jurassic.BankServer/FilePreDeal.aspx?AttachmentNo=5ad150de-563d-471b-8b6d-17802f9cc999";
		//var ll = lj.trim();
       // zslj =lj.replace("//","//pedis:sanguoyanyi@");
        //
        sel.InsertFile(lj, "", false, false,false);
		alert("11111111");
} 

  //获得地质分层数据   add by yang 2009\03\30
function getdzfc(data1) {
             
           
	   	   dojo.io.bind({
				    url: "json_diceng_data.jsp?data1="+escapeWith__(data1),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { dzfcCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
		
		
			
		}
		
function dzfcCallback(data){
	 
	   	eval("var aaa = " + data);
	
		arr=aaa;
		
	
	
	
	}

// 添加地层分层  add by yang 2009\03\30

 function adddzfc(juids) {
	
		
	
   		var juidshuzu;
        juidshuzu = juids.split(",");
		
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
        if(jb1=='评价井')
        {
        	var zjyTable = tables(15);
        }
        else
        {
        	var zjyTable = tables(11);
        }
		
		zjyTable.Cell(1,2).range.Text;
		
        with(zjyTable)
        {
         getdzfc(juidshuzu[0]);
         var num=(Columns.Count-4)/2;
         
		 for(var i=0;i<num;i++){
		 	var title = Cell(1,i+2).range.Text;
		 	if(title.length<=2)
		 		break;
		 }
		 
		 if(i>=num)
		 {
		 	return;
		 }
            
			Cell(1,i+2).Range.InsertAfter(arr[0][0]);

            Cell(3,2*i+5).Range.InsertAfter(arr[0][1]);
			
			Cell(4,2*i+5).Range.InsertAfter(arr[0][2]);
			 
			if(arr[0][2]>0){
				Cell(4,2*i+6).Range.InsertAfter(arr[0][2]-arr[0][1]);
			 }
			 
			 
			 Cell(5,2*i+5).Range.InsertAfter(arr[0][3]);
			 
			 if(arr[0][3]>0){
				Cell(5,2*i+6).Range.InsertAfter(arr[0][3]-arr[0][2]);
			 }
			
             Cell(6,2*i+5).Range.InsertAfter(arr[0][4]);
			 
			 if(arr[0][4]>0){
				Cell(6,2*i+6).Range.InsertAfter(arr[0][4]-arr[0][3]);
			 }
			
			 Cell(7,2*i+5).Range.InsertAfter(arr[0][5]);
			 
			 if(arr[0][5]>0){
				Cell(7,2*i+6).Range.InsertAfter(arr[0][5]-arr[0][4]);
			 }
			 
			 Cell(8,2*i+5).Range.InsertAfter(arr[0][6]);
			 
			 if(arr[0][6]>0){
				Cell(8,2*i+6).Range.InsertAfter(arr[0][6]-arr[0][5]);
			 }
			 
			 Cell(9,2*i+5).Range.InsertAfter(arr[0][7]);
			 
            if(arr[0][7]>0){
				Cell(9,2*i+6).Range.InsertAfter(arr[0][7]-arr[0][6]);
			 }
			 
			 Cell(10,2*i+5).Range.InsertAfter(arr[0][8]);
			 
			if(arr[0][8]>0){
				Cell(10,2*i+6).Range.InsertAfter(arr[0][8]-arr[0][7]);
			 }
			 
			 Cell(11,2*i+5).Range.InsertAfter(arr[0][9]);
			 
			if(arr[0][9]>0){
				Cell(11,2*i+6).Range.InsertAfter(arr[0][9]-arr[0][8]);
			 }
			 
			 Cell(12,2*i+5).Range.InsertAfter(arr[0][10]);
			 
			if(arr[0][10]>0){
				Cell(12,2*i+6).Range.InsertAfter(arr[0][10]-arr[0][9]);
			 }
			 
			 Cell(13,2*i+5).Range.InsertAfter(arr[0][11]);
			 
            if(arr[0][11]>0){
				Cell(13,2*i+6).Range.InsertAfter(arr[0][11]-arr[0][10]);
			 }
			 
			 Cell(14,2*i+5).Range.InsertAfter(arr[0][12]);
			 
			if(arr[0][12]>0){
				Cell(14,2*i+6).Range.InsertAfter(arr[0][12]-arr[0][11]);
			 }
			 
			 Cell(15,2*i+5).Range.InsertAfter(arr[0][13]);
			 
			 if(arr[0][13]>0){
				Cell(15,2*i+6).Range.InsertAfter(arr[0][13]-arr[0][12]);
			 }
			 
			 Cell(16,2*i+5).Range.InsertAfter(arr[0][14]);
			 
			 if(arr[0][14]>0){
				Cell(16,2*i+6).Range.InsertAfter(arr[0][14]-arr[0][13]);
			 }
			 
			 Cell(17,2*i+5).Range.InsertAfter(arr[0][15]);
			 
			if(arr[0][15]>0){
				Cell(17,2*i+6).Range.InsertAfter(arr[0][15]-arr[0][14]);
			 }
			 
			 Cell(18,2*i+5).Range.InsertAfter(arr[0][16]);
			 
			if(arr[0][16]>0){
				Cell(18,2*i+6).Range.InsertAfter(arr[0][16]-arr[0][15]);
			 }
			 
			 Cell(19,2*i+5).Range.InsertAfter(arr[0][17]);
			 
			if(arr[0][17]>0){
				Cell(19,2*i+6).Range.InsertAfter(arr[0][17]-arr[0][16]);
			 }
			 
			 Cell(20,2*i+5).Range.InsertAfter(arr[0][18]);
			 
			 if(arr[0][18]>0){
				Cell(20,2*i+6).Range.InsertAfter(arr[0][18]-arr[0][17]);
			 }
			 
			Cell(21,2*i+5).Range.InsertAfter(arr[0][19]);
			
			 if(arr[0][19]>0){
				Cell(21,2*i+6).Range.InsertAfter(arr[0][19]-arr[0][18]);
			 }
			 
			 Cell(22,2*i+4).Range.InsertAfter(arr[0][20]);
			 
			 if(arr[0][20]>0){
				Cell(22,2*i+5).Range.InsertAfter(arr[0][20]-arr[0][19]);
			 }
			 
			 Cell(23,2*i+4).Range.InsertAfter(arr[0][21]);
			 
			if(arr[0][21]>0){
				Cell(23,2*i+5).Range.InsertAfter(arr[0][21]-arr[0][20]);
			 }
			 
			 Cell(24,2*i+4).Range.InsertAfter(arr[0][22]);

			if(arr[0][22]>0){
				Cell(24,2*i+5).Range.InsertAfter(arr[0][22]-arr[0][21]);
			 }
			 
			 Cell(25,2*i+4).Range.InsertAfter(arr[0][23]);
			 
			 if(arr[0][23]>0){
				Cell(25,2*i+5).Range.InsertAfter(arr[0][23]-arr[0][22]);
			 }
		
			 Cell(26,i+3).Range.InsertAfter(arr[0][25]);

			 Cell(27,i+3).Range.InsertAfter(arr[0][26]);
			 
			 Cell(28,i+2).Range.InsertAfter(arr[0][27]);  
        }

}








	// 添加岩屑显示数据  add by yang 2009\03\30

 function addljxs(juids) {
	
        var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(12);
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getljxs(juidshuzu[i]);
	
           var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
          
          var r =Rows.Count;
         if (r==2)
         {
			 Cell(1,7).Select();
         }else
		 {
			Cell(r+1,7).Select();
		 }
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r+1,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r+1,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][4]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][5]);
             Cell(r,7).Range.InsertAfter(arr[r-numr][6]);
             Cell(r,8).Range.InsertAfter(arr[r-numr][7]);
			 Cell(r,8).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	

}

 //获得岩屑显示数据   add by yang 2009\03\30
function getljxs(data1) {
		  
	   	   dojo.io.bind({
				    url: "json_lujingxianshi_data.jsp?data1="+escapeWith__(data1),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { ljxsCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
			
		}
		
function ljxsCallback(data){
		
	   	eval("var aaa = " + data);
			
		arr=aaa;

		
	
	}
	
	//增加气测显示数据 add by yang 20090825
	
	function addqcxs(juids) {
		
        var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(13);
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getqcxs(juidshuzu[i]);
        var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
          
          var r =Rows.Count;
          Cell(r,8).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][4]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][5]);
             Cell(r,7).Range.InsertAfter(arr[r-numr][6]);
			 if(arr[r-numr][5]>0){
				Cell(r,8).Range.InsertAfter((arr[r-numr][6]/arr[r-numr][5]).toFixed(3));
			 }
             
			 Cell(r,9).Range.InsertAfter(arr[r-numr][8]);
			 
			 Cell(r,9).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	
	

}

 //获得气测显示数据   add by yang 20090825
function getqcxs(data1) {
		  
	   	   dojo.io.bind({
				    url: "json_qicexianshi_data.jsp?data1="+escapeWith__(data1),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { qcxsCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
			
		}
		
function qcxsCallback(data){
		
	   	eval("var aaa = " + data);
			
		arr=aaa;

		
	
	}


	// 添加测井解释成果  add by yang 2009\03\30

	 function addcjjs(juids) {

        var juidshuzu;
		
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(14);
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getcjjs(juidshuzu[i]);
        var r =Rows.Count;
        
	
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
         
          var r =Rows.Count;
          Cell(r,9).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][4]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][5]);
             Cell(r,7).Range.InsertAfter(arr[r-numr][6]);
             Cell(r,8).Range.InsertAfter(arr[r-numr][7]);
			 Cell(r,9).Range.InsertAfter(arr[r-numr][8]);
			 Cell(r,10).Range.InsertAfter(arr[r-numr][9]);
			
			 Cell(r,10).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	

}

 //获得测井解释成果数据   add by yang 2009\03\30
function getcjjs(data1) {
		  
	   	   dojo.io.bind({
				    url: "json_cejingjieshi.jsp?data1="+escapeWith__(data1),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { cjjsCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
			
		}
		
function cjjsCallback(data){
	  
	   	eval("var aaa = " + data);
		
		arr=aaa;

		
	
	}

//增加自定义菜单 add by yang 2009\03\30

function initCustom1Menus()
{
	//var myobj = TANGER_OCX_OBJ;	
	TANGER_OCX_OBJ.AddCustomMenu2(8,"邻井数据提取"); 	
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,0,-1,false,"地层分层",false,4);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,1,-1,false,"岩屑显示数据",false,5);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,2,-1,false,"气测显示",false,6);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,3,-1,false,"测井数据",false,7);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,4,-1,false,"试油数据",false,8);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,5,-1,false,"天然气组份",false,9);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,6,-1,false,"水分析数据",false,10);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,7,-1,false,"插入附图",false,13);	
	

	TANGER_OCX_OBJ.AddCustomMenu2(7,"编辑控制");
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,0,-1,false,"保存到服务器",false,1);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,1,-1,false,"显示痕迹",false,2);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,2,-1,false,"隐藏痕迹",false,3);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,3,-1,false,"插入本井基本数据",false,0);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,4,-1,false,"历史设计查询",false,-1);

	//TANGER_OCX_OBJ.AddCustomMenu2(9,"插入附图"); 
	
	//TANGER_OCX_OBJ.AddCustomMenuItem2(9,0,-1,false,"插入附图",false,13);	
	
}

function initCustom2Menus()
{
	//var myobj = TANGER_OCX_OBJ;	
	TANGER_OCX_OBJ.AddCustomMenu2(8,"邻井数据提取"); 
	
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,0,-1,false,"试油成果",false,11);	
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,1,-1,false,"地层分层",false,12);
	TANGER_OCX_OBJ.AddCustomMenuItem2(8,2,-1,false,"插入附图",false,13);	

	//TANGER_OCX_OBJ.AddCustomMenuItem2(8,2,-1,false,"产层实测压力 ",false,13);
	//TANGER_OCX_OBJ.AddCustomMenuItem2(8,3,-1,false,"破裂压力",false,14);
	//TANGER_OCX_OBJ.AddCustomMenuItem2(8,4,-1,false,"钻井液数据",false,15);
	//TANGER_OCX_OBJ.AddCustomMenuItem2(8,5,-1,false,"录井，测井成果",false,16);
	
	

	TANGER_OCX_OBJ.AddCustomMenu2(7,"编辑控制");
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,0,-1,false,"保存到服务器",false,1);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,1,-1,false,"显示痕迹",false,2);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,2,-1,false,"隐藏痕迹",false,3);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,3,-1,false,"插入本井基本数据",false,17);
	TANGER_OCX_OBJ.AddCustomMenuItem2(7,4,-1,false,"历史设计查询",false,-1);

	//TANGER_OCX_OBJ.AddCustomMenu2(9,"插入附图"); 
	
	//TANGER_OCX_OBJ.AddCustomMenuItem2(9,0,-1,false,"插入附图",false,13);	
}

function initCustom3Menus()
{

	TANGER_OCX_OBJ.AddCustomMenu2(9,"编辑控制");
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,0,-1,false,"保存到服务器",false,1);
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,1,-1,false,"显示痕迹",false,2);
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,2,-1,false,"隐藏痕迹",false,3);
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,3,-1,false,"插入本井基本数据",false,17);
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,4,-1,false,"插入附图",false,13);	
	
}
// 增加基本信息  edit by zjm 20090330

function addjbxx(wdmc,juid) {
             
             
       
		  //alert(wdmc);
	   	   dojo.io.bind({
				    url: "get_jbxx.jsp?wdmc="+escapeWith__(wdmc),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
				    sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { jbxxCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		

}
		
function jbxxCallback(data){
		
	   
	   eval("var LjcxAjax = " + data);
	 
	   jh = LjcxAjax.JH; 
	   jb = LjcxAjax.JB;
	   jx = LjcxAjax.JX;
	   sjmdc = LjcxAjax.SJMDC;
	   sjwzcw= LjcxAjax.SJWZCW; 
	   ss = LjcxAjax.SS;
	   sjjs = LjcxAjax.SJJS;
	   dlwz = LjcxAjax.DLWZ;
	   gzwz= LjcxAjax.GZWZ; 
	   dmhb = LjcxAjax.DMHB;
	   
	   
	   sjzzbx= LjcxAjax.SJZZBX;
	   sjhzby= LjcxAjax.SJHZBY
	   
	   ktxm = LjcxAjax.KTXM;
	   jpdzcx1 = LjcxAjax.JPDZCX1; 
	   jpdzcx2 = LjcxAjax.JPDZCX2;
	   wdmc = LjcxAjax.WDMC;
	   bxrq = LjcxAjax.BXRQ;
	   xcxwz= LjcxAjax.XCXWZ;
	   wzyz= LjcxAjax.WZYZ;
	    bz= LjcxAjax.BZ;
/*	
	  TANGER_OCX_OBJ.SetBookmarkValue("JH1",jh);
	  TANGER_OCX_OBJ.SetBookmarkValue("JH2",jh);
	  TANGER_OCX_OBJ.SetBookmarkValue("JH3",jh);
	  TANGER_OCX_OBJ.SetBookmarkValue("JH4",jh);
	  TANGER_OCX_OBJ.SetBookmarkValue("JH5",jh);
	
	  TANGER_OCX_OBJ.SetBookmarkValue("BXRQ1",bxrq.substring(0, 10));
	
	 
	  TANGER_OCX_OBJ.SetBookmarkValue("HZBX",sjzzbx);
	  TANGER_OCX_OBJ.SetBookmarkValue("ZZBY",sjhzby);
	  alert(LjcxAjax.DMHB);
	  if(dmhb!=" null"){
	  TANGER_OCX_OBJ.SetBookmarkValue("DMHB",dmhb);}
	  TANGER_OCX_OBJ.SetBookmarkValue("DLWZ",dlwz);
	  TANGER_OCX_OBJ.SetBookmarkValue("GZWZ",gzwz); 

	  TANGER_OCX_OBJ.SetBookmarkValue("CXWZ1",jpdzcx1);
	  TANGER_OCX_OBJ.SetBookmarkValue("CXWZ2",jpdzcx2);
	  TANGER_OCX_OBJ.SetBookmarkValue("SJJS",sjjs);
	  TANGER_OCX_OBJ.SetBookmarkValue("MDCW",sjmdc);
	  TANGER_OCX_OBJ.SetBookmarkValue("WZCW",sjwzcw);
	  TANGER_OCX_OBJ.SetBookmarkValue("WZYZ",wzyz);
	  TANGER_OCX_OBJ.SetBookmarkValue("WJFF",bz);
*/
	  SetBookmarkValueRe("JH1",jh);
	  SetBookmarkValueRe("JH2",jh);
	  SetBookmarkValueRe("JH3",jh);
	  SetBookmarkValueRe("JH4",jh);
	  SetBookmarkValueRe("JH5",jh);
	
	  SetBookmarkValueRe("BXRQ1",bxrq.substring(0, 10));
	
	 
	  SetBookmarkValueRe("HZBX",sjzzbx);
	  SetBookmarkValueRe("ZZBY",sjhzby);
	  SetBookmarkValueRe("DMHB",dmhb);
	  SetBookmarkValueRe("DLWZ",dlwz);
	  SetBookmarkValueRe("GZWZ",gzwz); 

	  SetBookmarkValueRe("CXWZ1",jpdzcx1);
	  SetBookmarkValueRe("CXWZ2",jpdzcx2);
	  SetBookmarkValueRe("SJJS",sjjs);
	  SetBookmarkValueRe("MDCW",sjmdc);
	  SetBookmarkValueRe("WZCW",sjwzcw);
	  SetBookmarkValueRe("WZYZ",wzyz);
	  SetBookmarkValueRe("WJFF",bz);	 
	 
	}

//给书签插入数据时，判断数据为空	
function SetBookmarkValueRe(name,obj){

	if(obj!="null"){
	
	  	TANGER_OCX_OBJ.SetBookmarkValue(name,obj);
	  }
}

	
//增加评价井基本信息的自动插入  add by yang 20090509
function addjbxx3(wdmc,juid) {
             
             
       
		  //alert(wdmc);
	   	   dojo.io.bind({
				    url: "get_jbxx3.jsp?wdmc="+escapeWith__(wdmc),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
				    sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { jbxxCallback3(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		

}
		
function jbxxCallback3(data){
	  
	 //alert(data);
	  eval("var LjcxAjax = " + data);
	  jh = LjcxAjax.JH; 
	  jb = LjcxAjax.JB;
	  jx = LjcxAjax.JX;
	  sjmdc = LjcxAjax.SJMDC;
	  sjwzcw= LjcxAjax.SJWZCW; 
	  ss = LjcxAjax.SS;
	  sjjs = LjcxAjax.SJJS;
	  dlwz = LjcxAjax.DLWZ;
	  gzwz= LjcxAjax.GZWZ; 
	  dmhb = LjcxAjax.DMHB;
	  sjzzbx=" " + LjcxAjax.SJZZBX;
	  sjhzby=" " + LjcxAjax.SJHZBY
	   
	  ktxm = LjcxAjax.KTXM;
	  jpdzcx1 = LjcxAjax.JPDZCX1; 
	  jpdzcx2 = LjcxAjax.JPDZCX2;
	  wdmc = LjcxAjax.WDMC;
	  bxrq = LjcxAjax.BXRQ;
	  xcxwz= LjcxAjax.XCXWZ;
	  wzyz= LjcxAjax.WZYZ;
	  sjjd= LjcxAjax.SJJD;//设计经度
	  sjwd= LjcxAjax.SJWD;
	  ss= LjcxAjax.SS;
	   
	  htqh= LjcxAjax.HTQH;
	  sywz= LjcxAjax.SYWZ;
	  cpj= LjcxAjax.CPJ;//磁偏角
	  //alert(sjwd);
	   
	  SetBookmarkValueRe("JH",jh);
	  SetBookmarkValueRe("JH1",jh);
	  SetBookmarkValueRe("JH2",jh);
	  SetBookmarkValueRe("JH3",jh);
	  SetBookmarkValueRe("JH4",jh);
	  SetBookmarkValueRe("JH5",jh);
	  SetBookmarkValueRe("JH6",jh);
	  SetBookmarkValueRe("JX",jx);
	  SetBookmarkValueRe("JX1",jx);
	  SetBookmarkValueRe("JB",jb);
	
	  SetBookmarkValueRe("BXRQ1",bxrq.substring(0, 10));
	  
	  SetBookmarkValueRe("BXSJ2",bxrq.substring(0, 10));
	  SetBookmarkValueRe("BXSJ3",bxrq.substring(0, 10));
	 
	 
	  SetBookmarkValueRe("DDZZB",sjzzbx);
	  SetBookmarkValueRe("DDHZB",sjhzby);
	  SetBookmarkValueRe("DMHB",dmhb);
	  SetBookmarkValueRe("DLWZ",dlwz);
	  SetBookmarkValueRe("GZWZ",gzwz); 

	  SetBookmarkValueRe("CXWZ1",jpdzcx1);
	  SetBookmarkValueRe("CXWZ2",jpdzcx2);
	  SetBookmarkValueRe("SJJS",sjjs);
	  SetBookmarkValueRe("MDCW",sjmdc);
	  SetBookmarkValueRe("WZCW",sjwzcw);
	  SetBookmarkValueRe("SJJD",sjjd);
	
	  SetBookmarkValueRe("SJWD",sjwd);
	  SetBookmarkValueRe("SS",ss);
	  SetBookmarkValueRe("HTQH",htqh);
	  SetBookmarkValueRe("SYWZ",sywz);
	  SetBookmarkValueRe("CPJ",cpj);
	  
	 
	 
	}
	

	
	//添加天然气组分分析数据
	function gettrqzf(data1) {
             
          		  
	   	   dojo.io.bind({
				    url: "json_trqzf_data.jsp?data1="+escapeWith__(data1),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { trqzfCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
		
		
			
		}
		
function trqzfCallback(data){
	  
	   	eval("var aaa = " + data);
			
		arr=aaa;

	
	}
//添加天然气组分分析数据
function addtrqzf(juids) {
	

      var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(16);
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        gettrqzf(juidshuzu[i]);
        var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
          Rows.Alignment=1;
          
          var r =Rows.Count;

		 Cell(r,17).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]+"-"+arr[r-numr][4]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][5]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][6]);
             Cell(r,7).Range.InsertAfter(arr[r-numr][7]);
             Cell(r,8).Range.InsertAfter(arr[r-numr][8]);
             Cell(r,9).Range.InsertAfter(arr[r-numr][9]);
             Cell(r,10).Range.InsertAfter(arr[r-numr][10]);
             Cell(r,11).Range.InsertAfter(arr[r-numr][11]);
			 Cell(r,12).Range.InsertAfter(arr[r-numr][12]);
			 Cell(r,13).Range.InsertAfter(arr[r-numr][13]);
			 Cell(r,14).Range.InsertAfter(arr[r-numr][14]);
			 Cell(r,15).Range.InsertAfter(arr[r-numr][15]);
			 Cell(r,16).Range.InsertAfter(arr[r-numr][16]);
			 Cell(r,17).Range.InsertAfter(arr[r-numr][17]);
			 Cell(r,17).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	

}


//添加邻井水分析数据  by  yangl  20090831
	function getsfxsj(data1) {
             
          		 
	   	   dojo.io.bind({
				    url: "json_sfxsj_data.jsp?data1="+escapeWith__(data1),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { sfxsjCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
		
		
			
		}
		
function sfxsjCallback(data){
	  
	   	eval("var aaa = " + data);
			
		arr=aaa;

	
	}
//添加临井水分析数据  by  yangl  20090831
function addsfxsj(juids) {
	
	var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
		var zjyTable = tables(17);
        with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        
        	getsfxsj(juidshuzu[i]);
        
        	var numr = Rows.Count;
        	var r =Rows.Count;
        	if(style != "网格型")
               {
                  style = "网格型";
               }
         
         Rows.Alignment=1;
         //alert(Rows.Alignment);
         var r =Rows.Count;
		 //Cell(r,17).Select();
		 //sel.MoveRight(1,1,1);
		 //sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {
			
             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][4]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][5]);
             Cell(r,7).Range.InsertAfter(arr[r-numr][6]);
             Cell(r,8).Range.InsertAfter(arr[r-numr][7]);
             Cell(r,9).Range.InsertAfter(arr[r-numr][8]);
             Cell(r,10).Range.InsertAfter(arr[r-numr][9]);
             Cell(r,11).Range.InsertAfter(arr[r-numr][10]);
			 Cell(r,12).Range.InsertAfter(arr[r-numr][11]);
			 Cell(r,13).Range.InsertAfter(arr[r-numr][12]);
			 Cell(r,14).Range.InsertAfter(arr[r-numr][13]);
			 Cell(r,15).Range.InsertAfter(arr[r-numr][14]);
			 Cell(r,16).Range.InsertAfter(arr[r-numr][15]);
			 Cell(r,17).Range.InsertAfter(arr[r-numr][16]);
			 Cell(r,18).Range.InsertAfter(arr[r-numr][17]);
			 Cell(r,18).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
	

}






//获取邻井试油成果   by  zjm  20090330
function getsycg(data1) {
             
             
     
		  
	   	   dojo.io.bind({
				    url: "json_sycg_data.jsp?data1="+escapeWith__(data1),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
					sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { sycgCallback(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		
		
		
			
		}
		
function sycgCallback(data){
	  
	   	eval("var aaa = " + data);
			
		
		arr=aaa;
		
	
	}
	
	
//添加试油成果表
function addsycg(juids) {

   var juidshuzu;
        juidshuzu = juids.split(",");
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
		var sel = app.Selection; //得到Selection对象
        var tables = mydoc.Tables;
        if(jb1=='评价井')
        {
        	var zjyTable = tables(16);
        }
        else
        {
        	var zjyTable = tables(15);
        }
		
            with(zjyTable)
        {
		for(i=0;i<juidshuzu.length-1;i++)
        {
        var numr = Rows.Count;
        getsycg(juidshuzu[i]);
        var r =Rows.Count;
        
       
            if(style != "网格型")
               {
                  style = "网格型";
               }
        Rows.Alignment=1;
          
          var r =Rows.Count;
			
		 Cell(r,10).Select();
		 sel.MoveRight(1,1,1)
		 sel.InsertRowsBelow(1);
		 var numr = Rows.Count;
		 r =Rows.Count;
          for(r;r<numr+arr.length;r++)
          
          {

             Cell(r,1).Range.InsertAfter(arr[r-numr][0]);
             Cell(r,2).Range.InsertAfter(arr[r-numr][1]);
             Cell(r,3).Range.InsertAfter(arr[r-numr][2]);
             Cell(r,4).Range.InsertAfter(arr[r-numr][3]+"-"+arr[r-numr][4]);
             Cell(r,5).Range.InsertAfter(arr[r-numr][5]);
             Cell(r,6).Range.InsertAfter(arr[r-numr][6]);
             Cell(r,7).Range.InsertAfter(arr[r-numr][7]);
             Cell(r,8).Range.InsertAfter(arr[r-numr][8]);
             Cell(r,9).Range.InsertAfter(arr[r-numr][9]);
             Cell(r,10).Range.InsertAfter(arr[r-numr][10]);
             Cell(r,11).Range.InsertAfter(arr[r-numr][11]);
			 Cell(r,11).Select();
			 sel.MoveRight(1,1,1);
			 sel.InsertRowsBelow(1);

          }
          sel.Rows.Delete();
        
        }
		}
}

function delectqianming()
{
			TANGER_OCX_OBJ.SetBookmarkValue("XMZZQM"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("SZQM"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("SZQMRQ"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("SJDWFZR"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("SJDWFZRRQ"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("LJZJQM"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("CJZJQM"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("CJZJQMRQ"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("XMJLQM"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("XMJLQMRQ"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("KTCQM"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("KTCQMRQ"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("ZJLQM"," ");
			TANGER_OCX_OBJ.SetBookmarkValue("ZJLQMRQ"," ");
}


function initCustomnddMenus()
{

	TANGER_OCX_OBJ.AddCustomMenu2(9,"编辑控制");
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,0,-1,false,"保存到服务器",false,1);
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,1,-1,false,"显示痕迹",false,2);
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,2,-1,false,"隐藏痕迹",false,3);
	TANGER_OCX_OBJ.AddCustomMenuItem2(9,3,-1,false,"插入本井基本数据",false,11);
	
}
//井位拟定单基本信息的自动插入  add by xiaoheng 20100325

function addNddJbxx(wdmc,ndduid) {
             
		  //alert(wdmc);
	   	   dojo.io.bind({
				    url: "get_nddjbxx.jsp?ndduid="+escapeWith__(ndduid),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
				    sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { Callback_NddJbxx(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		

}
		
function Callback_NddJbxx(data){
	  
	 //alert(data);
	 eval("var LjcxAjax = " + data);
	  
	 jh = LjcxAjax.JH;
	 jb = LjcxAjax.JB;
	 jx = LjcxAjax.JX;
	
	 sjzzb = LjcxAjax.SJZZB;
	 sjhzb = LjcxAjax.SJHZB;
	 sjzbgc = LjcxAjax.SJZBGC;
	 sjjs = LjcxAjax.SJJS;

	 sjmdc = LjcxAjax.SJMDC;
	 sjwzcw = LjcxAjax.SJWZCW;
	 dlwz = LjcxAjax.DLWZ;
	 gzwz = LjcxAjax.GZWZ;
	 bxrq = LjcxAjax.BXRQ;
	 jwndyj = LjcxAjax.JWNDYJ; 
	 ztmd = LjcxAjax.ZTMD;
	 cxwz1 = LjcxAjax.CXWZ1;
	 cxwz2 = LjcxAjax.CXWZ2;
	 ydyqfw = LjcxAjax.YDYQFW;
	 ydyqjl = LjcxAjax.YDYQJL;
	 sjqx = LjcxAjax.SJQX;
	 ktqksjyq = LjcxAjax.KTQKSJYQ;
	 bxry = LjcxAjax.BXRY;
	 bxbm = LjcxAjax.BXBM;

	   
	  SetBookmarkValueRe("JH",jh);
	  SetBookmarkValueRe("JB",jb);
	  SetBookmarkValueRe("BXRQ",bxrq.substring(0,10));
	  SetBookmarkValueRe("JWNDYJ",jwndyj);
	  SetBookmarkValueRe("ZTMD",ztmd);
	  SetBookmarkValueRe("GZWZ",gzwz);
	  SetBookmarkValueRe("CXWZ1",cxwz1);
	  SetBookmarkValueRe("CXWZ2",cxwz2);
	  SetBookmarkValueRe("DLWZ",dlwz);
	   
	  SetBookmarkValueRe("SJHZB",sjhzb);
	  SetBookmarkValueRe("SJZZB",sjzzb);
	  SetBookmarkValueRe("ZJZBGC",sjzbgc);
	  SetBookmarkValueRe("YDYQFW",ydyqfw);
	  SetBookmarkValueRe("YDYQJL",ydyqjl); 

	  SetBookmarkValueRe("SJJS",sjjs);
	  SetBookmarkValueRe("SJWZCW",sjwzcw);
	  SetBookmarkValueRe("SJQX",sjqx);
	  SetBookmarkValueRe("KTQKSJYQ",ktqksjyq);
	  SetBookmarkValueRe("BXBM",bxbm);
	  SetBookmarkValueRe("BXRQ2",bxrq.substring(0,10));
	
	  SetBookmarkValueRe("BXBM2",bxbm);
	  SetBookmarkValueRe("BXRY",bxry);;
	   
	}
	
// 插入试油地质设计基本信息
function addSYSJJbxx(wdmc,uid) {
             
		  //alert(wdmc);
	   	   dojo.io.bind({
				    url: "get_sysj_jbxx.jsp?uid="+escapeWith__(uid),
				    mimetype: "text/html",
				    timeoutSeconds: 3000, //set timeout time,set o never timeout
				    method: "GET",
				    sync: true, 
				    content:{'invokePaneName': 'test'},
				    load: function(type, data, evt) { Callback_SYSJJbxx(data);}, //type should be "load", data is that we wanted
				    error: function(type, error) {  alert(error.message);  }, //error is dojo.io.Erroralert(error.message);
				    timeout: function(type) { alert("time out!"); }
		  });
		

}

function Callback_SYSJJbxx(data){
	  
	 //alert(data);
	 eval("var LjcxAjax = " + data);
	 
	objuid = LjcxAjax.OBJUID;
	jh = LjcxAjax.JH;
	jb = LjcxAjax.JB;
	jx = LjcxAjax.JX;
	dw = LjcxAjax.DW;
	kzrq = LjcxAjax.KZRQ;
	wzrq = LjcxAjax.WZRQ;
	wjrq = LjcxAjax.WJRQ;
	wzcw = LjcxAjax.WZCW;
	wzjs = LjcxAjax.WZJS;
	dnhb = LjcxAjax.DMHB;
	bxhb = LjcxAjax.BXHB;
	sjss = LjcxAjax.SJSS;
	rgjd = LjcxAjax.RGJD;
	jtbj = LjcxAjax.JTBJ;
	ytbj = LjcxAjax.YTBJ;	
	wzff = LjcxAjax.WZFF;
	dlwz = LjcxAjax.DLWZ;
	gzwz = LjcxAjax.GZWZ;
	sjhzb = LjcxAjax.SJHZB;
	sjzzb = LjcxAjax.SJZZB;
	zdjx = LjcxAjax.ZDJX;
	sjjs = LjcxAjax.SJJS;
	fwj = LjcxAjax.FWJ;
	jdwy = LjcxAjax.JDWY;
	wjsy = LjcxAjax.WJSY;
	sjbz = LjcxAjax.SJBZ;
	bxrq = LjcxAjax.BXRQ;
	bxry = LjcxAjax.BXRY;
	bxbm = LjcxAjax.BXBM;
	user_name = LjcxAjax.USER_NAME;
	wdmc = LjcxAjax.WDMC;
	dept_name = LjcxAjax.DEPT_NAME;
	wdlj = LjcxAjax.WDLJ;
	wdmb = LjcxAjax.WDMB;
	dzsj_uid = LjcxAjax.DZSJ_UID;
	  
  
	  SetBookmarkValueRe("JH",jh);
	  SetBookmarkValueRe("JH2",jh);
	  SetBookmarkValueRe("BXRQ",bxrq.substring(0,10));
	  
   
	}
	
	////////////////加表格
	
	function testAddTable(juids)
	{
	        
	       	callService({'serviceName':'dev_plan_well_list',
		       	'callType':'sa',
		       	callback:ttt
		          } 
	         );
	}
	
	function ttt(v){
	  
        var mydoc = TANGER_OCX_OBJ.ActiveDocument; //得到Document对象
        var app = mydoc.Application; //得到Application对象
        var sel = app.Selection; //得到Selection对象

		if(v!=null && v.length>0){
		     for(var i = 0; i < v.length; i++){
		              sel.TypeParagraph();
		              sel.TypeText(v[i].yt);
		     }

	    }

	
	}
