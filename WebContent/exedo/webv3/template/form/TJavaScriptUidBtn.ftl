<button  type="button" style="${model.style?if_exists}"   id='${model.objUid}' >&nbsp;${model.l10n}&nbsp;</button>
<script>
$('#${model.objUid}').bind('click',function(){
	  <#if (model.doClickJs?exists)>	  
      	eval("${model.doClickJs}(<#if (model.data?exists)>'$(model.data.uid)'<#else>null</#if>,this)");
      </#if>
  }
);
</script>