<#macro JudgeStyle a>
	<#if (a.isOutGridAction?exists && ( a.isOutGridAction == 1)) >
		class='ctlBtn'
	<#elseif (a.style?exists ) >
		class='${a.style}'
	<#elseif (a.linkPaneModel?exists && (a.linkPaneModel.cssStyle)?exists ) >
		class='${a.linkPaneModel.cssStyle}'
	<#else>
	    class='role-setup'	
	</#if>
</#macro>