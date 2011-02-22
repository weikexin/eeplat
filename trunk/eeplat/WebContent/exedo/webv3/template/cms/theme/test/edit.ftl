<#include "header.ftl">
<div class="taobaobei" >
	<div class="subnavi">
	</div>
		 <script type="text/javascript">
		         var oFCKeditor = new FCKeditor('FCKeditor1') ;
		         oFCKeditor.BasePath = "${rootPath}/fckeditor/";
		         //oFCKeditor.BasePath   = "/FCKEditTest/fckeditor/";
		         oFCKeditor.Width="100%";
		         oFCKeditor.Height="600";
		         oFCKeditor.Value="";
		         //oFCKeditor.ToolbarSet="Basic";
		         //默认是default
		         oFCKeditor.ToolbarSet="Default";
		         oFCKeditor.Create() ;
		  </script>
	
</div>
<div class="sidebar2">
	<#include "sidebar1.ftl">
</div>
<!-- 侧边栏2 结束 -->

<br clear="all" />
<#include "flink.ftl">
<#include "footer.ftl">

</body>

</html>

