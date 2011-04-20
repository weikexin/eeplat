//自动填写案卷档号
function fkinsertrollarchcode() {
	var type = "-FK-";
	var archtype = $('#gm_architem_insert_financing_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_insert_financing_unit').val();
	var filingyear = $('#gm_architem_insert_financing_filingyear').val();
	var rollarch = unit + type + filingyear + "-";
	var paras = "rollarchcode="+rollarch;
	callService({
		'serviceName' : 'architem_browse_count_rollcode',
		'paras':paras,
		'callback':fkinsertrollarchcodeBack,
		'callType' : 'sa'
	});

}

function fkinsertrollarchcodeBack(v) {
	var rollcode = v[0].rollcode;
	var len = 4-rollcode.length;
	for(var i = 0; i < len; i++) {
		rollcode = "0" + rollcode;
	}
	
	var type = "-FK-";
	var archtype = $('#gm_architem_insert_financing_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_insert_financing_unit').val();
	var filingyear = $('#gm_architem_insert_financing_filingyear').val();
	
	//案卷档号
	var rollarchcode = unit + type + filingyear + "-" + rollcode;
	$('#gm_architem_insert_financing_rollarchcode').val(rollarchcode);
	//目录号
	$('#gm_architem_insert_financing_rollcode').val(rollcode);
}

//自动填写案卷档号
function fkupdaterollarchcode() {
	var type = "-FK-";
	var archtype = $('#gm_architem_update_financing_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_update_financing_unit').val();
	var filingyear = $('#gm_architem_update_financing_filingyear').val();
	
	
		var rollarch = unit + type + filingyear + "-";
		var paras = "rollarchcode="+rollarch;
		callService({
			'serviceName' : 'architem_browse_count_rollcode',
			'paras':paras,
			'callback':fkupdaterollarchcodeBack,
			'callType' : 'sa'
		});
}

function fkupdaterollarchcodeBack(v) {
	var rollcode = v[0].rollcode;
	var len = 4-rollcode.length;
	for(var i = 0; i < len; i++) {
		rollcode = "0" + rollcode;
	}
	var type = "-FK-";
	var archtype = $('#gm_architem_update_financing_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_update_financing_unit').val();
	var filingyear = $('#gm_architem_update_financing_filingyear').val();
	
	//案卷档号
	var rollarchcode = unit + type + filingyear + "-" + rollcode;
	$('#gm_architem_update_financing_rollarchcode').val(rollarchcode);
	//目录号
	$('#gm_architem_update_financing_rollcode').val(rollcode);
	
	var curritemuid = $('#curritemuid').val();
	var paras = "objuid="+curritemuid;
	callService({
		'serviceName' : 'architem_browse',
		'paras':paras,
		'callback':fkupdaterollarchcodeBackII,
		'callType' : 'sa'
	});
}

function fkupdaterollarchcodeBackII(v) {
	var filingyear = $('#gm_architem_update_financing_filingyear').val();
	var filcurr = v[0].filingyear;
	if(filingyear == filcurr) {
		var rollarchcode = v[0].rollarchcode;
		var rollcode = v[0].rollcode;
		$('#gm_architem_update_financing_rollarchcode').val(rollarchcode);
		//目录号
		$('#gm_architem_update_financing_rollcode').val(rollcode);
	}
}


//填写承担人同时填写项目
function fksetinsertitemname() {
	var tenant = $('#gm_architem_insert_financing_tenant').val();
	$('#gm_architem_insert_financing_itemname').val(tenant);
}
//填写承担人同时填写项目
function fksetupdateitemname() {
	var tenant = $('#gm_architem_update_financing_tenant').val();
	$('#gm_architem_update_financing_itemname').val(tenant);
}



//自动填写案卷档号
function psinsertrollarchcode() {
	var type = "-FK-";
	var archtype = $('#gm_architem_insert_pingshen_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_insert_pingshen_unit').val();
	var filingyear = $('#gm_architem_insert_pingshen_filingyear').val();
	
	var rollarch = unit + type + filingyear + "-";
	var paras = "rollarchcode="+rollarch;
	callService({
		'serviceName' : 'architem_browse_count_rollcode',
		'paras':paras,
		'callback':psinsertrollarchcodeBack,
		'callType' : 'sa'
	});
	
}

