<#include "default/header.ftl">
	<div id="content">
		<div id="content_text" class="content_text">
			<#list Blog as data >
				<div>
					<h3><p><a href=${data.url} >${data.title}</a></p></h3>
					<p class="summary">发表于${data.date}由${data.user!"admin"}</p>
					<p>${data.content}</p>
					<p class="summary">发表在 ${data.cety} | 留下评论 </p>
				</div>
			</#list>
			
		</div><!-- content_text -->
<#include "default/sidebar.ftl">
<#include "default/footer.ftl">
	
</div><!-- mainframe -->

</body>
</html>