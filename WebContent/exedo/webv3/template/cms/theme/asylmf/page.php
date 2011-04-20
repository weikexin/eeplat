<?php get_header();?>
<div id="content">
	<div id="mainleft">
    	<p class="breadcrumb">当前位置：<?php the_breadcrumb(); ?></p>
    	<?php if(have_posts()) : ?><?php while(have_posts()) : the_post(); ?>
        	<span class="entry">
				<h1 id="entrytitl">
                	<p class="title"><a href="<?php the_permalink(); ?>" title="<?php the_title(); ?>"><?php the_title(); ?></a></p>
                </h1>
                <div class="neiwen">
					<?php the_content(); ?>
                    <?php link_pages('<p><strong>页面:</strong>', '</p>', 'number'); ?>
                </div>
        	</span>
		<?php endwhile; ?>
        <?php endif; ?>
    </div>
    <?php get_sidebar(); ?>
<?php get_footer(); ?>