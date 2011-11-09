<#include "TFormBase.ftl">
 <button type="button" style=""   id='${model.objUid}' class='delete'>${model.l10n?default("删除")}</button>
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
       if(confirm('你确定要删除吗')){
        	$('#g${model.gridModel.objUid} tbody  tr.selected').remove();
       }
  })
 </script>
