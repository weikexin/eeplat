<#macro JudgeStyle a>
	<#if (a.isOutGridAction?exists && ( a.isOutGridAction == 1)) >
		class='ctlBtn'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.style?exists ) >
		class='${a.style}'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif  (a.l10n?exists &&('新增'=a.l10n) ) >
		class='new'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif  (a.l10n?exists &&('新建'=a.l10n) ) >
		class='new'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists &&('修改'=a.l10n) ) >
		class='edit'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists &&('删除'=a.l10n) ) >
		class='delete'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists &&('查看'=a.l10n) ) >
		class='btn-all'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.linkPaneModel?exists && (a.linkPaneModel.cssStyle)?exists ) >
		class='${a.linkPaneModel.cssStyle}'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#else>
	    class='role-setup'	  <#compress><@JudgeMobileIcon a/> </#compress>
	</#if>
</#macro>


<#macro JudgeMobileIcon a>
	<#if (a.uiType?exists )>
		data-icon='${a.uiType}' data-theme='c'
	<#elseif  (a.l10n?exists &&(a.l10n?contains('新增')) ) >
		 data-icon='plus'  data-theme='b'
	<#elseif  (a.l10n?exists &&(a.l10n?contains('新建')) ) >
		 data-icon='plus' data-theme='b'
	<#elseif (a.l10n?exists &&(a.l10n?contains('修改')) ) >
		 data-icon='gear' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('删除')) ) >
		 data-icon='delete' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('查看')) ) >
		 data-icon='info' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('查询')) ) >
		 data-icon='search' data-theme='b'
	<#elseif (a.l10n?exists &&(a.l10n?contains('保存')) ) >
		 data-icon='gear' data-theme='b'
	<#else>
	   data-icon='gear' data-theme='c'
	</#if>
</#macro>