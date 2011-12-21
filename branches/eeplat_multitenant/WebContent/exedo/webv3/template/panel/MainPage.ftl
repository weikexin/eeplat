<TD class=gRi vAlign=top> 
	<DIV class=mRight id ="mRight">${items_html}</div>
 </TD>
<script>
   <#if (model.resource)?exists >
		loadWorkbench("${model.resource.resourcePath}");
  </#if>	
</script>