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

<div style="margin:0 15px 5px 5px">
<div id="${model.objUid}" > 
	   <#list data as ins>
	   		<h3><a href="#">
	   			<#assign lastItem=""/>
	   		    <#assign formsize=model.normalGridFormLinks?size />
	   			<#list model.normalGridFormLinks as item> 
	   				<#if item_index < (formsize-1)>
		             	<#if '${dataBind(ins,item)}' ==''> ${item.htmlValue} </#if> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            <#else>
		                <#if '${dataBind(ins,item)}' ==''> <#assign lastItem =item.htmlValue/> </#if>	
		            </#if> 
				</#list>
				</a>
	   		</h3>
			<div>
				${lastItem}
			</div>
	     </#list>
</div>
</div>
<#if (model.rowSize?exists && model.rowSize > 0 && resultSize > model.rowSize)>
		     	<div id="Pagination" style="width:100%"></div> 
</#if>

<script language="javascript">
			$( "#${model.objUid}" ).accordion({autoHeight: false,
			navigation: true,
			collapsible: true});	
		
		
		<#if (model.rowSize?exists && model.rowSize > 0 && pmlName?exists)>
			//pageSplit('${model.containerPane.name}','${pmlName}','${formName}');
			 $("#Pagination").pagination(${resultSize}, {  
	            callback: PageCallback,  
	            prev_text: '上一页',       //上一页按钮里text  
	            next_text: '下一页',       //下一页按钮里text  
	            items_per_page: ${rowSize},  //显示条数  
	            num_display_entries: 6,    //连续分页主体部分分页条目数  
	            current_page: ${pageNo}-1,   //当前页索引  
	            num_edge_entries: 2        //两侧首尾分页条目数  
	        });  
	        
	      	function PageCallback(index, containers){
	      	
				   var pmlUrl = getPmlUrl('${pmlName}',index+1,'${rowSize}');
				   if($('#${pmlName}').size() > 0){
				   	loadPml({'pml':pmlUrl,'target':'${pmlName}','formName':'${formName}'});
				   }
			}
		</#if>	
		


</script>
