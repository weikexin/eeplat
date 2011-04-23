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
<style type="text/css">
<!--
table.mytablesorter {
	font-size:12px;
	background-color: #CDCDCD;
	margin:0px 5px 5px 5px;
	font-size: 12px;
	width: 600px;
	text-align: center;
	valign:top;
}
table.mytablesorter tr{
	height:20px;
}
table.mytablesorter td {
	color: #3D3D3D;
	padding: 1px;
	padding-left:2px;
	background-color: #FFF;
	text-align: center;
	vertical-align: top; 
}
table.mytablesorter thead tr th,table.mytablesorter thead tr td{
			text-align: left; 
}

table.mytablesorter thead tr th,table.mytablesorter tr td.title{
		background-color:#e6EEEE;
		border: 1px solid #FFF;
		font-size: 12px;
		padding: 1px;
		vertical-align: middle; 
}
table.mytablesorter tfoot tr th {
 		background-color:#e6EEEE;
		border: 1px solid #FFF;
		font-size: 12px;
}
table.mytablesorter thead tr .header {
	background-image: url(bg.gif);
	background-repeat: no-repeat;
	background-position: center right;
	cursor: pointer;
	vertical-align: middle; 
}

.firstPage,.prevPage,.pagedisplay,.nextPage,.lastPage{
	margin-bottom:-4px;
	cursor:pointer;
}

table.mytablesorter thead tr .headerSortUp {
	background-image: url(asc.gif);
}
table.mytablesorter thead tr .headerSortDown {
	background-image: url(desc.gif);
}
table.mytablesorter thead tr .headerSortDown, table.mytablesorter thead tr .headerSortUp {
	background-color: #8dbdd8;
}
table.mytablesorter tbody tr.selected td{
    background-color:yellow;
}
table.mytablesorter tbody tr.mover td{
	background-color: #a6c2e7;
}

table.mytablesorter tfoot tr td {
		background-color:#e6EEEE;
		border: 1px solid #FFF;
		font-size: 12px;
}

table.mytablesorter tbody tr.zebra td{
	background-color: #ECF5FF;
}

table.mytablesorter tbody tr.selected:hover td{
	background-color:#ff9;
}


.table_checkbox{width:12px; height:12px}
-->
</style>

<div width="100%" align="left" height="100%">
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' class='mytablesorter' border="0" cellpadding="1" cellspacing="1" >
		<thead>
		<#if model.caption?exists>
			<#assign icon = (model.icon)?default("MyRightArrow.jpg")/>
			<tr> 
				<td class='title'> <img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> </td>
			</tr>
		</#if> 
		</thead>	
		<tbody>
			<tr><td>
			<#assign colNum = (model.colNum)?default(3)/>
			<#assign i = 0/>
			<#list model.normalGridFormLinks as item>
				 <#if (i == colNum) && (item_index+1)!=model.normalGridFormLinks?size>
				 	<#assign i = 0/>
					</tr></td>
					<tr><td>
				 </#if> 
				 <#if  (item.newLine && item_index > 0 )>
				 	<#assign i = 0/>
					</tr></td>
					<tr><td>
				 </#if>
		         ${item.l10n}     <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
		         <#assign i = i + 1/>
			</#list>
			<#list model.allOutGridFormLinks as item> 
				 <#if  (item.newLine && item_index >= 0 ) >
					</tr></td>
					<tr><td>
				 </#if>
		          ${item.htmlValue} &nbsp; 
			</#list>
			</td></tr>
		</tbody>
	</table>
</form>	
</div>	