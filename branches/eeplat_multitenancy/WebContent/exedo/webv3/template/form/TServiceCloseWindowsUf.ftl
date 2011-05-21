<#include "TFormBase.ftl">
<#--参数通过formName传�1�7�1�7-->
<button type="button" style="${model.style?if_exists}"   id='${model.objUid}' <#compress><@JudgeStyle model/></#compress> >${model.l10n}</button>
<script>
 function fnCB${model.objUid}(){
  	<#if (!((model.inputConstraint)?exists && model.inputConstraint=='noCloseOpener'))>
	    <#if (model.gridModel.containerPane.name)?exists>
	       try{
			if($('#F' + '${model.gridModel.containerPane.name}').size()>0){
	  			$('#F' + '${model.gridModel.containerPane.name}').jqmHide();
	  		}else{
	  			$('#' + '${model.gridModel.containerPane.name}').parents(".jqmDialog").jqmHide();
		  	}  	
	  	}catch(e){
	  	}
	  	
		try{
			var tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.name}'] .btn";
			tabCloseWindow(tabBtnSelector);
			 <#if (model.gridModel.containerPane.parent)?exists>
				tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.parent.name}'] .btn";
				tabCloseWindow(tabBtnSelector);
				<#if (model.gridModel.containerPane.parent.parent)?exists>
				tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.parent.parent.name}'] .btn";
				tabCloseWindow(tabBtnSelector);
			</#if>
			</#if>
			
		}catch(e){	  	

		}
	  	</#if>
	 </#if>
	  
  	 <#if ((model.inputConfig)?exists && model.inputConfig=='loadParent')>
  		reloadTree();     
  	</#if>
   
 }

$('#${model.objUid}').bind('click',function(){
        callService({'btn':this,
        		 'callType':'uf',
        		 'serviceUid':'${model.linkService.objUid}',
        		 'paras':'invokeButtonUid=${model.objUid}',
		         'callback':fnCB${model.objUid},
		         'formName':'${model.targetForms}'
		         <#if (model.linkPaneModel)?exists>
		         ,'pml':'${model.linkPaneModel.fullCorrHref}'
		         ,'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}'
	   			 ,'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}'
		         <#else>
		         ,'target':'${model.gridModel.containerPane.name}'	
		         ,'pml':'${model.gridModel.containerPane.fullCorrHref}'
		         </#if>
		         <#if (model.targetPaneModel)?exists>	         
		         ,'target':'${model.targetPaneModel.name}'
		         </#if>
		         <#if (model.echoJs)?exists>	         
		         ,'echoJs':'${model.echoJs}'
		         </#if>
		         });
  }
);
</script>