function psinsertrollarchcodeBack(v) {
	var rollcode = v[0].rollcode;
	var len = 4-rollcode.length;
	for(var i = 0; i < len; i++) {
		rollcode = "0" + rollcode;
	}
	var type = "-FK-";
	var archtype = $('#gm_architem_insert_pingshen_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_insert_pingshen_unit').val();
	var filingyear = $('#gm_architem_insert_pingshen_filingyear').val();
	
	//案卷档号
	var rollarchcode = unit + type + filingyear + "-" + rollcode;
	$('#gm_architem_insert_pingshen_rollarchcode').val(rollarchcode);
	//目录号
	$('#gm_architem_insert_pingshen_rollcode').val(rollcode);
}

//自动填写案卷档号
function psupdaterollarchcode() {
	
	var type = "-FK-";
	var archtype = $('#gm_architem_update_pingshen_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_update_pingshen_unit').val();
	var filingyear = $('#gm_architem_update_pingshen_filingyear').val();
	
		var rollarch = unit + type + filingyear + "-";
		var paras = "rollarchcode="+rollarch;
		callService({
			'serviceName' : 'architem_browse_count_rollcode',
			'paras':paras,
			'callback':psupdaterollarchcodeBack,
			'callType' : 'sa'
		});
}

function psupdaterollarchcodeBack(v) {
	var rollcode = v[0].rollcode;
	var len = 4-rollcode.length;
	for(var i = 0; i < len; i++) {
		rollcode = "0" + rollcode;
	}
	
	var type = "-FK-";
	var archtype = $('#gm_architem_update_pingshen_archtype').val();
	if(archtype == '2c90b0482f0977b3012f0a735e5800cf') {
		type = "-FK-";
	} else if(archtype == '2c90b0482f0977b3012f0a73fe7100d0') {
		type = "-PS-";
	} else if(archtype == '2c90b0482f0977b3012f0a76846000d3') {
		type = "-ZH-";
	} else if(archtype == 'c90b0482f0977b3012f0a7638ff00d2') {
		type = "-SS-";
	} else if(archtype == '2c90b0482f0977b3012f0a75c59400d1') {
		type = "-BL-";
	} 
	var unit = $('#gm_architem_update_pingshen_unit').val();
	var filingyear = $('#gm_architem_update_pingshen_filingyear').val();
	//案卷档号
	var rollarchcode = unit + type + filingyear + "-" + rollcode;
	$('#gm_architem_update_pingshen_rollarchcode').val(rollarchcode);
	//目录号
	$('#gm_architem_update_pingshen_rollcode').val(rollcode);
	
	var curritemuid = $('#curritemuid').val();
	var paras = "objuid="+curritemuid;
	callService({
		'serviceName' : 'architem_browse',
		'paras':paras,
		'callback':psupdaterollarchcodeBackII,
		'callType' : 'sa'
	});
}

function psupdaterollarchcodeBack(v) {
	var filingyear = $('#gm_architem_update_pingshen_filingyear').val();
	var filcurr = v[0].filingyear;
	if(filingyear == filcurr) {
		var rollarchcode = v[0].rollarchcode;
		var rollcode = v[0].rollcode;
		$('#gm_architem_update_pingshen_rollarchcode').val(rollarchcode);
		//目录号
		$('#gm_architem_update_pingshen_rollcode').val(rollcode);
	} else {
		
	}
}

//填写承担人同时填写项目
function pssetinsertitemname() {
	var tenant = $('#gm_architem_insert_pingshen_tenant').val();
	$('#gm_architem_insert_pingshen_itemname').val(tenant);
}
//填写承担人同时填写项目
function pssetupdateitemname() {
	var tenant = $('#gm_architem_update_pingshen_tenant').val();
	$('#gm_architem_update_pingshen_itemname').val(tenant);
}



//填写 单击打开面板
function tronclick2c90b0fd2ec7b577012ec7dbc2b70047() {
	//PM_archcatalog_Result_financing 
	var selectedValue = $('#g2c90b0fd2ec7b577012ec7dbc2b70047 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	loadPml({
		'paras':dealBus, 
		'pml':'PM_archcatalog_lr_Result_financing',
		'target':'PM_archcatalog_lr_Main_kongbaiweizhi'
	});
}

