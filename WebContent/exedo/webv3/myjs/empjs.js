function insertemp(btn,forname) {
	//表单验证
	if(!validate(forname)){
		if(btn){
		 	btn.disabled = false; 
			return;
		}
	}
	
	var sn = null;
	var cn = null;
	var mobile = null;
	var telephonenumber = null;
	var mail = null;
	var mailquotacount = null;
	var mailquotasize = null;
	var userpassword = null;
	
	
	var v = getParasOfForms(forname);
	var arrays = new Array();
	if (arrays != null) {
		for (var i = 0; i < arrays.length; i++) {
			var array = arrays[i].split('=');
			if (array != null && array.length == 2) {
				if(array[0] == 'sn')
					sn = array[1];
				if(array[0] == 'cn')
					cn = array[1];
				if(array[0] == 'mobile')
					mobile = array[1];
				if(array[0] == 'telephonenumber')
					telephonenumber = array[1];
				if(array[0] == 'mail')
					mail = array[1];
				
				if(array[0] == 'mailquotacount')
					mailquotacount = array[1];
				if(array[0] == 'mailquotasize')
					mailquotasize = array[1];
				if(array[0] == 'userpassword')
					userpassword = array[1];
			}		
		}
	}
	var paras = "sn="+sn+"&cn="+cn+"&mobile="+mobile+"&telephonenumber="+telephonenumber+"&mail="+mail+"&mailquotacount="+mailquotacount+"&mailquotasize="+mailquotasize+"&userpassword="+userpassword;
	
	callAction({'actionName':'com.exedosoft.plat.login.zidingyi.EmpInsert',
		'actionConfigName':'aaa',
		'paras':paras,
		'callback':insertempBackI});
}
	
function insertempBackI(v) {
	var paras = "sn="+v.sn+"&cn="+v.cn+"&mobile="+v.mobile+"&telephonenumber="+v.telephonenumber+"&mailmessagestore="+v.mailmessagestore+"&sn="+v.sn+"&mail="+v.mail+"&mailquotacount="+v.mailquotacount+"&mailquotasize="+v.mailquotasize+"&mailhost="+v.mailhost+"&userpassword="+v.userpassword;
	
	callService({'serviceName': 'zf_employee_insert',
			'paras':paras,
			'callback':insertempBackII,
			'callType':'uf'});
}

function insertempBackII(v) {
	loadPml({
 		'pml':'PM_zf_employee_Result',
		'target':'PM_zf_employee_Result'});
	
	var tabBtnSelector = "#dvTab table[tabId='PM_zf_employee_insert'] .btn";
	tabCloseWindow(tabBtnSelector);
}

function setinsertmail(){
	var sn = $('#gm_zf_employee_insert_sn').val();
	var mail = sn+'@zephyr.com.cn';
	$('#gm_zf_employee_insert_mail').val(mail);	
}

function setupdatemail(){
	var sn = $('#gm_zf_employee_update_sn').val();
	var mail = sn+'@zephyr.com.cn';
	$('#gm_zf_employee_update_mail').val(mail);	
}






//////导入节假日时间表//////////
function bzvacationload(btn,forName){
	if(btn){
	 	btn.disabled = true;
	}
	callAction({'actionName':'com.exedosoft.plat.login.zidingyi.ExcelLoadVacation',
		'actionConfigName':'aaa',
		'callback':bzVacationBack,
		'formName':'a402880f92d9c5ad7012d9c5c1ec10003'});
}
function bzVacationBack(data) {
	var flag = data.empty;
	var msg = data.msg;
	if(flag == "errordata") {
		var message = msg + "。年假或 倒休类型的日期不能为双休日，请更改。"
		alert(message);
		bzVacationPaneBack();
		return false;
	} else if(flag == "errordata2") {
		var message = msg + "。工作日类型的日期只能选择双休日，请更改。"
		alert(message);
		bzVacationPaneBack();
		return false;
	}
	
	if(flag == "notempty") {
		$('body').append("<div style='height: 100%; outline: 0pt none; width: 100%;' " +
				"class='simplemodal-wrap' tabindex='-1' id='vacationdiv'>" +
				"<div style='display: block;' class='simplemodal-data' id='confirm'> " + 	
				"<div class='header_vacation'><span>导入节假日期表</span></div>" + 
				"<div class='message_vacation'></div>" + 
				"<div class='buttons_vacation'>" + 
					"<div class='quxiao'>取消</div>" + 
					"<div class='nocover'>否</div>" + 
					"<div class='cover'>是</div>" + 
				"</div></div></div>");
		$('#vacationdiv').hide();
		confirm_vacation("数据有重复，确定全部覆盖吗？");
	} else {
		var isexist = data.isexist;
		if(isexist == 'isexist')
			return false;
		else 
			bzVacationPaneBack();
	}
}
function confirm_vacation(message) {
	$('#confirm').modal({
		closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
		position: ["20%",],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			$('.message_vacation', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.nocover', dialog.data[0]).click(function () {
				callAction({'actionName':'com.exedosoft.plat.login.zidingyi.ExcelLoadVacation',
					'actionConfigName':'aaa',
					'paras':'iscover=nocover',
					'callback':bzVacationBack,
					'formName':'a402880f92d9c5ad7012d9c5c1ec10003'});
				$.modal.close();
			});
			$('.cover', dialog.data[0]).click(function () {
				// call the callback
				callAction({'actionName': 'com.exedosoft.plat.login.zidingyi.ExcelLoadVacation',
					'actionConfigName':'aaa',
					'paras':'iscover=cover',
					'callback':bzVacationPaneBack,
					'formName':'a402880f92d9c5ad7012d9c5c1ec10003'});
				// close the dialog
				$.modal.close();
			});
			$('.quxiao', dialog.data[0]).click(function () {
				loadPml({
			 		'pml':'PM_cw_holidays_browse_load',
					'target':'PM_cw_holidays_browse_load'});
				$.modal.close();
			});
		}
	});
}

