<?php get_header(); ?>
<!--Header End-->
<!--Banner-->
<div class="page_ps">
现在的位置: <a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞<?php the_category(', ') ?>＞正文<!-- <?php the_title();?> --></div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
<!--left page star-->
  <div class="P_left">
    <H3 class="Title"><?php the_title(); ?></H3>
    <div class="t_clie">发表于：<?php the_time('Y年m月d日') ?>  分类：<?php the_category(', ') ?>  <?php comments_popup_link('添加评论', '1条评论', '% 条评论'); ?>  <?php if(function_exists(the_views)) { the_views(' 次浏览', true);}?>  <?php edit_post_link('<span class="edita">&nbsp&nbsp&nbsp&nbsp&nbsp</span>', '  ', '  '); ?></div>
    <div class="P_content">
      <?php the_content('Read more...'); ?>
	  <?php wp_link_pages(array('before' => '<p><strong>分页:</strong> ', 'after' => '</p>', 'next_or_number' => 'number')); ?>
        固定链接: <a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><?php the_permalink() ?> | <?php bloginfo('name');?></a>
    </div>
    <!-- end: entrymeta -->
		<div class="entrymeta">
			<div class="authorbio">
				<div class="author_pic">
					<?php echo get_avatar( get_the_author_email(), '48' ); ?>				</div>
				<div class="author_text">
					<h4>作者: <span><?php the_author_posts_link('namefl'); ?></span></h4>
					<?php the_author_description(); ?>
			  </div>
		</div>
		<span class="copyright">
			该日志由 <?php the_author() ?> 于<?php the_time('Y年m月d日') ?>发表在<?php the_category(', ') ?>分类下，
			<?php if (('open' == $post-> comment_status) && ('open' == $post->ping_status)) {?>
			你可以<a href="#respond">发表评论</a>，并在保留<a href="<?php the_permalink() ?>" rel="bookmark">原文地址</a>及作者的情况下<a href="<?php trackback_url(); ?>" rel="trackback">引用</a>到你的网站或博客。
			<?php } elseif (('open' == $post-> comment_status) && !('open' == $post->ping_status)) { ?>
			通告目前不可用，你可以至底部留下评论。
			<?php } ?><br/>
			转载请注明: <a href="<?php the_permalink() ?>" rel="bookmark" title="本文固定链接 <?php the_permalink() ?>"><?php the_title(); ?> | <?php bloginfo('name');?></a><br/>
			<span class="content_tag"><?php the_tags('标签: ', ', ', ''); ?></span>	
		</span>
		<div class="clear"></div>
		</div>
		<!-- end: entrymeta -->
        <div class="spostinfo">			
		<?php previous_post_link('【上篇】%link') ?><br/><?php next_post_link('【下篇】%link') ?>	</div>
	<!-- relatedposts -->
	<div class="entry_b">
	<?php include('includes/related.php'); ?>
	<div class="related_img">
ad
</div>
	<div class="clear"></div>
	</div>
	<?php comments_template(); ?>
    
    <!--PingLun End-->
    <?php endwhile; else: ?>
	<?php endif; ?>
    <div class="clear"></div>
  </div>
  <!--left page end-->
  <!--right page star-->
  <div class="p_rightbar">
    <?php get_sidebar(); ?>
  <!--Right End -->
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
<!--Content End-->

<!--Footer-->
<?php get_footer(); ?>