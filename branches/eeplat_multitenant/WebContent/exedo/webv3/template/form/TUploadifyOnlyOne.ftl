  <div id="fqol${model.objUid}"></div>
  <div style="vertical-align: middle">
  <input type="text" readonly="readonly" name="${model.colName}"/>
  <input  class="uploadify" id="ufol${model.objUid}"  type='file' value='上传'/>
  
  <div>
  <script>
   	uploadifyOnlyOne("ufol${model.objUid}","fqol${model.objUid}","${model.inputConfig?if_exists}","${model.inputConstraint?if_exists}",true,"${sessionid}");
  </script> 
  </div> 