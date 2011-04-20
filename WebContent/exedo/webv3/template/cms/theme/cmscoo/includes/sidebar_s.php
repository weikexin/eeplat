<div id="sidebar">

<div class="tab">
	<ul id=drawer>
  		<li><a href="">相关文章</a>
			<ul>
				<ol id="related">
					<?php
					 $tags = wp_get_post_tags($post->ID);
					$tagIDs = array();
					if ($tags) {
 					$tagcount = count($tags);
					for ($i = 0; $i < $tagcount; $i++) {
					$tagIDs[$i] = $tags[$i]->term_id;
					}
					$args=array(
					'tag__in' => $tagIDs,
					'post__not_in' => array($post->ID),
					'showposts'=>10,
					'caller_get_posts'=>1
					);
					$my_query = new WP_Query($args);
					if( $my_query->have_posts() ) {
					while ($my_query->have_posts()) : $my_query->the_post(); ?>
					<li><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title_attribute(); ?>"><?php echo cut_str($post->post_title,32); ?></a> </li>
					<?php endwhile;
					}
					}
					?>
				</ol>
			</ul>

		<li><a href="">最热文章</a> 
			<ul>
				<ol id="hotarticles"><?php $result = $wpdb->get_results("SELECT comment_count,ID,post_title FROM $wpdb->posts ORDER BY comment_count DESC LIMIT 0 , 10");
					foreach ($result as $post) {
					setup_postdata($post);
					$postid = $post->ID;
					$title = $post->post_title;
					$commentcount = $post->comment_count;
					if ($commentcount != 0) { ?>
					<li><a href="<?php echo get_permalink($postid); ?>" title="<?php echo $title ?>">
					<?php echo cut_str($post->post_title,32); ?></a> </li>
					<?php } } ?>
				</ol> 
			</ul>

		<li><a href="">最新文章</a> 
			<ul>
				<ol id="newarticles">
					<?php $myposts = get_posts('numberposts=10&offset=0');foreach($myposts as $post) :?>
					<a href="<?php the_permalink(); ?>" rel="bookmark" title="详细阅读 <?php the_title_attribute(); ?>"><?php echo cut_str($post->post_title,32); ?></a>
					<?php endforeach; ?>
				</ol>
			</ul>
		</li>
	</ul>
</div>
  <!-- end: tab -->
  
<div class="clear"></div>

<div class="s_category"><?php include('s_category.php'); ?></div>
<div class="ad"><?php include('ads.php'); ?></div>
<div class="comments"><?php include('r_comments.php'); ?></div>
<div class="clear"></div>
<div class="widget">
	<?php if ( !function_exists('dynamic_sidebar') || !dynamic_sidebar('sidebar Widget') ) : ?>
    <?php endif; ?>
</div>
  <!-- end: widget -->
</div>