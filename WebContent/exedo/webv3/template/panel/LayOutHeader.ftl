 <div class='gTop' id='${model.name}'>
 	<CENTER> <DIV id=outDv></div></CENTER>
	<DIV class=gTab  id=dvTab>
		<TABLE class='on' tabId='workbench_container'  title='首页' style='WIDTH: 130px; ZOOM: 1'>
		   <TBODY><TR>
		     <TD class=tLe></TD><TD class=bdy  onclick='loadWorkbench()'><NOBR>首页</NOBR></TD><TD class=tRi></TD>
		    </TR></TBODY>
		</TABLE>
	</DIV>
 </div>
<script>
	//加载头部
	<#if model.resource.resourcePath?exists>
		$("#outDv").empty().load("${model.resource.resourcePath}");
	</#if>
</script>
