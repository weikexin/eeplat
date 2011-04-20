<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="Content-Type" content="${bloginfo('html_type')}; charset=${bloginfo('charset')}" />
<title>${bloginfo('name')} </title>
<link rel="stylesheet" href="${bloginfo('current_path')}css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${bloginfo('current_path')}css/template.css" type="text/css" media="screen" />
<script type="js/menu.js"></script>


</head>

<body>
<!-- 顶部 开始 -->
<div id="header">

<!-- 导航菜单 开始 -->
<div id="menubar">
	<ul id="menus" class="menus">
		
		<li >
			<a title="" href="${bloginfo('site_url')}">首页</a>
			
		</li>
		<@list_category>
			<li ><a title="${dataMap.cat_name}" href="${dataMap.url}">${dataMap.cat_name}</a></li>
		</@list_category>
		
	</ul>
   
</div>
<!-- 导航菜单 结束 -->

<!-- Logo banner 开始 -->
<div id="logobanner">
    <a href="${bloginfo('site_url')}"><img src="${bloginfo('current_path')}images/logo.jpg" alt="${bloginfo('name')}" title="${bloginfo('name')}" /></a>
        
	<div id="rt">
       <a href="${bloginfo('site_url')}" ><img src="${bloginfo('current_path')}images/banner.jpg" width="780" height="90" alt="" /></a>
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