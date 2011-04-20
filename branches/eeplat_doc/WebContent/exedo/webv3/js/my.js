function cbAAA1(v){
	if(v!=null && v.length>0){
		alert("閮ㄩ棬鍚嶇О::" + v[0].name);
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
String.prototype.trim=function(){     
    return this.replace(/(^\s*)|(\s*$)/g, '');  
} 
function scan(){
	
	var packageNumber = $('#gm_image_info_scan_page_package_id').val();
	
	if(packageNumber.trim()===''){
		alert('璇疯緭鍏ユ壒娆″彿锛�');
		return;
	}
	
	var xml = "<zms><scan path=\""+packageNumber+"\" dpi=\"200\"></scan></zms>";
//	alert(xml);
	var Result =a4028803e29633fd80129633fd8ca0000.DOcxtest1.Zms(1100, xml);
		
	alert('鎵弿瀹屾垚!');
}

function scanNoView(){
		
	var xml = "<zms><scan path=\"1001\" dpi=\"200\"></scan></zms>";
//	alert(xml);
	var Result =zephyrOcx.DOcxtest1.Zms(1100, xml);
		
	alert('鎵弿瀹屾垚!');
}


/*************************************鍥剧墖鏀惧ぇ缂╁皬鏃嬭浆*********************************************/
//璁℃暟鍣�
var bitemp = 0;
// 鎷栨嫿瀵硅薄 
var ie=document.all; 
var nn6=document.getElementById&&!document.all;
var isdrag=false; 
var y,x; 
var oDragObj;
var dragObj;


function moveMouse(e) { 
	if (isdrag) { 
		oDragObj.style.top = (nn6 ? nTY + e.clientY - y : nTY + event.clientY - y)+"px"; 
		oDragObj.style.left = (nn6 ? nTX + e.clientX - x : nTX + event.clientX - x)+"px"; 
		return false; 
	} 
} 

function initDrag(e) {
	var oDragHandle = nn6 ? e.target : event.srcElement; 
	var topElement = "HTML"; 
	while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") 
	{ 
		oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement; 
	} 
	if (oDragHandle.className=="dragAble") { 
		isdrag = true; 
		oDragObj = oDragHandle; 
		nTY = parseInt(oDragObj.style.top+0); 
		y = nn6 ? e.clientY : event.clientY; 
		nTX = parseInt(oDragObj.style.left+0); 
		x = nn6 ? e.clientX : event.clientX; 
		document.onmousemove=moveMouse; 
		return false; 
	} 
} 
document.onmousedown=initDrag; 
document.onmouseup=new Function("isdrag=false"); 

function clickMove(s){ 
	if(s=="up"){ 
		dragObj.style.top = parseInt(dragObj.style.top) + 100; 
	}else if(s=="down"){ 
		dragObj.style.top = parseInt(dragObj.style.top) - 100; 
	}else if(s=="left"){ 
		dragObj.style.left = parseInt(dragObj.style.left) + 100; 
	}else if(s=="right"){ 
		dragObj.style.left = parseInt(dragObj.style.left) - 100; 
	} 

} 

function smallit(){ 
	if(bitemp>0){
		var height1=ie.images111.height; 
		var width1=ie.images111.width; 
		ie.images111.height=height1/1.2; 
		ie.images111.width=width1/1.2; 
		bitemp--;
	}
} 

function bigit(){ 
	if(bitemp<10){
		var height1=ie.images111.height; 
		var width1=ie.images111.width; 
		ie.images111.height=height1*1.2; 
		ie.images111.width=width1*1.2; 
		bitemp++;
	}
} 
function realsize() 
{ 
	ie.images111.height=ie.images222.height; 
	ie.images111.width=ie.images222.width; 
	ie.images111.style.filter="progid:DXImageTransform.Microsoft.BasicImage( Rotation='0')";
	block1.style.left = 0; 
	block1.style.top = 70; 
	bitemp = 0;
} 
function featsize() 
{ 
	var width1=images222.width; 
	var height1=images222.height; 
	var width2=360; 
	var height2=200; 
	var h=height1/height2; 
	var w=width1/width2; 
	
	if(height1<height2&&width1<width2){ 
		ie.images111.height=height1; 
		ie.images111.width=width1; 
	}else{ 
		if(h>w){ 
			ie.images111.height=height2; 
			ie.images111.width=width1*height2/height1; 
		}else{
			ie.images111.width=width2; 
			ie.images111.height=height1*width2/width1; 
		} 
	} 
	block1.style.left = 0; 
	block1.style.top = 0; 
} 

function MM_reloadPage(init) { //reloads the window if Nav4 resized 
if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) { 
document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }} 
else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload(); 
} 
MM_reloadPage(true); 

