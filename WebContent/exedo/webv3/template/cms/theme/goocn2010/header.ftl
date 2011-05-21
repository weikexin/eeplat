<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${bloginfo('name')} </title>
<link rel="stylesheet" href="${bloginfo('current_path')}/style.css" type="text/css" media="all" />
<script type="text/javascript" src="${bloginfo('current_path')}/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${bloginfo('current_path')}/js/fx.js"></script>
<script type="text/javascript" src="${bloginfo('current_path')}/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="${bloginfo('current_path')}/js/jquery.lavalamp.js"></script>
<script type="text/javascript" src="${bloginfo('current_path')}/js/loading.js"></script>
<script type="text/javascript" src="js/jquery-1.4a2.min.js"></script>
<script type="text/javascript" src="js/jquery.KinSlideshow-1.2.1.min.js"></script>

</head>

<body>
<div id="loading" style="position:fixed !important;position:absolute;top:0;left:0;height:100%; width:100%; z-index:999; background:#000 url(${bloginfo('current_path')}/images/load.gif) no-repeat center center; opacity:0.6; filter:alpha(opacity=60);font-size:14px;line-height:20px;" onclick="javascript:turnoff('loading')">
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
        	<a href="${bloginfo('site_url')}" target="_parent"><img src="images/zephyr.png" /></a>
        </div>
        <div class="heagg">
        	<p><?php if(function_exists('genki_announcement')) { genki_announcement(); } ?></p>
        </div>
        <div class="clear"></div>
	</div>
    <div class="header2">
    	<div class="header21">
        	<div class="ppnav">
            	<li class="page_item"><a href="${bloginfo('site_url')}" class="ppcur"><font color="#333">首&nbsp;&nbsp;页</font></a></li>
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
            <ul>
            	<@list_category>
            		<li class="cat-item cat-item-4"><a title="查看 ${dataMap.cat_name} 下的所有文章" href="${dataMap.url}">${dataMap.cat_name}</a></li>
            	</@list_category>
            </ul>
            
        </div>
    </div>


	