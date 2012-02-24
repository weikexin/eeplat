package com.exedosoft.plat.ui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.util.Escape;

public class DODownLoadFile {

	private static Log log = LogFactory.getLog(DODownLoadFile.class);

	/**
	 * //关于文件下载时采用文件流输出的方式处理： //加上response.reset()，并且所有的％>后面不要换行，包括最后一个；
	 * //因为Application Server在处理编译jsp时对于％>和<％之间的内容一般是原样输出，而且默认是PrintWriter，
	 * //而你却要进行流输出：ServletOutputStream，这样做相当于试图在Servlet中使用两种输出机制，
	 * //就会发生：getOutputStream() has already been called for this response的错误
	 * //详细请见《More Java Pitfill》一书的第二部分 Web层Item 33：试图在Servlet中使用两种输出机制 270
	 * //而且如果有换行，对于文本文件没有什么问题，但是对于其它格式，比如AutoCAD、Word、Excel等文件
	 * //下载下来的文件中就会多出一些换行符0x0d和0x0a，这样可能导致某些格式的文件无法打开，有些也可以正常打开。
	 * 
	 * @param bouid
	 * @param thisuuid
	 * @param name
	 * @param rename
	 * @param response
	 */
	public static void outStream(String bouid, String thisuuid, String name,
			String rename, String downLoadSizeCol, HttpServletResponse response) {

		DOBO bo = DOBO.getDOBOByID(bouid);
		BOInstance instance = bo.getInstance(thisuuid);
		String renameValue = "attachment";
		if (rename != null) {
			renameValue = instance.getValue(rename);
			try {
				renameValue = URLEncoder.encode(renameValue, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		response.addHeader("Content-Disposition", "attachment;filename="
				+ renameValue);
		StringBuffer sql = new StringBuffer("select ");
		sql.append(name);
		sql.append(" from ").append(bo.getSqlStr()).append(" where ").append(
				bo.getKeyCol()).append(" = ?");
		log.info("The get blob sql is " + sql);
		Connection con = null;
		OutputStream output = null;
		InputStream is = null;
		try {
			con = bo.getDataBase().getContextConnection();
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, thisuuid);
			ResultSet rset = pstmt.executeQuery();
			Blob blob = null;
			if (rset.next()) {
				blob = (Blob) rset.getBlob(1);
			}
			output = response.getOutputStream();
			is = blob.getBinaryStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = is.read(b)) > 0) {
				output.write(b, 0, i);
			}
			output.flush();
		} catch (Exception ex1) {
			log.error(ex1);
		} finally {
			try {
				bo.getDataBase().ifCloseConnection(con);
				if (is != null) {
					is.close();
					is = null;
				}
				if (output != null) {
					output.close();
					output = null;
				}
			} catch (Exception ex1) {
				log.error(ex1);
			}
		}

		if (downLoadSizeCol != null) {
			downLoadSizeCol(bo, downLoadSizeCol, thisuuid);
		}
	}

	public static void outStreamFromHDEscape(String filePath, String fileName,
			HttpServletResponse response) {

		log.info("Befor FilePath::::::::" + filePath);
		log.info("Befor fileName::::::::" + fileName);

		filePath = Escape.unescape(filePath);
		fileName = Escape.unescape(fileName);
		filePath = filePath.replace("20%", " ");
		
		log.info("After FilePath::::::::" + filePath);
		log.info("After fileName::::::::" + fileName);
		outStreamFromHD(filePath, fileName, response);
	}

	/**
	 * 从硬盘输出文件
	 * 
	 */

	public static void outStreamFromHD(String filePath, String fileName,
			HttpServletResponse response) {

		try {
			response.setCharacterEncoding("utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e.fillInStackTrace());
		}

		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileName);

		// OutputStream output = null;
		// InputStream is = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// output = response.getOutputStream();

			bis = new java.io.BufferedInputStream(new java.io.FileInputStream(
					filePath));
			bos = new java.io.BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[2048 * 100];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

			// is = new FileInputStream(filePath);
			// byte[] b = new byte[100 * 1024];
			// int i = 0;
			// while ((i = is.read(b)) > 0) {
			// output.write(b, 0, i);
			// }
			// output.flush();
		} catch (Exception ex1) {
			log.error(ex1);
		} finally {
			try {

				if (bis != null) {
					bis.close();
					bis = null;
				}
				if (bos != null) {
					bos.close();
					bos = null;
				}
			} catch (Exception ex1) {
				log.error(ex1);
			}
		}

	}

	public static String outHtmlCode(String paneModelUid) {

		DOPaneModel pm = DOPaneModel.getPaneModelByID(paneModelUid);
		if (pm.getLinkType() != null
				&& pm.getLinkType().intValue() == DOPaneModel.LINKTYPE_GRIDMODEL) {
			if (pm.getLinkUID() != null && !"".equals(pm.getLinkUID().trim())) {
				DOGridModel gm = DOGridModel.getGridModelByID(pm.getLinkUID());
				return gm.getHtmlCode();
			}
		}

		return "";
	}

	/**
	 * @param pstmt
	 * @param i
	 * @throws SQLException
	 */
	private static void downLoadSizeCol(DOBO bo, String downLoadSizeCol,
			String thisuuid) {

		StringBuffer sql = new StringBuffer("update ");
		sql.append(bo.getSqlStr());
		sql.append(" set ").append(downLoadSizeCol).append("=").append(
				downLoadSizeCol).append("+1 where ").append(bo.getKeyCol())
				.append("=?");

		log.info("The increase key sql is " + sql);
		log.info("The uuid is  " + thisuuid);
		Connection con = null;
		try {
			con = bo.getDataBase().getContextConnection();
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, thisuuid);
			pstmt.executeUpdate();
		} catch (Exception ex1) {
			log.error(ex1);
		} finally {
			try {
				bo.getDataBase().ifCloseConnection(con);
			} catch (Exception ex1) {
				log.error(ex1);
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String fileName = "aaaabb20%cc";
	   	fileName = fileName.replace("20%", " ");
	   	System.out.println(fileName);

	}

	// public

}
