  <div id="fq${model.objUid}"></div>
  <div style="vertical-align: middle">
  <input type="text" readonly="readonly" name="${model.colName}"/>
  <input  class="uploadify" id="uf${model.objUid}"  type='file' value='上传'/>
  
  <div>
  <script>
   	uploadify_empinfor("uf${model.objUid}","fq${model.objUid}","${model.inputConfig?if_exists}","${model.inputConstraint?if_exists}",true,"${emp_name}");
  </script>  