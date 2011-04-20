<#include "header.ftl">
<!--文章正文 开始 -->
<div class="taobaobei">
        <div class="subnavi">
            <div class="subnavi-l">当前位置: </div>
        </div>

			<div class="bb_post" <#if (dataMap.posts_id?exists)> posts_id="${dataMap.posts_id}" </#if> >
						<div class="bb_list" >
						   <@list_posts>
			                  <h2 class="txtcenter">${dataMap.posts_title}</h2>
			                   <div class="descmid dashborder">
			                   		
			                        <label class="page-date">发表时间：${dataMap.posts_date}</label><label class="page-views">关注度： </label><label class="page-comments">评论数：<a href="#respond">${dataMap.comment_count!"1"}</a></label> 
			                   </div>
			                   <div class="bb_detail baobei_info">
			                        ${dataMap.posts_content}
			                        <div class="reship">
			                            <p>转载请注明文章转载自：<a href="${dataMap.posts_url}">${dataMap.posts_author!"admin"}</a> [${dataMap.posts_url}]<br /></p>
			                        </div>
			                        
			             		</div>
			                </@list_posts>
			    
						</div>
			
			</div>

<div class="clear"></div>

<#include "comments.ftl">
	
</div>


<!--文章正文 结束 -->
<!-- 侧边栏2 开始 -->
<div class="sidebar2">
	<#include "sidebar1.ftl">
</div>
<!-- 侧边栏2 结束 -->

<br clear="all" />

<#include "flink.ftl">
<#include "footer.ftl">


