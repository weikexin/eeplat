package com.exedosoft.plat.ui.customize.ace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOViewTemplate;

/**
 * @author aa
 */
public class CSS extends JS {

	private static Log log = LogFactory.getLog(CSS.class);


	public CSS() {
		this.templateFile = "customize/ace/css.ftl";
	}
	


	public static void main(String[] args) {

		int i = (int) Math.round( 0.51);
		System.out.println("i:::::::::" + i);
	}

}
