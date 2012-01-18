<input type="button"  style="${model.style?if_exists}"  id='${model.objUid}' value="&nbsp;${model.l10n}&nbsp;">
<script>
 
$('#${model.objUid}').bind('click',function(){
	  if($('#g${model.gridModel.objUid} tbody  tr.selected').length == 0){
		    	alert("请选择一条记录！");
	         	return;
       }
       var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
	   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}&invokeButtonUid=${model.objUid}" + "&contextValue=" + selectedValue;
	   callPlatBus({'paras':dealBus});

	  <#if (model.doClickJs?exists)>	  
      	eval("${model.doClickJs}");
      </#if>
  }
);
</script>