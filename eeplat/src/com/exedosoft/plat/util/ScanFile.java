package com.exedosoft.plat.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Title: </p> <p> Description: </p> <p> Copyright: Copyright (c) 2006 </p> <p> Company: </p>
 * @author  not attributable
 * @version  1.0
 */

public class ScanFile {

	private String[] fileType = new String[] { ".java" };

	public ScanFile() {
		allFiles = new ArrayList();
	}

	/**
	 * @uml.property  name="allFiles"
	 */
	private List allFiles;

	/**
	 * @return
	 * @uml.property  name="allFiles"
	 */
	public List getAllFiles() {
		return allFiles;
	}

	public List parse(String filePath, String[] fileType) {
		
		
		this.fileType = fileType;

		File file = new File(filePath);
		if (file.isDirectory()) {
			readFromPath(file);
		} else {
			readFromFile(file);
		}
		return this.allFiles;
	}

	private void readFromPath(File file) {
		try {
			if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						readFromPath(files[i]);
					} else {
						readFromFile(files[i]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readFromFile(File file) {

		for (int i = 0; i < fileType.length; i++) {
			String aType = fileType[i];
			if (aType.equalsIgnoreCase(".java")) {
				if (file.getName().toLowerCase().endsWith(".java")) {
					String pathName = file.getAbsolutePath().substring(
							file.getAbsolutePath().indexOf("com"),
							file.getAbsolutePath().indexOf(".java"));
					System.out.println(pathName);
					this.allFiles.add(pathName.replace("\\", "."));
				}
			} else {
				if (file.getName().toLowerCase().endsWith(aType)) {
					this.allFiles.add(file.getAbsolutePath());
				}

			}
		}
	}

	public static List getDefaultJavaFiles() {

		ScanFile scanFile = new ScanFile();
		scanFile.parse(System.getProperty("user.dir")
				+ "\\JavaSource\\com\\exedosoft\\plat\\ui\\defaultimp",
				new String[] { ".java" });
		return scanFile.getAllFiles();

	}

	public static void main(String[] args) {

		ScanFile scanFile = new ScanFile();
		scanFile.parse(System.getProperty("user.dir"),
				new String[] { ".jar" });
		System.out.println(scanFile.getAllFiles());
	}

}