<?php get_header(); ?>
<div id="mainBox">
	<div id="content"> 
		<div class="postHeader">
        	<span class="alignLeft">
			   <?php previous_post_link('上一篇：%link') ?>
            </span> 
            <span class="alignRight">
               <?php next_post_link('下一篇：%link') ?>
            </span>
        </div>
		<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
            <div class="entry">
            <div id="post-<?php the_ID(); ?>">
                <h2 class="post"><?php the_title(); ?></h2>
    			<div class="gray post" title="<?php the_time('Y-m-d\TH:i:sO'); ?>"><?php unset($previousday); printf(__('%1$s &nbsp;|&nbsp; %2$s'), the_date('', '', '', false), get_the_time()) ?>分类：<?php the_category(',') ?>&nbsp;&nbsp;|&nbsp;&nbsp;<?php edit_post_link('编辑', '', '&nbsp;&nbsp;|&nbsp;&nbsp;'); ?><?php the_tags('标签：','、','&nbsp;&nbsp;|&nbsp;&nbsp;') ?><!--<?php the_author() ?>--><?php if(function_exists('the_views')) { the_views(); } ?></div>
            	<div class="content"><?php the_content('<p class="serif">Read the rest of this entry &raquo;</p>'); ?>
</div>
            	
				<?php wp_link_pages(array('before' => '<p><strong>Pages:</strong> ', 'after' => '</p>', 'next_or_number' => 'number')); ?>
			</div>
            <div class="collect">
            	喜欢本文，那就收藏到：<?php if (function_exists('wp_addbookmarks')){;?>
				<?php wp_addbookmarks();?>
                <?php } ;?>
            </div>
            </div>
			<div class="entry">
				<div class="postList"><?php wp_related_posts(); ?></div>
            	<div class="postList"><h3>随机日志</h3><?php random_posts(); ?></div>
            </div>
            <div class="entry">
                <?php comments_template(); ?>
            </div>
            <?php endwhile; else: ?>
            <div class="entry">
                <p>哦！您要找的日志可能已经更换地址，重新搜索一下吧，或者点击<a title="Home" class="active" href="<?php echo get_option('home'); ?>/"> 这里 </a>回首页看看吧</p>
            </div>
            <?php endif; ?>
        </div>
<?php get_sidebar(); ?>
<?php get_footer(); ?>