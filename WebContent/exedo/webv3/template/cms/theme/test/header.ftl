<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${bloginfo('name')} </title>
<link rel="stylesheet" type="text/css" href="${bloginfo('current_path')}style.css" />
<link rel="alternate" type="application/rss+xml" title="RSS Feed" href="#" />
<script src="${bloginfo('current_path')}js/jquery-1.4a2.min.js" type="text/javascript"></script>
<script src="${bloginfo('current_path')}js/jquery.KinSlideshow-1.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
alert("sss");
$(function(){
	$("#KinSlideshow").KinSlideshow();
})
}
</script>
</head>
<body>
<!-- 头部 开始 -->
<div class="header">
<div class="header-content">
		<!-- 顶部功能 开始 -->
    	<div id="logo">
    		<h1 id="blogtitle" ><a href="http://www.zephyr.com.cn" title="首页"></a></h1></a>
        </div>

        <!-- 右侧 开始 -->
        <div class="headerR">
        	<div class="top_menu">
        		<ul class="menu_list">
        			<#if is_userlogin() >
        				<li class="page_item page-item-2"><a title="关于" href="#">欢迎您,${get_user_info().userinfo.user_nicename}</a></li>
        			</#if>
        		    <li class="page_item page-item-2"><a title="关于" href="#">关于</a></li>
        		    <li class="page_item page-item-2"><a title="收藏本站" href="#">收藏本站</a></li>
        			
        		</ul>
        	</div>
             <div class="clear"></div>
            <!-- 搜索 开始 -->
            <div class="so">
                   <#include "searchform.ftl">
            </div>
            <!-- 搜索 结束 -->
        </div>
        <!-- 右侧 结束 -->
        <!-- 顶部功能 结束 -->
        <div class="clear"></div>
        <!-- 整站大导航 开始 -->
        <div class="navigation">
			<div class="mnavil"></div>
    		<div class="mnavim">
                <ul class="main_menu">
                   	<li class="cat-item cat-item-1">
                   		<a href="${bloginfo('site_url')}" title="${bloginfo('name')}">首页</a>
					</li>
                	<@list_category>
                   	<li class="cat-item cat-item-1">
                   		<a href="${dataMap.url}" title="查看 ${dataMap.cat_name} 下的所有文章">${dataMap.cat_name}</a>
					</li>
					</@list_category>
                </ul>
    		</div>
    		<div class="mnavir"></div>
		</div>    
        <!-- 整站大导航 结束 -->
</div>
</div>
<!-- 头部 结束 -->
<div class="clear"></div>
<!-- 内容 开始 -->
<div class="wrap">
    <div class="content">