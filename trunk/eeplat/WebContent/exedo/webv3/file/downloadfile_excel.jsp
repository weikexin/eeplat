<%@ page pageEncoding="UTF-8"%><%@ page import="com.exedosoft.plat.ui.DODownLoadFile,com.exedosoft.plat.SessionContext"%><%response.setContentType("application/vnd.ms-excel; charset=gbk");  
SessionContext context = (SessionContext) session
.getAttribute("userInfo");
if (null == session.getAttribute("userInfo")
	|| context.getUser() == null) {
	response.sendRedirect(request.getContextPath()
		+ "/exedo/webv3/logoff.jsp");
}
String paneModelUid =  request.getParameter("paneModelUid");  response.addHeader("Content-Disposition", "attachment;filename=export.xls");    out.println(DODownLoadFile.outHtmlCode(paneModelUid));%>
