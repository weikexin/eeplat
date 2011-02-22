<#include "header.ftl">

<!-- 顶部自定义 开始 -->
<div id="topnews">
	<!-- 图片幻灯 开始 -->
		<div id="picswitch">
			<?php flash(); ?>
		</div>
	<!-- 图片幻灯 结束 -->
<div id="txtnews">
	<!-- 最新头条 开始 -->
	<div id="toplist">
    	<div id="topdetail">
					<@list_posts>
						<h4><a href="${dataMap.posts_url}" title="${dataMap.posts_title}" class="a_blue">${dataMap.posts_title}</a></h4>
						${dataMap.posts_except}
					</@list_posts>
      	</div>
        	
            <ul>
              <@list_posts>
               <li><span class="titlel titlel2"><a href="${dataMap.posts_url}" rel="bookmark" class="title">${dataMap.posts_title}</a></span><span class="timer">${dataMap.posts_date}</span></li><!-- 由上述条件指定的文章标题的列表 -->
              </@list_posts>
            </ul>
    </div>
    <!-- 最新头条 结束 -->
    <!-- 热评文章 [ 评论最多 ] 开始 -->
    <div id="recommend">
    	<h4>热评文章</h4>
        <ul>
        	
				<li><a href="<?php echo get_permalink($postid); ?>" title="<?php echo $title ?>"><?php echo $title ?></a></li>
			
        </ul>
    </div>
    <!-- 热评文章 [ 评论最多 ] 结束 -->
</div>
</div>
<?php } ?>
<!-- 顶部自定义 结束 -->
<!-- 左侧 开始 -->
<div class="con_left">
<!-- 左侧通栏广告 开始-->
<div class="leftad"><img src="images/leftad.jpg" /></div>
<!-- 左侧通栏广告 结束-->
<?php if(!is_paged()) { ?>
	<div class="cat-posts">
		<#include "cats.ftl">
    </div>
<?php } ?>
<!-- 最新文章列表 开始 -->
<div class="entry">
	<h4>文章列表</h4>
   <#if have_posts() >
   		<@list_posts>
			<div class="post" id="post-" onmouseover="this.className='post post_border'" onmouseout="this.className='post'">
				<h2><a href="${dataMap.posts_url}" rel="bookmark" title="${dataMap.posts_title}">${dataMap.posts_title}</a></h2>
				<div class="small_desc">${dataMap.posts_author!"admin"} 发表于 ${dataMap.posts_date} 阅读:</div>
					<div class="postbg">${dataMap.posts_except}</div>
						<div class="postother">
							分类: ${dataMap.posts_category} | 评论数:  | <a href="${dataMap.posts_url}" rel="bookmark" title="${dataMap.posts_title}"><strong class="f14px">阅读全文</strong></a>
						</div>
			</div>
		</@list_posts>
	<#else>
		<!-- 无文章提示 -->
		<div class="post">
			<h2 class="center search">抱歉,没有找到合适的文章.</h2>
			<p class="center">请您<a href="${bloginof('site_url')}">返回首页</a>或在搜索中查找您所需的信息.带来不便,敬请谅解!</p>
        </div>
	</#if>
</div>

<!-- 最新文章列表 结束 -->
<div class="spacebox"></div>
<!-- 翻页导航 开始 -->
<div class="wp-pagenavi pageNavi">
    <?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } 
			else { ?>

			<div class="right"><?php next_posts_link('Next Page &raquo;') ?></div>
			<div class="left"><?php previous_posts_link('&laquo; Previous Page') ?></div>
	<?php } ?>
</div>
<!-- 翻页导航 结束 -->

</div>
<!-- 左侧 结束 -->

<!-- 右侧 开始 -->
<div class="con_right">
<#include "topbar.ftl">
<#include "sidebar.ftl">
</div>
<!-- 右侧 结束 -->

<!-- 友情链接 开始 -->
<div id="footer">
	<h2>友情链接</h2>
	<ul class="flink">
		
	</ul>
</div>
<!-- 友情链接 结束 -->

<#include "footer.ftl">
</div>
</body>
</html>