var dmLayerWidth  =150;//表的宽度     鼠标移上时的背景色、上下页样式都在5个样式表中
var dmLayerOutbg  ="#FFF";//鼠标移出时的背景色
var times=0;
function mOver(tableRow){
		$(tableRow).siblings().removeClass("dmLayerMouseOutCss");
		$(tableRow).siblings().removeClass("dmLayerMouseOverCss");
		$(tableRow).addClass("dmLayerMouseOverCss");
		var scope = this ;
		if($.browser.msie)
			scope = document;
		$(scope).bind("keydown",function(event){
			var myEvent = event||window.event;
        	var keyCode = myEvent.keyCode;
        	if(keyCode==40){
        		//下一个框获得,焦点
	        	$(this).unbind("keydown");
	        	if($.browser.msie){//IE and FireFox  are different about nextSibling
	        		if(tableRow.nextSibling!=null){
	        			mOver(tableRow.nextSibling);
	        		}else{
	        			mOver(tableRow.parentNode.firstChild);
	        		}	        		
	        	}else{
	        		if(tableRow.nextSibling!=null){
	        			if(tableRow.nextSibling.nextSibling!=null){
	        				mOver(tableRow.nextSibling.nextSibling);
	        			}else{
	        				mOver(tableRow.parentNode.firstChild);
	        			}
	        		}else if(tableRow.parentNode!=null){
	        			mOver(tableRow.parentNode.firstChild);
	        		}
	        	}	
        	}else if(keyCode==38){
	        	$(this).unbind("keydown");
	        	if($.browser.msie){//IE and FireFox  are different about nextSibling
	        		if(tableRow.previousSibling!=null){
	        			mOver(tableRow.previousSibling);
	        		}else{
	        			mOver(tableRow.parentNode.lastChild);
	        		}	        		
	        	}else{
	        		if(tableRow.previousSibling!=null){
	        			if(tableRow.previousSibling.previousSibling!=null){
							mOver(tableRow.previousSibling.previousSibling);
						}else{mOver(tableRow.parentNode.lastChild);}	
	        		}else if(tableRow.parentNode!=null){
		        		mOver(tableRow.parentNode.lastChild.previousSibling);
	        		}
	        	}
        	}else if(keyCode==13){
        		$(this).unbind("keydown");
        		selInputValue(tableRow);
        		$("#dmLayer").css("display","none");
        	}
  		});
	}
function mOut(tableRow){
	$(tableRow).siblings().removeClass("dmLayerMouseOverCss");
	$(tableRow).siblings().removeClass("dmLayerMouseOutCss");
	$(tableRow).addClass("dmLayerMouseOutCss");
	var scope = this;
	if($.browser.msie)
		scope = document;
	$(scope).unbind("keydown");
}
	
var  sc_page_no = 1;
var  sc_page_size  = 50;
var objGlobals;

function createDmLayer(obj,aFormName,serviceName,searchColName,pageNo,pageSize,configClearOtherUid){

    objGlobals = obj;
	// sc_page_size(每页多少条),sc_page_no(第几页)。
	$("dmLayer").innerHTML = "<font color='red'>正在加载.............</font>";
	/////联动菜单清楚其它的
	if(configClearOtherUid!=null){
	  var clears = configClearOtherUid.split(",");
	  for(i = 0; i < clears.length; i++){
	      var clearOtherUid = clears[i];
	      if(clearOtherUid==""){
	      	continue;
	      }
		  if( $(clearOtherUid)!=null){
	  	  	$(clearOtherUid).value = "";
	  	  }
	  	  if($(clearOtherUid)!=null && $(clearOtherUid).previousSibling!=null){
		  	$(clearOtherUid).previousSibling.value = "";
		  }
	  }
	}
	var aService = serviceName;
	if(pageNo!=null){
		sc_page_no = pageNo;
	}
	if(pageSize!=null){
	   sc_page_size = pageSize;
	}
	
	var Col_Value = encodeURIComponent(escape(objGlobals.value));
	var paras = $("#"+aFormName).formSerialize();
	paras = urlCodeDeal(paras);
	if(obj!=null && searchColName!=null && obj.value!=null){
			eval("paras." + searchColName + "='" + escape(obj.value) + "'");
	}
	var url = globalURL + "servicecontroller?contextServiceName="+aService+"&callType=ss&sc_page_no="
					+ sc_page_no+"&sc_page_size="+sc_page_size+"&"+searchColName+"="+ Col_Value +"&"+paras;
	$.post(url,function(result){
	   	eval("var ret =" + result);
		   if(ret!=null && ret.items!=null && ret.items.length>0){
  			var dmLayer = "<div>";
			dmLayer = dmLayer + '<table width="'+dmLayerWidth+'" border="0" cellpadding="0" cellspacing="0" style="font-size:9pt;cursor:pointer">';
			dmLayer = dmLayer + '<tr><td width="'+dmLayerWidth+'" align="center" class="dmLayerUpPageCss"><span onClick="pageUp(\''
			+ serviceName + '\',\'' + searchColName + '\',\'' + sc_page_no + '\',\'' + sc_page_size 
			+ '\')">上一页</span>&nbsp;&nbsp;<span onClick="clearSelect(' +
			')">清除</span> ';//上一页
			dmLayer = dmLayer + '</td> </tr></table>';
			
			dmLayer = dmLayer + '<table id="dmBody" width="'+dmLayerWidth+'" border="0" cellpadding="0" cellspacing="0" style="font-size:9pt;cursor:pointer">';
			
			for(var i = 0 ;  i < ret.items.length; i++){
	 		    var content = ret.items[i];
	 		   	dmLayer = dmLayer + '<tr codeID="'
	 		   	+ content.objuid +
	 		   	'" onmouseover="mOver(this);" onmouseout="mOut(this);" onclick="selInputValue(this)" onkeydown="pressInputValue(this)" >'
				+' <td width="149" class="DMRB2">'+
				content.name
				+'</td> </tr> ';
			}
			dmLayer = dmLayer + '</table>';
	        if( i == sc_page_size){
				dmLayer = dmLayer + '<table width="'+dmLayerWidth+'" border="0" cellpadding="0" cellspacing="0"  style="font-size:9pt;cursor:pointer">';
				dmLayer = dmLayer + '<tr><td width="'+dmLayerWidth+'" colspan="2" align="center" class="dmLayerUpPageCss" onclick="pageDown(\''
			+ serviceName + '\',\'' + searchColName + '\',\'' + sc_page_no + '\',\'' + sc_page_size 
			+
			'\')">下一页</td></tr> ';//下一页
				dmLayer = dmLayer + '</table>';
			}
			dmLayer = dmLayer + '</div>';				
			$("#dmLayer").css("display","block");
			$("#dmLayer").empty().append(dmLayer);
			mOver($("#dmBody")[0].firstChild.firstChild);
			
	   }else{
			$("#dmLayer").css("display","block").empty().append("&nbsp;&nbsp;&nbsp;&nbsp;没有记录!");
	   }	                            
   });
   
   
}


