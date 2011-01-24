package com.exedosoft.plat.util;

import java.io.File;
import java.io.FileFilter;

public class ExtensionFileFilter implements FileFilter {

	public ExtensionFileFilter() {
	}

	/**
	 * Tests whether or not the specified abstract pathname should be included
	 * in a pathname list.
	 * 
	 * @param pathname
	 *            The abstract pathname to be tested
	 * @return <code>true</code> if and only if <code>pathname</code> should
	 *         be included
	 * @todo Implement this java.io.FileFilter method
	 */
	public boolean accept(File file) {
		// 只选择路径
		// if (file.isDirectory()) {
		// return true;
		// } else {
		// return false;
		// }
		return true;
	}


}
