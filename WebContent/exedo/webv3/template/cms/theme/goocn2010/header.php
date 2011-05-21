<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" <?php language_attributes(); ?>>
<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><?php bloginfo('name'); ?> <?php wp_title(); ?></title>
<meta name="generator" content="WordPress <?php bloginfo('version'); ?>" /> <!-- leave this for stats -->
<meta name="description" content="<?php bloginfo('description') ?>" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="<?php bloginfo('stylesheet_url'); ?>" type="text/css" media="all" />
<link rel="alternate" type="application/rss+xml" title="<?php bloginfo('name'); ?> RSS Feed" href="<?php bloginfo('rss2_url'); ?>" />
<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
<script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/fx.js"></script>
<script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/jquery.lavalamp.js"></script>
<script type="text/javascript" src="<?php bloginfo('stylesheet_directory'); ?>/js/loading.js"></script>
<?php wp_get_archives('type=monthly&format=link'); ?>
<?php //comments_popup_script(); // off by default ?>
<?php wp_head(); ?>

</head>

<body>
<div id="loading" style="position:fixed !important;position:absolute;top:0;left:0;height:100%; width:100%; z-index:999; background:#000 url(/wp-content/themes/goocn2010/images/load.gif) no-repeat center center; opacity:0.6; filter:alpha(opacity=60);font-size:14px;line-height:20px;" onclick="javascript:turnoff('loading')">
<p id="loading-one" style="color:#fff;position:absolute; top:50%; left:50%; margin:20px 0 0 -50px; padding:3px 10px;" onclick="javascript:turnoff('loading')">页面载入中..</p>
</div>
<script type="text/javascript">
//<![CDATA[
  function turnoff(obj){
    document.getElementById(obj).style.display="none";
  }
//]]>
</script>

<div class="main">
    <div class="header1">
    	<div class="logo">
        	<a href="<?php echo get_option('home'); ?>" target="_parent"><img src="<?php bloginfo('template_directory'); ?>/images/logo.jpg" /></a>
        </div>
        <div class="heagg">
        	<p><?php if(function_exists('genki_announcement')) { genki_announcement(); } ?></p>
        </div>
        <div class="clear"></div>
	</div>
    <div class="header2">
    	<div class="header21">
        	<div class="ppnav">
            	<li class="page_item"><a href="<?php echo get_option('home'); ?>" class="ppcur"><font color="#333">首&nbsp;&nbsp;页</font></a></li>
                <?php wp_list_pages('title_li=&depth=1'); ?>
                <div class="clear"></div>
            </div>
            <div class="ppsea">
                <form action="<?php echo get_option('home'); ?>/">
                <input type="text" name="s" id="s" value="<?php the_search_query(); ?>" />
                <button type="submit">搜索</button>
                </form>
            </div>
            <div class="clear"></div>
        </div>
        <div id="menu">
            <ul><?php wp_list_categories('title_li=&depth=1'); ?></ul>
        </div>
    </div>


	