<?php get_header(); ?>

<!-- 左侧 开始 -->
<div class="con_left">

<!-- 搜索结果 开始 -->
<div class="entry_list">

	<?php if (have_posts()) : ?>
    
		<div class="postnavi"><div class="site-navi">搜索到符合条件的文章列表</div></div>
        
	<?php while (have_posts()) : the_post(); ?>

			<div class="post" id="post-<?php the_ID(); ?>">

				<h2><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php the_title(); ?></a></h2>
				<div class="small_desc"><?php the_author() ?> 发表于 <?php the_time('Y-m-d H:i'); ?> 浏览次数:<?php if(function_exists('the_views')) { the_views(); } ?> 来源：<a href="<?php $values = get_post_custom_values("fromsiteurl"); echo $values[0]; ?>"><?php $values = get_post_custom_values("fromsitename"); echo $values[0]; ?></a> <?php edit_post_link('Edit', '', ''); ?></div>

					<div class="postbg"><?php the_content(''); ?></div>

<div class="postother">关键字:<?php the_tags(' ', ', ', ''); ?> | 分类: <?php the_category(', ') ?> | 评论数: <?php comments_popup_link('0 ', '1 ', '% '); ?> | <a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><strong>阅读全文</strong></a></div>

			</div>

		<?php endwhile; ?>

	<?php else : ?>

		<div class="post">

		<h2 class="center search">抱歉,没有找到合适的文章.</h2>

		<p class="center">请您<a href="<?php echo get_settings('home'); ?>">返回首页<?php echo $langblog;?></a>或在搜索中查找您所需的信息.带来不便,敬请谅解!</p>

        </div>

	<?php endif; ?>

</div>
<!-- 搜索结果 结束 -->

<div class="spacebox"></div>
<!-- 翻页导航 开始 -->
<div class="wp-pagenavi pageNavi">
    <?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } ?>
</div>
<!-- 翻页导航 结束 -->

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