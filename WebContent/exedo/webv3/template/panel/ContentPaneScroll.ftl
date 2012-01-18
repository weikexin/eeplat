<div id="${model.name}"  style="overflow:auto;width:100%;height:100%;">
	<#if model.navigationTxt?exists>
		<div class='navcontainer'>
		    <div class='navleft'>
		       ${model.navigationTxt}
		    </div>
		 </div>
	 </#if>
 	${items_html}
</div>