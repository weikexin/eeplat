	<div class="thumbnail">
		<?php if ( get_post_meta($post->ID, 'thumbnail', true) ) : ?>
			<?php $image = get_post_meta($post->ID, 'thumbnail', true); ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php echo $image; ?>" alt="<?php the_title(); ?>"/></a>
			<?php else: ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php bloginfo('template_directory'); ?>/timthumb.php?src=<?php echo catch_that_image(); ?>&w=140&h=100&zc=1"></a>
			<?php endif; ?>
	</div>
