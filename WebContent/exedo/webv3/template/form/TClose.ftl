<input type="button"  style="${model.style?if_exists}"  id='${model.objUid}' value="&nbsp;${model.l10n}&nbsp;" class='ctlBtn'>
<script>
$('#${model.objUid}').bind('click',function(){
	<#if (model.gridModel.containerPane.name)?exists>
		 <#if (model.linkPaneModel)?exists>
			loadPml({
				   		 'pml':'${model.linkPaneModel.name}',
				   		 'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 		 'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',
				   		 'title':'${model.linkPaneModel.title}',
				   		 'formName':'a${model.gridModel.objUid}'
				   		  <#if (model.targetPaneModel)?exists>	         
						,'target':'${model.targetPaneModel.name}'
						 </#if> }
		    );
		    
		  </#if>  


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
	  			  		
		try{
			var tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.name}'] .btn";
			tabCloseWindow(tabBtnSelector);
			<#if (model.gridModel.containerPane.parent)?exists>	 
			    tabBtnSelector = "#dvTab table[tabId='${model.gridModel.containerPane.parent.name}'] .btn";
			    tabCloseWindow(tabBtnSelector);
		 	</#if>
		}catch(e){
				  	
		}
	     
	
  	</#if>
  }
);
</script>