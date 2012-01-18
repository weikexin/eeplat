<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>
<div width="100%" height="100%">
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
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
			<#list model.normalGridFormLinks as item> 
				 <#if  item.newLine>
			</tr></td>
			<tr><td>
				 </#if>
		         ${item.l10n}    ${item.htmlValue} &nbsp; 
			</#list>
			<#list model.allOutGridFormLinks as item> 
				 <#if  item.newLine>
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