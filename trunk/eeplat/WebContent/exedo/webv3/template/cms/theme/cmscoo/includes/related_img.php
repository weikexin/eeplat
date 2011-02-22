<?php if (get_theme_mod('showr_img') == 'Yes') { ?>
<div class="related_img">
<?php
  $backup = $post; 
  $tags = wp_get_post_tags($post->ID);
  $tagIDs = array();
  if ($tags) {
	echo '<ul>';
    $tagcount = count($tags);
    for ($i = 0; $i < $tagcount; $i++) {
      $tagIDs[$i] = $tags[$i]->term_id;
    }
    $args=array(
      'tag__in' => $tagIDs,
      'post__not_in' => array($post->ID),
      'showposts'=>12,
      'caller_get_posts'=>1
    );
    $my_query = new WP_Query($args);
    if( $my_query->have_posts() ) {
      while ($my_query->have_posts()) : $my_query->the_post(); ?>
		<div class="related_thumbnail">
		<?php if ( get_post_meta($post->ID, 'thumbnail', true) ) : ?>
			<?php $image = get_post_meta($post->ID, 'thumbnail', true); ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php echo $image; ?>" alt="<?php the_title(); ?>"/></a>
			<?php else: ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php bloginfo('template_directory'); ?>/images/random/tb<?php echo rand(1,20)?>.jpg" alt="<?php the_title(); ?>" /></a>
			<?php endif; ?>
		</div>
      <?php endwhile;
	  	echo '</ul>';
    } else { ?>
		<!-- 没有相关文章显示随机文章 -->
<ul>
	<?php
	query_posts(array('orderby' => 'rand', 'showposts' => 12));
	if (have_posts()) :
	while (have_posts()) : the_post();?>
		<div class="related_thumbnail">
		<?php if ( get_post_meta($post->ID, 'thumbnail', true) ) : ?>
			<?php $image = get_post_meta($post->ID, 'thumbnail', true); ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php echo $image; ?>" alt="<?php the_title(); ?>"/></a>
			<?php else: ?>
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><img src="<?php bloginfo('template_directory'); ?>/images/random/tb<?php echo rand(1,3)?>.jpg" alt="<?php the_title(); ?>" /></a>
			<?php endif; ?>
		</div>
	<?php endwhile;endif; ?>
</ul>
<?php }
  }
  $post = $backup;
  wp_reset_query();
?>

</div>
	<?php } else { ?>
	<?php } ?>