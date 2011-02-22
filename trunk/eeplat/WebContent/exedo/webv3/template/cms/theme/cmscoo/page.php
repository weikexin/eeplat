<?php get_header(); ?>
<!--Banner-->
<div class="page_ps">
现在位置：<a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞<?php the_title(); ?></div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
  <?php if (have_posts()) : ?><?php while (have_posts()) : the_post(); ?>
  <div class="pageno">  <?php the_content('More &raquo;'); ?></div>

 <?php comments_template(); ?>
  <?php endwhile; ?><?php else : ?>
  <h2 class="center">Not Found</h2>
  <p class="center">Sorry, but you are looking for something that isn't here.</p>
  <?php include (TEMPLATEPATH . "/searchform.php"); ?>
  <?php endif; ?>
    <div class="clear"></div>
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
