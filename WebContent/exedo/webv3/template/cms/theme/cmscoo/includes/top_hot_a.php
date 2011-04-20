	<div id="featured" class="clearfix">
		<?php $recent = new WP_Query("cat=".get_theme_mod('top_hot')."&showposts=4"); while($recent->have_posts()) : $recent->the_post();?>
		<div class="item grid_3">
			<?php if ( get_post_meta($post->ID, 'image', true) ) : ?>
			<?php $image = get_post_meta($post->ID, 'image', true); ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php echo $image; ?>" alt="<?php the_title(); ?>"/></a>
			<?php else: ?>
				<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php bloginfo('template_directory'); ?>/timthumb.php?src=<?php echo catch_that_image(); ?>&w=236&h=155&zc=1"></a>
			<?php endif; ?>
			<div class="top_box"><a href="<?php the_permalink(); ?>" rel="bookmark" title="<?php the_title_attribute(); ?>">详细内容</a></div>
			<div class="boxCaption">
				<h2><a href="<?php the_permalink(); ?>" title="Permalink to <?php the_title(); ?>"><?php the_title(); ?></a></h2>
			</div>		
		</div>
		<?php endwhile; ?>	
	</div>