<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<fieldset class="ui-grid-a">
   <#list model.linkForms as item>
     <div class="ui-block-b">
      <#if '${dataBind(model.data,item)}' ==''>
        <#if item.newLine><br/></#if> ${item.htmlValue} &nbsp;
      </#if>  
      </div>
   </#list>
</fieldset>