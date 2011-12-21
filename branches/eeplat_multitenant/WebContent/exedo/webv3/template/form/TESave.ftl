<#include "TFormBase.ftl">
 <button type="button"   id='${model.objUid}' class='save'>${model.l10n?default("保存")}</button>
 <script>
 
 function fnCB${model.objUid}(){
  	<#if (!((model.inputConstraint)?exists && model.inputConstraint=='noCloseOpener'))>
	    <#if (model.gridModel.containerPane.name)?exists>

		try{
			if($('#F' + '${model.gridModel.containerPane.name}').size()>0){
	  			$('#F' + '${model.gridModel.containerPane.name}').dialog('close');
	  		}else{
	  			$('#' + '${model.gridModel.containerPane.name}').parents(".ui-dialog-content").dialog('close');
	  			<#if (model.gridModel.containerPane.parent)?exists>	
	  				$('#F' + '${model.gridModel.containerPane.parent.name}').dialog('close');
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
 		     var addStr = "";
 		     var modiStr = "";
 		     var inputName = "";
 		     $('#g${model.gridModel.objUid} tbody tr').each(
 		        function(){
 		        	if($(this).attr("edit")=='true'){
 		        		if($(this).attr("value")==null){//add
 		        		   addStr = addStr + "&" + $(this).find(":input").serialize();
 		        		   if(inputName==""){
 		        		   	 inputName = $(this).find(":input").eq(0).attr("name");
 		        		   }
 		        		}else{//update
 		        		  
 		        		   modiStr = modiStr + "&id=" + $(this).attr("value")+";﹕#";
 		        		    $(this).find(":input").each(
 		        		      function(i,o){
  		        		      	 modiStr = modiStr + $(o).attr("name")+"﹕﹕" + $(o).val()+";﹕#";
 		        		      }
 		        		    );
 		        		}
 		        	}		        
 		        }
 		     );
 		     var dealBus = "formUid=${model.objUid}&inputName="+inputName+"&";
             dealBus = dealBus + (addStr + modiStr).substr(1);
               //表单验证
		if(validate('${model.targetForms}')){
     	    callAction({'btn':this,
				'actionName':'com.exedosoft.plat.action.CoreSaveEditAction',  
				'callback':fnCB${model.objUid}, 
				'paras': dealBus
			});
		}

  });
 
 </script>
