<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>紫枫工资信息查询</title>

<link rel="stylesheet" href="/yiyi/exedo/webv3/css/main/main_lan.css"
	type="text/css">

<script language="javascript">
<%
	String uid = (String) session.getAttribute("uid");
	String sname = (String)request.getAttribute("name");
	String syear = (String)request.getAttribute("year");
	String smonth = (String)request.getAttribute("month");
	String sl = (String)request.getAttribute("sl");
	String sh = (String)request.getAttribute("sh");
	if(uid != null)
		uid = uid.trim();
	if(sname != null) {
		sname = sname.trim();
		if(sname.length() <= 0)
			sname = null;
	}
		
	if(syear != null) {
		syear = syear.trim();
		if(syear.length() <= 0)
			syear = null;
	}
	if(smonth != null){
		smonth = smonth.trim();
		if(smonth.length() <= 0)
			smonth = null;
	}
	if(sl != null){
		sl = sl.trim();
		if(sl.length() <= 0)
			sl = null;
	}
	if(sh != null){
		sh = sh.trim();
		if(sh.length() <= 0)
			sh = null;
	}
	
	String ty = (syear != null? syear + "年":null);
	String tm = (smonth != null? smonth + "月":null);
	String tl = (sl != null? "大于等于" + sl:null);
	String th = (sh != null? "小于等于" + sh:null);
	String text = null;
	if(text == null)
		text = ty;
	if(text == null)
		text = tm;
	else if(tm != null)
		text = text + tm;
	
	if(text == null)
		text = tl;
	else if(tl != null)
		text = text + ", 工资" + tl;
	
	if(text == null)
		text = th;
	else if(th != null && tl == null)
		text = text + ", 工资" + th;
	else if(th != null && tl != null)
		text = text + "且" + th;
	
	if(text == null) 
		text = "查看所有的工资信息";
	else{
		text = "查看" + text + "的工资信息";	
	}
%>
</script>

<style type="text/css">
body {
	overflow: auto;
}
</style>
</head>

<body>

<br>

<br>

<br>

<form
	action="<%=request.getContextPath()%>/allsm?uid=<%=session.getAttribute("uid") %>"
	method="post">
<div id="gztable1">
<table class="tablesorter" border="0" cellpadding="0" cellspacing="1"
	width="100%" id="gzcxtable">
	<thead>
		<tr>
			<td class="title" colspan="2"><img
				src="/yiyi/exedo/webv3//images/MyRightArrow.jpg"><b>
			查看紫枫员工【<%=sname %>】工资信息 </b></td>
		</tr>
	</thead>

	<tbody>
		<tr>
			<td colspan="2" rowspan="2"
				style="text-align: center; font-size: 15pt; color: lightsteelblue"><%=text %></td>
		</tr>
		<tr></tr>
		<tr>
			<td style="text-align: right" nowrap="nowrap" width="45%">年&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td style="text-align: left" nowrap="nowrap" width="55%"><select
				name="year">
				<option></option>
				<%
					java.util.Calendar c = java.util.Calendar.getInstance();
					int year = c.get(java.util.Calendar.YEAR);
					for (int i = 2000; i <= year; i++) {
						String s = " " + i + " 年  ";
				%>
				<option value="<%=i%>"><%=s%></option>
				<%
					}
				%>

			</select></td>
		</tr>
		<tr>
			<td style="text-align: right" nowrap="nowrap" width="45%">月&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td style="text-align: left" nowrap="nowrap" width="55%"><select
				name="month">
				<option></option>
				<%
					
					for (int i = 1; i <= 12; i++) {
						String s = " " + i + " 月  ";
				%>
				<option value="<%=i%>"><%=s%></option>
				<%
					}
				%>

			</select></td>

		</tr>
		<tr>
			<td style="text-align: right" nowrap="nowrap" width="45%">月工资&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td style="text-align: left" nowrap="nowrap" width="55%"><input
				style="border: 1px solid rgb(179, 179, 179);"
				onmouseover="this.style.borderColor='#99E300'"
				onmouseout="this.style.borderColor='#A1BCA3'" name="lower"
				id="lower" title="最低" size="14" type="text">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;
			<input style="border: 1px solid rgb(179, 179, 179);"
				onmouseover="this.style.borderColor='#99E300'"
				onmouseout="this.style.borderColor='#A1BCA3'" name="high" id="high"
				title="最高" size="14" type="text"></td>
		</tr>
		<tr class="buttonMore">
			<td style="text-align: center;" colspan="2">
			<button type="submit" id="cxgzid" class="cxgzbtn"">查询</button>
			&nbsp;</td>
		</tr>
	</tbody>
