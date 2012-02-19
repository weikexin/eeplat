<#--dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()>  
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>
			<#list formlinks as item> 
			 <#if ((item.controller.category.objUid == 'c_form_button') || (item.controller.category.objUid == 'c_form_suite') ||  (item.controller.name?lower_case?contains('valuestatictext'))  ||  (item.controller.name?lower_case?contains('dolabel')) )>
  						<#if '${dataBind(data,item)}' ==''> ${item.htmlValue}	</#if>
		        <#else>
		        	 <div data-role="fieldcontain">
		   			   <#if ( (item.controller.category.objUid == 'c_form_list') ||  (item.controller.name?lower_case?contains('staticlist')) ) >
		   			      <label for="${item.fullColID}" class="select">${item.l10n} :</label>
		   			       <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if>
		   			   <#elseif ( (item.controller.name?lower_case?contains('checkbox') ) ) >
		   			      	 <fieldset data-role="controlgroup">
		   			      	     <legend>${item.l10n} : </legend>
		  						 <#if '${dataBind(data,item)}' ==''> ${item.htmlValue}	</#if>
		  					</fiedlset>	 
		  			   <#elseif ( (item.controller.name?lower_case?contains('radio') ) ) >		
		  			        <fieldset data-role="controlgroup">
		   			      	     <legend>${item.l10n} : </legend>
		  						 <#if '${dataBind(data,item)}' ==''> ${item.htmlValue}	</#if>
		  					</fiedlset>	  
		  						   
		    		   <#else>
				           <label for="${item.fullColID}">${item.l10n} : </label>
			               <#if '${dataBind(data,item)}' ==''> ${item.htmlValue}  </#if>
			         </#if>  
		        	</div> 
	        	</#if>
			</#list>
			
       <fieldset class="ui-grid-a">
					<#list model.allOutGridFormLinks as item> 
							<div class="ui-block-b">
								<#if (item.controller.name!="com.exedosoft.plat.ui.jquery.form.TClose") >
				                      <#if '${dataBind(data,item)}' ==''> ${item.htmlValue}  </#if>
				                  </#if>    
				            </div>   
		 			</#list>
        </fieldset>

</form>	