//填写 单击打开面板 --评审
function tronclick2c90b0b92f57f050012f58046f280080() {
	//PM_archcatalog_Result_financing 
	var selectedValue = $('#g2c90b0b92f57f050012f58046f280080 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	loadPml({
		'paras':dealBus, 
		'pml':'PM_archcatalog_lr_Result_pingshen',
		'target':'PM_archcatalog_lr_Main_kongbaiweizhi_ps'
	});
}

//审批 单击打开面板
function tronclick2c90b0ec2edb7978012edb94bdb70022() {
	//PM_archcatalog_Main_arch_sp 
	var selectedValue = $('#g2c90b0ec2edb7978012edb94bdb70022 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	loadPml({
		'paras':dealBus, 
		'pml':'PM_archcatalog_sp_Main_arch_sp',
		'target':'PM_archcatalog_sp_Main_kongbaiweizhi'
	});
}

//查询1 单击打开面板
function tronclick2c90b0182ee21f40012ee2462b8c0037() {
	//PM_archcatalog_Main_arch_sp 
	var selectedValue = $('#g2c90b0182ee21f40012ee2462b8c0037 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	loadPml({
		'paras':dealBus, 
		'pml':'	PM_archcatalog_cx_Main_arch_cx_1',
		'target':'PM_archcatalog_cx_Main_kongbaiweizhi_cx_1'
	});
}



//审批 单击目录打开文件面板
function tronclick2c90b0ec2edb7978012edc4008380049() {
	//PM_archcatalog_Main_arch_sp 
	var selectedValue = $('#g2c90b0ec2edb7978012edc4008380049 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=archcatalog" + "&contextValue=" + selectedValue;
	loadPml({
		'paras':dealBus, 
		'title':'原文件影像',
		'pml':'PM_archfiles_sp_Main_hengpai_sp',
		'target':'_opener_tab'
	});
}

//审批 单击文件刷新面板
function tronclick2c90b0ec2edc4bdd012edc506c750002() {
	//PM_archcatalog_Main_arch_sp 
	var selectedValue = $('#g2c90b0ec2edc4bdd012edc506c750002 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=archfiles" + "&contextValue=" + selectedValue;
	//文件信息面板
	loadPml({
		'paras':dealBus,
		'pml':'PM_archfiles_browse_arch_zuo',
		'target':'PM_archfiles_browse_arch_zuo_quesheng'
	});
	//图像面板
	loadPml({
		'paras':dealBus, 
		'pml':'PM_archfiles_browse_wenjtp',
		'target':'PM_archfiles_sp_Main_kongmb_sp_you'
	});
}

//录入选择版本
function refreshCurrent_archlr(btn, fm, value, num, size) {
	var selectedValue = $('#g2c90b0fd2ec7b577012ec7dbc2b70047 tbody  tr.selected').attr('value');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	callPlatBus({'paras':dealBus});	
	invokePopup(btn, fm, value, num, size);
}

//原文件  单击打开面板

function tronclick2c90b0fd2eccca33012ecce629e30009() {
	//PM_archcatalog_Result_financing 
	var selectedValue = $('#g2c90b0fd2eccca33012ecce629e30009 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=archfiles" + "&contextValue=" + selectedValue;
	//文件信息面板
	loadPml({
		'paras':dealBus, 
		'pml':'PM_archfiles_browse_arch_zuo',
		'target':'PM_archfiles_browse_arch_zuo_quesheng'
	});
	
	//图像面板
	loadPml({
		'paras':dealBus, 
		'pml':'PM_archfiles_browse_wenjtp',
		'target':'PM_archfiles_rl_Main_kongmb_you'
	});
}


//查询1选择版本
function refreshCurrent_archcx1(btn, fm, value, num, size) {
	var selectedValue = $('#g2c90b0182ee21f40012ee2462b8c0037 tbody  tr.selected').attr('value');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	callPlatBus({'paras':dealBus});	
	invokePopup(btn, fm, value, num, size);
}

//查询2选择版本
function refreshCurrent_archcx2(btn, fm, value, num, size) {
	var selectedValue = $('#g2c90b18d2f14db1a012f14f533fb0026 tbody  tr.selected').attr('value');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	callPlatBus({'paras':dealBus});	
	invokePopup(btn, fm, value, num, size);
}

//修改申请审批 选择版本
function refreshCurrent_archxgsq(btn, fm, value, num, size) {
	var selectedValue = $('#g2c90b0f52f2d895f012f2db9fe8e003d tbody  tr.selected').attr('value');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	callPlatBus({'paras':dealBus});	
	invokePopup(btn, fm, value, num, size);
}


////查询  单击目录打开文件面板
//function tronclick2c90b1d92f10067f012f10321cd8002a() {
//	var selectedValue = $('#g2c90b1d92f10067f012f10321cd8002a tbody  tr.selected').attr('value');
//	var dealBus = "dataBus=setContext&contextKey=archcatalog" + "&contextValue=" + selectedValue;
//	loadPml({
//		'paras':dealBus, 
//		'title':'原文件影像',
//		'pml':'PM_archfiles_cx_Main_hengpai_cx_1',
//		'target':'_opener_tab'
//	});
//}

////原文件  单击打开面板
//function tronclick2c90b1d92f107d65012f108a85e60007() {
//	//PM_archcatalog_Result_financing 
//	var selectedValue = $('#g2c90b1d92f107d65012f108a85e60007 tbody  tr.selected').attr('value');
//	var dealBus = "dataBus=setContext&contextKey=archfiles" + "&contextValue=" + selectedValue;
//	
//	//图像面板
//	loadPml({
//		'paras':dealBus, 
//		'pml':'PM_archfiles_browse_wenjtp',
//		'target':'PM_archfiles_cx_Main_kongmb_cx_1_you'
//	});
//}


//打印点击目录刷新文件
function tronclick2c90b1d92f10067f012f10511f61004f() {
	var selectedValue = $('#g2c90b1d92f10067f012f10511f61004f tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=archcatalog" + "&contextValue=" + selectedValue;
	loadPml({
		'paras':dealBus, 
		'title':'原文件影像',
		'pml':'	PM_archfiles_cx_Result_archcx_print',
		'target':'PM_archcatalog_cx_Main_kongbaiweizhi_cx_print'
	});
}

////////////////////////设计器相关的函数mysortDown
//目录排序--放款
function  mysortUp2c90b0fd2eccca33012eccee9106001c(pml,target,objuid){
	var paras = "objuid="+objuid+"&type=sortup&pml="+pml+"&target="+target;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.ReSortCatalog',
		'callback':mysortBack,
		'paras':paras});
}
function  mysortDown2c90b0fd2eccca33012eccee9106001c(pml,target,objuid){
	var paras = "objuid="+objuid+"&type=sortdown&pml="+pml+"&target="+target;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.ReSortCatalog',
		'callback':mysortBack,
		'paras':paras});
}

