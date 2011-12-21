<#include "TFormBase.ftl">
 <button type="button" style=""   id='${model.objUid}' class='delete'>${model.l10n?default("删除")}</button>
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
////默认只支持删除一条记录，这也是防止误删的手段。如果要删除多条用CoreaSaveAll这个自定义动作
 $('#${model.objUid}').bind('click',function(){
 
     	if($('#g${model.gridModel.objUid} tbody  tr.selected').length == 0){
	       if($(this).parent().parent().attr('value')!=null){
	  		    $(this).parent().parent().addClass("selected");				
			}else{	
			    	alert("请选择一条记录！");
		         	return;
	      }
       }
       if(confirm('你确定要删除吗')){
        	if($('#g${model.gridModel.objUid} tbody  tr.selected').attr('value')!=null){
        	 var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
		     var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}&invokeButtonUid=${model.objUid}" + "&contextValue=" + selectedValue;
			 callService({'btn':this,
				'serviceUid':'${model.linkService.objUid}',  
				'callback':fnCB${model.objUid}, 
				'formName':'${model.targetForms}',
				'paras': dealBus
				});
        	}
        	$('#g${model.gridModel.objUid} tbody  tr.selected').remove();
       }
  })
 </script>
