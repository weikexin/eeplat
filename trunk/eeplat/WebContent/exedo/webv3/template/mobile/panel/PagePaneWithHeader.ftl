<div data-role="page"  id="${model.name}">
	<div data-role="header" data-theme="b">
		<h1>${model.title}</h1>
		<a href="${app_index}" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
	</div>
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
 	<#if  footer?exists>
	 	<div  data-role="footer">
	 	   <div data-role="navbar" data-theme="e">
				<ul>
				   ${footer}
				   <li><a href="javascript:window.location='/${webmodule}/${model.name}.pml'" data-theme='b' data-icon='refresh'>刷新</a></li>
				</ul>
	  	</div><!-- /navbar -->
	 	</div> 
 	</#if>
</div>
