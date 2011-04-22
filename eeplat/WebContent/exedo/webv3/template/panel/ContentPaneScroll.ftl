<#if model.template?exists>
<script>
	eval("${model.template}");
</script>
</#if>
<div id="${model.name}"  style="overflow:auto;width:100%;height:100%;">
 	${items_html}
</div>
<#if model.templateClass?exists>
<script>
	eval("${model.templateClass}");
</script>	
</#if>