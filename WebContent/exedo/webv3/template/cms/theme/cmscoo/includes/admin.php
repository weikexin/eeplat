<?php if (get_theme_mod('showadmin') == 'Yes') { ?>
<?php
	global $user_identity,$user_level;
	get_currentuserinfo();
	if ($user_identity) { ?>			
	<h3>网站管理</h3>
	<div class="admin">
		<ul class="admin_bar">
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/" title="网站管理"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_dashboard.png" /><?php _e('&nbsp;&nbsp;管理'); ?></a></li>
			<?php if ($user_level >= 1) { ?>
			<?php // Get comments awaiting moderation
			$numcomments = $wpdb->get_var("SELECT COUNT(*) FROM $wpdb->comments WHERE comment_approved = '0'");
			?>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/post-new.php" title="添加文章"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_page_new.png"/><?php _e('&nbsp;&nbsp;文章'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/widgets.php" title="挂件"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_post_new.png"/><?php _e('&nbsp;&nbsp;挂件'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/link-manager.php" title="链接管理"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_link_manage.png"/><?php _e('&nbsp;&nbsp;链接'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/upload.php" title="媒体管理"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_uploads_manage.png" /><?php _e('&nbsp;&nbsp;媒体'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/tools.php" title="工具"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_post_manage.png"/><?php _e('&nbsp;&nbsp;工具'); ?></a></li>
			<?php } ?>
		</ul>
		<ul class="admin_bar">
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/moderation.php" title="评论管理"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_comments.png"/><?php _e('&nbsp;&nbsp;评论'); ?> <?php if ( $numcomments ) : ?> (<strong><?php echo number_format($numcomments); ?></strong>)<?php endif; ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/themes.php" title="主题"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_presentation.png"/><?php _e('&nbsp;&nbsp;主题'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/plugins.php" title="管理插件"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_plugins.png"/><?php _e('&nbsp;&nbsp;插件'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/options-general.php" title="设置"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_options.png"/><?php _e('&nbsp;&nbsp;设置'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-admin/users.php" title="用户管理"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_users.png" /><?php _e('&nbsp;&nbsp;用户'); ?></a></li>
			<li><a href="<?php echo get_option('siteurl'); ?>/wp-login.php?action=logout" title="网站管理"><img src="<?php bloginfo('template_url'); ?>/images/adminbar/icon_logout.png"/><?php _e('&nbsp;&nbsp;注销'); ?></a></li>
		</ul>
	</div>
	<div class="clear"></div>
	<div class="admin-bottom"></div>
	<?php } ?>
<?php } else { ?>
<?php } ?>