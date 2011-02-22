<?php // Do not delete these lines
	if ('comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
		die ('Please do not load this page directly. Thanks!');

	if (!empty($post->post_password)) { // if there's a password
		if ($_COOKIE['wp-postpass_' . COOKIEHASH] != $post->post_password) {  // and it doesn't match the cookie
			?>

			<p class="nocomments">This post is password protected. Enter the password to view comments.</p>

			<?php
			return;
		}
	}

	/* This variable is for alternating comment background */
	$oddcomment = 'class="alt" ';
?>

<!-- You can start editing here. -->

<?php if ($comments) : ?>
	<h3 id="comments"><strong><?php comments_number('赶紧抢个沙发做！', '才1个评论', '已经有% 个评论' );?></strong></h3>

	<ol class="commentlist">
	<?php $cmntCnt = 1; ?>
	<?php foreach ($comments as $comment) : ?>

		<li <?php echo $oddcomment; ?>id="comment-<?php comment_ID() ?>">
			<div class="gravatar"><a href="<?php comment_author_url(); ?>" target="_blank"><?php if(function_exists("get_avatar")) echo get_avatar( get_comment_author_email(), '48', 'http://i3.6.cn/cvbnm/e6/72/01/57edccd8dd622b35ab7e4ea33cc2e0cb.jpg' ); ?></a>
            <label class="floor"><?php echo($cmntCnt++); ?>楼</label></div>
            <small class="commentmetadata"><?php comment_date('F jS, Y') ?> at <?php comment_time() ?> <?php edit_comment_link('edit','&nbsp;&nbsp;',''); ?></small>
            <strong><a href="<?php comment_author_url(); ?>" target="_blank"><?php comment_author(); ?></a></strong> 说:
			<?php if ($comment->comment_approved == '0') : ?>
			<em>您的评论要等管理员审核后才能显示！</em>
			<?php endif; ?>
            
			<?php comment_text() ?>

		</li>

	<?php
		/* Changes every other comment to a different class */
		$oddcomment = ( empty( $oddcomment ) ) ? 'class="alt" ' : '';
	?>

	<?php endforeach; /* end for each comment */ ?>

	</ol>

 <?php else : // this is displayed if there are no comments so far ?>

	<?php if ('open' == $post->comment_status) : ?>
		<!-- If comments are open, but there are no comments. -->

	 <?php else : // comments are closed ?>
		<!-- If comments are closed. -->
		<p class="nocomments">Comments are closed.</p>

	<?php endif; ?>
<?php endif; ?>


<?php if ('open' == $post->comment_status) : ?>

<h3 id="respond">我要评论</h3>

<div class="spacebox"></div>
<?php if ( get_option('comment_registration') && !$user_ID ) : ?>
<p>你必须 <a href="<?php echo get_option('siteurl'); ?>/wp-login.php?redirect_to=<?php echo urlencode(get_permalink()); ?>">登录后</a> 才能对文章进行评论！</p>
<?php else : ?>

<form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">

<?php if ( $user_ID ) : ?>

<p>您现在是以 <a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a> 的身份登录,<a href="<?php echo wp_logout_url(get_permalink()) ?>" title="退出系统"> 点击退出系统 &raquo;</a></p>

<?php else : ?>

<p><label for="author">您的网名: </label><input type="text" name="author" id="author" value="<?php echo $comment_author; ?>" size="22" tabindex="1" /> *</p>

<p><label for="email">电子邮件: </label><input type="text" name="email" id="email" value="<?php echo $comment_author_email; ?>" size="22" tabindex="2" /> * 绝不会泄露</p>

<p><label for="url">你的网址: </label><input type="text" name="url" id="url" value="<?php echo $comment_author_url; ?>" size="22" tabindex="3" /></p>

<?php endif; ?>

<!--<p><small><strong>XHTML:</strong> You can use these tags: <code><?php echo allowed_tags(); ?></code></small></p>-->

<p><label for="content">评论内容：</label><br /><textarea name="comment" id="comment" cols="100%" rows="10" tabindex="4" onkeyup="javascript:return ctrlEnter(event);"></textarea></p>

<p id="but_submit"><input name="submit" type="submit" id="submit" tabindex="5" value="提交评论" />
<input type="hidden" name="comment_post_ID" value="<?php echo $id; ?>" /><label id="ctrl_enter">(Ctrl+Enter快捷回复)</label>
</p>
<?php do_action('comment_form', $post->ID); ?>

</form>

<?php endif; // If registration required and not logged in ?>

<?php endif; // if you delete this the sky will fall on your head ?>

<script type="text/javascript">
	function ctrlEnter(A){var B=A?A:window.event;if(B.ctrlKey&&B.keyCode==13){document.getElementById("submit").click()}}
</script>