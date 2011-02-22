 <div class="p_rightbar">
    <div class="p_right">
       <h3>最新文档</h3>
        <ul class="p_news">
             <@list_posts>
					 <li class="p_news">· <a href="${dataMap.posts_url}" rel="bookmark" title="详细阅读 ">${dataMap.posts_title}</a></li>
			</@list_posts>
        </ul>
    </div>
      <!---AD-->
  <div class="p_right">
        <img src="<?php bloginfo('template_directory'); ?>/images/AD.gif" width="300" height="150" />
        
    </div>
  <!--AD END-->
  <div class="p_right">
       <h3>网站标签</h3>
        <ul class="p_news">
  <?php wp_tag_cloud('smallest=8&largest=16'); ?>
        </ul>
    </div>
    <div class="p_right">
      <dl>
        <?php include('includes/top_comment.php'); ?>
       </dl>
     <div class="clear"></div>
    </div>
  
<!--comments-->
  
    <div class="comments"><h3>最新评论</h3>
<div class="r_comments">
<ul>
<?php include('includes/r_comments.php'); ?>
<div class="clear"></div>
</ul>
</div>
<div class="clear"></div>
</div>
<!--Comment End-->
  <div class="clear"></div>
  </div>