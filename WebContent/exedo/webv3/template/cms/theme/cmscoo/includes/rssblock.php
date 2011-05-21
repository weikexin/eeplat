<script type="text/javascript">	
	var s;
	function showMessage(){
	window.clearTimeout(s);
	document.getElementById("message").style.display="block";
	}
	function hiddenMessage(){
	s=window.setTimeout("hidden();",500);
	}
	function hidden(){
	document.getElementById("message").style.display="none";
	}
</script>
<?php if (get_theme_mod('showrss') == 'Yes') { ?>
	<h3>订阅本站</h3>
<span onmouseover="showMessage()" onmouseout="hiddenMessage()"><div id="rssfeed"><a href="<?php echo stripslashes(get_theme_mod('rsssub')); ?>" title="订阅本站" class="image"><img src="<?php bloginfo('template_directory'); ?>/images/rsssub.png" /></a></div></span>
<div id="message" style="display:none" onmouseover="showMessage()" onmouseout="hiddenMessage()">
	<?php echo stripslashes(get_theme_mod('rss')); ?>
</div>
	<div class="box-bottom"></div>
	<div class="clear"></div>
<?php } else { ?>
<?php } ?>