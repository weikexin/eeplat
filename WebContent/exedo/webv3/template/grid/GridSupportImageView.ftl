<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()>  
<#if model.numTopP?exists>
	<#if model.numTopP != 0>
		<#list 1..model.numTopP as x>  
			<br/>
    	</#list>
	</#if>
</#if>
<style type="text/css">
.oImgMaxBox
{
width: MaxWidth;
height: MaxHeight;
position: relative;
overflow: hidden;
}
.oImg
{
position: absolute;
top:-(imgHeight - MaxHeight) / 2;
left:-(imgWidth - MaxWidth) / 2;
width: imgWidth;
height: imgHeight;
}
</style>
<div width="100%">
<#if (model.topOutGridFormLinks?size > 0) > 
	<DIV class="toolbar" style="BORDER-RIGHT: #8db2e3 1px solid; BORDER-TOP: #8db2e3 1px solid; BORDER-LEFT: #8db2e3 1px solid; BORDER-BOTTOM: #8db2e3 1px solid">
		<DIV align="left"><!--布局用-->
			<TABLE>
				<TBODY>
					<TR>
						<TD style="WIDTH: 2px"></TD><!--左缩进-->
						<#list model.topOutGridFormLinks as item>
							<TD>
								<TABLE  cellSpacing=0 cellPadding=0>
									<TBODY>
										<TR class="">
											<TD class="b_left"></TD>
											<TD class="b_center">${item.htmlValue}</TD>
											<TD class="b_right"></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
							<#if ((item_index+1)!=(model.topOutGridFormLinks?size))>
							<TD><SPAN class="spacer"></SPAN></TD><!--分隔条-->
							</#if>
						</#list>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
	</DIV>
</#if>
	<table id='g${model.objUid}' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
		<tr><td>
		<#assign filename = '&nbsp;'/>
		<#assign filepath = '&nbsp;'/>
		<#if (model.moreGridFormLinks?size > 0) >
			<#list model.moreGridFormLinks as item>
				<#if item_index == 0> 
					<#if '${dataBind(data,item)}' ==''> <#assign filename = '${item.htmlValue}'/> </#if>
				</#if>
				<#if item_index == 1> 
					<#if '${dataBind(data,item)}' ==''> <#assign filepath = '${item.htmlValue}'/> </#if>
				</#if>
			</#list>	
		</#if>
			<div id="oImgDiv" align="center" class="oImgMaxBox"
				style="height:450px; overflow: hidden;background-color:#F2FCF2;text-align:center;">
				<#if !filepath?ends_with('0000')> 
					<img onmouseup="mymEnd();"
				onmousedown="mymStart(event);"
				style="position: relative; zoom: 100%; cursor: move; z-index: auto;"
				width="820" src="${filepath}" title="${filename}" id="oImg" name="oImg" class="oImg">
			</#if>
		</div>
		</td></tr>
	</table>
</div>	
<script>



var clientH = document.body.clientHeight;
var clientW = document.body.clientWidth;
var oImgDiv =  document.getElementById('oImgDiv');
var oImgPic = document.getElementById('oImg');


var divPM =  document.getElementById('FPM_archfiles_browse_wenjtp_danmb');
if(divPM == null) {
	divPM =  document.getElementById('FPM_archfiles_browse_wenjtp_danmb_dayin');
	if(divPM == null) {
		divPM =  document.getElementById('FPM_archfiles_browse_wenjtp_danmb_xiazai');
		if(divPM == null) {
			divPM =  document.getElementById('FPM_archfiles_browse_wenjtp_danmb_dyxz');
		}
	}
	
}

var imgInitW = 820;
var imgInitH = 820;
if(oImgPic!=null) {
	var imgWidth = oImgPic.width;
	var imgHeight = oImgPic.height;
	imgInitH = 820*imgHeight/imgWidth;
}
//单面板
if(divPM!=null) {
	var browser=navigator.appName;
	var b_version=navigator.appVersion;
	var version=b_version.split(";");
	var len = version.length;
	var trim_Version="";
	if(len > 1) {
		trim_Version=version[1].replace(/[ ]/g,"");
	}
	if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE7.0")
	{
    	if(oImgPic != null) {
			var imgWidth = oImgPic.width;
			var imgHeight = oImgPic.height;
			oImgPic.height = 820*imgHeight/imgWidth;
			oImgPic.width = 820;
			if(oImgDiv != null) {
				oImgDiv.style.height = (clientH-120)+'px';
				oImgDiv.style.width = '860px';
				oImgDiv.style.overflow= "scroll";
			}
		}
		divPM.style.top = '2px';
		divPM.style.height = (clientH-5)+'px';
		if(oImgDiv != null) {
			oImgDiv.style.height = (clientH-120)+'px';
			oImgDiv.style.width = '860px';
			oImgDiv.style.overflow= "scroll";
		}
	} else {
   		if(oImgPic != null) {
			var imgWidth = oImgPic.width;
			var imgHeight = oImgPic.height;
			oImgPic.height = 820*imgHeight/imgWidth;
			oImgPic.width = 820;
			if(oImgDiv != null) {
				oImgDiv.style.height = (clientH-120)+'px';
				oImgDiv.style.width = '860px';
				oImgDiv.style.overflow= "scroll";
			}
		}
		divPM.style.top = '2px';
		divPM.style.height = (clientH-5)+'px';
		if(oImgDiv != null) {
			oImgDiv.style.height = (clientH-120)+'px';
			oImgDiv.style.width = '860px';
			oImgDiv.style.overflow= "scroll";
		}
	}
	
} 
//嵌入在主面板中
else {
	if(oImgPic != null) {
		var imgWidth = oImgPic.width;
		var imgHeight = oImgPic.height;
		oImgPic.height = 820*imgHeight/imgWidth;
		oImgPic.width = 820;
		if(oImgDiv != null) {
			oImgDiv.style.height = (clientH-130) + 'px';
			oImgDiv.style.width = clientW*0.66+'px';
			oImgDiv.style.overflow= "scroll";
		}
	}
}


