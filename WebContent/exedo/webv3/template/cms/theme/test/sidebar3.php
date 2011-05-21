<div class="sidebar2">
     <ul>
		<?php if ( function_exists('dynamic_sidebar') && dynamic_sidebar(3) ) : else : ?>
            <li>
				<h2><?php _e('Categories'); ?></h2>
                <ul>
                <?php wp_list_cats('sort_column=name&optioncount=1&hierarchical=0'); ?>
                </ul>
            </li>
            <li>
                <h2><?php _e('Archives'); ?></h2>
                    <ul>
                    <?php wp_get_archives('type=monthly'); ?>
                    </ul>
            </li>
    
            <li>
            <h2><?php _e('Links'); ?></h2>
                <ul>
                 <?php get_links(2, '<li>', '</li>', '', TRUE, 'url', FALSE); ?>
                 </ul>
            </li> 
        <?php endif; ?>
	</ul>
</div>
