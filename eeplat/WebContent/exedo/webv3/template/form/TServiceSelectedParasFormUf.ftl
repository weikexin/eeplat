<#include "TFormBase.ftl">
<button style="${model.style?if_exists}"   type="button" id='${model.objUid}' <#compress><@JudgeStyle model/></#compress>>${model.l10n}</button>
 <script>
 
  function fnCB${model.objUid}(){
  	<#if (!((model.inputConstraint)?exists && model.inputConstraint=='noCloseOpener'))>
	    <#if (model.gridModel.containerPane.name)?exists>

		try{
			if($('#F' + '${model.gridModel.containerPane.name}').size()>0){
	  			$('#F' + '${model.gridModel.containerPane.name}').dialog('close');
	  		}else{
	  			$('#' + '${model.gridModel.containerPane.name}').parents(".ui-dialog-content").dialog('close');
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

	  if($('#g${model.gridModel.objUid} tbody  tr.selected').length == 0){
		    	alert("请选择一条记录！");
	         	return;
       }
	   var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
	   var dealBus = "invokeButtonUid=${model.objUid}&contextValue=" + selectedValue;
	   $(".toolbar .selected").removeClass("selected");
	   $(this).addClass("selected");
			callService({'btn':this,
				'callType':'uf',
				'callback':fnCB${model.objUid},
				'serviceUid':'${model.linkService.objUid}',   
				'formName':'${model.targetForms}',
				'paras': dealBus
				<#if (model.linkPaneModel)?exists>	  
				 ,'pml':'${model.linkPaneModel.name}'
 		         ,'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}'
	   			 ,'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}'
				 
				 <#else>
		         ,'pml':'${model.gridModel.containerPane.name}'
				</#if> 
			    <#if (model.targetPaneModel)?exists>	         
		         	,'target':'${model.targetPaneModel.name}'
		        <#else>
		        	,'target':'${model.gridModel.containerPane.name}'	 	
				</#if>
				<#if (model.echoJs)?exists>	         
		         ,'echoJs':'${model.echoJs}'
		         </#if>
				})
	});
</script>				