var i=1;
function playImg()
{
	ie.images111.style.filter="progid:DXImageTransform.Microsoft.BasicImage( Rotation="+i+")";
	i++;
	if(i>4)
	{i=1};
}





/*************************************鍥剧墖鏀惧ぇ缂╁皬鏃嬭浆*********************************************/
//璁℃暟鍣�
var bitemp = 0;
// 鎷栨嫿瀵硅薄 
var ie=document.all; 
var nn6=document.getElementById&&!document.all;
var isdrag=false; 
var y,x; 
var oDragObj;
var dragObj;


function moveMouse(e) { 
	if (isdrag) { 
		oDragObj.style.top = (nn6 ? nTY + e.clientY - y : nTY + event.clientY - y)+"px"; 
		oDragObj.style.left = (nn6 ? nTX + e.clientX - x : nTX + event.clientX - x)+"px"; 
		return false; 
	} 
} 

function initDrag(e) {
	var oDragHandle = nn6 ? e.target : event.srcElement; 
	var topElement = "HTML"; 
	while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") 
	{ 
		oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement; 
	} 
	if (oDragHandle.className=="dragAble") { 
		isdrag = true; 
		oDragObj = oDragHandle; 
		nTY = parseInt(oDragObj.style.top+0); 
		y = nn6 ? e.clientY : event.clientY; 
		nTX = parseInt(oDragObj.style.left+0); 
		x = nn6 ? e.clientX : event.clientX; 
		document.onmousemove=moveMouse; 
		return false; 
	} 
} 
document.onmousedown=initDrag; 
document.onmouseup=new Function("isdrag=false"); 

function clickMove(s){ 
	if(s=="up"){ 
		dragObj.style.top = parseInt(dragObj.style.top) + 100; 
	}else if(s=="down"){ 
		dragObj.style.top = parseInt(dragObj.style.top) - 100; 
	}else if(s=="left"){ 
		dragObj.style.left = parseInt(dragObj.style.left) + 100; 
	}else if(s=="right"){ 
		dragObj.style.left = parseInt(dragObj.style.left) - 100; 
	} 

} 

function smallit(){ 
	if(bitemp>0){
		var height1=ie.images111.height; 
		var width1=ie.images111.width; 
		ie.images111.height=height1/1.2; 
		ie.images111.width=width1/1.2; 
		bitemp--;
	}
} 

function bigit(){ 
	if(bitemp<10){
		var height1=ie.images111.height; 
		var width1=ie.images111.width; 
		ie.images111.height=height1*1.2; 
		ie.images111.width=width1*1.2; 
		bitemp++;
	}
} 
function realsize() 
{ 
	ie.images111.height=ie.images222.height; 
	ie.images111.width=ie.images222.width; 
	ie.images111.style.filter="progid:DXImageTransform.Microsoft.BasicImage( Rotation='0')";
	block1.style.left = 0; 
	block1.style.top = 70; 
	bitemp = 0;
} 
function featsize() 
{ 
	var width1=images222.width; 
	var height1=images222.height; 
	var width2=360; 
	var height2=200; 
	var h=height1/height2; 
	var w=width1/width2; 
	
	if(height1<height2&&width1<width2){ 
		ie.images111.height=height1; 
		ie.images111.width=width1; 
	}else{ 
		if(h>w){ 
			ie.images111.height=height2; 
			ie.images111.width=width1*height2/height1; 
		}else{
			ie.images111.width=width2; 
			ie.images111.height=height1*width2/width1; 
		} 
	} 
	block1.style.left = 0; 
	block1.style.top = 0; 
} 

function MM_reloadPage(init) { //reloads the window if Nav4 resized 
if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) { 
document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }} 
else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload(); 
} 
MM_reloadPage(true); 

var i=1;
function playImg()
{
	ie.images111.style.filter="progid:DXImageTransform.Microsoft.BasicImage( Rotation="+i+")";
	i++;
	if(i>4)
	{i=1};
}

