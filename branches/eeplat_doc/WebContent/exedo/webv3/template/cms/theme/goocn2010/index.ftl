	<#include "header.ftl" />
    
	<!--继承class main DIV-->
    
    <div class="foubg">
        <div class="fource">
        	<#include "picbrowse.ftl" />
        </div>
        <div class="fourceri">
            <div class="fcr1">
                <h1><a href="http://www.goocn.com/archives/3066.html" target="_blank">"Windows 7的20个应用小技巧"</a></h1>
                <p>微软不断地在企业中注入新鲜的元素。为大家总结了Windows 7中的20个应用小技巧。</p>
            </div>
            <div class="fcr2">
                <ul>
                <li><a href="http://www.goocn.com/archives/2361.html" target="_blank">俄罗斯艺术家-天空の城</a></li>
                <li><a href="http://www.goocn.com/archives/1331.html" target="_blank">WordPress模板修改及函数整理</a></li>
                <li><a href="http://www.goocn.com/archives/2019.html" target="_blank">元旦将至拿出两款精品分享</a></li>
                <li><a href="http://www.goocn.com/archives/1847.html" target="_blank">本站精挑细选的10张桌面壁纸</a></li>
                <li><a href="http://www.goocn.com/archives/460.html" target="_blank">汽车发烧音乐-翻唱魔女</a></li>
                </ul>
                <h5><a href="http://www.goocn.com/archives/category/3"><img src="${bloginfo('current_path')}/images/more.png" /></a></h5>
            </div>
        </div>
        <div class="clear"></div>
       
        	

    </div>


        <div class="postnav">
        	<div class="szwz">
            	<h1>您所在位置：<a href="#">主页</a>>文章分类></h1>
            </div>
            
            <div class="clear"></div>
        </div>
    
    
    <div id="content">
    	<div class="indexbg">
    	<!--完整的一个分类列表-->
    	<@list_category >
        <div class="catlist">
        	<#assign cat_url = "${dataMap.url}" />
            <h3><a href="${dataMap.url}">${dataMap.cat_name}</a></h3>
            <ul>
            <@list_posts cat_id="${dataMap.cat_id}">
            	
            	<#if (dataMap.posts_title?exists)>
		            <li><a href="${dataMap.posts_url}">${dataMap.posts_title}</a></li>
		            <br />
		        <#else>
		        	&nbsp;&nbsp;&nbsp;此分类暂无内容
		        </#if>
            </@list_posts>
		            <h4><a href="${cat_url}"><img src="${bloginfo('current_path')}/images/gdnr.png" /></a></h4>
            </ul>
		</div>
		</@list_category>
		<!--完整的一个分类列表-->
        </div>
    </div>

	<#include "sidebar.ftl" />
	<#include "footer.ftl" />
