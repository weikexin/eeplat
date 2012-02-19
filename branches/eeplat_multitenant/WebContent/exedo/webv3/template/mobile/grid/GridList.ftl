<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  

  <ul data-role='listview' >
  
   <#if (data?size > 0)>
	  <#list data as ins>
	      <li>
	           <#if (controCols?size==0) > 
		        <a  data-direction="reverse" href="/${webmodule}/${linkPaneName}.pml?dataBus=setContext&contextKey=${ins.bo.name}&contextValue=${ins.uid}&contextNIUid=${(ins.map.contextniuid)?if_exists}&contextPIUid=${ins.map.contextpiuid?if_exists}">
		       </#if> 
				   <#list showCols as item> 
							${item.l10n}:&nbsp; <#if '${dataBind(ins,item)}' ==''> ${item.htmlValue}  </#if> <br/>
			      </#list>
	           <#if (controCols?size==0) > 
				</a>
			   </#if>	
			   <!--如果后面控制器大于2项，控制器每列3项-->
			   <#if (controCols?size > 2) > 
				   <div class="ui-grid-b"> 
					 <#list  controCols as item>
					     <#if ((item_index % 3)==0)>
						     <div class="ui-block-a">
						    <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
						     </div>
					     </#if>
					     <#if ((item_index % 3) ==1)>
						     <div class="ui-block-b">
						    <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
						     </div>
					     </#if>
					     <#if ((item_index % 3) ==2)>
						     <div class="ui-block-c">
						    <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
						     </div>
					     </#if>
					 </#list>  
					</div> 
			 <#else>	
			 	 <#list  controCols as item>
			  		 <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
			  	  </#list>	 
			 </#if>
			 	
		  </li> 
	   </#list><!--End data-->
	  <#if ((pageNo?exists) && (data?size > pageSize ) )>
       <li>
		    	第<span class='pageNo'>${pageNo}</span>页&nbsp;
		    	 每页<span class='rowSize'>${rowSize}</span>条&nbsp;
		    	 共<span class='pageSize'>${pageSize}</span>页 &nbsp; 
		    	共<span class="resultSize">${resultSize}</span>条
	   </li>
	 </#if> <!--End Page Split--> 
    <#else>
          <li>
            暂无数据！
          </li>
    </#if>
	</ul>  

<p/>	
   <#if ((pageNo?exists) && (data?size > pageSize ) )>
	<div data-role="controlgroup" data-type="horizontal" data-theme="e">
	    <a   class='firstPage' data-role="button" data-theme="a" data-inline="true">首</a>
		<a   class='prevPage' data-role="button" data-theme="a" data-icon="arrow-l" data-inline="true">前</a>
		<a   class='nextPage' data-role="button" data-theme="a" data-icon="arrow-r" data-inline="true">后</a>
		<a   class='lastPage' data-role="button" data-theme="a"  data-inline="true">尾</a>
    </div>
   </#if> 

   <fieldset class="ui-grid-a">
					<#list bottomForms as item> 
										<div class="ui-block-b">
										      <#if (item.controller.name!="com.exedosoft.plat.ui.jquery.form.TClose") >
				                      <#if '${dataBind(null,item)}' ==''> ${item.htmlValue}  </#if>
				                  </#if>    
				            </div>   
		 			</#list>
     </fieldset>
   
<#if (model.rowSize?exists && model.rowSize > 0 && pmlName?exists)>
  <script>
			pageSplit('${model.containerPane.name}','${pmlName}','${formName}');
	</script>		
</#if>	
	
