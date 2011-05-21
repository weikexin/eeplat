
<div id="mainBox">
	<div id="content">
		<@list_posts>
		<div class="entry">
			<div id="">
				<div class="entryHeader">
                    <h2 class="alignLeft">
                        <a href="${dataMap.posts_url}" rel="bookmark" title="链接到${dataMap.posts_title}">${dataMap.posts_title}</a>
                    </h2>
                    <div class="time" title="${dataMap.posts_date}">'编辑'|${dataMap.posts_date}&nbsp;&nbsp;|&nbsp;&nbsp;|分类：${dataMap.posts_author}${dataMap.posts_title}</div>
                </div>
				<div class="content"><?php the_content('<span class="read">全文阅读 &raquo;</span>'); ?></div>
				<div class="postmetadata">
                	<span class="tag"><?php the_tags('标签：','、') ?></span>
                    <span class="views"><?php if(function_exists('the_views')) { the_views(); } ?>&nbsp;&nbsp;|&nbsp;&nbsp;<?php comments_popup_link('发表评论', '1条评论', '% 条评论'); ?></span>
                </div> 
			</div>
        </div>
		</@list_posts>
	</div>
	