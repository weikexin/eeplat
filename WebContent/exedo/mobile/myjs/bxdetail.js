//all browse
function bxdtsave(){
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327bdc35f0127bdda33aa005c'});
}
//all read
function bxdtread(){
	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d0c8320127d1bd2ab700dc'});
}
//yuangong
function bxdtyg(){
	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327bdc35f0127bddaac84009f'});
}
//dept
function bxdtdept(){
	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d2145e0127d2443872003d'});
}

function bxdtdeptread(){

	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d5cc850127d6125c63006c'});
}

function bxdtdeptsp(){

	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d28e110127d2afc354002d'});
}
//caiwu
function bxdtcw(){
	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d2145e0127d245810f0054'});
}

function bxdtcwsp(){

	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d28e110127d2b22cf10040'});
}
//total
function bxdtttread(){

	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d5cc850127d60da7cc0058'});
}

function bxdtttsp(){

	
	callService({'serviceName': 'cw_baoxiaoinfor_browse_printbxdetail',
		'callback':bxdtCallBack,
		'callType':'uf',
		'formName':'a4028803327d28e110127d2b4828a005d'});
}
function bxdtCallBack(data) {
	var filepath = data.exlfile;
	window.location="exedo/webv3/myjsp/download.jsp?filepath=" + filepath;
}

