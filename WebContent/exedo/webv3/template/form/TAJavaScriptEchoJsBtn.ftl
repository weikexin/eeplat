<a  id='${model.objUid}${model.data.uid}' style="${model.style?if_exists}"    href='#' value="${model.l10n}">${model.l10n}</a>
<script>
 
$('#${model.objUid}${model.data.uid}').bind('click',function(){
	 <#if (model.echoJs)?exists>
		<#--提示性问题-->
		if(!eval(unescape(${model.echoJs}))){
    		return;
		}
	 </#if>
	

	   var dealBus = 'dataBus=setContext&contextKey=${model.data.bo.name}&contextValue=${model.data.uid}&invokeButtonUid=${model.objUid}&contextNIUid=${(model.data.map.contextniuid)?if_exists}&contextPIUid=${model.data.map.contextpiuid?if_exists}';
	   callPlatBus({'paras':dealBus});
	  <#if (model.doClickJs?exists)>	
	 	<#--添加按钮，用于防止重复提交-->  
      	eval("${model.doClickJs}('${model.data.uid}',this)");
      </#if>
  }
);
</script>