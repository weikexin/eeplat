/*****************************************主页面框架代码******************************************/
var position = "first"; //tab页显示顺序，first是显示在前面，last是显示在后面
var isHome = 1; //是否有首页   有是1   没有是0
var globalURL = "/yiyi/";
var mRightWidth=0; //mRight的宽和高
var mRightHeight=0;

//得到浏览器可用高度，赋给菜单  以及右边区域总div
function resscrEvt(height,width){
	if(height==undefined||width==undefined){
		height = $(window).height();
		width = $(window).width();
	}
	var mRightOffSet = $(".mRight:eq(0)").offset();
///左边索引菜单
	$(".gFpage:eq(0)").css("height",height-mRightOffSet.top );
////右边主要显示区域
	$(".mRight:eq(0)").css("height",height-mRightOffSet.top);
	$(".mRight:eq(0)").css("width",width-mRightOffSet.left);
///树	 
    $(".tree").css("height",height-mRightOffSet.top);
//tab-pane
    $(".ui-tabs-panel").css("height",height-mRightOffSet.top-25);  
    $(".ui-tabs-panel").css("width",width-mRightOffSet.left-$(".lrschidren").width());   
    
  
    $(".mRight:eq(0)").css("overflow","hidden");
    
    mRightWidth =$(".mRight:eq(0)").outerWidth();
	mRightHeight = $(".mRight:eq(0)").outerHeight();
}
//让菜单能伸展   如果这个方法放到类里执行 就会非常慢  所以没有放到类里，在这里判断如果有outlook菜单 则执行
$(function(){
	if($(".mHi").length>0){
		//所有菜单ul标记隐藏
		$(".mHi:gt(0)").hide(); 
		
		$(".mTitle").bind("click",function(){
	  		$(".mHi").hide(); 
			$(".mTitle-hover").removeClass("mTitle-hover");
			$(this).addClass("mTitle-hover");
			$(this).next('.mHi').fadeIn("normal");
	 	})
	}
});

//鼠标在菜单上时   更换背景
function bindMenuHoverCss(){
  $(".mMenu").bind("mouseover",function(){
		$(this).addClass("mMenu-hover");
  }).bind("mouseout",function(){
		$(this).removeClass("mMenu-hover");
  })
};

//点击菜单
function bindClickMenu() {
 $(".mMenu").bind("click",function(){
 		//设置center总区域有滚动条
		//$(".mRight:eq(0)").css("overflow-y","auto");
		//$(".mRight:eq(0)").css("overflow-x","auto");
		
		$(".mMenu").removeClass("mMenu-hover2");
		$(".mMenu").removeClass("mMenu-hover");
		$(this).addClass("mMenu-hover2");
		
		
		//菜单id和tab  id有关联的
		var menuId = $(this).attr("id");
		//菜单title 等于 tab的 title
		var menuName = $(this).attr("name");
		//属性选择器   选择table 属性 tabId  值为 menuId的
		var tabId = "#dvTab table[tabId='"+menuId+"']";
		if($(tabId).length==1){
			//如果这个tab已经存在，则设置成选中的css
			selectTabCss(tabId);
			return;
		}
		
	 
		//如果tab页到7个  则关闭最后一个
		if($("#dvTab table").length ==7){
			if(position=="first"){
				$("#dvTab table:last").remove();
			}else if(position=="last"){
				$("#dvTab table:eq("+isHome+")").remove();
				return;
			}	
		}

		//得到paneId
		var paneId  = $(this).attr("paneid");
		//添加tab页
		if($("#dvTab table").length>0){
			$("#dvTab table:" + position).after("<TABLE class=\"\" tabId=\""+menuId+"\" paneId = \""+paneId+"\" title=\""+menuName+"\" style=\"WIDTH: 130px; ZOOM: 1\"><TBODY><TR><TD class=tLe><TD class=bdy><NOBR>"+menuName+"</NOBR></TD><TD class=btn><A class=TabCls>&nbsp;&nbsp;&nbsp;</A></TD><TD class=tRi></TD></TR></TBODY></TABLE>");
		}else {
			$("#dvTab").append("<TABLE class=\"\" tabId=\""+menuId+"\" paneId = \""+paneId+"\" title=\""+menuName+"\" style=\"WIDTH: 130px; ZOOM: 1\"><TBODY><TR><TD class=tLe><TD class=bdy><NOBR>"+menuName+"</NOBR></TD><TD class=btn><A class=TabCls>&nbsp;&nbsp;&nbsp;</A></TD><TD class=tRi></TD></TR></TBODY></TABLE>");
		}
		//设置新添加的tab页为选中的css
		var tabBtnId = tabId+" .btn";
		selectTabCss($(tabId));
		//重新绑定事件
		bindTabClickCss($(tabId));
		bindTabCloseCss($(tabBtnId));
		bindTabCloseWindow($(tabBtnId));
		
  })
};

