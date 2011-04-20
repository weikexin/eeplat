/**
 * 
 * 提交申请执行函数
 * 
 * **/
////////////////////放款//////////////////
//提交
var isdongingtj = false;
function archtjrzzl(uid, btn) {
	//
	if(isdongingtj) {
		alert("请不要重复点击！");
		return false;
	}
	isdongingtj = true;
	var paras = "objuid=" + uid;
	callService({
		'btn':btn,
		'serviceName' : 'archfiles_browse_if_filestatus_y',
		'paras' : paras,
		'callback' : archtjrzzlBackI,
		'callType' : 'sa'
	});
}

function archtjrzzlBackI(v) {
	var sum = v[0].sum;
	if (sum > 0) {
		 var submit = confirm("该项目有不合格的扫描文件,您确定还继续提交吗？")
		 if(!submit) {
			 isdongingtj = false;
			 return false;
		 }
	}
	//执行工作流程
	callService({
		'serviceName' : 'architem_update_spstatus_rukushq',
		'callback' : archtjrzzlBackII,
		'callType' : 'uf'
	});
	isdongingtj = false;
}

function archtjrzzlBackII(v) {
	loadPml({
		'pml':'PM_architem_Main_financing_result',
		'target':'PM_architem_Main_financing_result'
	});
	isdongingtj = false;
}

//重新提交
//提交
function archtjcxsqrk() {
	callService({
		'serviceName' : 'archfiles_browse_if_filestatus_y_curr',
		'callback' : archtjrzzlBackI,
		'callType' : 'sa'
	});
}




//提交
function archtjxgrk(uid, btn) {
	//
	if(isdongingtj) {
		alert("请不要重复点击！");
		return false;
	}
	isdongingtj = true;
	var paras = "objuid=" + uid;
	callService({
		'btn':btn,
		'serviceName' : 'archfiles_browse_if_filestatus_y',
		'paras' : paras,
		'callback' : archtjxgrkBackI,
		'callType' : 'sa'
	});
}

function archtjxgrkBackI(v) {
	var sum = v[0].sum;
	if (sum > 0) {
		 var submit = confirm("该项目有不合格的扫描文件,您确定还继续提交吗？")
		 if(!submit) {
			 isdongingtj = false;
			 return false;
		 }
	}
	//执行工作流程
	callService({
		'serviceName' : 'architem_update_spstatus_xgrukushq',
		'callback' : archtjxgrkBackII,
		'callType' : 'uf'
	});
	isdongingtj = false;
}

function archtjxgrkBackII(v) {
	loadPml({
		'pml':'PM_architem_Main_financing_result',
		'target':'PM_architem_Main_financing_result'
	});
	isdongingtj = false;
}

//重新提交
//提交
//提交
function archtjcxxgrk() {
	callService({
		'serviceName' : 'archfiles_browse_if_filestatus_y_curr',
		'paras' : paras,
		'callback' : archtjxgrkBackI,
		'callType' : 'sa'
	});
}


/////////////////评审/////////////////////
//提交
var isdongingpstj = false;
function archtjps(uid, btn) {
	//
	if(isdongingpstj) {
		alert("请不要重复点击！");
		return false;
	}
	isdongingpstj = true;
	var paras = "objuid=" + uid;
	callService({
		'btn':btn,
		'serviceName' : 'archfiles_browse_if_filestatus_y',
		'paras' : paras,
		'callback' : archtjpsBackI,
		'callType' : 'sa'
	});
}

function archtjpsBackI(v) {
	var sum = v[0].sum;
	if (sum > 0) {
		 var submit = confirm("该项目有不合格的扫描文件,您确定还继续提交吗？")
		 if(!submit) {
			 isdongingpstj = false;
			 return false;
		 }
	}
	//执行工作流程
	callService({
		'serviceName' : 'architem_update_spstatus_rukushq',
		'callback' : archtjpsBackII,
		'callType' : 'uf'
	});
	isdongingpstj = false;
}

