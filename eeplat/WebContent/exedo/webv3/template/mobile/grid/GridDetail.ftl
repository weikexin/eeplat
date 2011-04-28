<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>
			<#list model.normalGridFormLinks as item> 
   			<div data-role="fieldcontain">
   			   <#if model.controller.category.objUid == 'c_form_list' >
   			      <label for="${item.fullColID}" class="select">${item.l10n} :</label>
   			      ${item.htmlValue} 
   			   <#elseif  (model.controller.l10n == 'form.DOInputRadio') || (model.controller.l10n == 'form.DOInputCheckBoxList') >
  						${item.htmlValue} 	   
     			 <#else>
	           <label for="${item.fullColID}">${item.l10n} :</label>
             ${item.htmlValue} 
	         </#if>  
        </div>   
			</#list>
			
       <fieldset class="ui-grid-a">
					<#list model.allOutGridFormLinks as item> 
										<div class="ui-block-b">
				               ${item.htmlValue} 
				            </div>   
		 			</#list>
        </fieldset>

</form>	
