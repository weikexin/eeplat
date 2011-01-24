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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpgradeHSqlScript {

	public UpgradeHSqlScript() {
		// TODO Auto-generated constructor stub
	}

	void upgrade() {

		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf") + 7);

		try {

			File oldFile = new File(prefix + "/db/upgrade/old/mydb.script");
			File oldBaseFile = new File(prefix
					+ "/db/upgrade/old_base/mydb.script");
			File newFile = new File(prefix + "/db/upgrade/new/mydb.script");
			File newBaseFile = new File(prefix
					+ "/db/upgrade/new_base/mydb.script");

			List<String> oldFileLines = getStrList(oldFile);
			List<String> oldBaseFileLines = getStrList(oldBaseFile);

			StringBuilder addLines = new StringBuilder();
			for (Iterator<String> it = oldFileLines.iterator(); it.hasNext();) {
				String aLine = it.next();
				if (!oldBaseFileLines.contains(aLine)) {
					addLines.append(aLine).append("\n");
				}
			}

			List<String> newBaseFileLines = getStrList(newBaseFile);
			StringBuilder finalLines = new StringBuilder();

			// ////////整理new_base/mydb.script,把已经修改或者删除的缺省实现数据去掉
			oldBaseFileLines.removeAll(oldFileLines);
			for (Iterator<String> itNewBase = newBaseFileLines.iterator(); itNewBase
					.hasNext();) {
				String aNewLine = itNewBase.next();

				boolean isModi = false;

				if (aNewLine.indexOf(" VALUES(") != -1) {

					String newObjuid = aNewLine.substring(aNewLine
							.indexOf(" VALUES(") + 9, aNewLine
							.indexOf(" VALUES(") + 41);
					for (Iterator<String> it = oldBaseFileLines.iterator(); it
							.hasNext();) {
						String aLine = it.next();
						if (aLine.indexOf(" VALUES(") != -1) {
							String objuid = aLine.substring(aLine
									.indexOf(" VALUES(") + 9, aLine
									.indexOf(" VALUES(") + 41);
							if (newObjuid.equals(objuid)) {
								isModi = true;
								break;
							}
						}
					}
				}

				if (!isModi) {
					finalLines.append(aNewLine).append("\n");
				}
			}

			// ////加上“增加的” lines
			finalLines.append(addLines);

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(newFile), "utf-8"));
			out.append(finalLines.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(prefix);

	}
	
	
	void test(){
		
		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf") + 7);

		try {

			File oldFile = new File(prefix + "/db/upgrade/old/mydb.script");
			File newFile = new File(prefix + "/db/upgrade/new/mydb.script");

			List<String> oldFileLines = getStrList(oldFile);
			List<String> newFileLines = getStrList(newFile);

			for (Iterator<String> it = newFileLines.iterator(); it.hasNext();) {
				String aLine = it.next();
				if (!oldFileLines.contains(aLine)) {
					System.out.println("Dif Lines::" + aLine);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(prefix);

		
   }

	private List<String> getStrList(File aFile)
			throws UnsupportedEncodingException, FileNotFoundException,
			IOException {
		List<String> strLines = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(aFile), "utf-8"));
		while (true) {
			String aLine = in.readLine();
			if (aLine == null) {
				break;
			}
			strLines.add(aLine);

		}
		in.close();
		return strLines;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		UpgradeHSqlScript uhs = new UpgradeHSqlScript();
		uhs.upgrade();

	}

}
