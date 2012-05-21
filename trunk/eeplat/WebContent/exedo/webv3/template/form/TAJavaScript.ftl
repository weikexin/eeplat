<a  id='${model.objUid}${model.data.uid}' data-role="button" style="${model.style?if_exists}"    href='#' value="${model.l10n}">${model.l10n}</a>
<script>
 
$('#${model.objUid}${model.data.uid}').bind('click',function(){
	   var dealBus = 'dataBus=setContext&contextKey=${model.data.bo.name}&contextValue=${model.data.uid}&invokeButtonUid=${model.objUid}&contextNIUid=${(model.data.map.contextniuid)?if_exists}&contextPIUid=${model.data.map.contextpiuid?if_exists}';
	   callPlatBus({'paras':dealBus});
	  <#if (model.doClickJs?exists)>	  
      	eval("${model.doClickJs}");
      </#if>
      
       <#if ((model.inputConfig)?exists && model.inputConfig=='closeTab')>
	       <#if (model.gridModel.containerPane.name)?exists>
				var tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.name}'] .btn";
		  		tabCloseWindow(tabBtnSelector);
		  </#if>
		</#if>  
	  
	   <#if ( (model.linkPaneModel)?exists && (model.targetPaneModel)?exists )>
  	  	 $("#${model.targetPaneModel.name}").empty().load("${model.linkPaneModel.name}.pml");
	   </#if>	
      
  }
);
</script>