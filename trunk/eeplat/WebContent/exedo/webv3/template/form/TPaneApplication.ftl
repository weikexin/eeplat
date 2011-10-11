<input type="button" style="${model.style?if_exists}"   id="${model.objUid}" value="&nbsp;${model.l10n}&nbsp;" class='ctlBtn' >
<script>
  <#if (model.inputConfig?exists && model.inputConfig=="direct")>
	  $('#${model.objUid}').bind('click',function(){
	 		window.open('${appPml}.pml?isApp=true');
	  });
  <#else>
	  $('#${model.objUid}').bind('click',function(){
	 		window.open('CRM/');
	  });
   </#if>	  
</script>