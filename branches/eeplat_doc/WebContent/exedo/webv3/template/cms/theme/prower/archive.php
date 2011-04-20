<?php get_header(); ?>
<div id="mainBox">
	<div id="content">

		<?php if (have_posts()) : ?>

		 <?php $post = $posts[0]; // Hack. Set $post so that the_date() works. ?>
<?php /* If this is a category archive */ if (is_category()) { ?>
		<h3 class="postHeader">日志分类：<?php single_cat_title(); ?></h3>

 	  <?php /* If this is a daily archive */ } elseif (is_day()) { ?>
		<h3 class="postHeader">日志存档： <?php the_time('Y-m-d'); ?></h3>

	 <?php /* If this is a monthly archive */ } elseif (is_month()) { ?>
		<h3 class="postHeader">日志存档：<?php the_time('m, Y'); ?></h3>

		<?php /* If this is a yearly archive */ } elseif (is_year()) { ?>
		<h3 class="postHeader">日志存档： <?php the_time('Y'); ?></h3>

	  <?php /* If this is an author archive */ } elseif (is_author()) { ?>
		<h3 class="postHeader">作者档案：</h3>

		<?php /* If this is a paged archive */ } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
		<h3 class="postHeader">博客档案：</h3>

		<?php } ?>


						
		<?php while (have_posts()) : the_post(); ?>
		<div class="entry">
			<div id="post-<?php the_ID(); ?>">
				<div class="entryHeader">
                    <h2 class="alignLeft">
                        <a href="<?php the_permalink() ?>" rel="bookmark" title="链接到<?php the_title(); ?>"><?php the_title(); ?></a>
                    </h2>
                    <div class="time" title="<?php the_time('Y-m-d'); ?>"><?php edit_post_link('编辑', '', ' | '); ?><?php unset($previousday); printf(__('%1$s %2$s'), the_date('', '', '', false), get_the_time()) ?>&nbsp;&nbsp;|&nbsp;&nbsp;分类：<?php the_category(',') ?><!--<?php the_author() ?>--></div>
                </div>
				<div class="content"><?php the_content('<span class="read">全文阅读 &raquo;</span>'); ?></div>
				<div class="postmetadata">
                	<span class="tag"><?php the_tags('标签：','、') ?></span>
                    <span class="views"><?php if(function_exists('the_views')) { the_views(); } ?>&nbsp;&nbsp;|&nbsp;&nbsp;<?php comments_popup_link('发表评论', '1条评论', '% 条评论'); ?></span>
                </div> 
			</div>
        </div>

		<?php endwhile; ?>

		<?php
           if (function_exists('wp_pagebar'))
              wp_pagebar();
          ?>

	<?php else : ?>
				<div class="entry">
		<h2 class="center">Not Found</h2>
</div>

	<?php endif; ?>

	</div>

<?php get_sidebar(); ?>

<?php get_footer(); ?>