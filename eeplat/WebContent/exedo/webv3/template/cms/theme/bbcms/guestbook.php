<?php
/*
Template Name: Guestbook
*/
?>

<?php get_header(); ?>

<!-- 左侧 开始 -->
<div class="con_left">

<!-- 今日更新 开始 -->
<div class="entry_list">
	<?php if (have_posts()) : ?>
    
    <div class="postnavi">
		<div class="site-navi">您的位置: <a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> > 留言</div>
	</div>
    
		<?php while (have_posts()) : the_post(); ?>
			<div class="post" id="post-<?php the_ID(); ?>">
					<h2 class="mid_single"><?php the_title(); ?></h2>
					<div class="postbg"><?php the_content(''); ?></div>
                    <br />
					<?php comments_template('/guestcomments.php'); ?>
            </div>        
		<?php endwhile; ?>
	<?php else : ?>
		<div class="post">
			<h2 class="center search">抱歉,没有找到合适的文章.</h2>
			<p class="center">请您<a href="<?php echo get_settings('home'); ?>">返回首页<?php echo $langblog;?></a>或在搜索中查找您所需的信息.带来不便,敬请谅解!</p>
        </div>
	<?php endif; ?>
</div>
<!-- 今日更新 结束 -->
<div class="spacebox"></div>
<!-- 翻页导航 开始 -->
<div class="wp-pagenavi pageNavi">
    <?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } ?>
</div>
<!-- 翻页导航 结束 -->
</div>
<!-- 左侧 结束 -->

<!-- 右侧 开始 -->
<div class="con_right">
<?php include (TEMPLATEPATH . '/topbar.php'); ?>
<?php include (TEMPLATEPATH . '/sidebar.php'); ?>
</div>

<br clear="all" />
<?php get_footer(); ?>
</div>
<!-- 右侧 结束 -->
</body>
</html>