
<div width="100%" height="100%">
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' width="100%" align="center">
		<thead>
		<#if model.caption?exists>
			<#assign icon = (model.icon)?default("MyRightArrow.jpg")/>
			<tr> 
				<td class='title'> <img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> </td>
			</tr>
		</#if> 
		</thead>	
		<tbody>
			<tr align="center"><td align="center">
			<#assign colNum = (model.colNum)?default(3)/>
			<#assign i = 0/>
			<#list model.allOutGridFormLinks as item> 
				 <#if  (item.newLine && item_index >= 0 ) >
					</tr></td>
					<tr><td>
				 </#if>
		          ${item.htmlValue} &nbsp; 
			</#list>
			<br>
			</td></tr>
		</tbody>
	</table>
</form>	
</div>	