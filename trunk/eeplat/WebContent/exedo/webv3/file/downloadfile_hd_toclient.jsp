<%@ page pageEncoding="UTF-8" contentType="application/octet-stream"%><%@ page import="com.exedosoft.plat.ui.DODownLoadFile,com.exedosoft.plat.SessionContext"%><%@ page import="com.exedosoft.plat.util.StringUtil"%><%
SessionContext context = (SessionContext) session
.getAttribute("userInfo");
if (null == session.getAttribute("userInfo")
	|| context.getUser() == null) {
	response.sendRedirect(request.getContextPath()
		+ "/exedo/webv3/logoff.jsp");
}
String filePath =  request.getParameter("filePath");     String fileName = request.getParameter("fileName");DODownLoadFile.outStreamFromHDEscape(filePath,fileName,response);%>
