<?php

/*
Plugin Name: HelloFlash
Plugin URI:  http://www.phpstudio.cn
Description: Huang deng pian 
Version: 0.1
Author: vimcoder
Author URI: http://www.phpstudio.cn
*/
//test
function do_head_artsautoin_style(){
}

add_action('admin_head', 'do_head_artsautoin_style');

add_action('admin_menu', 'fw_set_artsautoin_options_page');

function flash_css(){
	global $wpdb;
		$sql = "select option_value from $wpdb->options where option_name = 'flash_width'";
	$results = $wpdb->get_results($sql);
	if(count($results) == 0){
		$width = "300";
	}
	else{
		foreach($results as $result){
			$width = $result->option_value ;
		}		
	}
	
	$sql = "select option_value from $wpdb->options where option_name = 'flash_height'";
	$results = $wpdb->get_results($sql);
	if(count($results) == 0){
		$height = "300";
	}
	else{
		foreach($results as $result){
			$height = $result->option_value ;
		}		
	}
	echo "
<style type='text/css'>\n
.flashNews {position:relative;width:".$width."px;height:".$height."px;margin-bottom:12px;overflow:hidden;border:1px solid #B6CAE3;text-align:left;font:normal 12px/1.6em simsun, Verdana, Lucida, Arial, Helvetica, sans-serif;color:#353535;}\n
.flashNews .bg {position:absolute;left:0;bottom:0;width:".$width."px;height:73px;background:#000000;filter:alpha(opacity=39);-moz-opacity:0.39;opacity:0.39;}\n
.flashNews h3 {position:absolute;left:10px;top:".($height-70)."px;width:".$width."px;height:35px;line-height:35px;z-index:2;margin:0;padding:0;}\n
.flashNews h3 a {font-size:20px;font-weight:600;color:#FFFFFF;}\n
.flashNews333 a:hover {color:#F20000;}\n
.flashNews p {position:absolute;left:10px;top:".($height-40)."px;width:284px;height:20px;line-height:20px;;z-index:2;margin:0;padding:0;}\n
.flashNews p a {color:#FFFFFF;}\n
.flashNews p a:hover {color:#F20000;}\n
.flashNews ul {position:absolute;right:0;bottom:0;padding-left:47px;_padding-left:46px;background:url(../img/flashPage_bg.png) 0 0 no-repeat;z-index:2;_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='img/flashPage_bg.png',sizingMethod='crop')}\n
.flashNews ul li {float:left;width:15px;height:18px;line-height:18px;text-align:center;margin-left:1px;background:#000000;}\n
.flashNews ul li a {display:block;width:15px;height:18px;font-size:10px;font-family:Tahoma;font-weight:bold;color:#FFFFFF;margin:0;padding:0;}\n
.flashNews ul li a:hover, .flashNews ul li a.sel {color:#F20000;margin:0;padding:0;}\n
</style>\n";
}

function flash_script(){
	echo "<script type=\"text/javascript\" src=\"".get_settings('home')."/wp-content/plugins/flash/funcs.js\"></script>";
}

function flash(){

	global $wpdb;

	$sql = "select option_value from $wpdb->options where option_name = 'flash_mode'";
	$results = $wpdb->get_results($sql);
	if(count($results) == 0){
		$mode = "1";
	}
	else{
		foreach($results as $result){
			$mode = $result->option_value ;
		}		
	}

	if($mode == 1){
	$sql = "
SELECT a.post_title AS stitle, a.guid AS link, b.post_title AS title, b.guid AS img
FROM wp_posts AS a, wp_posts AS b
WHERE b.post_parent >0
AND b.post_mime_type LIKE \"image%\"
AND a.id = b.post_parent
ORDER BY `a`.`id` desc 
LIMIT 6 
";
	}else{
	$sql = "
SELECT a.post_title AS title, a.guid AS link, b.meta_value AS img
FROM wp_posts AS a, wp_postmeta AS b
WHERE meta_key = 'flash_image'
AND a.post_type = 'post'
AND b.post_id = a.id
ORDER BY `a`.`id` DESC
LIMIT 6 
";
	}
	$results = $wpdb->get_results($sql);
	$flashstr = ' <script type="text/javascript">
		var MaxScreen = '.count($results).' ;
		var CurScreen = 1 ;
		</script>
		<div class="flashNews" style="text-align:left;font:normal 12px/1.6em simsun, Verdana, Lucida, Arial, Helvetica, sans-serif;color:#353535;">';
		
	for($i = 0 ; $i < (count($results) -1 ); $i++){
		$flashstr .= '<div style="display: none;" id="Switch_'.($i+1).'"><a href="'.$results[$i]->link.'" blockid="1121" style="text-decoration:none;color:#353535;"><img src="'.$results[$i]->img.'" onmouseover="pauseSwitch();" onmouseout="goonSwitch();" style="border:0;"></a></div>';
	}

	$flashstr .= '<div id="Switch_'.count($results).'" style="display: block;"><a href="'.$results[$i]->link.'" blockid="1121"><img src="'.$results[$i]->img.'" onmouseover="pauseSwitch();" onmouseout="goonSwitch();" style="border:0;"></a></div>';

	$flashstr .= '<div id="SwitchTitle" onmouseover="pauseSwitch();" onmouseout="goonSwitch();"></div>';

	$flashstr .= '<ul id="SwitchNav" style="list-style: none;margin: 0pt; padding: 0pt; list-style: none; list-style-image: none; list-style-position: inside;"></ul>';

	$flashstr .= '<div class="bg" onmouseover="pauseSwitch();" onmouseout="goonSwitch();"></div></div>';

	$flashstr .= '<script type="text/javascript">
			var Switcher = new Array();';

	for($i = 0 ; $i < count($results); $i++){	
		$flashstr .= 'Switcher['.($i+1).'] = Array() ;
			Switcher['.($i+1).'][\'title\'] = "'.$results[$i]->title.'" ;
			Switcher['.($i+1).'][\'stitle\'] = "'.$results[$i]->stitle.'" ;
			Switcher['.($i+1).'][\'link\'] = "'.$results[$i]->link.'";';
	}
	$flashstr .= 'var refreshSwitchTimer = null;
		switchPic(CurScreen);
		refreshSwitchTimer = setTimeout(\'reSwitchPic();\', 3000);
		</script>';

	echo $flashstr ;
}


	
add_action('wp_head','flash_css');
add_action('wp_head','flash_script');

function test_post(){
	global $wpdb;
	$sql="SELECT ID,post_title,post_parent,guid FROM $wpdb->posts where post_parent > 0 and post_mime_type like \"image%\" limit 5" ;

	$results = $wpdb->get_results($sql);

	foreach($results as $result){
		echo $result->ID;
	$test = $result->guid ;
	}
	echo $test;
}

function fw_set_artsautoin_options_page(){
	if (function_exists ( 'add_options_page' )){
			add_options_page ( 'HelloFlash', 'HelloFlash', 'manage_options',basename(__FILE__),'FwArtsAutoIn_Options');
	}
}


function FwArtsAutoIn_Options(){

	global $wpdb;
	if(!isset($_POST['width'])){
		$tip = "Please set width and height!";
	}

	if(isset($_POST['width'])){

		$sql = "select count(*) as num from $wpdb->options where option_name = 'flash_width'";
		$results = $wpdb->get_results($sql);
	
		foreach($results as $result){
			if($result->num == 0){
				echo "num-0";
				$sql = "insert into $wpdb->options (blog_id, option_name, option_value,autoload) values ('0','flash_width','".$_POST['width']."','yes')";
				$wpdb->get_results($sql);
				$sql = "insert into $wpdb->options (blog_id, option_name, option_value,autoload) values ('0','flash_height','".$_POST['height']."','yes')";
				$wpdb->get_results($sql);

				$sql = "insert into $wpdb->options (blog_id, option_name, option_value,autoload) values ('0','flash_mode','".$_POST['mode']."','yes')";
				$wpdb->get_results($sql);

			}
			else{
				$sql = "update $wpdb->options set option_value = '".$_POST['width']."' where option_name = 'flash_width'";
				$wpdb->get_results($sql);
				$sql = "update $wpdb->options set option_value = '".$_POST['height']."' where option_name = 'flash_height'";
				$wpdb->get_results($sql);
				$sql = "update $wpdb->options set option_value = '".$_POST['mode']."' where option_name = 'flash_mode'";
				$wpdb->get_results($sql);
			}
		}
		//echo "OK!!!!!!!!!!!!!!!!!!!!!!!!!!";

	}	
	$sql = "select option_value from $wpdb->options where option_name = 'flash_width'";
	$results = $wpdb->get_results($sql);
	if(count($results) == 0){
		$width = "300";
	}
	else{
		foreach($results as $result){
			$width = $result->option_value ;
		}		
	}
	
	$sql = "select option_value from $wpdb->options where option_name = 'flash_height'";
	$results = $wpdb->get_results($sql);
	if(count($results) == 0){
		$height = "300";
	}
	else{
		foreach($results as $result){
			$height = $result->option_value ;
		}		
	}

	$sql = "select option_value from $wpdb->options where option_name = 'flash_mode'";
	$results = $wpdb->get_results($sql);
	if(count($results) == 0){
		$mode = "1";
	}
	else{
		foreach($results as $result){
			$mode = $result->option_value ;
		}		
	}
	
?>
	<SCRIPT language=Javascript>
var isStart=true;
var nn;
var tt;
function setFocus(i)
{
 if(tt) clearTimeout(tt);
 nn = i;
 selectLayer1(i);
 tt=setTimeout('change_img()',4000);
}
function selectLayer1(i)
{
 switch(i)
 {
 case 1:
document.getElementById("bbs_s1").style.display="block";
document.getElementById("bbs_s2").style.display="none";
 break;
case 2:
document.getElementById("bbs_s1").style.display="none";
document.getElementById("bbs_s2").style.display="block";
 break;
 }
}
</SCRIPT>
<div id="icon-edit-pages" class="icon32">
<br/>
</div>
<h2>Hello Flash</h2>
<hr style="color:#ffffff;"/>
<DIV id=top_slider>
<!--bbs s1 -->
<DIV id=bbs_s1 class=solidbox>
<ul class="subsubsub">
<li>
<a class="current" onclick="setFocus(1)" href="#">
<?php echo iconv("gb2312","utf-8","设置"); ?>
</a>
</li>
</ul>

<br />
<br />
<div id="fwatinbody">
<form method="post" id="toppostpage" action="<?php echo $_SERVER ['REQUEST_URI'];	?>">
<table class="form-table">
<tbody>
<tr valign="top">
<th scope="row"><label for="blogdescription"><?php echo iconv("gb2312","utf-8","选择模式"); ?></label></th>
<td>
<?php
	if($mode == 1){
?>
<input type="radio" name="mode" value="1" checked="true"><?php echo iconv("gb2312","utf-8","简单模式"); ?>&nbsp;
<input type="radio" name="mode" value="2"><?php echo iconv("gb2312","utf-8","自定义模式"); ?>
<?php	
	}else{
?>
<input type="radio" name="mode" value="1"><?php echo iconv("gb2312","utf-8","简单模式"); ?>&nbsp;
<input type="radio" name="mode" value="2" checked="true"><?php echo iconv("gb2312","utf-8","自定义模式"); ?>&nbsp;
<?php
	}
?>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<span class="setting-description"><?php echo iconv("gb2312","utf-8","设置显示方式"); ?></span></td>
</tr>

<tr valign="top">
<th scope="row"><label for="blogname"><?php echo iconv("gb2312","utf-8","宽度"); ?></label></th>
<td><input name="width" type="text" id="blogname" value=<?php echo $width ;?> class="regular-text" />
<span class="setting-description"><?php echo iconv("gb2312","utf-8","幻灯片的宽度"); ?></span></td>
</tr>
<tr valign="top">
<th scope="row"><label for="blogdescription"><?php echo iconv("gb2312","utf-8","高度"); ?></label></th>
<td><input name="height" type="text" id="blogdescription"  value=<?php echo $height;?> class="regular-text" />
<span class="setting-description"><?php echo iconv("gb2312","utf-8","幻灯片的高度"); ?></span></td>
</tr>

</tbody>
</table>
<br />
<br />
<input class="button-primary" type="submit" value="Submit" name="Submit" style="width:90px;"/>
</p>

</form>
<br />
<br />
<br />
<br />
<div style="background: #ffffff;
	border: 1px dotted #ccc;
	color: #666;
	padding: 0.5em;
	margin: 0 1em;">
<center><h2><?php echo iconv("gb2312","utf-8","使用说明"); ?></h2></center>
<p>
<?php echo iconv("gb2312","utf-8","简单模式下"); ?><br />
<?php echo iconv("gb2312","utf-8","不用设置什么，参考"); ?><br />
<a href="http://www.phpstudio.cn/2008/12/28/helloflash/">1 http://www.phpstudio.cn/2008/10/04/wp-flash-plugins/</a><br />
<a href="http://www.bbon.cn/2009/01/helloflashwordpress%E5%B9%BB%E7%81%AF%E7%89%87%E6%8F%92%E4%BB%B6%E7%9A%84%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9.html"><?php echo iconv("gb2312","utf-8","2、bbon的介绍"); ?></a><br />
<a href="http://www.mxjava.com/blog/archives/213.html"><?php echo iconv("gb2312","utf-8","3、MXJAVA的介绍"); ?></a>
<br />
<?php echo iconv("gb2312","utf-8","感谢两位"); ?>
<br /><br />
<?php echo iconv("gb2312","utf-8","自定义模式"); ?><br />
<?php echo iconv("gb2312","utf-8","1、设置成自定义模式 ，同时设置幻灯片长度高度"); ?><br />
<?php echo iconv("gb2312","utf-8","2、发布文章的时候，添加自定义字段 ，字段名<< <font color=\"red\">flash_image</font> >>"); ?><br />
<?php echo iconv("gb2312","utf-8","具体介绍:"); ?>

<br /><br />
<?php echo iconv("gb2312","utf-8","最后感谢一下因为本插件给我留过言的朋友们，由于自己的懒惰到现在才写出来"); ?>
<br />
<p>
</div>
</div>
</div>



<?php
}
?>
