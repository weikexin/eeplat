<%@ page contentType="text/html;charset=gb2312" %><%@ page language="java" import="java.sql.*" %><%@ page language="java" import="com.exedosoft.plat.bo.DODataSource" %><%	String docid = request.getParameter("docid");
	Connection conn = null; 
	Statement stmt = null;
	ResultSet rs = null; 

	//与DBMS建立连接 
	try
	{ 
		conn = DODataSource.getDefaultCon(); 
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from MyUploadTable where id="+docid); 
	} 
	catch(SQLException ex)
	{ 
		System.err.println("aq.executeQuery: " + ex.getMessage()); 
	}
	while(rs.next())
	{
	  try
	  {
	  	String filename = new String( rs.getBytes("filename"),"gb2312");
	  	//System.out.println(filename);
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+filename);
		java.io.InputStream in = rs.getBinaryStream("filedata");
		//response.setContentLength(in.available());
		java.io.OutputStream outStream = response.getOutputStream();
		byte[] buf = new byte[1024];
		int bytes = 0;
		while((bytes = in.read(buf)) != -1)
			outStream.write(buf, 0, bytes);
		in.close();	
		outStream.close();	
	  }
	  catch(Throwable e)
	  {
	  	System.out.println(e.toString());
		throw new ServletException(e.toString());
	  }
	  break;
	}
	rs.close();
	stmt.close();	
	conn.close();
%>