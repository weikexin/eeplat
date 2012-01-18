package com.exedosoft.plat.util;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author  IBM
 */
public class Precomplie {

	public FileFilter fileFilter = new ExtensionFileFilter();

	public java.net.URI rootURI;

	/**
	 * @uml.property  name="v"
	 */
	List v = new java.util.ArrayList();

	public Precomplie(String dir) {
		try {
			rootURI = ClassLoader.getSystemResource(dir).toURI();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
	}

	public void precompileDirectory(File dir) {
		File[] files = dir.listFiles(fileFilter);
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				precompileDirectory(files[i]);
			} else {
				v.add(getClass(files[i]));
			}
		}
	}

	private Class getClass(File file) {
		String ClassName = file.getAbsolutePath();
		String ClassPath = "";
		// ClassLoader.getSystemResource()
		int i = ClassName.indexOf(".");
		if (i > 0) {
			ClassName = ClassName.substring(0, i);
			String[] s = ClassName.split("\\\\");
			for (int j = 2; j < s.length; j++) {
				ClassPath = ClassPath + s[j] + ".";
			}
			ClassPath = ClassPath.substring(0, ClassPath.length() - 1);
			int y = ClassPath.indexOf("lib");
			if (y > 0) {
				ClassPath = ClassPath.substring(y + 4, ClassPath.length());
			}
			y = ClassPath.indexOf("classes");
			if (y > 0) {
				ClassPath = ClassPath.substring(y + 8, ClassPath.length());
			}
			try {
				Class c = Class.forName(ClassPath);
				return c;
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public Object[] getObjects() {
		File f = new File(this.rootURI);
		this.precompileDirectory(f);
		return this.getV().toArray();
	}

	/**
	 * @return
	 * @uml.property  name="v"
	 */
	public List getV() {
		return v;
	}

	/**
	 * @param v
	 * @uml.property  name="v"
	 */
	public void setV(List v) {
		this.v = v;
	}

	public static void main(String[] args) {
		// System.out.println("e:\\dd".replaceAll("\\\\","//"));
		try {
			Precomplie p = new Precomplie("com.exedosoft.plat.util");
			Object[] o = p.getObjects();
			for (int i = 0; i < o.length; i++) {
				System.out.println(o.getClass().toString());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