//右侧tab页事件   鼠标点击时更换css
function bindTabClickCss(obj){
  if(obj==undefined){
  		  $(".gTab table").bind("click",function(){
				tabClickCss(this);
		  })
  }else{
  		$(obj).bind("click",function(){
				tabClickCss(obj);
		})
  }
}
//鼠标点击tab处理
function tabClickCss(obj){
	selectTabCss(obj);
	$(".mMenu").removeClass("mMenu-hover2");
	$(".mMenu").removeClass("mMenu-hover");
	var menuId = "#"+$(obj).attr("tabId");
	$(menuId).addClass("mMenu-hover2");
}
function selectTabCss(obj){
	$(".gTab table").removeClass("on");
	$(obj).addClass("on");
	
	//加载内容
	var paneId = $(obj).attr("paneId");
	showMainMsg("#mRight",32,32,"center","loadingImg","","n");
	$("#mRight").empty().load(paneId,function(){
		closeWin();
	});
	//closeWin();
}
//控制tab也上的差号显示
function bindTabCloseCss(obj){
  if(obj==undefined){
  		  $(".btn").bind("mouseover",function(){
				$(this).children("a").removeClass("TabCls");
		  }).bind("mouseout",function(){
				$(this).children("a").addClass("TabCls");
		  })
  }else{
  		  $(obj).bind("mouseover",function(){
				$(this).children("a").removeClass("TabCls");
		  }).bind("mouseout",function(){
				$(this).children("a").addClass("TabCls");
		  })
  }
}
//给差号绑定关闭事件
function bindTabCloseWindow(obj){
  if(obj==undefined){
	  $(".btn").bind("click",function(){
			tabCloseWindow(this);
	  })
  }else{
	  $(obj).bind("click",function(){
			tabCloseWindow(obj);
	  })
  }
}
//关闭tab
function tabCloseWindow(obj){
	
	$(obj).parents("table").remove();
	$(".mRight").empty();
			if($(".on").length==0){
				//如果没有被选中的tab页，则选中最后一个
				$(".mMenu").removeClass("mMenu-hover");
				//如果只有一个tab页   选中首页
				if($(".gTab table:eq(1)").length>0){
					selectTabCss(".gTab table:eq(1)");
				}else{
					selectTabCss(".gTab table:eq(0)");
				}
				//菜单跟tab同步    最后一个tab选中后  对应的菜单也要选中
				var menuId = "#"+$(".gTab table:eq(1)").attr("tabId");
				$(menuId).addClass("mMenu-hover");
			}
}
//绑定弹出页面的差号   鼠标悬停样式
function bindOpenWinCss(){
	$("#clsDiv").bind("mouseover",function(){
			$(this).children("a").removeClass("btnCls");
     }).bind("mouseout",function(){
			$(this).children("a").addClass("btnCls");
	 })
}
//绑定弹出页面关闭事件   
function bindOpenWinClose(){
	$("#clsDiv").bind("click",function(){
		var removeId= "#"+$(this).parents("div").parents("div").attr("id");
		$(removeId).remove();
	})
	
}

