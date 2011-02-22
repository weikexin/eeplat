<?php
/*
Template Name: Page
*/
?>

<?php get_header(); ?>
<!--文章正文 开始 -->
<div class="taobaobei">
        <div class="subnavi">
            <div class="subnavi-l">当前位置: <a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> > <?php the_title(); ?></div>
        </div>

    <div class="bb_post">
        <?php if (have_posts()) : ?>
            <?php while (have_posts()) : the_post(); ?>
                <div class="bb_list bb_list_page" id="post-<?php the_ID(); ?>">
                    <h2 class="txtcenter"><?php the_title(); ?></h2>
                    <div class="bb_detail baobei_info"><?php the_content(''); ?></div>
                </div>
    
            <?php endwhile; ?>
    
        <?php else : ?>
            <div class="bb_post">
                 <h2>抱歉,没有找到合适您的商品！</h2>
                 <p>请您<a href="<?php echo get_settings('home'); ?>">返回首页<?php echo $langblog;?></a>或在搜索中查找您所需的信息,给您带来不便,敬请谅解！</p>
            </div>
        <?php endif; ?>
    </div>
</div>
<!--文章正文 结束 -->

<!-- 侧边栏2 开始 -->
<?php include (TEMPLATEPATH . '/sidebar3.php'); ?>
<!-- 侧边栏2 结束 -->
<br clear="all" />
<?php get_footer(); ?>
</body>
</html>
