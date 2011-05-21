<?php get_header(); ?>

<!-- 左侧 开始 -->
<div class="con_left">

<!-- 正文 开始 -->
<div class="entry_single">

	<?php if (have_posts()) : ?>
    	
        <div class="postnavi"><div class="site-navi">当前位置: <a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> > <?php the_category(', ') ?> > 文章正文</div></div>
        
		<?php while (have_posts()) : the_post(); ?>
			<div class="post" id="post-<?php the_ID(); ?>">
				<h2 class="mid_single"><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php the_title(); ?></a></h2>
				<div class="describe"><?php the_author_posts_link(); ?> 发表于 <?php the_time('Y-m-d H:i'); ?> | 来源：<a href="<?php echo get_post_meta($post->ID, "fromsiteurl", true); ?>"><?php echo get_post_meta($post->ID, "fromsitename", true); ?></a> | 阅读 <?php if(function_exists('the_views')) { the_views(); } ?> <?php edit_post_link('Edit', '', ''); ?></div>

					<div class="postbg"><?php the_content('Read more &raquo;'); ?></div>

<div class="postother">关键字:<?php the_tags(' ', ', ', ''); ?></div>
<!-- 上一篇 下一篇  开始 -->
        <div class="navigation_single">
			<div class="alignleft"><?php previous_post_link('上一篇 %link') ?></div>
			<div class="alignright"><?php next_post_link('%link 下一篇') ?></div>
		</div>
<!-- 上一篇 下一篇  结束 -->

<div id="subrss">喜欢<a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a>的文章，那就通过 <a href="<?php bloginfo('rss2_url'); ?>">RSS Feed</a> 功能订阅阅读吧！</div>

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

<?php comments_template(); ?>

			</div>

		<?php endwhile; ?>

	<?php else : ?>
		<div class="post">
		<h2 class="center search">抱歉,没有找到合适的文章.</h2>
		<p class="center">请您<a href="<?php echo get_settings('home'); ?>">返回首页<?php echo $langblog;?></a>或在搜索中查找您所需的信息.带来不便,敬请谅解!</p>

        </div>
	<?php endif; ?>

	</div>
<!-- 正文 结束 -->
    
</div>
<!-- 左侧 结束 -->  

<!-- 右侧 开始 -->
<div class="con_right">
<?php include (TEMPLATEPATH . '/topbar.php'); ?>

<?php include (TEMPLATEPATH . '/sidebar.php'); ?>

</div>

<br clear="all" />

<?php get_footer(); ?>

</div>
<!-- 右侧 结束 -->
</body>

</html>