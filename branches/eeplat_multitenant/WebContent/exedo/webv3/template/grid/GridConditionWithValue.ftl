<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()>  
<#--开始输出空行-->
<#if model.numTopP?exists>
	<#if model.numTopP != 0>
		<#list 1..model.numTopP as x>  
			<br/>
   	 	</#list>
    </#if>
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
				<td class='title' colspan="${colNum*2}"> <img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> </td>
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
		         ${item.l10n}    <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
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
<script>
function toggleMore(obj){

	$(obj).parent().parent().nextAll(":not(.buttonMore)").toggle();
	var html = $(obj).text()=='更多信息' ? '<b>更少信息</b>' : '<b>更多信息</b>';
	$(obj).html(html);

}
 $('#g${model.objUid} tbody  tr').bind('click',function(){
			$('#g${model.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$(this).addClass("selected");
 });
</script>

