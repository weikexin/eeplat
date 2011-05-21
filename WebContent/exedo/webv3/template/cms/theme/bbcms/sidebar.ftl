<div class="sidebar">
	<ul>
	<?php if ( function_exists('dynamic_sidebar') && dynamic_sidebar(2) ) : else : ?>
        <li><h2><?php _e('Archives'); ?></h2>
            <ul>
            <?php wp_get_archives('type=monthly'); ?>
            </ul>
        </li>
        <li><h2><?php _e('Pages'); ?></h2>
			<ul>
				<?php wp_list_pages('title_li=' ); ?>
			</ul>
		</li>
		<li><h2><?php _e('Blogroll'); ?></h2>
			<ul>
				<?php wp_list_bookmarks('title_li=&categorize=0'); ?>
			</ul>
		</li>
		<li><h2><?php _e('Meta'); ?></h2>
			<ul>
				<?php wp_register(); ?>
				<li><?php wp_loginout(); ?></li>
			</ul>
		</li>
		<?php endif; ?>
	</ul>
    
</div>
