
<!-- You can start editing here. -->

<div class="clear"></div>


<#if have_comments("${dataMap.posts_id}")>
	<h3 id="comments"><strong>评论信息</strong></h3>

	<ol class="commentlist">
	
    <#list get_comments("${dataMap.posts_id}") as data>
		<li class="alt" id="${data.comment_id}">
		    <!-- 后期需要加入头像、楼层、引用回复等功能-->
			<div class="gravatar">
			<a target="_blank" href=""><img width="48" height="48" class="avatar avatar-48 photo" src="${bloginfo('current_path')}images/default_user_ico.jpg" alt=""></a>
            <label class="floor">${data_index + 1}楼</label>
            </div>
            <small class="commentmetadata">at 2011-01-10</small>
            <strong>${data.comment_author}</strong> 说:
            
            <!-- 下一步实现管理员审核
			<?php if ($comment->comment_approved == '0') : ?>
			<em>您的评论要等管理员审核后才能显示！</em>
			<?php endif; ?>
            -->
			<p>${data.comment_author_content}</p>

		</li>
	</#list>
	</ol>
	<!-- 在这里加入判断文章是否允许评论-->
</#if>

<h3 id="respond">发表评论</h3>
<div id="editcomment">
<#if !is_userlogin()>
<p>你必须 <a href="${bloginfo('site_url')}/login.ftl">登录后</a> 才能对文章进行评论！</p>
<#else>

<form action="${bloginfo('site_url')}/cms-comments-post.ftl" method="get" id="commentform">



<p>您现在是以 ${get_user_info().userinfo.user_nicename} 的身份登录,<a href="${bloginfo('site_url')}/theme/test/index.ftl?action=logoff" title="Log out of this account"> 点击退出系统 &raquo;</a></p>



<div class="clear"></div>


<!--<p><small><strong>XHTML:</strong> You can use these tags: <code><?php echo allowed_tags(); ?></code></small></p>-->

<p><label for="content">评论内容:</label><br /><textarea name="comment" id="comment" cols="100%" rows="10" tabindex="4" onkeyup="javascript:return ctrlEnter(event);"></textarea></p>

<p id="but_submit"><input name="submit" type="submit" id="submit" tabindex="5" value="提交评论" />
<input type="hidden" name="posts_id" value="${dataMap.posts_id}" >
</p>

</#if>

</form>

</div>
