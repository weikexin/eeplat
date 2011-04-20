<#include "header.ftl" />
<div id="content">
	<div id="mainleft">	
        	<div id="KinSlideshow" style="visibility:hidden;">
            	<?php $previous_posts = get_posts('numberposts=6'); foreach($previous_posts as $post) : setup_postdata($post); ?>
                <a href="<?php the_permalink(); ?>" target="_blank" title=""><?php asylmf_slide_image()?></a>
                <?php endforeach; ?>
            </div>
        <#if have_posts()><@list_posts>
            <span class="wenzhang">
				<h1>
                	<p class="title"><a href="<?php the_permalink(); ?>" title="${dataMap.posts_title}">${dataMap.posts_title}</a></p>
                    <p class="pinglun"><?php the_time('Y骞磎鏈坖鏃�); ?></p>
                </h1>
                <a href="<?php the_permalink(); ?>" title="${dataMap.posts_title}"><img src="<?php echo catch_that_image() ?>" alt="${dataMap.posts_title}"/></a>
                <p class="zhaiyao"><?php echo mb_strimwidth(strip_tags(apply_filters('the_content', $post->post_content)), 0, 304," ...... "); ?><a href="<?php the_permalink(); ?>" rel="nofollow"> 闃呰鍏ㄦ枃 &raquo;</a></p>
            </span>
		</@list_posts>
        <span class="pagination"><?php asylmf_pagination($query_string); ?>
</span>
        </#if>
    </div>
    <#include "sidebar.ftl" />
<#include "footer.ftl" />