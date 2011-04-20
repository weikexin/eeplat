<?php get_header(); ?>

<!-- 左侧 开始 -->
<div class="con_left">

<!-- 今日更新 开始 -->
<div class="entry_list">
	<?php if (have_posts()) : ?>
    
    <div class="postnavi">
 	  <?php $post = $posts[0]; // Hack. Set $post so that the_date() works. ?>
 	  <?php /* If this is a category archive */ if (is_category()) { ?>
		<div class="site-navi">您的位置: <a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> > <?php the_category(', ') ?></div>
 	  <?php /* If this is a tag archive */ } elseif( is_tag() ) { ?>
		<div class="site-navi">搜索到符合 <strong><?php single_tag_title(); ?></strong> 标签的相关文章</div>
 	  <?php /* If this is a daily archive */ } elseif (is_day()) { ?>
		<div class="site-navi">搜索到 <strong><?php the_time('Y, F jS'); ?></strong> 时间内的文章</div>
 	  <?php /* If this is a monthly archive */ } elseif (is_month()) { ?>
		<div class="site-navi">搜索到 <strong><?php the_time('Y, F'); ?></strong> 时间内的文章</div>
 	  <?php /* If this is a yearly archive */ } elseif (is_year()) { ?>
		<div class="site-navi">搜索到 <strong><?php the_time('Y'); ?></strong> 时间内的文章</div>
	  <?php /* If this is an author archive */ } elseif (is_author()) { ?>
		<div class="site-navi">搜索到该作者的所有文章</div>
 	  <?php /* If this is a paged archive */ } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
		<div class="site-navi"><a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a> 存档</div>
 	  <?php } ?>
	</div>
    
		<?php while (have_posts()) : the_post(); ?>
        
			<div class="post" id="post-<?php the_ID(); ?>">

				<h2><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php the_title(); ?></a></h2>
				<div class="small_desc"><?php the_author_posts_link(); ?> 发表于 <?php the_time('Y-m-d H:i'); ?> 浏览次数:<?php if(function_exists('the_views')) { the_views(); } ?> <?php edit_post_link('Edit', '', ''); ?></div>

					<div class="postbg"><?php the_content(''); ?></div>

<div class="postother">关键字:<?php the_tags(' ', ', ', ''); ?> | 分类: <?php the_category(', ') ?> | 评论数: <?php comments_popup_link('0 ', '1 ', '% '); ?> | <a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>"><strong>阅读全文</strong></a></div>

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
    <?php if(function_exists('wp_pagenavi')) { wp_pagenavi(); } 
			else { ?>
			<div class="right"><?php next_posts_link('Next Page &raquo;') ?></div>
			<div class="left"><?php previous_posts_link('&laquo; Previous Page') ?></div>
	<?php } ?>
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