function archtjpsBackII(v) {
	loadPml({
		'pml':'PM_architem_Main_pingsh_result',
		'target':'PM_architem_Main_pingsh_result'
	});
	isdongingpstj = false;
}

//重新提交
//提交
function archtjcxsqrkps() {
	callService({
		'serviceName' : 'archfiles_browse_if_filestatus_y_curr',
		'callback' : archtjpsBackI,
		'callType' : 'sa'
	});
}




//提交
function archtjxgrkps(uid, btn) {
	//
	if(isdongingtj) {
		alert("请不要重复点击！");
		return false;
	}
	isdongingtj = true;
	var paras = "objuid=" + uid;
	callService({
		'btn':btn,
		'serviceName' : 'archfiles_browse_if_filestatus_y',
		'paras' : paras,
		'callback' : archtjxgrkBackI,
		'callType' : 'sa'
	});
}

function archtjxgrkBackI(v) {
	var sum = v[0].sum;
	if (sum > 0) {
		 var submit = confirm("该项目有不合格的扫描文件,您确定还继续提交吗？")
		 if(!submit) {
			 isdongingtj = false;
			 return false;
		 }
	}
	//执行工作流程
	callService({
		'serviceName' : 'architem_update_spstatus_xgrukushq',
		'callback' : archtjxgrkBackII,
		'callType' : 'uf'
	});
	isdongingtj = false;
}

function archtjxgrkBackII(v) {
	loadPml({
		'pml':'PM_architem_Main_financing_result',
		'target':'PM_architem_Main_financing_result'
	});
	isdongingtj = false;
}

//重新提交
//提交
//提交
function archtjcxxgrk() {
	callService({
		'serviceName' : 'archfiles_browse_if_filestatus_y_curr',
		'paras' : paras,
		'callback' : archtjxgrkBackI,
		'callType' : 'sa'
	});
}



/**
 * 
 * 审批执行函数
 * 
 * **/



var isthissp = false;
//一级审批
function archsp(uid, btn) {
	//
	if(isthissp) {
		alert("请不要重复点击！");
		return false;
	}
	isthissp = true;
	
	var paras = "objuid=" + uid;
	callService({
		'btn':btn,
		'serviceName' : 'archfiles_browse_if_filestatus_y',
		'paras' : paras,
		'callback' : archspBackI,
		'callType' : 'sa'
	});
}

function archspBackI(v) {
	var sum = v[0].sum;
	if (sum > 0) {
		 var submit = confirm("该项目有不合格的扫描文件,您确定还继续审批通过吗？")
		 if(!submit) {
			 isthissp = false;
			 return false;
		 }
	}
	//执行工作流程
	callService({
		'serviceName' : 'architem_update_spstatus_rukushq_yunxu',
		'callback' : archspBackII,
		'callType' : 'uf'
	});
	isthissp = false;
}

function archspBackII(v) {
	loadPml({
		'pml':'PM_architem_Main_arch_sp_result',
		'target':'PM_architem_Main_arch_sp_result'
	});
	isthissp = false;
}



