
<!-- You can start editing here. -->
<#if have_comments("${dataMap.posts_id}")>
	<h2 id="comments">已有评论</h2>
	<ol class="commentlist">
  		<#list get_comments("${dataMap.posts_id}") as data>
			<li id="comment-67" class="comment even thread-even depth-1">
			   <div id="div-comment-${data_index}">
			      		<div class="comment-author vcard">
							<img class="avatar" alt="" src="${bloginfo('current_path')}images/gravatar.png">
						<strong><a class="url" rel="external nofollow" href="http://sdfsadf">${data.comment_author}</a></strong> :
						<span class="datetime">2011年01月24日 上午 6:50 </span>
						<span class="reply"><a onclick="return addComment.moveForm(&quot;div-comment-67&quot;, &quot;67&quot;, &quot;respond&quot;, &quot;1053&quot;)" href="/tools/moble-qq-2011-s60v5-beta1build0006.html?replytocom=67#respond" class="comment-reply-link" rel="nofollow">回复</a></span>
					</div>
					<p>${data.comment_author_content}</p>
					<div class="clear"></div>
			  </div>
			</li>
		</#list>
	</ol>
	<div class="navigation_c">
		<div class="previous"></div>
	</div>
</#if>

<!-- 准备加入评论权限功能
 <?php else : // this is displayed if there are no comments so far ?>
	<?php if ('open' == $post->comment_status) : ?>
	 <?php else : // comments are closed ?>
		<p class="nocomments">报歉!评论已关闭.</p>
	<?php endif; ?>
	<?php endif; ?>
	<?php if ('open' == $post->comment_status) : ?>-->
	<div id="respond">
		<h3>给我留言</h3>

		<div class="cancel-comment-reply">
		<small></small>
     </div>
	<#if !is_userlogin()>
		<p>你必须 <a href="./exedo/webv3/template/cms/login.ftl">登录后</a> 才能对文章进行评论!</p>
    <#else>
	<form action="./exedo/webv3/template/cms/cms-comments-post.ftl" method="get" id="commentform">

	<div class="author_avatar"></div>
	<div class="author">欢迎你， ${get_user_info().userinfo.user_nicename}.<a href="./exedo/webv3/template/cms/theme/test/index.ftl?action=logoff" title="Log out of this account"> 点击退出系统 &raquo;</a></div>
	

      <!--<p><small><strong>XHTML:</strong> You can use these tags: <code><?php echo allowed_tags(); ?></code></small></p>-->
      <div class="clear"></div>
      <p>
        <textarea name="comment" id="comment" tabindex="4"></textarea>
      </p>
      <p>
        <input class="submit" name="submit" type="submit" id="submit" tabindex="5" value="提交留言" />
		<input type="hidden" name="posts_id" value="${dataMap.posts_id}" >
      </p>
    </form>
    </#if>

</div>
