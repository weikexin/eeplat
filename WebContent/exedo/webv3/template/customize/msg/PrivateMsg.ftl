
<div id="${model.objUid}" class="fb-wall" style="display: block;">
  <#list data as ins>
     <#if ins_index==0>
       <div class="fb-wall-box fb-wall-box-first">
      <#else>
		<div class="fb-wall-box">
     </#if>

			<a href="#"
				target="_blank"><img class="fb-wall-avatar"
				src="${contextPath}images/empty.gif"></a>
			
			<div class="fb-wall-data">
				<span class="fb-wall-message"><a
					href="#" class="fb-wall-message-from">${ins.map.from_user_name?if_exists}</a> 
					${ins.map.msg_content?if_exists}
					<br></span>
				<span class="fb-wall-date">${ins.map.trans_time?if_exists}</span>
		
			</div>


			<div class="fb-wall-clean"></div>
		</div>
  </#list>
</div>