///打印选项
//封面
function printfengmian(btn) {
	btn.disabled = true;
	callService({
		'btn':btn,
		'serviceName' : 'architem_browse',
		'callback' : printfmBack,
		'callType' : 'sa'
	});
	btn.disabled = false;
}
function printfmBack(v) {
	var projectName = v[0].itemname;
	var date1 = v[0].leasebegin;
	var date2 = v[0].leaseend;
	var totalpcs = v[0].totalpcs;
	var totalpages = v[0].totalpages;
	var rollarchcode = v[0].rollarchcode;
	
	//归档号 没有对应 ， 暂时设置为目录号
	var filingcode = v[0].catalogcode;
	var retainterm = v[0].retainterm;
	if(retainterm == "yjbl") {
		retainterm = "永久";
	}
	
	var fondscode = v[0].fondscode;
	var catalogcode = v[0].catalogcode;
	var rollcode = v[0].rollcode;
	if(typeof projectName == "undefined" || projectName =="undefined"  || projectName ==null) {
		projectName = "";
	}
	if(typeof date1 == "undefined"  || date1 ==null) {
		date1 = "";
	}
	if(typeof date2 == "undefined" || date2 ==null) {
		date2 = "";
	}
	if(typeof totalpcs == "undefined"  || totalpcs ==null) {
		totalpcs = "";
	}
	if(typeof totalpages == "undefined"  || totalpages ==null) {
		totalpages = "";
	}
	if(typeof filingcode == "undefined" || filingcode ==null) {
		filingcode = "";
	}
	if(typeof retainterm == "undefined" || retainterm ==null) {
		retainterm = "";
	}
	
	if(typeof fondscode == "undefined"  || fondscode ==null) {
		fondscode = "";
	}
	if(typeof catalogcode == "undefined"  || catalogcode ==null) {
		catalogcode = "";
	}
	if(typeof rollcode == "undefined" || rollcode ==null) {
		rollcode = "";
	}
	if(typeof rollarchcode == "undefined" || rollarchcode ==null) {
		rollarchcode = "";
	}
	CreatePrintPage(projectName,date1,date2,totalpcs,totalpages,filingcode,retainterm,fondscode,catalogcode,rollcode,rollarchcode);
}
///打印 封面模板 放款
function CreatePrintPage(projectName,date1,date2,totalpcs,totalpages,filingcode,retainterm,fondscode,catalogcode,rollcode,rollarchcode) {
	var img = "<img width='15px' height='70px'  style='padding: 0;margin: 0'  src='exedo/webv3/code_pic/!.gif'/>"
	var arrayInt = new Array();
	var sum = 0;
	for(var r = 0; r < rollarchcode.length; r++) {
		var c = rollarchcode.substring(r,r+1);
		
		if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
			sum += 1;
			arrayInt[sum - 1] = c;
			img = img + "<img width='15px' height='70px'  style='padding: 0;margin: 0'  src='exedo/webv3/code_pic/"+c+".gif'/>"
		} else if(c == '-'){
			continue;
		} else {
			sum += 1;
			if(c == 'A') 
				arrayInt[sum - 1] = 10;	
			else if(c == 'B')
				arrayInt[sum - 1] = 11;	
			else if(c == 'C')
				arrayInt[sum - 1] = 12;	
			else if(c == 'D')
				arrayInt[sum - 1] = 13;	
			else if(c == 'E')
				arrayInt[sum - 1] = 14;	
			else if(c == 'F')
				arrayInt[sum - 1] = 15;	
			else if(c == 'G')
				arrayInt[sum - 1] = 16;	
			else if(c == 'H')
				arrayInt[sum - 1] = 17;	
			else if(c == 'I')
				arrayInt[sum - 1] = 18;	
			else if(c == 'J')
				arrayInt[sum - 1] = 19;	
			else if(c == 'K')
				arrayInt[sum - 1] = 20;	
			else if(c == 'L')
				arrayInt[sum - 1] = 21;	
			else if(c == 'M')
				arrayInt[sum - 1] = 22;	
			else if(c == 'N')
				arrayInt[sum - 1] = 23;	
			else if(c == 'O')
				arrayInt[sum - 1] = 24;	
			else if(c == 'P')
				arrayInt[sum - 1] = 25;	
			else if(c == 'Q')
				arrayInt[sum - 1] = 26;	
			else if(c == 'R')
				arrayInt[sum - 1] = 27;	
			else if(c == 'S')
				arrayInt[sum - 1] = 28;	
			else if(c == 'T')
				arrayInt[sum - 1] = 29;	
			else if(c == 'U')
				arrayInt[sum - 1] = 30;	
			else if(c == 'V')
				arrayInt[sum - 1] = 31;	
			else if(c == 'W')
				arrayInt[sum - 1] = 32;
			else if(c == 'X')
				arrayInt[sum - 1] = 33;	
			else if(c == 'Y')
				arrayInt[sum - 1] = 34;	
			else if(c == 'Z')
				arrayInt[sum - 1] = 35;
			img = img + "<img width='15px' height='70px'  style='padding: 0;margin: 0'  src='exedo/webv3/code_pic/"+c+".gif'/>"
		}
		
	}
	
	var b1 = 0;
	var b2 = 0;
	var all = 0;
	for(var k = 1; k <= sum; k ++) {
		var ai = arrayInt[k-1];
		all = parseInt(all) + parseInt(k*ai);
	}
	b1 = (all % 100)/10;
	b2 = (all % 100)%10;
	var b = b1+"";
	if(b.indexOf(".") != -1) {
		b = b.substring(0,1);
	}
	img = img + "<img width='15px' height='70px'  style='padding: 0;margin: 0'  src='exedo/webv3/code_pic/"+b+".gif'/>"
	img = img + "<img width='15px' height='70px'  style='padding: 0;margin: 0'  src='exedo/webv3/code_pic/"+b2+".gif'/>"
	var str = "";
	str = str + "<table border=0 width='100%'>";
	str = str + "<tr><td>";
	str = str + "<table border=1 width='100%' cellspacing='0' cellpadding='0' style='border-collapse:collapse' bordercolor='#000000'>";
	str = str + "<tr align='center' >";  
	str = str + "<td style='font-weight:bold;font-size:27pt' height='150px' colspan='4'>民生金融租赁股份有限公司</td>";  
	str = str + "</tr>";  
	str = str + "<tr align='center' >";  
	str = str + "<td style='font-weight:bold;font-size:28pt' height='150px' colspan='4'>项&nbsp;  &nbsp; 目&nbsp; &nbsp; 档 &nbsp; &nbsp; 案</td> ";   
	str = str + "</tr>";  
	str = str + "<tr align='center' >";  
	str = str + "<td  colspan='4'>";  
	str = str + "<table border=0 width='100%' align='center' >";  
	str = str + "<tr align='center' ><td style='font-weight:bold;font-size:20pt' height='180px' width='28%'>项目名称:</td>";  
	var pname = projectName;
	if(projectName.indexOf("公司") != -1) {
		if(projectName.length > 15) {
			var gs = projectName.substring(0,projectName.indexOf("公司")+2);
			var last = projectName.substring(projectName.indexOf("公司")+2);
			pname = gs+"<br>"+last;
		} else {
			pname = projectName;
		}
	} else if(projectName.length > 15) {
		var gs = projectName.substring(0,12);
		var last = projectName.substring(12);
		pname = gs+"<br>"+last;
	}
	str = str + "<td style='font-weight:bold;font-size:18pt' height='180px' colspan='3' >"+pname+"</td></tr>";  
	str = str + "<tr align='center' ><td style='font-weight:bold;font-size:20pt' width='28%' >租赁期限:</td>";  
	str = str + "<td style='font-weight:bold;font-size:18pt' width='31%' >"+date1+"</td>";  
	str = str + "<td style='font-weight:bold;font-size:20pt' width='10%' >至</td>";  
	str = str + "<td style='font-weight:bold;font-size:18pt' width='31%' >"+date2+"</td>";  
	str = str + "</tr>";  
	str = str + "<tr align='center' ><td style='font-weight:bold;font-size:18pt' height='70px' colspan='4' >&nbsp;</td></tr>";  
	str = str + "</table></td> </tr><tr><td  colspan='4'>";  
	str = str + "<table border=0 width='100%' align='center' >";  
	str = str + "<tr align='center' ><td style='font-weight:bold;font-size:14pt' width='28%' height='90px'>本卷共</td>";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='24%' >"+totalpcs+"</td>";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='6%' >件</td>";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='29%' >"+totalpages+"</td>";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='13%' >页</td>";  
	str = str + "</tr></table></td></tr>";  

	str = str + "<tr align='center' >";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='28%' height='90px'>归档号</td>";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='24%' >"+filingcode+"</td>";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='20%' >保管期限</td>";  
	str = str + "<td style='font-weight:bold;font-size:14pt' width='28%' >"+retainterm+"</td>";  
	str = str + "</tr></table></td></tr><tr><td height='8px' style='font-weight:bold;font-size:6pt'>&nbsp;</td></tr>";  
	str = str + "<tr><td><table border=0 width='100%' align='center' ><tr align='center'>";  
	str = str + "<td width='58%' height='90px'>"+img+"</td>";  
	str = str + "<td width='43%' height='90px'>";  
	str = str + "<table border=1 width='101%' cellspacing='0' cellpadding='0' style='border-collapse:collapse' bordercolor='#000000'>";  
	str = str + "<tr align='center' >";  
	str = str + "<td style='font-weight:bold;font-size:15pt' width='33%' height='45px'>全宗号</td>";  
	str = str + "<td style='font-weight:bold;font-size:12pt' width='33%' >目录号</td>";  
	str = str + "<td style='font-weight:bold;font-size:12pt' width='34%' >案卷号</td>";  
	str = str + "</tr><tr align='center' >";  
	str = str + "<td style='font-weight:bold;font-size:15pt' width='33%' height='45px'>"+fondscode+"</td>";  
	str = str + "<td style='font-weight:bold;font-size:12pt' width='33%' >"+catalogcode+"</td>";  
	str = str + "<td style='font-weight:bold;font-size:12pt' width='34%' >"+rollcode+"</td>";  
	str = str + "</tr></table></td></tr></table></td></tr></table>";  
	
	var objDiv = document.getElementById("indexprint");
	objDiv.innerHTML="";
	objDiv.innerHTML = str;
	objDiv.style.display = "none"; 
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INITA(0,0,800,1600,"民生金融租赁股份有限公司_打印封面");
	LODOP.ADD_PRINT_TABLE(80,25,690,950,document.getElementById("indexprint").innerHTML);
	LODOP.PREVIEW();
}	

