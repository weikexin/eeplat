<?php
$themename = "AsYLMF";
$shortname = "asylmf";

$options = array (
	
	array(  "name" => "头部广告",
       		 "type" => "title",
			"desc" => "头部广告设置.",
       ),
	   
  	array("type" => "open"),
	   
	   
	array("name" => "头部广告（468X60）",
			"desc" => "输入广告代码 .",
            "id" => $shortname."_ad5",
            "std" => "<img src=\"".get_bloginfo('template_url')."/images/468banner.jpg\" alt=\"\"/>",
            "type" => "textarea"), 
			

	array("type" => "close"),
	
	array(  "name" => "125 x 125 四格广告位设置",
            "type" => "title",
			"desc" => "设置侧边栏四格广告",
       ), 
	 
	array("type" => "open"),
	
	
	array("name" => "图片1",
			"desc" => "输入图片的绝对地址.",
            "id" => $shortname."_banner1",
        	"std" => "".get_bloginfo('template_url')."/images/banner.jpg",
            "type" => "text"), 
			
	array("name" => "图片1广告词",
			"desc" => "输入广告词.",
            "id" => $shortname."_alt1",
        	"std" => "Theme by Donling.com.",
            "type" => "text"),    
	  
	array("name" => "图片1跳转地址",
			"desc" => "输入跳转后的绝对地址.",
            "id" => $shortname."_url1",
            "std" => "http://donliang.com/",
            "type" => "text"),    
			
	array("name" => "图片1广告标题",
			"desc" => "输入标题文本.",
            "id" => $shortname."_lab2",
            "std" => "Welcome to Donliang.com.",
            "type" => "text"),   
	
	array("type" => "break"),
	
	array("name" => "图片2",
			"desc" => "输入图片的绝对地址.",
            "id" => $shortname."_banner2",
        	"std" => "".get_bloginfo('template_url')."/images/banner.jpg",
            "type" => "text"), 
			
	array("name" => "图片2广告词",
			"desc" => "输入广告词.",
            "id" => $shortname."_alt2",
        	"std" => "Theme by Donling.com.",
            "type" => "text"),    
	  
	array("name" => "图片2跳转地址",
			"desc" => "输入跳转后的绝对地址.",
            "id" => $shortname."_url2",
            "std" => "http://donliang.com/",
            "type" => "text"),    
			
	array("name" => "图片2广告标题",
			"desc" => "输入标题文本.",
            "id" => $shortname."_lab2",
            "std" => "Welcome to Donliang.com.",
            "type" => "text"),   
	
	array("type" => "break"),
	
	array("name" => "图片3",
			"desc" => "输入图片的绝对地址.",
            "id" => $shortname."_banner3",
        	"std" => "".get_bloginfo('template_url')."/images/banner.jpg",
            "type" => "text"), 
			
	array("name" => "图片3广告词",
			"desc" => "输入广告词.",
            "id" => $shortname."_alt3",
        	"std" => "Theme by Donling.com.",
            "type" => "text"),    
	  
	array("name" => "图片3跳转地址",
			"desc" => "输入跳转后的绝对地址.",
            "id" => $shortname."_url3",
            "std" => "http://donliang.com/",
            "type" => "text"),    
			
	array("name" => "图片3广告标题",
			"desc" => "输入标题文本.",
            "id" => $shortname."_lab3",
            "std" => "Welcome to Donliang.com.",
            "type" => "text"),   
	
	array("type" => "break"),
	
	array("name" => "图片4",
			"desc" => "输入图片的绝对地址.",
            "id" => $shortname."_banner4",
        	"std" => "".get_bloginfo('template_url')."/images/banner.jpg",
            "type" => "text"), 
			
	array("name" => "图片4广告词",
			"desc" => "输入广告词.",
            "id" => $shortname."_alt4",
        	"std" => "Theme by Donling.com.",
            "type" => "text"),    
	  
	array("name" => "图片4跳转地址",
			"desc" => "输入跳转后的绝对地址.",
            "id" => $shortname."_url4",
            "std" => "http://donliang.com/",
            "type" => "text"),    
			
	array("name" => "图片4广告标题",
			"desc" => "输入标题文本.",
            "id" => $shortname."_lab4",
            "std" => "Welcome to Donliang.com.",
            "type" => "text"),   	
		
	array("type" => "close"),	
	
	
);

 
function mytheme_add_admin() {

    global $themename, $shortname, $options;

    if ( $_GET['page'] == basename(__FILE__) ) {
    
        if ( 'save' == $_REQUEST['action'] ) {

                foreach ($options as $value) {
                    update_option( $value['id'], $_REQUEST[ $value['id'] ] ); }

                foreach ($options as $value) {
                    if( isset( $_REQUEST[ $value['id'] ] ) ) { update_option( $value['id'], $_REQUEST[ $value['id'] ]  ); } else { delete_option( $value['id'] ); } }

                header("Location: themes.php?page=theme_options.php&saved=true");
                die;

        } else if( 'reset' == $_REQUEST['action'] ) {

            foreach ($options as $value) {
                delete_option( $value['id'] ); 
                update_option( $value['id'], $value['std'] );}

            header("Location: themes.php?page=theme_options.php&reset=true");
            die;

        }
    }

      add_theme_page($themename." Options", "$themename 设置", 'edit_themes', basename(__FILE__), 'mytheme_admin');

}



