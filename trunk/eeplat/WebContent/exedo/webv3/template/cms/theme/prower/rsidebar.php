<div id="rss">
	<!-- Feedsky FEED发布代码开始 -->
    <!-- FEED自动发现标记开始 -->
    <link title="RSS 2.0" type="application/rss+xml" href="http://feed.prower.cn" rel="alternate" />
    <!-- FEED自动发现标记结束 -->
    <a href="http://fusion.google.com/add?feedurl=http://feed.prower.cn" target="_blank"><img border="0" src="http://img.feedsky.com/images/icon_subshot02_google.gif" alt="google reader" vspace="2" style="margin-right:8px;" ></a>
    <a href="http://www.zhuaxia.com/add_channel.php?url=http://feed.prower.cn" target="_blank"><img border="0" src="http://img.feedsky.com/images/icon_subshot02_zhuaxia.gif" alt="&#25235;&#34430;" vspace="2" ></a><br />
    <a href="http://www.xianguo.com/subscribe.php?url=http://feed.prower.cn" target="_blank"><img border="0" src="http://img.feedsky.com/images/icon_subshot02_xianguo.jpg" alt="&#40092;&#26524;" vspace="2" style="margin-right:8px;" ></a>
    <a href="http://reader.yodao.com/#url=http://feed.prower.cn" target="_blank"><img border="0" src="http://img.feedsky.com/images/icon_subshot02_youdao.gif" alt="&#26377;&#36947;" vspace="2" ></a><br />
    <a href="http://www.pageflakes.com/subscribe.aspx?url=http://feed.prower.cn" target="_blank"><img border="0" src="http://img.feedsky.com/images/icon_subshot02_pageflakes.gif" alt="pageflakes" vspace="2" style="margin-right:8px;" ></a>
    <a href="http://add.my.yahoo.com/rss?url=http://feed.prower.cn" target="_blank"><img border="0" src="http://img.feedsky.com/images/icon_subshot02_yahoo.gif" alt="my yahoo" vspace="2" ></a>
</div>
<div id="search">
<!-- SiteSearch Google -->
<form method="get" action="http://www.google.com/custom" target="google_window">
<table border="0" bgcolor="#ffffff">
<tr><td nowrap="nowrap" valign="top" align="left" height="32">
<a href="http://www.google.com/">
<img src="http://www.google.com/logos/Logo_25wht.gif" border="0" alt="Google" align="middle"></img></a>
</td>
<td nowrap="nowrap">
<input type="hidden" name="domains" value="www.prower.cn"></input>
<label for="sbi" style="display: none">输入您的搜索字词</label>
<input type="text" name="q" size="18" maxlength="255" value="" id="sbi"></input>
<label for="sbb" style="display: none">提交搜索表单</label>
<input type="submit" name="sa" value="搜索" id="sbb"></input>
</td></tr>
<tr>
<td>&nbsp;</td>
<td nowrap="nowrap">
<table>
<tr>
<td>
<input type="radio" name="sitesearch" value="" id="ss0"></input>
<label for="ss0" title="搜索网络"><font size="-1" color="#000000">Web</font></label></td>
<td>
<input type="radio" name="sitesearch" value="www.prower.cn" checked id="ss1"></input>
<label for="ss1" title="搜索 www.prower.cn"><font size="-1" color="#000000">www.prower.cn</font></label></td>
</tr>
</table>
<input type="hidden" name="client" value="pub-2783733942058498"></input>
<input type="hidden" name="forid" value="1"></input>
<input type="hidden" name="channel" value="0609693060"></input>
<input type="hidden" name="ie" value="UTF-8"></input>
<input type="hidden" name="oe" value="UTF-8"></input>
<input type="hidden" name="safe" value="active"></input>
<input type="hidden" name="cof" value="GALT:#008000;GL:1;DIV:#336699;VLC:663399;AH:center;BGC:FFFFFF;LBGC:336699;ALC:0000FF;LC:0000FF;T:000000;GFNT:0000FF;GIMP:0000FF;FORID:1"></input>
<input type="hidden" name="hl" value="zh-CN"></input>
</td></tr></table>
</form>
<!-- SiteSearch Google -->
</div>
<div class="rsidebar">
<ul>
	<?php if ( function_exists('dynamic_sidebar') && dynamic_sidebar(2) ) : else : ?>
	<?php wp_list_categories('show_count=1&title_li=<h2>日志分类</h2>'); ?>
    <li><h2>最新日志</h2>
		<?php query_posts('showposts=10'); ?>
		<ul>
  			<?php while (have_posts()) : the_post(); ?>
  			<li><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></li>
  			<?php endwhile;?>
		</ul>
	</li>
    <li><h2>最新评论</h2>
		<?php
		global $wpdb;
		$sql = "SELECT DISTINCT ID, post_title, post_password, comment_ID,
		comment_post_ID, comment_author, comment_date_gmt, comment_approved,
		comment_type,comment_author_url,
		SUBSTRING(comment_content,1,35) AS com_excerpt
		FROM $wpdb->comments
		LEFT OUTER JOIN $wpdb->posts ON ($wpdb->comments.comment_post_ID =
		$wpdb->posts.ID)
		WHERE comment_approved = '1' AND comment_type = '' AND
		post_password = ''
		ORDER BY comment_date_gmt DESC
		LIMIT 10";
		$comments = $wpdb->get_results($sql);
		$output = $pre_HTML;
		$output .= "\n<ul>";
		foreach ($comments as $comment) {
		$output .= "\n<li>".strip_tags($comment->comment_author)
		.":" . "<a href=\"" . get_permalink($comment->ID) .
		"#comment-" . $comment->comment_ID . "\" title=\"on " .
		$comment->post_title . "\">" . strip_tags($comment->com_excerpt)
		."</a></li>";
		}
		$output .= "\n</ul>";
		$output .= $post_HTML;
		echo $output;?>	
	</li>
    <li><h2>热门日志</h2>
		<ul>
			<?php if (function_exists('get_most_viewed')): ?>    
            <?php get_most_viewed(); ?>    
            <?php endif; ?>
    	</ul>
    </li>
	<li><h2>日志存档</h2>
		<form class="tags" id="archiveform" action=""> 
            <select name="archive_chrono" onchange="window.location = 
            (document.forms.archiveform.archive_chrono[ 
            document.forms.archiveform.archive_chrono.selectedIndex].value);"> 
            <option value=''>请选择月份查看</option> 
            <?php wp_get_archives('type=monthly&format=option&show_post_count=true'); ?> 
            </select> 
        </form>
	</li>
	<li><h2>标签云</h2>
    	<div class="tags">
			<?php wp_tag_cloud('unit=px&smallest=12&largest=22&number=20'); ?>
		</div>
    </li>
	<?php /* If this is the frontpage */ if ( is_home() || is_page() ) { ?>
	<?php wp_list_bookmarks(); ?>
	<li><h2>博客管理</h2>
		<ul>
			<?php wp_register(); ?>
			<li><?php wp_loginout(); ?></li>
			<?php wp_meta(); ?>
		</ul>
	</li>
	<?php } ?> 
	<?php endif; ?>
</ul>
</div>