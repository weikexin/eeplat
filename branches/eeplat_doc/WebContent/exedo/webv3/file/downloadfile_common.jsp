<%@ page pageEncoding="UTF-8"%><%@ page import="com.exedosoft.plat.util.DOGlobals"%><%@ page import="com.exedosoft.plat.util.Escape"%><%@ page import="com.exedosoft.plat.bo.BOInstance"%><%@ page import="com.exedosoft.plat.ui.DOFormModel"%><%@ page import="com.exedosoft.plat.bo.DOService"%><%@ page import="java.net.URLEncoder"%><% response.setContentType("application/x-download;charset=UTF-8");

  response.setCharacterEncoding("UTF-8");
  String formModelUid =  request.getParameter("formModelUid");
  String fileName =  request.getParameter("fileName");
  DOFormModel theModel = DOFormModel.getFormModelByID(formModelUid);
 
  DOService aService = theModel.getLinkService();
  if(aService!=null){
  	aService.invokeAll();
  }
  String aFileName = fileName ;
  
  if(aFileName==null && aService!=null){
	  aFileName = aService.getBo().getCorrInstance().getName() + ".xml";
  }
 // aFileName = URLEncoder.encode(aFileName, "UTF-8");
  response.addHeader("Content-Disposition", "attachment;filename="+aFileName);

  out.println(Escape.unescape(DOGlobals.getInstance().getRuleContext().getEchoValue()));%>

//downloadfile_common.jsp 文件中提到的方法 _jspService() 包含未经验证的数据，
//这些数据位于 HTTP 响应头文件的第 18 行。这会招致各种形式的攻击，包括
//：cache-poisoning、cross-site scripting、cross-user defacement、page hijacking、
//cookie manipulation 或 open redirect。
//