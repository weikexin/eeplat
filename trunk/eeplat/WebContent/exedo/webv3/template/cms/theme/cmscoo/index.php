<?php get_header(); ?>
<!--Header End-->
<!--Banner-->
<div class="banner">
   <div class="b_bar">
      <div class="B_border">
         <div class="B_left">
             <dl class="News">最近更新</dl>
             <ul class="newslist">
            <?php $myposts = get_posts('numberposts=8&offset=0');foreach($myposts as $post) :?>
					 <li>· <a href="<?php the_permalink(); ?>" rel="bookmark" title="详细阅读 <?php the_title_attribute(); ?>"><?php echo cut_str($post->post_title,40); ?></a></li>
					<?php endforeach; ?>
             </ul>
         </div>
        <div class="b_right"><a href="http://www.id78.com" target="_blank"><img src="http://www.cmscoo.com.cn/f.gif" Alt="电子商务外包"/></a></div>
      </div>
   </div>
</div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
    
<?php $display_categories = array(1,3,4,130,6,7,8,5); 
		foreach ($display_categories as $category) { ?>
<div class="P_category">
<?php query_posts("showposts=8&cat=$category")?>
<h2 class="P_c_one"><a href="<?php echo get_category_link($category);?>"><?php single_cat_title(); ?></a></h2>
    <ul class="p_news">
<?php while (have_posts()) : the_post(); ?>
    <li>· <a href="<?php the_permalink() ?>" title="<?php the_title(); ?>"><?php echo mb_strimwidth(get_the_title(), 0, 40, '…'); ?>
</a> </li>
<?php endwhile; ?>
    </ul>
    </div>
<?php } ?>
   <div class="clear"></div>
  </div>
  <!--left page end-->
  <!--right page star-->
 <?php get_sidebar(); ?>
  <!--Right End -->
<div class="clear"></div>
</div>
<!--Content End-->
<!--Link-->
<div class="Link">
<ul class='xoxo blogroll'>
 <?php
$result = $wpdb->get_results("SELECT link_url,link_name,link_target FROM $wpdb->links ORDER BY link_id DESC LIMIT 0 , 10");
foreach ($result as $link) {
					setup_postdata($link);
					$linkurl = $link->link_url;
					$linkname = $link->link_name;
					$linktarget = $link->link_target;
					?>
<li><a href="<?php echo $linkurl; ?>" title="<?php echo $linkname ?>" target="<?php echo $linktarget ?>"><?php echo $linkname ?></a></li><?php }?>
	</ul>
</div>
<!--Link End-->
<?php get_footer(); ?>