///打印目录索引
function printcatalindex(btn) {
	btn.disabled = true;
	callService({
		'btn':btn,
		'serviceName' : 'archcatalog_browse_by_curr_catalog_item',
		'callback' : printcatalindexBack,
		'callType' : 'sa'
	});
	btn.disabled = false;
}
function printcatalindexBack(v) {
	var objDiv = document.getElementById("indexprint");
		objDiv.innerHTML="";
	var tenant = "";
	var titlename = new Array(25);
	var pagenumber = new Array(25);
	for(var n = 0; n < 25; n++) {
		titlename[n] = "";
		pagenumber[n]= "";
	}
	for(var i = 1; i < v.length+1; i++) {
		tenant = v[i-1].tenant;
		titlename[i-1] = v[i-1].titlename;
		pagenumber[i-1]= v[i-1].pagenumber;
	}
	if(typeof tenant == "undefined" || tenant =="undefined") {
		tenant = "";
	}
	var sum = 0;
	var str = "<table border=1 width='100%' cellspacing='0' cellpadding='0' style='border-collapse:collapse;border: none;'>" +
			"<thead><tr style='border-style: none none none none;'><th colspan='4' height='110px' style='font-weight:bold;font-size:20pt;border-style: none none none none;'>卷内文件目录</th></tr>" +
			"<tr><th width='85' style='font-weight:bold;	font-size:14pt;border-style: none none none none;' height='33px' >承租人:</th>";
		str = str+"<th align='left' style='font-weight:bold;	font-size:14pt;border-style: none none none none;' height='33px' colspan='3' >"+tenant+"</th>";
		str = str + "</th></tr><tr bordercolor='#000000'><th width='85' style='font-weight:bold;	font-size:14pt'>顺<br>序<br>号</th>" +
				"<th width='700' style='font-weight:bold;	font-size:14pt'>题&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 名</th>" +
				"<th width='90'  style='font-weight:bold;	font-size:14pt'>页&nbsp; &nbsp;号</th>" +
				"<th width='90' style='font-weight:bold;	font-size:14pt'>备&nbsp; &nbsp;注</th></tr></thead>";
	for(var n = 1; n <= v.length; n ++) {
		sum += 1;
		var titlen = titlename[n-1];
		if(typeof titlen == "undefined" || titlen =="undefined" || titlen == null ) {
			titlen = "";
		}
		var pagen = pagenumber[n-1]
		if(typeof pagen == "undefined" || pagen =="undefined"  || pagen ==null) {
			pagen = "";
		}
		str = str + "<tr align='center' bordercolor='#000000'>" +
				"<td style='font-size:11pt' height='29px'>"+n;
		str = str + "</td><td style='font-size:10pt'>"+titlen;
		str = str + "</td><td style='font-size:11pt'>"+pagen;
		str = str + "</td><td style='font-size:10pt'></td></tr>";
	}
	
	if(sum % 25 != 0) {
		var k = sum % 25;
		for(var m = k; m <25; m++) {
			str = str + "<tr align='center' bordercolor='#000000'>" +
			"<td style='font-size:11pt' height='29px'>";
			str = str + "</td><td style='font-size:10pt'>";
			str = str + "</td><td style='font-size:11pt'>";
			str = str + "</td><td style='font-size:10pt'></td></tr>";
		}
	}
	str = str + "<tfoot><tr style='border-style: none none none none;'>" +
			"<th align='center' colspan='4' style='font-size:9pt;border-style: none none none none;' height='29px'>" +
			"<span tdata='pageNO' format='#'>第<font color='#0000FF'>#</font>页</span>" +
			"<span tdata='pageCount' format='#'>/共<font color='#0000FF'>#</font>页</span>" +
			"</th></tfoot></table>";
	
	objDiv.innerHTML = str;
	objDiv.style.display = "none"; 
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INITA(0,0,800,1600,"民生金融租赁股份有限公司_打印目录索引");
	LODOP.ADD_PRINT_TABLE(60,25,690,740,document.getElementById("indexprint").innerHTML);
	LODOP.PREVIEW();
}


