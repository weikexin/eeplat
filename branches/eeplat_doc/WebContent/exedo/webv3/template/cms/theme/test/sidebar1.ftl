
<!-- 
目前是研究阶段 ，模板中的很多地方是硬编码生成的。
下一步和平台无缝集成后，需要修改模板。

-->
<div class="sidebar2">
            <li>
				<h2>分类目录</h2>
                <ul>
                	<@list_category class="cat-item cat-item-1 current-cat">
						<li class=""><a href="${dataMap.url}">${dataMap.cat_name}(${dataMap.cat_posts_count})</a> </li>
					</@list_category>
                </ul>
            </li>
            <li>
                <h2>文章归档</h2>
                    <ul>
                    	<li><a href='http://localhost/wordpress/?m=201101' title='2011 年一月'>2011 年一月</a></li>
                    </ul>
            </li>
            <li>
                <h2>功能</h2>

                    <ul>
                    <li><a href="http://localhost:8080/zwiki/templates/test/edit.ftl">发表文章</a></li>                    
                    <li><a href="http://localhost/wordpress/wp-admin/">站点管理</a></li> 
                    <#if is_userlogin()>
                    	 <li><a href="${bloginfo('site_url')}/theme/test/index.ftl?action=logoff">登出</a></li>
                  	<#else>       
                    	<li><a href="${bloginfo('site_url')}/login.ftl">登录</a></li>
                    </#if>  
                   </ul>
            </li>
   </ul> 
</div>
