	<?php get_header(); ?>
    
	<!--继承class main DIV-->
    
    <div class="foubg">
        <div class="fource">
        	<?php flash(); ?>
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
                <h5><a href="http://www.goocn.com/archives/category/3"><img src="<?php bloginfo('template_directory'); ?>/images/more.png" /></a></h5>
            </div>
        </div>
        <div class="clear"></div>
       
        <div class="header22">
        	<p><span style="font-size:13pt">热门搜索：</span><?php wp_tag_cloud('smallest=13&largest=13&unit=pt&number=45 &format=flat&orderby=name&order=ASC'); ?></p>
            <h5><a href="javascript:window.external.AddFavorite ('http://www.goocn.com/', '未来之城')"><img src="<?php bloginfo('template_directory'); ?>/images/tag.gif" width="49" height="18" /></a></h5>
            <h5><a href="<?php bloginfo('rss2_url'); ?>" target="_blank"><img src="<?php bloginfo('template_directory'); ?>/images/rss.gif" width="49" height="18" /></a></h5>
            <div class="clear"></div>

        </div>
    </div>


    <?php if (have_posts()) : ?>
        <div class="postnav">
        	<div class="szwz">
            	<h1>您所在位置：<a href="#">主页</a>>文章分类></h1>
            </div>
            
            <div class="clear"></div>
        </div>
        <?php endif; ?>
    
    
    <div id="content">
    	<div class="indexbg">
    	<!--完整的一个分类列表-->
		<?php $display_categories = array(242,294,5,4,162,134,1,181); foreach ($display_categories as $category) { ?>
        <div class="catlist"><?php query_posts("showposts=6&cat=$category"); $wp_query->is_category = false; $wp_query->is_archive = false; $wp_query->is_home = true; ?>
            <h3><a href="<?php echo get_category_link($category);?>"><?php single_cat_title(); ?></a></h3>
            <ul>
            <?php if (have_posts()) : ?>
            <?php while (have_posts()) : the_post(); ?>
            <li><a href="<?php the_permalink() ?>"><?php the_title(); ?></a></li>
            <?php endwhile; ?><?php else : ?><br />&nbsp;&nbsp;&nbsp;此分类暂无内容
            <?php endif; ?></ul>
            <h4><a href="<?php echo get_category_link($category);?>"><img src="<?php bloginfo('template_directory'); ?>/images/gdnr.png" /></a></h4>
		</div><?php } ?>
		<!--完整的一个分类列表-->
        </div>
    </div>

	<?php get_sidebar(); get_footer(); ?>