//目录排序--评审
function  mysortUp2c90b0b92f581c1e012f582560920005(pml,target,objuid){
	var paras = "objuid="+objuid+"&type=sortup&pml="+pml+"&target="+target;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.ReSortCatalog',
		'callback':mysortBack,
		'paras':paras});
}
function  mysortDown2c90b0b92f581c1e012f582560920005(pml,target,objuid){
	var paras = "objuid="+objuid+"&type=sortdown&pml="+pml+"&target="+target;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.ReSortCatalog',
		'callback':mysortBack,
		'paras':paras});
}


function mysortBack(v) {
	var msg = v.msg;
	if(msg != null && msg != "" && typeof msg != "undefined") {
		alert(msg);
		return false;
	}
	var pml = v.pml;
	var target = v.target;
	
	if(pml == null || pml == "" || typeof pml == "undefined") {
		alert("目标面板不能为空");
		return false;
	}
	if(target == null || target == "") {
		target = pml;
	}
	loadPml({
 		'pml':pml,
		'target':target});
}

//原文件图像 排序
function  mysortUp2c90b1cf2f00d190012f00dc3f3c000d(pml,target,objuid){
	var paras = "objuid="+objuid+"&type=sortup&pml="+pml+"&target="+target;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.ReSortFiles',
		'callback':sortBack2c90b1cf2f00d190012f00dc3f3c000d,
		'paras':paras});
}
function  mysortDown2c90b1cf2f00d190012f00dc3f3c000d(pml,target,objuid){
	var paras = "objuid="+objuid+"&type=sortdown&pml="+pml+"&target="+target;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.ReSortFiles',
		'callback':sortBack2c90b1cf2f00d190012f00dc3f3c000d,
		'paras':paras});
}
function sortBack2c90b1cf2f00d190012f00dc3f3c000d(v) {
	var msg = v.msg;
	if(msg != null && msg != "" && typeof msg != "undefined") {
		alert(msg);
		return false;
	}
	var pml = v.pml;
	var target = v.target;
	
	if(pml == null || pml == "" || typeof pml == "undefined") {
		alert("目标面板不能为空");
		return false;
	}
	if(target == null || target == "") {
		target = pml;
	}
	loadPml({
 		'pml':pml,
		'target':target});
}

