
		<@list_category>
        	<div id="cat-" class="cat-left">
            	<div class="cat-left-content">
                <h4><a href="${dataMap.url}" title=""${dataMap.cat_name}"">"${dataMap.cat_name}"</a></h4><!-- 输出指定分类的标题 -->
                <ul>
                   <@list_posts cat_id="${dataMap.cat_id}">
                    <li><span class="titlel"><a href="${dataMap.posts_url}" rel="bookmark" class="title">${dataMap.posts_title}</a></span><span class="timer">${dataMap.posts_date}</span></li><!-- 由上述条件指定分类的文章标题列表 -->
                    </@list_posts>
                </ul>
               </div>
            </div>
      </@list_category>
<div class="spacebox"></div>
 <?php query_posts(''); ?>