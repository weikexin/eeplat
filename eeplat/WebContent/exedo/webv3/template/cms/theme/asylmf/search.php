<?php get_header(); ?>

<div id="content">
	<div id="mainleft">
    	<p class="breadcrumb">当前位置：<?php the_breadcrumb(); ?></p>
    	<?php if(have_posts()) : ?><?php while(have_posts()) : the_post(); ?>
            <span class="wenzhang">
				<h1>
                	<p class="title"><a href="<?php the_permalink(); ?>" title="<?php the_title(); ?>"><?php the_title(); ?></a></p>
                    <p class="pinglun"><?php the_time('Y年m月j日'); ?></p>
                </h1>
                <a href="<?php the_permalink(); ?>" title="<?php the_title(); ?>"><img src="<?php echo catch_that_image() ?>" alt="<?php the_title(); ?>"/></a>
                <p class="zhaiyao"><?php echo mb_strimwidth(strip_tags(apply_filters('the_content', $post->post_content)), 0, 304," ...... "); ?><a href="<?php the_permalink(); ?>" rel="nofollow"> 阅读全文 &raquo;</a></p>
            </span>
		<?php endwhile; ?>
        <span class="pagination"><?php asylmf_pagination($query_string); ?></span>
		<?php else : ?>
        <span class="entry">
        	<h1 id="entrytitl">
                <p class="title">搜索 “ <?php the_search_query();?> ” 没有结果.</p>
            </h1>
            <div class="neiwen">
                请确保没有错别字.
            </div>
        </span>
        <?php endif; ?>
    </div>
	
</div>
<?php get_sidebar(); ?>
<?php get_footer(); ?>

