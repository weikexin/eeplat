<button  type="button" style="${model.style?if_exists}"   id='${model.objUid}' >&nbsp;${model.l10n}&nbsp;</button>
<script>
$('#${model.objUid}').bind('click',function(){
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