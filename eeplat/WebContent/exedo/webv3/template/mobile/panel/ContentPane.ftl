<div data-role="content">
    <script>
         $('#${model.name}').bind('pagehide',function(event, ui){
            if(ui.nextPage){
	              if( $(this).attr("id")===ui.nextPage.attr("id") ){
	                  if( $(this).attr("id")===ui.nextPage.attr("id") ){
	                 	   $(this).remove();
	                 	 }
	              }
             }
          });
          
        $('#${model.name}').bind('pagebeforeshow',function(event){
        ////不一定是active 也有可能重复，nnd 要想一个统一的解决方案
        
             if($.mobile.activePage!=null &&  ($.mobile.activePage.attr("id")=='${model.name}')){
                  if($.mobile.activePage!=null &&  ($.mobile.activePage.attr("id")=='${model.name}')){
                    	$.mobile.activePage.remove();
                      $.mobile.urlHistory.stack.pop();  	
                 }
             }
				     return true;
				});
     </script>
 	${items_html}
</div>
