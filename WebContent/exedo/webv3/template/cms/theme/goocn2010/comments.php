<?php if (function_exists('wp_list_comments')) : ?>

<?php
/**
 * @package WordPress
 * @subpackage Default_Theme
 */

// Do not delete these lines
	if (!empty($_SERVER['SCRIPT_FILENAME']) && 'comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
		die ('Please do not load this page directly. Thanks!');

	if ( post_password_required() ) { ?>
		<p class="nocomments">该内容受密码保护，请输入密码进行查看.</p>
	<?php
		return;
	}
?>

<!-- You can start editing here. -->

<?php if ( have_comments() ) : ?>
	<h2><img src="/wp-content/themes/goocn2010/images/pao.gif" />&nbsp;评论总数<?php comments_number('', ' (1)', ' (%)' );?></h2>
	<p>&nbsp;</p>

	<ol class="commentlist">
	<?php wp_list_comments(); ?>
	</ol>

	<div class="navigation">
		<div class="alignleft"><?php previous_comments_link() ?></div>
		<div class="alignright"><?php next_comments_link() ?></div>
	</div>
 <?php else : // this is displayed if there are no comments so far ?>

	<?php if ('open' == $post->comment_status) : ?>
		<!-- If comments are open, but there are no comments. -->

	 <?php else : // comments are closed ?>
		<!-- If comments are closed. -->
		<p class="nocomments">评论已经关闭.</p>

	<?php endif; ?>
<?php endif; ?>


<?php if ('open' == $post->comment_status) : ?>

<div id="respond">

<h2><?php comment_form_title( '发表评论', ' %s' ); ?></h2>

<div class="cancel-comment-reply">
	<small><?php cancel_comment_reply_link(); ?></small>
</div>

<?php if ( get_option('comment_registration') && !$user_ID ) : ?>
<p>你必须先 <a href="<?php echo get_option('siteurl'); ?>/wp-login.php?redirect_to=<?php echo urlencode(get_permalink()); ?>">登录</a> 再发表评论.</p>
<?php else : ?>

<form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">

<?php if ( $user_ID ) : ?>

<p>欢迎你 <a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a>. <a href="<?php echo wp_logout_url(get_permalink()); ?>" title="Log out of this account">注销 &raquo;</a></p>

<?php else : ?>

<p><input type="text" name="author" id="author" value="<?php echo $comment_author; ?>" size="22" tabindex="1" <?php if ($req) echo "aria-required='true'"; ?> />
<label for="author"><small>名字 <?php if ($req) echo "(必填)"; ?></small></label></p>

<p><input type="text" name="email" id="email" value="<?php echo $comment_author_email; ?>" size="22" tabindex="2" <?php if ($req) echo "aria-required='true'"; ?> />
<label for="email"><small>Mail (不公开) <?php if ($req) echo "(必填)"; ?></small></label></p>

<p><input type="text" name="url" id="url" value="<?php echo $comment_author_url; ?>" size="22" tabindex="3" />
<label for="url"><small>主页/博客</small></label></p>

<?php endif; ?>

<!--<p><small><strong>XHTML:</strong> You can use these tags: <code><?php echo allowed_tags(); ?></code></small></p>-->

<?php wp_smilies();?> 

<p><textarea name="comment" id="comment" cols="100%" rows="10" tabindex="4"></textarea></p>

<p><button name="submit" type="submit" id="submit">写好了，提交</button>
<p>
		<img src="<?php bloginfo('template_directory'); ?>/images/jiantou.gif" />&nbsp;注意：<br />
		1、欢迎参与讨论，请在这里发表您的看法、交流您的观点。<br />
		2、本站取消了审核机制，你的留言提交后可立即显示，请不要重复提交，谢谢。<br />
		3、留言时的头像是Gravatar提供的服务。想设置的看<a href="http://www.wopus.org/wordpress-basic/basic-use/1640.html" target="_blank">这里</a>。<br />
</p>
<?php comment_id_fields(); ?>
</p>
<?php # do_action('comment_form', $post->ID); ?>

</form>

<?php endif; // If registration required and not logged in ?>
</div>

<?php endif; // if you delete this the sky will fall on your head ?>

<?php else: ?>

<?php // Do not delete these lines
if ('comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
	die ('Please do not load this page directly. Thanks!');
	if (!empty($post->post_password)) { // if there's a password
		if ($_COOKIE['wp-postpass_' . COOKIEHASH] != $post->post_password) {  // and it doesn't match the cookie
			?>
			<p class="nocomments">该内容受密码保护，请输入密码进行查看.<p>
			<?php
			return;
		}
	}
/* This variable is for alternating comment background */
$oddcomment = 'comment1';
?>
<h2>评论<?php comments_number('', ' (1)', ' (%)' );?></h2> 
<?php comments_number('<p>没有评论</p>', '', '' );?>
<?php if ($comments) : $first = true; ?>
<?php foreach ($comments as $comment) : ?>
<div class="<?php echo $oddcomment; ?><?php if ($first) { echo ' first'; $first = false; } ?>" id="comment-<?php comment_ID() ?>">
	<div class="commentdetails">
		<p class="commentauthor"><?php comment_author_link() ?></p>
		<?php if ($comment->comment_approved == '0') : ?>
		<em>你的评论正在审核中.</em>
		<?php endif; ?>
		<p class="commentdate"><?php comment_date('F jS, Y') ?> - <?php comment_time() ?>
		&nbsp; &nbsp; <?php edit_comment_link('编辑','',''); ?>
		</p>
	</div>
	<?php dp_gravatar(); ?>
	<br class="break" />
	<?php comment_text() ?>
</div>
<?php endforeach; /* end for each comment */ ?>
<?php endif; ?>
<h2 id="respond">发表评论</h2>
<?php if ('open' == $post->comment_status) : ?>
	<?php if ( get_option('comment_registration') && !$user_ID ) : ?>
	<p>你必须先 <a href="<?php echo get_option('siteurl'); ?>/wp-login.php?redirect_to=<?php the_permalink(); ?>">登录</a> 再发表评论.</p>
	<?php else : ?>
	<form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">
	<?php if ( $user_ID ) : ?>
	<p>欢迎你 <a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a>. <a href="<?php echo get_option('siteurl'); ?>/wp-login.php?action=logout" title="Log out of this account">注销 &raquo;</a></p>
	<?php else : ?>
		<p><input size="36" type="text" name="author" /> 名字 <span class="required">*</span></p>
		<p><input size="36" type="text" name="email" /> Mail <span class="required">*</span></p>
		<p><input size="36" type="text" name="url" /> 主页/博客</p>
	<?php endif; ?>
		<p><textarea rows="12" cols="42" name="comment"></textarea></p>
		<p><button name="submit" type="submit" id="submit" tabindex="5">写好了，提交</button>
		<input type="hidden" name="comment_post_ID" value="<?php echo $id; ?>" />
		</p>
		<?php # do_action('comment_form', $post->ID); ?>
	</form>
	<?php endif; ?>
	<?php endif; ?>
	
<?php endif; ?>
