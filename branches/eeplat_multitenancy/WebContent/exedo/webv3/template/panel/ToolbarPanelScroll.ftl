   <div id="${model.name}_toolbar" align="left"></div>
   <div id="${model.name}_content" style="width:100%;height:92%;overflow:auto"></div>
   <script  language='javascript'>
		var toolbar = new Toolbar({
        renderTo : '${model.name}_toolbar',
		//border: 'top',
        items : [
                 <#list items as item>
	             {
			          type : 'button',
			          text : '${item.title}',
			          bodyStyle : '${item.cssStyle}',
			          useable : 'T',
			          handler : function(){
			            $("#${model.name}_toolbar .toolbar .selected").removeClass("selected");
			            $(this).addClass("selected");
			          	$("#${model.name}_content").empty().load("${item.fullCorrHref}");
			          }
			     }<#if ((item_index+1) != items?size)>,'-',</#if>
                </#list> ]      
         });
	  toolbar.render();
	  $("#${model.name}_toolbar .toolbar  button:first").click();
  </script>	
	
	
