<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<?php include('includes/seo.php'); ?>	
<link rel="profile" href="http://gmpg.org/xfn/11" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="<?php bloginfo('template_directory'); ?>/style.css" />
<link rel="alternate" type="application/rss+xml" title="<?php bloginfo('name'); ?> RSS Feed" href="<?php bloginfo('rss2_url'); ?>" />
<link rel="alternate" type="application/atom+xml" title="<?php bloginfo('name'); ?> Atom Feed" href="<?php bloginfo('atom_url'); ?>" />
<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
<link rel="shortcut icon" href="<?php bloginfo('template_directory'); ?>/images/favicon.ico" />
<?php if (function_exists('wp_enqueue_script') && function_exists('is_singular')) : ?>
<script type="text/javascript" src="<?php bloginfo('stylesheet_directory'); ?>/js/jquery.min.js" ></script>

<?php if ( is_singular() ){ ?>
<script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/comments-ajax.js"></script>
<?php } ?>
<?php endif; ?>
</head>
<body>
<div id="wrapper">
<!--Header Stra-->
<div class="header">
   <div class="top">
      <div class="logo">
      <h1><a href="<?php bloginfo('siteurl'); ?>"><?php bloginfo(’name’); ?></a></h1>
      </div>
      <div class="search">
      <form method="get" action="<?php bloginfo('url'); ?>" name="search"  id="searchform">
         <dl class="tab_search">
			<input type="text" name="s" title="Search" class="searchinput" id="searchinput" onkeydown="if (event.keyCode==13) {}" onblur="if(this.value=='')value='- Search -';" onfocus="if(this.value=='- Search -')value='';" value="- Search -" size="10"/>
		
			<input type="image" width="21" height="17" class="searchaction" onclick="if(document.forms['search'].searchinput.value=='- Search -')document.forms['search'].searchinput.value='';" alt="Search" src="<?php bloginfo('template_directory'); ?>/images/magglass.gif" border="0" hspace="2"/>
	    </dl>
</form>
      </div>
   </div>
   
   
  <div id="navcontainer">
   <ul id="navlist">
    <li><a href="<?php bloginfo('siteurl'); ?>">首页</a></li>
    <?php wp_list_cats("sort_column=name&hierarchical=0&exclude="); ?>
	<!--<li><a href="http://www.cmscoo.com.cn/line-post">在线投稿</a></li>-->
  </ul>
 </div>
<div class="clear"></div>
</div>