function bzVacationPaneBack() {
	loadPml({
 		'pml':'PM_cw_holidays_Main_load',
		'target':'PM_cw_holidays_Main_load'});
}

////////////////////////////////无刷新 flash上传
function uploadify_vacation(uploadifyID,uploadifyQueueID,fileDesc,fileExt,autoUpload,sessionid){
	
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
'scriptData'     :{'jsessionid':sessionid},
'script'         : 'exedo/webv3/upload_vacation.jsp?',
'cancelImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/cancel.png',
'queueID'        : uploadifyQueueID,
'auto'           : autoUpload,
'multi'          : true,
'simUploadLimit' : 2,
'buttonImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/browse-files.gif',
'wmode'          : 'transparent',
'width'          : 75,
'height'          : 25,
'fileDesc'       :fileDesc,
'fileExt'		 :fileExt,
'onSelect'       : function(event,queueID,fileObj){if(o.val()==""){ o.val(o.val() + fileObj.name);}else{ o.val(o.val() + ";" + fileObj.name);}},
'onCancel'       : function(event,queueID,fileObj,data){o.val(o.val().replace(fileObj.name,""));}
});
}


////////////////////////////////无刷新 flash上传
function uploadify_empinfor(uploadifyID,uploadifyQueueID,fileDesc,fileExt,autoUpload,emp_name){
	
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
'script'         : 'exedo/webv3/upload_empinfor.jsp?emp_name=' + emp_name ,
'cancelImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/cancel.png',
'queueID'        : uploadifyQueueID,
'auto'           : autoUpload,
'multi'          : true,
'simUploadLimit' : 2,
'buttonImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/browse-files.gif',
'wmode'          : 'transparent',
'width'          : 75,
'height'          : 25,
'fileDesc'       :fileDesc,
'fileExt'		 :fileExt,
'onSelect'       : function(event,queueID,fileObj){if(o.val()==""){ o.val(o.val() + fileObj.name);}else{ o.val(o.val() + ";" + fileObj.name);}},
'onCancel'       : function(event,queueID,fileObj,data){o.val(o.val().replace(fileObj.name,""));}
});
}


//日志回复调用
function callServiceHuFu(p) {
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
	if(!validateHuFu(p.formName)){
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


function validateHuFu(formName) {
	//需要验证所有字段
	var acontent = "";
	var zzhang = $("#gm_cw_apptext_insert_xinsp_zzhang_acontent").val();
	var zlingdao = $("#gm_cw_apptext_insert_xinsp_zlingdao_acontent").val();
	var dept = $("#gm_cw_apptext_insert_xinsp_dept_acontent").val();
	var deptxj = $("#gm_cw_apptext_insert_xinsp_dept_xj_acontent").val();
	if(typeof zzhang != "undefined"){
		acontent = $("#gm_cw_apptext_insert_xinsp_zzhang_acontent").val();
	}else if (typeof zlingdao != "undefined") {
		acontent = $("#gm_cw_apptext_insert_xinsp_zlingdao_acontent").val();
	}else if (typeof dept != "undefined") {
		acontent = $("#gm_cw_apptext_insert_xinsp_dept_acontent").val();
	}else if (typeof deptxj != "undefined") {
		acontent = $("#gm_cw_apptext_insert_xinsp_dept_xj_acontent").val();
	}
	
	if($.trim(acontent) == "" ) {
		alert("回复内容不能为空。");
		return false;
	}
	
	return true;
}

function datepicker() {
	var datetype = $("#gm_cw_holidays_insert_daytype").val();
	if(typeof datetype == "undefined") {
		datetype = $("#gm_cw_holidays_update_daytype").val();
	}
	if(datetype == "") {
		alert("请选择日期类型。");
		return false;
	} else if (datetype == "3" || datetype == "4") {
		WdatePicker({dateFmt:'yyyy-MM-dd',disabledDays:[0,6]});
	} else if (datetype == "2"){
		WdatePicker({dateFmt:'yyyy-MM-dd',disabledDays:[1,2,3,4,5]});
	} else {
		WdatePicker({dateFmt:'yyyy-MM-dd'});
	}
}

function lookwlogcurrent() {
	loadPml({
 		'pml':'PM_cw_worklog_Result_allowance_currentdetail',
		'target':'_opener_tab'});
}

function lookwloghistory() {
	loadPml({
 		'pml':'PM_cw_worklog_Main_allowance_tji_geren',
		'target':'_opener_tab'});
}

function bmjlspym() {
	loadPml({
 		'pml':'PM_cw_worklog_Main_logsp_dept',
		'target':'_opener_tab'});
}

function zjlspym() {
	loadPml({
 		'pml':'PM_cw_worklog_Main_logsp_zzhang',
		'target':'_opener_tab'});
}

function updatezf_employee(btn) {
	btn.disabled = true;
	callAction({'actionName': 'com.exedosoft.plat.action.ldap.InsertZf_employeeByLdap',
		'actionConfigName':'aaa',
		'callback':updatezf_employeeBack});
}

function updatezf_employeeBack(v) {
	alert("数据更新完毕。");
	loadPml({
 		'pml':'PM_zf_employee_Result',
		'target':'PM_zf_employee_Result'});
}