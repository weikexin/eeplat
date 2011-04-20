<#include "TFormBase.ftl">
<a  id='${model.objUid}${model.data.uid}'  style="${model.style?if_exists}"  href='#' value="${model.l10n}">${model.l10n}</a>
<#if (paneModel?exists) >
<script>

  $('#${model.objUid}${model.data.uid}').bind('click',function(){
	    loadPml({
		   		  <#if ((paneModel.linkType?exists) && (paneModel.linkType==5))>
	   			 	'pml':'${paneModel.resource.resourcePath}',
	   			 <#else>
	   			 	'pml':'${paneModel.name}',
	   			 	'pmlWidth':'${paneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${paneModel.paneHeight?if_exists}',
	   			 </#if>
	   			 'paras':'dataBus=setContext&contextKey=do_pt_processtemplate&contextValue=${model.data.uid}',
		   		 'title':'${paneModel.title}',
		   		 'formName':'a${model.gridModel.objUid}'
		   		  <#if (model.targetPaneModel)?exists>	         
				,'target':'${model.targetPaneModel.name}'
				 </#if> });
  });
</script>
 </#if> 
