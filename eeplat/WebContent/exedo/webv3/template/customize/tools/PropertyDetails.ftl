<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
			<script>
				//获得参数的方法
					
				$(function(){
					try{
							var selectedNode = window.opener.selectedNodeBak;
							var nodetype = selectedNode.getAttribute('nodetype');
							if(nodetype=='xorDecision' 
							||  nodetype=='andDecision' 
							||  nodetype=='start'
							||  nodetype=='end'
							||  nodetype=='andConjuction'){
								
									$("#trNodeStateShow").css('display','none');	
									$("#trNodeStateShowBack").css('display','none');	
									$("#trAuthType").css('display','none');	
									$("#trAccessClass").css('display','none');	
									$("#trSpecName").css('display','none');	
									$("#trPassTxt").css('display','none');	
									$("#trRejectTxt").css('display','none');	
									$("#trAutoService").css('display','none');	
									$("#trPaneName").css('display','none');
									$("#trNodeExt1").css('display','none');
							}
							
							if( nodetype=='start'
							||  nodetype=='end'
							||  nodetype=='activity'
							||  nodetype=='andDecision' 
							||  nodetype=='andConjuction'){

									$("#trDecisionExpression").css('display','none');	
									$("#trDecisionType").css('display','none');	
									$("#trAutoService").css('display','none');	
									$("#trNodeExt1").css('display','none');
							}
							
							
							if( nodetype=='auto' ||  nodetype=='subFlow'
							 ){
									$("#trPaneName").css('display','none');
									$("#trDecisionExpression").css('display','none');	
									$("#trDecisionType").css('display','none');	
									$("#trNodeStateShow").css('display','none');	
									$("#trNodeStateShowBack").css('display','none');	
									$("#trAuthType").css('display','none');	
									$("#trAccessClass").css('display','none');	
									$("#trSpecName").css('display','none');	
									$("#trPassTxt").css('display','none');	
									$("#trRejectTxt").css('display','none');	
									if(nodetype=='subFlow'){
										$("#trAutoService").css('display','none');	
									}else{
										$("#trNodeExt1").css('display','none');
									}
							}
							
							
							///////下面是赋值语句
							
							var selectedNode = window.opener.selectedNodeBak;

						  $("#node_id").text(selectedNode.getAttribute('id'));	
						  $("#node_name").val(selectedNode.getAttribute('nodename'));	
						  $("#nodeStateShow").val(selectedNode.getAttribute('nodestateshow'));	
						  $("#nodeStateShowBack").val(selectedNode.getAttribute('nodestateshowback'));	
						  $("#authType").val(selectedNode.getAttribute('authtype'));	
						  $("#accessClass").val(selectedNode.getAttribute('accessclass'));	
						  $("#specName").val(selectedNode.getAttribute('specname'));	
						  $("#passTxt").val(selectedNode.getAttribute('passtxt'));	
						  $("#rejectTxt").val(selectedNode.getAttribute('rejecttxt'));	
						  $("#autoService").val(selectedNode.getAttribute('autoservice'));	
						  $("#paneName").val(selectedNode.getAttribute('panename'));	
						  $("#decisionExpression").val(selectedNode.getAttribute('decisionexpression'));	
						  
						  $("#decisionType").val(selectedNode.getAttribute('decisiontype'));	
						  $("#nodeDesc").val(selectedNode.getAttribute('nodedesc'));
						  $("#node_ext1").val(selectedNode.getAttribute('subflow'));		
						  
						  ////刷新总线 当前节点为选中的节点
						  callPlatBus({'paras':'dataBus=setContext&contextKey=do_pt_node_reference&contextValue=' + selectedNode.getAttribute('id') });
						  
														
						
					}catch(e){
					}
			   }
			  ) 
				
				function setValues(){
				
				        var  aValue = $("#node_name").val();
						if(aValue==null  || $.trim(aValue)==''){
						   alert("节点名称不能为空!");
						   return;	
						}
					
					/////为节点赋值
						var selectedNode = window.opener.selectedNodeBak;
						selectedNode.setAttribute("nodename",aValue);

						selectedNode.setAttribute("nodestateshow",$("#nodeStateShow").val());
						selectedNode.setAttribute("nodestateshowback",$("#nodeStateShowBack").val());
						selectedNode.setAttribute("authtype",$("#authType option:selected").val());
						selectedNode.setAttribute("accessclass",$("#accessClass").val());
						selectedNode.setAttribute("specname",$("#specName").val());
						selectedNode.setAttribute("passtxt",$("#passTxt").val());
						selectedNode.setAttribute("rejecttxt",$("#rejectTxt").val());
						selectedNode.setAttribute("autoservice",$("#autoService").val());
						selectedNode.setAttribute("panename",$("#paneName").val());
						selectedNode.setAttribute("decisionexpression",$("#decisionExpression").val());
						selectedNode.setAttribute("decisiontype",$("#decisionType").val());
						selectedNode.setAttribute("nodedesc",$("#nodeDesc").val());
						selectedNode.setAttribute("subflow",$("#node_ext1").val());
						
						
						
				    window.opener.svgDoc.getElementById('txt_' + selectedNode.getAttribute('id')).firstChild.nodeValue=aValue
					    
				    window.close();

						//var svgDoc = svgWin.SVGDocument;
						//var selectedNode = svgWin.selectedNode;
						//svgDoc.getElementById('txt_' + selectedNode.getAttribute('id')).firstChild.nodeValue=aValue;
						
				} 
		</script>
		<br/>
		<form name="propertyDetail" id="propertyDetail">
			
			<table class='tablesorter'  border="0" cellpadding="1" cellspacing="1" >
					
					<thead>
						<tr>
							<td class='title' colspan=2><b>${i18n('节点属性编辑')}</b></td>
						</tr>
					</thead>
					<tbody>
					   <tr>
							<td width="35%">${i18n('节点ID：')}</td>
							<td align="left"><span  name="node_id" id="node_id" title="${i18n('节点属性编辑')}" size="25"/></td>
						</tr>	
						<tr>
							<td width="35%">${i18n('节点名称：')}</td>
							<td align="left"><input type="text" name="node_name" id="node_name" title="${i18n('节点名称：')}" size="25"/></td>
						</tr>
						<tr id="trNodeStateShow" >
							<td   width='35%'>${i18n('流程状态：')}</td>
							<td    align='left'>
								<input					type='text' name='nodeStateShow'
								id='nodeStateShow' title='${i18n('流程状态：')}'
								size="25" /></td>
						</tr>
						
						<tr id="trNodeStateShowBack">
							<td  width='35%'>${i18n('回退时流程状态：')}</td>
							<td   align='left'>
								<input					type='text' name='nodeStateShowBack'
								id='nodeStateShowBack' title='${i18n('回退时流程状态')}'
								size="25" /></td>
						</tr>
						
						<tr id="trAuthType">
							<td  width='35%'>${i18n('权限验证类型：')}</td>
							<td   align='left'>
							<select	name="authType" id="authType" title='${i18n('权限验证类型：')}'>
	
								<option value="0">${i18n('标准权限表')}</option>
								<option value="-1">${i18n('==以下类型不适合通用的VIEW==')}</option>
								<option value="3">${i18n('数据拥有者')}</option>
								<option value="8">${i18n('运行时指定用户')}</option>
								<option value="16">${i18n('运行时指定角色')}</option>
								<option value="18">${i18n('运行时通过类指定于工作流权限表')}</option>
								<option value="-1">${i18n('==以下类型只能通过API获取个人工作列表==')}</option>
								<option value="1">${i18n('使用脚本')}</option>
								<option value="2">${i18n('使用JAVA类')}</option> 
							</select>	
		
						</tr>
						
												
						<tr id="trAccessClass">
							<td  width='35%'>${i18n('权限控制类：')}</td>
							<td align='left'>
								<input		type='text' name='accessClass'
								id='accessClass' title='${i18n('权限控制类：')}'
								size="25" /></td>
						</tr>
						
						
						<tr id="trSpecName">
							<td  width='35%'>${i18n('数据拥有者标识：')}</td>
							<td   align='left'>
								<input					type='text' name='specName'
								id='specName' title='${i18n('数据拥有者标识：')}'
								size="25" /></td>
						</tr>
						
						<tr id="trDecisionType">
							<td  width='35%'>${i18n('条件判断类型：')}</td>
							<td   align='left'>
							<select
							name="decisionType" id="decisionType" title='${i18n('条件判断类型：')}'>
								<option value="1">${i18n('使用脚本')}</option>
								<option value="2">${i18n('使用JAVA类')}</option>
							</select>	
						</tr>
						
						<tr id="trDecisionExpression">
							<td  width='35%'>${i18n('条件判断表达式(Class)：')}</td>
							<td   align='left'>
								<input type='text' name='decisionExpression'
								id='decisionExpression' title='${i18n('条件判断表达式(Class)：')}'
								size="25" /></td>
						</tr>


						<tr id="trAutoService">
							<td  width='35%'>${i18n('自动服务：')}</td>
							<td   align='left'>
								<input	type='text' name='autoService'
								id='autoService' title='${i18n('自动服务：')}'
								size="25" /></td>
						</tr>
						
						<tr id="trPaneName">
							<td  width='35%'>${i18n('审批面板名称：')}</td>
							<td   align='left'>
								<input	type='text' name='paneName'
								id='paneName' title='${i18n('审批面板名称：')}'
								size="25" /></td>
						</tr>
						
						<tr id="trPassTxt">
							<td  width='35%'>${i18n('通过意见界面表单字段：')}</td>
							<td   align='left'>
								<input		type='text' name='passTxt'
								id='passTxt' title='${i18n('通过意见界面表单字段：')}'
								size="25" /></td>
						</tr>
						
						<tr  id="trRejectTxt">
							<td  width='35%'>${i18n('驳回意见界面表单字段：')}</td>
							<td align='left'>
								<input		type='text' name='rejectTxt'
								id='rejectTxt' title='${i18n('驳回意见界面表单字段：')}'
								size="25" /></td>
						</tr>
						
					    <tr id='trNodeExt1'>
							<td width='35%'>${i18n('关联流程：')}</td>
							<td   align='left'>
								<input	type='text' name='node_ext1'
								 title='${i18n('关联流程：')}' id='node_ext1'
								size="25" >  </td>
						</tr>
						
						<tr>
							<td width='35%'>${i18n('节点描述：')}</td>
							<td   align='left'>
								<textarea		cols="25" rows="3"		type='text' name='nodeDesc'
								id='nodeDesc' title='${i18n('节点描述：')}'
								size="20" > </textarea>  </td>
						</tr>
						

							<tr>
								<td  height='30' valign='bottom' colspan=2 style="text-align:center">
								<button type="button" title='${i18n('确定')}'
									id='40288036151908400115194a696e009e'
									onclick="javascript:setValues();return false">
								${i18n('确定')}</button>
								</td>
							</tr>
	                 </tbody> 
					</table>	
			</form>		