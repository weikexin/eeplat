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
	<table id='g123456' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
		<thead>
		<#assign colNum = model.colNum?default(2)/>
		</thead>	

		<tbody>
			<#assign strs = ''/>
			<tr  ><td>
			<#list model.moreGridFormLinks as item>
			   <#if '${dataBind(data,item)}' ==''> <#assign strs = '${item.htmlValue}'/></#if>
			</#list>
	<!--
	<OBJECT id = "scanObj"
		classid="clsid:67D136FE-85E3-4D5A-B0AA-17D8EF312510"
		WIDTH=880 HEIGHT=620>
	</OBJECT>
	<BODY onload = "scanObj.SetInfor('${strs}')">

	</BODY>
-->
			 <div id="scanObjControl">
				<script>
					var myObject = document.createElement('object');
					scanObjControl.appendChild(myObject);
					myObject.width = "1150";
					myObject.height = "580";
					myObject.classid= "clsid:67D136FE-85E3-4D5A-B0AA-17D8EF312510"; 
					myObject.URL = "";
					myObject.uiMode = "none" ;
					myObject.SetServer("127.0.0.1", "11000");
					myObject.SetInfor('${strs}');
				</script>
			</div>
			</td></tr>	
	<#--下面是按钮部分-->
				<tr class="buttonMore" > <td  style="text-align:center;align:center">
				<#list model.bottomOutGridFormLinks as item> 
			          <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
				</#list>
				</td></tr>
		</tbody>
	  </table>
</form>	
</div>	
