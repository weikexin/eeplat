<#include "TFormBase.ftl">
<a  id='${model.objUid}${model.data.uid}'  style="${model.style?if_exists}"  href='#' value="${model.l10n}">${model.l10n}</a>
<#if (model.linkPaneModel?exists) >
<script>

  $('#${model.objUid}${model.data.uid}').bind('click',function(){
	    loadPml({
		   		  <#if ((model.linkPaneModel.linkType?exists) && (model.linkPaneModel.linkType==5))>
	   			 	'pml':'${model.linkPaneModel.resource.resourcePath}',
	   			 <#else>
	   			 	'pml':'${model.linkPaneModel.name}',
	   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',
	   			 </#if>
	   			 'paras':'dataBus=setContext&contextKey=${model.data.bo.name}&contextValue=${model.data.uid}&contextNIUid=${(model.data.map.contextniuid)?if_exists}&contextPIUid=${model.data.map.contextpiuid?if_exists}',
		   		 'title':'${model.linkPaneModel.title}',
		   		 'formName':'a${model.gridModel.objUid}'
		   		  <#if (model.targetPaneModel)?exists>	         
				,'target':'${model.targetPaneModel.name}'
				 </#if> }
		);
  });
</script>
 </#if> 
