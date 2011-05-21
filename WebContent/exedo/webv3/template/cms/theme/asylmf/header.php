<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">

<title>
<?php
	global $page, $paged;

	wp_title( '|', true, 'right' );

	// Add the blog name.
	bloginfo( 'name' );

	// Add the blog description for the home/front page.
	$site_description = get_bloginfo( 'description', 'display' );
	if ( $site_description && ( is_home() || is_front_page() ) )
		echo " | $site_description";

	// Add a page number if necessary:
	if ( $paged >= 2 || $page >= 2 )
		echo ' | ' . sprintf( __( '页面 %s', 'asylmf' ), max( $paged, $page ) );

	?>
</title>

	<meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />	
	<meta name="generator" content="WordPress <?php bloginfo('version'); ?>" /> <!-- leave this for stats please -->
	<link rel="stylesheet" href="<?php bloginfo('stylesheet_url'); ?>" type="text/css" media="screen" />
	<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="<?php bloginfo('rss2_url'); ?>" />
	<link rel="alternate" type="text/xml" title="RSS .92" href="<?php bloginfo('rss_url'); ?>" />
	<link rel="alternate" type="application/atom+xml" title="Atom 0.3" href="<?php bloginfo('atom_url'); ?>" />
	<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />

	<?php wp_get_archives('type=monthly&format=link'); ?>
	<?php //comments_popup_script(); // off by default ?>
	<?php wp_head(); ?>
    
    <script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/jquery-1.4a2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/jquery.KinSlideshow-1.2.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$("#KinSlideshow").KinSlideshow();
	})
	</script>
	<?php wp_head(); ?> 

</head>

<body>

<div id="nav1">
	<p class="description"><?php bloginfo('description'); ?></p>
    <ul class="login">
        <li><?php wp_register(); ?></li>
        <li><?php wp_loginout(); ?></li>
    </ul>
    <?php 
		$topNav = '';
		if (function_exists('wp_nav_menu')) {
			$topNav = wp_nav_menu( array( 'theme_location' => 'header-primary', 'menu_class' => 'menu1', 'echo' => false, 'fallback_cb' => '' ) );};
		if ($topNav == '') { ?>
    <ul class="menu1">
    	<?php wp_list_pages('title_li=&depth=1'); ?>
    </ul>
    <?php }
			else echo($topNav); 
		?>
</div>

<div id="header">
	<p class="logo"><a href="<?php bloginfo('siteurl'); ?>" title="<?php bloginfo('name'); ?>"><?php bloginfo('name'); ?></a></p>
    <p class="topbanner"><?php $ad1 = get_option('asylmf_ad5'); echo stripslashes($ad1); ?></p>
</div>

<div id="nav2">
	<?php 
		$seconNav = '';
		if (function_exists('wp_nav_menu')) {
			$seconNav = wp_nav_menu( array( 'theme_location' => 'header-secondary','echo' => false, 'fallback_cb' => '' ) );};
		if ($seconNav == '') { ?>
	<ul>
    	<li><a href="<?php bloginfo('siteurl'); ?>" title="<?php bloginfo('name'); ?>">首页</a></li>
        <?php wp_list_categories('title_li=&depth=1'); ?>
    </ul>
    <?php }
			else echo($seconNav); 
		?>
</div>

<div id="searchbar">
	<span class="searchbotton">
    	<?php include(TEMPLATEPATH . '/searchform.php'); ?>
    </span>
    <span class="tags">
    	<ul>
        	<li><?php _e('热门标签:','AsYLMF'); ?></li>
            <?php wp_tag_cloud('unit=px&smallest=12&largest=12&number=8'); ?>
        </ul>
    </span>
    <span class="rss"><a href="<?php bloginfo('rss_url'); ?>">订阅RSS</a></span>
</div>
