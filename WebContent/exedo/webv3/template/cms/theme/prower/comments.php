<?php // Do not delete these lines
	if (!empty($_SERVER['SCRIPT_FILENAME']) && 'comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
		die ('Please do not load this page directly. Thanks!');

	if (!empty($post->post_password)) { // if there's a password
		if ($_COOKIE['wp-postpass_' . COOKIEHASH] != $post->post_password) {  // and it doesn't match the cookie
			?>
			<p class="nocomments">本文已启用密码保护。输入密码可以查看评论。</p>
			<?php
			return;
		}
	}

	/* This variable is for alternating comment background */
	$oddcomment = 'class="alt" ';
?>

<?php if ($comments) : ?>
	<h3 class="comments"><?php comments_number('没有评论', '1条评论', '%条评论' );?> 关于 &#8220;<?php the_title(); ?>&#8221;</h3>
	<ol class="commentList">
	<?php foreach ($comments as $comment) : ?>
		<li <?php echo $oddcomment; ?>id="comment-<?php comment_ID() ?>">
			<div class="userPic"><div class="gravatars"><?php echo get_avatar( $comment, 60 ); ?></div></div>
            <div class="commentText">
                <strong><?php comment_author_link() ?></strong> 发表于：
                <a href="#comment-<?php comment_ID() ?>" title=""></a><?php comment_date('F jS, Y ') ?> <?php comment_time() ?><?php edit_comment_link('编辑','&nbsp;&nbsp;',''); ?>
                <?php if ($comment->comment_approved == '0') : ?>
                <span class="orange">您的评论需要审核后才能显示，请稍等！</span>
                <?php endif; ?>
                <br />
                <?php comment_text() ?>
        	</div>
        </li>
	<?php
		/* Changes every other comment to a different class */
		$oddcomment = ( empty( $oddcomment ) ) ? 'class="alt" ' : '';
	?>
	<?php endforeach; /* end for each comment */ ?>
	</ol>
 <?php else : // this is displayed if there are no comments so far ?>

	<?php if ('open' == $post->comment_status) : ?>

	 <?php else : // comments are closed ?>

		<p class="nocomments">评论已经关闭。</p>
	<?php endif; ?>
<?php endif; ?>


<?php if ('open' == $post->comment_status) : ?>
<br />
<h3 class="comments">发表您的评论</h3>

<?php if ( get_option('comment_registration') && !$user_ID ) : ?>

<p>您必须<a href="<?php echo get_option('siteurl'); ?>/wp-login.php?redirect_to=<?php echo urlencode(get_permalink()); ?>"> 登录 </a> 才能发表评论。</p>

<?php else : ?>

<form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">

<?php if ( $user_ID ) : ?>

<p style="clear:both;">登录为 <a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a><a href="<?php echo get_option('siteurl'); ?>/wp-login.php?action=logout" title="Log out of this account"> 退出 </a></p>

<?php else : ?>

<p><label for="author">姓名 <?php if ($req) echo "(必填)"; ?></label></p><p><input type="text" name="author" id="author" value="<?php echo $comment_author; ?>" size="30" tabindex="1" /></p>

<p><label for="email">Mail <?php if ($req) echo "(必填，不会被公开)"; ?></label></p><p><input type="text" name="email" id="email" value="<?php echo $comment_author_email; ?>" size="30" tabindex="2" />
</p>

<p><label for="url">网站</label></p><p><input type="text" name="url" id="url" value="<?php echo $comment_author_url; ?>" size="30" tabindex="3" />
</p>

<?php endif; ?>

<p><label for="comment">评论内容</label></p><p><textarea name="comment" id="comment" cols="75" rows="10" tabindex="4"></textarea>
</p>

<p><input name="submit" type="submit" id="submit" tabindex="5" value="发表评论" />
<input type="hidden" name="comment_post_ID" value="<?php echo $id; ?>" />
</p>

<?php do_action('comment_form', $post->ID); ?>

</form>

<?php endif; // If registration required and not logged in ?>

<?php endif; // if you delete this the sky will fall on your head ?>
