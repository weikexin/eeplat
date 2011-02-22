
	<div class="thumbnail">
		<?php if ( get_post_meta($post->ID, 'thumbnail', true) ) : ?>
			<?php $image = get_post_meta($post->ID, 'thumbnail', true); ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php echo $image; ?>" alt="<?php the_title(); ?>"/></a>
			<?php else: ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php bloginfo('template_directory'); ?>/images/random/tb<?php echo rand(1,20)?>.jpg" alt="<?php the_title(); ?>" /></a>
			<?php endif; ?>
	</div>
