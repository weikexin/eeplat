	<!-- BEGIN sidebar -->
	<div id="sidebar">
        <div class="sideli">
       <h3>最近更新</h3>
        
        <ul>
        <@list_posts order_by="test">
        	<li><a href="${dataMap.posts_url}">${dataMap.posts_title}</a></li>
        </@list_posts>
        </ul>
        </div>
        
        <div class="sideli">
       <h3>热门关注</h3>
        <ul>
        <@list_posts order_by="test">
        	<li><a href="${dataMap.posts_url}">${dataMap.posts_title}</a></li>
        </@list_posts>
        </ul>
        </div>
         
        <div class="sideli">
        <h3>留下评论</h3>
        <ul>
        <?php dp_recent_comments(8); ?>
        </ul>
        </div>
        <?php endif; ?>
        
        <div class="yqlj">
        	<div class="yqljl">
            	<?php wp_list_bookmarks(); ?>
            </div>
            <div class="dldiv">
            	<h2 style="padding-left:12px">登录</h2>
            	<ul>
				<?php wp_register(); ?>
                <li><?php wp_loginout(); ?></li>
                <li><script src="http://s96.cnzz.com/stat.php?id=1857645&web_id=1857645" language="JavaScript" charset="gb2312"></script></li> 
                <?php wp_meta(); ?>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
	<!-- END sidebar -->
    
<script language="javascript">
var a=document.getElementById("sidebar")
var b=document.getElementById("content")
if (a.scrollHeight<b.scrollHeight)    
{
a.style.height=b.scrollHeight+"px"; 
}
else
{
b.style.height=a.scrollHeight+"px";  }
</script> 