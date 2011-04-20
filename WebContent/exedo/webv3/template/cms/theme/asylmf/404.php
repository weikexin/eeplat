<?php
/**
 * The template for displaying 404 pages (Not Found).
 */

get_header(); ?>
	<div id="error">
		<h1><?php _e( '什么也没有', 'AsYLMF' ); ?></h1>
		<p><?php _e( '抱歉, 此页面已经不存在. 请返回首页或重新搜索.', 'AsYLMF' ); ?></p>
        <p><?php _e( '欢迎光临本站，请多多支持！', 'AsYLMF' ); ?></p>
	</div>
<?php get_footer(); ?>