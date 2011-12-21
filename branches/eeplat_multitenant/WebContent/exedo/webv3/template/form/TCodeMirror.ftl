<textarea id="${model.fullColID}" name="${model.colName}"  style="height: 300px; width: 100%;">${model.value?if_exists}
</textarea>
<!--
<input type="hidden" id="${model.fullColID}_cphd" name="${model.colName}" value="${model.value?if_exists}">
-->
<br/>
<script>
  mirrorEditor = CodeMirror.fromTextArea(document.getElementById("${model.fullColID}"),
       { tabMode: "indent",lineNumbers: true,  matchBrackets: true, onKeyEvent: function(i, e) {
	      if (e.keyCode == 32 && (e.shiftKey || e.metaKey) && !e.altKey) {
	        e.stop();
	        return startComplete();
	      }
    	}
   });
 </script>