function invokePopup(obj,aFormName,serviceName,searchColName,pageNo,pageSize,clearOtherUid){
  if(obj!=null){  
  	obj = obj.previousSibling;
  }
  setTip(obj);
  createDmLayer(obj,aFormName,serviceName,searchColName,pageNo,pageSize,'');
}


function textInvokePopup(obj,aFormName,serviceName,searchColName,pageNo,pageSize,clearOtherUid){
  
	$(obj).bind("keydown",function(event){
			var myEvent = event||window.event;
        	var keyCode = myEvent.keyCode;
        	if(keyCode==40){
        		if(times==0){
        			$(obj).unbind("keydown");
        			times = 1;
        			setTip(obj);
  					createDmLayer(obj,aFormName,serviceName,searchColName,pageNo,pageSize,'');
  					
        		}
        	}
		});
	times = 0;
}


function setTip(obj){

	if (arguments.length >  2){alert("对不起！传入本控件的参数太多！");return;}
	if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
	//创建样式，以及定位
	var dads  = $("#dmLayer");
	dads.attr("sname",obj.id);//sname存放对应的文本框的id，目的是方便以后赋值
	var ttop  = obj.offsetTop;     //TT控件的定位点高
	var thei  = obj.clientHeight;  //TT控件本身的高
	var twid  = obj.clientWidth;   //TT控件本身的宽
	var tleft = obj.offsetLeft;    //TT控件的定位点宽
	while (obj = obj.offsetParent){ttop=ttop + obj.offsetTop -obj.scrollTop; tleft=tleft + obj.offsetLeft-obj.scrollLeft;}
	var height = ttop+thei+1;
	if(height>=320){
		height = height - dads.height()-thei-5;
	}
	
	dads.css("top",height+"px").css("left",tleft+1+"px");
 	if (window.event){//IE
       	event.returnValue=false;
    }else{
    	if(obj!=null){
      	obj.preventDefault();
      	}
   }
}
function selInputValue(obj){
	var objID = $("#dmLayer").attr("sname");//得到对应文本框的id
	 if(objID.length>0){
	 	$("#"+objID).attr("value",$(obj).children().html());	//input chinese value
	 	
	 	$("#"+objID).unbind("keydown");
		if($("#"+objID)[0].previousSibling!=null){
			$("#"+objID)[0].previousSibling.value=$(obj).attr("codeID");//把codeID存放到对应的隐藏域中
		}else{
		 	alert("赋值失败，发生错误!!!!!!");
		}
	}else{
		if(objGlobals!=null){
			objGlobals.value = $(obj).children().html();
			if(objGlobals.previousSibling!=null){
				objGlobals.previousSibling.value= $(obj).attr("codeID");//把codeID存放到对应的隐藏域中
			}else{
			  alert("赋值失败，发生错误!!!!!!");
			}
		}
	}
}

function pressInputValue(here){
	alert("here");
}

//任意点击时关闭该控件
document.onclick=function(){
	$("#dmLayer").css("display","none");	
}

function pageUp(serviceName,searchColName,pageNo,pageSize){
     if(pageNo!=null && pageNo<=1){
       return;
     }
	 createDmLayer(objGlobals,null,serviceName,searchColName,parseInt(pageNo)-1,pageSize);
}
function pageDown(serviceName,searchColName,pageNo,pageSize){
	createDmLayer(objGlobals,null,serviceName,searchColName,parseInt(pageNo)+1,pageSize);
}

function  clearSelect(obj){
	var objID = $("#dmLayer").attr("sname");//得到对应文本框的id
 	 if(objID.length>0){
		$("#"+objID).attr("value","");
		if($("#"+objID)[0].previousSibling!=null){
			$("#"+objID)[0].previousSibling.value=''; //把codeID存放到对应的隐藏域中
		}else{
		 	alert("赋值失败，发生错误!!!!!!");
		}
	}else{
	  	objGlobals.value='';
	   	if(objGlobals.previousSibling!=null){
			objGlobals.previousSibling.value='';//把codeID存放到对应的隐藏域中
		}
	}
}













