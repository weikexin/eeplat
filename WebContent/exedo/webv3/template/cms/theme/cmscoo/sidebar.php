 <div class="p_rightbar">
    <div class="p_right">
       <h3>随机文档</h3>
        <ul class="p_news">
   <!--<?php $result = $wpdb->get_results("SELECT comment_count,ID,post_title FROM $wpdb->posts ORDER BY comment_count DESC LIMIT 0 , 7");
					foreach ($result as $post) {
					setup_postdata($post);
					$postid = $post->ID;
					$title = $post->post_title;
					$commentcount = $post->comment_count;
					if ($commentcount != 0) { ?>
					<li><a href="<?php echo get_permalink($postid); ?>" title="<?php echo $title ?>">
					<?php echo mb_strimwidth(get_the_title(), 0, 32, '…'); ?></a> </li>
					<?php } } ?>
   -->
   <?php include('includes/random.php'); ?>
    </ul>
    </div>
      <!---AD-->
  <div class="p_right">
        <img src="<?php bloginfo('template_directory'); ?>/images/AD.gif" width="300" height="150" />
        
    </div>
  <!--AD END-->
  <div class="p_right">
       <h3>网站标签</h3>
        <ul class="p_news">
  <?php wp_tag_cloud('smallest=8&largest=16'); ?>
        </ul>
    </div>
    <div class="p_right">
      <dl>
        <?php include('includes/top_comment.php'); ?>
       </dl>
     <div class="clear"></div>
    </div>
  
<!--comments-->
  
    <div class="comments"><h3>最新评论</h3>
<div class="r_comments">
<ul>
<?php include('includes/r_comments.php'); ?>
<div class="clear"></div>
</ul>
</div>
<div class="clear"></div>
</div>
<!--Comment End-->
  <div class="clear"></div>
  </div>