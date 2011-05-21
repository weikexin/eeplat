<?php
/*
 * Template Name: tougao
 * @author: Ludou  
 * @Email : zhouzb889@gmail.com
 * @Blog  : http://www.ludou.org/
 */
    
if( isset($_POST['tougao_form']) && $_POST['tougao_form'] == 'send')
{
    if ( isset($_COOKIE["tougao"]) && ( time() - $_COOKIE["tougao"] ) < 120 )
    {
        wp_die('您投稿也太勤快了吧，先歇会儿！');
    }
        
    // 表单变量初始化
    $name = isset( $_POST['tougao_authorname'] ) ? trim(htmlspecialchars($_POST['tougao_authorname'], ENT_QUOTES)) : '';
    $email =  isset( $_POST['tougao_authoremail'] ) ? trim(htmlspecialchars($_POST['tougao_authoremail'], ENT_QUOTES)) : '';
    $blog =  isset( $_POST['tougao_authorblog'] ) ? trim(htmlspecialchars($_POST['tougao_authorblog'], ENT_QUOTES)) : '';
    $title =  isset( $_POST['tougao_title'] ) ? trim(htmlspecialchars($_POST['tougao_title'], ENT_QUOTES)) : '';
    $category =  isset( $_POST['cat'] ) ? (int)$_POST['cat'] : 0;
    $content =  isset( $_POST['tougao_content'] ) ? trim(htmlspecialchars($_POST['tougao_content'], ENT_QUOTES)) : '';
    
    // 表单项数据验证
    if ( empty($name) || strlen($name) > 20 )
    {
        wp_die('昵称必须填写，且长度不得超过20字');
    }
    
    if ( empty($email) || strlen($email) > 60 || !preg_match("/^([a-z0-9\+_\-]+)(\.[a-z0-9\+_\-]+)*@([a-z0-9\-]+\.)+[a-z]{2,6}$/ix", $email))
    {
        wp_die('Email必须填写，且长度不得超过60字，必须符合Email格式');
    }
    
    if ( empty($title) || strlen($title) > 100 )
    {
        wp_die('标题必须填写，且长度不得超过100字');
    }
    
    if ( empty($content) || strlen($content) > 3000 || strlen($content) < 100)
    {
        wp_die('内容必须填写，且长度不得超过3000字，不得少于100字');
    }
    
    $post_content = '昵称: '.$name.'<br />Email: '.$email.'<br />blog: '.$blog.'<br />内容:'.$content;
  
    $tougao = array(
        'post_title' => $title,
        'post_content' => $post_content,
        'post_category' => array($category)
    );


    // 将文章插入数据库
    $status = wp_insert_post( $tougao );
  
    if ($status != 0) 
    {
        setcookie("tougao", time(), time()+180);
        wp_die('投稿成功！感谢投稿！');
    }
    else
    {
        wp_die('投稿失败！');
    }
}

?>

<?php get_header(); ?>
<!--Banner-->
<div class="page_ps">
现在位置：<a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> ＞<?php the_title(); ?></div>
<!--Banner End-->
<!--Content Star-->
<div class="pages">
<!--left page star-->
  <div class="P_left">
  <?php if (have_posts()) : ?><?php while (have_posts()) : the_post(); ?>
  <div class="pageno">  <?php the_content(); ?>
<form method="post" action="<?php echo $_SERVER["REQUEST_URI"]; ?>">
<div style="text-align: left; padding-top: 10px;">
        <label>文章标题:*</label>
    </div>
    <div>
        <input type="text" size="40" value="" name="tougao_title" />
    </div>
     <div style="text-align: left; padding-top: 10px;">
        <label>分类:*</label>
    </div>
    <div style="text-align: left;">
        <?php wp_dropdown_categories('show_count=1&hierarchical=1'); ?>
    </div>
    <div style="text-align: left; padding-top: 10px;">
        <label>昵称:*</label>
    </div>
    <div>
        <input type="text" size="40" value="" name="tougao_authorname" />
    </div>

    <div style="text-align: left; padding-top: 10px;">
        <label>E-Mail:*</label>
    </div>
    <div>
        <input type="text" size="40" value="" name="tougao_authoremail" />
    </div>
                    
    <div style="text-align: left; padding-top: 10px;">
        <label>您的博客:</label>
    </div>
    <div>
        <input type="text" size="40" value="" name="tougao_authorblog" />
    </div>

                    
    

   
                    
    <div style="text-align: left; padding-top: 10px;">
        <label>文章内容:*</label>
    </div>
    <div>
        <textarea rows="15" cols="55" name="tougao_content"></textarea>
    </div>
                    
    <br clear="all">
    <div style="text-align: center; padding-top: 10px;">
        <input type="hidden" value="send" name="tougao_form" />
        <input type="submit" value="提交" />
        <input type="reset" value="重填" />
    </div>
</form> 
</div>


  <?php endwhile; ?><?php else : ?>
  <h2 class="center">Not Found</h2>
  <p class="center">Sorry, but you are looking for something that isn't here.</p>
  <?php include (TEMPLATEPATH . "/searchform.php"); ?>
  <?php endif; ?>
  
    <div class="clear"></div>
  </div>
  <!--left page end-->
  <!--right page star-->
  <div class="p_rightbar">
    <?php get_sidebar(); ?>
	<div class="clear"></div>
</div>
  <!--Right End -->
<div class="clear"></div>
</div>
<!--Content End-->

<?php get_footer(); ?>
