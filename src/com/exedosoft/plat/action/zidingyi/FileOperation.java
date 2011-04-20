package com.exedosoft.plat.action.zidingyi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Timer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DODownLoadFile;
import com.exedosoft.plat.util.Escape;

public class FileOperation {
	private static Log log = LogFactory.getLog(DODownLoadFile.class);
	/**
	 * @param delFile:0文件不存在;1,文件为目录;2,文件成功删除;3,文件删除失败
	 */
	public static int delFile(String filePath) throws Exception {
		
		int delFile = 0;
		File file = new File(filePath);
		if(file.exists() && file.isDirectory()) {
			delFile = 1;
		} else if(file.exists() && file.isFile()) {
			boolean isSuccess = file.delete();
			if(isSuccess)
				delFile = 2;
			else 
				delFile = 3;
		}
		
		return delFile;
	}
	
	/**
	 * @param isExists:文件是否存在
	 */
	public static boolean isExistsFile(String filePath) throws Exception {
		boolean isExists = false;
		File file = new File(filePath);
		if(file.exists())
			isExists = true;
		return isExists;
	}
	
	
	public static void outStreamFromHDEscape(String filePath, String fileName,
			HttpServletResponse response) {


		filePath = Escape.unescape(filePath);
		fileName = Escape.unescape(fileName);
		filePath = filePath.replace("20%", " ");
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
		
		//删除文件
		TaskDelFile delFile = new TaskDelFile(filePath);
		Timer timer  =  new Timer();
		long ltime = 5000;
		File zipFile = new File(filePath);
		if(zipFile.exists()) {
			if(zipFile.length() < 1024*1024*10) {
				ltime = 1000*60*60*3;
			} else if(zipFile.length() < 1024*1024*50) {
				ltime = 1000*60*60*12;
			} else {
				ltime = 1000*60*60*24;
			}
		}
		timer.schedule(delFile,ltime);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			boolean b = FileOperation.isExistsFile("F:\\workspI\\yiyi_new\\WebContent\\upload\\");
			
			int i = FileOperation.delFile("F:\\workspI\\yiyi_new\\WebContent\\upload\\");
//			System.out.println(i + " === " + b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
