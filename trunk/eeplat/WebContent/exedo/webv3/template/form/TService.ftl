<#include "TFormBase.ftl">
<#--参数通过formName传递-->
<button type="button"  style="${model.style?if_exists}"  id='${model.objUid}'  <#compress><@JudgeStyle model/></#compress> >${model.l10n}</button>
<script>

 function fnCB${model.objUid}(){
  	<#if (!((model.inputConstraint)?exists && model.inputConstraint=='noCloseOpener'))>
	    <#if (model.gridModel.containerPane.name)?exists>
		try{
			if($('#F' + '${model.gridModel.containerPane.name}').size()>0){
	  			$('#F' + '${model.gridModel.containerPane.name}').jqmHide();
	  		}else{
	  			$('#' + '${model.gridModel.containerPane.name}').parents(".jqmDialog").jqmHide();
	  			<#if (model.gridModel.containerPane.parent)?exists>	
	  				$('#F' + '${model.gridModel.containerPane.parent.name}').jqmHide();
	  			</#if>
		  	}  	
	  	}catch(e){
	  	}	
	  	</#if>
	 </#if>
	 
  	 <#if ((model.inputConfig)?exists && model.inputConfig=='closeTab')>
	 	<#if (model.gridModel.containerPane.name)?exists>
			var tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.name}'] .btn";
	  		tabCloseWindow(tabBtnSelector);
	  	</#if>
	 </#if> 	
	  
  	 <#if ((model.inputConfig)?exists && model.inputConfig=='loadParent')>
  		reloadTree();     
  	</#if>
  	
  	
   
 }

$('#${model.objUid}').bind('click',function(){
        callService({'btn':this,
		         'serviceUid':'${model.linkService.objUid}',
		         'callback':fnCB${model.objUid},
		         'paras':'invokeButtonUid=${model.objUid}',
		         'formName':'${model.targetForms}'
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
		         
  }
);
</script>