<?php
include 'theme_options.php';

register_nav_menus(
      array(
         'header-primary' => __( '顶部导航', 'AsYLMF' ),
         'header-secondary' => __( '主导航', 'AsYLMF' ),
         'footercat' => __( '页底导航', 'AsYLMF' )
      )
   );

if ( function_exists('register_sidebar') )
	register_sidebar(array(
	'name' => 'Sidebar',
    'before_widget' => '<li class="sidebox">',
    'after_widget' => '</li>',
	'before_title' => '<h2 class="sidetitl">',
    'after_title' => '</h2>',
	));

if ( function_exists( 'add_theme_support' ) ) {
	add_theme_support( 'post-thumbnails' );
	add_image_size( 'asylmf_slide', 630, 300, true ); 

}

function asylmf_slide_image(){
if ( has_post_thumbnail() ) {
	 the_post_thumbnail( 'asylmf_slide', array('alt' => '<?php the_title(); ?>') );
} else { 
?><img src="<?php bloginfo('template_directory'); ?>/images/dummy.jpg" alt="<?php the_title(); ?>"/>
<?php
};
}

/*	COPYRIGHT YEAR  */
function comicpress_copyright() {
    global $wpdb;
    $copyright_dates = $wpdb->get_results("
    SELECT
    YEAR(min(post_date_gmt)) AS firstdate,
    YEAR(max(post_date_gmt)) AS lastdate
    FROM
    $wpdb->posts
    WHERE
    post_status = 'publish'
    ");
    $output = '';
    if($copyright_dates) {
    $copyright = "&copy; " . $copyright_dates[0]->firstdate;
    if($copyright_dates[0]->firstdate != $copyright_dates[0]->lastdate) {
    $copyright .= '-' . $copyright_dates[0]->lastdate;
    }
    $output = $copyright;
    }
    return $output;
    }

/* GET IMAGE */
function catch_that_image() {
	global $post, $posts;
	$first_img = '';
	ob_start();
	ob_end_clean();
	$output = preg_match_all('/<img.+src=[\'"]([^\'"]+)[\'"].*>/i', $post->post_content, $matches);
	$first_img = $matches [1] [0];
	if(empty($first_img)){ 
	$first_img = bloginfo('template_url'). '/images/default-thumb.jpg';
	}
	return $first_img;
	}

/*BREADCRUMB*/
function the_breadcrumb() {
	if (!is_home()) {
		echo '<a href="';
		echo get_option('home');
		echo '">';
		echo "首页";
		echo "</a> > ";
		if (is_category() || is_single()) {
			single_cat_title();
			if (is_single()) {
			the_category(', ');
				echo " > ";
				the_title();
			}
		} elseif (is_page()) {
			echo the_title();
		}
		  elseif (is_tag()) {
			echo '标签为 "';
			single_tag_title();
			echo '"'; }
		elseif (is_day()) {echo "发表于 "; the_time(' Y-m-j');}
		elseif (is_month()) {echo "发表于 "; the_time(' Y-m');}
		elseif (is_year()) {echo "发表于 "; the_time(' Y');}
		elseif (is_author()) {echo "作者归档";}
		elseif (isset($_GET['paged']) && !empty($_GET['paged'])) {echo "本站归档";}
		elseif (is_search()) {echo "搜索结果如下";}
	}
}

/*PAGINATION*/
function asylmf_pagination($query_string){
global $posts_per_page, $paged;
$my_query = new WP_Query($query_string ."&posts_per_page=-1");
$total_posts = $my_query->post_count;
if(empty($paged))$paged = 1;
$prev = $paged - 1;
$next = $paged + 1;
$range = 3; // only edit this if you want to show more page-links
$showitems = ($range * 2)+1;

$pages = ceil($total_posts/$posts_per_page);
if(1 != $pages){
echo "<div class='pagination'>";
echo ($paged > 2 && $paged+$range+1 > $pages && $showitems < $pages)? "<a href='".get_pagenum_link(1)."'>最前</a>":"";
echo ($paged > 1 && $showitems < $pages)? "<a href='".get_pagenum_link($prev)."'>上一页</a>":"";

for ($i=1; $i <= $pages; $i++){
if (1 != $pages &&( !($i >= $paged+$range+1 || $i <= $paged-$range-1) || $pages <= $showitems )){
echo ($paged == $i)? "<span class='current'>".$i."</span>":"<a href='".get_pagenum_link($i)."' class='inactive' >".$i."</a>";
}
}

echo ($paged < $pages && $showitems < $pages) ? "<a href='".get_pagenum_link($next)."'>下一页</a>" :"";
echo ($paged < $pages-1 &&  $paged+$range-1 < $pages && $showitems < $pages) ? "<a href='".get_pagenum_link($pages)."'>最后</a>":"";
echo "</div>\n";
}
}

?>