</div>

<div id="links">
	<ul>
        <li><?php _e('友情链接:','AsYLMF'); ?></li>
        <?php wp_list_bookmarks('title_li=&categorize=0&limit=12'); ?>
    </ul>
</div>

<div id="nav3">
	<?php 
		$footNav = '';
		if (function_exists('wp_nav_menu')) {
			$footNav = wp_nav_menu( array( 'theme_location' => 'footercat','echo' => false, 'fallback_cb' => '' ) );};
		if ($footNav == '') { ?>
	<ul>
    	<?php wp_list_categories('title_li=&depth=1'); ?><?php wp_list_pages('title_li=&depth=1'); ?>
    </ul>
    <?php }
			else echo($footNav); 
		?>
</div>

<div id="footer">
	<p class="alignleft">Copyright <?php echo comicpress_copyright(); ?> <a href="<?php bloginfo('siteurl'); ?>" title="<?php bloginfo('name'); ?>"><?php bloginfo('name'); ?></a>. All rights reserved.</p>
    <p class="alignright">Powered by <a href="http://www.wordpress.org/" target="_blank">WordPress</a>. Template by <a href="http://donliang.com" target="_blank">DonLiang</a>.</p>
</div>

<?php wp_footer(); ?>

</body>

</html>