/*
 * 固定表头，右侧添加滚动条
 * @param tableEl
 * @param tableHeight
 * @param tableWidth
 * @return
 */

function ScrollableTable (tableEl, tableHeight, tableWidth) {  
    this.initIEengine = function () {  
        this.containerEl.style.overflowY = 'auto';  
        if (this.tableEl.parentElement.clientHeight - this.tableEl.offsetHeight < 0) {  
            this.tableEl.style.width = this.newWidth - this.scrollWidth +'px';  
        } else {  
            this.containerEl.style.overflowY = 'hidden';  
            this.tableEl.style.width = this.newWidth +'px';  
        }  
   
        if (this.thead) {  
            var trs = this.thead.getElementsByTagName('tr');  
            for (x=0; x<trs.length; x++) {  
                trs[x].style.position ='relative';  
                trs[x].style.setExpression("top",  "this.parentElement.parentElement.parentElement.scrollTop + 'px'");  
            }  
        }  
   
        if (this.tfoot) {  
            var trs = this.tfoot.getElementsByTagName('tr');  
            for (x=0; x<trs.length; x++) {  
                trs[x].style.position ='relative';  
                trs[x].style.setExpression("bottom",  "(this.parentElement.parentElement.offsetHeight - this.parentElement.parentElement.parentElement.clientHeight - this.parentElement.parentElement.parentElement.scrollTop) + 'px'");  
            }  
        }  
   
        eval("window.attachEvent('onresize', function () { document.getElementById('" + this.tableEl.id + "').style.visibility = 'hidden'; document.getElementById('" + this.tableEl.id + "').style.visibility = 'visible'; } )");  
    };  
   
   
    this.initFFengine = function () {  
        this.containerEl.style.overflow = 'hidden';  
        this.tableEl.style.width = this.newWidth + 'px';  
   
        var headHeight = (this.thead) ? this.thead.clientHeight : 0;  
        var footHeight = (this.tfoot) ? this.tfoot.clientHeight : 0;  
        var bodyHeight = this.tbody.clientHeight;  
        var trs = this.tbody.getElementsByTagName('tr');  
        if (bodyHeight >= (this.newHeight - (headHeight + footHeight))) {  
            this.tbody.style.overflow = '-moz-scrollbars-vertical';  
            for (x=0; x<trs.length; x++) {  
                var tds = trs[x].getElementsByTagName('td');  
                tds[tds.length-1].style.paddingRight += this.scrollWidth + 'px';  
            }  
        } else {  
            this.tbody.style.overflow = '-moz-scrollbars-none';  
        }  
   
        var cellSpacing = (this.tableEl.offsetHeight - (this.tbody.clientHeight + headHeight + footHeight)) / 4;  
        this.tbody.style.height = (this.newHeight - (headHeight + cellSpacing * 2) - (footHeight + cellSpacing * 2)) + 'px';  
   
    };  
   
    this.tableEl = tableEl;  
    this.scrollWidth = 16;  
   
    this.originalHeight = this.tableEl.clientHeight;  
    this.originalWidth = this.tableEl.clientWidth;  
   
    this.newHeight = parseInt(tableHeight);  
    this.newWidth = tableWidth ? parseInt(tableWidth) : this.originalWidth;  
   
    this.tableEl.style.height = 'auto';  
    this.tableEl.removeAttribute('height');  
   
    this.containerEl = this.tableEl.parentNode.insertBefore(document.createElement('div'), this.tableEl);  
    this.containerEl.appendChild(this.tableEl);  
    this.containerEl.style.height = this.newHeight + 'px';  
    this.containerEl.style.width = this.newWidth + 'px';  
   
   
    var thead = this.tableEl.getElementsByTagName('thead');  
    this.thead = (thead[0]) ? thead[0] : null;  
   
    var tfoot = this.tableEl.getElementsByTagName('tfoot');  
    this.tfoot = (tfoot[0]) ? tfoot[0] : null;  
   
    var tbody = this.tableEl.getElementsByTagName('tbody');  
    this.tbody = (tbody[0]) ? tbody[0] : null;  
   
    if (!this.tbody) return;  
   
    if (document.all && document.getElementById && !window.opera) this.initIEengine();  
    if (!document.all && document.getElementById && !window.opera) this.initFFengine();  
   
   
}  
	      
 




