package com.exedosoft.plat.ui.customize.ace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOViewTemplate;

/**
 * @author aa
 */
public class HTML extends JS {

	private static Log log = LogFactory.getLog(HTML.class);


	public HTML() {
		this.templateFile = "customize/ace/html.ftl";
	}

	public static void main(String[] args) {

		int i = (int) Math.round( 0.51);
		System.out.println("i:::::::::" + i);
	}

}
