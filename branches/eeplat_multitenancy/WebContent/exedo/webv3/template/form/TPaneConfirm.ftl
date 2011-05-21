<#include "TFormBase.ftl">
<button  type="button" id="${model.objUid}"  style="${model.style?if_exists}"  <#compress><@JudgeStyle model/></#compress> > ${model.l10n} </button>
<script>


  //var dealBus = "dataBus=setUserContext&contextKey=stationuid&contextValue=" + resend_date;
//'paras':dealBus,

  $('#${model.objUid}').bind('click',function(){
  	
  		if(validate('${model.targetForms}')){
			loadPml({
		   		  <#if (model.linkPaneModel.linkType==5)>
	   			 	'pml':'${model.linkPaneModel.resource.resourcePath}',
	   			 <#else>
	   			 	'pml':'${model.linkPaneModel.name}',
	   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',
	   			 </#if>
		   		 'title':'${model.linkPaneModel.title}',
		   		 'formName':'a${model.gridModel.objUid}'
		   		  <#if (model.targetPaneModel)?exists>	         
				,'target':'${model.targetPaneModel.name}'
				 </#if> }
			);
		}
  
	    
  });
</script>