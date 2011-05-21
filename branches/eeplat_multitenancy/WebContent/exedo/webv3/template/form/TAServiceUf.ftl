<#include "TFormBase.ftl">
<#--参数通过本条记录的主键传递，如果是弹出窗口，并不关闭-->
<a  id='${model.objUid}${model.data.uid}' data-role="button"  style="${model.style?if_exists}"    href='#' value="${model.l10n}">${model.l10n}</a>
<script>

$('#${model.objUid}${model.data.uid}').bind('click',function(){
        callService({'btn':this,
        		 'callType':'uf',
		         'serviceUid':'${model.linkService.objUid}',
		         'paras':'dataBus=setContext&contextKey=${model.data.bo.name}&contextValue=${model.data.uid}&invokeButtonUid=${model.objUid}&contextNIUid=${(model.data.map.contextniuid)?if_exists}&contextPIUid=${model.data.map.contextpiuid?if_exists}'
		         <#if (model.linkPaneModel)?exists>
		         ,'pml':'${model.linkPaneModel.name}'
		         ,'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}'
	   			 ,'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}'
		         <#else>
		         ,'target':'${model.gridModel.containerPane.name}'	
		         ,'pml':'${model.gridModel.containerPane.name}'
		         </#if>
		         <#if (model.targetPaneModel)?exists>	         
		         ,'target':'${model.targetPaneModel.name}'
		         </#if>
		         <#if (model.echoJs)?exists>	         
		         ,'echoJs':'${model.echoJs}'
		         </#if>
		         });

});
</script>