//条形码
//function printtiaoxm(btn) {
//	btn.disabled = true;
//	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.GetCurrItemCatalog',
//		'callback':printtiaoxmBack});
//	btn.disabled = false;
//}
//function printtiaoxmBack(v) {
//	var catalogs = v.catalogs;
//	var itemname = v.itemname;
//	var url = "exedo/webv3/txmcode.jsp?catalogs="+catalogs+"&itemname="+itemname;
//	alert(url);
//	window.open(url);
//}

//条形码
function printtiaoxm(btn) {
	btn.disabled = true;
	callService({
		'btn':btn,
		'serviceName' : 'archcatalog_browse_by_curr_version_catalog',
		'callback' : printtiaoxmBack,
		'callType' : 'sa'
	});
	btn.disabled = false;
}

function printtiaoxmBack(v) {
	if(v != null) {
		var array = new Array();
		for(var i = 0; i < v.length; i++) {
			array[i] = v[i].archcode;
		}
		CreatePrintPageTxm(array);
//		LODOP.SET_PRINT_PAGESIZE(1,2000,0,"A4");	
//		LODOP.PREVIEW();
	}
}
///打印条形码
function CreatePrintPageTxm(catalogs) {
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INITA(0,0,800,1600,"民生金融租赁股份有限公司_打印条形码");
	if(catalogs != null) {
		var str = "<table width='100%'>";
		for(var i = 0; i < catalogs.length; i++) {
			var catalog = catalogs[i];
			var img = "<tr><td height='90px'><img src='exedo/webv3/code_pic/!.gif'/>"
				var arrayInt = new Array();
				var sum = 0;
				for(var r = 0; r < catalog.length; r++) {
					var c = catalog.substring(r,r+1);
					if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
						sum += 1;
						arrayInt[sum - 1] = c;
						img = img + "<img src='exedo/webv3/code_pic/"+c+".gif'/>"
					} else if(c == '-'){
						continue;
					} else {
						sum += 1;
						if(c == 'A') 
							arrayInt[sum - 1] = 10;	
						else if(c == 'B')
							arrayInt[sum - 1] = 11;	
						else if(c == 'C')
							arrayInt[sum - 1] = 12;	
						else if(c == 'D')
							arrayInt[sum - 1] = 13;	
						else if(c == 'E')
							arrayInt[sum - 1] = 14;	
						else if(c == 'F')
							arrayInt[sum - 1] = 15;	
						else if(c == 'G')
							arrayInt[sum - 1] = 16;	
						else if(c == 'H')
							arrayInt[sum - 1] = 17;	
						else if(c == 'I')
							arrayInt[sum - 1] = 18;	
						else if(c == 'J')
							arrayInt[sum - 1] = 19;	
						else if(c == 'K')
							arrayInt[sum - 1] = 20;	
						else if(c == 'L')
							arrayInt[sum - 1] = 21;	
						else if(c == 'M')
							arrayInt[sum - 1] = 22;	
						else if(c == 'N')
							arrayInt[sum - 1] = 23;	
						else if(c == 'O')
							arrayInt[sum - 1] = 24;	
						else if(c == 'P')
							arrayInt[sum - 1] = 25;	
						else if(c == 'Q')
							arrayInt[sum - 1] = 26;	
						else if(c == 'R')
							arrayInt[sum - 1] = 27;	
						else if(c == 'S')
							arrayInt[sum - 1] = 28;	
						else if(c == 'T')
							arrayInt[sum - 1] = 29;	
						else if(c == 'U')
							arrayInt[sum - 1] = 30;	
						else if(c == 'V')
							arrayInt[sum - 1] = 31;	
						else if(c == 'W')
							arrayInt[sum - 1] = 32;
						else if(c == 'X')
							arrayInt[sum - 1] = 33;	
						else if(c == 'Y')
							arrayInt[sum - 1] = 34;	
						else if(c == 'Z')
							arrayInt[sum - 1] = 35;
						img = img + "<img src='exedo/webv3/code_pic/"+c+".gif'/>"
					}
				}
				
				var b1 = 0;
				var b2 = 0;
				var all = 0;
				for(var k = 1; k <= sum; k ++) {
					var ai = arrayInt[k-1];
					all = parseInt(all) + parseInt(k*ai);
				}
				b1 = (all % 100)/10;
				b2 = (all % 100)%10;
				var b = b1+"";
				if(b.indexOf(".") != -1) {
					b = b.substring(0,1);
				}
				img = img + "<img src='exedo/webv3/code_pic/"+b+".gif'/>"
				img = img + "<img src='exedo/webv3/code_pic/"+b2+".gif'/>"
				str = str+img+"</td></tr>";
		}
		str = str + "</table>";
		var objDiv = document.getElementById("indexprint");
		objDiv.innerHTML="";
		objDiv.innerHTML = str;
		objDiv.style.display = "none"; 
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INITA(0,0,800,1600,"民生金融租赁股份有限公司_打印目录条形码");
		LODOP.ADD_PRINT_TABLE(80,25,2100,2970,document.getElementById("indexprint").innerHTML);
		LODOP.PREVIEW();
	}
}



