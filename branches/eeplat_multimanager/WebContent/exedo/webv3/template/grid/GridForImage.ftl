<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/> 
<#list data as ins>
	<#list model.normalGridFormLinks as item> 
		${dataBind(ins,item)}${item.htmlValue}
	</#list>
</#list>