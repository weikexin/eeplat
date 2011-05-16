<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.login.zidingyi.WLogIndexMessage;import java.util.List;import java.util.ArrayList;"%>
<script type="text/javascript">
<!--
	
//-->
</script>
<% 
	 //补助信息
	String[] messages = WLogIndexMessage.getBZMessage();
	String days = messages[0]==null?"0":messages[0];
	String money = messages[1]==null?"0":messages[1];
	String address = messages[2]==null?"0":messages[2];
	String xiujiaDays = messages[3]==null?"0":messages[3];
	String xinjiaDays = messages[4]==null?"0":messages[4];
	boolean isChuchai = false;
	if(!"0.00".equals(days) && !"0.0".equals(days) && !"0".equals(days) ) {
		isChuchai = true;
	}
	
	//员工信息
	List<String[]> empList = new ArrayList<String[]>();
	empList = WLogIndexMessage.getEmpMessage();
	
	//任务列表
	String[] rwLb = WLogIndexMessage.getReWuList();
%>
<div id="workbench_container">
  <div id="gzt1" class="gztmodel">
    <div id="gztit1" class="title"><div class="icon"></div>
    <div class="titcon">出差补助信息</div></div>
	<div class="gzmid"><div class="midleft"></div>
		<div class="midcon">
		 	<span style="font-size: 120%;font-weight: normal;"><% 
		 		if(isChuchai) {
		 			%>
		 			您当前状态正在&nbsp;<span style="font-size: 110%;font-weight: bold;"><%=address %></span>&nbsp;出差。<br/>
		 			本次出差的天数已经有&nbsp;<span style="font-size: 110%;font-weight: bold;"><%=days %></span>&nbsp;天
		 			<%
		 			if(!"0".equals(xiujiaDays) && !"0.0".equals(xiujiaDays) && !"0.00".equals(xiujiaDays)) {
		 				%>
			 			，其中休假有&nbsp;<span style="font-size: 110%;font-weight: bold;"><%=xiujiaDays %></span>&nbsp;天
			 			<%
			 			if(!"0".equals(xinjiaDays) && !"0.0".equals(xinjiaDays) && !"0.00".equals(xinjiaDays)) {
			 				%>
				 			，带薪休假有&nbsp;<span style="font-size: 110%;font-weight: bold;"><%=xinjiaDays %></span>&nbsp;天
				 			<%
			 			}
		 			}
		 			if(!"0".equals(xinjiaDays) && !"0.0".equals(xinjiaDays) && !"0.00".equals(xinjiaDays)) {
		 				%>
			 			<br/>
			 			其中带薪休假有&nbsp;<span style="font-size: 110%;font-weight: bold;"><%=xinjiaDays %></span>&nbsp;天
			 			<%
		 			}
		 			%>
		 			。<br/>出差的补贴共&nbsp;<span style="font-size: 110%;font-weight: bold;"> <%=money %></span>&nbsp;元。
		 			<% 
		 		} else {
		 			%>
		 			您当前状态不是出差。<br/>
		 			出差的天数为&nbsp;<span style="font-size: 110%;font-weight: bold;"> 0 </span>&nbsp;天，
		 			出差的补贴共&nbsp;<span style="font-size: 110%;font-weight: bold;"> 0 </span>&nbsp;元。
		 			<%
		 		}
		 	%>
		 	<br><br>
		 	<a onclick="lookwlogcurrent();" href="#"><span style="color: blue;">查看本月详细信息</span></a><br>
		 	<a onclick="lookwloghistory();" href="#"><span style="color: blue;">查看历史记录信息</span></a>
		 	</span>
		</div>
	</div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt2" class="gztmodel">
	<div id="gztit2" class="title"><div class="icon"></div>
	<div class="titcon">员工信息</div>	</div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon">
				<table style="width: 100%" align="center">
						
		 					 <tr><th style="width:60px;">员工姓名</th><th style="width:100px;">当前所在地</th><th style="width:1px;">|</th><th style="width:60px;">员工姓名</th><th style="width:100px;">当前所在地</th></tr>
		 				
	    			 	<% 
		 			for(int i = 0; i < empList.size(); i++) {
		 				String[] str = empList.get(i);
		 				String userName = str[0];
		 				String waddress = str[1];
		 				if(i % 2 == 0) {
		 					%>
			 				<tr align="center">
			 				<td><%=userName %></td><td><%=waddress %></td><td style="width:1px;">|</td>
			 				<%
		 				} else {
		 				%>
		 				<td><%=userName %></td><td><%=waddress %></td>
		 				<%
		 				}
		 				if(i % 2 == 1) {
		 				%>
			 				</tr>
			 			
						<%
		 					} 
		 				%>
		 				<%
		 				}
	    			 	if(empList.size() % 2 == 1) {
	    			 		%>
			 				<td style="width:1px;">|</td><td colspan="2"></td>
			 				</tr>
			 				<%
	    			 	}
		 				%>
		 		</table>
		 				
		 			
		 	
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt3" class="gztmodel">
	<div id="gztit3" class="title"><div class="icon"></div>
	<div class="titcon">任务列表</div></div>
	<div class="gzmid"><div class="midleft">
	</div><div class="midcon">
		<span style="font-size: 120%;font-weight: normal;"><% 
			if(rwLb != null) {%>
			有&nbsp;<span style="font-size: 110%;font-weight: bold;"><%=rwLb[0] %></span>&nbsp;条日志记录等待您的审批。
			<br><br>
				<% if("bmjl".equals(rwLb[1])) { %>
				<a onclick="bmjlspym();" href="#"><span style="color: blue;">打开审批页面</span></a>
				<%
				} else if("zjl".equals(rwLb[1])) { %>
				<a onclick="zjlspym();" href="#"><span style="color: blue;">打开审批页面</span></a>
				
			<%	}
	    		}
		 	%>	
			
		</span>
		
	</div>
	</div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt4" class="gztmodel">
	<div id="gztit4" class="title"><div class="icon"></div>
	<div class="titcon">欢迎使用</div></div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon"></div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
 </div>
