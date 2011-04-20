<?php get_header();?>
<div id="content">
	<div id="mainleft">	
        	<div id="KinSlideshow" style="visibility:hidden;">
            	<?php $previous_posts = get_posts('numberposts=6'); foreach($previous_posts as $post) : setup_postdata($post); ?>
                <a href="<?php the_permalink(); ?>" target="_blank" title="<?php the_title(); ?>"><?php asylmf_slide_image()?></a>
                <?php endforeach; ?>
            </div>
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
        <span class="pagination"><?php asylmf_pagination($query_string); ?>
</span>
        <?php endif; ?>
    </div>
    <?php get_sidebar(); ?>
<?php get_footer(); ?>