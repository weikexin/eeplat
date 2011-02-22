<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<#--开始输出空行-->
 <#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>
<#-- 格式定义 -->
<#macro JudgeStyle a>
	<#if (a.isOutGridAction?exists && ( a.isOutGridAction == 1)) >
		class='ctlBtn'
	<#elseif (a.linkPaneModel?exists && (a.linkPaneModel.cssStyle)?exists ) >
		class='${a.linkPaneModel.cssStyle}'
	<#else>
	    class='role-setup'	
	</#if>
</#macro>

<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>
<#if (model.topOutGridFormLinks?size > 0) > 
	<DIV class="toolbar" style="BORDER-RIGHT: #8db2e3 1px solid; BORDER-TOP: #8db2e3 1px solid; BORDER-LEFT: #8db2e3 1px solid; BORDER-BOTTOM: #8db2e3 1px solid">
		<DIV align="left"><!--布局用-->
			<TABLE>
				<TBODY>
					<TR>
						<TD style="WIDTH: 2px"></TD><!--左缩进-->
						<#list model.topOutGridFormLinks as item>
							<TD>
								<TABLE  cellSpacing='0' cellPadding='0'>
									<TBODY>
										<TR>
											<TD class="b_left"></TD>
											<TD class="b_center">${item.htmlValue}</TD>
											<TD class="b_right"></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
							<#if ((item_index+1)!=(model.topOutGridFormLinks?size))>
							<TD><SPAN class="spacer"></SPAN></TD><!--分隔条-->
							</#if>
						</#list>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
	</DIV>
</#if>

<div width="100%" height="100%" >

	<div id="themes" >
	<table id='g${model.objUid}' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
		<thead>
		 <tr>
			<#--隐藏列，数据部分输出记录的主键-->
			<th colspan='${datarowSize}' ></th> 
				<#--定义宏 判断输出什么类型的align-->
			 <#macro JudgeAlign item>
			    <#if item.align?exists>
			    	align='${item.align}'
			    <#else>
			        align='center' 
			    </#if>
			</#macro>
		<#--输出其它的头标题-->
		</tr>
		</thead>
		<#--Table Header部分输出完毕-->
		<tbody>
		<#if (cms?size > 0)>
			<tr>
		</#if>
		<#assign x=0 />
		<#assign rowSize = '${datarowSize}' />
	   <#list cms as ins>
				<td id='${ins_index}${model.objUid}' >
					<center><img id='${ins.theme_dir}' value='${ins.theme_dir}' class="imgzoon" src="/yiyi/exedo/webv3/template/cms/theme/${ins.theme_dir}/screenshot.jpg"  /></center>
					<p><center><h1><a  id='${model.objUid}${ins.theme_dir}' ><#if (ins.current_theme?exists)>当前使用主题<#else>启用</#if></a></h1></center></p>

				<#-- 图片单击选中 -->
				<script>
				
				</script>
				<#-- 图片双击 弹出详细资料窗口-->
					<script>
							 $('#${model.objUid}${ins.theme_dir}').bind('click',function(){
							 		
							 		var paras = "theme_dir=${ins.theme_dir}" ;
									callAction({ 'btn': this,
			  									'actionName':"com.exedosoft.plat.cms.ChageThemesAction",
			 									 'callback':callbackFunction,
			 									 'paras':paras});
							    });		
							    
							    function callbackFunction(v){
							    	
							    }
					</script>	

				<#--点击TD改变背景色 --->
				
			    <#assign x = x + 1 >
			   <#-- 判断当前行的列数是否已经等于定义的rowSize,如果不等于，并且还有未显示的数据，则增加tr-->
			<#if (x >= (rowSize?number)) && (ins_index + 1 != cms?size) >
				</tr>
				<tr>
				<#assign x=0 />
			</#if>
			<script>
			<#--alert("${ins.map.file_path}");-->
			</script>
	     </#list>
	
	     
	     <#if statistics?exists>
	      	<tr>
		      	<#if model.NO><#--是否有数字序列-->
					<td align='center'>统计</td>
				</#if>
				<td colspan="${model.normalGridFormLinks?size + 1}"> ${statistics_details?if_exists}   </td>
	        </tr>
	     </#if>
	     
	     
		</tbody>
		
		<#if (model.rowSize?exists && model.rowSize > 0)>
		<tfoot>
		  <tr>
		   <#assign cols =  (model.normalGridFormLinks?size+2)/>
		    <td  colspan="${cms?size}" >
		   &nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/first.png'  class='firstPage' title="第一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/prev.png'  class='prevPage'  title="上一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/next.png'  class='nextPage' title="下一页"/>&nbsp;&nbsp;
		    <img src='${contextPath}/images/grid/last.png'  class='lastPage' title="最后一页"/>&nbsp;&nbsp;
		    	第<span class='pageNo'>${pageNo}</span>页&nbsp;
		    	<#--每页<span class='rowSize'>${rowSize}</span>条&nbsp;-->
		    	 共<span class='pageSize'>${pageSize}</span>页 &nbsp; 
		    	共<span class="resultSize">${resultSize}</span>条记录
		      </td>
		  </tr>
		 </tfoot> 
	   </#if>
	   		
		
	</table>
	</div>
	<#if (model.bottomOutGridFormLinks?size > 0) > 
	    <table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="text-align:center" >
				<tr><td align="center" style="text-align:center">
				<#list model.bottomOutGridFormLinks as item> 
				 ${item.htmlValue} &nbsp; 
				</#list>
				</td></tr>
		</table>
	</#if>
		
</form>	


<script language="javascript">
       var x = $('#g${model.objUid} tbody  tr:first-child td').length;
	   if( ${cms?size} > 0 ){
	           //最后一行用TD填充到顶格 。
		      for (i = x - ${x}; i > 0 ; --i ){
		        $('#g${model.objUid} tbody  tr:last').append("<td/>");
		      }
	      }
	      


</script>
