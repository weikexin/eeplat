<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.exedosoft.plat.bo.org.DOAuthSuite"%>
<%@page import="com.exedosoft.plat.ui.DOMenuModel"%>
<%@page import="com.exedosoft.plat.bo.DOBO"%>
<%@page import="com.exedosoft.plat.bo.BOInstance"%>
<%@page import="java.util.Iterator"%>

<%
String roleUid = request.getParameter("contextInstanceUid");
DOBO boBO = DOBO.getDOBOByName("do.bx.role");
BOInstance roleBI = boBO.getInstance(roleUid);

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="<%=request.getContextPath() %>/exedo/web/js/dojo/dojo/dojo.js"
		djConfig="parseOnLoad: true, isDebug: true"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/exedo/web/js/ExedoAjax.js"   charset="gb2312"></script>

<title>权限设置</title>
</head>
<!--Other Scripts  -->
<body bgcolor="#d6cfce" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr> 
    
    <td valign="top"> <br>
      <table width="98%" border="0" align="center" cellspacing="0" cellpadding="0" height="100%">
        <tr> 
          <td bgcolor="#d6cfce" valign="top"> 
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td height="25" colspan="3"> &nbsp;&nbsp;<b>修改岗位权限</b></td>
              </tr>
              <tr><td height="1" colspan="3" bgcolor="#333333"></td></tr>
              <tr><td height="1" colspan="3" bgcolor="#ffffff"></td></tr>
              <tr>
                <td height="1" colspan="3" valign="top"> 
                  <br>     
                    <form name="Frm_Permit" method="post" >
                      <input type="hidden" id="AskType" name="AskType" value="Add">
                      <input type="hidden" id="fdEmpID" name="fdEmpID" value="1">
                    <TABLE align=center bgColor=C6EBDE border=0 cellPadding=4 cellSpacing=1 width=780>
                      <TBODY> 
                      <TR height=25 bgcolor="C6EBDE"> 
                        <Td height="22" colspan="2"><FONT color=#ffffff><b>:<font color="#000000">: 
                          岗位信息::</font></b></FONT></td>
                      </TR>
                      <TR > 
                        <TD bgColor=#ffffff width="141">岗位ID：</TD>
                        <TD  bgColor=#ffffff width="620"><%=roleBI.getValue("roleId") %></TD>
                      </TR>                      
                      <TR > 
                        <TD bgColor=#ffffff width="141">岗位名称：</TD>
                        <TD  bgColor=#ffffff width="620"><%=roleBI.getName()%></TD>
                      </TR>
                      <TR > 
                        <TD bgColor=#ffffff width="141">描述：</TD>           
                        <TD  bgColor=#ffffff width="620"><%=roleBI.getValue("description")%></TD>
                      </TR>
                      
                      <TR bgcolor="C6EBDE" > 
                        <TD colspan="2" ><b><font color="#000000">:: 岗位权限定义::</font></b></TD>
                      </TR>
                      <TR > 
                        <TD bgColor=#ffffff width="141">是否允许Excel</TD>
                        <TD width="620"  bgColor=#ffffff> 
							<INPUT type="radio" id=fdCanTranToExcel name=fdCanTranToExcel checked value=0>不允许
							<INPUT type="radio" id=fdCanTranToExcel name=fdCanTranToExcel value=1 >允许 （如不允许转换<font color="#0000ff">全部Excel</font>，请选择<font color="#ff0000">不允许</font>，相应权限请在下方选择。）
                        </TD>
                      </TR>
                    
                  
                      <TR >
                        <TD valign="top" bgColor=#ffffff>限定年级、学科、班次<br>
                          （按CTRL多选）</TD>
                        <TD width="620"  bgColor=#ffffff>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#FF0000">年级</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#FF0000">学科</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#FF0000">班次</font><br>

   <select id="FDGrade" class='css_InputSelect' name="FDGrade"  size=10 multiple >
   		<option value="" selected>不限定</option>
	
		<option value="1" >小学一年级</option>
		
		<option value="2" >小学二年级</option>
		
		<option value="3" >小学三年级</option>
		
		<option value="4" >小学四年级</option>
		
		<option value="5" >小学五年级</option>
		
		<option value="6" >小学六年级</option>
		
		<option value="7" >初中一年级</option>
		
		<option value="8" >初中二年级</option>
		
		<option value="9" >初中三年级</option>
		
		<option value="11" >高中一年级</option>
		
		<option value="12" >高中二年级</option>
		
		<option value="13" >高中三年级</option>
		
		<option value="23" >小学组</option>
		
		<option value="24" >初中组</option>
		
		<option value="32" >石油附小二年级</option>
		
		<option value="33" >石油附小三年级</option>
		
		<option value="34" >石油附小四年级</option>
		
		<option value="35" >石油附小五年级</option>
		
		<option value="36" >石油附小六年级</option>
		
		<option value="37" >初一尖子班</option>
		
		<option value="38" >三年级</option>
		
		<option value="39" >花园班</option>
		
		<option value="40" >高三</option>
		
		<option value="41" >高二</option>
		
		 </select>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<select id="FDSubject" class='css_InputSelect' name="FDSubject" size=10 multiple>
		<option value="" selected>不限定</option>
	
		<option value="1" >数学</option>
		
		<option value="2" >英语</option>
		
		<option value="3" >物理</option>
		
		<option value="4" >化学</option>
		
		<option value="5" >语文</option>
		
		<option value="9" >其它</option>
		
		<option value="10" >理化</option>
		
		<option value="11" >数英</option>
		
		<option value="12" >测试</option>
		
		<option value="14" >综合</option>
		