///////打印或下载
function CreateImageMSFL(path) {
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INIT("打印原文件影像图片");
	LODOP.ADD_PRINT_IMAGE(0,0,"210mm","297mm","<img border='0' src='"+path+"'/>");
	LODOP.SET_PRINT_STYLEA(0,"Stretch",1);
	//LODOP.ADD_PRINT_IMAGE(200,150,260,150,"C:/test.jpg"); //本地图片
};
//打印整个项目文件
function printall(btn) {
	btn.disabled = true;
	callService({
		'btn':btn,
		'serviceName' : 'archfiles_browse_print_all',
		'callback' : printallBack,
		'callType' : 'sa'
	});
}
function printallBack(v) {
	var arrayF = new Array();
	arrayF = v;
	var len = arrayF.length;
	for(var i =0; i < len; i++) {
		var filepath = v[i].filepath;
		if(filepath != "") {
			CreateImageMSFL('upload/'+filepath);
			LODOP.PRINT();;
		}
	}
	alert("打印完成");
	loadPml({
		'pml':'PM_architem_Condition_archcx_print',
		'target':'PM_architem_Condition_archcx_print'
	});
}

//打印单个文件
function printfile(btn) {
	//调用类，取得选中的文件图片path
	btn.disabled = true;
	callAction({'actionName':'com.exedosoft.plat.action.zidingyi.GetSelectFilesPath',
		'btn':btn,
		'formName':'a2c90b1d92f10067f012f1056d106005e',
		'callback':printfileBack});
}
function printfileBack(v) {
	var paths = v.filespath;
	if(paths == null || paths.trim == "") {
		alert("没有数据");
	} else {
		//打印方式
		var path = new Array();
		path = paths.split(";");
		for(var i = 0; i < path.length; i ++) {
			var filepath = path[i];
			if(filepath != "") {
				CreateImageMSFL('upload/'+filepath);
				LODOP.PRINT();;
			}
		}
		alert("打印完成");
	}
	loadPml({
		'pml':'PM_archfiles_cx_Result_archcx_print',
		'target':'PM_archfiles_cx_Result_archcx_print'
	});
}



