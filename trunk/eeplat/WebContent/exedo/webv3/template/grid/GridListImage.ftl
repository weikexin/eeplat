<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<#--开始输出空行-->
 <#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>

<div width="100%" height="100%" >

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
		<thead>
		  <tr>
			<#--隐藏列，数据部分输出记录的主键-->
			<th  style='display:none' class="{sorter: false}" ></th>
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
            <th colspan='${datarowSize}' id='${item.colName}'  <#if item.width?exists> width='${item.width}'</#if>  <#if item.noWrapLabel>nowrap='nowrap'</#if> <#compress><@JudgeAlign item/> </#compress>> </th> 
		</#list>
		</tr>
		</thead>
		<#--Table Header部分输出完毕-->
		<tbody>
		<#if (data?size > 0)>
			<tr class="click">
		</#if>
		<#assign x=0 />
		<#assign rowSize = '${datarowSize}' />
	   <#list data as ins>
				<td id='${ins_index}${ins.uid}' value='${ins.uid}' ><center><img id='${ins.uid}' class="imgzoom" src="/yiyi/upload/${ins.map.file_path}" width="300" height="300" /></center></td>
				<#if '${fm}' != "">
				<script>
						$('#${ins.uid}').bind('dblclick',function(){
							   var selectedValue = this.id;
							   var dealBus = "dataBus=setContext&contextKey=${fm.gridModel.service.bo.name}" + "&contextValue=" + selectedValue;;
								$(this).addClass("selected");
								loadPml({
					   			 'paras':dealBus, 
					   			 <#if ('${fm.linkPaneModel.linkType}'=='5')>
					   			 	'pml':'${fm.linkPaneModel.resource.resourcePath}',
					   			 <#else>
					   			 	'pml':'${fm.linkPaneModel.name}',
					   			 	'pmlWidth':'${fm.linkPaneModel.paneWidth?if_exists}',
					   			 	'pmlHeight':'${fm.linkPaneModel.paneHeight?if_exists}',
					   			 	
					   			 </#if>
					       		 'title':'${fm.linkPaneModel.title}'
					      		  <#if '${fm.targetPaneModel}'?exists>	         
					      				,'target':'${fm.targetPaneModel.name}'
							 </#if> }
					           );
						});
						$('#${ins_index}${ins.uid}').bind('click',function(){
						        $('#g${model.objUid} tbody  td.selected').removeAttr("style");
						        $('#g${model.objUid} tbody  td.selected').removeClass("selected");
						    	$(this).addClass("selected");
							    $(this).css("background","yellow")
				
						});
										
				</script>
				</#if>
			    <#assign x = x + 1 >
			   <#-- 判断当前行的列数是否已经等于定义的rowSize,如果不等于，并且还有未显示的数据，则增加tr-->
			<#if (x >= (rowSize?number)) && (ins_index + 1 != data?size) >
				</tr>
				<tr>
				<#assign x=0 />
			</#if>
			<script>
			<#--alert("${ins.map.file_path}");-->
			</script>
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
		    <td  colspan="${datarowSize}" >
		   &nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/first.png'  class='firstPage' title="第一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/prev.png'  class='prevPage'  title="上一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/next.png'  class='nextPage' title="下一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/last.png'  class='lastPage' title="最后一页"/>&nbsp;&nbsp;
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
				 ${item.htmlValue} &nbsp; 
				</#list>
				</td></tr>
		</table>
	</#if>
		
</form>	


</div>	

<script language="javascript">
          
	      if( ${data?size} > 0 ){
	           //最后一行用TD填充到顶格 。
		      for (i = ${datarowSize} - ${x}; i > 0 ; i-- ){
		        $('#g${model.objUid} tbody  tr:last').append("<td/>");
		      }
		       //固定表头，右侧增加滚动条
	         new ScrollableTable(document.getElementById('g${model.objUid}'), 700);  
	      }
	      
 

</script>
