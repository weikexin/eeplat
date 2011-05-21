<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()>  
<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>
<div width="100%">

<#if (model.topOutGridFormLinks?size > 0) > 
	<DIV class="toolbar" style="BORDER-RIGHT: #8db2e3 1px solid; BORDER-TOP: #8db2e3 1px solid; BORDER-LEFT: #8db2e3 1px solid; BORDER-BOTTOM: #8db2e3 1px solid">
		<DIV align="left"><!--布局用-->
			<TABLE>
				<TBODY>
					<TR>
						<TD style="WIDTH: 2px"></TD><!--左缩进-->
						<#list model.topOutGridFormLinks as item>
							<TD>
								<TABLE  cellSpacing=0 cellPadding=0>
									<TBODY>
										<TR class="">
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
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
		<thead>
		<#assign colNum = model.colNum?default(2)/>
		<#if model.caption?exists>
			<#assign icon = (model.icon)?default("MyRightArrow.jpg")/>
			<tr> 
				<td class='title' colspan="${colNum*2}"> <img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> </td>
			</tr>
		</#if> 
		</thead>	
		
		<tbody>
			<tr><td colspan="${colNum*2}" >
			<#if (model.moreGridFormLinks?size > 0) >
				<#assign emp_uid = ''/>
				<#assign tjdate = ''/>
				<#assign wdate = ''/>
				<#assign waddress = ''/>
				<#assign wprogress = ''/>
				<#assign wcontent = ''/>
				<#assign wdifficulties = ''/>
				<#assign wtomorplan = ''/>
				<#list model.moreGridFormLinks as item>
			     	<#if  item_index+1 == 1>
				   		<#if '${dataBind(data,item)}' ==''> <#assign emp_uid = '${item.htmlValue}'/></#if>
			    	</#if> 
			    	<#if  item_index+1 == 2>
				   		<#if '${dataBind(data,item)}' ==''> <#assign tjdate = '${item.htmlValue}'/></#if>
			    	</#if> 
			    	<#if  item_index+1 == 3>
				   		<#if '${dataBind(data,item)}' ==''> <#assign wdate = '${item.htmlValue}'/></#if>
			    	</#if> 
			    	<#if  item_index+1 == 4>
				   		<#if '${dataBind(data,item)}' ==''> <#assign waddress = '${item.htmlValue}'/></#if>
			    	</#if> 
			    	<#if  item_index+1 == 5>
				   		<#if '${dataBind(data,item)}' ==''> <#assign wprogress = '${item.htmlValue}'/></#if>
			    	</#if> 
			    	<#if  item_index+1 == 6>
				   		<#if '${dataBind(data,item)}' ==''> <#assign wcontent = '${item.htmlValue}'/></#if>
			    	</#if> 
			    	<#if  item_index+1 == 7>
				   		<#if '${dataBind(data,item)}' ==''> <#assign wdifficulties = '${item.htmlValue}'/></#if>
			    	</#if> 
			    	<#if  item_index+1 == 8>
				   		<#if '${dataBind(data,item)}' ==''> <#assign wtomorplan = '${item.htmlValue}'/></#if>
			    	</#if> 
				</#list>
			<div style="width: 100%;" align="center" >
			<div style="width: 88%;border: 4px solid #F3F3FA;" align="center" >
			<div style="width: 85%;margin-bottom: 20px;margin-top: 10px;" align="center" >
			<div style="min-height: 30px;_height:30px;" >
				<span  style="font-size: 160%;font-weight: 800;position: relative;left:-20px;" >员工日志浏览</span>
			</div>
			<div style="width:88%;" align="right" >
				<span style="font-size: 100%;font-weight:lighter;">员工姓名：${emp_uid}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填写日期：${tjdate}</span>
			</div>
			<div align="left" style="position: relative;left:20px;border: 8px solid white;"><p><span style="font-size: 120%;font-weight: 800;position: relative;left:0px;">工作日期：</span><span style="font-size: 100%;">${wdate}</span><span style="font-size: 120%;font-weight: 800;position: relative;left:30px;">工作地点：</span><span style="font-size: 100%;position: relative;left:30px;">${waddress}</span><span style="font-size: 120%;font-weight: 800;position: relative;left:60px;">工作进度：</span><span style="font-size: 100%;position: relative;left:60px;">${wprogress}</span></p></div>
			<#--
			<div align="left" style="position: relative;left:20px;border: 8px solid white;"><p><span style="font-size: 110%;font:bold;">工作地点：</span><span style="font-size: 100%;">公司或本地</span></p></div>
			<div align="left" style="position: relative;left:20px;border: 8px solid white;"><p><span style="font-size: 110%;font:bold;">工作进度：</span><span style="font-size: 100%;">正常</span></p></div>
			 -->
			<div align="left" style="position: relative;left:20px;border: 8px solid white"><p><span style="font-size: 120%;font-weight: 800;">工作内容：</span></p></div>
			<div align="left" style="position: relative;left:0px;border: 2px solid #F3F3FA;min-height: 130px;_height:130px;">
				<span style="font-size: 100%;font-weight:lighter;font:bold;position: relative;left:3px;letter-spacing: 1pt;line-height: 16pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${wcontent}</span>
			</div>
			
			<div align="left" style="position: relative;left:20px;border: 8px solid white;"><p><span style="font-size: 120%;font-weight: 800;">遇到困难：</span></p></div>
			<div align="left" style="position: relative;left:0px;border: 2px solid #F3F3FA;min-height: 90px;_height:90px;">
				<span style="font-size: 100%;font-weight:lighter;font:bold;position: relative;left:3px;letter-spacing: 1pt;line-height: 16pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${wdifficulties}</span>				
			</div>
			
			<div align="left" style="position: relative;left:20px;border: 8px solid white;"><p><span style="font-size: 120%;font-weight: 800;">明日计划：</span></p></div>
			<div align="left" style="position: relative;left:0px;border: 2px solid #F3F3FA;min-height: 90px;_height:90px;">
				<span style="font-size: 100%;font-weight:lighter;font:bold;position: relative;left:3px;letter-spacing: 1pt;line-height: 16pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${wtomorplan}</span>				
			</div>
			</div>
		</div>
	</div>
				
			</td>
			</tr>	
	<#--下面是按钮部分-->
			<#if  (model.bottomOutGridFormLinks?size > 0)>
				<tr class="buttonMore" > <td  style="text-align:center;align:center"  colspan="${colNum*2}" >
				<#list model.bottomOutGridFormLinks as item> 
			          <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
				</#list>
				</td></tr>
				</#if>		
			</#if> 	
		</tbody>
	  </table>
</form>	
</div>	
<script>
</script>

