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
				<td class='title' colspan="${colNum*2}"> <img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> </td>
			</tr>
		</#if> 
		</thead>	
		
		<tbody>
			<#assign i = 0/>
			<#if (model.abstractGridFormLinks?size > 0)>
			<tr>
			<#list model.abstractGridFormLinks as item>
				<#--控制换行-->
			  	<#if (i>0 && (((i == colNum) ) ||item.newLine || (item_index > 0 && model.abstractGridFormLinks[item_index-1].newLine) ) )>
				 	<#assign i = 0/>
				 	</tr>
				 	<tr>
				</#if> 	
				
				<#if (item.nameColspan?exists && item.nameColspan == 0) >
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if>   <#if (item.newLine || (item_index+1)==model.abstractGridFormLinks?size  || model.abstractGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+2}"</#if>> <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if></td>
				<#else>                                                 
				    <td <#if item.noWrapLabel>nowrap='nowrap'</#if>> ${item.l10n}</td> 
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if>   <#if (item.newLine || (item_index+1)==model.abstractGridFormLinks?size  || model.abstractGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+1}"</#if>> <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if></td>
				</#if>     
			    <#assign i = i + 1/>    
			</#list>
			</tr>
			</#if>
			
			
			<#if (model.moreGridFormLinks?size > 0) >
				<#if (model.abstractGridFormLinks?size > 0)>   
				<tr>
					<td colspan="${colNum*2}" style="cursor:pointer" ><span style="cursor:pointer" onclick="toggleMore(this);"><b>更多信息</b></span></td>
				</tr>
				</#if>
			<#macro JudgeDisplay >
			    <#if (model.abstractGridFormLinks?size > 0)>
			    	style="display:none"
			    </#if>
			</#macro>
			
			<tr  <#compress><@JudgeDisplay/></#compress> >
			<#assign i = 0/>
			<#list model.moreGridFormLinks as item>
			     <#--控制换行-->
			  	<#if (i>0 && ((i == colNum) ||item.newLine || (item_index > 0 &&  model.moreGridFormLinks[item_index-1].newLine) ) )>
				 	<#assign i = 0/>
				 	</tr>
				 	<tr  <#compress><@JudgeDisplay/></#compress> >
				</#if> 	
				
				<#if (item.nameColspan?exists && item.nameColspan == 0) >
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if> <#if (item.newLine || (item_index+1)==model.moreGridFormLinks?size || model.moreGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+2}"</#if>><#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if></td>
				<#else>
				    <td <#if item.noWrapLabel>nowrap='nowrap'</#if>> ${item.l10n}</td>
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if> <#if (item.newLine || (item_index+1)==model.moreGridFormLinks?size || model.moreGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+1}"</#if>><#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if></td>
			    </#if>
			     
				<#assign i = i + 1/>
			</#list>
			</tr>	
	<#--下面是按钮部分-->
			<#if  (model.bottomOutGridFormLinks?size > 0)>
				<tr class="buttonMore" > <td  style="text-align:center;align:center"  colspan="${colNum*2}" >
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

