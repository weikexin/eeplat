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
		<div align="center" class="oImgMaxBox"
				style="width: 100%; height: 415px; overflow: hidden; z-index: 100;">
				<#if filepath != '0000'> 
					<img 
				height="415" width="320" onmouseup="mymEnd();"
				onmousedown="mymStart(event);"
				style="position: relative; zoom: 100%; cursor: move; z-index: auto;"
				src="upload/${filepath}" title="${filename}" id="oImg" name="oImg" class="oImg">
				</#if>
		</div>
		</td></tr>
	</table>
</div>	
<script>
// 缩放图片
function myimgToSize(oBool) {
	var oImg = document.getElementById('oImg');
	var intZom = parseInt(oImg.style.zoom) + (oBool ? 20 : -20);
	if(intZom <= 0)
		return false;
	oImg.style.zoom = intZom + '%';
}

// 旋转图片
var oArcSize = 1;
function myimgRoll() {
	var oImg = document.getElementById('oImg');
	oImg.style.filter = 'Progid:DXImageTransform.Microsoft.BasicImage(Rotation='
			+ oArcSize + ')';
	oArcSize += 1;
	oArcSize = oArcSize == 4 ? 0 : oArcSize;
}

// 翻转图片
function myimgReverse(arg) {
	var oImg = document.getElementById('oImg');
	oImg.style.filter = 'Flip' + arg;
}


// 拖动图片
var oBoolean = false, oLeftSpace = 0, oTopSpace = 0;
function mymStart(e) {
	oBoolean = true;
	if (oBoolean) {
		var oImg = document.getElementById('oImg');
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
}

//还原图片
function mymReset() {
	var oImg = document.getElementById('oImg');
	oImg.style.zoom = '100%';
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

