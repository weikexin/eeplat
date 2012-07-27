		<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
		<br/>
		<form name="form1">
			
				<table class='tablesorter'  border="0" cellpadding="1" cellspacing="1" >
					
						<tr>
							<td class='title' colspan=2><b>${i18n('条件判断表达式')}</b></td>
						</tr>
						
						<tr>
							<td width='35%'>${i18n('表达式：')}</td>
							<td  align='left'>
								<input					type='text' name='condition'
								id='condition' title="${i18n('表达式：')}"
								size="25" /></td>
						</tr>
						
						<tr>
							<td width='35%'>${i18n('界面显示：')}</td>
							<td  align='left'>
								<input					type='text' name='ui_show'
								id='ui_show' title="${i18n('界面显示：')}"
								size="25" /></td>
						</tr>

							<tr>
								<td height='30' valign='bottom' align='center' colspan=2 style="text-align:center">
								<button type="button" title="${i18n('条件判断表达式')}"
									id='40288036151908400115194a696e009e'
									onclick="javascript:setValues(form1);return false">
								${i18n('确定')}</button>
								</td>
							</tr>
							

			
			</table>	

		</form>		
			
 <script>
				//获得参数的方法

				var selectedLineTxt = window.opener.selectedLineTxt;
					if(selectedLineTxt){
						var selectedLine = window.opener.svgDoc.getElementById(selectedLineTxt.getAttribute('id').substring(4));
						$("#condition").val(selectedLine.getAttribute("condition"));
						$("#ui_show").val(selectedLine.getAttribute("showvalue"));
				}
				
				function setValues(form1){
					
					var aValue = form1.condition.value;
					var showValue = form1.ui_show.value;
					
					if(aValue==null || $.trim(aValue)==''){
						alert("${i18n('表达式不可以为空')}");
						form1.condition.focus();
					  return;	
					}
					
				
					if(showValue==null || $.trim(showValue)==''){
						alert("${i18n('界面显示不可以为空')}");
						form1.ui_show.focus();
					  return;	
					}

					var selectedLineTxt = window.opener.selectedLineTxt;
					if(selectedLineTxt){

								var selectedLine = window.opener.svgDoc.getElementById(selectedLineTxt.getAttribute('id').substring(4));
								selectedLine.setAttribute("condition",aValue);
								selectedLine.setAttribute("showvalue",showValue);
						    window.opener.svgDoc.getElementById(selectedLineTxt.getAttribute('id')).firstChild.nodeValue=showValue;
					   }else{
					   	alert("${i18n('系统故障，请与管理员联系!')}");
					   	}
					   	window.close();

						//var svgDoc = svgWin.SVGDocument;
						//var selectedNode = svgWin.selectedNode;
						//svgDoc.getElementById('txt_' + selectedNode.getAttribute('id')).firstChild.nodeValue=aValue;
						
				} 
		</script>