<form id="archiveDetails">
<table class="tablesorter"  border="0" cellpadding="0" cellspacing="1">
<thead>
<tr>
   <th  nowrap='nowrap' align='center'>附件类型</th> 
   <th  nowrap='nowrap' align='center'>附件标题</th> 
   <th  nowrap='nowrap' align='center'>相关附件</th> 
</tr>
</thead>
<tbody>
<tr>
<td align=center><select   style='width:100px' 
  id="grid_arch_accountimage_insert_insert_imagetype"  name="imagetype"
    title='附件类型' exedo_notnull='NotNull' >
	<option value="1" selected>营业执照</option>
	<option value="2">税务登记证</option>
	<option value="3">申请书</option>
	<option value="4">协议</option>
	</select>
</td>	

<td   align='left'><input  style='border:#B3B3B3 1px solid;' onclick="this.style.borderColor='#406B9B'" 
 onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'"
   type='text' name='imagetitle' id='grid_arch_accountimage_insert_insert_imagetitle'
    title='附件标题' size="25"/>
</td>
    
    
<td   align='left'>
  <div id="fileQueue1"></div>
   <input type="hidden" name="ImageAll" value=""/>
  <input class="uploadify" id="uploadify1"  type='file' value='上传'/>
</td>


</tr>

<!-------------------------222222222----------------------------------->
<tr>
<td align=center><select   style='width:100px' 
  id="grid_arch_accountimage_insert_insert_imagetype"  name="imagetype"
    title='附件类型' exedo_notnull='NotNull' >
	<option value="1" >营业执照</option>
	<option value="2" selected>税务登记证</option>
	<option value="3">申请书</option>
	<option value="4">协议</option>
	</select>
</td>	

<td   align='left'><input  style='border:#B3B3B3 1px solid;' onclick="this.style.borderColor='#406B9B'" 
 onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'"
   type='text' name='imagetitle' id='grid_arch_accountimage_insert_insert_imagetitle'
    title='附件标题' size="25"/>
</td>
    
    
<td  align='left'>
  <div id="fileQueue2"></div>
  <input type="hidden" name="ImageAll" value=""/>
  <input class="uploadify" id="uploadify2"  type='file' value='上传'/>
</td>


</tr>


<!-------------------------333333333333333333333333333333----------------------------------->
<tr>
<td align=center><select   style='width:100px' 
  id="grid_arch_accountimage_insert_insert_imagetype"  name="imagetype"
    title='附件类型' exedo_notnull='NotNull' >
	<option value="1" >营业执照</option>
	<option value="2" >税务登记证</option>
	<option value="3" selected>申请书</option>
	<option value="4">协议</option>
	</select>
</td>	

<td   align='left'><input  style='border:#B3B3B3 1px solid;' onclick="this.style.borderColor='#406B9B'" 
 onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'"
   type='text' name='imagetitle' id='grid_arch_accountimage_insert_insert_imagetitle'
    title='附件标题' size="25"/>
</td>
    
    
<td   align='left'>
  <div id="fileQueue3"></div>
  <input type="hidden" name="ImageAll" value=""/>
  <input class="uploadify" id="uploadify3" type='file' value='上传'/>
</td>

</tr>

<!-------------------------444444444444444444444444----------------------------------->
<tr>
<td align=center><select   style='width:100px' 
  id="grid_arch_accountimage_insert_insert_imagetype"  name="imagetype"
    title='附件类型' exedo_notnull='NotNull' >
	<option value="1" >营业执照</option>
	<option value="2" >税务登记证</option>
	<option value="3" >申请书</option>
	<option value="4" selected>协议</option>
	</select>
</td>	

<td   align='left'><input  style='border:#B3B3B3 1px solid;' onclick="this.style.borderColor='#406B9B'" 
 onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'"
   type='text' name='imagetitle' id='grid_arch_accountimage_insert_insert_imagetitle'
    title='附件标题' size="25"/>
</td>
    
    
<td   align='left'>
  <div id="fileQueue4"></div>
  <input type="hidden" name="ImageAll" value=""/>
  <input class="uploadify" id="uploadify4"  onclick="saveArchiveDetails(this)"  type='file' value='上传'/>

</td>

</tr>


</tbody>


</table>

<table border='0' cellspacing='0' cellpadding='0' align='center'>
	<tr><td height='30' valign='bottom'   align='center' >&nbsp;
	<input type='button' 
	 style="height:25px"  title='上传档案并保存纪录' id='f887e6c419dfb4a82b3177b8ec1954b2f'
	  onclick="saveArchiveDetails(this)"
	   value='上传档案并保存纪录'/>
&nbsp;&nbsp;</td></tr>
</table>
</form>

<script>

uploadify("uploadify1","fileQueue1");
uploadify("uploadify2","fileQueue2");
uploadify("uploadify3","fileQueue3");
uploadify("uploadify4","fileQueue4");

function saveArchiveDetails(btn){

	   btn.disabled = true;

	   var paras = $("#a6858baff72f04a3fbb78ecf62e99872d").formSerialize() + "&"
	   + $("#archiveDetails").formSerialize();

	   callAction({'btn':btn,
	   			   'actionName':"com.exedosoft.plat.action.customize.archive.SaveArchiveAndDetails",
	   			   'paras':paras});
	   
	   $('#uploadify1').uploadifyUpload();
	   $('#uploadify2').uploadifyUpload();
	   $('#uploadify3').uploadifyUpload();
	   $('#uploadify4').uploadifyUpload();
	   
	   $("#a6858baff72f04a3fbb78ecf62e99872d")[0].reset();
	   $("#archiveDetails")[0].reset();
}
</script>
