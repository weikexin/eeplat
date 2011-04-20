<div class="topbar">
      	<ul>
		<?php if ( function_exists('dynamic_sidebar') && dynamic_sidebar(1) ) : else : ?>
            <li>
            <h2><?php _e('Categories'); ?></h2>
                <ul>
                <?php wp_list_cats('sort_column=name&optioncount=1&hierarchical=0'); ?>
                </ul>
            </li>
            <?php endif; ?>

<!-- 所在分类相关文章 开始 -->
            <li>
            	<?php
                    //Gets category and author info
                    global $wp_query;
                    $bm_cats = get_the_category();		
                    $tempQuery = $wp_query;
                    
                    // related category posts
                    $bm_clist = ""; 
                    forEach( $bm_cats as $bm_c ) {
                        if( $bm_clist != "" ) { $bm_clist .= ","; }
                        $bm_clist .= $bm_c->cat_ID;
                    }
                    $newQuery = "posts_per_page=10&cat=" . $bm_clist;
                    query_posts( $newQuery );
            
                    $bm_categoryPosts = "";
            
                    if (have_posts()) {
                        while (have_posts()) {	
                            the_post();
                            $bm_categoryPosts .= '<li><a href="' . get_permalink() . '">' . the_title( "", "", false ) . '</a></li>'				
                            ;
                        }
                    }
                    
                    $wp_query = $tempQuery; 
            ?>
                    <?php if ( is_single() ) : ?>
                        <h2>该分类最新文章</h2>
                        <ul>
                            <?php echo $bm_categoryPosts; ?>
                        </ul>
                    <?php else : ?>
                        <h2>最新文章</h2>
                         <ul>
                         <?php
                          $lastposts = get_posts('numberposts=10&orderby=post_date&order=DESC');
                          foreach($lastposts as $post) :
                          setup_postdata($post);
                        ?>   
                        <li><a href="<?php the_permalink(); ?>" title="<?php the_title(); ?>"><?php the_title(); ?></a></li>   
                        <?php endforeach; ?>
                        </ul>
                    <?php endif ?>
            </li>        
            <!-- 所在分类相关文章 结束 -->

            <!-- 博友留言 开始 -->
            <li> 
                <h2>最新评论</h2>     
                <ul><?php
                    global $wpdb;
                    $sql = "SELECT DISTINCT ID, post_title, post_password, comment_ID,
                    comment_post_ID, comment_author, comment_date_gmt, comment_approved,
                    comment_type,comment_author_url,
                    SUBSTRING(comment_content,1,30) AS com_excerpt
                    FROM $wpdb->comments
                    LEFT OUTER JOIN $wpdb->posts ON ($wpdb->comments.comment_post_ID =
                    $wpdb->posts.ID)
                    WHERE comment_approved = '1' AND comment_type = '' AND
                    post_password = ''
                    ORDER BY comment_date_gmt DESC
                    LIMIT 10";
                    $comments = $wpdb->get_results($sql);
                    $output = $pre_HTML;
                    
                    foreach ($comments as $comment) {
                    $output .= "\n<li><strong>". strip_tags($comment->comment_author)
                    ."</strong>:" . " <a href=\"" . get_permalink($comment->ID) .
                    "#comment-" . $comment->comment_ID . "\" title=\"on " .
                    $comment->post_title . "\">" . strip_tags($comment->com_excerpt)
                    ."</a></li>";
                    }
                    
                    $output .= $post_HTML;
                    echo $output;?>
                </ul>
            </li>
    </ul>    
</div>
<!-- 博友留言 结束 -->