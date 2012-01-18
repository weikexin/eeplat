<#include "TFormBase.ftl">
<#--参数通过formName传递-->
<button type="button"  id='${model.objUid}'  <#compress><@JudgeStyle model/></#compress> >${model.l10n}</button>
<script language="javascript">
/*
	model.inputConfig--->  template name
	model.linkService
	
	onclick = window.open('xxx.jsp?' +)
	1,paras
	2,service
	3,template
*/
$('#${model.objUid}').bind('click',function(){
	
	var para = '';
	<#if formID?exists><#-- 我在结果面板中 -->
		para=$("#${formID}").serialize();
		alert(para);
	<#else><#-- 我在条件面板中 不用传具体参数了，在action中直接调用获得表单所有参数的API-->
		para='-100';//没有业务含义，用来在平台中做自身所处位置判断。
		alert(para);
	</#if>
	var templateFileName = unescape(${model.inputConfig?if_exists});
	var frame = document.createElement("iframe");
	frame.src = globalURL+"exedo/webv3/download_action.jsp?formArgs="+para+"&serviceID=<#if model.linkService.objUid?exists>${model.linkService.objUid}<#else>${serviceID}</#if>&fileName="+templateFileName;
	frame.style.display = "none";  
	document.body.appendChild(frame); 

});

</script>