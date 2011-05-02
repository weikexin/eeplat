<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
  <ul data-role='listview' >
  <#list data as ins>
      <li>
		<#list model.normalGridFormLinks as item> 
			 <#if (item_index > 2)>
					  <#break/>
			 </#if>
			<p><strong>${item.l10n}:</strong>  <#if '${dataBind(ins,item)}' ==''> ${item.htmlValue}  </#if> </p>

	    </#list>
	 </li> 
   </#list>
       <li>
		    	第<span class='pageNo'>${pageNo}</span>页&nbsp;
		    	 每页<span class='rowSize'>${rowSize}</span>条&nbsp;
		    	 共<span class='pageSize'>${pageSize}</span>页 &nbsp; 
		    	共<span class="resultSize">${resultSize}</span>条
	   </li>	
	</ul>  

    	

	
	<div data-role="controlgroup" data-type="horizontal" data-theme="e">
	    <a   class='firstPage' data-role="button" data-theme="a" data-inline="true">首</a>
		  <a   class='prevPage' data-role="button" data-theme="a" data-icon="arrow-l" data-inline="true">前</a>
		  <a   class='nextPage' data-role="button" data-theme="a" data-icon="arrow-r" data-inline="true">后</a>
		   <a   class='lastPage' data-role="button" data-theme="a"  data-inline="true">尾</a>
		  
	</div>	  
	
