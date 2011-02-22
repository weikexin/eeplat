<div class="sidebar">
	<div class="sidenews">
			<h2 class="sidetitl">最新日志</h2>
            	<?php query_posts('showposts=1'); ?><?php if (have_posts()) : while (have_posts()) : the_post(); ?>
                <dl>
                <dt class="newpic"><a href="<?php the_permalink(); ?>" title="<?php the_title(); ?>"><img src="<?php echo catch_that_image() ?>" alt="<?php the_title(); ?>"/></a></dt>
                <dd class="newtitl"><a href="<?php the_permalink(); ?>" title="<?php the_title(); ?>"><?php the_title(); ?></a></dt>
                <dd class="newentry"><?php echo mb_strimwidth(strip_tags(apply_filters('the_content', $post->post_content)), 0, 126,"&hellip;"); ?></dd>       
                </dl>
                <?php endwhile; endif; ?>
                <?php query_posts('showposts=5&offset=1'); ?>
				<ul>
					<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
					<li><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></li>
					<?php endwhile; endif; ?>
				</ul>
                
	</div>
    <?php include (TEMPLATEPATH . '/sponsors.php'); ?>
    <div class="sidebox">
          <h2 class="sidetitl">最热文章</h2>
            	<ul>
					<?php $result = $wpdb->get_results("SELECT comment_count,ID,post_title FROM $wpdb->posts ORDER BY comment_count DESC LIMIT 0 , 6"); 
                    foreach ($result as $post) { 
                    setup_postdata($post); 
                    $postid = $post->ID; 
                    $title = $post->post_title; 
                    $commentcount = $post->comment_count; 
                    if ($commentcount != 0) { ?> 
                    <li><a href="<?php echo get_permalink($postid); ?>" title="<?php echo $title ?>"> 
                    <?php echo $title ?> - <?php echo $commentcount ?>条评论</a> </li> 
                    <?php } } ?>
             	</ul>
     </div>
     <div class="sidebox">
     	<h2 class="sidetitl">最新留言</h2>
        <ul>
			<?php
			global $wpdb;
			$sql = "SELECT DISTINCT ID, post_title, post_password, comment_ID, comment_post_ID, comment_author, comment_date_gmt, comment_approved, comment_type,comment_author_url,comment_author_email, SUBSTRING(comment_content,1,38) AS com_excerpt FROM $wpdb->comments LEFT OUTER JOIN $wpdb->posts ON ($wpdb->comments.comment_post_ID = $wpdb->posts.ID) WHERE comment_approved = '1' AND comment_type = '' AND post_password = '' AND user_id='0' ORDER BY comment_date_gmt DESC LIMIT 6";
			$comments = $wpdb->get_results($sql);
			$output = $pre_HTML;
			foreach ($comments as $comment) {$output .= "\n<li>".get_avatar(get_comment_author_email(), 30).strip_tags($comment->comment_author).":<br />" . " <a href=\"" . get_permalink($comment->ID) ."#comment-" . $comment->comment_ID . "\" title=\"关于 " .$comment->post_title . "\">" . strip_tags($comment->com_excerpt)."&hellip; "."</a></li>";}
			$output .= $post_HTML;
			echo $output;
			?> 
        </ul>
     </div>
		<ul>
        	<?php if ( !function_exists('dynamic_sidebar') || !dynamic_sidebar('Sidebar') ) : ?>
			<?php endif; ?>
		</ul>
    </div>