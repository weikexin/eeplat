	
				<?php
					query_posts(array('orderby' => 'rand', 'showposts' => 7));
					if (have_posts()) :
					while (have_posts()) : the_post();?>
					<li>· <a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php echo mb_strimwidth(get_the_title(), 0, 40, '…'); ?></a></li>
					<?php endwhile;endif; ?>
		