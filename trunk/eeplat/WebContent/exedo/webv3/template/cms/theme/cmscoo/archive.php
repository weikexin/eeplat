<?php get_header(); ?>
<!--Banner-->
<div class="page_ps">
现在位置 ：<a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞ <?php the_category(', ') ?></div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
          <ul class="listone" >
		  <?php while ( have_posts() ) : the_post();  ?>
		  <li id="item_<?php the_ID(); ?>">
		  <h5>· <a href="<?php the_permalink() ?>" rel="bookmark" title="详细阅读 <?php the_title_attribute(); ?>"><?php the_title(); ?></a></h5>
		  <p><a href="<?php the_permalink() ?>" rel="bookmark" title="详细阅读 <?php the_title_attribute(); ?>"><?php echo mb_strimwidth(strip_tags(apply_filters('the_content', $post->post_content)), 0, 150,".."); ?></a></p>
		  </li>
		
		  <?php endwhile; ?>
	
		 </ul>
		
 	<!-- navigation -->
    <div class="navigation"><?php pagination($query_string); ?></div>
 	<!-- end: navigation -->
  </div>
  <!--left page end-->
  <!--right page star-->
  <div class="p_rightbar">
    <?php get_sidebar(); ?>
  <div class="clear"></div>
  </div>
  <!--Right End -->
<div class="clear"></div>
</div>
<!--Content End-->

<?php get_footer(); ?>
