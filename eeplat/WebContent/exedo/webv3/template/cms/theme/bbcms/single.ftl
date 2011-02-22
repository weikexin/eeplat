<#include "header.ftl">

<!-- 左侧 开始 -->
<div class="con_left">

<!-- 正文 开始 -->
<div class="entry_single">

	<#if have_posts() >
    	<@list_posts>
        <div class="postnavi"><div class="site-navi">当前位置: <a href="${bloginfo('site_url')}">${bloginfo('name')}</a> >${dataMap.posts_category}文章正文</div></div>
        
			<div class="post" id="post-<?php the_ID(); ?>">
				<h2 class="mid_single"><a href="${dataMap.posts_url}" rel="bookmark" title="${dataMap.posts_title}">${dataMap.posts_title}</a></h2>
				<div class="describe">${dataMap.posts_author!"admin"} 发表于${dataMap.posts_date} | 来源：| 阅读 </div>

					<div class="postbg">${dataMap.posts_content}</div>
	    </@list_posts>
<div class="postother">关键字:</div>
<!-- 上一篇 下一篇  开始 -->
        <div class="navigation_single">
			<div class="alignleft"><?php previous_post_link('上一篇 %link') ?></div>
			<div class="alignright"><?php next_post_link('%link 下一篇') ?></div>
		</div>
<!-- 上一篇 下一篇  结束 -->


<!-- 相关日志  开始 -->
	<div class="relran">
    	<div class="relran_cont">
			<?php wp_related_posts(); ?>
        </div>
        
        <div class="relran_cont">
        	<h3>随机文章</h3>
			<ul>
				<?php
                $randposts = $wpdb->get_results('SELECT p.ID, p.post_title, rand()*p1.id AS o_id FROM ' . $wpdb->posts . ' AS p JOIN ( SELECT MAX(ID) AS id FROM ' . $wpdb->posts . ' WHERE post_type="post" AND post_status="publish") AS p1 WHERE p.post_type="post"  AND p.post_status="publish" ORDER BY o_id LIMIT 10');
                foreach($randposts as $randpost) {
                    echo('<li><a href="' . get_permalink($randpost->ID) . '" title="' . $randpost->post_title . '">' . $randpost->post_title . '</a></li>');
                }
                ?>
        	</ul>
        </div>
        
    </div>

<!-- 相关日志 随机日志结束 -->

<#include "comments.ftl">

			</div>

		<?php endwhile; ?>

	<#else>
		<div class="post">
		<h2 class="center search">抱歉,没有找到合适的文章.</h2>
		<p class="center">请您<a href="<?php echo get_settings('home'); ?>">返回首页<?php echo $langblog;?></a>或在搜索中查找您所需的信息.带来不便,敬请谅解!</p>

        </div>
	</#if>

	</div>
<!-- 正文 结束 -->
    
</div>
<!-- 左侧 结束 -->  

<!-- 右侧 开始 -->
<div class="con_right">
<#include "topbar.ftl">
<#include "sidebar.ftl">


</div>

<br clear="all" />

<#include "footer.ftl">

</div>
<!-- 右侧 结束 -->
</body>

</html>