<?php get_header(); ?>
<?php $post = $posts[0]; ?>  

	<div id="ppcate">
		<div class="ppca">
			<?php if (have_posts()) : while(have_posts()) : the_post(); ?>    
            <!--一个循环体开始-->
            <div class="ppcali">
                <div class="ppcate1">
                    <div class="ppcate1le">
                        <h2><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></h2>
                        <p class="details">作者：<?php the_author_posts_link(); ?> 发表时间：<?php the_time('F j, Y') ?> &nbsp;|&nbsp; <?php comments_popup_link('没有评论', '1 条评论', '% 条评论'); ?></p>
                    </div>
                    <div class="ppcatelri"><?php $GLOBALS['edg_instance']->get_button(); ?></div>
                    <div style="clear:both"></div>
                </div>
                
                <div class="ppcate2">
                    <a href="<?php the_permalink(); ?>"><?php dp_attachment_image($post->ID, 'thumbnail', 'class="' . $post->post_title . '"'); ?></a><?php the_excerpt(); ?>
                    <div style="clear:both"></div>
                </div>
                
                <div class="ppcate3">
                    <?php the_tags('<p class="pleft">标签：', ', ', '</p>'); ?>
					<p class="pright">类别：<?php the_category(', '); ?></p>
                    <div style="clear:both"></div>
                </div>
                 <div style="clear:both"></div>
            </div>
            <!--一个循环体开始-->
			<?php endwhile; endif; ?>
            <div style="clear:both"></div>
        </div>
    
	
        <?php if (have_posts()) : ?>
        <div class="postnav">
        	<div class="szwz">
            	<h1>您所在位置：<a href="#">主页</a>>文章列表></h1>
            </div>
            <?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } else { ?>
            <?php next_posts_link('&laquo; 上一页'); ?> &nbsp; 
            <?php previous_posts_link('下一页 &raquo;'); ?>
            <?php } ?>
            <div class="clear"></div>
        </div>
        <?php endif; ?>
</div>
<!--ppcate end-->

	<?php if (isset($_GET['paged']) && empty($_GET['paged'])) {get_sidebar(); get_footer();} else {get_footer();} ?>
