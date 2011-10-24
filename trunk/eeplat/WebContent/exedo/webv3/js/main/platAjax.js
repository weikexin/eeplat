var invokeDomId = "";
var mirrorEditor ;
var mirrorEditor2 ;
var ie6len = 1000;

// 在js里面直接调用action类
/**
 * p的定义 p.btn p.actionName p.formName p.paras p.callback
 * 
 * @param p
 * @return
 */
function callAction(p){
	
	if(p.btn){
		p.btn.disabled = true;
	}
    if(p.actionName==null && p.actionConfigName==null){
    	if(p.btn){
		    p.btn.disabled = false; 
    	}
    	return;
     }
    
	var async = true;
	
	if(p.async == false){
		async = false;
	}
	var aMsg = "后台处理,请稍后......";
	if(p.msg){
		aMsg = p.msg;
	}
    //表单验证
	if(!validate(p.formName)){
		if(p.btn){
		 	p.btn.disabled = false; 
		}
		return;
	}
	
	///支持pml的两种形式 
	var pmlName = "";
	if(p.pml!=null &&  p.pml.indexOf('mvccontroller')==-1 && p.pml.indexOf('.pml')==-1){
		pmlName = p.pml;
		p.pml =  globalURL + p.pml + ".pml?1=1";
	}
	//如果没有设置参数，自动从表单中获取
	var paras = "";
	if(p.paras){
		paras = p.paras;
		if(p.formName!=null && $.trim(p.formName)!=""){
			paras = paras + "&" + getParasOfForms(p.formName);
		}
	}else if(p.paras==null && p.formName!=null && $.trim(p.formName)!=""){
		paras =  getParasOfForms(p.formName);
	}
	if(p.actionName){
		paras = "callType=as&greenChannel=true&userDefineClass="+p.actionName + "&" + urlCodeDeal(paras);
	}
	if(p.actionConfigName){
		paras = "callType=as&contextServiceName=do_auth_owner_browse&greenChannel=true&actionConfigName="+p.actionConfigName + "&" + urlCodeDeal(paras);
	}
	
	loading(aMsg);
	$.ajax({
		type: "post",
		url: globalService,
		data: paras,
		async: async,
		success: function (data, textStatus){
		
		   closeWin();
    	
		   if(data!=null && data.echo_msg!=null  && $.trim(data.echo_msg)!=''){
		   		var echo_msg = unescape(data.echo_msg);
		   		if(data.success  && 
		   				(data.success=='success'  || data.success=='true')){///成功也有可能含有提示
		   			if(echo_msg!='success'  && echo_msg!='null' && echo_msg!='undefined'){
			   			alert(echo_msg);
		   			}
		   		}else{
			   		if(echo_msg!='success'  && echo_msg!='null' && echo_msg!='undefined'){
			   			alert(echo_msg);
			   		}
	   	    		if(p.btn.nodeName=='A'){
	   	    			p.btn.flag = false;
	   	    		}else{
	   	    			p.btn.disabled = false;
	   	    		}		 				   		
	   				return;
		   		}
		   }
	   	   if(p.pml!=null) {
	   		   
	   		   if(p.target &&  $.trim(p.target)!=""){
  				    var resourcePath = p.resourcePath;
   				    if(resourcePath==null){
   				    	resourcePath = p.pml;
   				    }
   				 if(resourcePath!=null && resourcePath.indexOf("?")==-1){
   					resourcePath = resourcePath + "?1=1";		
   			     }
	   			  if(p.target=='_opener_window'){
	   				    window.open(resourcePath + "&"  + getShorterParas(paras),title,'height=760,width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	   			  }else  if(p.target=='_opener_location'){
	   				  	window.location = resourcePath + "&"  + getShorterParas(paras) + "&isApp=true";
	   			  }  
	   			  else if(p.target=='_opener_tab'){
						createNewTab(pmlName,title,p.pml);
				  }else if(p.target=='_opener'){
						popupDialog(pmlName,title,p.pml,p.pmlWidth,p.pmlHeight);
				  }else{
					  if($("#" + p.target).size() > 0 && (pmlName == p.target) ){
						  
 						  var pageNo = $("#"+pmlName+" .pageNo").text();
 						  var pageSize = $("#"+pmlName+" .pageSize").text();
 						  var dataParas = $("#" + p.target).data('paras');
 						  
 						  if(pageNo!=null && pageSize!=null){
 							dataParas = dataParas + "&pageSize="+pageSize+"&pageNo="+pageNo; 
 						  }
 						  $("#" + p.target).empty().load(p.pml,getShorterParas(dataParas));
					  }else{
						  $("#" + p.target).empty().load(p.pml);
					  }
				  }	
	   		   }else if(pmlName!=""){
	   		     $("#" + pmlName).empty().load(p.pml);  
	   		   }
	   		   else{
				 alert("没有定义目标面板,请检查相关配置!");
	   		   }	 
		   }
	   	   if(p.callback){
	   		   p.callback(data);
	   	   }
		   if(p.btn){
	    		if(p.btn.nodeName=='A'){
	   	    			p.btn.flag = false;
	   	    	}else{
	   	    			p.btn.disabled = false;
	   	    	}	
	   	   }
        },
		dataType: "json"});
 }


//在js里面直接调用action类
/**
 * p的定义
 * 
 * p.btn p.serviceUid p.serviceName p.formName p.paras p.title p.pml p.pmlHeight
 * p.pmlWidth p.target p.echoJs p.async 缺省 true p.callType //触发类别 default: us ;
 * others: uf p.callback //回调函数
 * 
 */

function callService(p){
	
	if(p==null){
		return;
	}
	
	var aMsg = "后台处理,请稍后......";
	if(p.msg){
		aMsg = p.msg;
	}
	
	if(p.btn){
		if(p.btn.nodeName=='A'){
			if(p.btn.flag){
				alert("请不要重复点击！");
				return;
			}
			p.btn.flag = true;
		}else{
			p.btn.disabled = true;
		}
	}	
	var async = true;
	
	if(p.async == false){
		async = false;
	}
    if(p.serviceUid==null && p.serviceName==null){
    	if(p.btn){
    		if(p.btn.nodeName=='A'){
    			p.btn.flag = false;
    		}else{
    			p.btn.disabled = false;
    		}	
    	}	
    	return;
    }

	//表单验证
	if(!validate(p.formName)){
		if(p.btn){
			if(p.btn.nodeName=='A'){
    			p.btn.flag = false;
			}else{
				p.btn.disabled = false;
			}
		}	
		return;
	}
	///支持pml的两种形式 
	var pmlName = "";
	if(p.pml!=null &&  p.pml.indexOf('mvccontroller')==-1 && p.pml.indexOf('.pml')==-1){
		pmlName = p.pml;
		p.pml =  globalURL + p.pml + ".pml?1=1";
	}
	
	/////提示性问题
	if(p.echoJs!=null && !eval(unescape(p.echoJs))){
    	if(p.btn){
    		if(p.btn.nodeName=='A'){
    			p.btn.flag = false;
    		}else{
    			p.btn.disabled = false;
    		}	
    	}
		return;
	}
	//初始化FckEditor值 
	updateEditorFormValue();
	// 只要设置了formName，就从表单中获取
	var paras = "";
	if(p.paras){
		paras = p.paras;
		if(p.formName!=null && $.trim(p.formName)!=""){
			paras = paras + "&" + getParasOfForms(p.formName);
		}
	}else if(p.paras==null && p.formName!=null && $.trim(p.formName)!=""){
		paras =  getParasOfForms(p.formName);
	}
	var callType = "us";
	if(p.callType){
		callType = p.callType;
	}

	var  callServStr = "";
	if(p.serviceUid){
		callServStr = "contextServiceUid="+ p.serviceUid;
	}else{
		callServStr = "contextServiceName="+ p.serviceName;
	}

	paras = callServStr + "&callType=" + callType  + "&" + urlCodeDeal(paras);
	
	loading(aMsg);
	$.ajax({
		type: "post",
		url: globalService,
		data: paras,
		async:async,
		success: function (data, textStatus){

				   closeWin();
				   if(data!=null && data.echo_msg!=null && $.trim(data.echo_msg)!=''){
				   		var errmsg = unescape(data.echo_msg);
				   		
				   		if(data.success  && 
				   				(data.success=='success'  || data.success=='true')){///成功也有可能含有提示
				   			if(errmsg!='success'  && errmsg!='null' && errmsg!='undefined'){
					   			alert(errmsg);
				   			}
				   		}else{
					   		if(errmsg!='success'  && errmsg!='null'  && errmsg!='undefined'){
	 		   		 			alert(errmsg);
					   		}
	 		   	    		if(p.btn.nodeName=='A'){
	 		   	    			p.btn.flag = false;
	 		   	    		}else{
	 		   	    			p.btn.disabled = false;
	 		   	    		}		 				   		
 			   				return;
				   		}
				   }
    			   if(data.returnPath!=null && data.returnPath!=null){
    				   
   				    var arrayPath = data.returnPath.split(",");
   				    var arrayTarget = data.targetPane.split(",");
   				    var arrayTitle = data.returnTitle.split(",");
   				    for(ai = 0; ai < arrayPath.length;ai++){
   				        var aPath = arrayPath[ai];
   				        var target = arrayTarget[ai];
   				        var aTitle= arrayTitle[ai];
   				        if(aTitle==null){
   				        	aTitle = "信息窗口";
   				        }
   				        if(aPath!=null && aPath!=""
   				        && target!=null && target!=""){
   				   			  if(target=='_opener_window'){
   		  		   				  	window.open(aPath + "&"  + getShorterParas(paras),aTitle,'height=760,width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
   		  		   			  }else  if(target=='_opener_location'){
   		  		   				  	window.location = aPath + "&"  + getShorterParas(paras) + "&isApp=true";
   		  		   			  }  
   		  		   			  else if(target=='_opener_tab'){
   		  							createNewTab(pmlName,aTitle,aPath);
   		  					  }else if(target=='_opener'){
   	  								popupDialog(pmlName,aTitle,aPath,p.pmlWidth,p.pmlHeight);
   		  					  }else{
   		  	 		   			 $("#" + target).empty().load(aPath);  
   		  					  }	
   			        	}
   			         }
   				   }
                   var title = "新窗口"
                   if(p.title != null && p.title != "")
                	  title = p.title;
			 
 		   	   if(p.pml!=null) {
 		   		   
  		   		   if(p.target &&  $.trim(p.target)!=""){
  	  				    var resourcePath = p.resourcePath;
  	   				    if(resourcePath==null){
  	   				    	resourcePath = p.pml;
  	   				    }
  	   				 if(resourcePath!=null && resourcePath.indexOf("?")==-1){
  	   					 resourcePath = resourcePath + "?1=1";		
  	   				 }
  		   			  if(p.target=='_opener_window'){
  		   				  	window.open(resourcePath + "&"  + getShorterParas(paras),title,'height=760,width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
  		   			  }else  if(p.target=='_opener_location'){
  		   				  	window.location = resourcePath + "&"  + getShorterParas(paras) + "&isApp=true";
  		   			  }  
  		   			  else if(p.target=='_opener_tab'){
  							createNewTab(pmlName,title,p.pml);
  					  }else if(p.target=='_opener'){
  							popupDialog(pmlName,title,p.pml,p.pmlWidth,p.pmlHeight);
  					  }else{
  						  ////loadPml的时候并不改造这个地方，在下部需要分页的情况下，修改时会把当前页面状态改变
 						  
  						  if($("#" + p.target).size() > 0 && (pmlName == p.target) ){
  							  
  	  						  var pageNo = $("#"+pmlName+" .pageNo").text();
  	  						  var pageSize = $("#"+pmlName+" .pageSize").text();
  	  						  var dataParas = $("#" + p.target).data('paras');
  	  						  
  	  						  if(pageNo!=null && pageSize!=null){
  	  							dataParas = dataParas + "&pageSize="+pageSize+"&pageNo="+pageNo; 
  	  						  }
  	  						  $("#" + p.target).empty().load(p.pml,getShorterParas(dataParas));
  						  }else{
  							  $("#" + p.target).empty().load(p.pml);
  						  }
  					  }	
 		   		   }else if(pmlName!=""){

 		   		     $("#" + pmlName).empty().load(p.pml);  
 		   		   }
 		   		   else{
					 alert("没有定义目标面板,请检查相关配置!");
 		   		   }	 
			   }
 		   	   if(p.callback){
 		   		   p.callback(data);
 		   	   }
 		   	   if(p.btn){
	 		  		if(p.btn.nodeName=='A'){
	 	    			p.btn.flag = false;
	 				}else{
	 					p.btn.disabled = false;
	 				}
 		   	   }
	  },
	  dataType: "json" });
}

/**
 * p.paras
 * @param p
 * @return
 */
function callPlatBus(p){
	
	if(p==null){
		return;
	}
	if(p.paras==null){
		return;
	}
    $.ajax({   type: "POST",
    		   url: globalService,
    		   data: p.paras,
    		   async: false
    		})
}


/**
 * p的定义 
 * p.formName
 * p.paras
 * p.target
 * p.pml
 * p.pmlName
 * urlCodeDeal(paras)  改写可以加快速度
 * @param p
 * @return
 */
function loadPml(p){
	

	if(p==null){
		return;
	}
	if(p.pml ==  null){
		return;
	}
	var pmlName = "";
	
	if(p.pmlName){
		pmlName = p.pmlName;
	}



/**
 * 是否为简化配置 
 * 如果直接把pml配置为面板的名称即为简化配置 
 */
	var simpleConfig = false;
	if(p.pml!=null &&  p.pml.indexOf('mvccontroller')==-1 
			&& p.pml.indexOf('.pml')==-1 
			&& p.pml.indexOf('.jsp')==-1
			&& p.pml.indexOf('.htm')==-1){
		pmlName = p.pml;
		simpleConfig = true;
		p.pml =  globalURL + p.pml + ".pml?1=1";

	}
	
	if(p.pml!=null && p.pml.indexOf("?")==-1){
		p.pml = p.pml + "?1=1";		
	}


	var paras = "";
	if(p.paras){
		paras = p.paras;
	}else if(p.paras==null && p.formName!=null && $.trim(p.formName)!=""){
		paras =  getParasOfForms(p.formName);
	}

	try{

		
		 var resourcePath = p.resourcePath;

		 if(resourcePath==null){
		 resourcePath = p.pml;
		 }
		 if(resourcePath!=null && resourcePath.indexOf("?")==-1){
				resourcePath = resourcePath + "?1=1";		
		  }

	   if(p.target=='_opener_window'){
				  	window.open(resourcePath + "&"  + getShorterParas(paras),title,'height=760,width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
		}else  if(p.target=='_opener_location'){
				  	window.location = resourcePath + "&"  + getShorterParas(paras) + "&isApp=true";
	   } 
	   else	if(p.target && $.trim(p.target)!="" && p.target!='_opener_tab'){

			if(p.target=='_opener'){
				var title = "";
				if(p.title){
					title = p.title;
				}
				popupDialog(pmlName,title,p.pml + "&" +  getShorterParas(urlCodeDeal(paras)),p.pmlWidth,p.pmlHeight);
				
			}else{
				loading();
				$("#" + p.target).empty().load(p.pml,getShorterParas(urlCodeDeal(paras)),function(){
					closeWin();
				});
				$("#" + pmlName).data('paras',urlCodeDeal(paras));
			}
		}else{
	
	
			if(pmlName!=""){
		
				////如果采用简化配置的情况 
				if(p.target!='_opener' && p.target!='_opener_tab' && simpleConfig && $("#" + pmlName).size() > 0){
					loading();
					$("#" + pmlName).empty().load(p.pml,getShorterParas(urlCodeDeal(paras)),function(){
						closeWin();
					});
					$("#" + pmlName).data('paras',urlCodeDeal(paras));
				}else{
					var title = "";
					if(p.title){
						title = p.title;
					}
					var thisPml = p.pml;
					if(paras!="" && urlCodeDeal(paras)!=""){
						thisPml = p.pml + "&" +  getShorterParas(urlCodeDeal(paras));
					}
					if(p.target=='_opener_tab'){
						createNewTab(pmlName,title,thisPml);
					}else{
						popupDialog(pmlName,title,thisPml,p.pmlWidth,p.pmlHeight);
					}	
				}
			}else{
				alert("您没有定义弹出窗口的名称");
			}	
		}
	}catch(e){
		
		
	}
}

/**
 * 根据form转换为字符串形式的paras
 * @param targetForms
 * @return
 */
function  getParasOfForms(targetForms){

	var paras = "";
	if(targetForms.indexOf(" ")!=-1){
		var forms = targetForms.split(" ")
		for(var i = 0; i < forms.length; i++ ){
			var aForm = forms[i];
			if(paras==""){
				paras = $("#"+aForm).serialize();
			}else{
				paras = paras + "&" +  $("#"+aForm).serialize();
			}	
		}	
	}else{
		paras = $("#"+targetForms).serialize();
	}
	return paras;
}

/**
 * 
 * IE6、7 URL长度的限制
 * @param paras
 * @return
 */
function getShorterParas(paras){
	
	if(paras!=null && paras.length>ie6len && $.browser.msie && 
			($.browser.version=='6.0' 
		||	 $.browser.version=='7.0')){
		return paras.substr(0,ie6len);
    }
	return paras;
}


function updateEditorFormValue()
{	 try {
			 ///fckeditor
                for ( i = 0; i < parent.frames.length; ++i )
                        if ( parent.frames[i].FCK )
                                parent.frames[i].FCK.UpdateLinkedField();
    ////codemirror
	    if(mirrorEditor){
		    mirrorEditor.save();
	    }
	    if(mirrorEditor2){
	    	mirrorEditor2.save();
	    }

	}catch(e){
	 }          
}


////////////////////////设计器相关的函数
function  sortUp(o,boName,serviceName){

	var trO = $(o).parent().parent();
	var base = trO.attr('value');
	var trPre  = trO.prev();
	var pre = trPre.attr('value');
	
	if(trPre.attr('value')){
		var trOClone = trO.clone(true);
		trO.remove();
		trPre.before(trOClone);
	}
	var paras = "dataBus=setContext&contextKey=" + boName + "&contextValue=" + pre
	+ '&base=' + base + '&pre=' + pre;

	
	callService(
		{
		serviceName:serviceName,
		paras:paras,
		callType:'uf'
		}
	);

}
function  sortDown(o,boName,serviceName){

	var trO = $(o).parent().parent();
	var pre = trO.attr('value');
	var trNext  = trO.next();
	var base = trNext.attr('value');
	if(trNext.attr('value')){
		var trOClone = trO.clone(true);
		trO.remove();
		trNext.after(trOClone);
	}
	
	var paras = "dataBus=setContext&contextKey=" + boName + "&contextValue=" + pre
	+ '&base=' + base + '&pre=' + pre;
	
	callService(
		{
		serviceName:serviceName,
		paras:paras,
		callType:'uf'
		}
	);
	
}




////////////////////////////////无刷新 flash上传
function uploadify(uploadifyID,uploadifyQueueID,fileDesc,fileExt,autoUpload,sessionid){
	var now = new Date(); 
	var myTime=now.getTime();
	var myRand = Math.floor(Math.random()*myTime)+1;
	var myFile = myTime+myRand;
	
	if(fileDesc==null || $.trim(fileDesc)==""){
		fileDesc='只能选择图像类文件(*.jpg;*.gif;*.bmp)';
	}
	if(fileExt==null || $.trim(fileExt)==""){
		fileExt = '*.jpg;*.gif;*.bmp';
	}
	if(autoUpload==null){
		autoUpload = true;
	}
	
	var o = $("#" + uploadifyID).prev();
	$("#" + uploadifyID).uploadify({
		'uploader'       : 'exedo/webv3/js/jquery-plugin/fileuploader/uploadify.swf',
		'scriptData'     : {'jsessionid':sessionid,'myFile':myFile},
		'script'         : 'exedo/webv3/upload_action_uploadify.jsp;jsessionid=' + sessionid ,
		'cancelImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/cancel.png',
		'queueID'        : uploadifyQueueID,
		'auto'           : autoUpload,
		'multi'          : true,
		'simUploadLimit' : 2,
		'buttonImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/browse-files.gif',
		'wmode'          : 'transparent',
		'width'          : 75,
		'height'         : 25,
		'fileDesc'       : fileDesc,
		'fileExt'		 : fileExt,
		'onSelect'       : function(event,queueID,fileObj){if(o.val()==""){ o.val(o.val() + myFile+fileObj.name);}else{ o.val(o.val() + ";" + myFile+fileObj.name);}},
		'onCancel'       : function(event,queueID,fileObj,data){o.val(o.val().replace(myFile+fileObj.name,""));}
	});
}

////////////////////////////////无刷新 flash上传 限制只能上传一个
function uploadifyOnlyOne(uploadifyID,uploadifyQueueID,fileDesc,fileExt,autoUpload,sessionid){
	var now = new Date(); 
	var myTime=now.getTime();
	var myRand = Math.floor(Math.random()*myTime)+1;
	var myFile = myTime+myRand;
	
	if(fileDesc==null || $.trim(fileDesc)==""){
		fileDesc='只能选择图像类文件(*.jpg;*.gif;*.bmp)';
	}
	if(fileExt==null || $.trim(fileExt)==""){
		fileExt = '*.jpg;*.gif;*.bmp';
	}
	if(autoUpload==null){
		autoUpload = true;
	}	
	var o = $("#" + uploadifyID).prev();
	$("#" + uploadifyID).uploadify({
	'uploader'       : 'exedo/webv3/js/jquery-plugin/fileuploader/uploadify.swf',
	'scriptData'     : {'jsessionid':sessionid,'myFile':myFile},
	'script'         : 'exedo/webv3/upload_action_uploadify.jsp;jsessionid=' + sessionid,
	'cancelImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/cancel.png',
	'queueID'        : uploadifyQueueID,
	'auto'           : autoUpload,
	'multi'          : false,
	'simUploadLimit' : 2,
	'buttonImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/browse-files.gif',
	'wmode'          : 'transparent',
	'width'          : 75,
	'height'         : 25,
	'fileDesc'       : fileDesc,
	'fileExt'		 : fileExt,
	'onSelect'       : function(event,queueID,fileObj){ o.val(fileObj.name);},
	//'onCancel'     : function(event,queueID,fileObj,data){o.val(o.val().replace(fileObj.name,""));}
	'onProgress'     : function(event,queueId,fileObj,date){o.val(myFile+fileObj.name);}
	});
	
	
}

//验证表单函数
function validate(formName){
	
  if(formName==null || $.trim(formName)==""){
  	return true;
  }

  try{  
    var el;
    var exedo_notnull;
    var exedo_validconfig ;
    var exedo_length;
    var exedo_script;
    var title;
    
    
    var ay = formName.split(" ");
 for(var ii = 0 ; ii < ay.length ; ii++){
    var aFName = ay[ii];
    var formJquery = $("#"+aFName);
    var form = formJquery[0];
    if(form==null){
    	continue;
    }
    for (var i = 0; i < form.elements.length;i++)
    {
        el = form.elements[i];
        var theValue = el.value;
        var theId = el.id;
        if(el.attributes["exedo_validconfig"])
        {
            exedo_validconfig = el.attributes["exedo_validconfig"].value;
        }
        else
        {
            exedo_validconfig = null;
        }
        if(el.attributes["exedo_notnull"])
        {
            exedo_notnull = el.attributes["exedo_notnull"].value;
        }
        else
        {
            exedo_notnull = null;
        }
        if(el.attributes["exedo_length"]){
   	      	exedo_length = el.attributes["exedo_length"].value;
   	    }else{
   	      	exedo_length = null;
   	    }
   	    
   	    if(el.attributes["exedo_script"]){
   	      	exedo_script = el.attributes["exedo_script"].value;
   	    }else{
   	      	exedo_script = null;
   	    }
        
        title = el.title;
        if(title==null || title=="")
        {
            title = "必填字段";
        }
        if(exedo_notnull!=null&& exedo_notnull=="NotNull")
        {
            theValue = $.trim(theValue);
            if(el.type=='radio'){
            	 var checks = formJquery.find("input:checked");
            	 if(checks.size() == 0){
            		 alert(title + " 不能为空!");
 	                 el.focus();
            		 return false;
            	 }
            	 var isChecked = false;
            	 checks.each(
            			 function(i){
            				 if($(this).attr("name")==el.name){
            					 isChecked = true;
            				 }            				             				 
            			 }
            	 );
            	 if(!isChecked){
            		 alert(title + " 不能为空!");
 	                 el.focus();
            		 return false;
            	 }
            	
            	
            }else{         
	            if(theValue==null || ""==theValue)
	            {
	                alert(title + " 不能为空!");
	                el.focus();
	                return false;
	            }
            }
        }
        if(theValue!=null && ""!=theValue)
        {
            if(exedo_validconfig!=null && exedo_validconfig=="Email")
            {
                if(checkErrEmail(theValue))
                {
                    alert("E-MAIL 地址格式不正确！");
                    el.focus();
                    return false;
                }
            }
            if(exedo_validconfig!=null && exedo_validconfig=="Url")
            {
                if(checkErrUrl(theValue))
                {
                    alert("Url 地址格式不正确！");
                    el.focus();
                    return false;
                }
            }
            if(exedo_validconfig!=null && exedo_validconfig=="Integer")
            {
                if(checkErrNum(theValue))
                {
                    alert(title + " 必须为整数类型!");
                    el.focus();
                    return false;
                }
            }
            if(exedo_validconfig!=null && exedo_validconfig=="RealNumber")
            {
                if(checkErrFloat(theValue))
                {
                    alert(title + " 必须为数字类型!");
                    el.focus();
                    return false;
                }
            }
            if(exedo_validconfig!=null && exedo_validconfig=="IDCard")
            {
                var checkResult = checkIdCard(theValue);
                if(checkResult!="yes")
                {
                    alert(checkResult);
                    return false;
                }
            }
            if(exedo_validconfig!=null && exedo_validconfig=="MoBile")
            {
                if(checkNotMobile(theValue))
                {
                    alert(title + " 必须为手机录入格式!");
                    el.focus();
                    return false;
                }
            }
            if(exedo_validconfig!=null && exedo_validconfig=="Telphone")
            {
                if(checkNotTelphone(theValue))
                {
                    alert(title + " 必须为固定电话录入格式!");
                    el.focus();
                    return false;
                }
            }
            
            if(exedo_validconfig!=null && exedo_validconfig=="NoChin")
            {
                if(funcChin(theValue))
                {
                    alert(title + "不能含有汉字!");
                    el.focus();
                    return false;
                }
            }
            
            if(exedo_validconfig!=null && exedo_validconfig=="NoSBCDot")
            {
                if(funcSBCDot(theValue))
                {
                    alert(title + "不能含有全角字符和点(.)!");
                    el.focus();
                    return false;
                }
            }

            if(exedo_length!=null && ""!=exedo_length)
            {
                if(theValue.length>exedo_length)
                {
                    alert( title +"的长度大于"+exedo_length+"个字符!");
                    el.focus();
                    return false;
                }
            }
        }
    }
   }
  }catch(e){
     return false;
  }
    return true;
}

//检查是否含有汉字
function funcChin(value){
	return (/.*[\u4e00-\u9fa5]+.*$/.test(value)); 
} 


//检查是否含有全角和点
function funcSBCDot(value){
	
	if(value==null){
		return false;
	}
	
	if(value.indexOf(".")>-1){
		return true;
	}
	for   (var   i=0;   i<value.length;   i++)   {   
	  if   (value.charCodeAt(i)   >   128) return true;   
   }
	
} 
// 检查Email
function checkErrEmail(value){
	return !/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
}
// 检查Url
function checkErrUrl(value){
	return !/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
}

// 检查Integer
function checkErrNum(value){
	return !/^\d+$/.test(value);
}
// 检查RealNumber
function checkErrFloat(value){
	return !/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
}
// 检查IDCard
function checkIdCard(idcard){
	//判断身份证是否合法
    var Errors=new Array(
    "yes",
    "身份证号码位数不对!",
    "身份证号码出生日期超出范围或含有非法字符!",
    "身份证号码校验错误!",
    "身份证地区非法!"
    );
    var area=
    {
        11:"??",12:"??",13:"??",14:"??",15:"???",21:"??",22:"??",23:"???",31:"??",32:"??",33:"??",34:"??",35:"??",36:"??",37:"??",41:"??",42:"??",43:"??",44:"??",45:"??",46:"??",50:"??",51:"??",52:"??",53:"??",54:"??",61:"??",62:"??",63:"??",64:"??",65:"??",71:"??",81:"??",82:"??",91:"??"
    }
    var idcard,Y,JYM;
    var S,M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
	switch(idcard.length){ 
		case 15: 
		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){ 
		ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; 
		}else { 
		ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; 
		} 
		if(ereg.test(idcard)) return Errors[0]; 
		else return Errors[2]; 
		break; 
		case 18: 
		if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){ 
		ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;// ???????????????
		} else { 
		ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;// ???????????????
		} 
		if(ereg.test(idcard)){
            S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
            + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
            + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
            + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
            + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
            + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
            + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
            + parseInt(idcard_array[7]) * 1
            + parseInt(idcard_array[8]) * 6
            + parseInt(idcard_array[9]) * 3 ;
            Y = S % 11;
            M = "F";
            JYM = "10X98765432";
            M = JYM.substr(Y,1);
            if(M == idcard_array[17]) return Errors[0];
            else return Errors[3];
        }else return Errors[2];
        break;
        default:
        return Errors[1];
        break;
    }
}
// 检查MoBile
function checkNotMobile(value){
	return !/(\d){11}/.test(value);
}
// 检查固定电话 Telphone
function checkNotTelphone(value){
	return !/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)/.test(value);
}

function pageSplit(dataKey,pmlName,formName){
	
			if($(document.body).data(dataKey)==null){
				$(document.body).data(dataKey,1);
			}

			$("#"+dataKey+" .firstPage").bind('click',function(){
				    if($(document.body).data(dataKey)=="1"){
				    	return;
				    }
			  		$(document.body).data(dataKey,"1");
				    var pmlUrl = getPmlUrl(pmlName,1,$("#"+dataKey+" .rowSize").text() );
				    loadPml({'pml':pmlUrl,'target':pmlName,'formName':formName});
			});
			
			$("#"+dataKey+" .prevPage").bind('click',function(){
				    if($(document.body).data(dataKey)=="1"){
				    	return;
				    }
				    var curPageNo = parseInt($(document.body).data(dataKey)) - 1;
				    $(document.body).data(dataKey,"" + curPageNo);
				    var pmlUrl = getPmlUrl(pmlName,curPageNo,$("#"+dataKey+" .rowSize").text() );
				    loadPml({'pml':pmlUrl,'target':pmlName,'formName':formName});
			});
			
			$("#"+dataKey+" .nextPage").bind('click',function(){

				    if(parseInt($("#"+dataKey+" .pageNo").text())>=parseInt(($("#"+dataKey+" .pageSize").text())) ){
				    	return;
				    }
				    var curPageNo = parseInt($("#"+dataKey+" .pageNo").text()) + 1;
				    $(document.body).data(dataKey,"" + curPageNo);
				    var pmlUrl = getPmlUrl(pmlName,curPageNo,$("#"+dataKey+" .rowSize").text() );
				    loadPml({'pml':pmlUrl,'target':pmlName,'formName':formName});
			});
			
			$("#"+dataKey+" .lastPage").bind('click',function(){
				    if(parseInt($("#"+dataKey+" .pageNo").text())==$("#"+dataKey+" .pageSize").text()){
				    	return;
				    }
				    $(document.body).data(dataKey,$("#"+dataKey+" .pageSize").text());
				    var pmlUrl = getPmlUrl(pmlName,$("#"+dataKey+" .pageSize").text(),$("#"+dataKey+" .rowSize").text() );
				    loadPml({'pml':pmlUrl,'target':pmlName,'formName':formName});
			});

	}

function getPmlUrl(pmlName,pageNo,pageSize){
	return  globalURL + pmlName + ".pml?pageSize="+pageSize+"&pageNo="+pageNo; 
}

function insertAuthPt(){
	var selectedNode = window.opener.selectedNodeBak;
	var whatuid = selectedNode.getAttribute('id');
	var ouuid = $("#gm_do_authorization_insert_ptnode_role_ouuid").val();
	callService({'serviceName':'u_role_ptnode',paras:"whatuid=" + whatuid + "&ouuid="+ouuid,'pml':'PM_do_org_role_of_ptnode','target':'PM_do_org_role_of_ptnode'}  );
	
	$('#FPM_do_authorization_insert_ptnode_role').jqmHide();
	
}
