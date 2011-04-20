<?php get_header(); ?>
<!--Banner-->
<div class="page_ps">
现在的位置: <a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞<?php the_title(); ?></div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
          <?php if (have_posts()) : ?><?php while (have_posts()) : the_post(); ?>
            <?php the_content('More &raquo;'); ?>
			<?php edit_post_link('<span class="edita">&nbsp&nbsp&nbsp&nbsp&nbsp</span>', '  ', '  '); ?>
			<?php
global $wpdb;
$sql = "SELECT DISTINCT ID, post_title, post_password, comment_ID, comment_post_ID, comment_author, comment_date_gmt, comment_approved, comment_type,comment_author_url,comment_author_email, SUBSTRING(comment_content,1,60) AS com_excerpt FROM $wpdb->comments LEFT OUTER JOIN $wpdb->posts ON ($wpdb->comments.comment_post_ID = $wpdb->posts.ID) WHERE comment_approved = '1' AND comment_type = '' AND post_password = '' AND user_id='0' ORDER BY comment_date_gmt DESC LIMIT 15";
$comments = $wpdb->get_results($sql);
$output = $pre_HTML;
foreach ($comments as $comment) {
$a= get_bloginfo('wpurl') .'/avatar/'.md5(strtolower($comment->comment_author_email)).'.jpg';
$output .= "\n<li><img src='". $a ."'/>$comment->comment_author:<br /><a href=\"" . get_permalink($comment->ID) ."#comment-" . $comment->comment_ID . "\" title=\"查看 " .$comment->post_title . "\">" . strip_tags($comment->com_excerpt)."</a></li>";
}
$output .= $post_HTML;
echo $output;
?> 
 <?php comments_template(); ?>
  <?php endwhile; ?><?php else : ?>
  <h2 >Not Found</h2>
  <p >Sorry, but you are looking for something that isn't here.</p>
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
