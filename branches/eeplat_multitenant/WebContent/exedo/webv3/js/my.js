function cbAAA1(v){
	if(v!=null && v.length>0){
		alert("回调返回的值::" + v[0].name);
	}
	//$('#gm_do_org_user_insert_name').val(v);
}
function aaa1(){

	var selectValue  = $('#gm_do_org_user_insert_deptuid').val();
	var paras = 'objuid=' + selectValue;
	callService({'serviceName':'do_org_dept_findobjuidform',
				 'paras':paras, 
				 'callType':'sa',
		         'callback':cbAAA1});
	
}
