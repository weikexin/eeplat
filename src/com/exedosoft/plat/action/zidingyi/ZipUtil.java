package com.exedosoft.plat.action.zidingyi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.exedosoft.plat.util.DOGlobals;

public class ZipUtil {
	private ZipOutputStream zipOut;
	private byte[] buf;
	private int readedBytes;

	public ZipUtil() {
		this(1024);
	}

	public ZipUtil(int bufSize) {
		this.buf = new byte[bufSize];
	}

	public String doZip(String zipDirectory, String dirName, String zipName,List<String> filesName)
			throws IOException {
		if (zipDirectory == null)
			return null;
		File zipDir;
		zipDir = new File(zipDirectory + dirName);
		if(!zipDir.exists()) {
			zipDir.mkdir();
		}
		String zipPath = DOGlobals.UPLOAD_TEMP + zipName;
		this.zipOut = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(zipPath)));
		//

		handelDir(zipDir, this.zipOut, dirName,filesName);
		this.zipOut.close();

		System.out.println("生成" + zipPath + "成功");
		return zipPath;
	}

	private void handelDir(File zipDir, ZipOutputStream out, String dirName,List<String> filesName) {
		// TODO Auto-generated method stub
		FileInputStream filein;
		File[] files;
		files = zipDir.listFiles();
		try {
			if (files.length == 0) {
				this.zipOut.putNextEntry(new ZipEntry(zipDir.getName() + "/"));
				this.zipOut.closeEntry();
			} else {
				for (File fileName : files) {
					if (fileName.isDirectory()) {
						handelDir(fileName, this.zipOut, dirName,filesName);
					} else {
						String filen = fileName.getName();
						if(!filesName.contains(filen)) {
							continue;
						}
						filein = new FileInputStream(fileName);
						String absPath = fileName.getParentFile()
								.getAbsolutePath();
						String dirPath = absPath.substring(absPath
								.indexOf(dirName));
						this.zipOut.putNextEntry(new ZipEntry(dirPath + "/"
								+ fileName.getName()));
						while ((this.readedBytes = filein.read(this.buf)) > 0) {
							this.zipOut.write(this.buf, 0, this.readedBytes);
						}
						this.zipOut.closeEntry();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ZipUtil files = new ZipUtil();
//		try {
//			files.doZip("D:\\workmsfl\\yiyi_dev\\WebContent\\upload\\",
//					"MFSL-FK-2011-0001",
//					"MFSL-FK-2011-000111111111111111111111.zip",null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
