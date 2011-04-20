<div class="sidebar2">
	<ul>
	<?php if ( function_exists('dynamic_sidebar') && dynamic_sidebar(2) ) : else : ?>                
        <li>
			<h2><?php _e('Categories'); ?></h2>
                <ul>
                <?php wp_list_cats('sort_column=name&optioncount=1&hierarchical=0'); ?>
                </ul>
            </li>
        <li>
        <h2><?php _e('Meta'); ?></h2>
            <ul>
            <?php wp_register(); ?>
            <li><?php wp_loginout(); ?></li>
            <?php wp_meta(); ?>
            </ul>
        </li>
	<?php endif; ?>
	</ul>
</div>