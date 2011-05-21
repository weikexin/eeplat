//审批 单击大类刷新小类主面板
function tronclick2c90b1062ee5c911012ee5e6c6210039() {
	//PM_archcatalog_Main_arch_sp 
	var selectedValue = $('#g2c90b1062ee5c911012ee5e6c6210039 tbody  tr.selected').attr('value');
	var dealBus = "dataBus=setContext&contextKey=dicitemcatalog" + "&contextValue=" + selectedValue;
	//文件信息面板
	loadPml({
		'paras':dealBus, 
		'pml':'PM_dicitemcatalog_Main_Tree',
		'title':'二级类型信息',
		'target':'_opener_tab'
	});
	
}

//删除二级类型
function deletesubtype(btn) {
	btn.disabled = true;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.DeleteDicItem',
		'paras':'type=delsub',
		'callback':deletesubtypeBack});
}
function deletesubtypeBack() {
	loadPml({
		'pml':'PM_dicitemcatalog_Main_Tree',
		'title':'二级类型信息',
		'target':'_opener_tab'
	});
}

//删除二级目录类型
function deletecatatype(uid,btn) {
	btn.disabled = true;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.DeleteDicItem',
		'paras':'type=delcatal',
		'callback':deletecatatypeBack});
}
function deletecatatypeBack() {
	loadPml({
		'pml':'PM_dicitemcatalog_Result_sub_type_catalog',
		'target':'PM_dicitemcatalog_Result_sub_type_catalog'
	});
}

//修改二级类型
function updatecatatype() {
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.DeleteDicItem',
		'paras':'type=updatecatal',
		'formName':'a2c90b0482f0977b3012f09b94357003d',
		'callback':updatecatatypeBack});
}
function updatecatatypeBack() {
	loadPml({
		'pml':'PM_dicitemcatalog_browse_mlxx',
		'target':'PM_dicitemcatalog_browse_mlxx'
	});
}
