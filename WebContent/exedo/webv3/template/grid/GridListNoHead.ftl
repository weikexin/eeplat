<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  


<div width="100%" height="100%">
<#if model.caption?exists>
	 <div style="margin:0px 0px 0px 5px;border-top:1px solid #8db2e3;border-left:1px solid #8db2e3;border-right:1px solid #8db2e3;align:left;text-align:left;vertical-align: middle;background-color:#e6EEEE;width:98%;height:22px" >
			
							<#assign icon = (model.icon)?default("MyRightArrow.jpg")/>
		 					<img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> 
		
	 </div>
</#if> 		
<#if (model.topOutGridFormLinks?size > 0) > 
	<DIV class="toolbar" style="BORDER-RIGHT: #8db2e3 1px solid; BORDER-TOP: #8db2e3 1px solid; BORDER-LEFT: #8db2e3 1px solid; BORDER-BOTTOM: #8db2e3 1px solid">
		<DIV align="left"><!--布局用-->
			<TABLE>
				<TBODY>
					<TR>
						<TD style="WIDTH: 2px"></TD><!--左缩进-->
						<#list model.topOutGridFormLinks as item>
							<TD>
								<TABLE  cellSpacing='0' cellPadding='0'>
									<TBODY>
										<TR>
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
	
		
		<#--定义宏 判断输出什么类型的align-->
		<#macro JudgeAlign item>
		 
		</#macro>
		
		
		<#--Table Header部分输出完毕-->
		<tbody>
	   <#list data as ins>
			<tr  value='${ins.uid?if_exists}'  title='${ins.name?if_exists}'>
			<#if model.NO><#--是否有数字序列-->
				<td align='center'>#{ins_index+1}</td>
			</#if>
			<#if model.checkBox><#--定义CheckBox-->
				<td width="40px;">&nbsp;&nbsp;<input type ='checkbox' value='${ins.uid}' class='list_check'  name='checkinstance'/> <input type ='hidden' value='${ins.uid}' name='checkinstance_hidden'/> </td>
			<#elseif model.radio>
				<td align='center'><input type ='radio' value='${ins.uid}'  name='checkinstance'/>   <input type ='hidden' value='${ins.uid}' name='checkinstance_hidden'/>  </td>
			</#if>
			<#--输出其它的头标题 ins:{'l10n':'中国','name':'china','location':'a'}    item:{'key':'l10n'}--> 
			<#list model.normalGridFormLinks as item>
				<#if item_index == 0 || item_index == 1>
		            <td width="150px;" <#if item.noWrapValue>nowrap='nowrap'</#if> <#compress> <@JudgeAlign item/></#compress> >   <#if '${dataBind(ins,item)}' ==''>${item.htmlValue}</#if> </td> 
				<#else>
					<td <#if item.noWrapValue>nowrap='nowrap'</#if> <#compress> <@JudgeAlign item/></#compress> >   <#if '${dataBind(ins,item)}' ==''>${item.htmlValue}</#if> </td> 
				</#if>
			</#list>
			</tr>
	     </#list>
	     
	     <#if statistics?exists>
	      	<tr>
		      	<#if model.NO><#--是否有数字序列-->
					<td align='center'>统计</td>
				</#if>
				<td colspan="${model.normalGridFormLinks?size + 1}"> ${statistics_details?if_exists}   </td>
	        </tr>
	     </#if>
	     
	     
		</tbody>
		
		<#if (model.rowSize?exists && model.rowSize > 0)>
		<tfoot>
		  <tr>
		   <#assign cols =  (model.normalGridFormLinks?size+2)/>
		    <td  colspan="${cols}" >
		   &nbsp;&nbsp;
		    <img src='${contextPath}images/grid/first.png'  class='firstPage' title="第一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}images/grid/prev.png'  class='prevPage'  title="上一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}images/grid/next.png'  class='nextPage' title="下一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}images/grid/last.png'  class='lastPage' title="最后一页"/>&nbsp;&nbsp;
		    	第<span class='pageNo'>${pageNo}</span>页&nbsp;
		    	 每页<span class='rowSize'>${rowSize}</span>条&nbsp;
		    	 共<span class='pageSize'>${pageSize}</span>页 &nbsp; 
		    	共<span class="resultSize">${resultSize}</span>条记录
		      </td>
		  </tr>
		 </tfoot> 
	   </#if>
	   		
		
	</table>
	
	<#if (model.bottomOutGridFormLinks?size > 0) > 
	    <table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="text-align:center" >
				<tr><td align="center" style="text-align:center">
				<#list model.bottomOutGridFormLinks as item> 
				   <#if item.newLine><br/></#if> <#if '${dataBind(null,item)}' ==''>  ${item.htmlValue} </#if> &nbsp; 
				</#list>
				</td></tr>
		</table>
	</#if>
		
</form>	


</div>	

<script language="javascript">

		
		$('#check_${model.objUid}').bind('click',function(){
			$('#g${model.objUid} .list_check').attr('checked',$('#check_${model.objUid}').attr("checked"));
			///同时把第一条记录selected
			if($('#check_${model.objUid}').attr("checked")){
				$('#g${model.objUid} tbody  tr').eq(0).addClass("selected");
			}else{
				$('#g${model.objUid} tbody  tr').eq(0).removeClass("selected");
			}
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
			$(this).addClass("selected");
//			$(this).find(".list_check").attr("checked",true);//点击就选中，容易出现问题
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
		$('#g${model.objUid} tbody  tr').bind('mouseover',function(){
			$(this).addClass("mover");
		}).bind('mouseout',function(){
			$('#g${model.objUid} tbody  tr').removeClass("mover");
		});
		<#if (model.rowSize?exists && model.rowSize > 0 && pmlName?exists)>
			pageSplit('${model.containerPane.name}','${pmlName}','${formName}');
		</#if>	

</script>
