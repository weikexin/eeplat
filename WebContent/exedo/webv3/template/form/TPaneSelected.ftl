<#include "TFormBase.ftl">
<button type="button"  style="${model.style?if_exists}"  id='${model.objUid}' <#compress><@JudgeStyle model/></#compress> > ${model.l10n}</button>
<script>
 $('#${model.objUid}').bind('click',function(){  
	  if($('#g${model.gridModel.objUid} tbody  tr.selected').length == 0){
	       if($(this).parent().parent().attr('value')!=null){
	  		    $(this).parent().parent().addClass("selected");				
			}else{	
		    	alert("请选择一条记录！");
	         	return;
	      }
       }
	   var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
	   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}" + "&contextValue=" + selectedValue;
	   $(".toolbar .selected").removeClass("selected");
	   $(this).addClass("selected");
 	  <#if (model.linkPaneModel?exists && (model.linkPaneModel.linkType==5))>
 	     callPlatBus({'paras':dealBus});	   
	  </#if> 
	   
	   <#if ((model.linkPaneModel)?exists) >
	   loadPml({
	   			 'paras':dealBus, 
	   			 <#if (model.linkPaneModel.linkType==5)>
	   			 	'pml':'${model.linkPaneModel.resource.resourcePath}',
	   			 <#else>
	   			 	'pml':'${model.linkPaneModel.name}',
	   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',
	   			 	
	   			 </#if>
	       		 'title':'${model.linkPaneModel.title}'
	      		  <#if (model.targetPaneModel)?exists>	         
	      				,'target':'${model.targetPaneModel.name}'
			 </#if> }
	    );
	   </#if> 
  });
 </script>