/**
 * 
 */
package com.exedosoft.plat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class ExcuteSqlFile {

	/**
	 * 
	 */
	public ExcuteSqlFile() {
		// TODO Auto-generated constructor stub
	}

	// 从文件读放内容到按分号放到sqlFileList
	public static List<String> readSqlFile(String fileName) {

		List<String> sqlList = new ArrayList<String>();

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));   
			StringBuffer sb = new StringBuffer();
			while (true) {
				String aLine = in.readLine();
				if(aLine==null){
					break;
				}
				sb.append(aLine);
			}
			in.close();
			String theSqls = sb.substring(1);
			String sqls[] = theSqls.split(";");
			for (String sql : sqls) {
				sqlList.add(sql);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}

		return sqlList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		String fileName = null;
		
		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf"));
//			fileName = prefix + "/exedo/initsql/mysql.sql";
			fileName = prefix + "/exedo/initsql/sqlserver2000.sql";
			
			System.out.println("FileName" + fileName);
		System.out.println(ExcuteSqlFile.readSqlFile(fileName));
		

		//
		// 然后一句句的执行
		// for (String sql : sqlList) {
		// pstmt = con.prepareStatement(sql,
		// ResultSet.TYPE_SCROLL_INSENSITIVE,
		// ResultSet.CONCUR_READ_ONLY);
		// pstmt.execute();
		//
		// }

	}

}
