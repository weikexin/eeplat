package com.exedosoft.plat.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.ui.DODownLoadFile;
import com.exedosoft.plat.util.DOGlobals;

import freemarker.cache.TemplateLoader;

public class DBTemplateLoader implements TemplateLoader {

	public static String filePrefix = "";
	
	private static Log log = LogFactory.getLog(DBTemplateLoader.class);


//	static {
//		// URL url = DOGlobals.class.getResource("/globals.xml");
//		// String fullFilePath = url.getPath();
//		if (DOGlobals.getValue("extend_file_prix") != null) {
//			filePrefix = DOGlobals.getValue("extend_file_prefix");
//		}
//
//		// fullFilePath.substring(0, fullFilePath.toLowerCase()
//		// .indexOf("web-inf"));
//	}

	public void closeTemplateSource(Object templateSource) throws IOException {
		// System.out.println("closeTemplateSource");
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	public Object findTemplateSource(String para) throws IOException {

		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		DODataSource dss = DODataSource.parseGlobals();
		try {
			if (conn == null || conn.isClosed()) {
				conn = dss.getConnection();
			}
			s = conn
					.prepareStatement("select * from DO_UI_Controller where objuid= ?");
			s.setString(1, para);
			rs = s.executeQuery();
			if (rs.next()) {
				String template = rs.getString("template");
//				log.info("template:::" + template);
//				if (template != null && template.startsWith("/*linkfile*/")) {
//					File aFile = new File(filePrefix + template.substring(13));
//
//					BufferedReader in = new BufferedReader(
//							new InputStreamReader(new FileInputStream(aFile),
//									"utf-8"));
//					StringBuffer sb = new StringBuffer();
//					while (true) {
//						String aLine = in.readLine();
//						if (aLine == null) {
//							break;
//						}
//						sb.append(aLine).append("\n");// \n\r
//					}
//					in.close();
//					return sb.toString();
//				}
				return template;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				s.close();
				conn.close();
			} catch (Exception e) {
			}
		}

	}

	public long getLastModified(Object templateSource) {

		return -1l;
	}

	public Reader getReader(Object templateSource, String encodeType)
			throws IOException {

		StringReader read = new StringReader(templateSource.toString());
		return read;
		// Clob c = (Clob) templateSource;
		// try {
		// return c.getCharacterStream();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// throw new RuntimeException(e);
		// }

	}

}
