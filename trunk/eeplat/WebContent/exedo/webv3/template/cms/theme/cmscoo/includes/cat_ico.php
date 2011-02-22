<?php if (get_theme_mod('showcatico') == 'Yes') { ?>
	<div class="cat_ico">
		<?php foreach((get_the_category()) as $cat)
			{
			$catname =$cat->category_nicename;
			$cattitle=$cat->cat_name;
			echo "<a href=/category/";
			echo $catname;
			echo "/>";
			echo "<img src=/wp-content/caticon/";
			echo $catname;
			echo ".png alt=\"$catname category \" title=\"$cattitle\" /></a>\n";
			}
		?>
	</div>
	<?php } else { ?>
	<?php } ?>