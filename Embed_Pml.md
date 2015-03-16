# EEPlat支持在页面框架中嵌入使用 #

> 由于存在上线要求、风险考虑或项目遗留等原因必须使用现有的界面布局、组织结构以及索引菜单的情况，EEPlat支持嵌入到现有的界面框架，并且支持把现有的用户会话（Session）转换为EEPlat的用户会话。
> 解决方案是通过一个中介JSP转发，它会接受pml的参数（面板名称），如下列URL：：
```
   http://testme.com/eeplat/exedo/webv3/pml.jsp?pml=PM_do_log_Main
```
> 具体步骤：

  * 接受面板参数。
  * 判断EEPlat Session是否存在，存在的话直接转发面板界面。
  * 不存在，则获取当前框架的Session ,并把当前的Session转化为EEPlat Session ，然后转发面板界面。


如下图的jsp的内容，即使上述步骤的实现：

```
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ page language="java" import="com.exedosoft.plat.SessionContext"%>
<%@ page language="java" import="com.exedosoft.plat.login.LoginMain" %>
<%@ page language="java" import="com.exedosoft.plat.bo.BOInstance" %>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	
%>
<%

  String pml = request.getParameter("pml");
  if(pml!=null){
	  
	  
		SessionContext context = (SessionContext) session
				.getAttribute("userInfo");
		if (null == session.getAttribute("userInfo")
				|| context.getUser() == null) {
			
		///获取当前的用户session
                        String cust_id = usersession..getAttribute("cust_id");///第三方用户的ID
                        String cust_name = usersession..getAttribute("cust_name");////第三方用户的名称 

			BOInstance biUser = new BOInstance();
			biUser.putValue("objuid", cust_id);///第三方用户的ID
			biUser.putValue("name", cust_name);////第三方用户的名称 
		//	biUser.putValue("otherpara", "cust_name");////ID和名称是最重要的，也可以增加其他的属性
			LoginMain.makeLogin(biUser, request);
		}
	    
		application.getRequestDispatcher("/" +
				pml + ".pml?isApp=true").forward(
				request, response);
		return;
  }
%>

```