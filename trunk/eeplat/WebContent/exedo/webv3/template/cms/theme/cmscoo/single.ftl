<#include "header.ftl"/>
<!--Header End-->
<!--Banner-->
<@list_posts >
<div class="page_ps">
现在的位置: <a title="返回首页" href="${bloginfo('site_url')}">首页</a> ＞${dataMap.posts_category}＞正文<!-- <?php the_title();?> --></div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
    <H3 class="Title">${dataMap.posts_title}</H3>
    <div class="t_clie">发表于：${dataMap.posts_date} 分类：${dataMap.posts_category}</div>
    <div class="P_content">
    	${dataMap.posts_content}
    </div>
    <!-- end: entrymeta -->
		
	<div class="related_img">

</div>
	<div class="clear"></div>
	<#include "comments.ftl"/>
    
   
    <div class="clear"></div>
  </div>
  <!--left page end-->
  <!--right page star-->
  <div class="p_rightbar">
    <#include "sidebar.ftl" />
  <!--Right End -->
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
<!--Content End-->
</@list_posts>
<!--Footer-->
<#include "footer.ftl" />