<?php get_header(); ?>
	<?php $post = $posts[0]; // Hack. Set $post so that the_date() works. ?>
 	<!--文章列表 开始 -->
    <div class="taobaobei">
    	<!--页面导航 开始 -->
        <div class="subnavi">
          <?php /* If this is a category archive */ if (is_category()) { ?>
            <div class="subnavi-l">当前位置: <a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> > <?php the_category(', ') ?></div>
          <?php /* If this is a tag archive */ } elseif( is_tag() ) { ?>
            <div class="subnavi-l">搜索到符合 <strong><?php single_tag_title(); ?></strong> 标签的相关文章</div>
          <?php /* If this is a daily archive */ } elseif (is_day()) { ?>
            <div class="subnavi-l">搜索到 <strong><?php the_time('Y, F jS'); ?></strong> 时间内的文章</div>
          <?php /* If this is a monthly archive */ } elseif (is_month()) { ?>
            <div class="subnavi-l">搜索到 <strong><?php the_time('Y, F'); ?></strong> 时间内的文章</div>
          <?php /* If this is a yearly archive */ } elseif (is_year()) { ?>
            <div class="subnavi-l">搜索到 <strong><?php the_time('Y'); ?></strong> 时间内的文章</div>
          <?php /* If this is an author archive */ } elseif (is_author()) { ?>
            <div class="subnavi-l">搜索到该作者的所有文章</div>
          <?php /* If this is a paged archive */ } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
            <div class="subnavi-l"><a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> 存档</div>
          <?php } ?>
        </div>
        <!--页面导航 结束 -->
		<!-- 分类文章列表 开始 -->
        <div class="bb_post">
            <?php if (have_posts()) : ?>
                <?php while (have_posts()) : the_post(); ?>
                    <div class="bb_list" id="baobei-<?php the_ID(); ?>">
                        <h2><a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><?php the_title(); ?></a></h2>
                        <span class="bb_date"><?php the_time('Y-m-d'); ?> <?php edit_post_link('Edit', '', ''); ?></span>
                        <div class="bb_detail">
                            <?php echo mb_strimwidth(strip_tags(apply_filters('the_content', $post->post_content)), 0, 270,"..."); ?> <a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>">详细内容</a>
                        </div>
                    </div>
                <?php endwhile; ?>
        
                <!-- 翻页 开始 -->
                <div class="page_navi">
                    <?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } 
							else { ?>
							<div class="right page_next"><?php next_posts_link('Next Page &raquo;') ?></div>
							<div class="left page_pro"><?php previous_posts_link('&laquo; Previous Page') ?></div>
					<?php } ?>
                </div>
                <!-- 翻页 结束 -->
            <?php else : ?>
                <div class="bb_post">
                    <h2>抱歉,没有找到合适您的商品！</h2>
                <p>请您<a href="<?php echo get_settings('home'); ?>">返回首页<?php echo $langblog;?></a>或在搜索中查找您所需的信息,给您带来不便,敬请谅解！</p>
                </div>
            <?php endif; ?>
        </div>
		<!-- 分类文章列表 结束 -->
    </div>
    <!-- 文章列表 结束 -->
<!-- 侧边栏2 开始 -->
<div class="sidebar2">
	<?php include (TEMPLATEPATH . '/sidebar2.php'); ?>
</div>
<!-- 侧边栏2 结束 -->

<br clear="all" />
<?php get_footer(); ?>
</body>
</html>
