<#include "TFormBase.ftl">
<button  type="button" id="${model.objUid}"  style="${model.style?if_exists}"  <#compress><@JudgeStyle model/></#compress> > ${model.l10n} </button>
<script>
  

  $('#${model.objUid}').bind('click',function(){
  
		    <#if model.gridModel.service?exists>

		  try{
			   var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
			   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}&invokeButtonUid=${model.objUid}" + "&contextValue=" + selectedValue;
			   callPlatBus({'paras':dealBus});
		   }catch(e){
			   
		   }
		  </#if>
	    loadPml({
   			 	'pml':'${link_url}',
		   		 'title':'${(model.linkPaneModel.title)?if_exists}',
		   		 'formName':'a${model.gridModel.objUid}',
		   		 'target':'_opener_window'}
		);
  });
</script>