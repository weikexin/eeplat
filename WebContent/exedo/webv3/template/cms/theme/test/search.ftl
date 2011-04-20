<?php get_header(); ?>
<!--文章列表 开始 -->
    <div class="taobaobei">
    	<!--页面导航 开始 -->
        <div class="subnavi">
          当前位置: <a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> > 搜索结果
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
                            <?php echo mb_strimwidth(strip_tags(apply_filters('the_content', $post->post_content)), 0, 140,"..."); ?> <a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>">详细内容</a>
                        </div>
                    </div>
                <?php endwhile; ?>
        
                <!-- 翻页 开始 -->
                <div class="page_navi">
                    <?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } ?>
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
	<?php include (TEMPLATEPATH . '/sidebar3.php'); ?>
</div>
<!-- 侧边栏2 结束 -->
<br clear="all" />
<?php get_footer(); ?>
</body>
</html>
