<TD class=gRi vAlign=top> 
	<DIV class=mRight id ="mRight"></div>
 </TD>
<script>
   <#if (model.resource)?exists >
		loadWorkbench("${model.resource.resourcePath}");
   <#else>
     	loadWorkbench("exedo/webv3/workbench/workbench.jsp");
  </#if>	
</script>