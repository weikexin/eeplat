function delxlsfiledata(){
	var isdel = confirm("这将删除该文件和该月份的所有数据记录,你确定要删除吗")
	
	if(isdel) {
		callAction({'actionName':'com.exedosoft.plat.login.zidingyi.DelExcelFile',
			'actionConfigName':'aaa',
			'paras':'deltype=all',
			'callback':delXlsFileBack});
	} else {
		return false;
	}

}

function delxlsfile(){
	var isdel = confirm("这将删除该文件和及其记录，只保留该月份的所有数据,你确定要删除吗")
	if(isdel) {
		callAction({'actionName':'com.exedosoft.plat.login.zidingyi.DelExcelFile',
			'actionConfigName':'aaa',
			'paras':'deltype=file',
			'callback':delXlsFileBack});
	} else {
		return false;
	}

}

function delxlsdata(){
	var isdel = confirm("这将该月份的所有数据记录，只保留该文件和及其记录,你确定要删除吗")
	if(isdel) {
		callAction({'actionName':'com.exedosoft.plat.login.zidingyi.DelExcelFile',
			'actionConfigName':'aaa',
			'paras':'deltype=data',
			'callback':delXlsFileBack});
	} else {
		return false;
	}

}

function delXlsFileBack() {
	loadPml({
 		'pml':'PM_gz_salarymessage_Result_gztiao_ls',
		'target':'PM_gz_salarymessage_Result_gztiao_ls'});
}

function bxmessagesload(btn, forName){
	if(btn){
	 	btn.disabled = true;
	}
	callAction({'actionName':'com.exedosoft.plat.login.zidingyi.ExcelLoad',
		'actionConfigName':'aaa',
		'callback':bxMsgCallBack,
		'formName':'a402880332837d9dd012837e184310006'});
}

function bxMsgCallBack(data){
	var flag = data.empty;
	if(flag == "notempty") {
		$('body').append("<div style='height: 100%; outline: 0pt none; width: 100%;' " +
				"class='simplemodal-wrap' tabindex='-1' id='gongzidiv'>" +
				"<div style='display: block;' class='simplemodal-data' id='confirm'> " + 	
				"<div class='header_gongzi'><span>导入工资条</span></div>" + 
				"<div class='message_gongzi'></div>" + 
				"<div class='buttons_gongzi'>" + 
					"<div class='no simplemodal-close'>取消</div>" + 
					"<div class='nocover'>否</div>" + 
					"<div class='cover'>是</div>" + 
				"</div></div></div>");
		$('#gongzidiv').hide();
		confirm_gongzi("数据有重复，确定全部覆盖吗？");
	} else {
		var isexist = data.isexist;
		if(isexist == 'isexist')
			bxMsgPaneBack();
		else 
			bxMsgPaneBack();
	}
}

function confirm_gongzi(message) {
	$('#confirm').modal({
		closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
		position: ["20%",],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			$('.message_gongzi', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.nocover', dialog.data[0]).click(function () {
				callAction({'actionName':'com.exedosoft.plat.login.zidingyi.ExcelLoad',
					'actionConfigName':'aaa',
					'paras':'iscover=nocover',
					'callback':bxMsgPaneBack,
					'formName':'a402880332837d9dd012837e184310006'});
				$.modal.close();
			});
			$('.cover', dialog.data[0]).click(function () {
				// call the callback
				callAction({'actionName': 'com.exedosoft.plat.login.zidingyi.ExcelLoad',
					'actionConfigName':'aaa',
					'paras':'iscover=cover',
					'callback':bxMsgPaneBack,
					'formName':'a402880332837d9dd012837e184310006'});
				// close the dialog
				$.modal.close();
			});
		}
	});
}

function bxMsgPaneBack() {
	loadPml({
 		'pml':'PM_gz_salarymessage_Main_gongzitiaodr',
		'target':'PM_gz_salarymessage_Main_gongzitiaodr'});
}

