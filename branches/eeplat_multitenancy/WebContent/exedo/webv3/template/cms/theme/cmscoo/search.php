<?php get_header(); ?>
<!--Banner-->
<div class="page_ps">
现在位置 ：现在位置：<a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞搜索结果</div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
          <ul class="listone">
		  <?php if (have_posts()) : while (have_posts()) : the_post(); ?>
		  <li>
		  <h5><a href="<?php the_permalink() ?>" rel="bookmark" title="详细阅读 <?php the_title_attribute(); ?>"><?php the_title(); ?></a></h5>
		  <p><a href="<?php the_permalink() ?>" rel="bookmark" title="详细阅读 <?php the_title_attribute(); ?>"><?php echo mb_strimwidth(strip_tags(apply_filters('the_content', $post->post_content)), 0, 150,"..."); ?></a></p>
		  </li>
		 <?php endwhile; else: ?>
	<h3 class="center">非常抱歉，无法搜索到与之相匹配的信息。</h3>
<?php endif; ?>
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
