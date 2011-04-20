<?php get_header(); ?>

<!-- 左侧 开始 -->
<div class="con_left">

<!-- 正文 开始 -->
<div class="entry_list">

	<?php if (have_posts()) : ?>
		<div class="postnavi"><div class="site-navi">当前位置: <a href="<?php echo get_settings('home'); ?>">菠萝油</a> > <?php the_title(); ?></div></div>
	<?php while (have_posts()) : the_post(); ?>
			<div class="post" id="post-<?php the_ID(); ?>">
				<h2 class="mid_single"><?php the_title(); ?></h2>
				<div class="describe"><?php the_author_posts_link(); ?> 发表于 <?php the_time('Y-m-d H:i'); ?> | 来源：<a href="<?php echo get_post_meta($post->ID, "fromsiteurl", true); ?>"><?php echo get_post_meta($post->ID, "fromsitename", true); ?></a> | 阅读 <?php if(function_exists('the_views')) { the_views(); } ?> <?php edit_post_link('Edit', '', ''); ?></div>
				<div class="postbg"><?php the_content('Read more &raquo;'); ?></div>

			</div>

		<?php endwhile; ?>

		<div class="navigation">
			<div class="alignleft"><?php next_posts_link('&laquo; Older Entries') ?></div>
			<div class="alignright"><?php previous_posts_link('Newer Entries &raquo;') ?></div>
		</div>

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