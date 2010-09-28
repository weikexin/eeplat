package com.exedosoft.plat.template;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.exedosoft.plat.bo.DODataSource;

import freemarker.cache.TemplateLoader;

public class DBTemplateLoader implements TemplateLoader {


	public void closeTemplateSource(Object templateSource) throws IOException {
//		System.out.println("closeTemplateSource");
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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
			s = conn.prepareStatement("select * from DO_UI_Controller where objuid= ?");
			s.setString(1, para);
			rs = s.executeQuery();
			if(rs.next()){
				return rs.getString("template");
			}else{
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
