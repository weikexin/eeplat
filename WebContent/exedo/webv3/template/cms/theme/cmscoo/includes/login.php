<?php
	get_currentuserinfo();
	global $current_user, $user_ID, $user_identity;
	if( !$user_ID || '' == $user_ID ) {
?>            
	<!-- if not logged -->
	<form action="<?php echo wp_login_url( get_permalink() ); ?>" method="post" id="loginform">
		<div class="loginblock">
			<p class="check"><input type="checkbox" name="rememberme" id="modlogn_remember" class="inputbox" value="yes" alt="Remember Me" /></p>
			<p class="lefted"><button value="Submit" id="submit" type="submit" tabindex="13"></button></p>
			<p class="password"><input type="password" name="pwd" id="pwd"  size="10" tabindex="12" /></p>
			<p class="login"><input type="text" name="log" id="log" size="10" tabindex="11" /></p>
		</div>
		<input type="hidden" name="redirect_to" value="<?php echo $_SERVER[ 'REQUEST_URI' ]; ?>" />
	</form>
	<!-- end if not logged -->
	<?php
		} else {
		// deposit page permalink
		$dp_perma = get_permalink( $adddepositpage );
		$cb = $wpdb->get_var( "SELECT deposit FROM $wpdb->users WHERE ID = $user_ID" );
		if( $cb == NULL ) $cb = '0.00';
	?>
	<div class="loginblock">
		<span class="date_t"><?php date_default_timezone_set('PRC');print date('Y年m月d日'); ?></span> / <span class="loginx">您已经登录：<?php echo $user_identity; ?> / <?php wp_register('', ''); ?> / <?php wp_loginout(); ?></span>
	</div>
<?php
}
?>