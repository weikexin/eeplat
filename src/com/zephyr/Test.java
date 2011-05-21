package com.zephyr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import Erp.ScanSrvPrx;
import Erp.ScanSrvPrxHelper;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		ScanSrvI srvI = new ScanSrvI();
//		int n = srvI.isExist("CATALOG20110321140445.bmp@MSFL-FK-2011-0003");
//		System.out.println(n);
//		File file = new File("D:\\ProgramData\\Release\\scanDir\\CATALOG20110321140445.bmp");
//		byte[] data = null;
//		data = getBytesFromFile(file);
//		System.out.println(file.length());
//		System.out.println(Integer.MAX_VALUE);
//		int M = Integer.MAX_VALUE/(1024*1024);
//		System.out.println(M);
//		srvI.saveImageFile("CATALOG20110321140445.bmp","1", data, "MSFL-FK-2011-0003", null);
		Character[] c = new Character[12];
		c[0] = '1';
		int status = 0;
		Ice.Communicator ic = null;
		try {
//			args = new String[1];
//			args[0] = "--Ice.MessageSizeMax=5120";
//			System.out.println(args[0]);
			ic = Ice.Util.initialize(args);
			Ice.ObjectPrx base = ic.stringToProxy("ScanSrv:default -p 11000 -h 127.0.0.1");
			ScanSrvPrx scansrv = ScanSrvPrxHelper.checkedCast(base);
			if (scansrv == null)
				throw new Error("Invalid proxy");
			// scansrv.printString("Hello World!");
			
			System.out.println("begin...");
			byte[] data = new byte[1024];
			
			// make image into stream for test
//			File f = new File("D:\\ProgramData\\Release\\scanDir\\CATALOG20110321140445.bmp");
			File f = new File("D:\\CATALOG20110321140445.bmp");
			
			byte[] b = new byte[(int) f.length()];
			FileInputStream fis = new FileInputStream(f);
			fis.read(b);
			
			String info = "MSFL-FK-2011-0003";
			//scansrv.isExist("sjfkks23eo");
			scansrv.saveImageFile("CATALOG20110321140445.bmp", "1", b, info);
//			scansrv.saveImageFile("CATALOG20110321140445.bmp", "1", b, info);
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		} finally {
			if (ic != null)
				ic.destroy();
		}
		System.exit(status);
	}
	
	
	public static byte[] getBytesFromFile(File file) throws IOException {

        InputStream is = new FileInputStream(file);
        // 获取文件大小

        long length = file.length();

 

        if (length > Integer.MAX_VALUE) {

            // 文件太大，无法读取

        throw new IOException("File is to large "+file.getName());

        }

 

        // 创建一个数据来保存文件数据

        byte[] bytes = new byte[(int)length];

 

        // 读取数据到byte数组中

        int offset = 0;

        int numRead = 0;

        while (offset < bytes.length

               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {

            offset += numRead;

        }

 

        // 确保所有数据均被读取

        if (offset < bytes.length) {

            throw new IOException("Could not completely read file "+file.getName());

        }

 

        // Close the input stream and return bytes

        is.close();

        return bytes;

    }

}
