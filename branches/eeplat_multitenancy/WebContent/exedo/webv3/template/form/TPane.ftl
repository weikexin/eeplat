<#include "TFormBase.ftl">
<button  type="button" id="${model.objUid}"  style="${model.style?if_exists}"  <#compress><@JudgeStyle model/></#compress> > ${model.l10n} </button>
<script>


  //var dealBus = "dataBus=setUserContext&contextKey=stationuid&contextValue=" + resend_date;
//'paras':dealBus,

  $('#${model.objUid}').bind('click',function(){
	    loadPml({

	   			 	'pml':'${model.linkPaneModel.name}',
	   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',

		   		 'title':'${model.linkPaneModel.title}',
		   		 'formName':'a${model.gridModel.objUid}'
		   		  <#if (model.targetPaneModel)?exists>	         
				,'target':'${model.targetPaneModel.name}'
				 </#if> }
		);
  });
</script>