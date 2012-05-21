<%@page import="java.sql.Struct"%><%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.Escape"%>
<%@ page import="com.exedosoft.plat.util.StringUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.net.URLEncoder"%>
<%
	System.out.println("sessionid::" + session.getId());
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	DiskFileUpload fu = new DiskFileUpload();
	fu.setHeaderEncoding("UTF-8");
	// 设置允许用户上传文件大小,单位:字节
	fu.setSizeMax(1000 * 1024 * 1024);
	// 设置最多只允许在内存中存储的数据,单位:字节
	fu.setSizeThreshold(1024 * 100);
	// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
	///如果没有上传路径则创建
	File file = new File(DOGlobals.UPLOAD_TEMP);
	file.mkdirs();
	fu.setRepositoryPath(DOGlobals.UPLOAD_TEMP);
	//开始读取上传信息

	String colName = "";// requestM.getParameter("colName");
	String blobColName = "";// requestM.getParameter("blobColName");
	String fileName = "";// requestM.getFilesystemName("fileupload");
	try {
		List fileItems = fu.parseRequest(request);
		// 依次处理每个上传的文件
		Iterator iter = fileItems.iterator();

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			if (!item.isFormField()) {
				String name = item.getName();
				if (name.indexOf("\\") != -1) {
					name = name.substring(name.lastIndexOf("\\") + 1);
 
				}
				fileName =  name;
				item.write(new File(DOGlobals.UPLOAD_TEMP + fileName));
				System.out.println(fileName+"item:::::::"+item.getFieldName());
			}
			
			if (item.getFieldName().equals("colName")) {
				colName = item.getString();
			}
			if (item.getFieldName().equals("blobColName")) {
				blobColName = item.getString();
			}

		}
	} catch (Exception e) {
		e.printStackTrace();

	}
	
	System.out.println("Upload Action Comm FileName::" + fileName);
	
%>