//下载整个项目文件
var downloadallflag = false;
function downloadall(btn) {
	//调用类，取得选中的文件图片path
	if(downloadallflag) {
		alert("请不要重复点击！");
		return false;
	}
	downloadallflag = true;
	var paras = "type=all";
	callAction({
		'btn':btn,
		'actionName':'com.exedosoft.plat.action.zidingyi.DownloadFiles',
		'formName':'a2c90b1d92f10067f012f104400400046',
		'paras':paras,
		'callback':downloadallBack});
}

function downloadallBack(v) {
	var entryPath = v.entrypath;
	var fileName = v.filename;
	
	if(entryPath != null) {
		window.top.location.href="exedo/webv3/file/downloadfile_zip_del.jsp?filePath="+entryPath+"&fileName="+fileName;
	} else {
		alert("下载文件失败，请重新下载。");
	}
	downloadallflag = false;
	alert(123);
	//	PM_architem_Condition_archcx_xiazai 
	var tabBtnSelector = "#dvTab table[tabId='PM_architem_Condition_archcx_xiazai'] .btn";
	tabCloseWindow(tabBtnSelector);
}


//下载整个项目文件
var downloadall2nflag = false;
function downloadall2n(btn) {
	//调用类，取得选中的文件图片path
	if(downloadall2nflag) {
		alert("请不要重复点击！");
		return false;
	}
	downloadall2nflag = true;
	var paras = "type=number";
	callAction({
		'btn':btn,
		'actionName':'com.exedosoft.plat.action.zidingyi.DownloadFiles',
		'formName':'a2c90b1d92f10067f012f1056d106005e',
		'paras':paras,
		'callback':downloadall2nBack});
}

