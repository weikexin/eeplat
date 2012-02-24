<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<% 

     DOService aService = DOService.getService("dm.document.search.test");
     List list = aService.invokeSelect();
     StringBuffer buffer = new StringBuffer();
     for(Iterator it = list.iterator();it.hasNext();){
    	 BOInstance bi = (BOInstance)it.next();
    	 if(bi.getValue("attachmentName")==null){
    		 continue;
    	 }
    	 buffer.append(bi.getUid())
    	 .append(",")
    	 .append(bi.getValue("attachmentName"))
    	 .append(";");
     }
     request.setAttribute("uuids",buffer.toString());
     pageContext.getServletContext().getRequestDispatcher("/nebula/docm/FileDownLoad2.jsp")
	.forward(request, response);

%>
