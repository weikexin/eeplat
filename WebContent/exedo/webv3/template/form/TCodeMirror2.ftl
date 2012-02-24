<textarea id="${model.fullColID}" name="${model.colName}"  style="height: 300px; width: 100%;">${model.value?if_exists}
</textarea>
<!--
<input type="hidden" id="${model.fullColID}_cphd" name="${model.colName}" value="${model.value?if_exists}">
-->
<br/>
<script>
  mirrorEditor2 = CodeMirror.fromTextArea(document.getElementById("${model.fullColID}"),
       {  mode: "text/html",tabMode: "indent",indentUnit: 4, lineNumbers: true,  matchBrackets: true});
        
</script>





