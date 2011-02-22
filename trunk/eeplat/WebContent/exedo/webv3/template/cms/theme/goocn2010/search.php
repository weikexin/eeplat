<?php get_header(); ?>

<!-- BEGIN content -->
<div id="content">

	<?php if (have_posts()) : while(have_posts()) : the_post(); ?>

	<!-- BEGIN latest -->
	<div class="latest">
	
		<!-- begin post -->
		<div class="post">
		<h2><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></h2>
		<p class="details">作者：<?php the_author_posts_link(); ?> 发表时间：<?php the_time('F j, Y') ?> &nbsp;|&nbsp; <?php comments_popup_link('没有评论', '1 条评论', '% 条评论'); ?></p>
		<?php the_content(''); ?>
		<div class="details2">
			<?php the_tags('<p class="l">标签：', ', ', '</p>'); ?>
			<p class="r">类别：<?php the_category(', '); ?></p>
		</div>
		</div>
		<!-- end post -->
	
	</div>
	<!-- END latest -->
	
	<?php endwhile; endif; ?>
	
	<?php if (have_posts()) : ?>
	<p class="postnav">
		<?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } else { ?>
		<?php next_posts_link('&laquo; 上一页'); ?> &nbsp; 
		<?php previous_posts_link('下一页 &raquo;'); ?>
        <?php } ?>
	</p>
	<?php endif; ?>

</div>
<!-- END content -->

<?php get_sidebar(); get_footer(); ?>
