<#if browseAgent=='ie'>
	<textarea id="${model.fullColID}" name="${model.colName}"  style="height: ${model.inputConfig?default(300)}px; width: 100%;">${model.value?if_exists}
	</textarea>
	<span style="color:red">请注意此代码编辑功能在IE下为有限支持，请使用Firefox或Chrome。</span>
	<!--
	<input type="hidden" id="${model.fullColID}_cphd" name="${model.colName}" value="${model.value?if_exists}">
	-->
	<br/>
	<script>
	  mirrorEditor = CodeMirror.fromTextArea(document.getElementById("${model.fullColID}"),
	       {mode: "javascript",  tabMode: "indent",indentUnit: 4,lineNumbers: true,  matchBrackets: true}
	   );
	 </script>
<#else>
<input type="hidden" id="${model.fullColID}"  value="${model.value?if_exists}"/>
<iframe id="ace_editer_code" name="ace_editer_code" frameborder='0' src="ace_editor/css.jsp?hiddenid=${model.fullColID}" style="width:100%;height:450px"/>
</#if>



