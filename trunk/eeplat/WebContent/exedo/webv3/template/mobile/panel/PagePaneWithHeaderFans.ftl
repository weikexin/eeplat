<div data-role="page"  id="${model.name}" name="${model.name}">
	<div data-role="header" data-theme="b">
		<a href="#" data-icon="arrow-l" data-rel="back"  class="ui-btn-left">Back</a>
		<h1>${model.title}</h1>
		<a href="javascript:window.location='${app_index}'" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
	</div>
  <div data-role="content">
 	   ${items_html}
 	</div>   
 	<#if  footer?exists>
	 	<div  data-role="footer">
	 	   <div data-role="navbar" data-theme="e">
				<ul>
				   ${footer}
				</ul>
	  	</div><!-- /navbar -->
	 	</div> 
 	</#if>
</div>
