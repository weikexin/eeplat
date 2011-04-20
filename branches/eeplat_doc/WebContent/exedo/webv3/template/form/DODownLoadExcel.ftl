<#include "TFormBase.ftl">
<button  type="button" id="${model.objUid}"  style="${model.style?if_exists}"  <#compress><@JudgeStyle model/></#compress> > ${model.l10n} </button>
<script>

  $('#${model.objUid}').bind('click',function(){
	    loadPml({
   			 	'pml':'${excelurl}',
		   		 'title':'${model.linkPaneModel.title}',
		   		 'formName':'a${model.gridModel.objUid}',
		   		 'target':'_opener_window'}
		);
  });
</script>