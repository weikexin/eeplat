
		<?php $display_categories = array(1,2,3,4); $i = 1; 
		foreach ($display_categories as $category) { ?> <!-- 如果要增加栏目，请在array中输入你要显示的cat分类的ID -->
        
    	<!-- 分类列表 开始 -->
        	<div id="cat-<?php echo $i; ?>" class="cat-left">
            	<?php query_posts("showposts=10&cat=$category")?><!-- showposts 输出文章的“数目”， “cat=$category”，输出指定分类的文章 -->
            	<div class="cat-left-content">
                <h4><a href="<?php echo get_category_link($category);?>" title="<?php echo strip_tags(category_description($category)); ?>"><?php single_cat_title(); ?></a></h4><!-- 输出指定分类的标题 -->
                <ul>
                    <?php while (have_posts()) : the_post(); ?><!-- Loop 开始 -->
                    <li><span class="titlel"><a href="<?php the_permalink() ?>" rel="bookmark" class="title"><?php the_title(); ?></a></span><span class="timer"><?php the_time('m-d'); ?></span></li><!-- 由上述条件指定分类的文章标题列表 -->
                    <?php endwhile; ?><!-- Loop 结束 -->
                </ul>
               </div>
            </div>
        <!-- 分类列表 结束 -->
    <?php $i++; ?>
    	<?php } ?>      
<div class="spacebox"></div>
 <?php query_posts(''); ?>