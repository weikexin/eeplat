<?php get_header(); ?>

<!-- BEGIN content -->
<div id="content">

	<?php $post = $posts[0]; // Hack. Set $post so that the_date() works. ?>
	<?php /* If this is a category archive */ if (is_category()) { ?>
	<h2 class="title">存档于 <strong><?php single_cat_title(); ?></strong> 分类</h2>
	<?php /* If this is a tag archive */ } elseif( is_tag() ) { ?>
	<h2 class="title">标签存档 <strong><?php single_tag_title(); ?></strong></h2>
	<?php /* If this is a daily archive */ } elseif (is_day()) { ?>
	<h2 class="title">存档于 <?php the_time('F jS, Y'); ?></h2>
	<?php /* If this is a monthly archive */ } elseif (is_month()) { ?>
	<h2 class="title">存档于 <?php the_time('F, Y'); ?></h2>
	<?php /* If this is a yearly archive */ } elseif (is_year()) { ?>
	<h2 class="title">存档于 <?php the_time('Y'); ?></h2>
	<?php /* If this is an 作者存档 */ } elseif (is_author()) { ?>
	<h2 class="title">作者存档</h2>
	<?php /* If this is a paged archive */ } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
	<h2 class="title">博客存档</h2>
	<?php } ?>

	<?php if (have_posts()) : the_post(); ?>

	<!-- BEGIN latest -->
	<div class="latest">
	
		<!-- begin post -->
		<div class="post singlepost">
		<h2><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></h2>
		<p class="details">作者：<?php the_author_posts_link(); ?> &nbsp;&nbsp;发表时间：<?php the_time('F j, Y') ?>&nbsp;&nbsp;&nbsp;浏览：<?php if(function_exists('the_views')) { the_views(); } ?><?php edit_post_link(' Edit','',''); ?></p>
		<?php the_content(''); ?><?php link_pages('<p><strong>内容分页:</strong> ', '</p>', 'number'); ?>
		<div class="details2">
			<?php the_tags('<p class="l"><img src="/pic/xing.gif" />&nbsp;标签：', ', ', '</p>'); ?>
			<p class="r"><img src="<?php bloginfo('template_directory'); ?>/images/bi.gif" />&nbsp;类别：<?php the_category(', '); ?></p>
		</div>

			<!--Next&previous_Nav-->
			<div class="newsPrevions">
				<span>上一篇：<?php previous_post_link('%link') ?></span>
				<span>下一篇：<?php next_post_link('%link') ?></span></div>

			<?php wp_related_posts(); ?><!--//相关日志，需要插件支持。没安装不显示、不影响主题使用-->
		</div><!--//post -->
		<!-- begin comments -->
		<div id="comments"><?php comments_template(); ?></div>
		<!-- end comments -->
	</div>
	<!-- END latest -->
	
	<?php endif; ?>

</div>
<!-- END content -->

<?php get_sidebar(); get_footer(); ?>
