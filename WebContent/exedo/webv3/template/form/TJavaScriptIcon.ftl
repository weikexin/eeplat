&nbsp;&nbsp;<input type="image"  style="${model.style?if_exists}"  
	  <#if (model.inputConfig?exists)>	  
      	title='${model.inputConfig}' 
      </#if> 
      <#if (model.inputConstraint?exists)>	  
      	alt='${model.inputConstraint}'
      </#if>
      <#if (model.defaultValue?exists)>	  
      	src='${model.defaultValue}'
      </#if>
      id='${model.objUid}' />&nbsp;&nbsp;
<script>
$('#${model.objUid}').bind('click',function(){
	  <#if (model.doClickJs?exists)>	  
      	eval("${model.doClickJs}");
      </#if>
  }
);
</script>