// 缩放图片
function myimgToSize(oBool) {
	var oImg = document.getElementById('oImg');
	if(oImg == null || typeof oImg == 'undefined') {
		alert("请先选择一张图片");
		return false;
	}
	var oImgZW = oImg.width;
	var oImgZH = oImg.height;
	var intZom = parseInt(100) + parseInt(oBool ? 20 : -20);
	if(intZom <= 0)
		return false;
	oImg.width = oImgZW*intZom/100;
	oImg.height = oImgZH*intZom/100;
}

// 旋转图片
var oArcSize = 1;
function myimgRoll() {
	var oImg = document.getElementById('oImg');
	if(oImg == null || typeof oImg == 'undefined') {
		alert("请先选择一张图片");
		return false;
	}
	oImg.style.zoom = '100%';
//	var vM11 = cos(0);
//	var vM12 = -sin(0);
//	var vM21 = sin(0);
//	var vM22 = cos(0);
	var vM11 = 1.0;
	var vM12 = 0.0;
	var vM21 = 1.0;
	var vM22 = 0.0;
	oArcSize = oArcSize == 4 ? 0 : oArcSize;
	 switch(oArcSize)
    {
   		case 0:
			vM11 = 1.0;
			vM12 = 0.0;
			vM21 = 0.0;
			vM22 = 1.0;
			break;
    	case 1:
			vM11 = 0.0;
			vM12 = -1.0;
			vM21 = 1.0;
			vM22 = 0.0;
			break;
			
		case 2:
			
			vM11 = -1.0;
			vM12 = 0.0;
			vM21 = 0.0;
			vM22 = -1.0;
			break;
		case 3:
			
		
			vM11 = 0.0;
			vM12 = 1.0;
			vM21 = -1.0;
			vM22 = 0.0;
			break;
               
     }
	oImg.style.filter = "Progid:DXImageTransform.Microsoft.Matrix(SizingMethod='auto expand',,M11="+vM11+",M12="+vM12+",M21="+vM21+",M22="+vM22+")";
	//oImg.style.filter = 'Progid:DXImageTransform.Microsoft.BasicImage(Rotation=' + oArcSize + ')';

	oArcSize += 1;
}

// 翻转图片
function myimgReverse(arg) {
	var oImg = document.getElementById('oImg');
	if(oImg == null || typeof oImg == 'undefined') {
		alert("请先选择一张图片");
		return false;
	}
	oImg.style.filter = 'Flip' + arg;
}


// 拖动图片
var oBoolean = false, oLeftSpace = 0, oTopSpace = 0;
function mymStart(e) {
	oBoolean = true;
	if (oBoolean) {
		var oImg = document.getElementById('oImg');
		if(oImg == null || typeof oImg == 'undefined') {
			alert("请先选择一张图片");
			return false;
		}
		if (window.Event) {
			//火狐  暂时没有实现
		} else {
			oLeftSpace = window.event.clientX - oImg.style.pixelLeft;
			oTopSpace = window.event.clientY - oImg.style.pixelTop;
		}
		document.onmousemove = function(ev) {
			if (window.Event) {
				//火狐  暂时没有实现
				return false;
			} else {
				if (window.event.button == 1 && oBoolean) {
					oImg.style.pixelLeft = window.event.clientX - oLeftSpace;
					oImg.style.pixelTop = window.event.clientY - oTopSpace;
					return false;
				}
			}

		}
	}

}
function mymEnd() {
	oBoolean = false;
	document.onmousemove = null;
	return false;
}

//还原图片
function mymReset() {
	var oImg = document.getElementById('oImg');
	if(oImg == null || typeof oImg == 'undefined') {
		alert("请先选择一张图片");
		return false;
	}
	oImg.width = imgInitW;
	oImg.height = imgInitH;
	oImg.left=0;
	oImg.top=0;
}

	

// 按钮示例
// <div style="position:relative; z-index:1000;">
// <input type="button" value="放大" onClick="myimgToSize(1);">
// <input type="button" value="缩小" onClick="myimgToSize(0);"/>
// <input type="button" value="旋转" onClick="myimgRoll();">
// <input type="button" value="水平翻转" onClick="myimgReverse('H');"/>
// <input type="button" value="垂直翻转" onClick="myimgReverse('V');"/>
// </div>
</script>