function mytheme_admin() {

    global $themename, $shortname, $options;


    
    
?>
<div class="wrap">
<div class="opwrap" style="background:#fff; margin:20px auto; width:80%; padding:30px; border:1px solid #ddd;" >



<h2 class="wraphead" style="margin:10px 0px; padding:15px 10px; font-family:arial black; font-style:normal; background:#B3D5EF;"><b><?php echo $themename; ?> 主题设置</b></h2>
   <?php
   if ( $_REQUEST['saved'] ) echo '<div id="message" class="updated fade"><p><strong>'.$themename.' 设置已保存.</strong></p></div>';
    if ( $_REQUEST['reset'] ) echo '<div id="message" class="updated fade"><p><strong>'.$themename.' 设置已重置.</strong></p></div>';
	?>
<form method="post">

<?php foreach ($options as $value) {


switch ( $value['type'] ) {

case "open":
?>
<table width="100%" border="0" style="background-color:#eef5fb; padding:10px;">

<?php break;

case "close":
?>

</table><br />
<?php break;

case "break":
?>
<tr><td colspan="2" style="border-top:1px solid #C2DCEF;">&nbsp;</td></tr>

<?php break;

case "title":
?>

<table width="100%" border="0" style="background-color:#dceefc; padding:5px 10px;"><tr>
    <td colspan="2"><h3 style="font-size:18px;font-family:Georgia,'Times New Roman',Times,serif;"><?php echo $value['name']; ?></h3></td>
</tr>

<?php break;

case 'text':
?>

<tr>
    <td width="20%" rowspan="2" valign="middle"><strong><?php echo $value['name']; ?></strong></td>
    <td width="80%"><input style="width:400px;" name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>" type="<?php echo $value['type']; ?>" value="<?php if ( get_settings( $value['id'] ) != "") { echo get_settings( $value['id'] ); } else { echo $value['std']; } ?>" /></td>
</tr>

<tr>
    <td><small><?php echo $value['desc']; ?></small></td>
</tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px dotted #ffffff;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>

<?php
break;

case 'textarea':
?>

<tr>
    <td width="20%" rowspan="2" valign="middle"><strong><?php echo $value['name']; ?></strong></td>
    <td width="80%"><textarea name="<?php echo $value['id']; ?>" style="width:400px; height:200px;" type="<?php echo $value['type']; ?>" cols="" rows=""><?php if ( get_settings( $value['id'] ) != "") { echo stripslashes (get_settings( $value['id'] )); } else { echo $value['std']; } ?></textarea></td>

</tr>

<tr>
    <td><small><?php echo $value['desc']; ?></small></td>
</tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px dotted #ffffff;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>

<?php
break;

case 'select':
?>
<tr>
    <td width="20%" rowspan="2" valign="middle"><strong><?php echo $value['name']; ?></strong></td>
    <td width="80%"><select style="width:240px;" name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>"><?php foreach ($value['options'] as $option) { ?><option<?php if ( get_settings( $value['id'] ) == $option) { echo ' selected="selected"'; } elseif ($option == $value['std']) { echo ' selected="selected"'; } ?>><?php echo $option; ?></option><?php } ?></select></td>
</tr>

<tr>
    <td><small><?php echo $value['desc']; ?></small></td>
</tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px dotted #ffffff;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>

<?php
break;

case "checkbox":
?>
    <tr>
    <td width="20%" rowspan="2" valign="middle"><strong><?php echo $value['name']; ?></strong></td>
        <td width="80%"><? if(get_settings($value['id'])){ $checked = "checked=\"checked\""; }else{ $checked = ""; } ?>
                <input type="checkbox" name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>" value="true" <?php echo $checked; ?> />
                </td>
    </tr>

    <tr>
        <td><small><?php echo $value['desc']; ?></small></td>
   </tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px dotted #ffffff;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>

<?php         break;

}
}
?>

<p class="submit">
<input name="save" type="submit" value="确认" />
<input type="hidden" name="action" value="save" />
</p>
</form>
<form method="post">
<p class="submit">
<input name="reset" type="submit" value="重置" />
<input type="hidden" name="action" value="reset" />
</p>
</form>
<p style="text-align:right;"> <small> WordPress themes 来自 <a href="http://donliang.com/">Donliang.com</a> </small>
</div>
<?php
}
add_action('admin_menu', 'mytheme_add_admin'); ?>