function refarchfiles(){
	var paras = "type=close";
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.ReSortFiles',
		'paras':paras});
}



///放款  扫描上传
function rzzlscan(btn) {
	btn.disabled = true;
	var selectedValue = $('#g2c90b0fd2ec7b577012ec7dbc2b70047 tbody  tr.selected').attr('value');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
		    btn.disabled = false;
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	loadPml({
		'paras':dealBus, 
		'pml':'PM_archcatalog_lr_browse_scanupload',
		'title':'扫描原文件',
		'target':'_opener_tab'
	});
	btn.disabled = false;
}

///权限审批1查看项目信息
function right_ckitem1(uid,btn) {
	//查找项目uid
	btn.disabled = true;
	var paras = "objuid="+uid;
	callService({
		'serviceName' : 'archrightsapp_browse_by_form_uid',
		'paras':paras,
		'callback':right_ckitem1Back,
		'callType' : 'sa'
	});
	btn.disabled = false;
}

function right_ckitem1Back(v) {
	var objuid = v[0].item_uid;
	//刷新总线
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + objuid;
	loadPml({
		'paras':dealBus, 
		'title':'项目信息',
		'pml':'PM_archcatalog_right_Main_arch_cx_rightsp_1',
		'target':'_opener_tab'
	});
}

function refreshCurrent_archright1(btn, fm, value, num, size) {
	var selectedValue = $('#g2c90b0fc2f2853ac012f2865062e000e tbody  tr.selected').attr('title');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	callPlatBus({'paras':dealBus});	
	invokePopup(btn, fm, value, num, size);
}

///权限审批2查看项目信息
function right_ckitem2(uid,btn) {
	//查找项目uid
	btn.disabled = true;
	var paras = "objuid="+uid;
	callService({
		'serviceName' : 'archrightsapp_browse_by_form_uid',
		'paras':paras,
		'callback':right_ckitem2Back,
		'callType' : 'sa'
	});
	btn.disabled = false;
}

function right_ckitem2Back(v) {
	var objuid = v[0].item_uid;
	//刷新总线
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + objuid;
	loadPml({
		'paras':dealBus, 
		'title':'项目信息',
		'pml':'PM_archcatalog_right_Main_arch_cx_rightsp_2',
		'target':'_opener_tab'
	});
}

function refreshCurrent_archright2(btn, fm, value, num, size) {
	var selectedValue = $('#g2c90b1d92f107d65012f109d52d40016 tbody  tr.selected').attr('title');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	callPlatBus({'paras':dealBus});	
	invokePopup(btn, fm, value, num, size);
}


///权限查看查看项目信息
function right_ckitemck(uid,btn) {
	//查找项目uid
	btn.disabled = true;
	var paras = "objuid="+uid;
	callService({
		'serviceName' : 'archrightsapp_browse_by_form_uid',
		'paras':paras,
		'callback':right_ckitemckBack,
		'callType' : 'sa'
	});
	btn.disabled = false;
}

function right_ckitemckBack(v) {
	var objuid = v[0].item_uid;
	//刷新总线
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + objuid;
	loadPml({
		'paras':dealBus, 
		'title':'项目信息',
		'pml':'PM_archcatalog_right_Main_arch_cx_right',
		'target':'_opener_tab'
	});
}

function refreshCurrent_archrightck(btn, fm, value, num, size) {
	var selectedValue = $('#g2c90b0fc2f295688012f298d81d7002a tbody  tr.selected').attr('title');
	if(selectedValue == null || typeof selectedValue == "undefined"){
		    alert("请选择一条记录！");
	         return;
    }
	var dealBus = "dataBus=setContext&contextKey=architem" + "&contextValue=" + selectedValue;
	callPlatBus({'paras':dealBus});	
	invokePopup(btn, fm, value, num, size);
}