</table>
</div>
</form>
<br>
<div id="gztable2">
<table class="tablesorter" border="0" cellpadding="1" cellspacing="1"
	width="100%" id="gzrstable">
	<thead>
		<tr>
			<th style="display: none;" class="{sorter: false}"></th>
			<th id="month" align="center" nowrap="nowrap" width="77px">月份</th>
			<th id="name" align="center" nowrap="nowrap" width="55px">姓名</th>
			<th id="basesalary" align="center" nowrap="nowrap">月工资</th>
			<th id="buckshee" align="center" nowrap="nowrap">其他</th>
			<th id="rentdeduct" align="center" nowrap="nowrap">租房扣减</th>
			<th id="leavededuct" align="center" nowrap="nowrap">病(事)假<br />
			扣减</th>
			<th id="factsalary" align="center" nowrap="nowrap">应发额</th>
			<th id="payyanglaoinsure" align="center" nowrap="nowrap">代缴个人<br />
			养老保险</th>
			<th id="payshiyeinsure" align="center" nowrap="nowrap">代缴个人<br />
			失业保险</th>
			<th id="payyilaioinsure" align="center" nowrap="nowrap">代缴个人<br />
			医疗保险</th>
			<th id="payshebaofee" align="center" nowrap="nowrap">个人应缴<br />
			社保小计</th>
			<th id="payhousingsurplus" align="center" nowrap="nowrap">代缴个人<br />
			住房公积金</th>
			<th id="taxbefore" align="center" nowrap="nowrap">税前应发</th>

			<th id="taxget" align="center" nowrap="nowrap">应税所得<br />
			G=F-2000</th>
			<th id="taxlv" align="center" nowrap="nowrap">税率H</th>
			<th id="taxrm" align="center" nowrap="nowrap">速算扣除</th>

			<th id="tax" align="center" nowrap="nowrap">税</th>
			<th id="taxafter" align="center" nowrap="nowrap">税后实发</th>
			<th id="remark" align="center" nowrap="nowrap" width="120px">备注</th>
		</tr>
		<%
			Object obj = request.getSession().getAttribute("smlist");
			System.out.println(obj);
			List list = null;
			if (obj != null)
				list = (List) obj;
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
		%>
		<tr class="" align="center">
			<td align="center" style="text-align: center" width="75px"><%=map.get("month")%></td>
			<td align="center" style="text-align: center" width="50px"><%=map.get("name")%></td>
			<td align="center"><%=map.get("basesalary")%></td>
			<td align="center"><%=map.get("buckshee")%></td>
			<td align="center"><%=map.get("rentdeduct")%></td>
			<td align="center"><%=map.get("leavededuct")%></td>
			<td align="center"><%=map.get("factsalary")%></td>
			<td align="center"><%=map.get("payyanglaoinsure")%></td>

			<td align="center"><%=map.get("payshiyeinsure")%></td>
			<td align="center"><%=map.get("payyilaioinsure")%></td>
			<td align="center"><%=map.get("payshebaofee")%></td>
			<td align="center"><%=map.get("payhousingsurplus")%></td>

			<td align="center"><%=map.get("taxbefore")%></td>
			<td align="center"><%=map.get("taxget")%></td>
			<td align="center"><%=map.get("taxlv")%></td>
			<td align="center"><%=map.get("taxrm")%></td>

			<td align="center"><%=map.get("tax")%></td>
			<td align="center"><%=map.get("taxafter")%></td>
			<td align="center"><%=map.get("remark")%></td>
		</tr>
		<%
			}
		%>
	</thead>
	<tbody>
		登录报销系统：
		<a href="<%=request.getContextPath()%>/yiyi/">登录</a>
	</tbody>

	<tfoot>
		<tr>
			<td colspan="19"></td>
		</tr>
	</tfoot>
</table>
</div>
</body>
</html>