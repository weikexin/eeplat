<?php
	if (!empty($_SERVER['SCRIPT_FILENAME']) && 'comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
		die ('请不要多次刷新. 谢谢!');

	if ( post_password_required() ) { ?>
		<p class="nocomments">文章已加密. 请输入密码后继续阅读.</p>
	<?php
		return;
	}
?>

<!-- You can start editing here. -->
<div id="commentsbox">
<?php if ( have_comments() ) : ?>
	<h3 id="comments"><?php comments_number('0 条评论', '1 条评论', '% 条评论' );?></h3>
	<ol class="commentlist">
	<?php wp_list_comments(); ?>
	</ol>

	<div class="comment-nav">
		<div class="alignleft"><?php previous_comments_link() ?></div>
		<div class="alignright"><?php next_comments_link() ?></div>
	</div>
<?php else : // this is displayed if there are no comments so far ?>
	<?php if ( comments_open() ) : ?>
		<!-- If comments are open, but there are no comments. -->
		<p>暂时还没有评论.</p>
	 	<?php else : // comments are closed ?>
		<!-- If comments are closed. -->
		<p>评论已关闭.</p>
	<?php endif; ?>
<?php endif; ?>


<?php if ( comments_open() ) : ?>
    <div id="comment-form">
        <div id="respond">
        
            <div class="cancel-comment-reply">
                <?php cancel_comment_reply_link(); ?>
            </div>
        
        
        <?php if ( get_option('comment_registration') && !is_user_logged_in() ) : ?>
        <p>请 <a href="<?php echo wp_login_url( get_permalink() ); ?>">登陆</a> 后发表评论.</p>
        <?php else : ?>
        
        <form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">
        
        <?php if ( is_user_logged_in() ) : ?>
        
        <p>您好，<a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a> . <a href="<?php echo wp_logout_url(get_permalink()); ?>" title="退出此帐号">退出 &raquo;</a></p>
        
        <?php else : ?>
        <label for="author"><strong>名字<?php if ($req) echo "( 必填 )"; ?></strong>
        <input type="text" name="author" id="author" value="<?php echo esc_attr($comment_author); ?>" size="22" tabindex="1" <?php if ($req) echo "aria-required='true'"; ?> /></label>
        
        <label for="email"><strong>邮箱<?php if ($req) echo "( 必填 )"; ?></strong>
        <input type="text" name="email" id="email" value="<?php echo esc_attr($comment_author_email); ?>" size="22" tabindex="2" <?php if ($req) echo "aria-required='true'"; ?> /></label>
        
        <label for="url"><strong>网站</strong>
        <input type="text" name="url" id="url" value="<?php echo esc_attr($comment_author_url); ?>" size="22" tabindex="3" /></label>
        
        
        <?php endif; ?>
        
        <textarea name="comment" id="comment" cols="100%" rows="8" tabindex="4"></textarea><br />
        
        <input name="submit" type="submit" id="commentSubmit" tabindex="5" value="提 交" />
        <?php comment_id_fields(); ?>
        <?php do_action('comment_form', $post->ID); ?>
        
        </form>
        
        <?php endif; // If registration required and not logged in ?>
        </div>
    </div>

<?php endif; // if you delete this the sky will fall on your head ?>
</div>