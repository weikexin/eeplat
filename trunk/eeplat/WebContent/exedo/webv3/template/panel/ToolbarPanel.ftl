   <div id="${model.name}_toolbar" align="left"></div>
   <div id="${model.name}_content" style="width:100%;overflow:visible"></div>
   <script  language='javascript'>
		var toolbar = new Toolbar({
        renderTo : '${model.name}_toolbar',
		//border: 'top',
        items : [
                 <#list items as item>
	             {
			          type : 'button',
			          text : '${item.title}',
			          bodyStyle : <#if item.icon?exists>'${item.icon}'<#else>'${item.cssStyle}'</#if>,
			          useable : 'T',
			          handler : function(){
			            $("#${model.name}_toolbar .toolbar .selected").removeClass("selected");
			            $(this).addClass("selected");
			           
			            var paras =  "";
			            <#if conditionGrid?exists>
			             paras =  getParasOfForms("a${conditionGrid.objUid}");
			            </#if>
			          	$("#${model.name}_content").empty().load("${item.fullCorrHref}",urlCodeDeal(paras));
			          }
			     }<#if ((item_index+1) != items?size)>,'-',</#if>
                </#list> ]      
         });
	  toolbar.render();
	  $("#${model.name}_toolbar .toolbar  button:first").click();
  </script>	
	
	
