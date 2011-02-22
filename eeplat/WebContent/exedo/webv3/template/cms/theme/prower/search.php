<?php get_header(); ?>
<div id="mainBox">
	<div id="content">

	<?php if (have_posts()) : ?>

		<h2 class="pagetitle">搜索结果</h2>

		<?php while (have_posts()) : the_post(); ?>

			<div class="entry">
<h3 id="post-<?php the_ID(); ?>"><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php the_title(); ?></a></h3>	

<div class="time"><?php the_time('Y-m-d，l') ?> | <?php if(function_exists('the_views')) { the_views(); } ?> 分类： <?php the_category(', ') ?> | <?php edit_post_link('编辑', '', ' | '); ?> <?php the_tags('标签：','') ?><?php if(function_exists('the_views')) { the_views(); } ?></div>


					<div class="content"><?php the_content('<span class="read">全文阅读 &raquo;</span>'); ?></div>	


				<div class="postmetadata"><span class="cmt"><?php comments_popup_link('发表评论 &#187;', '1条评论 &#187;', '%条评论 &#187;'); ?></span></div>		
</div>

		<?php endwhile; ?>
		<?php
           if (function_exists('wp_pagebar'))
              wp_pagebar();
          ?>
	<?php else : ?>
	<div class="entry">
		<h2 class="center">没有找到您所要找的内容，请重新搜索！</h2>

</div>
	</div>

<?php get_sidebar(); ?>

<?php get_footer(); ?>