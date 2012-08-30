<%@ page pageEncoding="UTF-8" contentType="text/html;"%><%@ page import="com.exedosoft.plat.ui.DODownLoadFile,com.exedosoft.plat.SessionContext"%><%@ page import="com.exedosoft.plat.util.StringUtil"%><%response.setContentType("application/x-download;charset=UTF-8"); 
SessionContext context = (SessionContext) session
.getAttribute("userInfo");
if (null == session.getAttribute("userInfo")
	|| context.getUser() == null) {
	response.sendRedirect(request.getContextPath()
		+ "/exedo/webv3/logoff.jsp");
}
String filePath =  request.getParameter("filePath");     String fileName = request.getParameter("fileName");DODownLoadFile.outStreamFromHDEscape(filePath,fileName,response);%>