/*****************************************弹出层代码******************************************/
function openWinDragAndResizable(paneId) {
	$("#"+paneId).draggable({ handle:".sysWin",containment: ".gMPage", scroll: false});
			//拉大缩小弹出的层
	$("#"+paneId).resizable({ 
		handles: 'se',
		maxHeight: mRightHeight,
		maxWidth: mRightWidth,
		minHeight: 100,
		minWidth: 500,
		alsoResize: "#"+paneId + " .sysContent"
	});
};
function openWinBindClose(paneId){
	//弹出层关闭事情
	$("#"+paneId+" .sysWin h2 > .clsDiv").bind("click",function(){
		var removeId= "#"+$(this).parents("div").parents("div").attr("id");
		$(removeId).remove();
	});
}
/*****************************************遮罩层***************************************************/

/*
position:遮罩层显示在哪个区域，例如显示在aa  div里面：#aa 就可以
msgW:弹出框的宽
msgH:弹出框的高
align:显示位置  left,right,center  目前有三个值
type:类型，loading和loadingImg,登录现在用的是loading,loadingImg是一个图片，代表加载内容.如果自定义则给空值.
content:弹出框里面得内容，自定义
isClose:是否有关闭按钮
*/
function showMainMsg(position,msgW,msgH,align,type,content,isClose){
	$("body").prepend("<DIV id=fullBg></DIV><DIV id=main_msg></DIV>");
	
	if(type=="loading"){
		content="<div>&nbsp;&nbsp;请稍后......</div>";
	}else if(type=="loadingImg"){
		content= "<div class=index-loading></div>";
	}
	
	
	if(align=="left"){
		$("#main_msg").css({top:$(position).offset().top,left:$(position).offset().left,width:msgW,height:msgH});
	}else if(align=="center"){
		$("#main_msg").css({top:$(position).offset().top+($(position).height()-msgH)/2,left:$(position).offset().left+($(position).width()-msgH)/2,width:msgW,height:msgH});
	}else if(align=="right"){
		$("#main_msg").css({top:$(position).offset().top,left:$(position).offset().left+$(position).width()-msgW-5,width:msgW,height:msgH});
	}
	
	
	$("#fullBg").css({top:$(position).offset().top,left:$(position).offset().left,width:$(position).width(),height:$(position).height()});
	//显示关闭按钮
	if(isClose=="y"||isClose=="Y"){
		var closeImg="<div class='alertClose'></div>";
		$("#main_msg").append(closeImg);
	}
	
	$("#main_msg").append(content);
	
	//关闭按钮事件
	if(isClose=="y"||isClose=="Y"){
		$(".alertClose").bind("click",function(){
			closeWin();
		})
	}
}


function closeWin(){
//	$("#fullBg").css("display","none");
//	$(".alertClose").parent("#msg").css("display","none");
	$("#main_msg").remove();
	$("#fullBg").remove();
}
/*****************************button***************************************************************/

//查询按钮点击执行的事情
function selectButtonClick(selectFormId,linkName,targetName){
    var paras = $("#"+selectFormId).formSerialize();
    paras = urlCodeDeal(paras);
    $("#"+targetName).empty().load(globalURL+linkName+".pml",paras,function(data){
       $("#"+targetName).replaceWith(data); 
    });
}
//UrlCode 处理代码
function urlCodeDeal(str){
	if(str.length==0||null==str){
		return "";
	}
	var paras = new Array();
	paras = str.split('&');
	var result ="";
	for(var i = 0; i < paras.length; i++){
	    var name_V =   new Array();
	    name_V = paras[i].split('=');
	    if(i==0){
	    	result += name_V[0]+"=";
	    }else{
	    	result +="&" + name_V[0]+"=";
	    }
	    if(name_V.length>1){
	    	//之前空格被用+替换了, 参数中加号用空格替换回来 
	    	result += encodeURIComponent(escape(decodeURIComponent(name_V[1].split("+").join("%20"))));  
	    }
	}
	return result;
}
function selectButtonReset(selectFormId){
	$("#"+selectFormId).resetForm();
}
/************************************tree***********************************************************/
function loadTree(obj,paneUrl){
	$("#"+obj).empty().load(paneUrl);
//	alert($(this).closest('.tree').length);$('.mRight:eq(1)').load('
}
