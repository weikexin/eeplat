<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html >

<script>



////////////////////////////////无刷新 flash上传
function uploadify(uploadifyID,uploadifyQueueID,fileDesc,fileExt,autoUpload,sessionid){
	
	if(fileDesc==null || $.trim(fileDesc)==""){
		fileDesc='只能选择图像类文件(*.jpg;*.gif;*.bmp)';
	}
	if(fileExt==null || $.trim(fileExt)==""){
		fileExt = '*.jpg;*.gif;*.bmp';
	}
	if(autoUpload==null){
		autoUpload = true;
	}
	
	var o = $("#" + uploadifyID).prev();
	$("#" + uploadifyID).uploadify({
		
		'uploader'       : 'exedo/webv3/js/jquery-plugin/fileuploader/uploadify.swf',
		'upload'
		'scriptData'     :{'jsessionid':sessionid},
		'script'         : 'exedo/webv3/upload_action_uploadify.jsp;jsessionid=' + sessionid ,
		'cancelImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/cancel.png',
		'queueID'        : uploadifyQueueID,
		'auto'           : autoUpload,
		'multi'          : true,
		'simUploadLimit' : 2,
		'buttonImg'      : 'exedo/webv3/js/jquery-plugin/fileuploader/browse-files.gif',
		'wmode'          : 'transparent',
		'width'          : 75,
		'height'          : 25,
		'fileDesc'       :fileDesc,
		'fileExt'		 :fileExt,
		'onSelect'       : function(event,queueID,fileObj){if(o.val()==""){ o.val(o.val() + fileObj.name);}else{ o.val(o.val() + ";" + fileObj.name);}},
		'onCancel'       : function(event,queueID,fileObj,data){o.val(o.val().replace(fileObj.name,""));}
	});
}

</script>

  <div id="fq_"></div>
  <div style="vertical-align: middle">
  <input type="text" readonly="readonly" name="${model.colName}"/>
  <input  class="uploadify" id="uf${model.objUid}"  type='file' value='上传'/>
  
  <div>

  <script>
   	uploadify_empinfor("uf${model.objUid}","fq${model.objUid}","${model.inputConfig?if_exists}","${model.inputConstraint?if_exists}",true,"${emp_name}");
  </script>  

					<input  type="button" onclick="return subThisForm();" style="width:75px;text-align:center;" value="上传">
	</body>
</html>