</select>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<select id="FDClassLevel" class='css_InputSelect' name="FDClassLevel" size=10 multiple>
	<option value="" selected>不限定</option>
	   
			<option value="竞赛班" >竞赛班</option>
			   
			<option value="龙班" >龙班</option>
			   
			<option value="实验班" >实验班</option>
			   
			<option value="基础班" >基础班</option>
			   
			<option value="提高班" >提高班</option>
			   
			<option value="精英班" >精英班</option>
			   
			<option value="目标人大附四中班" >目标人大附四中班</option>
			   
			<option value="竞赛班" >竞赛班</option>
			   
			<option value="小学【此班取消】" >小学【此班取消】</option>
			   
			<option value="高中［此班取消］" >高中［此班取消］</option>
			   
			<option value="口语测试" >口语测试</option>
			   
			<option value="初中【班级取消】" >初中【班级取消】</option>
			   
			<option value="基础班" >基础班</option>
			   
			<option value="提高班" >提高班</option>
			   
			<option value="精英班" >精英班</option>
			   
			<option value="公一决胜班" >公一决胜班</option>
			   
			<option value="公二决胜班" >公二决胜班</option>
			   
			<option value="公三决胜班" >公三决胜班</option>
			   
			<option value="家教班" >家教班</option>
			   
			<option value="目标清华北大班" >目标清华北大班</option>
			   
			<option value="新概念三册上" >新概念三册上</option>
			   
			<option value="新概念二册中" >新概念二册中</option>
			   
			<option value="新概念二册上" >新概念二册上</option>
			   
			<option value="KET下" >KET下</option>
			   
			<option value="PET下" >PET下</option>
			   
			<option value="新概念三册中" >新概念三册中</option>
			   
			<option value="新概念二册中" >新概念二册中</option>
			   
			<option value="新概念二册下" >新概念二册下</option>
			   
			<option value="新概念三册下" >新概念三册下</option>
			   
			<option value="新概念三册上" >新概念三册上</option>
			   
			<option value="新概念一册上" >新概念一册上</option>
			   
			<option value="目标人大附实验中学班" >目标人大附实验中学班</option>
			   
			<option value="英语精英班" >英语精英班</option>
			   
			<option value="重点中学英语实验班" >重点中学英语实验班</option>
			   
			<option value="三一口语4级" >三一口语4级</option>
			   
			<option value="三一口语5级" >三一口语5级</option>
			   
			<option value="新概念一册下" >新概念一册下</option>
			   
			<option value="三一口语3级" >三一口语3级</option>
			   
			<option value="KET上" >KET上</option>
			   
			<option value="新概念三册中" >新概念三册中</option>
			   
			<option value="提高班" >提高班</option>
			   
			<option value="精英班" >精英班</option>
			   
			<option value="BETS1" >BETS1</option>
			   
			<option value="BETS2" >BETS2</option>
			   
			<option value="西城提高班" >西城提高班</option>
			   
			<option value="海淀提高班" >海淀提高班</option>
			   
			<option value="非海淀非西城提高班" >非海淀非西城提高班</option>
			   
			<option value="化学提高班" >化学提高班</option>
			   
			<option value="三一口语4级" >三一口语4级</option>
			   
			<option value="三一口语5级" >三一口语5级</option>
			   
			<option value="三一口语6级" >三一口语6级</option>
			   
			<option value="PET上" >PET上</option>
			   
			<option value="10月三一3级冲刺班" >10月三一3级冲刺班</option>
			   
			<option value="11月三一4级冲刺班" >11月三一4级冲刺班</option>
			   
			<option value="11月三一3级冲刺班" >11月三一3级冲刺班</option>
			   
			<option value="11月三一5级冲刺班" >11月三一5级冲刺班</option>
			   
			<option value="11月三一6级冲刺班" >11月三一6级冲刺班</option>
			   
			<option value="12月三一6级过渡冲刺" >12月三一6级过渡冲刺</option>
			   
			<option value="行程班" >行程班</option>
			   
			<option value="数论班" >数论班</option>
			   
			<option value="几何班" >几何班</option>
			   
			<option value="中考几何班" >中考几何班</option>
			   
			<option value="中考函数班" >中考函数班</option>
			   
			<option value="中考语法班" >中考语法班</option>
			   
			<option value="小学科英复赛模考班" >小学科英复赛模考班</option>
			   
			<option value="初中科英复赛模考班" >初中科英复赛模考班</option>
			   
			<option value="趣味数学班" >趣味数学班</option>
			   
			<option value="趣味奥数知识引导班" >趣味奥数知识引导班</option>
			   
			<option value="典型专题班" >典型专题班</option>
			   
			<option value="数学联赛内部培训班" >数学联赛内部培训班</option>
			   
			<option value="科技英语发放准考证" >科技英语发放准考证</option>
			   
			<option value="科技英语发放准考证" >科技英语发放准考证</option>
			   
			<option value="石油附小A" >石油附小A</option>
			   
			<option value="石油附小B" >石油附小B</option>
			   
			<option value="科英初赛班" >科英初赛班</option>
			   
			<option value="科英精英班" >科英精英班</option>
			   
			<option value="科技英语初赛班" >科技英语初赛班</option>
			   
			<option value="11月三一7级冲刺班" >11月三一7级冲刺班</option>
			   
			<option value="12月三一7级冲刺班" >12月三一7级冲刺班</option>
			   
			<option value="目标人大附冲刺班" >目标人大附冲刺班</option>
			   
			<option value="目标人大附四中班" >目标人大附四中班</option>
			   
			<option value="12月三一4级冲刺班" >12月三一4级冲刺班</option>
			   
			<option value="12月三一5级冲刺班" >12月三一5级冲刺班</option>
			   
			<option value="1月三一6级冲刺班" >1月三一6级冲刺班</option>
			   
			<option value="中考物理班" >中考物理班</option>
			   
			<option value="小学科英决赛模考班" >小学科英决赛模考班</option>
			   
			<option value="全国联赛集训班" >全国联赛集训班</option>
			   
			<option value="11月三一1级冲刺班" >11月三一1级冲刺班</option>
			   
			<option value="11月三一2级冲刺班" >11月三一2级冲刺班</option>
			   
			<option value="12月三一1级冲刺班" >12月三一1级冲刺班</option>
			   
			<option value="12月三一2级冲刺班" >12月三一2级冲刺班</option>
			   
			<option value="口语测试(针对寒假)" >口语测试(针对寒假)</option>
			   
			<option value="小学科英初赛冲刺班" >小学科英初赛冲刺班</option>
			   
			<option value="KET冲刺班" >KET冲刺班</option>
			   
			<option value="PET冲刺班"  >PET冲刺班</option>
			   
			<option value="2月三一4级过渡班" >2月三一4级过渡班</option>
			   
			<option value="2月三一5级过渡班" >2月三一5级过渡班</option>
			   
			<option value="2月三一2级冲刺班" >2月三一2级冲刺班</option>
			   
			<option value="2月三一3级冲刺班" >2月三一3级冲刺班</option>
			   
			<option value="2月三一4级冲刺班" >2月三一4级冲刺班</option>
			   
			<option value="2月三一5级冲刺班" >2月三一5级冲刺班</option>
			   
			<option value="2月三一6级冲刺班" >2月三一6级冲刺班</option>
			   
			<option value="2月三一6级过渡班" >2月三一6级过渡班</option>
			   
			<option value="2月三一7级冲刺班" >2月三一7级冲刺班</option>
			   
			<option value="2月三一7级过渡班" >2月三一7级过渡班</option>
			   
			<option value="BETS1过渡班" >BETS1过渡班</option>
			   
			<option value="BETS2过渡班上" >BETS2过渡班上</option>
			   
			<option value="剑桥少儿一A上" >剑桥少儿一A上</option>
			   
			<option value="剑桥少儿二A上" >剑桥少儿二A上</option>
			   
			<option value="剑桥少儿三A上" >剑桥少儿三A上</option>
			   
			<option value="新概念一册(49-72课)" >新概念一册(49-72课)</option>
			   
			<option value="新概念一册(73-96课)" >新概念一册(73-96课)</option>
			   
			<option value="新概念二册(01-16课)" >新概念二册(01-16课)</option>
			   
			<option value="新概念二册(41-54课)" >新概念二册(41-54课)</option>
			   
			<option value="新概念二册(69-96课)" >新概念二册(69-96课)</option>
			   
			<option value="新概念三册(21-30课)" >新概念三册(21-30课)</option>
			   
			<option value="新概念三册(41-60课)" >新概念三册(41-60课)</option>
			   
			<option value="新概念二册(01-16课)" >新概念二册(01-16课)</option>
			   
			<option value="新概念二册(33-48课)" >新概念二册(33-48课)</option>
			   
			<option value="新概念三册(01-10课)" >新概念三册(01-10课)</option>
			   
			<option value="新概念三册(21-30课)" >新概念三册(21-30课)</option>
			   
			<option value="新概念三册(41-60课)" >新概念三册(41-60课)</option>
			   
			<option value="新概念二册(69-96课)" >新概念二册(69-96课)</option>
			   
			<option value="08年5月剑桥KET下" >08年5月剑桥KET下</option>
			   
			<option value="物理化学冲刺班" >物理化学冲刺班</option>
			   
			<option value="衔接班" >衔接班</option>
			   
			<option value="中考冲刺班" >中考冲刺班</option>
			   
			<option value="公英优惠券" >公英优惠券</option>
			   
			<option value="尖子生班" >尖子生班</option>
			   
			<option value="系统复习班" >系统复习班</option>
			   
			<option value="EMC" >EMC</option>
			   
			<option value="数学解题能力班" >数学解题能力班</option>
			   
			<option value="1月三一3级冲刺班" >1月三一3级冲刺班</option>
			   
			<option value="1月三一4级冲刺班" >1月三一4级冲刺班</option>
			   
			<option value="1月三一5级冲刺班" >1月三一5级冲刺班</option>
			   
			<option value="剑桥少儿测试" >剑桥少儿测试</option>
			   
			<option value="08年5月剑桥KET上" >08年5月剑桥KET上</option>
			   
			<option value="公益公开课" >公益公开课</option>
			   
			<option value="英语实验班" >英语实验班</option>
			   
			<option value="迎春杯准考证" >迎春杯准考证</option>
			   
			<option value="迎春杯准考证" >迎春杯准考证</option>
			   
			<option value="数英复习" >数英复习</option>
			   
			<option value="天堂" >天堂</option>
			   
			<option value="新概念二（17－48课）" >新概念二（17－48课）</option>
			   
			<option value="1" >1</option>
			   
			<option value="预科班" >预科班</option>
			   
			<option value="预科班" >预科班</option>
		
		 </select>                          </TD>
                      </TR>
                      <TR >
                        <TD bgColor=#ffffff>查看满意度</TD>
                        <TD  bgColor=#ffffff>        
		
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='知春路知音楼'>知春路知音楼
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='翠微路印刷所'>翠微路印刷所
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='大钟寺中鼎大厦'>大钟寺中鼎大厦
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='世纪城姜杰钢琴城F座'>世纪城姜杰钢琴城F座
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='花园路高德北楼'>花园路高德北楼<br>
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='东四十条万信大厦'>东四十条万信大厦
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='公主坟城乡华懋'>公主坟城乡华懋
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='中关村绿创大厦'>中关村绿创大厦
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='亚运村北奥大厦'>亚运村北奥大厦
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='上地科贸大厦'>上地科贸大厦<br>
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='安贞博纳德'>安贞博纳德
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='方庄盛方中心C段'>方庄盛方中心C段
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='林家教学点'>林家教学点
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='北三环环区'>北三环环区
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='花园'>花园<br>
		<INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='北奥大厦'>北奥大厦
 <INPUT type="checkbox" id=FDEmpArea name=FDEmpArea value='呼叫中心'>呼叫中心
 </TD>
      </TR>
 
 <%
 
 DOMenuModel dmm = DOMenuModel.getMenuModelByID("40288a05197f1dd601197f1ddfb5000c");
 List parents =  dmm.retrieveChildrenNoAuth();
 for(Iterator it = parents.iterator(); it.hasNext();){
	 DOMenuModel pMenu = (DOMenuModel)it.next();

 
 %>
                 
                      
                      
                      <TR> 
                        <TD bgColor=#ffffff width="141" height="32"><%=pMenu.getL10n() %></TD>
                        <TD  bgColor=#ffffff width="620" height="32"> 
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td> 
                             <%
                                StringBuffer buffer = new StringBuffer();
                                StringBuffer radios = new StringBuffer();
								 List childrens =  pMenu.retrieveChildrenNoAuth();
								 for(Iterator itChild = childrens.iterator(); itChild.hasNext();){
									 DOMenuModel cMenu = (DOMenuModel)itChild.next(); 
									 buffer.append(cMenu.getObjUid()).append(",");
									 String checked = "";
									 if(cMenu.isAccessByRole(roleUid)){
										 checked = " checked='checked' ";
									 }
									 %>
                              <input type="checkbox" name="menuUid"  id="a<%=cMenu.getObjUid()%>" <%=checked%>  value="<%=cMenu.getObjUid()%>">
                                <%=cMenu.getL10n() %><br>
								  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  
										 <%
											 DOAuthSuite das =  cMenu.getAuthSuite();
											 if(das!=null){
											 	radios.append(das.getIncludeUid()).append(",");
		
										 %> 
    									<INPUT type="radio" id="o<%=das.getIncludeUid()%>" name="formModelUid" checked value=0>不允许Excel
										<INPUT type="radio" id="o<%=das.getIncludeUid()%>" name="formModelUid" value=1 >允许Excel
																			
										<%} %>
							<br>
								<%} %>
                             
								
                              </td>
                            </tr>
                            <tr> 
                              <td align="right"> 
                                <input type="button" name="Button" value="全选" class="css_Button" onClick="javascript:selectAuthCheck('<%=buffer%>','<%=radios%>',true)">
                                <input type="button" name="Submit3" value="取消" class="css_Button" onClick="javascript:selectAuthCheck('<%=buffer%>','<%=radios%>',false)" >
                              </td>
                            </tr>
                            
                               </table>
                        </TD>
                      </TR>
                            
           <%} %>                 
      
   				   <!-- 
					 <TR > 
                        <TD  bgColor=#ffffff colspan="2"> 
                          <input type="button" name="selectall"  value="选择所有选项"  class="css_Button"  onclick="javascript:SelectCheck(1,185)">
                          <input type="button" name="Submit4" value="取消所有选项"  class="css_Button" onclick="javascript:UnSelectCheck(1,185)" >
                        </TD>
                        
                                                <input type="button" name="Button" value="冻结该员工"  class="css_Button"  onclick="javascript:operate_Del('7','A付桂艳－CWB')" >
                          <input type="button" name="Button" value="返回" class="css_Button" onClick="javascript: Frm_PermitGoto();">
                        
                      </TR>
                       -->
                      
                      <TR > 
                        <TD  bgColor=#ffffff colspan="2" align="center"> 
                          <input type="button" name="Button2" value="修改权限 " class="css_Button" onClick="javascript:authManager('<%=roleUid%>',2);">
                          </TD>
                      </TR>
                      
                      </TBODY> 
                    </TABLE>
                    <br>
                  </form>
            
                </td>
              </tr>
           </table>   
          </td>
        </tr>
        
      </table>
    </td>
  </tr>
</table>
</body>
</html>
