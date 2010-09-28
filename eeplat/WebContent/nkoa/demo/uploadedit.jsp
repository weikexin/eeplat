<%@ page contentType="text/html;charset=gb2312" %>
<%@ page language="java" import="java.io.*,java.sql.*,com.anolesoft.epiboly.smartdot.dc.*"%>
<%@ page language="java" import="com.exedosoft.plat.bo.DODataSource" %>
<jsp:useBean id="mySmartUpload" scope="page" class="com.anolesoft.epiboly.smartdot.dc.SmartUpload" />
<%!
//这儿需要修改成您的数据库连接信息



public Connection conn = null; 

public String docid = "";
public String filename = "";
public int filesize = 0;
%>
<%

System.out.println("fdsfdsfdsfdsfdsf");
	// Initialization
 	mySmartUpload.initialize(pageContext);
	// Upload
	mySmartUpload.upload();
	
	//取消注释以保存文件
	//mySmartUpload.save("/jspdemo/upload"); 	
	//与DBMS建立连接 
	try
	{ 
		conn = DODataSource.getDefaultCon(); 			
	} 
	catch(Exception ex)
	{ 
		System.err.println("aq.executeQuery: " + ex.getMessage()); 
	}  
	
	SmartFile myFile = null;
	
	docid = mySmartUpload.getRequest().getParameter("docid");
	System.out.println("docid="+docid);	
	
	String afileName = mySmartUpload.getRequest().getParameter("EDITFILE");
	System.out.println("fileName==" + afileName);
	System.out.println("fileName==" + afileName);
	System.out.println("fileName==" + afileName);
	System.out.println("fileName==" + afileName);
	System.out.println("fileName==" + afileName);
	System.out.println("fileName==" + afileName);
	System.out.println("fileName==" + afileName);
	
	for (int i=0;i<mySmartUpload.getFiles().getCount();i++)
	{
		myFile = mySmartUpload.getFiles().getFile(i);
		//debug only
		System.out.println("File=" + myFile.getFileName());  		
  		//处理数据及文件
  		if (!myFile.isMissing())
	 	{	 		
	 		filename = myFile.getFileName();
	 		filesize = myFile.getSize();	 		 		
	 		if(myFile.getFieldName().equalsIgnoreCase("EDITFILE") )	 //正文文件
	 		{
	 			System.out.println("处理正文文件"); 
				System.out.println("filename="+filename);
				System.out.println("filesize="+filesize);
				System.out.println("myFile.getFieldName()="+myFile.getFieldName());			
		 		myFile.saveAs(filename,mySmartUpload.SAVE_PHYSICAL);	 		
				java.io.File tfile = new java.io.File(filename);
				java.io.InputStream inStream=new java.io.FileInputStream(tfile);				
		 		try
				{
				    String strSql = "";
		 			if ( (docid == null)||(docid.length() == 0) ) // new doc
		 			{		 				 					 						
						strSql="insert into MyUploadTable(filename,filesize,filedata) values(?,?,?)";
						                   	                	
					}									
		 			else // edit doc
		 			{
		 			    //iCurDocID = Integer.parseInt(docid);
		 			    strSql="update MyUploadTable set filename=?,filesize=?,filedata=? where id="+docid;
		 				
		 			}
		 	
		 			PreparedStatement ps = conn.prepareStatement(strSql);
					ps.setString(1, filename);
					ps.setInt(2, filesize);                		
            		ps.setBinaryStream(3,inStream,inStream.available());
                    ps.executeUpdate();
                    ps.close();  
		 			out.println("保存成功！<br>");
		 			out.println("在线编辑的文件: " + filename + "<br>");
					out.println("大小: " + filesize + " bytes<br>");
		 		}
		 		catch(Exception e)
				{
					out.println("发生错误: " + e.toString());				
				}
				inStream.close();
				//tfile.delete();			
			}
			else //附件文件
			{
				try
				{
					System.out.println("处理附件文件");
					System.out.println("filename="+filename);
					System.out.println("filesize="+filesize);
					System.out.println("myFile.getFieldName()="+myFile.getFieldName());
					out.println("其他的文件: " + filename + "<br>");
					out.println("大小: " + filesize + " bytes<br>");
		 		}
		 		catch(Exception e)
				{
					out.println("发生错误: " + e.toString());				
				}			
			}					
	 	}  // end if (!myFile.isMissing())
	}// end for 
	conn.close();
%>

		
