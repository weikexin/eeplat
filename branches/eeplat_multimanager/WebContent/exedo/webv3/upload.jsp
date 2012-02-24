<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html >
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/main/main_lan.css" type="text/css" />
<title>上传文件</title>
<%

 String colName  = request.getParameter("colName");
 String blobColName  = request.getParameter("blobColName");

%>
<script language="javascript">

 function subThisForm(){


  if(aform.fileupload.value==''){
     alert("您还没有选择文件！");
     return false;
  }
  aform.submit();

 }

</script>

</head>
	<body style="margin-left:5px">
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	    <form name="aform" action="upload_action_comm.jsp" method="post"  enctype='multipart/form-data'>
		<table class='labeltable_middle_table' cellspacing="0" cellpadding="1">
			<tr>
				<td  class='labeltable_middle_td_bar' ><b>:: 上传文件 ::</b></td>
			</tr>
			<tr id="put_menu">
				<td >
					<table cellspacing="0" cellpadding="1">
						<tr>
							<td>
								<input name="fileupload" type="file" size="30" style="text-align:left;">
                                <input name="colName"  type="hidden" value="<%=colName%>">
                                <input name="blobColName"  type="hidden" value="<%=blobColName%>">
                                
                                
							</td>
						</tr>
						<tr height=2>
						
						</tr>
						
						<tr>
 						    <td  align="center">
								<input  type="button" style="width:75px;text-align:center;" onclick="window.close();" value="取消">
								<input  type="button" onclick="return subThisForm();" style="width:75px;text-align:center;" value="上传">
							</td>
						</tr>	
					
					</table>
				</td>
			</tr>
		</table>
	   </form>
	</body>
</html>