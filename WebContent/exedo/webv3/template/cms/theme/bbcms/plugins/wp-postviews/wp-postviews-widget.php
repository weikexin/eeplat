<?php
/*
Plugin Name: WP-PostViews Widget
Plugin URI: http://lesterchan.net/portfolio/programming.php
Description: Adds a PostViews Widget to display most viewed posts and/or pages on your sidebar. You will need to activate WP-PostViews first.
Version: 1.30
Author: Lester 'GaMerZ' Chan
Author URI: http://lesterchan.net
*/


/*  
	Copyright 2008  Lester Chan  (email : lesterchan@gmail.com)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/


### Function: Init WP-PostViews Widget
function widget_views_init() {
	if (!function_exists('register_sidebar_widget')) {
		return;
	}

	### Function: WP-PostViews Most Viewed Widget
	function widget_views_most_viewed($args) {
		extract($args);
		$options = get_option('widget_views_most_viewed');
		$title = htmlspecialchars(stripslashes($options['title']));		
		if (function_exists('get_most_viewed')) {
			echo $before_widget.$before_title.$title.$after_title;
			echo '<ul>'."\n";
			get_most_viewed($options['mode'], $options['limit'], $options['chars']);
			echo '</ul>'."\n";
			echo $after_widget;
		}		
	}

	### Function: WP-PostViews Most Viewed Widget Options
	function widget_views_most_viewed_options() {
		$options = get_option('widget_views_most_viewed');
		if (!is_array($options)) {
			$options = array('title' => __('Most Viewed', 'wp-postviews'), 'mode' => 'post', 'limit' => 10, 'chars' => 0);
		}
		if ($_POST['most_viewed-submit']) {
			$options['title'] = strip_tags($_POST['most_viewed-title']);
			$options['mode'] = strip_tags($_POST['most_viewed-mode']);
			$options['limit'] = intval($_POST['most_viewed-limit']);
			$options['chars'] = intval($_POST['most_viewed-chars']);
			update_option('widget_views_most_viewed', $options);
		}
		echo '<p style="text-align: left;"><label for="most_viewed-title">';
		_e('Title', 'wp-postviews');
		echo ': </label><input type="text" id="most_viewed-title" name="most_viewed-title" value="'.htmlspecialchars(stripslashes($options['title'])).'" /></p>'."\n";
		echo '<p style="text-align: left;"><label for="most_viewed-mode">';
		_e('Show Views For: ', 'wp-postviews');
		echo ' </label>'."\n";
		echo '<select id="most_viewed-mode" name="most_viewed-mode" size="1">'."\n";
		echo '<option value="both"';
		selected('both', $options['mode']);
		echo '>';
		_e('Post &amp Page', 'wp-postviews');
		echo '</option>'."\n";
		echo '<option value="post"';
		selected('post', $options['mode']);
		echo '>';
		_e('Post', 'wp-postviews');
		echo '</option>'."\n";
		echo '<option value="page"';
		selected('page', $options['mode']);
		echo '>';
		_e('Page', 'wp-postviews');
		echo '</option>'."\n";
		echo '</select>&nbsp;&nbsp;';
		_e('Only', 'wp-postviews');
		echo '</p>'."\n";
		echo '<p style="text-align: left;"><label for="most_viewed-limit">';
		_e('Limit', 'wp-postviews');
		echo ': </label><input type="text" id="most_viewed-limit" name="most_viewed-limit" value="'.intval($options['limit']).'" size="3" /></p>'."\n";
		echo '<p style="text-align: left;"><label for="most_viewed-chars">';
		_e('Post Title Length (Characters)', 'wp-postviews');
		echo ': </label><input type="text" id="most_viewed-chars" name="most_viewed-chars" value="'.intval($options['chars']).'" size="5" />&nbsp;&nbsp;'."\n";
		_e('(<strong>0</strong> to disable)', 'wp-postviews');
		echo '</p>'."\n";
		echo '<input type="hidden" id="most_viewed-submit" name="most_viewed-submit" value="1" />'."\n";
	}
	// Register Widgets
	register_sidebar_widget(array('Most Viewed', 'wp-postviews'), 'widget_views_most_viewed');
	register_widget_control(array('Most Viewed', 'wp-postviews'), 'widget_views_most_viewed_options', 400, 200);
}


### Function: Load The WP-PostViews Widget
add_action('plugins_loaded', 'widget_views_init')
?>