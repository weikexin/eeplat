<#include "TFormBase.ftl">
<a  id='${model.objUid}${model.data.uid}' data-role="button"  style="${model.style?if_exists}"  href='#' value="${model.l10n}">${model.l10n}</a>
<#if (paneModel?exists) >
<script  type="text/javascript">

  $('#${model.objUid}${model.data.uid}').bind('click',function(){

	    loadPml({
  			     <#if (model.linkPaneModel?exists && model.linkPaneModel.linkType==5)>
	   			 	'resourcePath':'${model.linkPaneModel.resource.resourcePath}',
	   			 </#if>
	   			 	'pml':'${paneModel.name}',
	   			 	'pmlWidth':'${paneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${paneModel.paneHeight?if_exists}',

	   			 'paras':'dataBus=setContext&contextKey=do_wfi_nodeinstance&contextValue=${model.data.uid}&contextKey=${busiBOName?if_exists}&contextValue=${instance_uid?if_exists}',
		   		 'title':'${paneModel.title}',
		         'formName':'${model.targetForms}'
		   		  <#if (model.targetPaneModel)?exists>	         
				,'target':'${model.targetPaneModel.name}'
				 </#if> });
  });
</script>
 </#if> 
