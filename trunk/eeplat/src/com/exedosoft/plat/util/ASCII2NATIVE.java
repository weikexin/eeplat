/**
 * 
 */
package com.exedosoft.plat.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author Administrator
 * 
 */
public class ASCII2NATIVE {

	/**
	 * 
	 */
	public ASCII2NATIVE() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		File f = new File(
				"c:\\a.sql");
		File f2 = new File(
		"c:\\a2.sql");
		if (f.exists() && f.isFile()) {
			// convert param-file
			BufferedReader br = null;
			StringBuffer sb = new StringBuffer();
			String  line;

			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(f), "JISAutoDetect"));

				while ((line = br.readLine()) != null) {
					System.out.println(ascii2native(line));
					sb.append(ascii2native(line)).append(";\n\r");
				}
				
				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new  FileOutputStream(f2),"utf-8"));
				out.append(sb.toString());
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				System.err.println("file not found - " + f);
			} catch (IOException e) {
				System.err.println("read error - " + f);
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (Exception e) {
				}
			}
		} else {
			// // convert param-data
			// System.out.print(ascii2native(args[i]));
			// if (i + 1 < args.length)
			// System.out.print(' ');
		}
	}

	/**
	 * core routine
	 */
	public static String ascii2native(String str) {
		String hex = "0123456789ABCDEF";
		StringBuffer buf = new StringBuffer();
		int ptn = 0;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '\\' && i + 1 <= str.length() && str.charAt(i + 1) == '\\') {
				buf.append("\\\\");
				i += 1;
			} else if (c == '\\' && i + 6 <= str.length()
					&& str.charAt(i + 1) == 'u') {
				String sub = str.substring(i + 2, i + 6).toUpperCase();
				int i0 = hex.indexOf(sub.charAt(0));
				int i1 = hex.indexOf(sub.charAt(1));
				int i2 = hex.indexOf(sub.charAt(2));
				int i3 = hex.indexOf(sub.charAt(3));

				if (i0 < 0 || i1 < 0 || i2 < 0 || i3 < 0) {
					buf.append("\\u");
					i += 1;
				} else {
					byte[] data = new byte[2];
					data[0] = i2b(i1 + i0 * 16);
					data[1] = i2b(i3 + i2 * 16);
					try {
						buf.append(new String(data, "UTF-16BE").toString());
					} catch (Exception ex) {
						buf.append("\\u" + sub);
					}
					i += 5;
				}
			} else {
				buf.append(c);
			}
		}

		return buf.toString();
	}

	/**
	 * binary to unsigned integer
	 * <P>
	 * 
	 * @param b
	 *            binary
	 * @return unsined integer
	 */
	private static int b2i(byte b) {
		return (int) ((b < 0) ? 256 + b : b);
	}

	/**
	 * unsigned integer to binary
	 * <P>
	 * 
	 * @param i
	 *            unsigned integer
	 * @return binary
	 */
	private static byte i2b(int i) {
		return (byte) ((i > 127) ? i - 256 : i);
	}

}
