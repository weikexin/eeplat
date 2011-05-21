<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()>  
<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>
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
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
		<thead>
		<#assign colNum = model.colNum?default(2)/>
		<#if model.caption?exists>
			<#assign icon = (model.icon)?default("MyRightArrow.jpg")/>
			<tr> 
				<td class='title' colspan="2"> <img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> </td>
			</tr>
		</#if> 
		</thead>	
		
		<tbody>
			<#assign i = 0/>
			<#if (model.moreGridFormLinks?size > 0) >
			<#macro JudgeDisplay >
			</#macro>
			
			<#compress><@JudgeDisplay/></#compress>
			<#assign i = 0/>
			<#assign lasttype = ''>
			<#assign lastendtime = ''>
			<#assign nexttype = ''>
			<#assign nextendtime = ''>
			<#assign ttype = ''>
			<#assign tbcity = ''>
			<#assign tecity = ''>
			<#assign tbtime = ''>
			<#assign tetime = ''>
			<#assign tmiaosu = ''>
			<#assign nextobjuid = ''>
			
			<#list model.moreGridFormLinks as item>
				<#if item_index == 0 >
				    <#if '${dataBind(data,item)}' ==''><#assign lasttype = '${item.htmlValue}'></#if>
				<#elseif item_index == 1>
					<#if '${dataBind(data,item)}' ==''><#assign lastendtime = '${item.htmlValue}'></#if>
				<#elseif item_index == 2 >
				    <#if '${dataBind(data,item)}' ==''><#assign nexttype = '${item.htmlValue}'></#if>
				<#elseif item_index == 3>
					<#if '${dataBind(data,item)}' ==''><#assign nextendtime = '${item.htmlValue}'></#if>
				<#elseif item_index == 4 >
				    <#if '${dataBind(data,item)}' ==''><#assign ttype = '${item.htmlValue}'></#if>
				<#elseif item_index == 5>
					<#if '${dataBind(data,item)}' ==''><#assign tbcity = '${item.htmlValue}'></#if>
				<#elseif item_index == 6>
				    <#if '${dataBind(data,item)}' ==''><#assign tecity = '${item.htmlValue}'></#if>
				<#elseif item_index == 7>
				   <#if '${dataBind(data,item)}' ==''><#assign tbtime = '${item.htmlValue}'></#if>
				<#elseif item_index == 8>
					<#if '${dataBind(data,item)}' ==''><#assign tetime = '${item.htmlValue}'></#if>
				<#elseif item_index == 9>
					<#if '${dataBind(data,item)}' ==''><#assign tmiaosu = '${item.htmlValue}'></#if>
				<#elseif item_index == 10>
					<#if '${dataBind(data,item)}' ==''><#assign nextobjuid = '${item.htmlValue}'></#if>
			    </#if>
			</#list>
	<#--下面是面板表格内容部分-->		
			<tr><td colspan="2" >${ttype}</td></tr>
			
			<tr>
				<td style="text-align: center;font-size: 120%;font-weight: bold;width: 50%;">起点</td>
				<td style="text-align: center;font-size: 120%;font-weight: bold;width: 50%;">终点</td>
			</tr>
			<tr><td >
				<div style="float: left;">
					<div style="float: left;position: relative;top:2px;">出发地点：</div>
					<div style="float: left;">${tbcity}</div>
				</div>
				</td>
				<td >
				<div style="float: left;">
					<div style="float: left;position: relative;top:2px;">到达地点：</div>
					<div style="float: left;">${tecity}</div>
				</div>
				</td>
			</tr>
			<tr><td >出发时间：${tbtime}</td><td >到达时间：${tetime}</td></tr>
			
			<tr><td colspan="2" >
				<div style="float: left;">
					<div style="float: left;position: relative;top:35px;">其他说明：</div>
					<div style="float: left;">${tmiaosu}</div>
				</div>
				<div style="display: none;">${lasttype}${lastendtime}${nexttype}${nextendtime}${nextobjuid}</div>
			</td></tr>
	<#--下面是按钮部分-->
			<#if  (model.bottomOutGridFormLinks?size > 0)>
				<tr class="buttonMore" > <td  style="text-align:center;align:center"  colspan="2" >
				<#list model.bottomOutGridFormLinks as item> 
			          <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
				</#list>
				</td></tr>
				</#if>		
			</#if> 	
		</tbody>
	  </table>
</form>	
</div>	
<script>
var iflxtype = document.getElementById("leixingtype");
var lasttype = document.getElementById("lasttype");
var nexttype = document.getElementById("nexttype");
var ifbegin = document.getElementById("beginaddress");
var ifend = document.getElementById("endaddress");
function ifcitymodify() {
	var index = iflxtype.value;
	if(index == '1') {
		ifbegin.value = '1';
		if(ifend.value == "" || ifend.value == '1')
			ifend.value='3';
		beginchange();
		endchange();
		ifbegin.disabled=true;
		ifend.disabled=false;
	} else if(index == '2'){
		ifbegin.value = '3';
		ifend.value='3';
		beginchange();
		endchange();
		ifbegin.disabled=false;
		ifend.disabled=false;
	
	} else if(index == '3') {
		if(ifbegin.value == "" || ifbegin.value == '1')
			ifbegin.value='3';
		ifend.value = '1';
		beginchange();
		endchange();
		ifend.disabled=true;
		ifbegin.disabled=false;
	} else {
		ifbegin.disabled=false;
		ifend.disabled=false;
	}
}

function ifbcitynochange() {
	var index = iflxtype.value;
	if(index == '1') {
		if(ifend.value == "" || ifend.value == '1')
			ifend.value='3';
		endchange();
		ifend.disabled=false;
	} else if(index == '2'){
		ifbegin.value = '3';
		ifend.value='3';
		beginchange();
		endchange();
		ifbegin.disabled=false;
		ifend.disabled=false;
	} else if(index == '3') {
		ifend.value = '1';
		endchange();
		ifend.disabled=true;
	} else {
		ifend.disabled=false;
	}
}

if($.trim(lasttype.value) == "") {
	ifcitymodify();
} else {
	ifbcitynochange();
} 
beginchange();
endchange();

	
 $('#g${model.objUid} tbody  tr').bind('click',function(){
			$('#g${model.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$(this).addClass("selected");
 });
</script>

