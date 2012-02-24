//newadd
function onlyread_insert_new() {
	var bxtype = $('#gm_cw_bxusefeedetail_insert_new_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_insert_new_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}

function onlyread_update_new() {
	var bxtype = $('#gm_cw_bxusefeedetail_update_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_update_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}

//sub
function onlyread_insert_sub() {
	var bxtype = $('#gm_cw_bxusefeedetail_insert_sub_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_insert_sub_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}

function onlyread_update_sub() {
	var bxtype = $('#gm_cw_bxusefeedetail_update_sub_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_update_sub_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}

//sub_dept
function onlyread_insert_sub_dept() {
	var bxtype = $('#gm_cw_bxusefeedetail_insert_sub_dept_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_insert_sub_dept_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}

function onlyread_update_sub_dept() {
	var bxtype = $('#gm_cw_bxusefeedetail_update_sub_dept_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_update_sub_dept_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}


//sub_caiwu
function onlyread_insert_sub_caiwu() {
	var bxtype = $('#gm_cw_bxusefeedetail_insert_sub_caiwu_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_insert_sub_caiwu_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}

function onlyread_update_sub_caiwu() {
	var bxtype = $('#gm_cw_bxusefeedetail_update_sub_caiwu_itemcode').val();
	if (bxtype == null || bxtype != 3) {
		$('#gm_cw_bxusefeedetail_update_sub_caiwu_hotelname').val(null);
		alert("非住宿费不能填写宾馆名称！");
	}
}