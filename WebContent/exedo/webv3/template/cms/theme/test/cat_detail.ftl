<#include "header.ftl" />
 	<!--文章列表 开始 -->
<div class="taobaobei">
    	<!--页面导航 开始 -->
        <div class="subnavi">
                      <div class="subnavi-l"></div>
        </div>
        <!--页面导航 结束 -->
		<!-- 分类文章列表 开始 -->
        <div class="bb_post">
        		 <@list_posts>
                        <div id="baobei-6" class="bb_list">
                        <h2><a  href="${dataMap.posts_url}">${dataMap.posts_title}</a></h2>
                        <span class="bb_date"></span>
                        <div class="bb_detail">
                                               ${dataMap.posts_except}
                        <a  href="${dataMap.posts_url}">详细内容</a>
                        </div>
                        </div>
                 </@list_posts>
                <!-- 翻页 开始 -->
                <div class="page_navi">
                    							<div class="right page_next"></div>
							<div class="left page_pro"></div>
					                </div>
                <!-- 翻页 结束 -->
                    </div>
		<!-- 分类文章列表 结束 -->
</div>

    <!-- 文章列表 结束 -->
<!-- 侧边栏2 开始 -->
<#include "sidebar1.ftl" />
<!-- 侧边栏2 结束 -->

<br clear="all" />
<#include "footer.ftl"/>
</body>
</html>
