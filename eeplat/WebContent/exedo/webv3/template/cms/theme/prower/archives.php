<?php
/*
Template Name: Archives
*/
?>

<?php get_header(); ?>
<div id="mainBox">
<div id="content">
<div class="entry">


<h2>Archives by Month:</h2>
	<ul>
		<?php wp_get_archives('type=monthly'); ?>
	</ul>

<h2>Archives by Subject:</h2>
	<ul>
		 <?php wp_list_categories(); ?>
	</ul>

</div></div>
<?php get_sidebar(); ?>
<?php get_footer(); ?>