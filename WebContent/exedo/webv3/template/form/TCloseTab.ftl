<input type="button"  style="${model.style?if_exists}"  id='${model.objUid}' value="&nbsp;${model.l10n}&nbsp;" class='ctlBtn'>

<script>

$('#${model.objUid}').bind('click',function(){
	<#if (model.gridModel.containerPane.name)?exists>
		var tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.name}'] .btn";
  		tabCloseWindow(tabBtnSelector);
  	</#if>
  }
);
</script>