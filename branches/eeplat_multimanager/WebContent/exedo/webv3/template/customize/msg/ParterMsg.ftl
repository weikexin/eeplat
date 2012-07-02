<div id="${model.objUid}" class="fb-wall" style="display: block;">

   <div class="fb-wall-box fb-wall-box-first">

	   	<div class="con">
			<div class="pd">
				<div class="share"></div>
				<div class="status">&nbsp;新的消息</div>
				<div class="loading"></div>
			</div>
			<div class="img_top"></div>
			<div class="text_status">
				<textarea class="input_box"></textarea>
			</div>
			<div class="button_outside_border_blue" id="msg_share">
				<div class="button_inside_border_blue">发布</div>
			</div>
			<div class="clear"></div>
			<div class="load_status_out"></div>
		</div>
	</div>	

  <#list data as ins>
		<div class="fb-wall-box">
			<a href="#"
				target="_blank"><img class="fb-wall-avatar"
				src="${contextPath}images/empty.gif"></a>
			
			<div class="fb-wall-data">
				<span class="fb-wall-message"><a
					href="#" class="fb-wall-message-from">${ins.map.msg_owner_name?if_exists}</a> 
					${ins.map.msg_content?if_exists}
					<br></span>
				<span class="fb-wall-date">${ins.map.mdate?if_exists}</span>
				
				<div class="fb-wall-comments">
			    <#if ( (ins.map.comments?exists) && ins.map.comments?size > 0 )>
				       <#list ins.map.comments as comm>
						<span class="fb-wall-comment"><a
							href="#s"
							class="fb-wall-comment-avatar" target="_blank"><img
								src="${contextPath}images/empty.gif"></a>
								
								<span class="fb-wall-comment-message"><a
								class="fb-wall-comment-from-name"
								href="#"
								target="_blank">${comm.map.c_owner_name?if_exists}</a> 
								${comm.map.c_content?if_exists}
								<span class="fb-wall-comment-from-date">${comm.map.mdate?if_exists}</span></span></span>
						</#list>			
				   </#if>
								
				    <span class="fb-wall-comment"> <a
							href="#s"
							class="fb-wall-comment-avatar" target="_blank"><img
								src="${contextPath}images/empty.gif"></a> 
				    <input type="text" name="ttt" /></span>			
			    </div>
			</div>


			<div class="fb-wall-clean"></div>
		</div>
  </#list>
</div>

<script type="text/javascript">

	$(".input_box").elastic().css("height","30px");

	

</script> 

