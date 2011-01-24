package com.exedosoft.plat.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CheckAClassInLib {
	
	
	public static void zipFiles(String zipPath) throws IOException {

	
		ZipFile aZipFile = new ZipFile(zipPath);
		boolean isMozilla = false;
		for (Enumeration enu = aZipFile.entries(); enu.hasMoreElements();) {
			ZipEntry ze = (ZipEntry) enu.nextElement();
			//index/QQDownload/
			String zipName = ze.getName();
			if(zipName.contains("jmx")){
				//System.out.println(zipName);
				isMozilla = true;
				System.out.println(zipName);
				break;
			}
		}
		if(isMozilla){
			System.out.println(zipPath);
		}
		
	}
	
	public static void scanZipFile() throws IOException{
		
		ScanFile sf = new ScanFile();
		List list = sf.parse("D:\\java5", new String[]{".jar"});
		for(Iterator it = list.iterator();it.hasNext();){
			String aJar = (String)it.next();
			zipFiles(aJar);
			//System.out.println(aJar);
			
		}
		
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {


		try {
			//CheckRhinoJs.zipFiles("c:\\portal.zip");
			CheckAClassInLib.scanZipFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
