<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()>  

<#macro JudgeStyle model>
	<#if model.linkPaneModel.cssStyle?exists>
		class='${model.style}'
	<#elseif (model.isOutGridAction == 2)>
		class='role-setup'
	<#else>
	    class='ctlBtn'	
	</#if>
</#macro>
<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>

<div width="100%" height="100%">
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

	<table  class="tablesorter" id='g${model.objUid}'  border="0" cellpadding="1" cellspacing="1" >
		<thead>
		  <tr>
			<#--隐藏列，数据部分输出记录的主键-->
			<th  style='display:none' class="{sorter: false}" ></th>
			<#if model.NO><#--是否有数字序列-->
				<th  align='center' width='5%' class="{sorter: 'digit'}" nowrap='nowrap'>序号</th>
			</#if>
		<#if model.checkBox><#--定义CheckBox-->
			<th style="align: center"  width='5%' nowrap='nowrap' class="{sorter: false}">
				全选<input type ='checkbox'   name='checkinstanceheader' 
				id="check_${model.objUid}"/>
			</th>
		</#if>
		<#--定义宏 判断输出什么类型的align-->
		<#macro JudgeAlign item>
		    <#if item.align?exists>
		    	align='${item.align}'
		    <#else>
		        align='center' 
		    </#if>
		</#macro>
		<#--输出其它的头标题-->
		<#list model.normalGridFormLinks as item>
            <th id='${item.colName}'  <#if item.width?exists> width='${item.width}'</#if>  <#if (item.noWrapLabel || item_index==0)>nowrap='nowrap'</#if> <#compress><@JudgeAlign item/> </#compress>>${item.l10n} </th> 
		</#list>
		</tr>
		</thead>
		<#--Table Header部分输出完毕-->
		<tbody>
	   <#list data as ins>
			<tr  id='${ins.uid}' value='${ins.uid}' <#if ins.map.child_of_class?exists>class='${ins.map.child_of_class}'</#if> >
			<#if model.NO><#--是否有数字序列-->
				<td align='center'>#{ins_index+1}</td>
			</#if>
			<#if model.checkBox><#--定义CheckBox-->
				<td style="align: center" >&nbsp;&nbsp;<input type ='checkbox' value='${ins.uid}' class='list_check'  name='checkinstance'/> <input type ='hidden' value='${ins.uid}' name='checkinstance_hidden'/> </td>
			<#elseif model.radio>
				<td align='center'><input type ='radio' value='${ins.uid}'  name='checkinstance'/>   <input type ='hidden' value='${ins.uid}' name='checkinstance_hidden'/>  </td>
			</#if>
			<#--输出具体值-->
			<#list model.normalGridFormLinks as item>
		            <td  <#if (item.noWrapValue || item_index==0)>nowrap='nowrap'</#if> <#compress> <@JudgeAlign item/></#compress> > <#if '${dataBind(ins,item)}' ==''> ${item.htmlValue} </#if> </td> 
			</#list>
			</tr>
	     </#list>
		</tbody>
	</table>
</form>	
</div>	

<script language="javascript">


		
		$('#check_${model.objUid}').bind('click',function(){
			$('#g${model.objUid} .list_check').attr('checked',$('#check_${model.objUid}').attr("checked"));
		});
		
		$('#g${model.objUid} .list_check').bind('click',function(e){
			if(!$(this).attr('checked')){
				$(this).parent().parent().removeClass("selected");
				if($('#g${model.objUid} .selected').size()==0){
					$('#g${model.objUid} .list_check:checked:first').parent().parent().addClass("selected");				
				}
				e.stopPropagation();
			}
		});

		$('#g${model.objUid} tbody  tr').bind('click',function(){
			$('#g${model.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$("#g${model.objUid} tbody tr:even").addClass("zebra");
			$(this).removeClass("zebra");
			$(this).addClass("selected");
			$(this).find(".list_check").attr("checked",true);
		});
		<#if model.subscribeAll> 
		$('#g${model.objUid} tbody  tr').bind('dblclick',function(){
			var selectedValue = $(this).attr('value');
			var dealBus = "&dataBus=setContext&contextKey=${model.service.bo.name}" + "&contextValue=" + selectedValue;
			$(".toolbar .selected").removeClass("selected");
			$(this).addClass("selected");
		    <#if ((model.service.bo.mainPaneModel.fullCorrHref)?exists) >
				popupDialog('${model.service.bo.mainPaneModel.name}','${model.service.bo.mainPaneModel.title}','${model.service.bo.mainPaneModel.fullCorrHref}' + dealBus,'${model.service.bo.mainPaneModel.paneWidth?if_exists}','${model.service.bo.mainPaneModel.paneHeight?if_exists}')
			</#if> 
		});
		</#if>
		$('#g${model.objUid} tbody  tr:odd').bind('mouseover',function(){
			$(this).addClass("mover");
		}).bind('mouseout',function(){
			$('#g${model.objUid} tbody  tr').removeClass("mover");
		});
		
		$('#g${model.objUid} tbody  tr:even').bind('mouseover',function(){
			$(this).addClass("mover");
			$(this).removeClass("zebra");
		}).bind('mouseout',function(){
			$('#g${model.objUid} tbody  tr').removeClass("mover");
			if(!$(this).hasClass("selected")){
				$(this).addClass("zebra");
			}
		});

		$("#g${model.objUid} tbody tr:even").addClass("zebra");
		//转化为treeTable
		$('#g${model.objUid}').treeTable({'clickableNodeNames':true,'indent':35,'initialState':'expanded'})
</script>
