
<#include "header.ftl">
<div class="bb_home">
    <!-- 侧边栏1 开始 -->
    <div class="sidebar1">
    	<!-- 分类 开始 -->
        <div class="special sbar1">
        	<h2>最热文章</h2>
            <div class="sbar1_content">
                <ul class="promo">
					<@list_posts order_by="comment">
						<li><a href="${dataMap.posts_url}" title="${dataMap.posts_title}">${dataMap.posts_title}</a></li>  
					</@list_posts>       
                </ul>
            </div>
        </div>
        <!-- 分类 结束 -->
	</div>
    <!-- 侧边栏1 结束 -->
    <!-- 宝贝推荐 开始 -->
    <div class="bb_recommend">
    	<#include "picbrowse.ftl" >
    	<!--
    	<@list_posts>
    		<div class="baobei" >
    			<h2><a href="${dataMap.posts_url}">${dataMap.posts_title}</a></h2>
    			<div class="baobei_detail">
    				<p>${dataMap.posts_except}</p>
    			</div>
    		</div>
    	</@list_posts>
    	-->
	</div>
    <!-- 宝贝推荐 结束 -->
    <div class="clear"></div>
    <div class="long_ad">
    	<a href="http://www.wpyou.com/"><img src="${bloginfo('current_path')}images/logo658X80.png" alt="购买WordPress淘宝客商业版主题" title="购买WordPress淘宝客商业版主题" /></a>
    </div>
    <div class="clear"></div>
    <div class="allcats">
        <!-- 单一分类 开始 -->
            <#include "cats.ftl">
        <!-- 单一分类 开始 -->
    <div class="clear"></div>
    </div>
</div>   
<#include "sidebar1.ftl">
<br clear="all" />
<#include "flink.ftl">
<div class="clear"></div>
<#include "footer.ftl">
