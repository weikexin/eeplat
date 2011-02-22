<?php
/* 
Plugin Name: Genki Announcement
Version: 1.4
Plugin URI: http://ericulous.com/2007/03/09/genki_announcement/
Description: Display an announcement on your blog
Author: Genkisan</a> - <a href="http://wp.mmrt-jp.net/">i18n patched by Masayan</a><a>
Author URI: http://ericulous.com
*/
load_plugin_textdomain('genki_announce');

add_action ('admin_menu', 'genki_announcement_menu');
add_action('wp_head', 'genki_announcement_loopstart');
add_action('genki_announcement_cronon', 'genki_announcement_showmsg');
add_action('genki_announcement_cronoff', 'genki_announcement_hidemsg');
//if ($_GET['page'] == 'genki_announcement.php') {
	//add_action('admin_head', 'add_tinymce');
//}

function genki_announcement_menu() {
	add_option('genki_announcement_location', '0');
	add_option('genki_announcement_msg', '');
	add_option('genki_announcement_multimsg', '0');
	add_option('genki_announcement_msgguests', '');
	add_option('genki_announcement_msgsubscribers', '');
	add_option('genki_announcement_msgcontributors', '');
	add_option('genki_announcement_msgauthors', '');
	add_option('genki_announcement_msgeditors', '');
	add_option('genki_announcement_msgadmins', '');
	add_option('genki_announcement_timeon', '');
	add_option('genki_announcement_timeoff', '');
	add_option('genki_announcement_bordercolor', '#ffffff');
	add_option('genki_announcement_bgcolor', '#ffffff');
    if (function_exists('add_options_page')) {
		add_options_page(__('Genki Announcement','genki_announce'), __('Genki Announcement','genki_announce'), 8, basename(__FILE__), 'genki_announcement_manage');
	}
}

function genki_announcement_loopstart() {
	if (get_option('genki_announcement_location') == 1) {
			add_action ('loop_start', 'genki_announcement_showmsg');
	}
}

function genki_announcement() {
	if (get_option('genki_announcement_location') == 3) {
			genki_announcement_showmsg();
	}
}

