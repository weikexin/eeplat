//all browse
function bxdansave(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327bdc35f0127bdda33aa005c'});
}
//all read
function bxdanread(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d0c8320127d1bd2ab700dc'});
}
//yuangong
function bxdanyg(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327bdc35f0127bddaac84009f'});
}

//dept
function bxdandept(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d2145e0127d2443872003d'});
}

function bxdandeptread(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d5cc850127d6125c63006c'});
}

function bxdandeptsp(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d28e110127d2afc354002d'});
}
//caiwu
function bxdancw(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d2145e0127d245810f0054'});
}function bxdancwsp(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d28e110127d2b22cf10040'});
}
//total 
function bxdanttread(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d5cc850127d60da7cc0058'});
}

function bxdanttsp(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdan',
		'callback':bxdanCallBack,
		'callType':'uf',
		'formName':'a4028803327d28e110127d2b4828a005d'});
}

function bxdanCallBack(data) {
	var filepath = data.exlfile;
	window.location="exedo/webv3/myjsp/download.jsp?filepath=" + filepath;
}




///////补助统计用
function bztjdetail(v,btn) {
	callService({'serviceName': 'cw_worklog_insert__bzxx_guodujilu',
		'paras':'backup2='+v,
		'callType':'uf',
		'callback':bztjdetailBackI});
}

function bztjdetailBackI() {
	loadPml({
 		'pml':'PM_cw_worklog_Result_allowance_detail',
		'target':'_opener_tab'});
}

///////假期管理补助用
function bzjqdetail(v,btn) {
	callService({'serviceName': 'cw_worklog_insert__bzxx_guodujilu',
		'paras':'backup2='+v,
		'callType':'uf',
		'callback':bzjqdetailBackI});
}

function bzjqdetailBackI() {
	loadPml({
 		'pml':'PM_cw_worklog_Result_jiaqigl_detail',
		'target':'_opener_tab'});
}


//在js里面直接调用action类
/**
 * p的定义
 * 
 * p.btn
 * p.serviceUid
 * p.serviceName
 * p.formName
 * p.paras
 * p.pml
 * p.pmlHeight
 * p.pmlWidth
 * p.target
 * p.echoJs
 * p.callType  //触发类别 default: us  ; others: uf
 * p.callback //回调函数  

 */

