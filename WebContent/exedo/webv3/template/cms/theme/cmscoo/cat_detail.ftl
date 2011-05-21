<#include "header.ftl" />
<!--Banner-->
<div class="page_ps">
现在位置 ：<a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞ ${get_category_name('${dataMap.cat_id}')}</div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
          <ul class="listone" >
          	<@list_posts>
			  <li>
				  <h5>· <a href="${dataMap.posts_url}" rel="bookmark" title="详细阅读 ${dataMap.posts_title}">${dataMap.posts_title}</a></h5>
				  <p><a href="${dataMap.posts_url}" rel="bookmark" title="详细阅读 ${dataMap.posts_title}">${dataMap.posts_except}</a></p>
			  </li>
			
			</@list_posts>
		 </ul>
		
 	<!-- navigation -->
    <div class="navigation"><?php pagination($query_string); ?></div>
 	<!-- end: navigation -->
  </div>
  <!--left page end-->
  <!--right page star-->
  <div class="p_rightbar">
   <#include "sidebar.ftl" />
  <div class="clear"></div>
  </div>
  <!--Right End -->
<div class="clear"></div>
</div>
<!--Content End-->

<#include "footer.ftl" />
