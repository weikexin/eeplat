<%@ page pageEncoding="UTF-8"%><%@ page import="com.exedosoft.plat.util.ZipUtil"%><%@ page import="com.exedosoft.plat.util.StringUtil"%><%@ page import="com.exedosoft.plat.util.Escape"%><%@ page import="com.exedosoft.plat.ui.DODownLoadFile"%><%@ page import="com.exedosoft.plat.bo.BOInstance"%><%@ page import="com.exedosoft.plat.bo.DOBO"%><%@ page import="com.exedosoft.plat.bo.DOService"%><%@ page import="java.net.URLEncoder"%><%@ page import="java.net.URLDecoder"%><% response.setContentType("application/x-download;charset=UTF-8");
  response.setCharacterEncoding("UTF-8");
  String allSelects = request.getParameter("allSelects");
  String paneModelUid =  "297e84c913f3b6c60113f3da1c020012";
  String contextBOName = "gt.uploadfiles";
  String  contextServiceName = "gt.uploadfiles.updatesetlicenceid_sheweiyidu";
  DOBO bo = DOBO.getDOBOByName(contextBOName);
  DOService aService = DOService.getService(contextServiceName);
//8761	
  if (paneModelUid == null) {
		paneModelUid = (String) request.getAttribute("paneModelUid");
  }
  String filePath = ZipUtil.writeZip(paneModelUid,bo,aService,allSelects);
  
  	String aFileName ="批量配号_" + StringUtil.getCurrentDayStr() + ".zip";
  	aFileName = Escape.escape(aFileName);
  	
    filePath = Escape.escape(filePath);
   	response.sendRedirect("downloadfile_hd.jsp?fileName="+ aFileName +
		  "&filePath=" + filePath);
  
  %>

