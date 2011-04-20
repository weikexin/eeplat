/**
 * 
 */
package com.zephyr;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author t
 *
 */
public class ReadZip {

	static final int BUFFER = 2048;
	
	/**
	 * 
	 */
	public ReadZip() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadZip rz = new ReadZip();

		//rz.readGZ();
		
		rz.readZip("C:\\test.zip", "C:\\zip\\");
	}

	
	/**
	 * 
	 * @param zipFile 	input zip file
	 * @param path 		extract path
	 */
	private void readZip(String zipFile, String path) {

		try {

			BufferedOutputStream dest = null;

			BufferedInputStream is = null;

			ZipEntry entry;

			ZipFile zipfile = new ZipFile(zipFile);

			Enumeration e = zipfile.entries();

			while (e.hasMoreElements()) {

				entry = (ZipEntry) e.nextElement();

				System.out.println("Extracting: " + entry);

				is = new BufferedInputStream(zipfile.getInputStream(entry));

				int count;

				byte data[] = new byte[BUFFER];
				
				// create folder if path not exist
				if(!new File(path).exists()){
					new File(path).mkdir();
				}

				FileOutputStream fos = new FileOutputStream(path+entry.getName());

				dest = new BufferedOutputStream(fos, BUFFER);

				while ((count = is.read(data, 0, BUFFER))!= -1) {

					dest.write(data, 0, count);
				}

				dest.flush();
				dest.close();
				is.close();
			}

		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

	private void readGZ() {
		try {
			int number;
			
			//建立GZIP压缩文件输入流 
			FileInputStream fin = new FileInputStream("C:\\test.gz");
			
			//建立GZIP解压工作流 
			GZIPInputStream gzin = new GZIPInputStream(fin);
			
			//建立解压文件输出流 
			FileOutputStream fout = new FileOutputStream("C:\\gz");
			
			//设定读入缓冲区尺寸
			byte[] buf = new byte[1024];
			
			while ((number = gzin.read(buf, 0, buf.length)) != -1)
				fout.write(buf, 0, number);
			
			//close source
			gzin.close();
			fout.close();
			fin.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}

}