<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<#macro mobileFormat item dd>

     <#if (item.controller.category.objUid == 'c_form_button') >
       			<#if '${dataBind(dd,item)}' ==''> ${item.htmlValue}	</#if>
	 <#elseif ( (item.controller.category.objUid == 'c_form_suite') ||  (item.controller.name?lower_case?contains('valuestatictext'))  ||  (item.controller.name?lower_case?contains('dolabel')) )>

     <#else>
       		  <div data-role="fieldcontain">
   			   <#if ( (item.controller.category.objUid == 'c_form_list') ||  (item.controller.name?lower_case?contains('staticlist')) ) >
   			      <label for="${item.fullColID}" class="select">${item.l10n} :</label>
   			       <#if '${dataBind(dd,item)}' ==''> ${item.htmlValue} </#if>
   			   <#elseif ( (item.controller.name?lower_case?contains('checkbox') ) ) >
   			      	 <fieldset data-role="controlgroup">
   			      	     <legend>${item.l10n} :</legend>
  						 <#if '${dataBind(dd,item)}' ==''> ${item.htmlValue}	</#if>
  					</fiedlset>	 
  			   <#elseif ( (item.controller.name?lower_case?contains('radio') ) ) >		
  			        <fieldset data-role="controlgroup">
   			      	     <legend>${item.l10n} : </legend>
  						 <#if '${dataBind(dd,item)}' ==''> ${item.htmlValue}	</#if>
  					</fiedlset>	  
    		   <#else>
		           <label for="${item.fullColID}">${item.l10n} :</label>
	               <#if '${dataBind(dd,item)}' ==''> ${item.htmlValue}  </#if>
	         </#if>  
        	</div>	
     </#if>   	
        	
</#macro>

<#if buttongroup?exists>
 <div data-role="fieldcontain">
	<fieldset class="ui-grid-b">
	   <#list model.linkForms as item>
	 				 <#if ((item_index % 3)==0)>
							<div class="ui-block-a">
								 <#if  model.data?exists>
								   <@mobileFormat item model.data/>
								 <#else>
								   <@mobileFormat item emptydata/>
								 </#if>  
							 </div>
					 </#if>
					<#if ((item_index % 3) ==1)>
							<div class="ui-block-b">
								 <#if  model.data?exists>
								   <@mobileFormat item model.data/>
								 <#else>
								   <@mobileFormat item emptydata/>
								 </#if>  
							</div>
					</#if>
					<#if ((item_index % 3) ==2)>
							<div class="ui-block-c">
								 <#if  model.data?exists>
								   <@mobileFormat item model.data/>
								 <#else>
								   <@mobileFormat item emptydata/>
								 </#if>  
							</div>
					</#if>
	   </#list>
	</fieldset>		
 </div>	
<#else>
  <#list model.linkForms as item>
  		 <#if  model.data?exists>
				<@mobileFormat item model.data/>
		<#else>
				<@mobileFormat item emptydata/>
		</#if>  
  </#list>

</#if>
