<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
  <ul data-role='listview' >
  <#list data as ins>
      <li>
	       <a href="${linkPaneName}.pml?dataBus=setContext&contextKey=${ins.bo.name}&contextValue=${ins.uid}&contextNIUid=${(ins.map.contextniuid)?if_exists}&contextPIUid=${ins.map.contextpiuid?if_exists}">
			   <#list model.normalGridFormLinks as item> 
				 <#if (item_index > 2)>
						  <#break/>
				 </#if>
				${item.l10n}:&nbsp; <#if '${dataBind(ins,item)}' ==''> ${item.htmlValue}  </#if> <br/>
	
		    </#list>
			  </a>   
	  </li> 
   </#list>
       <li>
		    	第<span class='pageNo'>${pageNo}</span>页&nbsp;
		    	 每页<span class='rowSize'>${rowSize}</span>条&nbsp;
		    	 共<span class='pageSize'>${pageSize}</span>页 &nbsp; 
		    	共<span class="resultSize">${resultSize}</span>条
	   </li>	
	</ul>  

<p/>	
	<div data-role="controlgroup" data-type="horizontal" data-theme="e">
	    <a   class='firstPage' data-role="button" data-theme="a" data-inline="true">首</a>
		  <a   class='prevPage' data-role="button" data-theme="a" data-icon="arrow-l" data-inline="true">前</a>
		  <a   class='nextPage' data-role="button" data-theme="a" data-icon="arrow-r" data-inline="true">后</a>
		   <a   class='lastPage' data-role="button" data-theme="a"  data-inline="true">尾</a>
		  
</div>
<#if (model.rowSize?exists && model.rowSize > 0 && pmlName?exists)>
  <script>
			pageSplit('${model.containerPane.name}','${pmlName}','${formName}');
	</script>		
</#if>	
	
