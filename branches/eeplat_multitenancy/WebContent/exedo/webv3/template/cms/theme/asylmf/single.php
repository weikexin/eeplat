<?php get_header();?>
<div id="content">
	<div id="mainleft">
    	<?php if(have_posts()) : ?><?php while(have_posts()) : the_post(); ?>
        	<span class="entry">
				<h1 id="entrytitl">
                	<p class="title">当前位置：<?php the_breadcrumb(); ?></p>
                </h1>
                <div class="entitle">
                	<h2><?php the_title(); ?></h2>
                    <p>时间：<?php the_time('Y-m-j H:i'); ?>&nbsp;&nbsp;分类：<?php the_category(', '); ?>&nbsp;&nbsp;评论数：<?php comments_popup_link('0 条', '1 条', '% 条'); ?>&nbsp;&nbsp;<?php edit_post_link('编辑'); ?></p>
                </div>
                <div class="neiwen">
					<?php the_content(); ?>
                    <?php link_pages('<p><strong>页面:</strong>', '</p>', 'number'); ?>
                    <span class="entags"><?php the_tags('标签: ', ', ', '<br />'); ?></span>
                    <p class="copy">转载请保留出处 - <strong><?php the_permalink(); ?></strong></p>
                </div>
            </span>
            
            <span class="ennav">
            	<p>上一篇：<?php previous_post_link('%link'); ?></p>
                <p>下一篇：<?php next_post_link('%link'); ?></p>
            </span>
            <span class="relatemore">
            	<h2>更多相关阅读</h2>
                <ul>
					<?php
                    $post_tags = wp_get_post_tags($post->ID);
                    if ($post_tags) {
                    foreach ($post_tags as $tag)
                    {
                        // 获取标签列表
                        $tag_list[] .= $tag->term_id;
                    }
                    // 随机获取标签列表中的一个标签
                    $post_tag = $tag_list[ mt_rand(0, count($tag_list) - 1) ];
                    // 该方法使用 query_posts() 函数来调用相关文章，以下是参数列表
                    $args = array(
                            'tag__in' => array($post_tag),
                            'category__not_in' => array(NULL),      // 不包括的分类ID
                            'post__not_in' => array($post->ID),
                            'showposts' => 4,               // 显示相关文章数量
                            'caller_get_posts' => 1
                        );
                    query_posts($args);
                    if (have_posts()) :
                        while (have_posts()) : the_post(); update_post_caches($posts); ?>
                    <li>
                    	<a href="<?php the_permalink(); ?>" title="<?php the_title(); ?>"><img src="<?php echo catch_that_image() ?>" alt="<?php the_title(); ?>"/></a>
                    	<a href="<?php the_permalink(); ?>" rel="bookmark" title="<?php the_title_attribute(); ?>"><?php the_title(); ?></a>
                    </li>
                    <?php endwhile; else : ?>
                        <li>暂无相关文章</li>
                    <?php endif; wp_reset_query(); } ?>
                </ul>
            </span>
            <span class="enmore">
            	<h2>更多推荐阅读</h2>
                <ul>
					<?php
                    $cats = wp_get_post_categories($post->ID);
                    if ($cats) {
                    $cat = get_category( $cats[0] );
                    $first_cat = $cat->cat_ID;
                    $related = $wpdb->get_results("
                    SELECT wp_posts.post_title, wp_posts.guid
                    FROM wp_posts, wp_term_relationships, wp_term_taxonomy
                    WHERE wp_posts.ID = wp_term_relationships.object_id
                    AND {$wpdb->prefix}term_taxonomy.taxonomy = 'category'
                    AND {$wpdb->prefix}term_taxonomy.term_taxonomy_id = {$wpdb->prefix}term_relationships.term_taxonomy_id
                    AND {$wpdb->prefix}posts.post_status = 'publish'
                    AND {$wpdb->prefix}posts.post_type = 'post'
                    AND {$wpdb->prefix}term_taxonomy.term_id = '" . $first_cat . "'
                    AND {$wpdb->prefix}posts.ID != '" . $post->ID . "'
                    ORDER BY RAND( )
                    LIMIT 10");
                    if ( $related ) {
                        foreach ($related as $related_post) {
                    ?>
                        <li>
                        	<a href="<?php echo $related_post->guid; ?>" rel="bookmark" title="<?php echo $related_post->post_title; ?>"><?php echo $related_post->post_title; ?></a>
                    	</li>
                    <?php  } } else { ?>
                        <li>暂无推荐文章</li>
                    <?php } }?>
                </ul>
            </span>
            <span class="comments-template">
                <?php comments_template(); ?>
            </span>
		<?php endwhile; else: ?>
        <h1>什么也找不到！</h1>
		<p>抱歉, 你要找的文章不在这里. 请返回首页继续阅读其他文章.</p>
        <?php endif; ?>
    </div>
    <?php get_sidebar(); ?>
<?php get_footer(); ?>