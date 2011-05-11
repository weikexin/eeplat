<#--dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()>  
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>
			<#list model.normalGridFormLinks as item> 
   			<div data-role="fieldcontain">
   			   <#if model.controller.category.objUid == 'c_form_list' >
   			      <label for="${item.fullColID}" class="select">${item.l10n} :</label>
   			       <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if>
   			   <#elseif  (model.controller.l10n == 'form.DOInputRadio') || (model.controller.l10n == 'form.DOInputCheckBoxList') >
  						 <#if '${dataBind(data,item)}' ==''> ${item.htmlValue}	</#if>
     			 <#else>
	           <label for="${item.fullColID}">${item.l10n} :</label>
               <#if '${dataBind(data,item)}' ==''> ${item.htmlValue}  </#if>
	         </#if>  
        </div>   
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
