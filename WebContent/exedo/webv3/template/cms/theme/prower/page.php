<?php get_header(); ?>
<div id="mainBox">
	<div id="content">
	<div class="entry">
		<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
		<div id="post-<?php the_ID(); ?>">		
				<div class="content"><?php the_content('<p class="serif">Read the rest of this page &raquo;</p>'); ?></div>
				<?php wp_link_pages(array('before' => '<p><strong>Pages:</strong> ', 'after' => '</p>', 'next_or_number' => 'number')); ?>

			</div>
		</div>
            <div class="entry">
                <?php comments_template(); ?>
            </div>
		<?php endwhile; endif; ?>
	</div>

<?php get_sidebar(); ?>

<?php get_footer(); ?>