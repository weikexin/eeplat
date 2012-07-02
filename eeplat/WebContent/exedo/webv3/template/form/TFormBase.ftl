<#macro JudgeStyle a>

	<#if (a.isOutGridAction?exists && ( a.isOutGridAction == 1)) >
		class='ctlBtn'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.style?exists ) >
		class='${a.style}'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif  (a.l10n?exists && (a.l10n?contains('新增') || a.l10n?contains('新建') || a.l10n?contains('创建')
	                            || a.l10n?lower_case?contains('create') ||  a.l10n?lower_case?contains('new') ||  a.l10n?contains('insert')
	       ) ) >
		class='new'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists && (a.l10n?contains('修改') || a.l10n?contains('编辑')
		                            || a.l10n?lower_case?contains('modify') ||  a.l10n?lower_case?contains('edit')
	
	  	)) >
		class='edit'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists &&( a.l10n?contains('删除') 
							|| a.l10n?lower_case?contains('delete') ||  a.l10n?lower_case?contains('remove')
		)) >
		class='delete'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists &&( a.l10n?contains('复制') 
							|| a.l10n?lower_case?contains('copy') 
		)) >
		class='copy'  <#compress><@JudgeMobileIcon a/> </#compress>	
	<#elseif (a.l10n?exists && (a.l10n?contains('查看') 
							|| a.l10n?lower_case?contains('browse') 
	)) >
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
	<#elseif  (a.l10n?exists &&( a.l10n?contains('新增') || a.l10n?contains('新建')
	                          || a.l10n?lower_case?contains('create') ||  a.l10n?lower_case?contains('new') ||  a.l10n?contains('insert')
	       ) ) >
		 data-icon='plus'  data-theme='b'
	<#elseif (a.l10n?exists &&(a.l10n?contains('修改')   
						|| a.l10n?lower_case?contains('modify') ||  a.l10n?lower_case?contains('edit')
	  	)) >
		 data-icon='gear' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('删除')
						|| a.l10n?lower_case?contains('delete') ||  a.l10n?lower_case?contains('remove')
		)) >
		 data-icon='delete' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('查看') 
		|| a.l10n?lower_case?contains('browse') 
		)) >
		 data-icon='info' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('查询')
		|| a.l10n?lower_case?contains('search') 
		)) >
		 data-icon='search' data-theme='b'
	<#elseif (a.l10n?exists &&(a.l10n?contains('保存')
		|| a.l10n?lower_case?contains('save') 
		|| a.l10n?lower_case?contains('store') 
	)) >
		 data-icon='gear' data-theme='b'
	<#else>
	   data-icon='gear' data-theme='c'
	</#if>
</#macro>