function downloadall2nBack(v) {
	var entryPath = v.entrypath;
	var fileName = v.filename;
	if(entryPath != null) {
		window.top.location.href="exedo/webv3/file/downloadfile_zip_del.jsp?filePath="+entryPath+"&fileName="+fileName;
		//下载后，更新有效次数
		callService({
			'serviceName' : 'archrightsapp_update_effecno-1_xz',
			'callType' : 'uf'
		});
	} else {
		alert("下载文件失败，请重新下载。");
	}
	downloadall2nflag = false;
}

//下载整个项目文件
var downloadall2tflag = false;
function downloadall2t(btn) {
	//调用类，取得选中的文件图片path
	if(downloadall2tflag) {
		alert("请不要重复点击！");
		return false;
	}
	downloadall2tflag = true;
	var paras = "type=time";
	callAction({
		'btn':btn,
		'actionName':'com.exedosoft.plat.action.zidingyi.DownloadFiles',
		'formName':'a2c90b1d92f10067f012f1056d106005e',
		'paras':paras,
		'callback':downloadall2tBack});
}

function downloadall2tBack(v) {
	var entryPath = v.entrypath;
	var fileName = v.filename;
	if(entryPath != null) {
		window.top.location.href="exedo/webv3/file/downloadfile_zip_del.jsp?filePath="+entryPath+"&fileName="+fileName;
	} else {
		alert("下载文件失败，请重新下载。");
	}
	downloadall2tflag = false;
}



