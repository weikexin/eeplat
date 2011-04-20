<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />
<title><?php bloginfo('name'); ?> <?php if ( is_single() ) { ?> &raquo; Blog Archive <?php } ?> <?php wp_title(); ?></title>
<meta name="generator" content="WordPress <?php bloginfo('version'); ?>" /> <!-- leave this for stats -->
<link rel="stylesheet" href="<?php bloginfo('stylesheet_url'); ?>" type="text/css" media="screen" />
<link rel="alternate" type="application/rss+xml" title="<?php bloginfo('name'); ?> RSS Feed" href="<?php bloginfo('rss2_url'); ?>" />
<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
<script type="text/javascript" src="<?php bloginfo('template_url'); ?>/js/menu.js"></script>
<?php wp_head(); ?>

</head>

<body>
<!-- 顶部 开始 -->
<div id="header">

<!-- 导航菜单 开始 -->
<div id="menubar">
	<ul id="menus" class="menus">
		<?php
			if (is_home()) {
				$home_menu_class = 'current-cat';
			} else {
				$home_menu_class = 'cat-item';
			}
		?>
		<li class="<?php echo($home_menu_class); ?>">
			<a title="<?php _e('Home', 'default'); ?>" href="<?php echo get_settings('home'); ?>/">首页</a>
		</li>
		<?php wp_list_categories('depth=2&title_li=0&orderby=name&show_count=0'); ?>
	</ul>
    <span><a href="<?php bloginfo('rss2_url'); ?>">RSS订阅</a></span>
</div>
<!-- 导航菜单 结束 -->

<!-- Logo banner 开始 -->
<div id="logobanner">
    <a href="<?php echo get_option('home'); ?>/"><img src="<?php bloginfo('template_directory'); ?>/images/logo.jpg" alt="<?php bloginfo('name'); ?>" title="<?php bloginfo('name'); ?>" /></a>
        
	<div id="rt">
       <a href="http://www.wpyou.com/" target="_blank"><img src="<?php bloginfo('template_directory'); ?>/images/banner.jpg" width="780" height="90" alt="" /></a>
	</div>
</div>
<!-- Logo banner 结束 -->

<!-- 公告牌 开始 -->
	<div id="placard">
    	<div id="announcement"><h4>公告牌: </h4>
		<!--滚动公告 开始--->
        <div id="anno_list">
			<div id="marqueebox"><div class="listbg"><?php if(function_exists('genki_announcement')) { genki_announcement(); } ?></div></div>
		</div>

		<script>
			function startmarquee(lh,speed,delay) {
			var p=false;
			var t;
			var o=document.getElementById("marqueebox");
			o.innerHTML+=o.innerHTML;
			o.style.marginTop=0;
			o.onmouseover=function(){p=true;}
			o.onmouseout=function(){p=false;}
			function start(){
			t=setInterval(scrolling,speed);
			if(!p) o.style.marginTop=parseInt(o.style.marginTop)-1+"px";
			}
			function scrolling(){
			if(parseInt(o.style.marginTop)%lh!=0){
			o.style.marginTop=parseInt(o.style.marginTop)-1+"px";
			if(Math.abs(parseInt(o.style.marginTop))>=o.scrollHeight/2) o.style.marginTop=0;
			}else{
			clearInterval(t);
			setTimeout(start,delay);
			}
			}
			setTimeout(start,delay);
			}
			startmarquee(20,19,3000);
			</script>
       <!--滚动公告 结束-->
		</div>
    	<div id="so"><?php include(TEMPLATEPATH . '/searchform.php'); ?></div>
    </div>
<!-- 公告牌 结束 -->
</div>
<!-- 顶部 结束 -->

<div class="spacebox"></div>

<div id="wrapper">