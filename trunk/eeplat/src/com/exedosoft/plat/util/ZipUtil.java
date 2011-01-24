package com.exedosoft.plat.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DODownLoadFile;
import com.exedosoft.plat.util.id.UUIDHex;

/**
 * 如果要用static 关键字一定要考虑好线同步问题
 * 
 * @author anolesoft
 * 
 */
public class ZipUtil {

	private static Log log = LogFactory.getLog(ZipUtil.class);

	// public synchronized static Map<String, InputStream> zipFiles(ZipFile
	// aZipFile)
	// throws IOException {
	//
	// Map<String, InputStream> zipIsMap = new HashMap<String, InputStream>();
	//
	// for (Enumeration enu = aZipFile.getEntries(); enu.hasMoreElements();) {
	// ZipEntry ze = (ZipEntry) enu.nextElement();
	// zipIsMap.put(ze.getName(), aZipFile.getInputStream(ze));
	// }
	// return zipIsMap;
	// }

	/**
	 * 是不是同时读取一个文件的时候出错呢 方法内的变量应该都是线程安全的 每个线程的执行都有自己独立的堆栈
	 */
	// public synchronized static InputStream getIsFromzipFile(ZipFile aZipFile,
	// String fileName) throws IOException {
	//
	// for (Enumeration enu = aZipFile.getEntries(); enu.hasMoreElements();) {
	// ZipEntry ze = (ZipEntry) enu.nextElement();
	// if (fileName.equalsIgnoreCase(ze.getName())) {
	// return aZipFile.getInputStream(ze);
	// }
	// }
	//		
	// log.error("未在ZIP压缩包中找到对应的的文件：：" + fileName);
	// return null;
	// }

	public static List unzip(ZipFile aZipFile) {

		String randomStr = UUIDHex.getInstance().generate();

		List unZipFiles = new ArrayList();

		try {
			for (Enumeration enu = aZipFile.getEntries(); enu.hasMoreElements();) {

				ZipEntry ze = (ZipEntry) enu.nextElement();
				InputStream is = aZipFile.getInputStream(ze);

				StringBuilder sb = new StringBuilder(
						DOGlobals.getInstance().UPLOAD_TEMP).append(randomStr)
						.append(File.separator);
				File dirFile = new File(sb.toString());
				dirFile.mkdir();
				sb.append(ze.getName());
				File aFile = new File(sb.toString());
				aFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(aFile);

				byte[] by = new byte[1024];
				int c;
				while ((c = is.read(by)) != -1) {
					fos.write(by, 0, c);
				}
				unZipFiles.add(sb.toString());
				fos.close();
				is.close();
			}
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unZipFiles;
	}

	/**
	 * 写压缩文件
	 */
	public static String writeZip(String paneModelUid, DOBO bo,
			DOService aService, String allSelects) throws IOException {

		String deptCode = "a000000";
		BOInstance aUser = DOGlobals.getInstance().getSessoinContext()
				.getUser();
		if (aUser != null) {
			deptCode = aUser.getValue("deptcode");
		}
		StringBuilder zipFilePath = new StringBuilder(DOGlobals.WORK_DIR)
				.append(File.separator).append(deptCode);
		File aWkDir = new File(zipFilePath.toString());
		if (!aWkDir.exists()) {
			aWkDir.mkdir();
		}

		zipFilePath.append(File.separator).append("batch.zip");

		File aFile = new File(zipFilePath.toString());
		aFile.createNewFile();
		OutputStream os = new FileOutputStream(aFile);
		ZipOutputStream zos = new ZipOutputStream(os);
		String[] arraySelect = allSelects.split(",");
		for (int i = 0; i < arraySelect.length; i++) {
			String aSelect = arraySelect[i];
			if (aSelect == null || aSelect.trim().equals("")) {
				continue;
			}
			BOInstance aInstance = bo.refreshContext(aSelect);
			if (aService != null) {
				try {
					aService.invokeAll();
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// String aFileName = aInstance.getValue("APP_NAME");
			if (aInstance != null) {
				String id_applyid = aInstance.getValue("id_applyid");
				ZipEntry ze = new ZipEntry(id_applyid + ".xml");
				zos.putNextEntry(ze);
				zos.write(DODownLoadFile.outHtmlCode(paneModelUid).getBytes(
						"utf-8"));
				ze.clone();
			}
		}
		zos.close();
		return zipFilePath.toString();

	}

	public static void testProduce() {

		DODataSource ds = DODataSource.getDataSourceByL10n("计量院系统数据库");
		System.out.println(ds);
		Connection con = null;
		CallableStatement pstmt = null;
		try {

			con = ds.getConnection();
			System.out.println(con.getCatalog());

			// "{call SP_GetDeptRegInfo(?)}";
			pstmt = con.prepareCall("SubmitChildProject(?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			pstmt.setString(1, "1");
			pstmt.registerOutParameter(2, Types.VARCHAR);

			pstmt.execute();
			String outp = pstmt.getString(2);
			System.out.println("outpoutpoutpoutpoutpoutpoutpoutpoutp:::::"
					+ outp);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				ds.ifCloseConnection(con);
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		
		
		File aFile = new File(DOGlobals.UPLOAD_TEMP +  StringUtil.getCurrentDayStr());
		aFile.mkdir();

		// File aFile = new File("c:\\2008-1.ZIP");//06657563.xml
		// aFile.createNewFile();
		// OutputStream os = new FileOutputStream(aFile);
		// ZipOutputStream zos = new ZipOutputStream(os);
		// ZipEntry ze = new ZipEntry("内蒙古自治区东胜煤田塔然_07010257.xml");
		//
		// zos.putNextEntry(ze);
		// zos.write("内蒙古自治区东胜煤田塔然".getBytes());
		//
		// ze.clone();
		//
		// zos.close();
//
//		ZipFile aZipFile = new ZipFile("F:\\2008-1.ZIP");
//
//		System.out.println(aZipFile.getEntry("07657038.xml"));
		// System.out.println(aZipFile.getEntry("06657563.xml").getName());

		// 内蒙古自治区东胜煤田塔然_07010257.xml

		// DOBO aBO = DOBO.getDOBOByName("t.project.new");
		// BOInstance aInstance =
		// aBO.getInstance("40288a01151ced1901151cfd70c3000a");
		// System.out.println(aInstance);
		// System.out.println(aInstance.getDependInstance());
		// DOService aService = DOService.getService("pro_new_produce");
		//
		// List aList = aService.invokeSelect("1");
		// System.out.println(aList);
		// testProduce();

		// String aFile = DOGlobals.UPLOAD_TEMP + StringUtil.getCurrentDayStr();
		// System.out.println(aFile);

		//		
		//
		// ZipUtil.zipFiles("c:\\portal.zip");

	}
}
