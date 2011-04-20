<#include "header.ftl" />
<!--Header End-->
<!--Banner-->
<div class="banner">
   <div class="b_bar">
      <div class="B_border">
         <div class="B_left">
         	<#include "picbrowse.ftl" />
         		<!--
             <dl class="News">最近更新</dl>
             <ul class="newslist">
             <@list_posts>
					 <li>· <a href="${dataMap.posts_url}" rel="bookmark" title="详细阅读 ">${dataMap.posts_title}</a></li>
			</@list_posts>
             </ul>
             -->
         </div>
        <div class="b_right"><a href="http://www.id78.com" target="_blank"><img src="${bloginfo('current_path')}/images/yiyi.png" Alt="电子商务外包"/></a></div>
      </div>
   </div>
</div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
  <div class="P_left">
	<@list_category>
		<div class="P_category">
			<h2 class="P_c_one"><a href="${dataMap.url}">${dataMap.cat_name}</a></h2>
		    	<ul class="p_news">
		    		<@list_posts>
		    			<li>· <a href="${dataMap.posts_url}" title="${dataMap.posts_title}">${dataMap.posts_title}</a></li>
		    		</@list_posts>
		   		</ul>
	   </div>
	 </@list_category>
<div class="clear"></div>
  </div>
<#include "sidebar.ftl"/>
  <!--Right End -->
<div class="clear"></div>
</div><!-- class="pages"-->
<!--Content End-->
<!--Link-->
<div class="Link">
<ul class='xoxo blogroll'>
<!--
 <?php
$result = $wpdb->get_results("SELECT link_url,link_name,link_target FROM $wpdb->links ORDER BY link_id DESC LIMIT 0 , 10");
foreach ($result as $link) {
					setup_postdata($link);
					$linkurl = $link->link_url;
					$linkname = $link->link_name;
					$linktarget = $link->link_target;
					?>
<li><a href="<?php echo $linkurl; ?>" title="<?php echo $linkname ?>" target="<?php echo $linktarget ?>"><?php echo $linkname ?></a></li><?php }?>
	</ul>
	-->
</div>
<!--Link End-->
<#include "footer.ftl" />