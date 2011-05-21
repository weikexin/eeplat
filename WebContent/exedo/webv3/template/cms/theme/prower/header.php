<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<title><?php if (is_home () ) { bloginfo('name'); } elseif ( is_category() ) { single_cat_title();
echo " - "; bloginfo('name'); } elseif (is_single() || is_page() ) { single_post_title(); echo " - "; bloginfo('name'); }
elseif (is_search() ) { bloginfo('name'); echo "search results:"; echo
wp_specialchars($s); } else { wp_title('',true); } ?></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="prower" />
<meta name="copyright" content="copyright by prower" />
<meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />
<meta name="generator" content="WordPress <?php bloginfo('version'); ?>" /> <!-- leave this for stats -->
<link rel="stylesheet" href="<?php bloginfo('stylesheet_url'); ?>" type="text/css" media="screen" />
<link rel="alternate" type="application/rss+xml" title="<?php bloginfo('name'); ?> RSS Feed" href="<?php bloginfo('rss2_url'); ?>" />
<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
<?php wp_head(); ?>
</head>
<body>
<div id="header">
    <div id="headerBox">
    	<div id="blogInfo">
		    <h1 class="blogName"><a href="<?php echo get_option('home'); ?>/"><?php bloginfo('name'); ?></a></h1>
    		<p class="blogSubhead"><?php bloginfo('description'); ?></p>
    	</div>
        <ul class="menu">
            <li <?php if(is_home()){echo 'class="current_page_item"';}?>><a title="Home" class="active" href="<?php echo get_option('home'); ?>/">日 志</a></li>
            <?php wp_list_pages('sort_column=id&depth=1&title_li='); ?>
        </ul>
    </div>
</div>
<div id="blogImg">
	<div id="blogPic"></div>
</div>
<div id="main">