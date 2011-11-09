<#include "TFormBase.ftl">


 <button type="button" style=""   id='${model.objUid}' class='delete'>${model.l10n?default("新增")}</button>
 <script>
 
   var globali = 2;
  $('#${model.objUid}').bind('click',function(){
      var o = $('#tr0').clone();
      o.css("display","")
      o.attr("id","tr" + globali++);
      o.bind('click',function(){
			$('#g${model.gridModel.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$(this).addClass("selected");
		});
	  $('#g${model.gridModel.objUid}').append(o);
	   
  });
 
 </script>
