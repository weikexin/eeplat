<a  id='${model.objUid}' data-role="button" style="${model.style?if_exists}"    href='#' value="${model.l10n}">${model.l10n}</a>
<script>
$('#${model.objUid}').bind('click',function(){
	  <#if (model.doClickJs?exists)>	  
      	eval("${model.doClickJs}");
      </#if>
  }
);
</script>