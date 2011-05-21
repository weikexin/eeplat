<button  type="button" style="${model.style?if_exists}"   id='${model.objUid}' >&nbsp;${model.l10n}&nbsp;</button>
<script>
 
$('#${model.objUid}').bind('click',function(){
	 <#if (model.echoJs)?exists>
		<#--提示性问题-->
		if(!eval(unescape(${model.echoJs}))){
    		return;
		}
	 </#if>
	
	  <#if (model.doClickJs?exists)>	
	 	<#--添加按钮，用于防止重复提交-->  
      	eval("${model.doClickJs}(this)");
      </#if>
  }
);
</script>