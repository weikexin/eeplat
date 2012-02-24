<#include "TFormBase.ftl">
<#--参数通过formName传递-->
<button type="button"  id='${model.objUid}' style="${model.style?if_exists}"   <#compress><@JudgeStyle model/></#compress> >${model.l10n}</button>
<script>
$('#${model.objUid}').bind('click',function(){
     
     var selects = $("#g${model.gridModel.objUid} tbody  tr input:checked");
     if(selects.length==0){
     	alert("你还没有选择数据");
     	return ;
     }
     var values = '';
     var titles = '';
     for(var i = 0; i < selects.length; i++){
        if(i > 0){
        	values = values + ',' + selects.eq(i).attr("value");
	        titles = titles + ',' +selects.eq(i).attr("title");
        }else{
	        values = values + selects.eq(i).attr("value");
	        titles = titles + selects.eq(i).attr("title");
	    }
     }
     $('#' + invokeDomId).val(values);
     $('#' + invokeDomId +"show").val(titles);
     
     ///关闭弹出页面
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
	 

  }
);
</script>