function callServiceBuzhuTj(p){
	
	if(p==null){
		return;
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
	if(!validateBuzhuTj(p.formName)){
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
	//只要设置了formName，就从表单中获取
	
	
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
	
	
    $.post(globalService,paras,
			function (data, textStatus){

			   if(data!=null && data.echo_msg!=null && $.trim(data.echo_msg)!=''){
			   		var errmsg = unescape(data.echo_msg);
			   		if(errmsg!='success'  && errmsg!='null'  && errmsg!='undefined'){
 		   		 			alert(errmsg);
			   		}
			   }
			 
 		   	   if(p.pml!=null) {
 		   		   
  		   		   if(p.target &&  $.trim(p.target)!=""){
  		   			  if(p.target=='_opener_window'){
  		   				  	window.open(title,p.pml + "&"  + urlCodeDeal(paras),'height=760,width=1012,left=0,top=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
  		   			  }else  if(p.target=='_opener_location'){
  		   				  	window.location = p.pml + "&"  + urlCodeDeal(paras) + "&isApp=true";
  		   			  }  
  		   			  else if(p.target=='_opener_tab'){
  							createNewTab(pmlName,title,p.pml);
  					  }else if(p.target=='_opener'){
  							popupDialog(pmlName,title,p.pml,p.pmlWidth,p.pmlHeight);
  					  }else{
  	 		   			 $("#" + p.target).empty().load(p.pml);  
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
	  },"json");
}


var data = '';

function validateBuzhuTj(formName) {
	//判断是否为新增
	var ifinsert = true;
	var mianbinsert = $("#gm_cw_worklog_insert_logmanage_wdate").val();
	if(typeof mianbinsert == "undefined"){
		ifinsert = false;
	}
	
	//需要验证所有字段
	var wdate = "";
	var waddress = "";
	var wseladdress = "";
	var wcontent = "";
	var wprogress = "";
	var wvacationtype = "";
	
	var v = getParasOfForms(formName);
	//开户验证
	if (v != null) {
		var arrays = new Array();
		arrays = v.split('&');
		if (arrays != null) {
			for (var i = 0; i < arrays.length; i++) {
				var array = arrays[i].split('=');
				if (array != null && array.length == 2) {
					if(array[0] == 'wdate')
						wdate = array[1];
					if(array[0] == 'waddress')
						waddress = array[1];
					if(array[0] == 'wseladdress')
						wseladdress = array[1];
					if(array[0] == 'wcontent')
						wcontent = array[1];
					if(array[0] == 'wprogress')
						wprogress = array[1];
					if(array[0] == 'wvacationtype')
						wvacationtype = array[1];
				}		
			}
		}
	}
	
	
	if(wdate != null)
		wdate = $.trim(wdate);
	else {
		wdate = "";
	}
	if(waddress != null)
		waddress = $.trim(waddress);
	else {
		waddress = "";
	}
	if(wseladdress != null)
		wseladdress = $.trim(wseladdress);
	else {
		wseladdress = "";
	}
	if(wcontent != null)
		wcontent = $.trim(wcontent);
	else {
		wcontent = "";
	}
	if(wprogress != null)
		wprogress = $.trim(wprogress);
	else {
		wprogress = "";
	}
	if(wvacationtype != null)
		wvacationtype = $.trim(wvacationtype);
	else {
		wvacationtype = "";
	}
	
	
	//是否已经有了这一天的记录
	var ifno = false;

	if(ifinsert) {
		 callService({'serviceName': 'cw_worklog_browse_ifhavewritethisday',
			'paras':'wdate='+wdate,
			'async':false,
			'callback':function(d){data = d[0].countsum},
			'callType':'sa'});
	} else {
		callService({'serviceName': 'cw_worklog_browse_ifhavewritethisday_update',
			'paras':'wdate='+wdate,
			'async':false,
			'callback':function(d){data = d[0].countsum},
			'callType':'sa'});
	}
	if(/^\d+$/.test(data) && data > 0) {
		alert(wdate+"的日志已经存在，请选择其他日期。");
		return false;
	} else {
		alert("判断错误：：："+data);
		return false;
	}
	if(wdate == "" || /^[+]+$/.test(wdate)) {
		alert("工作日期不能为空。");
		return false;
	}
	if(waddress == "" || /^[+]+$/.test(waddress)) {
		alert("工作地点不能为空。");
		return false;
	} else if(waddress == "2" || waddress == "3") {
		if(wseladdress == "" || /^[+]+$/.test(wseladdress)) {
			alert("工作地点不能为空。");
			return false;
		}
	} else if(waddress == "4") {
		if(wvacationtype == "") {
			alert("休假类型不能为空。");
			return false;
		}
	}
	
	if(wcontent == "" || /^[+]+$/.test(wcontent)) {
		alert("工作内容不能为空。");
		return false;
	}
	if(wprogress == "" || /^[+]+$/.test(wprogress)) {
		alert("工作进度不能为空。");
		return false;
	}
	return true;
}

 



//function callServiceBackNow(p) {
//	//初始化FckEditor值 
//	updateEditorFormValue();
//	//只要设置了formName，就从表单中获取
//	
//	
//	var paras = "";
//	if(p.paras){
//		paras = p.paras;
//		if(p.formName!=null && $.trim(p.formName)!=""){
//			paras = paras + "&" + getParasOfForms(p.formName);
//		}
//	}else if(p.paras==null && p.formName!=null && $.trim(p.formName)!=""){
//		paras =  getParasOfForms(p.formName);
//	}
//	var callType = "us";
//	if(p.callType){
//		callType = p.callType;
//	}
//
//	var  callServStr = "";
//	if(p.serviceUid){
//		callServStr = "contextServiceUid="+ p.serviceUid;
//	} else{
//		callServStr = "contextServiceName="+ p.serviceName;
//	}
//	
//	paras = callServStr + "&callType=" + callType  + "&" + urlCodeDeal(paras);
//	
///**
// * 使用$.post,异步，需使用 var con = confirm("你确定要保存日志吗？") 才能延迟得到执行后的结果
// * */	
////    var data = $.post(globalService,paras,
////			function (data, textStatus){
////			  return data;
////	  },"json");
////    
////    
////    var con = confirm("你确定要保存日志吗？");
////    
////    
////    var text = data.responseText;
////    var countsum = '';
////    if(text != null && text.indexOf(":") != -1) {
////    	var array = text.split(":");
////    	text = array[1];
////    	countsum = text.substring(1,2);
////    } 
////	if(con) {
////		return countsum;
////	} else {
////		return -1;
////	}
//	/**
//	 * 使用dojo,sync: true 同步
//	 * */
//	dojo.require("dojo.parser");
//	var myurl = globalService + "?" + paras;
//	var data = dojo.xhrGet({ 
//		  url:myurl,
//		  handleAs:"json",
//		  load:function(response,ioArgs){
//			 return response;
//		  },
//		  error:function(response,ioArgs){
//			  return response;
//		  },
//		  sync: true
//		 });
//    var countsum = -1;
//	var myresult = data.results[0];
//	var myerror =  data.results[1];
//	countsum = myresult[0].countsum;
//	if(countsum != null && countsum >= 0)
//		return countsum;
//	else if(myerror != null){
//		return myerror;
//	} else {
//		return -1;
//	}
//}