function genki_announcement_showmsg() {
	$bordercolor = get_option('genki_announcement_bordercolor');
	$bgcolor = get_option('genki_announcement_bgcolor');
	$timeon = get_option('genki_announcement_timeon');

	global $msgShownBefore;
	if (($timeon == '') || (strtotime($timeon . ':00 GMT') < (time() + (get_option('gmt_offset') * 3600)))) {
	if (get_option('genki_announcement_multimsg') == '0') {
		if (!isset($msgShownBefore)) {
			$msgShownBefore = 'yes';
			echo '<div style="border:none ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msg')) . '</div>';
		}
	}
	else {
		global $msgShownBefore;
		if (!is_user_logged_in()) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgguests')) . '</div>'; }}
		if (current_user_can('level_10')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgadmins')) . '</div>'; }}
		if (current_user_can('level_9')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgadmins')) . '</div>'; }}
		if (current_user_can('level_8')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgadmins')) . '</div>'; }}
		if (current_user_can('level_7')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgeditors')) . '</div>'; }}
		if (current_user_can('level_5')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgeditors')) . '</div>'; }}
		if (current_user_can('level_6')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgeditors')) . '</div>'; }}
		if (current_user_can('level_4')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgauthors')) . '</div>'; }}
		if (current_user_can('level_3')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgauthors')) . '</div>'; }}
		if (current_user_can('level_2')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgauthors')) . '</div>'; }}
		if (current_user_can('level_1')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgcontributors')) . '</div>'; }}
		if (current_user_can('level_0')) { if (!isset($msgShownBefore)) { $msgShownBefore = 'yes'; echo '<div style="border: 1px solid ' . $bordercolor . ';background-color:' . $bgcolor . '">' . stripslashes(get_option('genki_announcement_msgsubscribers')) . '</div>'; }}
	}
	update_option('genki_announcement_timeon', '');
	}
}

function genki_announcement_hidemsg() {
	update_option('genki_announcement_location', '0');
	update_option('genki_announcement_timeoff', '');
}

function genki_announcement_manage() {
if (isset($_POST['update_message'])) {

	?><div id="message" class="updated fade"><p><strong><?php

	update_option('genki_announcement_location', $_POST["location"]);
	update_option('genki_announcement_multimsg', $_POST["multimsg"]);
	update_option('genki_announcement_msg', $_POST["messageall"]);
	update_option('genki_announcement_msgguests', $_POST["msgguests"]);
	update_option('genki_announcement_msgsubscribers', $_POST["msgsubscribers"]);
	update_option('genki_announcement_msgcontributors', $_POST["msgcontributors"]);
	update_option('genki_announcement_msgauthors', $_POST["msgauthors"]);
	update_option('genki_announcement_msgeditors', $_POST["msgeditors"]);
	update_option('genki_announcement_msgadmins', $_POST["msgadmins"]);
	update_option('genki_announcement_timeon', $_POST["timeon"]);
	update_option('genki_announcement_timeoff', $_POST["timeoff"]);
	update_option('genki_announcement_bordercolor', $_POST['bordercolor']);
	update_option('genki_announcement_bgcolor', $_POST['bgcolor']);
	
	$timeon = $_POST["timeon"];
	if ($timeon != '') {
		$post_date_gmt = strtotime($timeon . ':00 GMT') - (get_option('gmt_offset') * 3600);
		wp_clear_scheduled_hook('genki_announcement_cronon');
		wp_schedule_single_event($post_date_gmt, 'genki_announcement_cronon');
	}

	$timeoff = $_POST["timeoff"];
	if ($timeoff != '') {
		$post_date_gmt = strtotime($timeoff . ':00 GMT') - (get_option('gmt_offset') * 3600);
		wp_clear_scheduled_hook('genki_announcement_cronoff');
		wp_schedule_single_event($post_date_gmt, 'genki_announcement_cronoff');
	}

	echo __("Options Updated!","genki_announce");

	?></strong></p></div><?php

}
global $wp_db_version;
$location = get_option('genki_announcement_location');
$msg = get_option('genki_announcement_msg');
$multimsg = get_option('genki_announcement_multimsg');
$msgguests = get_option('genki_announcement_msgguests');
$msgsubscribers = get_option('genki_announcement_msgsubscribers');
$msgcontributors = get_option('genki_announcement_msgcontributors');
$msgauthors = get_option('genki_announcement_msgauthors');
$msgeditors = get_option('genki_announcement_msgeditors');
$msgadmins = get_option('genki_announcement_msgadmins');
$timeon = get_option('genki_announcement_timeon');
$timeoff = get_option('genki_announcement_timeoff');
$bordercolor = get_option('genki_announcement_bordercolor');
$bgcolor = get_option('genki_announcement_bgcolor');

?>
	<div class=wrap>
	<h2><?php _e('Genki Announcement','genki_announce'); ?></h2>
		<form name="formamt" method="post" action="<?php echo $_SERVER["REQUEST_URI"]; ?>">
			<fieldset class="options">
				<legend><?php _e('Announcement status','genki_announce'); ?>
					<p>
					<input name="location" type="radio" id="location" value="0" <?php if ($location == '0') echo 'checked' ; ?>> <?php _e('Dont\'t show announcement','genki_announce'); ?></input><br />
					<input name="location" type="radio" id="location" value="1" <?php if ($location == '1') echo 'checked' ; ?>> <?php _e('Show above first post (may not work in some themes)','genki_announce'); ?></input><br />
					<input name="location" type="radio" id="location" value="2" <?php if ($location == '2') echo 'checked' ; ?>> <?php _e('Show in Widget (remember to activate the Genki Announcement Widget)','genki_announce'); ?></input><br />
					<input name="location" type="radio" id="location" value="3" <?php if ($location == '3') echo 'checked' ; ?>> <?php _e('Show where I insert the &lt;?php if(function_exists(\'genki_announcement\')) { genki_announcement(); } ?> template function','genki_announce'); ?></input>
					</p>
				</legend>
			</fieldset>

			<hr style="border:1px dashed #ccc" />

			<fieldset class="options">
                <legend><?php _e('Style Announcement Box','genki_announce'); ?>
					<p><?php _e('Border Color','genki_announce'); ?>: <input name="bordercolor" id="bordercolor" type="text" value="<?php echo $bordercolor; ?>"></input><br />
					<?php _e('Background Color','genki_announce'); ?>: <input name="bgcolor" id="bgcolor" type="text" value="<?php echo $bgcolor; ?>"></input>
					</p>
				</legend>
			</fieldset>

            <hr style="border:1px dashed #ccc" />

			<fieldset class="options">
				<p>
					<input type="radio" id="multimsg" name="multimsg" value="0" <?php if ($multimsg == '0') echo 'checked' ; ?> onClick="javascript:document.getElementById('msgsep').style.display='none';document.getElementById('msgall').style.display='block'"></input> <?php _e('Single message for all roles/users&nbsp;&nbsp;&nbsp;','genki_announce'); ?>
					<input type="radio" id="multimsg" name="multimsg" value="1" <?php if ($multimsg == '1') echo 'checked' ; ?> onClick="javascript:document.getElementById('msgsep').style.display='block';document.getElementById('msgall').style.display='none'"></input> <?php _e('Different message for different roles/users','genki_announce'); ?>
				</p>
			</fieldset>

			<div id="msgall" <?php if ($multimsg == '1') { ?> style="display:none" <?php } ?>>
			<fieldset class="options">
				<legend><?php _e('Message for All ~ HTML allowed','genki_announce'); ?>
					<p><textarea name="messageall" id="messageall" cols="40" rows="5" style="width: 80%;"><?php echo stripslashes($msg); ?></textarea></p>
				</legend>
			</fieldset>
			</div>

			<div id="msgsep" <?php if ($multimsg == '0') { ?> style="display:none" <?php } ?>>
			<fieldset class="options">
				<legend><?php _e('Message for Guests ~ HTML allowed','genki_announce'); ?>
					<p><textarea name="msgguests" id="msgguests" cols="40" rows="5" style="width: 80%;"><?php echo stripslashes($msgguests); ?></textarea></p>
				</legend>
			</fieldset>
			<fieldset class="options">
				<legend><?php _e('Message for Subscribers ~ HTML allowed','genki_announce'); ?>
					<p><textarea name="msgsubscribers" id="msgsubscribers" cols="40" rows="5" style="width: 80%;"><?php echo stripslashes($msgsubscribers); ?></textarea></p>
				</legend>
			</fieldset>
			<fieldset class="options">
				<legend><?php _e('Message for Contributors ~ HTML allowed','genki_announce'); ?>
					<p><textarea name="msgcontributors" id="msgcontributors" cols="40" rows="5" style="width: 80%;"><?php echo stripslashes($msgcontributors); ?></textarea></p>
				</legend>
			</fieldset>
			<fieldset class="options">
				<legend><?php _e('Message for Authors ~ HTML allowed','genki_announce'); ?>
					<p><textarea name="msgauthors" id="msgauthors" cols="40" rows="5" style="width: 80%;"><?php echo stripslashes($msgauthors); ?></textarea></p>
				</legend>
			</fieldset>
			<fieldset class="options">
				<legend><?php _e('Message for Editors ~ HTML allowed','genki_announce'); ?>
					<p><textarea name="msgeditors" id="msgeditors" cols="40" rows="5" style="width: 80%;"><?php echo stripslashes($msgeditors); ?></textarea></p>
				</legend>
			</fieldset>
			<fieldset class="options">
				<legend><?php _e('Message for Admins ~ HTML allowed','genki_announce'); ?>
					<p><textarea name="msgadmins" id="msgadmins" cols="40" rows="5" style="width: 80%;"><?php echo stripslashes($msgadmins); ?></textarea></p>
				</legend>
			</fieldset>
			</div>
			
			<hr style="border:1px dashed #ccc" />
            
			<fieldset class="options">
				<legend><?php _e('Preview','genki_announce'); ?>
					<p><?php genki_announcement_showmsg() ?></p>
                </legend>
			</fieldset>
            
  			<hr style="border:1px dashed #ccc" />
			
			<fieldset class="options">
				<legend><?php _e('Date to switch on announcement:','genki_announce'); ?>
				<?php
				if ( $wp_db_version < 3582 ) {
				?>
				<?php _e('This feature requires Wordpress 2.1 and above','genki_announce'); ?>
				<?php
				} else {
				?>
				<input name="timeon" type="text" id="timeoon" value="<?php echo $timeon ?>"></input><p><?php _e('- Format = YYYY-MM-DD hh:mm e.g 2007-08-18 13:59<br />- The time now is','genki_announce'); ?> <?php echo substr(current_time('mysql', 0),0,-3); ?><br /><?php _e('- Leave blank to turn on immediately','genki_announce'); ?></p>
				<?php
				}
				?>
			</fieldset>

			<fieldset class="options">
				<legend><?php _e('Date to switch off announcement:','genki_announce'); ?>
				<?php
				if ( $wp_db_version < 3582 ) {
				?>
				<?php _e('This feature requires Wordpress 2.1 and above','genki_announce'); ?>
				<?php
				} else {
				?>
				<input name="timeoff" type="text" id="timeoff" value="<?php echo $timeoff ?>"></input><p><?php _e('- Format = YYYY-MM-DD hh:mm e.g 2007-08-18 13:59<br />- The time now is','genki_announce'); ?> <?php echo substr(current_time('mysql', 0),0,-3); ?><br /><?php _e('- Leave blank to turn off manually','genki_announce'); ?></p>
				<?php
				}
				?>
			</fieldset>
			<p class="submit">
				<input type="submit" name="update_message" value="<?php _e('Update Options &raquo;','genki_announce'); ?>" />
			</p>
		</form>
	</div>
<?php
}

//Widgets Stuff
function widget_genkiannouncement_init() {

	if ( !function_exists('register_sidebar_widget') )
		return;

	function widget_genkiannouncement($args) {
		extract($args);
		
		$options = get_option('widget_genkiannouncement');
		$title = $options['title'];
		
		if (get_option('genki_announcement_location') == 2) {
		echo $before_widget . $before_title . $title . $after_title;
		$url_parts = parse_url(get_bloginfo('home'));

		genki_announcement_showmsg();
		echo $after_widget;
		}
	}

	function widget_genkiannouncement_control() {

		$options = get_option('widget_genkiannouncement');
		if ( !is_array($options) )
			$options = array('title'=>'');
		if ( $_POST['genkiannouncement-submit'] ) {
			$options['title'] = strip_tags(stripslashes($_POST['genkiannouncement-title']));
			update_option('widget_genkiannouncement', $options);
		}

		$title = htmlspecialchars($options['title'], ENT_QUOTES);
		
		echo '<p style="text-align:right;"><label for="genkiannouncement-title">' . __('Title:','genki_announce') . ' <input style="width: 200px;" id="genkiannouncement-title" name="genkiannouncement-title" type="text" value="'.$title.'" /></label></p>';
		$site_url = get_option('home');
		echo sprintf(__('<label>To set your message and other options, go to <a href="%s/wp-admin/options-general.php?page=genki_announcement.php">Genki Announcement Options page</a></label>','genki_announce'),$site_url);
		echo '<input type="hidden" id="genkiannouncement-submit" name="genkiannouncement-submit" value="1" />';
	}
	
	register_sidebar_widget(array('Genki Announcement', 'widgets'), 'widget_genkiannouncement');
	register_widget_control(array('Genki Announcement', 'widgets'), 'widget_genkiannouncement_control', 300, 100);
}

add_action('widgets_init', 'widget_genkiannouncement_init');
?>