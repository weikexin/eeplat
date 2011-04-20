<form id="searchform" method="get" action="<?php bloginfo('home'); ?>">
	<input type="text" value="<?php the_search_query(); ?>" name="s" id="s" size="23" />
	<input type="submit" value="" class="search_btn" />
</form>
