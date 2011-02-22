
<@list_category >
	<div  class="bb_cats">
            <div class="cats_content">

            	<h2><a href="${dataMap.url}" title="">${dataMap.cat_name}</a></h2>
                <ul>
                    <@list_posts cat_id="${dataMap.cat_id}">
                    	<li><a href="${dataMap.posts_url}" > ${dataMap.posts_title} </a></li>
                    </@list_posts>
                </ul>
            </div>
        </div>
</@list_category >

 