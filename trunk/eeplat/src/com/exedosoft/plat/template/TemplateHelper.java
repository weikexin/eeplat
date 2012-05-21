package com.exedosoft.plat.template;

/**
 * @author  IBM
 */
public class TemplateHelper {

	private String uiCode = "";

	/**
	 * @uml.property  name="beforScript"
	 */
	private String beforScript = "";

	/**
	 * @uml.property  name="afterScript"
	 */
	private String afterScript = "";

	public TemplateHelper(String code) {

		if (code.indexOf("/*beforsplit*/") != -1) {
			// ///得到相关的js
			beforScript = code.substring(0, code.indexOf("/*beforsplit*/"));
			// ///得到相关的json config
			code = code.substring((code.indexOf("/*beforsplit*/") + 14));
		}

		if (code.indexOf("/*aftersplit*/") != -1) {
			// ///得到相关的js
			afterScript = code.substring(code.indexOf("/*aftersplit*/") + 14);
			// ///得到相关的json config
			code = code.substring(0, code.indexOf("/*aftersplit*/"));
		}
		uiCode = code;

	}

	public String getHtmlCode() {

		return this.uiCode;

	}

	/**
	 * @return
	 * @uml.property  name="beforScript"
	 */
	public String getBeforScript() {

		return this.beforScript;
	}

	/**
	 * @return
	 * @uml.property  name="afterScript"
	 */
	public String getAfterScript() {

		return this.afterScript;
	}

}
