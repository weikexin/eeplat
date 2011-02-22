<?php get_header(); ?>
<?php if(!is_paged()) { ?>
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
				<?php query_posts('showposts=1&cat=1'); ?><!-- 后台新建1个"头条"分类 showposts 输出文章的“数目”， “cat=头条分类ID号”，输出指定分类的文章 -->
					<?php while (have_posts()) : the_post(); ?>
						<h4><a href="<?php the_permalink() ?>" title="<?php the_title(); ?>" class="a_blue"><?php the_title(); ?></a></h4>
						<?php the_excerpt(); ?>
					<?php endwhile; ?>
      	</div>
        	
			<?php query_posts('showposts=5&cat=1&offset=1'); ?><!-- showposts 输出文章的“数目”， “cat=分类ID号”，输出指定分类的文章 -->
            <ul>
               <?php while (have_posts()) : the_post(); ?><!-- Loop 开始 -->
               <li><span class="titlel titlel2"><a href="<?php the_permalink() ?>" rel="bookmark" class="title"><?php the_title(); ?></a></span><span class="timer"><?php the_time('m-d'); ?></span></li><!-- 由上述条件指定的文章标题的列表 -->
               <?php endwhile; ?><!-- Loop 结束 -->
            </ul>
    </div>
    <!-- 最新头条 结束 -->
    <!-- 热评文章 [ 评论最多 ] 开始 -->
    <div id="recommend">
    	<h4>热评文章</h4>
        <ul>
        	<?php 
				if(function_exists(akpc_most_popular)) {
				akpc_most_popular($limit = '8', $before = '<li>', $after = '</li>'); 
					} 
				else {
				$result = $wpdb->get_results("SELECT comment_count,ID,post_title FROM $wpdb->posts ORDER BY comment_count DESC LIMIT 0 , 8");
				foreach ($result as $topten) {
				$postid = $topten->ID;
				$title = $topten->post_title;
				$commentcount = $topten->comment_count; 
				if ($commentcount != 0) { ?>
				<li><a href="<?php echo get_permalink($postid); ?>" title="<?php echo $title ?>"><?php echo $title ?></a></li>
			<?php } } } ?>  
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
<div class="leftad"><img src="<?php bloginfo('template_directory'); ?>/images/leftad.jpg" /></div>
<!-- 左侧通栏广告 结束-->
<?php if(!is_paged()) { ?>
	<div class="cat-posts">
		<?php include (TEMPLATEPATH . '/cats.php'); ?>
    </div>
<?php } ?>
<!-- 最新文章列表 开始 -->
<div class="entry">
	<h4>文章列表</h4>
   <?php if (have_posts()) : ?>
		<?php while (have_posts()) : the_post(); ?>
			<div class="post" id="post-<?php the_ID(); ?>" onmouseover="this.className='post post_border'" onmouseout="this.className='post'">
				<h2><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php the_title(); ?></a></h2>
				<div class="small_desc"><?php the_author_posts_link(); ?> 发表于 <?php the_time('Y-m-d H:i'); ?> 阅读:<?php if(function_exists('the_views')) { the_views(); } ?></div>
					<div class="postbg"><?php the_content(''); ?></div>
						<div class="postother">
							分类: <?php the_category(', ') ?> | 评论数: <?php comments_popup_link('0 ', '1 ', '% '); ?> | <a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><strong class="f14px">阅读全文</strong></a>
						</div>
			</div>
		<?php endwhile; ?>
	<?php else : ?>
		<!-- 无文章提示 -->
		<div class="post">
			<h2 class="center search">抱歉,没有找到合适的文章.</h2>
			<p class="center">请您<a href="<?php echo get_settings('home'); ?>">返回首页<?php echo $langblog;?></a>或在搜索中查找您所需的信息.带来不便,敬请谅解!</p>
        </div>
	<?php endif; ?>
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
<?php include (TEMPLATEPATH . '/topbar.php'); ?>
<?php include (TEMPLATEPATH . '/sidebar.php'); ?>
</div>
<!-- 右侧 结束 -->

<!-- 友情链接 开始 -->
<div id="footer">
	<h2>友情链接</h2>
	<ul class="flink">
		<?php get_links('', '<li>', '</li>', ' ', FALSE, 'id', FALSE, FALSE, -1, FALSE); ?>
	</ul>
</div>
<!-- 友情链接 结束 -->

<?php get_footer(); ?>
</div>
</body>
</html>