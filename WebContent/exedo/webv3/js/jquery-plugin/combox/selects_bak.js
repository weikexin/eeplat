var dmLayerWidth  =150;//��Ŀ��     �������ʱ�ı���ɫ������ҳ��ʽ����5����ʽ����
var dmLayerOutbg  ="#FFF";//����Ƴ�ʱ�ı���ɫ
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
        		//��һ������,����
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
	// sc_page_size(ÿҳ������),sc_page_no(�ڼ�ҳ)��
	$("dmLayer").innerHTML = "<font color='red'>���ڼ���.............</font>";
	/////�����˵���������
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
	var paras = $("#"+aFormName).serialize();
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
			+ '\')">��һҳ</span>&nbsp;&nbsp;<span onClick="clearSelect(' +
			')">���</span> ';//��һҳ
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
			'\')">��һҳ</td></tr> ';//��һҳ
				dmLayer = dmLayer + '</table>';
			}
			dmLayer = dmLayer + '</div>';				
			$("#dmLayer").css("display","block");
			$("#dmLayer").empty().append(dmLayer);
			mOver($("#dmBody")[0].firstChild.firstChild);
			
	   }else{
			$("#dmLayer").css("display","block").empty().append("&nbsp;&nbsp;&nbsp;&nbsp;û�м�¼!");
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

	if (arguments.length >  2){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
	if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ���");return;}
	//������ʽ���Լ���λ
	var dads  = $("#dmLayer");
	dads.attr("sname",obj.id);//sname��Ŷ�Ӧ���ı����id��Ŀ���Ƿ����Ժ�ֵ
	var ttop  = obj.offsetTop;     //TT�ؼ��Ķ�λ���
	var thei  = obj.clientHeight;  //TT�ؼ�����ĸ�
	var twid  = obj.clientWidth;   //TT�ؼ�����Ŀ�
	var tleft = obj.offsetLeft;    //TT�ؼ��Ķ�λ���
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
	var objID = $("#dmLayer").attr("sname");//�õ���Ӧ�ı����id
	 if(objID.length>0){
	 	$("#"+objID).attr("value",$(obj).children().html());	//input chinese value
	 	
	 	$("#"+objID).unbind("keydown");
		if($("#"+objID)[0].previousSibling!=null){
			$("#"+objID)[0].previousSibling.value=$(obj).attr("codeID");//��codeID��ŵ���Ӧ����������
		}else{
		 	alert("��ֵʧ�ܣ��������!!!!!!");
		}
	}else{
		if(objGlobals!=null){
			objGlobals.value = $(obj).children().html();
			if(objGlobals.previousSibling!=null){
				objGlobals.previousSibling.value= $(obj).attr("codeID");//��codeID��ŵ���Ӧ����������
			}else{
			  alert("��ֵʧ�ܣ��������!!!!!!");
			}
		}
	}
}

function pressInputValue(here){
	alert("here");
}

//������ʱ�رոÿؼ�
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
	var objID = $("#dmLayer").attr("sname");//�õ���Ӧ�ı����id
 	 if(objID.length>0){
		$("#"+objID).attr("value","");
		if($("#"+objID)[0].previousSibling!=null){
			$("#"+objID)[0].previousSibling.value=''; //��codeID��ŵ���Ӧ����������
		}else{
		 	alert("��ֵʧ�ܣ��������!!!!!!");
		}
	}else{
	  	objGlobals.value='';
	   	if(objGlobals.previousSibling!=null){
			objGlobals.previousSibling.value='';//��codeID��ŵ���Ӧ����������
		}
	}
}













