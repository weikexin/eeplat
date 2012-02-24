        <div id="${model.name}" width=100% height=100%>
            <ul>
            	<#list items as item>
                	<li><a href="${item.fullCorrHref}"><span>${item.title}</span></a></li>
                </#list>
            </ul>
        </div>
	<script  language='javascript'>
		$('#${model.name}').tabs();
		resscrEvt();
	</script>	
	
	
