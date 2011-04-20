<?php get_header(); ?>
<!--Banner-->
<div class="page_ps">
现在位置 ：<a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞未知页面</div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
          <ul class="listone">
		  <h2>对不起！您找的文章可能已删除!</h2>
	
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
